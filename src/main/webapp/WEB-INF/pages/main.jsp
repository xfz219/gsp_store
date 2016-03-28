<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@ include file="taglibs.jsp" %>
<jsp:include page="./editPwd.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>普惠金融</title>
</head>
<body class="easyui-layout">

<div region="north" border="true" style="height: 55px;">
    <%@ include file="header.jsp" %></div>	
	<div data-options="region:'west',split:true,title:'管理'"
		style="height: 200px; padding: 1px; width: 200px;">
		<div style="margin: 10px 0;">
			<ul class="easyui-tree" id="treeView"></ul>
		</div>
	</div>
	<div id="mainPanle" region="center" border="true" border="false">
		 <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
                <div title="主页">
				<div class="cs-home-remark">
					<h1>这里是app监测系统主页</h1> <br>
				</div>
				</div>
        </div>
	</div>
	<div data-options="region:'south',border:'none'" style="height:20px;"><%@ include file="footer.jsp" %></div>
<!-- 菜单树、选项卡 -->
<script type="text/javascript" charset="UTF-8">
var _treeView = $('#treeView');//菜单栏
var _centerTabs = $('#tabs');//选项卡栏
$(function(){
	//加载左侧菜单树
	_treeView.tree({       
		 checkbox: false, 			 
		 url:"<%=request.getContextPath()%>/treeView?id=",
		 onBeforeExpand:function(node){
			 _treeView.tree("options").url="<%=request.getContextPath()%>/treeView?id="+node.id;
		 },
		 onClick:function(node){
			 var click_obj = $('#treeView').tree('getSelected');
			 if(click_obj.state == 'open' && click_obj.attributes == null){
				 _treeView.tree('collapse',node.target);
			 }else if(click_obj.state == 'closed' && click_obj.attributes == null){
				 _treeView.tree('expand',node.target);
			 }else{
				    var _tit = node.text;
					var _contant = node.attributes["url"];
				    var _tabCls = node.iconCls;
					addNewTab(_tit, _contant, _tabCls, node.id);
			 }
		 }
	}); 
	
	//给菜单添加点击事件
	$('.easyui-tree a').click(function() {
	    var _tit = $(this).html(),
	    _contant = $(this).attr("href"),
	    _tabCls = $(this).attr("tabCls");
	    addNewTab(_tit, _contant, _tabCls, $(this).attr("id"));
	    return false;
	});
	
	//加载选项卡
	_centerTabs.tabs({
		fit : true,
		border : false,
		onContextMenu : function(e, title) {
			e.preventDefault();
			tabsMenu.menu('show', {
				left : e.pageX,
				top : e.pageY
			}).data('tabTitle', title);
		}
	});	
	
	//添加选项卡
	function addNewTab(title, href, tabCls, nodeId){
		 //选项卡索引
		 var index;
		 
		 //选择存在的选项卡
		 $(_centerTabs.tabs('tabs')).each(function(i){
			 if(this[0].id == 'tree'+nodeId){
				 index = _centerTabs.tabs('getTabIndex',this);
				 _centerTabs.tabs('select', index);
				 return false;
			 }
		 });
		 
		 //不存在，则创建选项卡
		if(!_centerTabs.tabs('exists',index)){
			var content = '木有可以加载的页面';
			if(Boolean(href)){
				content = '<iframe scrolling="no" frameborder="0"  src="'+ href + '" style="width:100%;height:99%;"></iframe>';
			}
			
			_centerTabs.tabs({
				scrollIncrement : 100
			}).tabs('add', {
				id : 'tree'+nodeId,
				title : title,
				content : content,
				closable : true,
				iconCls : tabCls
			});
		}
	 }
});
</script>

<!-- 修改密码 -->
<script type="text/javascript" charset="UTF-8">
var RANKMIDDLE_INFO = '复杂度还行，再加强一下等级？';
var RANK_MIDDLE = '中等';
var RANKLOW_INFO = '试试字母、数字、标点的组合吧';
var RANK_LOW = '弱';
var RANKHIGH_INFO = '密码强度好,请牢记! ';
var RANK_HIGH = '强';

var _editCurrentUserPwdForm = $('#editCurrentUserPwdForm');
var _editPwd = $("#editPwd");//修改密码对话框
var _newPwd = $("#newPwd");
var _pwd_info = $("#pwd_info");
var _pwd_tips = $("#pwd_tips");
var _pwd_result = $("#pwd_result");
var _oldPwd = $("#oldPwd");
var _rePwd = $("#rePwd");
var _pwd_tip1 = $("#pwd_tip1");
var _pwd_tip2 = $("#pwd_tip2");
var _pwd_tip3 = $("#pwd_tip3");

$(function(){
	//加载修改密码对话框
	_editPwd.show().dialog({
		   modal:true,
	       title:"修改密码",
	       height:220,
		   width:450,
	       buttons:[{
	    	   text:"修改",
	           handler:function(){
	        	   if(_editCurrentUserPwdForm.form('validate')){
	        		   var newPwd = _newPwd.val();
	        		   var rank = countRank(newPwd);
	        		   if(rank<=1){
	        		  		$.messager.alert('提示信息',"您的密码强度太低,请重新录入!");
	        		  		return;
	        		  	}
	        		   if(rank<=0){
	        			   $.messager.alert('提示信息',"密码不符合规范");
	        		  		return;
	        		   }
	        		  	var params = _editCurrentUserPwdForm.serializeArray();   //自动将form表单封装成json
	        		  	$.post("${ctx}/editCurrentStaffPwd", params,
	       		  			function(data, textStatu) {
	       		  			    $.messager.alert('提示信息', data.msg);
	       		  			},"json"
       		  			).error(function(XMLHttpRequest, textStatus, errorThrown) {
       		  			    $.messager.alert('提示信息', XMLHttpRequest.responseText);
       		  			});
	   				 	_editPwd.dialog('close');
	   			 }else{
	   				Msgfade("还有必填信息未填写");
	   			} 	
	              }
	       },{
	    	   text:'取消',
			   handler:function(){
				   _editPwd.dialog('close');
				}
	       }
	    ],
	    onLoad:function(){
	    	console.log(777);
	    	alert(123);
	    	if(_oldPwd.val() != '' || null != _oldPwd.val()){
	    		_oldPwd.val('');
	    	}
	    	if(_newPwd.val() != '' || null != _newPwd.val()){
	    		_newPwd.val('');
	    	}
	    	if(_rePwd.val() != '' || null != _rePwd.val()){
	    		_rePwd.val('');
	    	}
	    }
	}).dialog('close');
	_pwd_info.hide();
	_pwd_tips.hide();
	_pwd_result.hide();
});

//显示修改密码对话框
function editPwdDialog(){
	 _editPwd.dialog('open');
}

//添加焦点事件
_newPwd.focus(function(){
	_pwd_tips.show();
	var newPwd = _newPwd.val();
	showPwdTips(newPwd);
}); 

//添加失去焦点事件
_newPwd.blur(function(){
	_pwd_tips.hide();
	//计算密码强度
	showPwdRank();
});

//添加值变化事件
_newPwd.bind('input propertychange', function(){
	showPwdTips($(this).val());
});

//添加键盘事件
_newPwd.bind('keypress', function(){
	showPwdTips($(this).val());
});

//添加键盘事件
_newPwd.bind('keydown', function(){
	showPwdTips($(this).val());
});

//添加焦点事件
_oldPwd.focus(function(){
	_pwd_tips.hide();
	_pwd_result.hide();
	var newPwd = _newPwd.val();
	if(newPwd != null && '' != newPwd){
		showPwdRank();
	}
});

//添加焦点事件
_rePwd.focus(function(){
	showPwdRank();
});

//检索明文密码的规则
function showPwdTips(newPwd){
	_pwd_tips.show();
	if(null== newPwd || "" == newPwd){
		console.log('newpwd is null');
		_pwd_tip1.attr('class',"default");
		_pwd_tip2.attr('class',"default");
		_pwd_tip3.attr('class',"default");
		return;
	}
	if(newPwd.length >=8 && newPwd.length<=12){
		_pwd_tip1.attr('class','yes');
	}else{
		_pwd_tip1.attr('class',"no red");
	}
	if(/^\S+$/.test(newPwd)){
		_pwd_tip3.attr('class',"yes");
	}else{
		_pwd_tip3.attr('class',"no red");
	}
	if(/^\d+$/.test(newPwd)){
		_pwd_tip2.attr('class',"no red");
	}else{
		_pwd_tip2.attr('class',"yes");
	}
}

//检索明文密码强度
function showPwdRank(){
	var newPwd = _newPwd.val();
	if(null == newPwd || '' == newPwd){
		_pwd_tips.show();
		_pwd_tip1.attr("default");
		_pwd_tip2.attr("default");
		_pwd_tip3.attr("default");
	}else{
		var rank = countRank(newPwd);
		if(0==rank){
			showPwdTips(newPwd);
		}else{
			_pwd_tips.hide();
			_pwd_result.show();
			switch (rank) {
				case 1: //弱
				 	 $('#password_pic').attr('class','rankLow');
				 	 $('#password_pic').text(RANK_LOW);
				 	 $('#password_info').text(RANKLOW_INFO);
				break;
				case 2: //中等
					 $('#password_pic').attr('class','rankMiddle');
					 $('#password_pic').text(RANK_MIDDLE);
					 $('#password_info').text(RANKMIDDLE_INFO);
				break;
				case 3: //强
					 $('#password_pic').attr('class','rankHigh');
					 $('#password_pic').text(RANK_HIGH);
					 $('#password_info').text(RANKHIGH_INFO);
				break;
			default:
				console.log('密码强度计算有误');
				showPwdTips(newPwd);
				break;
			}
		}
	}	
}

/**
 * 计算密码强度 
 * 2014-07-19 by zhangming
 * 1.如果不符合规范的密码(详见tips三条) 返回0 继续显示tip框
 * 2.密码强度计算
 *		1.至少包含两个不同字母并且包含大小写 +1
 *      2.包含特殊字符 +1
 */
 function countRank(newPwd){
	 //小于6位 或者大于 16位 不合规
	 if (newPwd.length < 8 || newPwd.length > 12){
		 return 0;
	 }
	 //如果密码中含有空格 不合规
	 if(!/^\S+$/.test(newPwd)){
		 return 0;
	 }
	 //纯数字 不合规
	 if(/^\d+$/.test(newPwd)){
		 return  0;
	 }
	 var rank = 1;
	 
	 //校验至少两个不同的字母 并且含有大写字母
	 if(countDiffLetter(newPwd)>=2 && /[A-Z]/.test(newPwd)){
		 rank++;
	 }
	 if (/\W/.test(newPwd)){ //特殊字符    
		 rank++;
	 }
	 return rank;
	
}

function countDiffLetter(newPwd){
	 var temp = new Object();
	 for(var i=0;i<newPwd.length;i++){
		 if (/[a-zA-Z]/.test(newPwd[i])){ //包括大小写
			temp[newPwd[i]]=1;
		 };
	 }	
	 var k=0;
	 for(var j in temp){
		 k++;
	 }
	 return k;
};
</script>
</body>
</html>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>线索管理</title>
<style>
.box table{
 	width:100% !important; 
}
.query{
		padding:5px 10px;
}
.query tr{
	height: 25px;
}
form .label{
	width:90px;
	text-align:right;
	
}
.box .datagrid-header-inner{
	width:100% !important; 
}
.box .datagrid .datagrid-pager table{
   width:auto !important; 
}
.box .datagrid-body{
overflow:hidden;
}
.type{
width:175px;
height: 20px;
}
</style>
<script type="text/javascript" src="${ctx}/static/js/common_datagrid.js"></script>
<script>
 var grid;
 var i = 0;
 var paramData = {};
 var i = 0;
	
$(function(){
	grid = $('#datagrid').datagrid({
		nowrap: false,
		striped: true,
		fit: true,
		url:'${ctx}/customerClues/selectCustomerCluesMethod',
	    columns:[[
			{field:'id',title:'id',align:'center',hidden:true},
			{field:'toPromoteId',title:'toPromoteId',hidden:true},
			{field:'department',title:'department',hidden:true},
			{field:'name',title:'客户姓名',align:'center',width:110},
			{field:'mobile',title:'手机号码',align:'center',width:110},
			{field:'registered',title:'是否注册app',align:'center',width:110},
			{field:'city',title:'借款城市',align:'center',width:110},
			{field:'branch',title:'门店',align:'center',width:147},
			{field:'sales',title:'是否绑定销售',align:'center',width:110},
			{field:'salesName',title:'销售姓名',align:'center',width:110},
			{field:'salesNo',title:'销售工号',align:'center',width:80},
			{field:'salesStatus',title:'销售状态',align:'center',width:80},
			{field:'channel',title:'进件渠道',align:'center',width:100},
			{field:'createTime',title:'创建时间',align:'center',width:100},
			{field:'updateAllotTime',title:'分配时间',align:'center',width:100},
			{field:'radio',title:'radio',align:'center',hidden:true}
	        
	    ]],
		idField:'id',
		singleSelect:true,
		frozenColumns:
		[[
            {field:'ck',checkbox:true}
		]],
		pagination:true,
		rownumbers:true,
		pageSize: 20,
		toolbar:"#tbLendRequest",
		onLoadSuccess:function (data){
		},
		onSelect: function (rowIndex, rowData) {
			var rows = $('#datagrid').datagrid('getSelections');
				$('#checkUser').hide();
			if (!(rows[0].channelTwo == '1')){
				$('#checkUser').show();
			}
		},
		onLoadError:function (data) {			
			$.messager.alert('提示信息',"查询线索管理失败！");
		}
	});
	
});

function fixWidth(percent) {
	return document.body.clientWidth * percent;
}


//过滤无效属性
function getData(data){
	var temp = {};
	$.each(data,function(key,value){
		if(typeof value != 'undefined' && value != '' && value != null){
			temp[key] = value;
		}
	});
	console.log(temp);
	return temp;
}

//搜索
function searchByConditions(){
	paramData.radio =  $('input:radio:checked').val();
	paramData.name = $('#name').val();
	paramData.mobile = $('#mobile').val();
	paramData.salesName = $('#salesName').val();
	paramData.salesNo = $('#salesNo').val();
	paramData.channel = $('#requestChannel').combobox('getValue');
	paramData.shopCode = $('#shopCode').combobox('getValue');
	grid.datagrid('load', getData(paramData));
	grid.datagrid('clearSelections'); 
}


//重置
function resetConditions(){
	$('#name').val('');
	$('#mobile').val('');
	$('#mobile').val('');
	$('#salesName').val('');
	$('#salesNo').val('');
	$('#requestChannel').combobox("setValue", '');
	$('#department').combobox("setValue", '');
	$("#salesStatus").combobox("setValue", '');
}

function bindingUserA() {
	var rows = $('#datagrid').datagrid('getSelections');
	if(rows.length<1){
		$.messager.alert('提示信息','请选择一条记录！');
	}else{
	$('#addBindingUser').dialog({
		title: "绑定",
	   	width: 400,
	   	height: 200,
	   	modal: true,
	   	closed:false
	});
	}
}
	
function YES() {
	var rows = $('#datagrid').datagrid('getSelections');
 	if(typeof $('#pid').combobox('getValue') != '' && $('#selectUserName').combobox('getValue') != ''){
    	$.messager.confirm('确定','您确定要绑定吗？',function(r){
        	if(r){
       			$.ajax({
       			    url: '${ctx}/customerClues/updateBindingUserMethod',
       			    data:{"selectUserName": $('#selectUserName').combobox('getValue'),"toPromoteId":rows[0].toPromoteId
       			    },
       			    type: 'POST',
       			    cache: false,
       			    dataType: "json",//返回值类型  
       			    async:false,
       			    success: function() {
       			        	$('#addBindingUser').dialog('close');
       			        	searchByConditions();
       			    },
       			    error: function(XMLHttpRequest, textStatus, errorThrown) {
       			        $.messager.alert('提示信息', "请求出现错误！");
       			    }
       			});
         	}
      	});
   	}else{
   		$.messager.alert('提示信息', '组别与姓名不可为空！');
   	}
}

function NO() {
	$('#addBindingUser').dialog({
	   	closed:true
	});
}

//详情
function checkUser(){
	var rows = grid.datagrid('getSelections');
    if (rows.length < 1) {
        $.messager.alert('提示信息', '请选择一条记录！');
    }else if(rows.length > 1){
    	$.messager.alert('提示信息','只能选择单条记录！');	
    }else {
    	var rows = $('#datagrid').datagrid('getSelections');
		$("#checkUserDialog").dialog({  
			iconCls:'icon-search',
			title:"客户信息",
			width:550,
			height:300,
			modal:true,
			href:'${ctx}/html/user/userCheck.jsp',
		    onLoad:function(){
		    	var id = rows[0].toPromoteId;
		    	$(document).ready(function(){
		    	 $.ajax({
		             url    : "${ctx}/customerClues/findUser?id="+id,
		             type   : "POST",
		             async: false, 
		             success: function (data) { 
		            	$("#checkId").val(data.id);
		            	$("#checkUserName").val(data.name);
		             	$("#checkUserIdNo").val(data.idNo);
		             	$("#checkUserAmount").val(data.amount);
		             	$("#checkUserMobile").val(data.mobile);
		             	$('#checkUserCity').val(data.city);
		             	$('#checkUserProvince').val(data.province);
		             	if(data.channel == '326'){
		             		$('#defect_data1').show();
		             		$('#defect_data2').show();
		             		$("#checkUserProductName").val(data.productName);
		             		if(data.isSettle){
		             			$("#checkUserIsSettle").val('是');
		             		}else{
		             			$("#checkUserIsSettle").val('否');
		             		}
			             	if(data.settleTime){
			             		$('#checkUserSettleTime').datebox('setValue',myformatter(new Date(data.settleTime)));
			             		
			             	}else{
			             		$('#checkUserSettleTime').datebox('setValue','');
			             	}
			             	
		             	}
		             
		             }
		    
		         });
		    	 
      });
    }
	})
    }};
    
    $(document).ready(function(){
   	 //产生一个在下拉框中的树，也就是组合树
   	$('#pid').combotree({
   	        url : '${ctx}/appUtil/getOrgTreeShop',
   	        idFiled:'id',
   	        textFiled:'name', 
   	        parentField : 'pid',
   	        checkbox:true,
   	        lines : true,
   	        panelHeight : '300',
   	        onLoadSuccess: function (node, data) {
   	            $('#pid').combotree('tree').tree("collapseAll"); 
   	            
   	        	$("#pid").combobox({
   	        		onChange: function (n,o) {
   	        			
   	        		$('#selectUserName').combobox('clear');
   	        		$.ajax({
   	        		    url: '${ctx}/changeCustomer/selectUserNameMethod',
   	        		    data:{"oid": $('#pid').combobox('getValue')
   	        		    },
   	        		    type: 'POST',
   	        		    cache: false,
   	        		    dataType: "json",//返回值类型  
   	        		    async:false,
   	        		    success: function(data) {
   	        		    	$("#selectUserName").combobox("loadData", data);
   	        		    },
   	        		    error: function(XMLHttpRequest, textStatus, errorThrown) {
   	        		        $.messager.alert('提示信息', "请求出现错误！");
   	        		    }
   	        		});
   	        		}
   	        		});
          }
   	});
   });
    
    $(document).ready(function () {
    	$("#pid").combobox({
    	onChange: function (n,o) {
    	$.ajax({
    	    url: '${ctx}/customerClues/selectUserNameMethod',
    	    data:{"department": $('#pid').combobox('getValue')
    	    },
    	    type: 'POST',
    	    cache: false,
    	    dataType: "json",//返回值类型  
    	    async:false,
    	    success: function(data) {
    	    	$("#selectUserName").combobox("loadData", data);
    	    },
    	    error: function(XMLHttpRequest, textStatus, errorThrown) {
    	        $.messager.alert('提示信息', "请求出现错误！");
    	    }
    	});
    	}
    	});
    	}); 
    
	function myformatter(date){  
	    var y = date.getFullYear();  
	    var m = date.getMonth()+1;  
	    var d = date.getDate();  
	    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
	}  
	
	$(document).ready(function(){
		//查询进件渠道
		$("#requestChannel").combobox({
			valueField:'codeValue',
			textField:'codeName',
			required:false,
			url:'${ctx}/customerClues/queryChannel',
			onSelect:function(data){
		
			}
		}); 
	});
	
	 $(document).ready(function(){
	   	 //产生一个在下拉框中的树，也就是组合树
	   	$('#shopCode').combotree({
	   	        url : '${ctx}/appUtil/getOrgTreeShop',
	   	        idFiled:'id',
	   	        textFiled:'name', 
	   	        parentField : 'pid',
	   	        checkbox:true,
	   	        lines : true,
	   	        panelHeight : '300',
	   	        onLoadSuccess: function (node, data) {
	   	            $('#shopCode').combotree('tree').tree("collapseAll"); 
	   	            
	   	        	$("#shopCode").combobox({
	   	        		onChange: function (n,o) {
	   	        			
	   	        		$('#selectUserName').combobox('clear');
	   	        		$.ajax({
	   	        		    url: '${ctx}/changeCustomer/selectUserNameMethod',
	   	        		    data:{"oid": $('#shopCode').combobox('getValue')
	   	        		    },
	   	        		    type: 'POST',
	   	        		    cache: false,
	   	        		    dataType: "json",//返回值类型  
	   	        		    async:false,
	   	        		    success: function(data) {
	   	        		    	$("#selectUserName").combobox("loadData", data);
	   	        		    },
	   	        		    error: function(XMLHttpRequest, textStatus, errorThrown) {
	   	        		        $.messager.alert('提示信息', "请求出现错误！");
	   	        		    }
	   	        		});
	   	        		}
	   	        		});
	          }
	   	});
	   });
</script>

</head>
<body class="easyui-layout">
<div id="checkUserDialog"></div>
<div data-options="region:'center'" style="overflow: hidden;">
		<table id="datagrid"></table>
</div>
	<div id="addBindingUser" class="easyui-dialog" title="绑定" style="width:400px;height:200px;" data-options="iconCls:'icon-save',resizable:true,closed:true" >
   		 <div style="margin: 20px 0px 0px 80px;">
   		 	<label style="margin: 0px 0px 0px 50px;">请选择需要绑定的销售</label>
   		 	<br><br><br>
   		 	<label>机构：</label>
			   <input class="easyui-combobox" data-options="editable:false" id='pid'/>
   		 	<br><br>
   		 	<label>姓名：</label>
			<input class="easyui-combobox" id='selectUserName' name="selectUserName" data-options="valueField:'selectUserCode', textField:'selectUserName', panelHeight:'auto',editable:false" />
			<br><br>
			<div style="margin: 0px 0px 0px 50px;">
				<a href="#" onclick="YES();" class="easyui-linkbutton">确认</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" onclick="NO();" class="easyui-linkbutton">取消</a>
			</div>
   		 </div>
	</div>
	<div id="tbLendRequest" style="width: 1800px">
		<br>
		&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="radio" value="1" >仅显示未绑定销售
		&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="radio" value="2" checked>显示全部
        <br><br>
			&nbsp;&nbsp;&nbsp;&nbsp;客户姓名：<input type="text" id="name" name="name" />&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;客户手机：<input type="text" id="mobile" name="mobile" />&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;门店机构： 
			<input class="easyui-combobox" data-options="editable:false" id='shopCode'/>
			<br>
			&nbsp;&nbsp;&nbsp;&nbsp;销售姓名：<input type="text" id="salesName" name="salesName" />&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;销售工号：<input type="text" id="salesNo" name="salesNo" />&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;进件渠道：	<select   style="width:168" id="requestChannel" name="requestChannel"  class="easyui-combobox" data-options="editable:false"></select>
			<a  href="javascript:void(0);" class="easyui-linkbutton search" iconCls="icon-search" plain="true" onclick="searchByConditions();">搜索</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
			<a  href="javascript:void(0);" class="easyui-linkbutton clear" iconCls="icon-remove" plain="true" onclick="resetConditions();">重置</a>
			<br><br>
			&nbsp;&nbsp;&nbsp;&nbsp;<a id="addChangeMobileDialog" href="#" class="easyui-linkbutton l-btn" onclick="bindingUserA();"><span class="l-btn-text">绑定</span></a>
			&nbsp;&nbsp;&nbsp;&nbsp;<a id="checkUser" style="display: none" href="#" class="easyui-linkbutton l-btn" onclick="checkUser();"><span class="l-btn-text">详情</span></a>
			<br><br>
			
	</div>
	
</body>
</html>
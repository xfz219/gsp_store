<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/static/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/static/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>
<link rel="stylesheet"  href="${ctx}/static/ueditor1_4_3-utf8-jsp/themes/default/css/ueditor.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告查询</title>
<style type="text/css">
	fieldset{
		width:1665px;
	}
	
	.one{
		text-align: right;
		height:30px;
		width:160px;
	}
	
	.two{
		text-align: left;
		height:30px;
		width:150px;
	}
	
	.prov,.city,.dist{
		width:150px;
	}
	.town{
		width:150px;
	}
	
	.number{
		width:150px;
	}
	
	.contractTable{
		float:left;
		margin:0px 8px;
	}
	
	.contractLabel{
		float: left;
		margin: 10px 20px;
	}
	
	select{
		height:22px;border: 1px solid #7F9DB9;
	}
	table td input{
		width: 151px;border: 1px solid #7F9DB9;
	}
	 #staffFormAdd .easyui-validatebox,.easyui-datebox,.easyui-numberbox{
		width: 151px; 
		editable: false; 
	} 
	 #staffFormAdd .easyui-combobox,.easyui-combobox{ 
		width: 151px; 
		editable: false; 
	 } 
	.combobox-item{
		height:12px;
	}
</style>
<body style="overflow:auto;">
<div>
<div id="lookdialog" style="text-align:center;padding-top:20px;"></div>
		<form id="addNoticeForm" class="easyui-form" method="post" data-options="novalidate:true">
			<div title="" style="padding: 10px;">
			
				<fieldset>
					<legend>添加公告 &nbsp;&nbsp;&nbsp;<font color="red">*必填</font></legend>
					<table>
						<tr>
						
							<td class="one">标题：</td>
							<td class="two">
								<input id="noticeTitle" name="noticeTitle" class="easyui-validatebox" placeholder="50个字以内" style="width: 590px;height: 26px" maxlength="50" data-options="required:true">
							</td>
							</tr>
							<tr style="height: 183px">
							<td class="one">摘要：</td>
							<td class="two">
								<textarea id="noticeAbstract" name="noticeAbstract" class="easyui-validatebox" style="width: 590px;height: 126px"  data-options="required:true"></textarea>
							</td>
							</tr>
							<tr style="height: 23px">
							<td class="one">通知部门：</td>
							<td class="two">
								<!-- <input type="text" id="noticeDepartment" name="noticeDepartment" /> -->
								<select id="noticeDepartment" name="noticeDepartment" class="easyui-combotree" style="width:260px;" data-options="multiple:true,required:true,panelHeight:120, editable:false"></select>  
								<!-- <select id="noticeDepartment" name="noticeDepartment" class="easyui-combobox" style="width:260px;" data-options="multiple:true,required:true,panelHeight:120, editable:false"></select> -->
							</td>
							</tr>
							<tr style="height:413px">
							<td class="one" >内容：</td>
							<td class="two">
								  <script type="text/plain" id="myEditor" style="width:1200px;height:400px;margin:30spx 4px;margin-left: ">${noticeContent}</script>  
							</td>
							</tr>
							</table>
							</fieldset>
							<div style="float:right;margin:10px">
				<a class="easyui-linkbutton" id="add">保存</a>
					&nbsp;&nbsp;
				<a class="easyui-linkbutton" id="close">关闭</a>
			
			</div>
			</div>
			</form>

</body>

<script type="text/javascript">
	$(document).ready(function(){
		 //产生一个在下拉框中的树，也就是组合树
		$('#noticeDepartment').combotree({
		        url : '${ctx}/userManage/getOrgShopTree',
		        idFiled:'id',
		        textFiled:'name', 
		        parentField : 'pid',
		        checkbox:true,
		        lines : true,
		        panelHeight : '300',
		        onLoadSuccess: function (node, data) {
		            $('#noticeDepartment').combotree('tree').tree("collapseAll"); 
	            }
		}); 
		 
	});
</script>

<script language="javascript">  
  
        $(document).ready(function() {  
            var editor = new baidu.editor.ui.Editor({  
                textarea : 'noticeContent'  
            });  
            editor.render("myEditor"); 
            
            
            //添加
            $('#add').click(function(){
            	$.messager.confirm('提示信息','确认保存吗?',function(r){  
            		if(r){
            	$('#addNoticeForm').form('submit',{
            		 url:'./LendNotice/addLendNotice',
            		 onSubmit: function(){ 
            			 if($('#addNoticeForm').form('validate')){
            				 if(!editor.hasContents()){
            					 $.messager.alert('提示信息','请编辑内容！');
                     			return false; 
            				 }else{
            					 return true;
            				 }
            			 }else{
            				 return false;
            			 }
            		 }, 
            		  success:function(data){  
            			  data=eval("(" + data+ ")");
            			  if(data.status=='success'){
            				  $.messager.alert('提示信息',data.result,'info',function(){
  				        		var tab = parent.$("#tabs").tabs("getTab","系统公告");
  					        	var url = $(tab.panel('options').content).attr('src');
  					        	parent.$('#tabs').tabs('update', {
  					        	      tab : tab,
  					        	      options : {
  					        	      	src : url
  					        	      }
  					        	});
  					        	parent.$("#tabs").tabs("select","系统公告");
  					        	parent.$("#tabs").tabs("close","添加公告");
  				        	});
            			  }else{
            				  $.messager.alert('提示信息',data.result);
            			  }
            		  }
            	});
            	}
            	});
            	});
            	
         
						      
            
           
            
           
           
            //关闭
            $('#close').click(function(){
            	$.messager.confirm('提示信息',"确认关闭吗？",function(r){    
        		    if (r){ 
        		    	 parent.$("#tabs").tabs("close","添加公告");
        		    }
        		    });
            	
            });
           
           
        });  
  
    </script>  
</html>


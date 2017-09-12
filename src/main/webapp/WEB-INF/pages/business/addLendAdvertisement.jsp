<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加广告位</title>
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
<link rel="stylesheet" type="text/css" href="${ctx}/static/js/uploadify/uploadify.css">
<script type="text/javascript" src="${ctx}/static/js/uploadify/jquery.uploadify.min.js?v=<%=System.nanoTime()%>"></script>

<body style="overflow:auto;">
	<div id="lookdialog" style="text-align:center;padding-top:20px;"></div>
		<form id="addAdForm" class="easyui-form" enctype="multipart/form-data" method="post" >
			<div title="添加广告" style="padding: 10px;">
				<fieldset>
					<legend>添加公告 &nbsp;&nbsp;&nbsp;<font color="red">*必填</font></legend>
					<table id="adTable" name="adTable">
						<tr>
							<td class="one">广告位名称：</td>
							<td class="two">
								<input type="text" name="name" id="name" placeholder="20字以内" style="width: 200px" class="easyui-validatebox" data-options="required:true">
							</td>
						</tr>
						<tr>
							<td class="one">广告跳转链接：</td>
							<td class="two">
								<input type="text" name="url" id="url" style="width:500px" class="easyui-validatebox" >
							</td>
						</tr>
						<tr>
							<td class="one">客户身份：</td>
							<td class="two">
								<select id="customerIdentity" class="easyui-combobox" name="customerIdentity">
									<option value="0">薪类客户</option>
									<option value="1">商类客户</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="one">是否启用：</td>
							<td class="two">
								<select id="enabled" class="easyui-combobox" name="enabled">
									<option value="0">否</option>
									<option value="1">是</option>
								</select>
							</td>
						</tr>
						<tr id="adFile">
							<td class="one">上传图片:</td>
							<td><input type="file" id="file" name="file"/></td>
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
		$(document).ready(function() {
	        //添加
	        $('#add').click(function(){
	        	$.messager.confirm('提示信息','确认保存吗?',function(r){
	        		if(r){
						$('#addAdForm').form('submit',{
							 url:'<%=request.getContextPath() %>/lendAdvertisement/saveLendAdvertisement',
							 onSubmit: function(){
								 if($('#addAdForm').form('validate')){
									 return true;
								 }else{
									 return false;
								 }
							 },
							success:function(data){
								data=eval("(" + data+ ")");
								if(data.status=='success'){
									$.messager.alert('提示信息',data.result,'info',function(){
										var tab = parent.$("#tabs").tabs("getTab","广告位管理");
										var url = $(tab.panel('options').content).attr('src');
										parent.$('#tabs').tabs('update', {
											  tab : tab,
											  options : {
												src : url
											  }
										});
										parent.$("#tabs").tabs("select","广告位管理");
										parent.$("#tabs").tabs("close","添加广告位");
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
	    		    	 parent.$("#tabs").tabs("close","添加广告位");
	    		    }
	    		});
	        });
		});
	</script>

</html>


<%@page import="com.puhui.model.uc.Staff"%>
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
					<input type="hidden" id="id" name="id" value="${lendAdvertisement.id }">
					<table id="adTable" name="adTable">
						<tr>
							<td class="one">广告位名称：</td>
							<td class="two">
								<input type="text" name="name" id="name" value="${lendAdvertisement.name }" placeholder="20字以内" style="width: 200px" class="easyui-validatebox" data-options="required:true">
							</td>
						</tr>
						<tr>
							<td class="one">广告跳转链接：</td>
							<td class="two">
								<input type="text" name="url" id="url" value="${lendAdvertisement.url }" style="width:500px" class="easyui-validatebox" >
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
							<td class="one">客户进件状态：</td>
							<td class="two">
								<select id="customerLendStatus" class="easyui-combobox" name="customerLendStatus">
									<option value="0">非放款成功</option>
									<option value="1">放款成功</option>
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
							<td><input type="file" id="file_upload" name="file_upload"/></td>
							<!-- <td><input type="file" name="file" multiple="false" onchange="previewImage(this)" ></td> -->
							<!-- <td>
								<div id="pic_view_div" style="width:150px; height:180px">  
							    	<img src="/puhui-lend/upload/puhui-lend/2015-12-25/19-19-30/b214fa62-0bc5-4440-b018-7d8ac3c62543.png" width="150" height="180" id="pic_view">  
							    </div>  
							</td> -->
						</tr>
					</table>

				</fieldset>
				<div style="float:right;margin:10px">
					<a class="easyui-linkbutton" id="save">保存</a>
					&nbsp;&nbsp;
					<a class="easyui-linkbutton" id="close">关闭</a>
				</div>
			</div>
		</form>
	</body>

	<script type="text/javascript">
		$(function(){
			//绑定上传
			jQuery("#file_upload").uploadify({
				swf: '${ctx}/static/js/uploadify/uploadify.swf',
				uploader: "${ctx}/lendAdvertisement/saveLendAdvertisementFile",
			    'fileSizeLimit' : '50MB',
			    'fileTypeDesc': "bmp,jpg,jpeg,png,gif",
			    'fileTypeExts': "*.bmp;*.jpg;*.jpeg;*.png;*.gif",
			    'overrideEvents' : [ 'onDialogClose', 'onSelectError' ],
			    'auto': true,
			    'buttonText': "上传...",
			    'width': 120,
			    'height': 25,
			    'uploadLimit' : 20,
			    'preventCaching' : false,
			    'removeTimeout' : 1,
			    'onUploadStart': function(file) {
			    	var paramData = {
			    			id: $('#id').val(),
						};
	
			    	//图片回现
			    	/* previewImage(file); */
	
			    	//清空缓存参数
			    	jQuery("#file_upload").data("uploadify").settings.formData = {};
			    	//设置参数
			   		jQuery("#file_upload").uploadify('settings', 'formData',paramData);
			    },
				'onUploadSuccess' : function(file, data, response) {
					data = $.parseJSON(data);
					$.messager.alert('提示信息','<span style="line-height:150%;">'+data.msg+'</span>');
					<%-- $("#pic_view").attr("src","<%=request.getContextPath()%>" + _arr_val[1]); --%>
					if(data.success){
						$('#id').val(data.result);
					}
				},
				'onSelectError': function (file, errorCode, errorMsg) {
					if(errorCode == SWFUpload.QUEUE_ERROR.INVALID_FILETYPE){
						$.messager.alert('提示信息','<span style="line-height:150%;">文件类型不正确！请选择文件类型为"'+jQuery("#file_upload").uploadify('settings', 'fileTypeDesc')+'"的文件上传</span>');
					}
			     	if(errorCode == SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT){
			     		alert("文件大小超过了50MB");
			    	}
			    }
			});
		});
	</script>

	<script type="text/javascript">
		$(document).ready(function() {
			
			$('#customerIdentity').combobox('select','${lendAdvertisement.customerIdentity}');
			$('#customerLendStatus').combobox('select','${lendAdvertisement.customerLendStatus}');
			$('#enabled').combobox('select','${lendAdvertisement.enabled}');
			
	        //添加
	        $('#save').click(function(){
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
										parent.$("#tabs").tabs("close","编辑广告位");
									});
								}else{
									$.messager.alert('提示信息',data.result);
								}
							}
						});
					};
				});
			});
		});
		
		//关闭
        $('#close').click(function(){
        	$.messager.confirm('提示信息',"确认关闭吗？",function(r){
    		    if (r){
    		    	 parent.$("#tabs").tabs("close","编辑广告位");
    		    }
    		});
        });
		
      	//预览
        function viewFile(data, fileName){

        	fileName = fileName.toLocaleLowerCase();
        	if (fileName.indexOf(".jpg") != -1 || fileName.indexOf(".gif") != -1
        				|| fileName.indexOf(".png") != -1
        				|| fileName.indexOf(".jepg") != -1
        				|| fileName.indexOf(".bmp") != -1) {
        			window.open("${ctx}/lendRequestUpload/previewForAjax?id="+ data,
        					'详细','height=800,width=1024,top=50,left=100,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
       		} else {
       			$.messager.alert('提示信息', '该广告位图片类型不是制定的上传类型，不支持在线预览');
       		}

       	}
	</script>

</html>


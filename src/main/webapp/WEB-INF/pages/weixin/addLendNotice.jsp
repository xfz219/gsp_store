<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/static/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/static/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>
<link rel="stylesheet"  href="${ctx}/static/ueditor1_4_3-utf8-jsp/themes/default/css/ueditor.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信文章</title>
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
<div>
<div id="lookdialog" style="text-align:center;padding-top:20px;"></div>
		<form id="addNoticeForm" class="easyui-form" method="post" data-options="novalidate:true">
			<div title="" style="padding: 10px;">
			
				<fieldset>
					<legend>添加文章 &nbsp;&nbsp;&nbsp;<font color="red">*必填</font></legend>
					<input type="hidden" id="id" name="id" value="${appWeixinArticle.id }">
					<table>
						<tr>
						
							<td class="one">标题：</td>
							<td class="two">
								<input id="article" name="article" class="easyui-validatebox" placeholder="50个字以内" style="width: 590px;height: 26px" maxlength="50" data-options="required:true">
							</td>
							</tr>
							<tr style="height: 60px">
							<td class="one">作者：</td>
							<td class="two">
								<input id="authorName" name="authorName" class="easyui-validatebox" placeholder="50个字以内" style="width: 590px;height: 26px" maxlength="50" data-options="required:true">
							</td>
							</tr>
							<tr style="height: 60px">
							<td class="one">摘要：</td>
							<td class="two">
								<input id="synopsis" name="synopsis" class="easyui-validatebox" placeholder="50个字以内" style="width: 590px;height: 26px" maxlength="50" data-options="required:true">
							</td>
							</tr>
							<tr id="adFile" style="height: 60px">
							<td class="one">上传图片:</td>
							<td> <input type="file" id="file_upload" name="file_upload"/></td>
							</tr>
							<tr style="height:413px">
							<td class="one" >内容：</td>
							<td class="two">
								  <script type="text/plain" id="myEditor" style="width:1200px;height:400px;margin:30spx 4px;margin-left: "></script>
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
</div>
</body>

<script type="text/javascript">
		$(function(){
			//绑定上传
			jQuery("#file_upload").uploadify({
				swf: '${ctx}/static/js/uploadify/uploadify.swf',
				uploader: "${ctx}/AppWeixiinArticle/saveLendAdvertisementFile",
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

			    	//清空缓存参数
			    	jQuery("#file_upload").data("uploadify").settings.formData = {};
			    	//设置参数
			   		jQuery("#file_upload").uploadify('settings', 'formData',paramData);
			    },
				'onUploadSuccess' : function(file, data, response) {
					data = $.parseJSON(data);
					$.messager.alert('提示信息','<span style="line-height:150%;">'+data.msg+'</span>');
					if(data.success){
						$('#id').val(data.obj);
					}
				},
				'onSelectError': function (file, errorCode, errorMsg) {
					if(errorCode == SWFUpload.QUEUE_ERROR.INVALID_FILETYPE){
						$.messager.alert('提示信息','<span style="line-height:150%;">文件类型不正确！请选择文件类型为"'+jQuery("#file_upload").uploadify('settings', 'fileTypeDesc')+'"的文件上传</span>');
					}
			     	if(errorCode == SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT){
			     		alert("文件大小超过了50MB");
			    	}
		    }});
		});
	</script>

<script language="javascript">  
  
        $(document).ready(function() {
            var editor = new baidu.editor.ui.Editor({  
                textarea : 'notice'  
            });  
            editor.render("myEditor"); 
            
            //添加
            $('#add').click(function(){
            	$.messager.confirm('提示信息','确认保存吗?',function(r){
            		if(r){
            	$('#addNoticeForm').form('submit',{
            		 url:'${ctx}/AppWeixiinArticle/addLendNotice',
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
  				        		var tab = parent.$("#tabs").tabs("getTab","微信文章配置");
								var url = $(tab.panel('options').content).attr('src');
								parent.$('#tabs').tabs('update', {
									  tab : tab,
									  options : {
										src : url
									  }
								});
  					        	parent.$("#tabs").tabs("select","微信文章配置");
  					        	parent.$("#tabs").tabs("close","添加文章");
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
        		    	 parent.$("#tabs").tabs("close","添加文章");
        		    }
        		    });
            	
            });
           
           
        });  
        
    </script>
</html>


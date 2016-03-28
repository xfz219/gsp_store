<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改客户录件状态</title>

</head>
<body class="easyui-layout">
<div data-options="region:'center'" style="overflow: hidden;">
		<table id="datagrid"></table>
</div>

<div id="addChangeMobileDialog"></div>	
	
<div id="tbChangeMobileListGrid">
	<span>
		<span class="datagrid-btn-separator" style="float:none;"></span>
		<a  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" id='add' onclick="showChangeMobileDialog();" >修改客户录件状态</a>
		<span class="datagrid-btn-separator" style="float:none;"></span>
	</span>
	<div style="clear:both;"></div>
</div>

<script type="text/javascript" src="${ctx}/static/js/common_datagrid.js"></script>
<script>
	var grid;
	var paramData = {};

	$(function() {
		grid = $('#datagrid').datagrid({
			nowrap : false,
			striped : true,
			fit : true,
			toolbar : "#tbChangeMobileListGrid" 
		});

	});

	function fixWidth(percent) {
		return document.body.clientWidth * percent;
	}

	//过滤无效属性
	function getData(data) {
		var temp = {};
		$.each(data, function(key, value) {
			if (typeof value != 'undefined' && value != '' && value != null) {
				temp[key] = value;
			}
		});
		console.log(temp);
		return temp;
	}
	
	var addChangeMobileDialog = $('#addChangeMobileDialog');
	function showChangeMobileDialog() {
		var contentData = '<div style="margin: 20px 0px 0px 50px;"><label>客户进件号：</label><input id="id" class="easyui-validatebox textbox" data-options="required:true"/></div>';
		addChangeMobileDialog.dialog({
	    	title: "修改客户录件状态",
	       	width: 350,
	       	height: 130,
	       	modal: true,
	       	content: contentData,
	       	buttons: [{
	        	text: '确定',
	          	handler: function() {
	            	var thisButton = $(this);
	             	if(typeof $('#id').val() != 'undefined' && $('#id').val() != '' && $('#id').val() != null){
	                	$.messager.confirm('确定','您确定修改客户录件状态吗？',function(r){
	                    	if(r){
	                        	thisButton.linkbutton('disable');
                       			$.ajax({
                       			    url: '${ctx}/updateCustomerEntryState/updateCustomerEntryStateMethod',
                       			    data: {
                       			        "id": $('#id').val()
                       			    },
                       			    type: 'POST',
                       			    cache: false,
                       			    dataType: "json",
                       			    async:false,
                       			 	success: function(data) {
                    			        addChangeMobileDialog.dialog('close');
                    			        $.messager.alert('提示信息','修改成功');
                    			    }
                       			});
	                     	}
                      	});
                   	}else{
                   		$.messager.alert('提示信息', '请输入客户进件号！');
                   	}
              	}},
              	{
                    text: '取消',
                    handler: function() {
                    	addChangeMobileDialog.dialog('close');
                    }
                }]
		});
	}
</script>
</body>
</html>
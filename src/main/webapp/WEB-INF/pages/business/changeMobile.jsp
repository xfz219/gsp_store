<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>手机设备变更</title>

</head>
<body class="easyui-layout">
	
<div data-options="region:'center'" style="overflow: hidden;">
		<table id="datagrid"></table>
</div>

<div id="addChangeMobileDialog"></div>	
	
<div id="tbChangeMobileListGrid">
	员工编号：<input type="text" id="salesNo" name="salesNo" />&nbsp;&nbsp;
	<span>
		<a  href="javascript:void(0);" class="easyui-linkbutton search" iconCls="icon-search" plain="true" onclick="searchByConditions();">搜索</a>
		<span class="datagrid-btn-separator" style="float:none;"></span>
		<a  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" id='add' onclick="showChangeMobileDialog();" >变更设备</a>
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
			url : '${ctx}/changeMobile/queryChangeMobileRecordMethod',
			columns : [[ 
			              {field : 'id',title : 'id',align : 'center',hidden : true }, 
			              {field : 'salesNo',title : '员工编号',align : 'center',width : fixWidth(0.21) }, 
			              {field : 'clientTypeName',title : '设备类型',align : 'center',width : fixWidth(0.21) }, 
			              {field : 'pid',title : '设备ID',align : 'center',width : fixWidth(0.33) }, 
			              {field : 'updateTime',title : '更换时间',align : 'center',width : fixWidth(0.21) } 
			]],
			idField : 'id',
			pagination : true,
			rownumbers : true,
			pageSize : 20,
			toolbar : "#tbChangeMobileListGrid",
			onLoadSuccess : function(data) {
			},
			onLoadError : function(data) {
				$.messager.alert('提示信息', "设备变更记录加载失败!");
			}
		});

	});

	function fixWidth(percent) {
		return document.body.clientWidth * percent;
	}

	//搜索
	function searchByConditions() {
		paramData.salesNo = $('#salesNo').val(); //员工编号
		grid.datagrid('load', getData(paramData));
		grid.datagrid('clearSelections')
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
		var contentData = '<div style="margin: 20px 0px 0px 50px;"><label>销售编号：</label><input id="changeSalesNo" class="easyui-validatebox textbox" data-options="required:true"/></div>';
		addChangeMobileDialog.dialog({
	    	title: "设备变更",
	       	width: 350,
	       	height: 130,
	       	modal: true,
	       	content: contentData,
	       	buttons: [{
	        	text: '确定',
	          	handler: function() {
	            	var thisButton = $(this);
	             	if(typeof $('#changeSalesNo').val() != 'undefined' && $('#changeSalesNo').val() != '' && $('#changeSalesNo').val() != null){
	                	$.messager.confirm('确定','您确定变更设备吗？',function(r){
	                    	if(r){
	                        	thisButton.linkbutton('disable');
                       			$.ajax({
                       			    url: '${ctx}/changeMobile/changeMobileMethod',
                       			    data: {
                       			        "salesNo": $('#changeSalesNo').val()
                       			    },
                       			    type: 'POST',
                       			    cache: false,
                       			    dataType: "json",
                       			    async:false,
                       			    success: function(data) {
                       			        if(data.returnEntity.success){
                       			        	addChangeMobileDialog.dialog('close');
                        			        grid.datagrid('clearSelections');
                        			        grid.datagrid('load');
                       			        }
                       			        $.messager.alert('提示信息',data.returnEntity.msg);
                       			     	thisButton.linkbutton('enable');
                       			    },
                       			    error: function(XMLHttpRequest, textStatus, errorThrown) {
                       			    	thisButton.linkbutton('enable');
                       			        $.messager.alert('提示信息', "请求出现错误！");
                       			    }
                       			});
	                     	}
                      	});
                   	}else{
                   		$.messager.alert('提示信息', '请输入员工编号！');
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
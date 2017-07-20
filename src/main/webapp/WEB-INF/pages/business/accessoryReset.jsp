<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>附件状态查询</title>
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
</style>
<script type="text/javascript" src="${ctx}/static/js/common_datagrid.js"></script>
<script>
 var grid;
 var i = 0;
 var paramData = {};
$(function(){
	
	grid = $('#datagrid').datagrid({
		nowrap: false,
		striped: true,
		fit: true,
		url:'${ctx}/accessoryReset/selectAccessoryMethod',
	    columns:[[
			{field:'id',title:'id',align:'center',hidden:true},
			{field:'app_lend_request_id',title:'进件id',align:'center',width : fixWidth(0.1)},
			{field:'type',title:'类型',align:'center',width : fixWidth(0.1)}
	        
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
		onLoadError:function (data) {			
			$.messager.alert('提示信息',"查询附件状态失败！");
		}
	});
	
});

function fixWidth(percent) {
	return document.body.clientWidth * percent;
}

function reset(){
	var rows = $('#datagrid').datagrid('getSelections');
	if(rows.length>1){
		$.messager.alert('提示信息','只能选择单条记录件！');
	}else if(rows.length<1){
		$.messager.alert('提示信息','请选择一条记录！');
	}else{
		$.ajax({
            type:'POST', //请求方式  
            url:'${ctx}/accessoryReset/updateAccessory', //请求路径  
            cache: false,     
            data:rows[0],  //传参  
            dataType: 'json'//返回值类型  
            });
		searchByConditions();
	}
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
	paramData.appLendRequestId = $('#appLendRequestId').val(); //进件id
	paramData.type =  $('#type').val();//类型
	grid.datagrid('load', getData(paramData));
	grid.datagrid('clearSelections'); 
}


//重置
function resetConditions(){
	$('#appLendRequestId').val(''); //员工编号
	$('#type').val(''); //员工编号
}

</script>

</head>
<body class="easyui-layout">
<div data-options="region:'center'" style="overflow: hidden;">
		<table id="datagrid"></table>
</div>
	
<div id="addBlacklistDialog"></div>	
<div id="modifyDialog"></div>
	<div id="tbLendRequest">
			app进件id：<input type="text" id="appLendRequestId" name="appLendRequestId" />&nbsp;&nbsp;&nbsp;&nbsp;
			类型：<select type="select" name="type" id="type">
					<option value=""></option>
					<option value="1">征信报告</option>
					<option value="2">银行流水</option>
					<option value="3">淘宝验证</option>
					<option value="4">手机运营商</option>
					<option value="21">社保</option>
					<option value="22">公积金</option>
				</select> &nbsp;&nbsp;&nbsp;&nbsp;
			<a  href="javascript:void(0);" class="easyui-linkbutton search" iconCls="icon-search" plain="true" onclick="searchByConditions();">搜索</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
			<a  href="javascript:void(0);" class="easyui-linkbutton clear" iconCls="icon-search" plain="true" onclick="resetConditions();">清空</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
			<a  href="javascript:void(0);" class="easyui-linkbutton clear" iconCls="icon-search" plain="true" onclick="reset();">重新获取附件</a>
	</div>
</body>
</html>
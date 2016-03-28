<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个贷App客户耗时分析表</title>
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
		url:'${ctx}/consumingAnalysisReport/getConsumingAnalysisReportMethod',
	    columns:[[
			{field:'id',title:'id',align:'center',hidden:true},
			{field:'customerName',title:'客户名称',align:'center',fitColumns:true},
	        {field:'salesName',title:'销售名称',align:'center',sortable:true,fitColumns:true},
	        {field:'requestId',title:'进件号',align:'center',resizable:true,fitColumns:true},
	        {field:'dataTotalTime',title:'录入资料总耗时',align:'center',fitColumns:true},
	        {field:'identityTime',title:'身份信息耗时',align:'center',fitColumns:true},
	        {field:'occupationTime',title:'职业信息耗时',align:'center',sortable:true,fitColumns:true},
	        {field:'contactTime',title:'联系人信息耗时',align:'center',fitColumns:true},
	        {field:'creditReportTime',title:'征信报告信息耗时',align:'center',sortable:true,fitColumns:true},
	        {field:'taobaoTime',title:'淘宝信息耗时',align:'center',fitColumns:true},
	        {field:'communicationTime',title:'运营商信息耗时',align:'center',fitColumns:true},
	        {field:'payFlowTime',title:'工资流水信息耗时',align:'center',fitColumns:true},
	        {field:'idPhotoTime',title:'身份证照片耗时',align:'center',fitColumns:true},
	        {field:'residenceCertificateTime',title:'居住证明信息耗时',align:'center',fitColumns:true},
	        {field:'workCertificateTime',title:'工作证明信息耗时',align:'center',fitColumns:true},
	        {field:'estateCertificateTime',title:'房产证明信息耗时',align:'center',fitColumns:true},
	        {field:'operatorCertificateTime',title:'经营证明信息耗时',align:'center',fitColumns:true},
	        {field:'businessAddressCertificateTime',title:'经营地址证明信息耗时',align:'center',fitColumns:true},
	        {field:'marriedTime',title:'已婚证明信息耗时',align:'center',fitColumns:true},
	        {field:'childrenTime',title:'子女证明信息耗时',align:'center',fitColumns:true},
	        {field:'degreeTime',title:'学历证明信息耗时',align:'center',fitColumns:true},
	        {field:'providentSocialTime',title:'社保公积金信息耗时',align:'center',fitColumns:true},
	        {field:'otherTime',title:'其他证明信息耗时',align:'center',fitColumns:true},
	        {field:'productUpdateCount',title:'产品更换次数',align:'center',fitColumns:true}
	        
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
		onSelect: function (rowIndex, rowData) {
			 dealPermission(rowIndex, rowData);
		},
		onUnselect:function (rowIndex, rowData) {
			 dealPermission(rowIndex, rowData);
		},
		onCheck:function (rowIndex, rowData) {
			 dealPermission(rowIndex, rowData);
		},
		onUncheck:function (rowIndex, rowData) {
			 dealPermission(rowIndex, rowData);
		},
		onLoadSuccess:function (data){				
		},
		onLoadError:function (data) {			
			//if(i == 0){		
				//refresh();						
				//i++;			
			//}	
			$.messager.alert('提示信息',"个贷App全流程分析表查询失败");
		}
	});
	
});



function refresh(){
     grid.datagrid('reload'); 
     grid.datagrid('clearSelections');      
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
	paramData.startTime =  $('#startTime').datebox('getValue'); //开始时间
	paramData.endTime = $('#endTime').datebox('getValue'); //结束时间
	grid.datagrid('load', getData(paramData));
	grid.datagrid('clearSelections'); 
}


//重置
function resetConditions(){
	$('#startTime').combobox("clear"); //开始时间
	$('#endTime').combobox("clear"); //结束时间
}


function createExcel(){
	startTime =  $('#startTime').datebox('getValue'); //开始时间
	endTime = $('#endTime').datebox('getValue'); //结束时间
	var url = "${ctx}/consumingAnalysisReport/exportConsumingAnalysisReportExcel?startTime="+startTime+"&endTime="+endTime;
	window.open(url,"文件下载");
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
			选择开始日期：<input id = "startTime" class="easyui-datebox" iconCls='icon-search' plain = "true" data-options="editable:false"/>
			&nbsp;&nbsp;&nbsp;&nbsp;
			选择结束日期：<input id = "endTime" class="easyui-datebox" iconCls='icon-search' plain = "true" data-options="editable:false"/>
			<a  href="javascript:void(0);" class="easyui-linkbutton search" iconCls="icon-search" plain="true" onclick="searchByConditions();">搜索</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
			<a  href="javascript:void(0);" class="easyui-linkbutton clear" iconCls="icon-search" plain="true" onclick="resetConditions();">清空</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
			<a href="javascript:void(0);" class="easyui-linkbutton downloadExcel" iconCls="icon-search" plain="true"  id='excel'  onclick='createExcel();'>导出excel</a>
	</div>
</body>
</html>
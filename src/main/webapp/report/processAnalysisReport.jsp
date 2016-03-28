<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个贷App全流程分析表</title>
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
		url:'${ctx}/processAnalysisReport/getProcessAnalysisReportMethod',
	    columns:[[
			{field:'id',title:'id',align:'center',hidden:true},
			{field:'salesNo',title:'员工编号',align:'center',fitColumns:true},
			{field:'salesName',title:'员工姓名',align:'center',fitColumns:true},
	        {field:'registCount',title:'注册客户数量',align:'center',sortable:true,fitColumns:true},
	        {field:'identityCount',title:'完成身份信息用户数量',align:'center',resizable:true,fitColumns:true},
	        {field:'identityRate',title:'身份信息完成率',align:'center',fitColumns:true},
	        {field:'occupationCount',title:'完成职业信息件数',align:'center',fitColumns:true},
	        {field:'occupationRate',title:'职业信息完成率',align:'center',sortable:true,fitColumns:true},
	        {field:'contactCount',title:'完成联系人信息件数',align:'center',fitColumns:true},
	        {field:'contactRate',title:'联系人信息完成率',align:'center',sortable:true,fitColumns:true},
	        {field:'creditReportCount',title:'完成申请征信报告件数',align:'center',fitColumns:true},
	        {field:'creditReportRate',title:'征信报告信息完成率',align:'center',fitColumns:true},
	        {field:'taobaoCount',title:'完成淘宝信息用户数量',align:'center',fitColumns:true},
	        {field:'taobaoRate',title:'淘宝信息完成率',align:'center',fitColumns:true},
	        {field:'communicationCount',title:'完成运营商信息用户数量',align:'center',fitColumns:true},
	        {field:'communicationRate',title:'运营商信息完成率',align:'center',fitColumns:true},
	        {field:'payFlowCount',title:'工资流水信息用户数量',align:'center',fitColumns:true},
	        {field:'payFlowRate',title:'工资流水信息完成率',align:'center',fitColumns:true},
	        {field:'idPhotoCount',title:'完成身份证照片用户数量',align:'center',fitColumns:true},
	        {field:'idPhotoRate',title:'身份证照片完成率',align:'center',fitColumns:true},
	        {field:'residenceCertificateCount',title:'完成居住证明信息用户数量',align:'center',fitColumns:true},
	        {field:'residenceCertificateRate',title:'居住证明信息完成率',align:'center',fitColumns:true},
	        {field:'workCertificateCount',title:'完成工作证明信息用户数量',align:'center',fitColumns:true},
	        {field:'workCertificateRate',title:'工作证明信息完成率',align:'center',fitColumns:true},
	        {field:'estateCertificateCount',title:'完成房产证明信息用户数量',align:'center',fitColumns:true},
	        {field:'estateCertificateRate',title:'房产证明信息完成率',align:'center',fitColumns:true},
	        {field:'operatorCertificateCount',title:'完成经营证明信息用户数量',align:'center',fitColumns:true},
	        {field:'operatorCertificateRate',title:'经营证明信息完成率',align:'center',fitColumns:true},
	        {field:'businessAddressCertificateCount',title:'完成经营地址证明信息用户数量',align:'center',fitColumns:true},
	        {field:'businessAddressCertificateRate',title:'经营地址证明信息完成率',align:'center',fitColumns:true},
	        {field:'marriedCount',title:'完成已婚证明信息用户数量',align:'center',fitColumns:true},
	        {field:'marriedRate',title:'已婚证明信息完成率',align:'center',fitColumns:true},
	        {field:'childrenCount',title:'完成子女证明信息用户数量',align:'center',fitColumns:true},
	        {field:'childrenRate',title:'子女证明信息完成率',align:'center',fitColumns:true},
	        {field:'degreeCount',title:'完成学历证明信息用户数量',align:'center',fitColumns:true},
	        {field:'degreeRate',title:'学历证明信息完成率',align:'center',fitColumns:true},
	        {field:'providentSocialCount',title:'完成社保公积金信息用户数量',align:'center',fitColumns:true},
	        {field:'providentSocialRate',title:'社保公积金信息完成率',align:'center',fitColumns:true},
	        {field:'otherCount',title:'完成其他证明信息用户数量',align:'center',fitColumns:true},
	        {field:'otherRate',title:'其他证明信息完成率',align:'center',fitColumns:true},
	        {field:'inputCompletionCount',title:'客户完成录入提交销售质检件数',align:'center',fitColumns:true},
	        {field:'inputCompletionRate',title:'客户完成录入提交销售质检件完成率',align:'center',fitColumns:true},
	        {field:'checkCompletionCount',title:'销售质检完成件数',align:'center',fitColumns:true},
	        {field:'checkCompletionRate',title:'销售质检完成率',align:'center',fitColumns:true}
	        
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
	paramData.salesNo = $('#salesNo').val(); //员工编号
	paramData.startTime =  $('#startTime').datebox('getValue'); //开始时间
	paramData.endTime = $('#endTime').datebox('getValue'); //结束时间
	grid.datagrid('load', getData(paramData));
	grid.datagrid('clearSelections'); 
}


//重置
function resetConditions(){
	$('#salesNo').val(''); //员工编号
	$('#startTime').combobox("clear"); //开始时间
	$('#endTime').combobox("clear"); //结束时间
}


function createExcel(){
	salesNo = $('#salesNo').val(); //员工编号
	startTime =  $('#startTime').datebox('getValue'); //开始时间
	endTime = $('#endTime').datebox('getValue'); //结束时间
	var url = "${ctx}/processAnalysisReport/exportProcessAnalysisReportExcel?salesNo="+salesNo+"&startTime="+startTime+"&endTime="+endTime;
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
			员工编号：<input type="text" id="salesNo" name="salesNo" />&nbsp;&nbsp;&nbsp;&nbsp;
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
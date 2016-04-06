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
			{field:'name',title:'客户姓名',align:'center',width : fixWidth(0.1)},
			{field:'mobile',title:'手机号码',align:'center',width : fixWidth(0.1)},
			{field:'registered',title:'是否注册app',align:'center',width : fixWidth(0.1)},
			{field:'city',title:'借款城市',align:'center',width : fixWidth(0.1)},
			{field:'branch',title:'门店',align:'center',width : fixWidth(0.1)},
			{field:'sales',title:'是否绑定销售',align:'center',width : fixWidth(0.1)},
			{field:'salesName',title:'销售姓名',align:'center',width : fixWidth(0.1)},
			{field:'salesNo',title:'销售工号',align:'center',width : fixWidth(0.1)},
			{field:'salesStatus',title:'销售状态',align:'center',width : fixWidth(0.1)},
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
	paramData.department = $('#department').combobox('getValue');
	paramData.salesStatus = $('#salesStatus').combobox('getValue');
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
 	if(typeof $('#selectUserZu').combobox('getValue') != '' && $('#selectUserName').combobox('getValue') != ''){
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

$(document).ready(function () {
	$("#selectUserZu").combobox({
	onChange: function (n,o) {
	$.ajax({
	    url: '${ctx}/customerClues/selectUserNameMethod',
	    data:{"department": $('#selectUserZu').combobox('getValue')
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
	
</script>

</head>
<body class="easyui-layout">
<div data-options="region:'center'" style="overflow: hidden;">
		<table id="datagrid"></table>
</div>
	<div id="addBindingUser" class="easyui-dialog" title="绑定" style="width:400px;height:200px;" data-options="iconCls:'icon-save',resizable:true,closed:true" >
   		 <div style="margin: 20px 0px 0px 80px;">
   		 	<label style="margin: 0px 0px 0px 50px;">请选择需要绑定的销售</label>
   		 	<br><br><br>
   		 	<label>组别：</label>
				<input class="easyui-combobox" data-options="editable:false" 
											   id='selectUserZu'
											   name="selectUserZu"
											   url='${ctx}/customerClues/selectUserMethod'
											   valueField='departmentCode'
											   textField='departmentName'
											   panelHeight='auto'
											   />
   		 	<br><br>
   		 	<label>姓名：</label>
			<input class="easyui-combobox" id='selectUserName' name="selectUserName" data-options="valueField:'selectUserCode', textField:'selectUserName', panelHeight:'auto'" />
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
			&nbsp;&nbsp;&nbsp;&nbsp;门店机构： <input class="easyui-combobox" data-options="editable:false" 
							   id='department'
							   name="department"
							   url='${ctx}/customerClues/selectUserMethod'
							   valueField='departmentCode'
							   textField='departmentName'
							   panelHeight='auto'
							   />
			<br>
			&nbsp;&nbsp;&nbsp;&nbsp;销售姓名：<input type="text" id="salesName" name="salesName" />&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;销售工号：<input type="text" id="salesNo" name="salesNo" />&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;销售状态：	<input class="easyui-combobox" data-options="editable:false" 
							   id='salesStatus'
							   name="salesStatus"
							   url='${ctx}/customerClues/selectUserSalesStatusMethod'
							   valueField='salesStatusCode'
							   textField='salesStatusName'
							   panelHeight='auto'
							   />
			<a  href="javascript:void(0);" class="easyui-linkbutton search" iconCls="icon-search" plain="true" onclick="searchByConditions();">搜索</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
			<a  href="javascript:void(0);" class="easyui-linkbutton clear" iconCls="icon-remove" plain="true" onclick="resetConditions();">重置</a>
			<br><br>
			&nbsp;&nbsp;&nbsp;&nbsp;<a id="addChangeMobileDialog" href="#" class="easyui-linkbutton l-btn" onclick="bindingUserA();"><span class="l-btn-text">绑定</span></a>
			<br><br>
	</div>
	
</body>
</html>
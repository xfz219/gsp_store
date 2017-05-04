<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>解绑管理</title>
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
		pagination:true,
		rownumbers:true,
		singleSelect:false,
		pageSize: 20,
		toolbar:"#tb",
		url:'${ctx}/changeCustomer/selectChangeCustomerMethod',
	    columns:[[
			{field:'id',title:'id',align:'center',hidden:true},
			{field:'name',title:'客户姓名',align:'center',width:110},
			{field:'mobile',title:'手机号码',align:'center',width:150},
			{field:'status',title:'客户状态',align:'center',width:120},
			{field:'shopName',title:'门店',align:'center',width:220},
			{field:'salesName',title:'销售姓名',align:'center',width:120},
			{field:'salesNo',title:'销售工号',align:'center',width:120},
			{field:'salesMobile',title:'销售手机号',align:'center',width:130},
			{field:'salesStatus',title:'销售状态',align:'center',width:100},
			{field:'createTime',title:'创建时间',align:'center',width:100}
	        
	    ]],
		idField:'id',
		frozenColumns:
		[[
            {field:'ck',checkbox:true}
		]],
		onLoadSuccess:function (data){
			//$('#datagrid').datagrid("unselectAll");
		},
		onLoadError:function (data) {			
			$.messager.alert('提示信息',"查询解绑管理失败！");
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
	paramData.name = $('#name').val();
	paramData.mobile = $('#mobile').val();
	paramData.salesMobile = $('#salesMobile').val();
	paramData.salesName = $('#salesName').val();
	paramData.salesNo = $('#salesNo').val();
	//paramData.salesStatus = $('#salesStatus').combobox('getValue');
	grid.datagrid('load', getData(paramData));
	grid.datagrid('clearSelections'); 
}


//重置
function resetConditions(){
	$('#name').val('');
	$('#mobile').val('');
	$('#salesMobile').val('');
	$('#salesName').val('');
	$('#salesNo').val('');
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
	var temp=[];
	var rows = $('#datagrid').datagrid('getSelections');
	var ids = "";
	if(rows.length>=1){
		for ( var i = 0; i < rows.length; i++) {
				temp.push(rows[i].id);
			}
		ids = temp.join(",");
	}
 	if(typeof $('#pid').combobox('getValue') != '' && $('#selectUserName').combobox('getValue') != ''){
    	$.messager.confirm('确定','您确定要绑定吗？',function(r){
        	if(r){
       			$.ajax({
       			    url: '${ctx}/changeCustomer/updateBindingUserMethod',
       			    data:{"selectUserName": $('#selectUserName').combobox('getValue'),"ids":ids
       			    },
       			    type: 'POST',
       			    cache: false,
       			    dataType: "json",//返回值类型  
       			    async:false,
       			    success: function() {
       			    	$.messager.alert('提示信息','更换销售成功');
       			    	grid.datagrid('reload');
      			      	grid.datagrid('clearSelections'); 
       			        $('#addBindingUser').dialog('close');
       			        
       			    },
       			    error: function(XMLHttpRequest, textStatus, errorThrown) {
       			    	$.messager.alert('提示信息',XMLHttpRequest.responseText);
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

$(document).ready(function(){
	 //产生一个在下拉框中的树，也就是组合树
	$('#pid').combotree({
	        url : '${ctx}/appUtil/getOrgTree',
	        idFiled:'id',
	        textFiled:'name', 
	        parentField : 'pid',
	        checkbox:true,
	        lines : true,
	        panelHeight : '300',
	        onLoadSuccess: function (node, data) {
	            $('#pid').combotree('tree').tree("collapseAll"); 
	            
	        	$("#pid").combobox({
	        		onChange: function (n,o) {
	        			
	        		$('#selectUserName').combobox('clear');
	        		$.ajax({
	        		    url: '${ctx}/changeCustomer/selectUserNameMethod',
	        		    data:{"oid": $('#pid').combobox('getValue')
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
       }
	});
});

 $(document).ready(function () {
	$("#pid").combobox({
	onChange: function (n,o) {
	$.ajax({
	    url: '${ctx}/customerClues/selectUserNameMethod',
	    data:{"department": $('#pid').combobox('getValue')
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
	
//easyUI面板点击优化
var timer = setInterval(function(){
	var closePanel = $('div.panel.layout-expand');
	if(closePanel.get(0)){
		clearInterval(timer);
		closePanel.unbind('click');
		closePanel.click(function(){
			closePanel.find('a.layout-button-down').click();
		});
		var openPanel = $('#searchDiv').panel('header');
		openPanel.unbind('click');
		openPanel.click(function(){
			openPanel.find('a.layout-button-up').click();
		}).mouseover(function(){
			openPanel.addClass('mouseHand');
		}).mouseout(function(){
			openPanel.removeClass('mouseHand');
		});
	}
},100);


//----------------------------搜索--------------------------------------------------------------------
//Enter搜索
$('#queryForm').keypress(function(e){
	var keynum; //字符的ASCII码。
	if(window.event){ // IE
		keynum = e.keyCode;
	}else if(e.which){ //其他浏览器
		keynum = e.which;
	}
	
	if(keynum == 13){ //按下“Enter”键
		$('#searchByConditions').focus();
		$('#searchByConditions').click();
	}
});

//刷新
function refresh(){
	grid.datagrid('clearSelections'); 
	grid.datagrid("load");
}


 //详情
 function detailCustomer() {
	 var rows = $('#datagrid').datagrid('getSelections');
	 if (rows.length != 1) {
		 $.messager.alert('提示信息', '请选择一条记录！');
	 } else {
		 $("#customerDetailDialog").dialog({
			 iconCls: 'icon-search',
			 title: "客户信息",
			 width: 550,
			 height: 300,
			 modal: true,
			 href: '${ctx}/html/user/detailCustomer.jsp',
			 onLoad: function () {
				 var id = rows[0].id;
				 $(document).ready(function () {
					 $.ajax({
						 url: "${ctx}/changeCustomer/selectCustomerById?customerId=" + id,
						 type: "GET",
						 async: false,
						 success: function (data) {
							 $("#customerName").val(data.customerName);
							 $("#customerMobile").val(data.mobile);
						 }
					 });
				 });
			 }
		 })
	 }
 }
 ;

</script>

</head>
<body class="easyui-layout">
<div id="customerDetailDialog"></div>
<div data-options="region:'center'" style="overflow: hidden;">
		<table id="datagrid"></table>
</div>
	<div id="addBindingUser" class="easyui-dialog" title="绑定" style="width:400px;height:200px;" data-options="iconCls:'icon-save',resizable:true,closed:true" >
   		 <div style="margin: 20px 0px 0px 80px;">
   		 	<label style="margin: 0px 0px 0px 50px;">请选择需要绑定的销售</label>
   		 	<br><br><br>
   		 	<label>组别：</label>
				<input class="easyui-combobox" data-options="editable:false" id='pid'/>
   		 	<br><br>
   		 	<label>姓名：</label>
			<input class="easyui-combobox" id='selectUserName' name="selectUserName" data-options="valueField:'selectUserCode', textField:'selectUserName', panelHeight:'auto',editable:false" />
			<br><br>
			<div style="margin: 0px 0px 0px 50px;">
				<a href="#" onclick="YES();" class="easyui-linkbutton">确认</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" onclick="NO();" class="easyui-linkbutton">取消</a>
			</div>
   		 </div>
	</div>
	
	
	<div id="searchDiv" data-options="region:'north',title:'搜索条件',split:true" style="overflow:hidden;height:120px;">
	<form  id="queryForm">
		<table class="query">
		<tr>
			<td class="label">客户姓名：</td> <td><input type="text" id="name" name="name" /></td>
			<td class="label">客户手机：</td> <td><input type="text" id="mobile" name="mobile" /></td>
			<%-- <td class="label">销售状态：</td> <td><input class="easyui-combobox" data-options="editable:false" 
							   id='salesStatus'
							   name="salesStatus"
							   url='${ctx}/customerClues/selectUserSalesStatusMethod'
							   valueField='salesStatusCode'
							   textField='salesStatusName'
							   panelHeight='auto'
							   /></td> --%>
			<td class="label">销售姓名：</td> <td><input type="text" id="salesName" name="salesName" /></td>
			<tr>
			
			<td class="label">销售工号：</td> <td><input type="text" id="salesNo" name="salesNo" /></td>
			<td class="label">销售手机号：</td> <td><input type="text" id="salesMobile" name="salesNo" /></td>
			
			<td colspan="2" style="text-align:center;">
		    			<a id="searchByConditions"  href="#" class="easyui-linkbutton" onclick="searchByConditions();">搜索</a>
		    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    			<a id="resetConditions"  href="#" class="easyui-linkbutton" onclick="resetConditions();">重置</a>
    				</td>
		</table>
	</form>
		
	</div>
	
	
	<div id="tb">
		<span>
			<a href="javascript:void(0);" class="easyui-linkbutton refresh" iconCls="icon-reload" plain="true" onclick="refresh();">刷新</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
			<a href="javascript:void(0);"  class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="bindingUserA();">绑定</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
			<a href="javascript:void(0);"  class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="detailCustomer();">详情</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
		</span>
    </div>
</body>

</html>
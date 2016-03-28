<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>缓存数据操作</title>
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
inputclass{
 width:300px;
}
</style>
<script type="text/javascript" src="${ctx}/static/js/common_datagrid.js"></script>
<script>
 var grid;
 var i = 0;
 var paramData = {};
 var updateData = {};
$(function(){
	grid = $('#datagrid').datagrid({
		nowrap: false,
		striped: true,
		fit: true,
		url:'${ctx}/dataCaching/selectDataMethod',
	    columns:[[
			{field:'id',title:'id',align:'center',hidden:true},
			{field:'className',title:'类   名',align:'center',width : fixWidth(0.1)},
			{field:'attribution',title:'属   性',align:'center',width : fixWidth(0.1)},
			{field:'codeName',title:'名   称',align:'center',width : fixWidth(0.1)},
			{field:'codeValue',title:'数   值',align:'center',width : fixWidth(0.1)},
			{field:'meaning',title:'定   义',align:'center',width : fixWidth(0.1)},
			{field:'message',title:'说   明',align:'center',width : fixWidth(0.1)}
	        
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
			$.messager.alert('提示信息',"查询缓存数据状态失败！");
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
	paramData.className = $('#className').val(); //类名
	paramData.attribution =  $('#attribution').val();//属性
	paramData.codeName =  $('#codeName').val();//名称
	grid.datagrid('load', getData(paramData));
	grid.datagrid('clearSelections'); 
}


//重置
function resetConditions(){
	$('#className').val(''); //类名
	$('#attribution').val(''); //属性
	$('#codeName').val(''); //名称
}

function edituser(type) {//修改-添加
	var rows = $('#datagrid').datagrid('getSelections');
	if(type=='update'){
	if(rows.length>1){
		$.messager.alert('提示信息','只能选择单条记录件！');
	}else if(rows.length<1){
		$.messager.alert('提示信息','请选择一条记录！');
	}else{
		$("#dlg").dialog("open").dialog('setTitle', '数据修改');
		$(document.getElementsByName('className')).prop('value',rows[0].className);
		$(document.getElementsByName('attribution')).prop('value',rows[0].attribution);
		$(document.getElementsByName('codeName')).prop('value',rows[0].codeName);
		$(document.getElementsByName('codeValue')).prop('value',rows[0].codeValue);
		$(document.getElementsByName('meaning')).prop('value',rows[0].meaning);
		$(document.getElementsByName('message')).prop('value',rows[0].message);
		$(document.getElementsByName('id')).prop('value',rows[0].id);
		$(document.getElementsByName('className')).prop('readonly',true);
		$(document.getElementsByName('attribution')).prop('readonly',true);
		$(document.getElementsByName('codeName')).prop('readonly',true);
	}
	}else if(type=='add'){
		$("#dlg").dialog("open").dialog('setTitle', '新增数据');
		$(document.getElementsByName('className')).prop('value','');
		$(document.getElementsByName('attribution')).prop('value','');
		$(document.getElementsByName('codeName')).prop('value','');
		$(document.getElementsByName('codeValue')).prop('value','');
		$(document.getElementsByName('meaning')).prop('value','');
		$(document.getElementsByName('message')).prop('value','');
		$(document.getElementsByName('id')).prop('value','');
		$(document.getElementsByName('className')).prop('readonly',false);
		$(document.getElementsByName('attribution')).prop('readonly',false);
		$(document.getElementsByName('codeName')).prop('readonly',false);
	}
}

function saveuser() {
	var urls;
	if("" != fm['id'].value){
		urls = '${ctx}/dataCaching/upDateDataMethod';
	}else{
		urls = '${ctx}/dataCaching/addDataMethod';
	}
	updateData.className = fm['className'].value; //类名
	updateData.attribution =  fm['attribution'].value;//属性
	updateData.codeName =  fm['codeName'].value;//名称
	updateData.codeValue =  fm['codeValue'].value;//数值
	updateData.meaning =  fm['meaning'].value;//定义
	updateData.message =  fm['message'].value;//说明
	updateData.id =  fm['id'].value;
	$.ajax({
        type:'POST', //请求方式  
        url:urls, //请求路径  
        cache: false,     
        data:updateData,  //传参  
        dataType: 'json'//返回值类型  
        });
		$('#dlg').dialog('close');
		searchByConditions();
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
			&nbsp;&nbsp;&nbsp;&nbsp;
			类名：<input type="text" id="className"  />&nbsp;&nbsp;&nbsp;&nbsp;
			属性：<input type="text" id="attribution"  />&nbsp;&nbsp;&nbsp;&nbsp;
			名称：<input type="text" id="codeName"  />&nbsp;&nbsp;&nbsp;&nbsp;
				
			<a  href="javascript:void(0);" class="easyui-linkbutton search" iconCls="icon-search" plain="true" onclick="searchByConditions();">搜索</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
			<a  href="javascript:void(0);" class="easyui-linkbutton clear" iconCls="icon-remove" plain="true" onclick="resetConditions();">清空</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
			<a  href="javascript:void(0);" class="easyui-linkbutton clear" iconCls="icon-edit" plain="true" onclick="edituser('update');">修改</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
			<a  href="javascript:void(0);" class="easyui-linkbutton clear" iconCls="icon-add" plain="true" onclick="edituser('add');">添加</a>
	</div>
	<div id="dlg" class="easyui-dialog" style="width: 400px; height: 380px; padding: 10px 20px;" closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<div class="fitem">
				<label>类   名 ：</label> <input name="className" style="width:300px" required="true" />
			</div><br/>
			<div class="fitem">
				<label>属   性 ：</label> <input name="attribution" style="width:300px" required="true" />
			</div><br/>
			<div class="fitem">
				<label>名   称 ：</label> <input name="codeName" style="width:300px" required="true" />
			</div><br/>
			<div class="fitem">
				<label>数   值 ：</label> <input name="codeValue" style="width:300px" required="true" />
			</div><br/>
			<div class="fitem">
				<label>定   义 ：</label> 
				<textarea rows="3" cols="30" name="meaning" style="width:300px"> </textarea>
			</div><br/>
			<div class="fitem">
				<label>说   明 ：</label> 
				<textarea rows="3" cols="30" name="message" style="width:300px"> </textarea>
			</div>
			<input type="hidden" name="id" id="id" />
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="saveuser()" iconcls="icon-save">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a>
	</div>

</body>
</html>
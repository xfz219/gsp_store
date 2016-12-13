<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告查询</title>

<style type="text/css">
	<style type="text/css">
	.query{
		padding:5px 10px;
	}
	.query tr{
		height: 25px;
	}
	form .label{
		width:75px;
		text-align:right;
	}
	input,select{
	width:160px;
	}
	#btn{
		margin:5px 0 0 5px;
	}
</style>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',title:'搜索条件',split:true"  style="overflow: hidden;height:108px;">
		<form id="queryNoticeForm" class="easyui-form">
			<table class="query">
				<tr>
					<td class="label">作者：</td>
					<td>
						<input id="userName" name="userName" class="easyui-textbox"/>
					</td>
					
					<td class="label">当前状态：</td>
					<td>
						<select id="noticeStatus" name="noticeStatus" class="easyui-combobox" style="width:160px;" data-options="editable:false">
								<option value=""></option>	
								<option value="0">草稿</option>
								<option value="1">已发布</option>
						</select>
					</td>
					<td class="label">更新时间：</td>
					<td>
						<input  id="lendNoticeStartTime" name="lendNoticeStartTime" class="easyui-datebox" data-options="width:200,required:false,editable:false"/>
						<label >——</label>
						<input  id="lendNoticeEndTime" name="lendNoticeEndTime" class="easyui-datebox" data-options="width:200,required:false,editable:false"/>
					</td>
					
				</tr>
				
				
				<tr >
					<td colspan="16" align="left">
	    			<a id="searchByConditions"  href="#" class="easyui-linkbutton" onclick="searchByConditions();">搜索</a>
	    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    			<a id="resetConditions"  href="#" class="easyui-linkbutton" onclick="resetConditions();">重置</a>
    			</td>
				</tr>
			</table>
		</form>
	</div>
<div data-options="region:'center',title:'搜索结果',split:true">
		<div id="qryNoticeDatagrid" ></div>
	</div>

<div id="tbLendNotice">
		<span>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="true" id="refresh">刷新</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
				<a  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" id='add'>添加</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
				<a  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id='edit' onclick="edit()">编辑</a>
				<span class="datagrid-btn-separator" style="float:none;"></span>
				<a  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" id='search'  onclick="searchNotice()">查看</a>
				<span class="datagrid-btn-separator" style="float:none;"></span>
			<a  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id='delete' onclick="del()">删除</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
			<a  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" plain="true" id='issue' onclick="issue()">发布</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
		</span>
	
    </div>

 
<script>
var grid;
	$(function(){
		grid = $('#qryNoticeDatagrid').datagrid({
			nowrap: false,
			striped: true,
			fit: true,
			url:'${ctx}/LendNotice/qryNoticeList',
			idField:'id',
			singleSelect:true,
			columns:[
			[
			{field:'id',hidden:true},
			{field:'noticeTitle',title:'标题',width:270,align:'left'},
			{field:'createUser.realName',title:'作者',width:180,align:'left'},
			{field:'updateTime',title:'更新时间',width:220,align:'left',formatter:function(value,data){
				return new Date(data.updateTime).formate("yyyy-MM-dd HH:mm");
			}},
			{field:'noticeStatus',title:'当前状态',width:200,align:'left',
				formatter:function(fieldVal,rowData,rowIndex){
					if(fieldVal=='1'){
						return '已发布';
					}else{
						return '草稿';
					}
				}
			}
			]],
			pagination:true,
			rownumbers:true,
			toolbar:"#tbLendNotice",
			onClickRow: function (rowIndex, rowData) {
				var row = $('#qryNoticeDatagrid').datagrid('getSelections');
				if(row.length>1){
				
					$("#edit").linkbutton("disable");
					$("#search").linkbutton("disable");
					for(var i=0;i<row.length;i++){
						if(row[i].noticeStatus=='1'){
							$("#issue").linkbutton("disable");
							$("#delete").linkbutton("disable");
							return false;
						}else{
							$("#issue").linkbutton("enable");
							$("#delete").linkbutton("enable");
						}
					}
				}if(row.length==0){
					$("#edit").linkbutton("disable");
					$("#search").linkbutton("disable");
					$("#delete").linkbutton("disable");
					$("#issue").linkbutton("disable");
				} else if(row.length==1){
					$("#search").linkbutton("enable");
 					if(row[0].noticeStatus=='1'){
 					$("#issue").linkbutton("disable");
 					$("#delete").linkbutton("disable");
 					$("#edit").linkbutton("disable");
 					}else{
 						$("#issue").linkbutton("enable");
 						$("#delete").linkbutton("enable");
 						$("#edit").linkbutton("enable");
 					}
				}
			},
			onLoadSuccess:function (data){
				$("#edit").linkbutton("disable");
				$("#search").linkbutton("disable");
				$("#delete").linkbutton("disable");
				$("#issue").linkbutton("disable");
			}
 		}
		);
		
		//刷新
		$('#refresh').click(function(){
				resetConditions();
		     grid.datagrid('reload'); 
		     grid.datagrid('clearSelections'); 
		});
		
		//添加
		$('#add').click(function(){
			parent.$("#tabs").tabs("add",{
				closable:true,
				title:'添加公告',
				content : '<iframe name="addNotice" id="addNotice" scrolling="no" frameborder="0"  src="${ctx}/addLendNotice" width="100%" height="99%"></iframe>'
			});
	});
});

	//发布
	function issue(){
		var ids=[];
		var row = $('#qryNoticeDatagrid').datagrid('getSelections');
		if(row.length<1){
			$.messager.alert('提示信息','请选择一条记录！');
			return false;
		}else if(row.length>1){
			$.messager.alert('提示信息','请选择一条记录进行发布！');
			return false;
		}
		$.messager.confirm('提示信息','确认发布吗?',function(r){  
		if(r){
			/* $.each(row, function(i, k) {
				ids.push(row[i].id);
			}); */
			$.ajax({
				url:"./LendNotice/isuseLendNotice",
				type: "POST",
				async: false,
				data:{'id':row[0].id},
				dataType : "json",
				success:function(data){
					if(data.success){
						resetConditions();
						grid.datagrid('reload'); 
						grid.datagrid('clearSelections'); 
      			  	}
					$.messager.alert('提示信息',data.msg);
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					$.messager.alert('提示信息',XMLHttpRequest.responseText);
				}
		
	});
		}
		});
}

	//编辑
	function edit(){
		var row = $('#qryNoticeDatagrid').datagrid('getSelections');
		if(row.length<1){
			$.messager.alert('提示信息','请选择一条记录！');
			return false;
			}
		if(row.length>1){
			$.messager.alert('提示信息','只能选择单条记录进行修改！');
			return false;
			}
		parent.$("#tabs").tabs("add",{
			closable:true,
			title:'编辑公告',
			content : '<iframe name="editNotice" id="editNotice" scrolling="no" frameborder="0"  src="${ctx}/LendNotice/getLendNoticeById/'+row[0].id+'/edit" width="100%" height="99%"></iframe>'
		});
	}
	
	//查看
function searchNotice(){
	var row = $('#qryNoticeDatagrid').datagrid('getSelections');
	if(row.length<1){
		$.messager.alert('提示信息','请选择一条记录！');
		return false;
		}
	if(row.length>1){
		$.messager.alert('提示信息','只能选择单条记录进行修改！');
		return false;
		}
	parent.$("#tabs").tabs("add",{
		closable:true,
		title:'查看公告',
		content : '<iframe name="editNotice" id="editNotice" scrolling="no" frameborder="0"  src="${ctx}/LendNotice/getLendNoticeById/'+row[0].id+'/look" width="100%" height="99%"></iframe>'
	});
	}
	//删除
	function del(){
		var ids=[];
		var row = $('#qryNoticeDatagrid').datagrid('getSelections');
		if(row.length<1){
			$.messager.alert('提示信息','请选择一条记录！');
			return false;
		}
		if(row.length>1){
			$.messager.alert('提示信息','只能选择单条记录进行删除！');
			return false;
		}
		$.messager.confirm('提示信息','确认删除吗?',function(r){  
    		if(r){
    			/* $.each(row, function(i, k) {
    				ids.push(row[i].id);
    			}); */
    			$.ajax({
					url:"./LendNotice/deleteLendNotice",
					type: "POST",
					async: false,
					data:{'id':row[0].id},
					dataType : "json",
					success:function(data){
						if(data.success){
							resetConditions();
							grid.datagrid('reload'); 
							grid.datagrid('clearSelections'); 
          			  	}
						$.messager.alert('提示信息',data.msg);
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						$.messager.alert('提示信息',XMLHttpRequest.responseText);
  					}
    		
	});
		}
		});
	}
	
//Enter搜索
$('#queryNoticeForm').keypress(function(e){
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
//搜索
function searchByConditions(){
	var dataObj = {};
	var lendNoticeStartTime=$('#lendNoticeStartTime').datebox('getValue'),
		lendNoticeEndTime=$('#lendNoticeEndTime').datebox('getValue'); 
	dataObj.userName = $('#userName').val(); 
	dataObj.noticeStatus = $('#noticeStatus').combobox('getValue');
	if(lendNoticeStartTime){
		dataObj.lendNoticeStartTime =lendNoticeStartTime;
	}
if(lendNoticeEndTime){
	dataObj.lendNoticeEndTime =lendNoticeEndTime; 
}
	$("#qryNoticeDatagrid").datagrid('load',dataObj); 
	$('#qryNoticeDatagrid').datagrid('clearSelections'); 
}


//重置
function resetConditions(){
	$('#queryNoticeForm').form('clear');
}
</script>
</body>
</html>
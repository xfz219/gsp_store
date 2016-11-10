<%@page import="com.puhui.model.uc.Staff"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>广告位管理</title>

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
	<div id="tbLendAdvertisement">
		<span>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="true" id="refresh">刷新</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
			<a  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" id='add'>添加</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
			<a  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id='edit' onclick="edit()">编辑</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
			<a  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" id='search'  onclick="view()">查看</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
			<a  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id='delete' onclick="del()">删除</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
			<a  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id='enableBtn' onclick="enableBtn()">启用</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
			<a  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id='disableBtn' onclick="disableBtn()">禁用</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
			<a  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id='forward' onclick="forward()">排位提前一位</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
			<a  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id='later' onclick="later()">排位推后一位</a>
		</span>

    </div>


<script>
	var grid;
	$(function(){
		grid = $('#qryAdDatagrid').datagrid({
			nowrap: false,
			striped: true,
			fit: true,
			url:'${ctx}/lendAdvertisement/queryLendAdvertisementList',
			idField:'id',
			singleSelect:true,
			columns:[
			[
				{field:'id',hidden:true},
				{field:'name',title:'广告名称',width:200,align:'left'},
				{field:'customerIdentity',title:'客户身份',width:180,align:'left',
					formatter:function(value,data){
						if(value=='0'){
							return '薪类客户';
						}else if(value=='1'){
							return '商类客户';
						}
					}
				},
				{field:'sort',title:'排位',width:60,align:'left'},
				{field:'updateTime',title:'更新时间',width:220,align:'left',formatter:function(value,data){
					return new Date(data.updateTime).formate("yyyy-MM-dd HH:mm");
				}},
				{field:'enabled',title:'是否启用',width:80,align:'left',
					formatter:function(value,rowData,rowIndex){
						if(value=='0'){
							return '否';
						}else if(value=='1'){
							return '是';
						}
					}
				},
				{field:'Operation',title:'图片操作',width:120,rowspan:2,formatter: function(value, data, index){
					if(data.originalPicName){
						return '<a href="javascript:void(0);" class="easyui-linkbutton"style="color: #00f;" iconCls="icon-earch" onclick="viewFile(\''+data.id+'\', \''+data.originalPicName+'\');"  >预览</a> ';	
					}else{
						return '<font color="green">未上传图片</font>';
					}
				}}
			]],
			pagination:true,
			rownumbers:true,
			pageSize: 20,
			toolbar:"#tbLendAdvertisement",
			onClickRow: function (rowIndex, rowData) {
				var row = $('#qryAdDatagrid').datagrid('getSelections');
				if(row.length>1){

					$("#edit").linkbutton("disable");
					$("#search").linkbutton("disable");
					for(var i=0;i<row.length;i++){
						if(row[i].noticeStatus=='1'){
							$("#delete").linkbutton("disable");
							return false;
						}else{
							$("#delete").linkbutton("enable");
						}
					}
				}if(row.length==0){
					$("#edit").linkbutton("disable");
					$("#search").linkbutton("disable");
					$("#delete").linkbutton("disable");
					$("#forward").linkbutton("disable");
					$("#later").linkbutton("disable");
					$("#enableBtn").linkbutton("disable");
					$("#disableBtn").linkbutton("disable");
				} else if(row.length==1){
					$("#search").linkbutton("enable");
					$("#delete").linkbutton("enable");
					$("#edit").linkbutton("enable");
					$("#forward").linkbutton("enable");
					$("#later").linkbutton("enable");
					if(row[0].enabled == '1'){
						$("#enableBtn").linkbutton("disable");
						$("#disableBtn").linkbutton("enable");
					}else if(row[0].enabled == '0'){
						$("#enableBtn").linkbutton("enable");
						$("#disableBtn").linkbutton("disable");
					}
				}
			},
			onLoadSuccess:function (data){
				$("#edit").linkbutton("disable");
				$("#search").linkbutton("disable");
				$("#delete").linkbutton("disable");
				$("#forward").linkbutton("disable");
				$("#later").linkbutton("disable");
				$("#enableBtn").linkbutton("disable");
				$("#disableBtn").linkbutton("disable");
			}
 		});

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
				title:'添加广告位',
				content : '<iframe name="addAdvertisement" id="addAdvertisement" scrolling="no" frameborder="0"  src="${ctx}/lendAdvertisement/lendAdvertisementDetail/0" width="100%" height="99%"></iframe>'
			});
		});
	});


	//编辑
	function edit(){
		var row = $('#qryAdDatagrid').datagrid('getSelections');
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
			title:'编辑广告位',
			content : '<iframe name="editAdvertisement" id="editAdvertisement" scrolling="no" frameborder="0"  src="${ctx}/lendAdvertisement/lendAdvertisementDetail/'+row[0].id+'" width="100%" height="99%"></iframe>'
		});
	}

	//查看
	function view(){
		var row = $('#qryAdDatagrid').datagrid('getSelections');
		if(row.length<1){
			$.messager.alert('提示信息','请选择一条记录！');
			return false;
			}
		if(row.length>1){
			$.messager.alert('提示信息','只能选择单条记录进行查看！');
			return false;
			}
		parent.$("#tabs").tabs("add",{
			closable:true,
			title:'查看广告位',
			content : '<iframe name="advertisementDetail" id="advertisementDetail" scrolling="no" frameborder="0"  src="${ctx}/lendAdvertisement/viewLendAdvertisement/'+row[0].id+'" width="100%" height="99%"></iframe>'
		});
	}
	
	//启用广告位
	function enableBtn(){
		var row = $('#qryAdDatagrid').datagrid('getSelections');
		if(row.length<1){
			$.messager.alert('提示信息','请选择一条记录！');
			return false;
		}else if(row.length>1){
			$.messager.alert('提示信息','只能选择单条记录进行修改！');
			return false;
		}else if(row[0].enabled == '1'){
			$.messager.alert('提示信息','该广告位已启用！');
			return false;
		}else{
			$.messager.confirm('警告', '确定启用该广告位吗?',function(r){
				if(r){
					$.ajax({
					   url : '${ctx}/lendAdvertisement/enableLendAdvertisement',
					   data : {"id":row[0].id},
					   type : 'POST',
					   cache : false,
					   dataType : "json",
					   success : function(data) {
						   $.messager.alert('提示信息',data.returnEntity.msg);
			        	   grid.datagrid('clearSelections'); 
			        	   grid.datagrid('load');
					   },
					   error: function(XMLHttpRequest, textStatus, errorThrown){
						   $.messager.alert('提示信息',XMLHttpRequest.responseText);
					   }
					});
				}
			});
		}
	}
	
	//禁用广告位
	function disableBtn(){
		var row = $('#qryAdDatagrid').datagrid('getSelections');
		if(row.length<1){
			$.messager.alert('提示信息','请选择一条记录！');
			return false;
		}else if(row.length>1){
			$.messager.alert('提示信息','只能选择单条记录进行修改！');
			return false;
		}else if(row[0].enabled == '0'){
			$.messager.alert('提示信息','该广告位已禁用！');
			return false;
		}else{
			$.messager.confirm('警告', '确定禁用该广告位吗?',function(r){
				if(r){
					$.ajax({
					   url : '${ctx}/lendAdvertisement/disableLendAdvertisement',
					   data : {"id":row[0].id},
					   type : 'POST',
					   cache : false,
					   dataType : "json",
					   success : function(data) {
						   $.messager.alert('提示信息',data.returnEntity.msg);
			        	   grid.datagrid('clearSelections'); 
			        	   grid.datagrid('load');
					   },
					   error: function(XMLHttpRequest, textStatus, errorThrown){
						   $.messager.alert('提示信息',XMLHttpRequest.responseText);
					   }
					});
				}
			});
		}
	}
	
	//排位提前一位
	function forward(){
		var row = $('#qryAdDatagrid').datagrid('getSelections');
		if(row.length<1){
			$.messager.alert('提示信息','请选择一条记录！');
			return false;
		}else if(row.length>1){
			$.messager.alert('提示信息','只能选择单条记录进行修改！');
			return false;
		}else if(row[0].sort == 1){
			$.messager.alert('提示信息','该广告位的排位已是第一位，不能再提前！');
			return false;
		}else{
			$.messager.confirm('警告', '确定对该广告位进行提前排位操作吗?',function(r){
				if(r){
					$.ajax({
					   url : '${ctx}/lendAdvertisement/forwartLendAdvertisementSort',
					   data : {"id":row[0].id},
					   type : 'POST',
					   cache : false,
					   dataType : "json",
					   success : function(data) {
						   $.messager.alert('提示信息',data.returnEntity.msg);
			        	   grid.datagrid('clearSelections'); 
			        	   grid.datagrid('load');
					   },
					   error: function(XMLHttpRequest, textStatus, errorThrown){
						   $.messager.alert('提示信息',XMLHttpRequest.responseText);
					   }
					});
				}
			});
		}
	}
	
	//排位推后一位
	function later(){
		var row = $('#qryAdDatagrid').datagrid('getSelections');
		if(row.length<1){
			$.messager.alert('提示信息','请选择一条记录！');
			return false;
		}else if(row.length>1){
			$.messager.alert('提示信息','只能选择单条记录进行修改！');
			return false;
		}else{
			$.messager.confirm('警告', '确定对该广告位进行提前推后操作吗?',function(r){
				if(r){
					$.ajax({
					   url : '${ctx}/lendAdvertisement/laterLendAdvertisementSort',
					   data : {"id":row[0].id},
					   type : 'POST',
					   cache : false,
					   dataType : "json",
					   success : function(data) {
						   $.messager.alert('提示信息',data.returnEntity.msg);
			        	   grid.datagrid('clearSelections'); 
			        	   grid.datagrid('load');
					   },
					   error: function(XMLHttpRequest, textStatus, errorThrown){
						   $.messager.alert('提示信息',XMLHttpRequest.responseText);
					   }
					});
				}
			});
		}
	}
	
	//删除
	function del(){
		var row = $('#qryAdDatagrid').datagrid('getSelections');
		if(row.length<1){
			$.messager.alert('提示信息','请选择一条记录！');
			return false;
		}else if(row.length > 1){
			$.messager.alert('提示信息','最多选择一条记录！');
			return false;
		}else{
			$.messager.confirm('提示信息','确认删除吗?',function(r){
    		if(r){
    			$.ajax({
					url:"./lendAdvertisement/deleteLendAdvertisement",
					type: "POST",
					async: false,
					data:{'id':row[0].id},
					dataType : "json",
					success:function(data){
						if(data.returnEntity.success){
							/* resetConditions(); */
							/* grid.datagrid('clearSelections'); */
							
							$.messager.alert('提示信息',data.returnEntity.msg);
				        	grid.datagrid('load');
          			  	}else{
          			  		$.messager.alert('提示信息',data.returnEntity.msg);
          			  	}
						
          			  	
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						$.messager.alert('提示信息',XMLHttpRequest.responseText);
  					}

				});
			}
		});
		}
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

	var paramData = {};
	//搜索
	function searchByConditions(){
		paramData.name = $('#name').val(); 
		paramData.customerIdentity = $('#customerIdentity').combobox('getValue');
		paramData.customerLendStatus = $('#customerLendStatus').combobox('getValue');
		paramData.enabled = $('#enabled').combobox('getValue');
		paramData.startTime = $('#startTime').combobox('getValue');
		paramData.endTime = $('#endTime').combobox('getValue');
		grid.datagrid('load', getData(paramData));
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
		return temp;
	}

	//重置
	function resetConditions(){
		$('#queryNoticeForm').form('clear');
	}
	
	//预览
    function viewFile(data, fileName){
		
    	fileName = fileName.toLocaleLowerCase();
    	if (fileName.indexOf(".jpg") != -1 || fileName.indexOf(".gif") != -1
    				|| fileName.indexOf(".png") != -1
    				|| fileName.indexOf(".jpeg") != -1
    				|| fileName.indexOf(".bmp") != -1) {
    			window.open("${ctx}/lendAdvertisement/previewForAjax?id="+ data,
    					'详细','height=800,width=1024,top=50,left=100,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
   		} else {
   			$.messager.alert('提示信息', '该广告位图片类型不是制定的上传类型，不支持在线预览');
   		}

   	}
</script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>系统升级</title>

    <style type="text/css">
        .query {
            padding: 5px 10px;
        }

        .query tr {
            height: 25px;
        }

        form .label {
            width: 75px;
            text-align: right;
        }

        input, select {
            width: 160px;
        }

    </style>

    <script>
        var grid;
        $(function(){
            grid = $('#dataGrid').datagrid({
                nowrap: false,
                striped: true,
                fit: false,
                pagination:false,
                rownumbers:true,
                singleSelect:true,
                toolbar:"#tbSystem",
                url:'${ctx}/system/list',
                idField:'id',
                columns:[[
                    {field:'ck',checkbox:true},
                    {field:'id',title:'id',align:'center',hidden:true},
                    {field:'typeName',title:'服务类型',align:'center',width:110},
                    {field:'clientTypeName',title:'终端类型',align:'center',width:110},
                    {field:'version',title:'版本号',align:'center',width:120},
                    {field:'memo',title:'升级提示',align:'center',width:250},
                    {field:'app',title:'升级地址',align:'center',width:350},
                    {field:'updateTime',title:'更新时间',align:'center',width:100},
                    {field:'createTime',title:'创建时间',align:'center',width:100}
                ]],
                onLoadSuccess:function (data){
                    $('#dataGrid').datagrid("unselectAll");
                },
                onLoadError:function (data) {
                    $.messager.alert('提示信息',"查询失败！");
                }
            });

        });

        function update(){
            var rows = grid.datagrid('getSelections');
            if (rows.length != 1) {
                $.messager.alert('提示信息', '请选择一条记录！');
            }
            alert(rows[0].typeName);
        }
    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'center',title:'升级列表',split:true">
    <div id="dataGrid"></div>
</div>
<div id="tbSystem">
		<span>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
               id='update' onclick="update()";>修改</a>
			<span class="datagrid-btn-separator" style="float:none;"></span>
		</span>
</div>

</body>
</html>
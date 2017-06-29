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
                toolbar:"#tbSystem",
                url:'${ctx}/system/list',
                singleSelect: true,
                columns:[[
                    {field:'id',title:'id',align:'center',hidden:true},
                    {field:'typeName',title:'服务类型',align:'center',width:110},
                    {field:'clientTypeName',title:'终端类型',align:'center',width:110},
                    {field:'version',title:'版本号',align:'center',width:120},
                    {field:'memo',title:'升级提示',align:'center',width:250,
                        formatter: function(value){
                            return "<span title='" + value + "'>" + value + "</span>";
                        }},
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
                return;
            }
            $("#updateSystemForm")[0].reset();
            $('#updateSystem').dialog({
                title: "修改",
                width: 600,
                height: 400,
                modal: true,
                closed:false
            });
        }

        function YES() {
            var rows = grid.datagrid('getSelections');
            if (rows.length != 1) {
                $.messager.alert('提示信息', '请选择一条记录！');
                return;
            }
            $.messager.confirm('确定','您确定修改吗？',function(r){
                if(r){
                    var dataJson = {"typeName":rows[0].typeName,
                        "id":rows[0].id,
                        "version":$("#version").val(),
                        "memo":$("#memo").val(),
                        "app":$("#app").val()};
                    $.ajax({
                        url: '${ctx}/system/update',
                        data: dataJson,
                        type: 'POST',
                        cache: false,
                        dataType: "json",
                        async:false,
                        success: function(data) {
                            if(data){
                                $.messager.alert('提示信息', "修改成功！");
                                grid.datagrid('reload');
                            } else{
                                $.messager.alert('提示信息', "修改失败！");
                            }
                            $('#updateSystem').dialog('close');
                        },
                        error: function(XMLHttpRequest, textStatus, errorThrown) {
                            $.messager.alert('提示信息', "请求出现错误！");
                        }
                    });
                }
            });
        }

        function NO() {
            $('#updateSystem').dialog({
                closed:true
            });
        }
    </script>
</head>
<body class="easyui-layout">

<div id="updateSystem" class="easyui-dialog" title="修改" style="width:600px;height:400px;" data-options="iconCls:'icon-save',resizable:true,closed:true" >
    <form id="updateSystemForm">
        <div style="margin: 20px 0px 0px 80px;">
            <label>&nbsp;&nbsp;&nbsp;&nbsp;版本号：</label>
            <input class="easyui-textbox" id="version" name="version" data-options="required:true;" style="width: 300px"/>
            <br><br>
            <label>升级提示：</label>
            <input class="easyui-textbox" id="memo" name="memo" data-options="required:true" style="width: 300px;"/>
            <br><br>
            <label>升级地址：</label>
            <input class="easyui-textbox" id="app" name="app" data-options="required:true;" style="width: 300px"/>
            <br><br>
            <div style="margin: 0px 0px 0px 50px;">
                <a href="#" onclick="YES();" class="easyui-linkbutton">确认</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="#" onclick="NO();" class="easyui-linkbutton">取消</a>
            </div>
        </div>
    </form>
</div>


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
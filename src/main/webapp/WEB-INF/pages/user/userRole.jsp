<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>权限管理</title>

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

        #btn {
            margin: 5px 0 0 5px;
        }
    </style>

</head>
<body class="easyui-layout">
<div data-options="region:'north',title:'搜索条件',split:true" style="overflow: hidden;height:70px;">
    <form id="queryNoticeForm" class="easyui-form">
        <table class="query">
            <input id="userId" name="userId" class="easyui-textbox"/>
        </table>
    </form>
</div>
<div data-options="region:'center',title:'搜索结果',split:true">
    <table id="getUserDatagrid"></table>
</div>
<div id="tbLendNotice">
    <span>
        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="true" id="refresh">刷新</a>
        <span class="datagrid-btn-separator" style="float:none;"></span>
        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" id='add'
           onclick="add()">新增角色</a>
        <span class="datagrid-btn-separator" style="float:none;"></span>
        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id='userRoleBtn'
           onclick="disableBtn()">删除角色</a>
    </span>
</div>

<script language="javascript">

    var userId = ${gspUser.id};

    window.top["reload_Abnormal_Monitor"]=function(){
        resetConditions();
        grid.datagrid('reload');
        grid.datagrid('clearSelections');
    };

    var grid;
    $(function () {

        grid = $('#getUserDatagrid').datagrid({
                nowrap: false,
                striped: true,
                fit: true,
                url: '${ctx}/user/queryUserRole',
                queryParams:{"userId": userId},
                singleSelect: true,
                columns: [
                    [
                        {field: 'id', hidden: true},
                        {field: 'userId', title: '用户名', width: 130, align: 'left'},
                        {field: 'roleId', title: '用户密码', width: 130, align: 'left'},
                        {field: 'name', title: '用户姓名', width: 130, align: 'left'},
                        {field: 'mobile', title: '用户手机号', width: 130, align: 'left'},
                        {field: 'email', title: '用户邮箱', width: 130, align: 'left'},
                        {field: 'org', title: '组织', width: 130, align: 'left'},
                        {
                            field: 'createTime',
                            title: '创建时间',
                            width: 130,
                            align: 'left',
                            formatter: function (value, data) {
                                return new Date(data.createTime).formate("yyyy-MM-dd HH:mm");
                            }
                        },
                        {
                            field: 'updateTime',
                            title: '更新时间',
                            width: 130,
                            align: 'left',
                            formatter: function (value, data) {
                                return new Date(data.updateTime).formate("yyyy-MM-dd HH:mm");
                            }
                        }
                    ]],
                pagination: true,
                rownumbers: true,
                pageList: [10, 20, 30],//选择一页显示多少数据
                loadFilter: pagerFilter,
                toolbar: "#tbLendNotice",
                onLoadError: function (data) {
                    $.messager.alert('提示信息', "查询失败！");
                }
            }
        );

        //分页功能
        function pagerFilter(data) {
            if (typeof data.length == 'number' && typeof data.splice == 'function') {
                data = {
                    total: data.length,
                    rows: data
                }
            }
            var dg = $(this);
            var opts = dg.datagrid('options');
            var pager = dg.datagrid('getPager');
            pager.pagination({
                onSelectPage: function (pageNum, pageSize) {
                    opts.pageNumber = pageNum;
                    opts.pageSize = pageSize;
                    pager.pagination('refresh', {
                        pageNumber: pageNum,
                        pageSize: pageSize
                    });
                    dg.datagrid('loadData', data);
                }
            });
            if (!data.originalRows) {
                if (data.rows)
                    data.originalRows = (data.rows);
                else if (data.data && data.data.rows)
                    data.originalRows = (data.data.rows);
                else
                    data.originalRows = [];
            }
            var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
            var end = start + parseInt(opts.pageSize);
            data.rows = (data.originalRows.slice(start, end));
            return data;
        }

        //刷新
        $('#refresh').click(function () {
            grid.datagrid('reload');
            grid.datagrid('clearSelections');
        });

    });

    //搜索
    function searchByConditions() {
        var dataObj = {};
        dataObj.userId = $('#userId').val();
        $("#getUserDatagrid").datagrid('load', dataObj);
        $('#getUserDatagrid').datagrid('clearSelections');
    }

    //禁用广告位
    function disableBtn() {
        var row = $('#getUserDatagrid').datagrid('getSelections');
        if (row.length < 1) {
            $.messager.alert('提示信息', '请选择一条记录！');
            return false;
        } else if (row.length > 1) {
            $.messager.alert('提示信息', '只能选择单条记录进行修改！');
            return false;
        } else if (row[0].enable == '0') {
            $.messager.alert('提示信息', '该用户已禁用！');
            return false;
        } else {
            $.messager.confirm('警告', '确定禁用该用户吗?', function (r) {
                if (r) {
                    $.ajax({
                        url: '${ctx}/user/queryUserRole',
                        data: {"id": row[0].id},
                        type: 'POST',
                        cache: false,
                        dataType: "json",
                        success: function (dataObj) {
                            if (dataObj.code == '200') {
                                grid.datagrid('clearSelections');
                                grid.datagrid('load');
                            }
                            $.messager.alert('提示信息', dataObj.message);
                        }
                    });
                }
            });
        }
    }
</script>
</body>
</html>
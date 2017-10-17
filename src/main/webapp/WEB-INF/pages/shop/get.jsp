<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>门店信息配置</title>

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
                <td class="label">门店名称：</td>
                <td>
                    <input id="shopName" name="shopName" class="easyui-textbox"/>
                </td>

                <td class="label">启用状态：</td>
                <td>
                    <select id="enabled" name="enabled" class="easyui-combobox" style="width:160px;" data-options="editable:false">
                        <option value="">所有状态</option>
                        <option value="0">未启用</option>
                        <option value="1">已启用</option>
                    </select>
                </td>
                <td colspan="16" align="left">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
        <a  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" id='update'  onclick="update()">修改</a>
        <span class="datagrid-btn-separator" style="float:none;"></span>
        <a  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id='enableBtn' onclick="enableBtn()">启用</a>
        <span class="datagrid-btn-separator" style="float:none;"></span>
        <a  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id='disableBtn' onclick="disableBtn()">禁用</a>
    </span>
</div>

<script>
    var grid;
    $(function(){
        grid = $('#qryNoticeDatagrid').datagrid({
                nowrap: false,
                striped: true,
                fit: true,
                url:'${ctx}/shop/list',
                singleSelect:true,
                columns:[
                    [
                        {field:'id',hidden:true},
                        {field:'parentId',title:'父ID',width:130,align:'left'},
                        {field:'shopCode',title:'门店CODE',width:130,align:'left'},
                        {field:'shopName',title:'门店NAME',width:130,align:'left'},
                        {field:'shopMobile',title:'门店手机号',width:130,align:'left'},
                        {field:'shopAddress',title:'门店地址',width:130,align:'left'},
                        {field:'longitude',title:'经度',width:130,align:'left'},
                        {field:'latitude',title:'纬度',width:130,align:'left'},
                        {field:'enabled',title:'使用状态',width:130,align:'left',
                            formatter:function(fieldVal,rowData,rowIndex){
                                if(fieldVal=='1'){
                                    return '已启用';
                                }else{
                                    return '未启用';
                                }
                            }
                        },
                        {field:'updateTime',title:'更新时间',width:130,align:'left',formatter:function(value,data){
                            return new Date(data.updateTime).formate("yyyy-MM-dd HH:mm");
                        }}
                    ]],
                pagination:true,
                rownumbers:true,
                toolbar:"#tbLendNotice",
                onLoadError:function (data) {
                    $.messager.alert('提示信息',"查询失败！");
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
                title:'添加门店',
                content : '<iframe src="${ctx}/shop/add" width="100%" height="99%"></iframe>'
            });
        });
    });



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
        dataObj.shopName = $('#shopName').val();
        dataObj.enabled = $('#enabled').combobox('getValue');
        $("#qryNoticeDatagrid").datagrid('load',dataObj);
        $('#qryNoticeDatagrid').datagrid('clearSelections');
    }


    //重置
    function resetConditions(){
        $('#queryNoticeForm').form('clear');
    }

    function update(){
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
            title:'修改门店',
            content : '<iframe name="updateShop" id="updateShop" scrolling="no" frameborder="0"  src="${ctx}/shop/update/'+row[0].id+'" width="100%" height="99%"></iframe>'
        });
    }

    function add(){
        parent.$("#tabs").tabs("add",{
            closable:true,
            title:'添加门店',
            content : '<iframe name="addShop" id="addShop" scrolling="no" frameborder="0"  src="${ctx}/shop/add" width="100%" height="99%"></iframe>'
        });
    }

    //启用广告位
    function enableBtn(){
        var row = $('#qryNoticeDatagrid').datagrid('getSelections');
        if(row.length<1){
            $.messager.alert('提示信息','请选择一条记录！');
            return false;
        }else if(row.length>1){
            $.messager.alert('提示信息','只能选择单条记录进行修改！');
            return false;
        }else if(row[0].enabled == '1'){
            $.messager.alert('提示信息','该门店已启用！');
            return false;
        }else{
            $.messager.confirm('警告', '确定启用该门店吗?',function(r){
                if(r){
                    $.ajax({
                        url : '${ctx}/shop/enable',
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
        var row = $('#qryNoticeDatagrid').datagrid('getSelections');
        if(row.length<1){
            $.messager.alert('提示信息','请选择一条记录！');
            return false;
        }else if(row.length>1){
            $.messager.alert('提示信息','只能选择单条记录进行修改！');
            return false;
        }else if(row[0].enabled == '0'){
            $.messager.alert('提示信息','该门店已禁用！');
            return false;
        }else{
            $.messager.confirm('警告', '确定禁用该门店吗?',function(r){
                if(r){
                    $.ajax({
                        url : '${ctx}/shop/stop',
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
</script>
</body>
</html>
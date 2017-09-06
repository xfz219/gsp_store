<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>奖励查询</title>

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
                <td class="label">奖品类型：</td>
                <td>
                    <select id="prizeType" name="prizeType" class="easyui-combobox" style="width:160px;" data-options="editable:false">
                        <option value="">所有类型</option>
                        <option value="JD_100">100元京东卡</option>
                        <option value="JD_50">50元京东卡</option>
                        <option value="I_QI_YI">爱奇艺会员月卡</option>
                    </select>
                </td>

                <td class="label">使用状态：</td>
                <td>
                    <select id="use" name="use" class="easyui-combobox" style="width:160px;" data-options="editable:false">
                        <option value="">所有状态</option>
                        <option value="0">未使用</option>
                        <option value="1">已使用</option>
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

<script>
    var grid;
    $(function(){
        grid = $('#qryNoticeDatagrid').datagrid({
                nowrap: false,
                striped: true,
                fit: true,
                url:'${ctx}/prizes/list',
                singleSelect:true,
                columns:[
                    [
                        {field:'id',hidden:true},
                        {field:'prizeType',title:'奖品类型',width:200,align:'left',
                            formatter:function(fieldVal,rowData,rowIndex){
                                if(fieldVal=='JD_100'){
                                    return '100元京东卡';
                                }else if(fieldVal == 'JD_50'){
                                    return '50元京东卡';
                                }else {
                                    return '爱奇艺会员月卡';
                                }
                            }
                        },
                        {field:'cardNumber',title:'卡号',width:180,align:'left'},
                        {field:'password',title:'密码',width:180,align:'left'},
                        {field:'use',title:'使用状态',width:200,align:'left',
                            formatter:function(fieldVal,rowData,rowIndex){
                                if(fieldVal=='1'){
                                    return '未使用';
                                }else{
                                    return '未使用';
                                }
                            }
                        },
                        {field:'updateTime',title:'更新时间',width:220,align:'left',formatter:function(value,data){
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
        dataObj.prizeType = $('#prizeType').combobox('getValue');
        dataObj.use = $('#use').combobox('getValue');
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
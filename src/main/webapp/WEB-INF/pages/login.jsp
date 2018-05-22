<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="taglibs.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Pharmacy Login</title>

    <!-- EasyUI CSS -->
    <style type="text/css">
        * {
            font-size: 15px;
            font-family: 'Open Sans', Arial, Helvetica, sans-serif;
        }

        h1, h1 span {
            font-size: 48px;
            font-family: 'Roboto', '微软雅黑';
            font-weight: 700;
        }

        .footer {
            margin: 10px auto;
        }
    </style>

    <script type="text/javascript" charset="UTF-8">

        $(document).ready(function () {
            //登录
            $('#loginBtn').click(function () {
                $('#loginForm').form('submit', {
                    url: '${ctx}/user/login',
                    success: function (data) {
                        data = eval("(" + data + ")");
                        if (data.code == 200) {
                            $.messager.alert('提示信息', data.result, 'info', function () {
//                                parent.$("#tabs").tabs("select", "添加门店");
//                                parent.$("#tabs").tabs("close", "添加门店");
                            });
                        } else {
                            $.messager.alert('提示信息', data.result);
                        }
                    }
                });
            });
        });

    </script>
</head>

<body>
<div
        style="text-align: center;overflow:auto;width:100%;height:100%;margin: 100px auto;">
    <h1><span style="color:#8FC31F">Pharmacy</span>-<span style="">Platform</span></h1>
    <div style="margin: 10px auto;">
    </div>
    <div style="margin: 0 auto;width: 500px;">
        <div class="easyui-panel" title="User Login" style="width:500px;">
            <div style="padding:10px 60px 20px 60px;">
                <form id="loginForm" class="easyui-form" method="post"
                      data-options="novalidate:true" action="toLogin">

                    <table cellpadding="5" style="">

                        <tr>
                            <td width="90">用户:</td>
                            <td><input class="easyui-textbox" type="text"
                                       name="user" id="user" style="height:30px;width: 190px;"
                                       data-options="validType:[],required:true,prompt:'user name...'"/></td>
                        </tr>
                        <tr>
                            <td>密码:</td>
                            <td><input class="easyui-textbox" type="password"
                                       name="password" style="height:30px;width: 190px;"
                                       data-options="required:true"/></td>
                        </tr>
                    </table>
                </form>
                <div style="text-align:center;padding:5px">
                    <a href="javascript:void(0)" class="easyui-linkbutton"
                       id="loginBtn">登 录</a>
                </div>

            </div>
        </div>

    </div>
    <div class="footer">
        <p>
            © 2018 - 2018 Ray
        </p>
        <p>
            联系、反馈、定制、培训/Contact, Feedback, Custom, Train Email：<a
                href="#">baidu@163.com</a>
        </p>
    </div>

</div>

</body>
</html>

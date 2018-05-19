<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<base href="<%=basePath%>">

<title>Pharmacy Login</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="sh,easyee,javaee,framework,java">
<meta http-equiv="description" content="EasyEE-SM basic framework by EasyProject">
 

<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700|Montserrat:400,700|Roboto:400,700,900" rel="stylesheet"> 
<!-- EasyUI CSS -->
<link rel="stylesheet" type="text/css" href="staticresources/easyui/themes/metro-blue/easyui.css" id="themeLink">
<link rel="stylesheet" type="text/css" href="staticresources/easyui/themes/icon.css">
<style type="text/css">
* {
	font-size: 15px;
	font-family: 'Open Sans', Arial, Helvetica, sans-serif; 
}

h1,h1 span {
	font-size: 48px;
	font-family: 'Roboto', '微软雅黑'; 
	font-weight: 700;
}

.footer {
	margin: 10px auto;
}
.title{ margin-bottom: 20px;text-align: center;}
.fname {
	color: #0084FF;
	font-weight: normal;
	font-family: 'Oxygen', 微软雅黑; 
}
</style>
<!-- 全局变量 -->
<script type="text/javascript">
var EasyEE={
		basePath:'<%=basePath%>'
	}
</script>

<!-- EasyUI JS -->
<script type="text/javascript" src="staticresources/easyui/jquery.min.js"></script>
<script type="text/javascript" src="staticresources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="staticresources/easyui/locale/easyui-lang-zh_CN.js"></script>

<!-- EasyUIEx -->
<link rel="stylesheet" type="text/css" href="staticresources/easyuiex/css/easyuiex.css">
<script type="text/javascript" src="staticresources/easyuiex/easy.easyuiex.min.js"></script>
<script type="text/javascript" src="staticresources/easyuiex/easy.easyuiex-validate.js"></script>
<!-- EasyUIEx的默认消息语言为中文，使用其他语言需要导入相应语言文件 -->
<script type="text/javascript" src="staticresources/easyuiex/lang/easy.easyuiex-lang-zh_CN.js"></script>
<%-- jquery Cookie plugin --%>
<script type="text/javascript" src="staticresources/easyee/jquery.cookie.js"></script>

<!-- 自定义页面相关JS -->
<script type="text/javascript" src="script/login.js"></script>

<!-- 登录消息提示JS -->
<c:if test="${!empty MSG}">

	<script type="text/javascript">
		$(function() {
			uiEx.alert("${MSG }", "info");
		})
	</script>
	<c:remove var="MSG" scope="session"/>
</c:if>
</head>

<body>
	<div
		style="text-align: center;overflow:auto;width:100%;height:100%;margin: 10px auto;">
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
									name="name" id="username" style="height:30px;width: 190px;"
									data-options="validType:[],required:true,prompt:'user name...'" value="demo"></input></td>
								<!-- 								<td><input class="easyui-textbox" type="text" name="uname" style="height:30px;width: 180px;"
									data-options="validType:['email','startChk[\'A\']'],required:true"></input></td> -->
							</tr>
							<tr>
								<td>密码:</td>
								<td><input class="easyui-textbox" type="password"
									name="password" style="height:30px;width: 190px;"
									data-options="required:true" value="111111"></input></td>
							</tr>
						</table>
					</form>
					<div style="text-align:center;padding:5px">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							id="loginBtn" iconCls="icon-man" style="padding: 3px 10px">登录</a> <a
							href="javascript:void(0)" iconCls="icon-clear"
							class="easyui-linkbutton" onclick="uiEx.clearForm('#loginForm')"style="padding: 3px 10px">重置</a>
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

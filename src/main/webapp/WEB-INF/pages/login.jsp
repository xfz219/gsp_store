<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="taglibs.jsp" %>
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
									name="name" id="username" style="height:30px;width: 190px;"
									data-options="validType:[],required:true,prompt:'user name...'" value=""/></td>
							</tr>
							<tr>
								<td>密码:</td>
								<td><input class="easyui-textbox" type="password"
									name="password" style="height:30px;width: 190px;"
									data-options="required:true" value=""/></td>
							</tr>
						</table>
					</form>
					<div style="text-align:center;padding:5px">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							id="loginBtn" >登 录</a>
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

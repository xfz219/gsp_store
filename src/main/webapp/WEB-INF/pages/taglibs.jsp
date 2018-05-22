<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="shortcut icon" type="image/x-icon" href="${ctx}/static/images/favicon.ico" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/style/main.css" />
<script type="text/javascript" src="${ctx}/static/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/easyui/extEasyUI.js"></script>
<script type="text/javascript" src="${ctx}/static/easyui/jquery.color.js"></script>
<script type="text/javascript" src="${ctx}/static/easyui/easyui-validator-ext.js"></script>

<!-- EasyUIEx -->
<link rel="stylesheet" type="text/css" href="${ctx}/static/easyuiex/css/easyuiex.css">
<script type="text/javascript" src="${ctx}/static/easyuiex/easy.easyuiex.min.js"></script>
<script type="text/javascript" src="${ctx}/static/easyuiex/easy.easyuiex-validate.js"></script>
<%-- jquery Cookie plugin --%>
<script type="text/javascript" src="${ctx}/static/easyee/jquery.cookie.js"></script>

<script type="text/javascript" >
	//全局的ajax访问，处理ajax清求时sesion超时
	$.ajaxSetup({
		contentType:"application/x-www-form-urlencoded;charset=utf-8",
		complete:function(XMLHttpRequest,textStatus){
			//通过XMLHttpRequest取得响应头，sessionstatus，
			var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); 
			if(sessionstatus=="timeout"){
			//如果超时就处理 ，指定要跳转的页面
				window.location.reload("/gsp-store/");
			}
			
			if(XMLHttpRequest.status==555){
				var location = XMLHttpRequest.getResponseHeader("Location");
				self.parent.window.location=location;
			}
		}
	});
</script>
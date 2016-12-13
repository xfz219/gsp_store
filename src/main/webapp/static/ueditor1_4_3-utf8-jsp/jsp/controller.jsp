<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	
	String rootPath = application.getRealPath( "/" );
	
 //	String saveRootPath="http://172.16.7.79:8093/puhui-lend/upload/puhui-lend/";
	//String saveRootPath=rootPath+"/static/";
	String saveRootPath="/upload/puhui-lend/";
	out.write( new ActionEnter( request,saveRootPath,rootPath ).exec() );
	
%>
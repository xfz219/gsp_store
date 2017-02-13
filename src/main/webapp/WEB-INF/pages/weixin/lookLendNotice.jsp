<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/static/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/static/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>
<link rel="stylesheet"  href="${ctx}/static/ueditor1_4_3-utf8-jsp/themes/default/css/ueditor.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看文章</title>
<style type="text/css">
	fieldset{
		width:1665px;
	}
	
	.one{
		text-align: right;
		height:30px;
		width:160px;
	}
	
	.two{
		text-align: left;
		height:30px;
		width:150px;
	}
	
	.prov,.city,.dist{
		width:150px;
	}
	.town{
		width:150px;
	}
	
	.number{
		width:150px;
	}
	
	.contractTable{
		float:left;
		margin:0px 8px;
	}
	
	.contractLabel{
		float: left;
		margin: 10px 20px;
	}
	
	select{
		height:22px;border: 1px solid #7F9DB9;
	}
	table td input{
		width: 151px;border: 1px solid #7F9DB9;
	}
	 #staffFormAdd .easyui-validatebox,.easyui-datebox,.easyui-numberbox{
		width: 151px; 
		editable: false; 
	} 
	 #staffFormAdd .easyui-combobox,.easyui-combobox{ 
		width: 151px; 
		editable: false; 
	 } 
	.combobox-item{
		height:12px;
	}
</style>
<body style="overflow:auto;">
<div>
<div id="lookdialog"></div>
		<form id="editNoticeForm" class="easyui-form" method="post" data-options="novalidate:true">
			<div title="查看公告" style="padding: 10px;">
		
				<fieldset>
					<legend>查看公告 &nbsp;&nbsp;&nbsp;</legend>
					<table>
						<tr>
						
							<td class="one">标题：</td>
							<td class="two">
								<input id="article" name="article" class="easyui-validatebox" placeholder="50个字以内" style="width: 590px;height: 26px" maxlength="50" readonly="readonly">
							</td>
							</tr>
							<tr style="height: 60px">
							<td class="one">作者：</td>
							<td class="two">
								<input id="authorName" name="authorName" class="easyui-validatebox" placeholder="50个字以内" style="width: 590px;height: 26px" maxlength="50" readonly="readonly">
							</td>
							</tr>
							<tr style="height: 60px">
							<td class="one">摘要：</td>
							<td class="two">
								<input id="synopsis" name="synopsis" class="easyui-validatebox" placeholder="50个字以内" style="width: 590px;height: 26px" maxlength="50" readonly="readonly">
							</td>
							</tr>
							<tr style="height:593px">
							<td class="one" >内容：</td>
							<td class="two">
								  <script type="text/plain" id="myEditor" style="width:1200px;height:400px;margin:30spx 4px;margin-left: ">${notice}</script>
							</td>
							</tr>
							</table>
							</fieldset>
							<div style="float:right;margin:10px">
				<a href="javascript:;" class="easyui-linkbutton" id="close">关闭</a>
			
			</div>
			</div>
			</form>
</div>
</body>

<script language="javascript">  
  
	$(document).ready(function() {
		
        var editor = new baidu.editor.ui.Editor({  
            textarea : 'notice'  
        });  
        editor.render("myEditor"); 
        editor.ready(function(){
       	 editor.setContent('${appWeixiinArticle.notice}');
       });
		$("#article").val('${appWeixiinArticle.article}');
	    $("#authorName").val("${appWeixiinArticle.authorName}");
	    $("#articleStatus").val("${appWeixiinArticle.articleStatus}");
	    $("#synopsis").val("${appWeixiinArticle.synopsis}");
		
	    //关闭
	    $('#close').click(function(){
	    	$.messager.confirm('提示信息',"确认关闭吗？",function(r){    
			    if (r){ 
			    	 parent.$("#tabs").tabs("close","查看文章");
			    }
			});
	    });
	});
</script>
    
</html>


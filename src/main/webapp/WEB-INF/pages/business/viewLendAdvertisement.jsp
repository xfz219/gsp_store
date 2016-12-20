<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看广告位</title>
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
<link rel="stylesheet" type="text/css" href="${ctx}/static/js/uploadify/uploadify.css">
<script type="text/javascript" src="${ctx}/static/js/uploadify/jquery.uploadify.min.js?v=<%=System.nanoTime()%>"></script>

<body style="overflow:auto;">
	<div id="lookdialog" style="text-align:center;padding-top:20px;"></div>
		<form id="addAdForm" class="easyui-form" enctype="multipart/form-data" method="post" >
			<div title="添加广告" style="padding: 10px;">
				<fieldset>
					<legend>添加公告 &nbsp;&nbsp;&nbsp;<font color="red">*必填</font></legend>
					<table id="adTable" name="adTable">
						<tr>
							<td class="one">广告位名称：</td>
							<td class="two">
								<input type="text" name="name" id="name" value="${lendAdvertisement.name }" placeholder="20字以内" disabled="disabled" style="width: 200px" class="easyui-validatebox" data-options="required:true">
							</td>
						</tr>
						<tr>
							<td class="one">广告跳转链接：</td>
							<td class="two">
								<input type="text" name="url" id="url" value="${lendAdvertisement.url }" style="width:500px" disabled="disabled" class="easyui-validatebox">
							</td>
						</tr>
						<tr>
							<td class="one">客户身份：</td>
							<td class="two">
								<select id="customerIdentity" name="customerIdentity" class="easyui-combobox" disabled="disabled">
									<option value="0" >薪类客户</option>
									<option value="1" >商类客户</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="one">是否启用：</td>
							<td class="two">
								<select id="enabled" name="enabled" class="easyui-combobox" disabled="disabled">
									<option value="0">否</option>
									<option value="1">是</option>
								</select>
							</td>
						</tr>
						<!-- <tr id="adFile">
							<td class="one">上传图片:</td>
							<td><input type="file" name="file" multiple="false" disabled="disabled" onchange="previewImage(this)" ></td>
							链接:<input type="text"  name="url" style="width:600px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" id="addRow" value="增加一行" >
						</tr> -->
					</table>

				</fieldset>
				<div style="float:right;margin:10px">
					<a flag="Button" class="easyui-linkbutton" onclick="closeTab();">关闭</a>
				</div>
			</div>
		</form>
	</body>

	<script type="text/javascript">
		
		$(function(){
			$('#customerIdentity').combobox('select','${lendAdvertisement.customerIdentity}');
			$('#enabled').combobox('select','${lendAdvertisement.enabled}');
		});

		//图片上传预览    IE是用了滤镜。
        function previewImage(file)
        {
          var MAXWIDTH  = 260; 
          var MAXHEIGHT = 180;
          var div = document.getElementById('preview');
          if (file.files && file.files[0])
          {
              div.innerHTML ='<img id=imghead>';
              var img = document.getElementById('imghead');
              img.onload = function(){
                var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                img.width  =  rect.width;
                img.height =  rect.height;
//                 img.style.marginLeft = rect.left+'px';
                img.style.marginTop = rect.top+'px';
              }
              var reader = new FileReader();
              reader.onload = function(evt){img.src = evt.target.result;}
              reader.readAsDataURL(file.files[0]);
          }
          else //兼容IE
          {
            var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
            file.select();
            var src = document.selection.createRange().text;
            div.innerHTML = '<img id=imghead>';
            var img = document.getElementById('imghead');
            img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
            div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
          }
        }
        function clacImgZoomParam( maxWidth, maxHeight, width, height ){
            var param = {top:0, left:0, width:width, height:height};
            if( width>maxWidth || height>maxHeight )
            {
                rateWidth = width / maxWidth;
                rateHeight = height / maxHeight;
                 
                if( rateWidth > rateHeight )
                {
                    param.width =  maxWidth;
                    param.height = Math.round(height / rateWidth);
                }else
                {
                    param.width = Math.round(width / rateHeight);
                    param.height = maxHeight;
                }
            }
             
            param.left = Math.round((maxWidth - param.width) / 2);
            param.top = Math.round((maxHeight - param.height) / 2);
            return param;
        }
		
      //关闭当前选项卡
       function closeTab(){
       	parent.$("#tabs").tabs("close","查看广告位");
       }
	</script>

</html>


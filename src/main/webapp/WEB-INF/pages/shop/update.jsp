<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/static/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/static/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>
<link rel="stylesheet"  href="${ctx}/static/ueditor1_4_3-utf8-jsp/themes/default/css/ueditor.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改门店</title>
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
			<div title="修改" style="padding: 10px;">
		
				<fieldset>
					<legend>修改信息 &nbsp;&nbsp;&nbsp;</legend>
					<table>
						<tr>
							<input type="hidden" id="id" name="id"/>
							<td class="one">父ID：</td>
							<td class="two">
								<input id="parentId" name="parentId" class="easyui-validatebox" style="width: 590px;height: 26px" maxlength="50">
							</td>
							</tr>
							<tr style="height: 50px">
							<td class="one">门店CODE：</td>
							<td class="two">
								<input id="shopCode" name="shopCode" class="easyui-validatebox" style="width: 590px;height: 26px" maxlength="50">
							</td>
							</tr>
							<tr style="height: 50px">
								<td class="one">门店NAME：</td>
								<td class="two">
									<input id="shopName" name="shopName" class="easyui-validatebox" style="width: 590px;height: 26px" maxlength="50">
								</td>
							</tr>
							<tr style="height: 50px">
								<td class="one">门店手机号：</td>
								<td class="two">
									<input id="shopMobile" name="shopMobile" class="easyui-validatebox" style="width: 590px;height: 26px" maxlength="50">
								</td>
							</tr>
							<tr style="height: 50px">
								<td class="one">门店地址：</td>
								<td class="two">
									<input id="shopAddress" name="shopAddress" class="easyui-validatebox" style="width: 590px;height: 26px" maxlength="50">
								</td>
							</tr>
							<tr style="height: 50px">
								<td class="one">经度：</td>
								<td class="two">
									<input id="longitude" name="longitude" class="easyui-validatebox" style="width: 590px;height: 26px" maxlength="50">
								</td>
							</tr>
							<tr style="height: 50px">
								<td class="one">纬度：</td>
								<td class="two">
									<input id="latitude" name="latitude" class="easyui-validatebox" style="width: 590px;height: 26px" maxlength="50">
								</td>
							</tr>
							</table>
							</fieldset>
							<div style="float:right;margin:10px">
				<a href="javascript:;" class="easyui-linkbutton" id="add">保存</a>
								&nbsp;&nbsp;
				<a href="javascript:;" class="easyui-linkbutton" id="close">关闭</a>

			</div>
			</div>
			</form>
</div>
</body>

<script language="javascript">  
  
	$(document).ready(function() {
	    $("#parentId").val("${appLendShop.parentId}");
	    $("#shopCode").val("${appLendShop.shopCode}");
	    $("#shopName").val("${appLendShop.shopName}");
	    $("#shopMobile").val("${appLendShop.shopMobile}");
	    $("#shopAddress").val("${appLendShop.shopAddress}");
	    $("#longitude").val("${appLendShop.longitude}");
	    $("#latitude").val("${appLendShop.latitude}");
	    $("#enabled").val("${appLendShop.enabled}");
	    $("#id").val("${appLendShop.id}");



        //添加
        $('#add').click(function(){
            $.messager.confirm('提示信息','确认保存吗?',function(r){
                if(r){
                    $('#editNoticeForm').form('submit',{
                        url:'${ctx}/shop/updateAppLendShop',
                        success:function(data){
                            data=eval("(" + data+ ")");
                            if(data.status=='success'){
                                $.messager.alert('提示信息',data.result,'info',function(){
								parent.$("#tabs").tabs("select","修改门店");
								parent.$("#tabs").tabs("close","修改门店");
                                });
                            }else{
                                $.messager.alert('提示信息',data.result);
                            }
                        }
                    });
                }
            });
        });

	    //关闭
	    $('#close').click(function(){
	    	$.messager.confirm('提示信息',"确认关闭吗？",function(r){
			    if (r){
			    	 parent.$("#tabs").tabs("close","修改门店");
			    }
			});

	    });
	});
</script>
    
</html>


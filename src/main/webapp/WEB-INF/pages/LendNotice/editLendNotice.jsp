<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/static/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/static/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>
<link rel="stylesheet"  href="${ctx}/static/ueditor1_4_3-utf8-jsp/themes/default/css/ueditor.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑公告</title>
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
<div id="lookdialog" style="text-align:center;padding-top:20px;"></div>
		<form id="editNoticeForm" class="easyui-form" method="post" data-options="novalidate:true">
		<input type="hidden" id="id" name="id"/>
		<input type="hidden" id="noticeStatus" name="noticeStatus"/>
		<input type="hidden" id="createTime" name="createTime"/>
			<div title="" style="padding: 10px;">
			
				<fieldset>
					<legend>编辑公告 &nbsp;&nbsp;&nbsp;<font color="red">*必填</font></legend>
					<table>
						<tr>
						
							<td class="one">标题：</td>
							<td class="two">
								<input id="noticeTitle" name="noticeTitle" class="easyui-validatebox" placeholder="50个字以内" style="width: 590px;height: 26px" maxlength="50" data-options="required:true">
							</td>
							</tr>
							<tr style="height: 183px">
							<td class="one">摘要：</td>
							<td class="two">
								<textarea id="noticeAbstract" name="noticeAbstract" class="easyui-validatebox" style="width: 590px;height: 126px"  data-options="required:true"></textarea>
							</td>
							</tr>
							<tr style="height: 23px">
							<td class="one">通知部门：</td>
							<td class="two">
								<select id="noticeDepartment" name="noticeDepartment" class="easyui-combotree" style="width:800px;" data-options="multiple:true,required:true,panelHeight:120,editable:false"></select>
						</select>
							</td>
							</tr>
							<tr style="height:413px">
							<td class="one" >内容：</td>
							<td class="two">
								  <script type="text/plain" id="myEditor" style="width:1200px;height:400px;margin:30spx 4px;margin-left: ">${noticeContent}</script>  
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
            var editor = new baidu.editor.ui.Editor({  
                textarea : 'noticeContent'  
            });  
            editor.render("myEditor"); 
            editor.ready(function(){
           	 editor.setContent('${appLendNotice.noticeContent}');
           });
            $("#id").val("${appLendNotice.id}");
            $("#noticeStatus").val("${appLendNotice.noticeStatus}");
            $("#noticeTitle").val("${appLendNotice.noticeTitle}");
            $("#noticeAbstract").html("${appLendNotice.noticeAbstract}");
            var arr = '${appLendNotice.noticeDepartment}'.split(',');
            $('#createTime').val("${appLendNotice.createTime}");
           
            //添加
            $('#add').click(function(){
            	$.messager.confirm('提示信息','确认保存吗?',function(r){  
            		if(r){
            	$('#editNoticeForm').form('submit',{
            		 url:'${ctx}/AppLendNotice/editLendNotice',
            		 onSubmit: function(){ 
            			 if($('#editNoticeForm').form('validate')){
            				 if(!editor.hasContents()){
            					 $.messager.alert('提示信息','请编辑内容！');
                     			return false; 
            				 }else{
            					 return true;
            				 }
            			 }else{
            				 return false;
            			 }
            		 }, 
            		  success:function(data){  
            			  data=eval("(" + data+ ")");
            			  if(data.status=='success'){
            				  $.messager.alert('提示信息',data.result,'info',function(){
  				        		var tab = parent.$("#tabs").tabs("getTab","系统公告");
  					        	var url = $(tab.panel('options').content).attr('src');
  					        	parent.$('#tabs').tabs('update', {
  					        	      tab : tab,
  					        	      options : {
  					        	      	src : url
  					        	      }
  					        	});
  					        	parent.$("#tabs").tabs("select","系统公告");
  					        	parent.$("#tabs").tabs("close","编辑公告");
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
        		    	 parent.$("#tabs").tabs("close","编辑公告");
        		    }
        		    });
            	
            });
           
            
          //产生一个在下拉框中的树，也就是组合树
	   		$('#noticeDepartment').combotree({
					url: '${ctx}/appUtil/getOrgTree',
	   		        idFiled:'id',
	   		        textFiled:'name', 
	   		        parentField : 'pid',
	   		        checkbox:true,
	   		        lines : true,
	   		        panelHeight : '300',
	   		        onLoadSuccess: function (node, data) {
	   		           /*  $('#noticeDepartment').combotree('tree').tree("collapseAll");  */
	   		        	for (var i=0;i<arr.length ;i++ ){
	                           node=$('#noticeDepartment').combotree('tree').tree('find',arr[i]);
	                           $('#noticeDepartment').combotree('tree').tree('check',node.target);
	                           $('#noticeDepartment').combotree('tree').tree('expandAll', node.target);
	                  		}
	   	           }
	   		});
           
        });  
  
    </script>  
</html>


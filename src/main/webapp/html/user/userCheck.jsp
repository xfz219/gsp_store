<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
    #usercheckForm table td{
     padding:10px 5px 2px 2px;
    }
</style>
 <div id ="usercheckDialog">
        <form id="usercheckForm" method="post">
                          <table>
                               		<input id="checkId" type="hidden" name="id"/>
                              <tr>
							   	<td width="80" height="30px"style="text-align: right" >客户姓名：</td>
							   	<td width="120">
							  <input id="checkUserName" type="text" readonly="readonly" name="userName" class="easyui-validatebox"  >
							    <td width="80" style="text-align: right">身份证号：</td>
							   <td width="120">
									<input id="checkUserIdNo" type="text" readonly="readonly" name="userIdNo" class="easyui-validatebox" >
                              </tr>
                              <tr>

								<td width="80" style="text-align: right">借款金额：</td>
							   	<td width="120">
									<input readonly="readonly" id="checkUserAmount" type="text" name="userAmount" />
								<td width="80" style="text-align: right">电话号码：</td>
							   	 <td width="120">
									<input id="checkUserMobile" type="text" name="mobile" class="easyui-validatebox" >
                              </tr>
							  <tr>
								 <td width="80" style="text-align: right">城市：</td>
							   	 <td width="120">
									<input readonly="readonly" id="checkUserCity" type="text" name="userCity"  class="easyui-validatebox" >
								<td width="80" style="text-align: right">省份：</td>
							   	 <td width="120">
									<input readonly="readonly" id="checkUserProvince" type="text" name="userProvince"  class="easyui-validatebox" >
                              </tr>

                              <tr id="defect_data1" style="display:none">
							   		<td width="80" style="text-align: right">是否提前结清：</td>
							  		<td width="120">
										<input readonly="readonly" id="checkUserIsSettle" type="text" name="isSettle" class="easyui-validatebox"  >
									<td width="80" style="text-align:right">进件产品：</td>
							   		<td width="120" >
									<input  readonly="readonly"id="checkUserProductName" type="text" name="productName" class="easyui-validatebox" >
							  </tr>
							  <tr id="defect_data2" style="display:none">
									<td width="80" style="text-align: right">结清日期：</td>
							   		<td width="120">
									<input readonly="readonly" id="checkUserSettleTime" type="text" name="settleTime"  class="easyui-datetimebox" >
							  </tr>
                           </table>
        </form>
    </div>
    
    
    
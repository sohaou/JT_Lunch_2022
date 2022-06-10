<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/adminHeader.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/admin/franchise.css?ver=1"> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/franchise.js?ver=1" >
<!--jQuery dependency, any stable version of jQuery-->   
    <script src="https://unpkg.com/jquery@2.2.4/dist/jquery.js"></script>     
<!--jQueryUI version 1.11.4 -->
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" />
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script> 
<!--ParamQuery Grid css files-->
    <link rel="stylesheet" href="/resources/grid/pqgrid.min.css" />    
    <link rel="stylesheet" href="/resources/grid/pqgrid.dev.css" />   
    <!--add pqgrid.ui.css for jQueryUI theme support-->
    <link rel="stylesheet" href="/resources/grid/pqgrid.ui.min.css" /> 
    <link rel="stylesheet" href="/resources/grid/pqgrid.ui.dev.css" /> 
    <!--ParamQuery Grid custom theme e.g., office, bootstrap, rosy, chocolate, etc (optional)-->
    <!-- <link rel="stylesheet" href="/resources/grid/pqgrid.css" />  -->
<!--Include jsZip file (v2.5.0) before pqgrid js file to support xlsx and zip export (optional)-->
    <!-- <script type="text/javascript" src="/resources/grid/jsZip.js" ></script>     -->
<!--ParamQuery Grid js files-->
    <script type="text/javascript" src="/resources/grid/pqgrid.min.js" ></script>
    <script type="text/javascript" src="/resources/grid/pqgrid.dev.js" ></script>        
    <!--ParamQuery Grid localization file (necessary since v5.2.0)-->
    <script src="/resources/grid/localize/pq-localize-de.js"></script>
    <script src="/resources/grid/localize/pq-localize-en.js"></script>  
	<script src="/resources/grid/jsZip-2.5.0/jszip.min.js"></script>  
	<script src="/resources/grid/jszip-utils-0.0.2/jszip-utils.min.js"></script>  
	<script src="/resources/FileSaver/src/FileSaver.js"></script>  
<!--Include pqTouch file to provide support for touch devices (optional)-->
    <!-- <script type="text/javascript" src="/resources/grid/pqtouch.min.js" ></script> -->    
<!--Include jquery.resize to support auto height of detail views in hierarchy (optional)-->
    <!-- <script type="text/javascript" src="/resources/grid/jquery.resize.js" ></script> -->   

<meta charset="utf-8">
<main>
	<div class="container-fluid px-4">
		<h1 class="mt-4">체인점 관리</h1>
		<ol class="breadcrumb mb-4">
               <li class="breadcrumb-item">관리자</li>
           	<li class="breadcrumb-item active">체인점 목록</li>
		</ol>
		<div>
			<div class="headDiv">
				<table>
					<tr>
						<form name="searchForm" method="post" role="form" onsubmit="return false;">
						<td>
							<select class="select" name="searchOption" id="searchOption">
								<option value="">--검색조건--</option>
								<option value="ID">ID</option>
								<option value="SHOP_NAME">지점명</option>
								<option value="ADDRESS">주소</option>
								<option value="TEL">전화번호</option>
								<option value="DATA_URL">데이터전송주소</option>
							</select>
						</td>
						<td><input type="text" name="keyword" id="keyword" style="width: 140px;" onKeypress="javascript:if(event.keyCode==13){check_value()}"></td>
						<td><input type="button" value="검 색" id="submitBtn" onclick="check_value()"/></td> 
						<td><input type="button" value="초 기 화" id="resetBtn" onclick="search_reset()"/></td> 
						</form>
					</tr>	
				</table>
			</div>
			<div class="headDiv" id="insertDiv">
				<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="FranchiseInsertModal" onclick="InsertModalShow()">지점 등록</button>	
				<button type="button" class="btn btn-secondary" style="width: 100px;" onclick="excelDownLoad('franchise')"> Excel </button>
			</div>	
		</div>
		<div id="grid_json"></div>
		<div id="rpp_div">
			<form name="rppForm" method="post" role="form" onsubmit="return false;">
				<td>
					<select class="select" name="rpp" id="rpp" onChange="rppChange(this.value)">
						<option value="10">10개</option>
						<option value="20">20개</option>
						<option value="50">50개</option>
						<option value="100">100개</option>
					</select>
				</td>
			</form>
		</div>
		<div id="page"></div>
	</div>
	
	<!-- 지점 등록 Modal -->
	<div class="modal fade" id="FranchiseInsertModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="container-fluid px-4">
			<div class="modal-dialog" role="document">
			    <div class="modal-content">
			    	<div class="modal-header">
			        	<h4 class="modal-title" id="myModalLabel">지점 등록</h4>
			      	</div>
					<div class="modal-body">
						<form id="FranchiseInsertForm" name="FranchiseInsertForm" style="display: inline-flex; width: 100%;">
				           <table id="joint" style="width:100%;">
					            <tr>
					            	<td colspan="2" style="font-size:12px; text-align:right;"><p style="margin-right:50px;"><a style="color:red; font-weight:bold;">*</a>은 필수 입력 정보입니다.</p></td>
					            </tr>
				            	<tr>
				            		<td id="title">분류</td>
				            		<td><input type="radio" name="TYPE" value="0" checked>일반 <input type="radio" name="TYPE" value="1" style="margin-left:60px;">고급</td>
				            	</tr>
				               	<tr>
				                   <td id="title">ID <a style="color:red; font-weight:bold;">*</a></td>
				                   <td>
										<!-- <div id="FranchiseID"></div> -->
										<input type="text" name="ID" id="ID" maxlength="20" style="width: 205px;" onKeyup="this.value=this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g,'');" onblur="checkIDreset()">
										<input type="button" value="중복확인" id="duplCheck" onclick="checkID()">  
										<input type="hidden" name="IDCheck" id="IDCheck" value="0"><br>
										<div id="idCheckDiv" style="display: none;">사용 가능한 아이디입니다</div>
				                   </td>
				              	</tr>
				                       
				               <tr>
				                   <td id="title">PW <a style="color:red; font-weight:bold;">*</a></td>
				                   <td>
				                       <input type="password" name="PW" id="PW" maxlength="15" style="width: 275px;" onblur="checkPW()">
				                       <p id="pwAlert" style="color: red;">8~16자 영문 대 소문자, 숫자, 특수문자 중 2종류 포함.<br>(단, 특수문자는 !@#$%^&* 만 가능)</p>
				                       <input type="hidden" name="PWValue" id="PWValue" value="0"><br>
				                   </td>
				               </tr>
				               
				               <tr>
				                   <td id="title">PW 확인 <a style="color:red; font-weight:bold;">*</a></td>
				                   <td>
				                       <input type="password" name="PWCheck" id="PWCheck" maxlength="15" style="width: 275px;" onblur="pwSameCheck()">
				                       <p id="pwCheck" style="color: red;"></p>
				                        <input type="hidden" name="PWCheckValue" id="PWCheckValue" value="0"><br>
				                   </td>
				               </tr>
				                   
				               <tr>
				                   <td id="title">지점명 <a style="color:red; font-weight:bold;">*</a></td>
				                   <td>
				                       <input type="text" name="SHOP_NAME" id="SHOP_NAME" maxlength="50" style="width: 275px;">
				                   </td>
				               </tr>
				               <tr>
				                   <td id="title">전화번호 <a style="color:red; font-weight:bold;">*</a></td>
				                   <td>
				                   010 -
				                       <input type="text" name="phone1" id="phone1" maxlength="4" size="3" style="width: 105px;" onKeyup="this.value=this.value.replace(/[^-0-9]/g,'');"> -
				                       <input type="text" name="phone2" id="phone2" maxlength="4" size="3" style="width: 105px;" onKeyup="this.value=this.value.replace(/[^-0-9]/g,'');">
				                   </td>
				               </tr>
				               <tr> 
				               	<td id="title">주소 <a style="color:red; font-weight:bold;">*</a></td> 
				               	<td> 
				               		<input type="text" name="ZIP_CODE" id="ZIP_CODE" placeholder="우편번호" style="width: 170px; margin-bottom:5px;" readonly>
									<input type="button" onclick="inputAddress()" value="우편번호" id="addressBtn" ><br>
									<input type="text" name="ADDRESS" id="ADDRESS" placeholder="도로명주소" style="width: 275px; margin-bottom:5px;"><br>
									<input type="text" name="DETAIL_ADDRESS" id="DETAIL_ADDRESS" placeholder="상세주소" style="width: 275px;">
				               	</td> 
				               </tr>
				               <tr>
				               	<td id="title">데이터 전송 주소 <a style="color:red; font-weight:bold;">*</a></td>
				               	<td>
				               		<input type="text" name="DATA_URL" style="width: 170px;" placeholder="(ex)192.111.0.123" onKeyup="this.value=this.value.replace(/[^-\.0-9]/g,'');">
				               		:
				               		<input type="text" name="PORT_NUM" style="width: 80px;" placeholder="(ex)8080" onKeyup="this.value=this.value.replace(/[^-0-9]/g,'');">
				               	</td>
				               </tr>
				               <tr>
				               	<td colspan="2" style="text-align:center;">
				               		<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
				               		<input type="button" id="submit" value="등 록"/>
				               		<input type="button" id="cancel" value="취 소" data-bs-dismiss="modal" onclick="form_reset('FranchiseInsertModal', 'FranchiseInsertForm')"/>
				               		<!-- <button type="button" id="cancel" class="btn btn-secondary" data-bs-dismiss="modal">취소</button> -->
				               	</td>
				               </tr>
				           </table>
				           <br>
				       </form>
					</div>
			    </div>
			</div>
		</div>
	</div>
	<!-- 지점 상세보기 Modal -->
	<div class="modal fade" id="FranchiseDetailModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="container-fluid px-4">
			<div class="modal-dialog" role="document">
			    <div class="modal-content">
			    	<div class="modal-header">
			        	<h4 class="modal-title" id="myModalLabel">지점 상세보기 / 수정</h4>
			      	</div>
					<div class="modal-body">
						<form id="FranchiseUpdateForm" name="FranchiseUpdateForm" style="display: inline-flex; width: 100%;">
				           <table id="joint" style="width:100%;">
					            <tr>
					            	<td colspan="2" style="font-size:12px;"><p style=" text-align:right; margin-right:50px;"><a style="color:red; font-weight:bold;">*</a>은 필수 입력 정보입니다.</p></td>
					            </tr>
				            	<tr>
				            		<td id="title">분류</td>
				            		<td><input type="radio" name="TYPE" value="0">일반 <input type="radio" name="TYPE" value="1" style="margin-left:60px;">고급</td>
				            	</tr>
				               <tr>
				                   <td id="title">ID</td>
				                   <td>
										<div id="UFranchiseID"></div>
										<input type="hidden" name="ID" id="UID">
				                   </td>
				               </tr>
				                       
				               <tr>
				                   <td id="title">PW <a style="color:red; font-weight:bold;">*</a></td>
				                   <td>
				                       <input type="password" name="PW" id="UPW" maxlength="15" style="width: 275px;" onblur="checkPW('U')">
				                       <p id="UpwAlert" style="color: red;"></p>
				                       <input type="hidden" name="PWValue" id="UPWValue" value="1"><br>
				                   </td>
				               </tr>
				               
				               <tr>
				                   <td id="title">PW 확인 <a style="color:red; font-weight:bold;">*</a></td>
				                   <td>
				                       <input type="password" name="PWCheck" id="UPWCheck" maxlength="15" style="width: 275px;" onblur="pwSameCheck('U')">
				                       <p id="UpwCheck" style="color: red;"></p>
				                        <input type="hidden" name="PWCheckValue" id="UPWCheckValue" value="1"><br>
				                   </td>
				               </tr>
				                   
				               <tr>
				                   <td id="title">지점명 <a style="color:red; font-weight:bold;">*</a></td>
				                   <td>
				                       <input type="text" name="SHOP_NAME" id="USHOP_NAME" maxlength="50" style="width: 275px;">
				                   </td>
				               </tr>
				               <tr>
				                   <td id="title">전화번호 <a style="color:red; font-weight:bold;">*</a></td>
				                   <td>
				                   010 -
				                       <input type="text" name="phone1" id="Uphone1" maxlength="4" size="3" style="width: 105px;" onKeyup="this.value=this.value.replace(/[^-0-9]/g,'');"> -
				                       <input type="text" name="phone2" id="Uphone2" maxlength="4" size="3" style="width: 105px;" onKeyup="this.value=this.value.replace(/[^-0-9]/g,'');">
				                   </td>
				               </tr>
				               <tr> 
				               	<td id="title">주소 <a style="color:red; font-weight:bold;">*</a></td> 
				               	<td> 
				               		<input type="text" name="ZIP_CODE" id="UZIP_CODE" placeholder="우편번호" style="width: 170px; margin-bottom:5px;" readonly>
									<input type="button" onclick="inputAddress()" value="우편번호" id="addressBtn" ><br>
									<input type="text" name="ADDRESS" id="UADDRESS" placeholder="도로명주소" style="width: 275px; margin-bottom:5px;"><br>
									<input type="text" name="DETAIL_ADDRESS" id="UDETAIL_ADDRESS" placeholder="상세주소" style="width: 275px;">
				               	</td> 
				               </tr>
				               <tr>
				               	<td id="title">데이터 전송 주소 <a style="color:red; font-weight:bold;">*</a></td>
				               	<td>
				               		<input type="text" name="DATA_URL" id="UDATA_URL"style="width: 170px;" placeholder="(ex)192.111.0.123" onKeyup="this.value=this.value.replace(/[^-\.0-9]/g,'');">
				               		:
				               		<input type="text" name="PORT_NUM" id="UPORT_NUM" style="width: 80px;" placeholder="(ex)8080" onKeyup="this.value=this.value.replace(/[^-0-9]/g,'');">
				               	</td>
				               </tr>
				               <tr>
				               	<td colspan="2" style="text-align:center;">
				               		<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
				               		<input type="button" id="deleteBtn" value="삭 제">
				               		<input type="button" id="Usubmit" value="수 정"/>
				               		<button type="button" id="cancel" data-bs-dismiss="modal">닫기</button>
				               		<!-- <input type="button" id="cancel" value="닫 기" onclick="form_reset('FranchiseDetailModal', 'FranchiseUpdateForm')"/> -->
				               	</td>
				               </tr>
				           </table>
				           <br>
				       </form>
					</div>
				</div>
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/layout/adminFooter.jspf" %>
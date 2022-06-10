<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/adminHeader.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/adminNotice.js?ver=2" ></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/admin/notice.css?ver=2">
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
<!--ParamQuery Grid js files-->
<script type="text/javascript" src="/resources/grid/pqgrid.min.js" ></script>
<script type="text/javascript" src="/resources/grid/pqgrid.dev.js" ></script>        
<!--ParamQuery Grid localization file (necessary since v5.2.0)-->
<script src="/resources/grid/localize/pq-localize-de.js"></script>
<script src="/resources/grid/localize/pq-localize-en.js"></script>  
<script src="/resources/grid/jsZip-2.5.0/jszip.min.js"></script>  
<script src="/resources/grid/jszip-utils-0.0.2/jszip-utils.min.js"></script>  
<script src="/resources/FileSaver/src/FileSaver.js"></script>  
<style>
#page a{
	selector-dummy: expression(this.hideFocus=true) !important;
    text-decoration: none !important;
    color: #000;
    outline: 0 !important;
    transition: all 0.3s ease;
}
</style>
<main>
	<div class="container-fluid px-4">
		<h1 class="mt-4" onClick="location.href='/admin/notice'">공지사항</h1>
		<ol class="breadcrumb mb-4">
               <li class="breadcrumb-item">관리자</li>
           	<li class="breadcrumb-item active">공지사항</li>
		</ol>
		<!-- <input style="width: 100px; float:right;" class="btn btn-primary" type="button" value="등  록" onclick="InsertModalShow();"/>
		<br><br> -->
		<div>
			<div class="headDiv" id="periodDiv">
				<table>
					<tr>
						<form name="searchForm" method="post" role="form" onsubmit="return false;">
						<td>
							<select class="select" name="searchOption" id="searchOption" <!-- onChange="OptionChange(this.value)" -->>
								<option value="">--검색조건--</option>
								<option value="TITLE">제목</option>
								<option value="CONTENT">내용</option>
								<option value="WRITER">작성자</option>
								<!-- <option value="DATE">기간</option> -->
							</select>
						</td>
						<td id="keywordtd"><input type="text" name="keyword" id="keyword" style="width: 140px;" onKeypress="javascript:if(event.keyCode==13){check_value()}"></td>
						<td id="datetd">&nbsp;&nbsp; <font style="font-weight: bold;">* 기&nbsp;&nbsp;간 :</font> <input type="text" name="startDate" id="stDate" style="width: 80px;"> ~ <input type="text" name="endDate" id="endDate" style="width: 80px;"></td>
						<td><input type="button" value="검 색" id="submitBtn" onclick="check_value()"/></td> 
						<td><input type="button" value="초 기 화" id="resetBtn" onclick="search_reset()"/></td> 
						</form>
					</tr>	
				</table>
			</div>
			<div class="headDiv" id="insertDiv">
				<button type="button" class="btn btn-primary" style="width: 100px;" data-bs-toggle="modal" data-bs-target="NoticeInsertModal" onclick="InsertModalShow()"> 등&nbsp;&nbsp;&nbsp;록 </button>
				<button type="button" class="btn btn-secondary" style="width: 100px;" onclick="excelDownLoad('notice')"> Excel </button>
				<!-- location.href='/excel?type=notice' -->
				<!-- <input style="width: 100px; float:right;" class="btn btn-primary" type="button" value="등  록" onclick="InsertModalShow();"/> -->
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
	<!-- 공지사항 등록 Modal -->
	<div class="modal fade" id="NoticeInsertModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="container-fluid px-4">
			<div class="modal-dialog" role="document">
			    <div class="modal-content">
			    	<div class="modal-header">
			        	<h4 class="modal-title" id="myModalLabel">공지사항 등록</h4>
			      	</div>
					<div class="modal-body">
						<form id="noticeInsertForm" name="noticeInsertForm" style="display: inline-flex; width: 100%;">
							<table style="width:100%;">	
								<tr>
									<th style="width:27%; padding:5px;">* 제목 : </th>
									<td><input type="text" name="title" id="title" style="width:100%; border:1; overflow:visible; text-overflow:ellipsis; padding:5px;"/></td>
								</tr>
								<tr>
									<th style="width:27%; padding:5px;">* 내용 : </th>
									<td><textarea name="content" id="content" style="width:100%; border:1; overflow:visible; text-overflow:ellipsis;" rows="10"></textarea></td>
								</tr>
								<tr>
									<th style="width:27%; padding:5px;">* 팝업 설정 : </th>
									<td><input type="checkbox" name="pop_up" id="pop_up"> 예</td>
								</tr>
								<tr id="popUpPeriod">
									<th style="width:27%; padding:5px;">* 팝업 기간 : </th>
									<td><input type="text" id="startDt" class="startDt" name="pop_up_start_date"/> ~ <input type="text" id="endDt" class="endDt" name="pop_up_end_date"/></td>
								</tr>
								<tr>
									<th></th>
									<td style="height:60px;">
										<input class="btn btn-primary" type="button" style="width:90px;" value="등 록" onclick="check_input()"/>&nbsp;&nbsp;
										<input class="btn btn-secondary" type="button" style="width:90px;" value="취 소" data-bs-dismiss="modal" onclick="InsertModalHide();"/>
									</td>
								</tr>
							</table>
						</form>
					</div>
			    </div>
			</div>
		</div>
	</div>
	
	<!-- 공지사항 상세보기/수정 Modal -->
	<div class="modal fade" id="NoticeUpdateModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="container-fluid px-4">
			<div class="modal-dialog" role="document">
			    <div class="modal-content">
			    	<div class="modal-header">
			        	<h4 class="modal-title" id="myModalLabel">공지사항 상세보기 / 수정</h4>
			      	</div>
					<div class="modal-body">
						<form id="noticeUpdateForm" name="noticeUpdateForm" style="display: inline-flex; width: 100%;">
							<table style="width:100%;">	
								<input type="hidden" name="notice_id" id="Unotice_id">
								<tr>
									<th style="width:27%; padding:5px;">* 제목 : </th>
									<td><input type="text" name="title" id="Utitle" style="width:100%; border:1; overflow:visible; text-overflow:ellipsis; padding:5px;"/></td>
								</tr>
								<tr>
									<th style="width:27%; padding:5px;">* 내용 : </th>
									<td><textarea name="content" id="Ucontent" style="width:100%; border:1; overflow:visible; text-overflow:ellipsis;" rows="10"></textarea></td>
								</tr>
								<tr>
									<th style="width:27%; padding:5px;">* 팝업 설정 : </th>
									<td><input type="checkbox" name="pop_up" id="Upop_up"> 예</td>
								</tr>
								<tr id="UpopUpPeriod">
									<th style="width:27%; padding:5px;">* 팝업 기간 : </th>
									<td><input type="text" id="UstartDt" class="startDt" name="pop_up_start_date"/> ~ <input type="text" id="UendDt" class="endDt" name="pop_up_end_date"/></td>
								</tr>
								<tr>
									<th colspan="2" style="height:60px; text-align: center;">
										<input class="btn btn-danger" type="button" style="width:90px;" value="삭 제" onclick="notice_delete()">&nbsp;&nbsp;
										<input class="btn btn-primary" type="button" style="width:90px;" value="수 정" onclick="notice_update()"/>&nbsp;&nbsp;
										<input class="btn btn-secondary" type="button" style="width:90px;" value="취 소" data-bs-dismiss="modal" onclick="UpdateModalHide();"/>
									</th>
								</tr>
							</table>
						</form>
					</div>
			    </div>
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/layout/adminFooter.jspf" %>
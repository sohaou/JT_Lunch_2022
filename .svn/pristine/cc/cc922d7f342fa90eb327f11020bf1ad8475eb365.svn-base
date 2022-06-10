<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/layout/ownerHeader.jspf" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/owner/calender.css"> 

<script type="text/javascript">
	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");
	
	function printAteUser(ate_date){
		var str=ate_date;
		$.ajax({
			url: "<c:url value='/owner/printAteUser'/>",
			type: "POST",
			data : {ate_date:str},
			dataType : 'json',
			success : function(data){
				$('#ateUserResult').empty();
				if(data.result.length>0){
					for (i=0; i<data.result.length; i++ ) {
						str='<tr>'
						str += "<td class='table-center'>"+data.result[i].department+"</td>";
						if(data.result[i].team!=null){str+="<td class='table-center'>"+data.result[i].team+"</td>";}
						if(data.result[i].team==null){str+="<td class='table-center'>&nbsp;</td>";}
						str+="<td class='table-center'>"+data.result[i].name+"</td>";
						str+="<td class='table-center'>"+data.atedate+"</td>";
						str+="</tr>"
						$('#ateUserResult').append(str);
					}
				}
			},				
			error:function(request,status,error)
			{
			   alert("error");
			}
		});
		$("#ateUserListModal").modal('show');
	}
</script>
<style>
	#closeate{
	    background-color: white;
	    border: none;
	    color:black;
	    padding: 15px 0;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 15px;
	    margin: 4px;
	    cursor: pointer;
	}
	#today_button {
	    background-color: white;
	    border: none;
	    color:blue;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 15px;
	    font-weight:bold;
	    margin: 4px;
	    cursor: pointer;
	}
	#AteUserTable {
		width:100%;
		border-collapse: collapse;
		border-top: 3px solid #168;
    }  
    #AteUserTable th {
		color: #168;
		background: #f0f6f9;
		text-align: center;
    }
    #AteUserTable th, #AteUserTable td {
		padding: 10px;
		border: 1px solid #ddd;
    }
    #AteUserTable th:first-child, #AteUserTable td:first-child {
		border-left: 0;
    }
    #AteUserTable th:last-child, #AteUserTable td:last-child {
		border-right: 0;
    }
    #AteUserTable tr td{
		text-align: center;
		font-size:12px;
    }
</style>
<main>  
	<div class="container-fluid px-4">
		<h1 class="mt-4">식수 인원 확인</h1>
			<ol class="breadcrumb mb-4">
                <li class="breadcrumb-item">식당운영자</li>
            	<li class="breadcrumb-item active">월/일별 식수 인원 확인</li>
			</ol>
	<form name="calendarFrm" id="calendarFrm" action="" method="GET">
		<div class="calendar" >
		<!--날짜 네비게이션  -->
		<div class="navigation">
			<a class="before_after_year" href="/owner/ownerCalendar?year=${today_info.search_year-1}&month=${today_info.search_month-1}">
				&lt;&lt;
			<!-- 이전해 -->
			</a> 
			<a class="before_after_month" href="/owner/ownerCalendar?year=${today_info.before_year}&month=${today_info.before_month}">
				&lt;
			<!-- 이전달 -->
			</a> 
			<span class="this_month">
				&nbsp;${today_info.search_year}. 
				<c:if test="${today_info.search_month<10}">0</c:if>${today_info.search_month}
			</span>
			<a class="before_after_month" href="/owner/ownerCalendar?year=${today_info.after_year}&month=${today_info.after_month}">
			<!-- 다음달 -->
				&gt;
			</a> 
			<a class="before_after_year" href="/owner/ownerCalendar?year=${today_info.search_year+1}&month=${today_info.search_month-1}">
				<!-- 다음해 -->
				&gt;&gt;
			</a>
		</div>
	
		<div class="today_button_div" style="width:100%">
			<table style="width:100%;">
				<tr>
					<td style="width:70%; font-size:16px; font-weight:bold;"># 이번 달 전체 식사 횟수 : ${countate} 번</td>
					<td style="width:30%; text-align: right;"><input type="button" id="today_button" onclick="javascript:location.href='/owner/ownerCalendar'" value="TODAY"/></td>
				</tr>
			</table>
		</div>
		<table class="calendar_body">
			<thead>
				<tr bgcolor="#CECECE">
					<td class="day sun" >일</td>
					<td class="day" >월</td>
					<td class="day" >화</td>
					<td class="day" >수</td>
					<td class="day" >목</td>
					<td class="day" >금</td>
					<td class="day sat" >토</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<c:forEach var="dateList" items="${dateList}" varStatus="date_status">
						<c:choose>
							<c:when test="${dateList.value == 'today'}">
								<td class="today">
									<div class="date">
										${dateList.date}
									</div>
									<div>
									</div>
								</td>
	  						</c:when>
							<c:when test="${date_status.index % 7 == 6}">
								<td class="sat_day">
									<div class="sat">
										${dateList.date}
									</div>
									<div>
									</div>
								</td>
							</c:when>
							<c:when test="${date_status.index % 7 == 0}">
				</tr>
				<tr>
					<td class="sun_day">
						<div class="sun">
							${dateList.date}
						</div>
						<div>
						</div>
					</td>
							</c:when>
	 						<c:otherwise>
					<td class="normal_day">
						<div class="date">
							${dateList.date}
							<div id="countAteUser">
								<c:set var="count" value="0"/>
	                            <c:set var="link" value=""/>
	                            <c:forEach var="ateuserList" items="${dateList.ateuser}" varStatus="ateuser_status">
		                        	<c:if test="${ateuserList.name!=null}"> 
		                            	<c:set var="count" value="${count+1}"/>
		                                <c:set var="link" value="${ateuserList.ate_date}"/>
									</c:if>
	                           	</c:forEach>
	                            <c:if test="${count>0 }">
	                                <a href="#" onclick="printAteUser('${link}')"><c:out value="${count }명 "/></a>
								</c:if>
							</div>
						</div>
					</td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</tbody>
		</table>
		</div>
	</form>
	</div>
	<!-- 식수 인원 리스트 Modal --> 
	<div class="modal fade" id="ateUserListModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true"> 
		<div class="container-fluid px-4">
			<div class="modal-dialog"> 
				<div class="modal-content"> 
					<div class="modal-header"> 
						<h4 class="modal-title">식사자 명단</h4>
						<button id="closeate" onclick="$('#ateUserListModal').modal('hide');">X</button>
					</div> 
					<div class="modal-body"> 
						<table id="AteUserTable">
							<colgroup>
								<col width="40%" />
								<col width="35%" />
								<col width="25%" />
								<col width="5%" />
							</colgroup>
							<thead>
					        	<tr>
									<th class="table-center">부서</th>
					        		<th class="table-center">팀</th>
					        		<th class="table-center">이름</th>
					        		<th class="table-center">식사일</th>
					        	</tr>	
				       	 	</thead>
							<tbody id="ateUserResult">	
							</tbody>
						</table>
					</div> 
					<div class="modal-footer"> 
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
					</div> 
				</div> 
			</div> 
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/layout/adminFooter.jspf" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/layout/ownerHeader.jspf" %>
<%-- <meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}"> --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/owner/calender.css"> 
<style>
	.before_after_month, .before_after_year{
		text-decoration : none;
	}	
</style>
<script type="text/javascript">
/* var header = $("meta[name='_csrf_header']").attr("content");
var token = $("meta[name='_csrf']").attr("content"); */
var token = $("input[name='_csrf']").val(); 
var header = "X-CSRF-TOKEN";

	$(document).ready(function() {
		calendarLoad(null, null);
	});
	
	function calendarLoad(year, month) {
		 let str = "";
		 let str1 = "";
		 let str2 = "";
		 let count = 0;
		 let link = "";
	
		 $.ajax({
			url: "<c:url value='/owner/AteUserMonth'/>",
			type: "POST",
			data : {
				year : year,
				month : month
			},
			/* beforeSend: function (xhr) {
		    	xhr.setRequestHeader(header, token);
		           
			}, */
			beforeSend : function(xhr) { xhr.setRequestHeader(header, token); },
			dataType : 'json',
			success : function(data){
				$("#navigation").empty();
				
				let date = new Date(data.today_info.search_year, data.today_info.search_month);
				const before_year = date.getFullYear() - 1;
				const before_month = date.getMonth() - 1;
				const after_year = date.getFullYear() + 1;
				const after_month = date.getMonth() - 1;
				
				<!-- 이전해 --> 
				str+="<a class='before_after_year' onclick='calendarLoad(" + before_year + "," + before_month + ")'>";
				str+="&lt;&lt;</a>";
				<!-- 이전달 --> 
				str+="<a class='before_after_month' onclick='calendarLoad(" + data.today_info.before_year + "," + data.today_info.before_month + ")'>";
				str+="&lt;</a>"; 
				str+="<span class='this_month'>&nbsp;";
				<!-- 년도.월 (1~9월까지는 앞에 0을 붙여서 01~09로 표시-->
				str+=data.today_info.search_year + ".";
				if (data.today_info.search_month < 10){
					str+="0" + data.today_info.search_month + "</span>";
				} else{
					str+=data.today_info.search_month + "</span>";
				}
				<!-- 다음달 -->
				str+="<a class='before_after_month' onclick='calendarLoad(" + data.today_info.after_year + "," + data.today_info.after_month + ")'>";
				str+="&gt;</a>"; 
				<!-- 다음해 -->
				str+="<a class='before_after_year' onclick='calendarLoad(" + after_year + "," + after_month + ")'>";
				str+="&gt;&gt;</a>"
				
				$("#navigation").append(str);
				
				$("#total_ate").html("# 이번 달 전체 식사 횟수 : " + data.countate + "번");
				
				$("#calendar_body").empty();
				str1+="<tr>";

				const dateList = data.dateList;
				const ateUserCount = data.ateuser_count;
				
				for (let i = 0; i < dateList.length; i++){
					if (dateList[i].value == 'today'){
						str1+="<td class='today'>";
						str1+="<div class='date'>";
						str1+=dateList[i].date;
						str1+="</div>";
						str1+="<div class='countAteUser'>";
						if(ateUserCount[dateList[i].date] != null){
							let searchDate = data.today_info.before_year + "-" + data.today_info.search_month + "-" + dateList[i].date;
							str1 += "<a onclick=\"AteUserList(\'" + searchDate + "\')\">" + ateUserCount[dateList[i].date] + "</a>";
						}
						str1+="</div>";
						str1+="</td>";
					} else if (i % 7 == 6){
						str1+="<td class='sat_day'>";
						str1+="<div class='sat'>";
						str1+=dateList[i].date;
						str1+="</div>";
						str1+="</td>";
					} else if (i % 7 == 0){
						str1+="</tr>";
						str1+="<tr>"
						str1+="<td class='sun_day'>";
						str1+="<div class='sun'>";
						str1+=dateList[i].date;
						str1+="</div>";
						str1+="</td>";
					} else{
						str1+="<td class='normal_day'>";
						str1+="<div class='date'>";
						str1+=dateList[i].date;
						str1+="<div class='countAteUser'>";
						if(ateUserCount[dateList[i].date] != null){
							let searchDate = data.today_info.before_year + "-" + data.today_info.search_month + "-" + dateList[i].date;
							str1 += "<a onclick=\"AteUserList(\'" + searchDate + "\')\">" + ateUserCount[dateList[i].date] + "</a>";
						} 
						str1+="</div>";
						str1+="</div>";
						str1+="</td>";
					}
				}
				$("#calendar_body").append(str1);
				str = "";
				str1 = "";
			},				
			error:function(request,status,error){
				alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
				console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" +  "error:" + error);
			}
		});
	} 
	
	function AteUserList(ate_date){
		let str = ate_date;

		$.ajax({
			url: "<c:url value='/owner/AteUserList'/>",
			type: "POST",
			data : {'ate_date' : str},
			/* beforeSend: function (xhr) {
		    	xhr.setRequestHeader(header, token);
		           
			}, */
			beforeSend : function(xhr) { xhr.setRequestHeader(header, token); },
			success : function(data){
				$('#ateUserResult').empty();
				if (data.result.length > 0){
					for (let i = 0; i < data.result.length; i++ ) {
						str='<tr>'
						str+="<td class='table-center'>" + data.result[i].department + "</td>";
						if (data.result[i].team != null){
							str+="<td class='table-center'>" + data.result[i].team + "</td>";
						}
						if (data.result[i].team == null){
							str+="<td class='table-center'>&nbsp;</td>";
						}
						str+="<td class='table-center'>" + data.result[i].name + "</td>";
						str+="<td class='table-center'>" + data.result[i].ate_date + "</td>";
						str+="</tr>"
						$('#ateUserResult').append(str);
					}
				}
			},				
			error:function(request,status,error){
			   //alert("error");
			   alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
			}
		});
		$("#ateUserListModal").modal('show');
	}
</script>
<style>
	#closeAte{
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
	#ateUserTable {
		width:100%;
		border-collapse: collapse;
		border-top: 3px solid #168;
    }  
    #ateUserTable th {
		color: #168;
		background: #f0f6f9;
		text-align: center;
    }
    #ateUserTable th, #ateUserTable td {
		padding: 10px;
		border: 1px solid #ddd;
    }
    #ateUserTable th:first-child, #ateUserTable td:first-child {
		border-left: 0;
    }
    #ateUserTable th:last-child, #ateUserTable td:last-child {
		border-right: 0;
    }
    #ateUserTable tr td{
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
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<div class="calendar" >
		<!--날짜 네비게이션  -->
			<div class="navigation" id="navigation">
			<!-- 년, 월 이동 div-->
			</div>
			<div class="today_button_div" style="width:100%">
			<!-- 이번 달 전체 식사 횟수 출력 및 오늘 날짜 이동 버튼 출력 -->
				<table style="width:100%;">
					<tr>
						<td style="width:70%; font-size:16px; font-weight:bold;" id="total_ate"></td>
						<td style="width:30%; text-align: right;"><input type="button" id="today_button" onclick="javascript:location.href='/owner/ownerCalendar'" value="TODAY"/></td>
					</tr>
				</table>
			</div>
			<table class="calendar_body">
				<thead>
					<tr bgcolor="#CECECE">
						<td class="day sun">일</td>
						<td class="day">월</td>
						<td class="day">화</td>
						<td class="day">수</td>
						<td class="day">목</td>
						<td class="day">금</td>
						<td class="day sat">토</td>
					</tr>
				</thead>
				<tbody id="calendar_body">
				</tbody>
			</table>
		</div>
	</div>
	<!-- 식수 인원 리스트 Modal --> 
	<div class="modal fade" id="ateUserListModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true"> 
		<div class="container-fluid px-4">
			<div class="modal-dialog"> 
				<div class="modal-content"> 
					<div class="modal-header"> 
						<h4 class="modal-title">식사자 명단</h4>
						<button id="closeAte" onclick="$('#ateUserListModal').modal('hide');">X</button>
					</div> 
					<div class="modal-body"> 
						<table id="ateUserTable">
							<colgroup>
								<col width="35%"/>
								<col width="20%"/>
								<col width="20%"/>
								<col width="35%"/>
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
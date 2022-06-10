<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/layout/adminHeader.jspf" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/owner/calender.css?ver=2"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<style>
	.form-check-input{margin-right: 2px;}
	.MenuPlan{
		font-size: 12px;
		font-weight: normal;
		line-height: 0.2px;
	}
	.before_after_month, .before_after_year{
		text-decoration : none;
	}
	.date{
		background-color: #A6A6A6;
		padding: 5px;
		border-bottom: 1px solid white;
	}
	.no_date{
		background-color: #A6A6A6;
		padding: 10px;
		border-bottom: 1px solid white;
	}
	.normal_day{
		width: 20%;
	}
	.this_month{
		font-size: 26px;
		font-weight: bold;
	}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		calendarLoad(null,null);
	});
	
	var menu_data;
	var iden;
	var rice_count = 0;
	var soup_count = 0;
	var side_count = 0;
	
	let today = new Date(); 
	let selectYear = today.getFullYear();
	let selectMonth = today.getMonth() + 1;
	let date = today.getDate();
	
	function calendarLoad(year, month) {
		 let str = "";
		 let str1 = "";
		 let str2 = "";
		 let count = 0;
		 let link = "";


		 $.ajax({
			url: "<c:url value='/owner/menuPlanLoad'/>",
			type: "POST",
			data : {
				year : year,
				month : month
			},
			dataType : "json",
			success : function(data){
				$("#navigation").empty();
				
				let date = new Date(data.today_info.search_year, data.today_info.search_month);
				const before_year = date.getFullYear() - 1;
				const before_month = date.getMonth() - 1;
				const after_year = date.getFullYear() + 1;
				const after_month = date.getMonth() - 1;
				
				/* str+="<a class='before_after_year' onclick='calendarLoad(" + before_year + "," + before_month + ")'>";
				str+="&lt;&lt;</a>"; */
				<!-- 이전달 --> 
				str+="<a class='before_after_month' onclick='calendarLoad(" + data.today_info.before_year + "," + data.today_info.before_month + ")'>";
				str+="<i class='fas fa-caret-left' style='color:black; font-size:40px; margin-right: 70px;'></i></a>"; 
				str+="<span class='this_month'>&nbsp;";
				<!-- 년도.월 (1~9월까지는 앞에 0을 붙여서 01~09로 표시-->
				/* str+=data.today_info.search_year + "."; */
				if(data.today_info.search_month < 10){
					/* str+="0" + data.today_info.search_month + "</span>"; */
					str+="0" + data.today_info.search_month + "월 식단표</span>";
				} else{
					str+=data.today_info.search_month + "</span>";
				}
				<!-- 다음달 -->
				str+="<a class='before_after_month' onclick='calendarLoad(" + data.today_info.after_year + "," + data.today_info.after_month + ")'>";
				str+="<i class='fas fa-caret-right' style='color:black; font-size:40px; margin-left:70px;'></i></a>"; 
				<!-- 다음해 -->
				/* str+="<a class='before_after_year' onclick='calendarLoad(" + after_year + "," + after_month + ")'>";
				str+="&gt;&gt;</a>" */
				
				$("#navigation").append(str);
				
				$("#CalendarTable_body").empty();
				str1+="<tr>";
				
				const dateList = data.dateList;
				const dailyDiet = data.daily_diet;
				
				for(i = 0; i < data.dateList.length; i++){
					if(data.dateList[i].value == 'today' && dateList[i].date != null){
						var day_of_week = ['월','화','수','목','금'];
						
						str1+="<td class='today'>";
						str1+="<div class='date'>";
						str1+=data.today_info.search_month + "월 " + dateList[i].date + "일 (" + day_of_week[i%7 - 1] + ")";
						str1+="</div>";
						str1+="<div id='menuplan_day' style='padding: 5px;'>";
						if(dailyDiet[dateList[i].date] != null){
							str1+="<div style='text-align:center;'>";
							str1+="<span class='MenuPlan' style='font-weight: bold;'>" + dailyDiet[dateList[i].date].steamed_rice + "</span><br>";
							str1+="<span class='MenuPlan' style='font-weight: bold;'>" + dailyDiet[dateList[i].date].soup + "</span><br>";
							var sideMenu = dailyDiet[dateList[i].date].side_dish.split(",");
							for(j = 0; j < sideMenu.length; j++){
								str1+="<span class='MenuPlan' style='font-weight: bold;'>" + sideMenu[j] + "</span><br>";
							}
						}
						str1+="</div>";
						str1+="</div>";
						str1+="</td>";
					}else if(i % 7 == 6){
						str1+="";
						/* str1+="<td class='sat_day'>";
						str1+="<div class='sat'>";
						str1+=data.dateList[i].date;
						str1+="</div>";
						str1+="</td>"; */
					}else if(i % 7 == 0){
						str1+="</tr>";
						str1+="<tr>"
						/* str1+="<td class='sun_day'>";
						str1+="<div class='sun'>";
						str1+=data.dateList[i].date;
						str1+="</div>";
						str1+="</td>"; */
					}else{
						str1+="<td class='normal_day'>";
						if(dateList[i].date != null && dateList[i].date != ""){
							var day_of_week = ['월','화','수','목','금'];
							
							str1+="<div class='date'>";
							str1+=data.today_info.search_month + "월 " + dateList[i].date + "일 (" + day_of_week[i%7 - 1] + ")";
							str1+="</div>";	
						} else{
							str1+="<div class='no_date'><div>";
						}
						str1+="<div id='menuplan_day' style='padding: 5px;'>";
						if(dailyDiet[dateList[i].date] != null){
							str1+="<div style='text-align:center;'>";
							str1+="<span class='MenuPlan'>" + dailyDiet[dateList[i].date].steamed_rice + "</span><br>";
							str1+="<span class='MenuPlan'>" + dailyDiet[dateList[i].date].soup + "</span><br>";
							var sideMenu = dailyDiet[dateList[i].date].side_dish.split(",");
							for(j = 0; j < sideMenu.length; j++){
								str1+="<span class='MenuPlan'>" + sideMenu[j] + "</span><br>";
							}
						}
						str1+="</div>";
						str1+="</div>";
						str1+="</td>";
					}
				}
				$("#CalendarTable_body").append(str1);
				str = "";
				str1 = "";
			},				
			error:function(request,status,error){
				alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
				console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" +  "error:" + error);
			}
		});
	}
</script>
<main>  
	<div class="container-fluid px-4" >
		<!-- <h1 class="mt-4" >식단</h1> -->
		<div class="calendar">
			<!--날짜 네비게이션  -->
			<div class="navigation" id="navigation">
			</div>
			<table class="calendar_body" style="margin-left: auto; margin-right:auto;">
				<!-- <thead>
					<tr bgcolor="#A9A9A9">
						<td class="day">월</td>
						<td class="day">화</td>
						<td class="day">수</td>
						<td class="day">목</td>
						<td class="day">금</td>
					</tr>
				</thead>  -->
				<tbody id="CalendarTable_body" style="border: 1px solid black;">
				</tbody>
			</table>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/layout/adminFooter.jspf" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/layout/ownerHeader.jspf" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/owner/calender.css"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style>
	.form-check-input{margin-right: 2px;}
	.MenuPlan{
		font-size: 6px;
		font-weight: normal;
		line-height: 0.2px;
	}
	.menuModal{
	    display: inline-block;
	    width: 33%;
	    height: 20px;
	}
	.before_after_month, .before_after_year{
		text-decoration : none;
	}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		calendarLoad(null, null);
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
				
				const before_year = parseInt(data.today_info.search_year) - 1;
				const before_month = parseInt(data.today_info.search_month) - 1;
				const after_year = parseInt(data.today_info.search_year) + 1;
				const after_month = parseInt(data.today_info.search_month) - 1;
				
				str+="<a class='before_after_year' onclick='calendarLoad(" + before_year + "," + before_month + ")'>";
				str+="&lt;&lt;</a>";
				<!-- 이전달 --> 
				str+="<a class='before_after_month' onclick='calendarLoad(" + data.today_info.before_year + "," + data.today_info.before_month + ")'>";
				str+="&lt;</a>"; 
				str+="<span class='this_month'>&nbsp;";
				<!-- 년도.월 (1~9월까지는 앞에 0을 붙여서 01~09로 표시-->
				str+=data.today_info.search_year + ".";
				if(data.today_info.search_month < 10){
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
				
				$("#CalendarTable_body").empty();
				str1+="<tr>";
				for(i = 0; i < data.dateList.length; i++){
					if(data.dateList[i].value == 'today'){
						str1+="<td class='today' onclick='javascript:location.href=\"/owner/main\"\'>";
						str1+="<div class='date'>";
						str1+=data.dateList[i].date;
						str1+="</div>";
						str1+="<div id='menuplan_day'>";
						for(let j = 0; j < data.MonthMenuPlan.length; j++){
							if (parseInt(data.dateList[i].date) < 10){
								if(data.MonthMenuPlan[j].today_date.substring(8,10) == "0" + data.dateList[i].date){
									str1+="<div style='text-align:center;'>";
									str1+="<span class='MenuPlan' style='font-weight: bold;'>" + data.MonthMenuPlan[j].steamed_rice + "</span><br>";
									str1+="<span class='MenuPlan' style='font-weight: bold;'>" + data.MonthMenuPlan[j].soup + "</span><br>";
									for(let z = 0; z < data.sideDish.length; z++){
										if(data.sideDish[z].menuplan_date.substring(8,10) == data.dateList[i].date){
											str1+="<span class='MenuPlan' style='font-weight: bold;'>" + data.sideDish[z].side_dish + "</span><br>";
										}
									}
									str1+="<br>";
									str1+="</div>";
								}
							} else{
								if(data.MonthMenuPlan[j].today_date.substring(8,10) == data.dateList[i].date){
									str1+="<div style='text-align:center;'>";
									str1+="<span class='MenuPlan' style='font-weight: bold;'>" + data.MonthMenuPlan[j].steamed_rice + "</span><br>";
									str1+="<span class='MenuPlan' style='font-weight: bold;'>" + data.MonthMenuPlan[j].soup + "</span><br>";
									for(let z = 0; z < data.sideDish.length; z++){
										if(data.sideDish[z].menuplan_date.substring(8,10) == data.dateList[i].date){
											str1+="<span class='MenuPlan' style='font-weight: bold;'>" + data.sideDish[z].side_dish + "</span><br>";
										}
									}
									str1+="<br>";
									str1+="</div>";
								}
							}
						}
						str1+="</div>";
						str1+="</div>";
						str1+="</td>";
					} else if(i % 7 == 6){
						str1+="<td class='sat_day'>";
						str1+="<div class='sat'>";
						str1+=data.dateList[i].date;
						str1+="</div>";
						str1+="</td>";
					}else if(i % 7 == 0){
						str1+="</tr>";
						str1+="<tr>"
						str1+="<td class='sun_day'>";
						str1+="<div class='sun'>";
						str1+=data.dateList[i].date;
						str1+="</div>";
						str1+="</td>";
					}else{
						str1+="<td class='normal_day' onclick='showinsertMenuPlan(" + data.today_info.search_year + "," + data.today_info.search_month + "," + data.dateList[i].date + ")'>";
						str1+="<div class='date'>";
						str1+=data.dateList[i].date;
						str1+="<div id='menuplan_day'>";
						for(let j = 0; j < data.MonthMenuPlan.length; j++){
							if (parseInt(data.dateList[i].date) < 10){
								if(data.MonthMenuPlan[j].today_date.substring(8,10) == "0" + data.dateList[i].date){
									str1+="<div style='text-align:center;'>";
									str1+="<span class='MenuPlan' style='font-weight: bold;'>" + data.MonthMenuPlan[j].steamed_rice + "</span><br>";
									str1+="<span class='MenuPlan' style='font-weight: bold;'>" + data.MonthMenuPlan[j].soup + "</span><br>";
									for(let z = 0; z < data.sideDish.length; z++){
										if(data.sideDish[z].menuplan_date.substring(8,10) == data.dateList[i].date){
											str1+="<span class='MenuPlan' style='font-weight: bold;'>" + data.sideDish[z].side_dish + "</span><br>";
										}
									}
									str1+="<br>";
									str1+="</div>";
								}
							} else{
								if(data.MonthMenuPlan[j].today_date.substring(8,10) == data.dateList[i].date){
									str1+="<div style='text-align:center;'>";
									str1+="<span class='MenuPlan' style='font-weight: bold;'>" + data.MonthMenuPlan[j].steamed_rice + "</span><br>";
									str1+="<span class='MenuPlan' style='font-weight: bold;'>" + data.MonthMenuPlan[j].soup + "</span><br>";
									for(let z = 0; z < data.sideDish.length; z++){
										if(data.sideDish[z].menuplan_date.substring(8,10) == data.dateList[i].date){
											str1+="<span class='MenuPlan' style='font-weight: bold;'>" + data.sideDish[z].side_dish + "</span><br>";
										}
									}
									str1+="<br>";
									str1+="</div>";
								}
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

	function showinsertMenuPlan(year,month,day){
		var year = year;
		var month = month;
		var day = day;
		var select;
		//1-9일 앞에 0 붙여서 01-09로 표시
		if (parseInt(Number(month) / 10) == 0){ 
			if (parseInt(Number(day) / 10) == 0){ 
				select = year + '-0' + month + '-0' + day;
			} else {
				select = year + '-0' + month + '-' + day;	
			}
		} else {
			if (parseInt(Number(day) / 10) == 0){ 
				select = year + '-' + month + '-0' + day;
			} else {
				select = year + '-' + month + '-' + day;	
			}
		}
		
		$("input[name=SelectDate]").attr('value',select); 
		$("#insertSelectday").html("< "+select+" > 식단 등록");
		$("#modiSelectday").html("< "+select+" > 식단 수정");
		
		if(parseInt(Number(selectYear)) <= parseInt(Number(year))){
			if(parseInt(Number(selectMonth)) < parseInt(Number(month))){
				insertTodayMenu(select);
			}else if(parseInt(Number(selectMonth)) == parseInt(Number(month))){
				if(parseInt(Number(date)) < parseInt(Number(day))){
					insertTodayMenu(select);
				} else{
					$("#select_modaltitle").html("< " + select + " > 식단");
					showTodayMenu(select);
				}
			}
		} else if(parseInt(Number(selectYear)) > parseInt(Number(year))){
			if(parseInt(Number(month)) < parseInt(Number(selectMonth))){
				$("#select_modaltitle").html("< " + select + " > 식단");
				showTodayMenu(select);
			} else {
				$("#select_modaltitle").html("< " + select + " > 식단");
				showTodayMenu(select);
			}	
		}
	}
	//선택한 날짜에 등록되어 있는 식단 출력 (오늘 날짜보다 이전인 경우)
	function showTodayMenu(selectDate){ 
		var str = selectDate;
		var side = "";
		$.ajax({
			url: "<c:url value='/owner/printTodayMenuPlan'/>",
			type: "POST",
			data : {selectDate : str},
			dataType : "json",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(data){
				if(data.check > 0){
					if(data.sidedish.length != 0){
						for (i = 0; i < data.sidedish.length; i++ ) {
							side += data.sidedish[i].side_dish + "<br>";
						}
					}
					$("#select_rice").html(data.result.steamed_rice);
					$("#select_soup").html(data.result.soup);
					$("#select_sideDish").html(side);
					
					$("#todayMenuModal").modal('show');
				} else if(data.check == 0){
					alert("해당 날짜에 등록된 식단이 없습니다!");
				}				
			},				
			error:function(request,status,error)
			{
			   alert(error);
			}
		});
		
	}
	// 오늘 날짜보다 후인 경우에 식단 등록/수정
	function insertTodayMenu(selectDate){
		var str = selectDate;
		
		$.ajax({
			url: "<c:url value='/owner/menuInCheck'/>",
			type: "POST",
			data : {selectDate : str},
			dataType : "json",
			success : function(data){
				// 선택한 날짜에 등록된 식단이 없다면
				// 식단 등록 모달 표시
				if(data.check == 0){
					iden = "_insert";
					$("#menuPlannerInsert").modal('show');
				}
				// 선택한 날짜에 등록된 식단이 있다면
				// 식단 수정 모달 표시
				else if(data.check > 0){
					iden = "_modify";
					$("#menuPlannerModify").modal('show');
				}
				// DB 테이블에 저장되어 있는 메뉴가 있다면
				if(data.category.length > 0) {
					menu_data = data.category;
					
					$("#rice" + iden).empty();
					$("#soup" + iden).empty();
					$("#sideDish" + iden).empty();
					
					// 만약 선택한 날짜에 저장되어 있는 식단이 있다면
					if(data.todayMenu != "" && data.todayMenu != null) {	
						for(var i = 0; i < data.category.length; i++) {
							if(data.category[i].menu_type == "R") {
								// 저장되어 있는 메뉴를 선택
								if(data.category[i].menu_name == data.todayMenu.steamed_rice){
									$("#rice" + iden).append("<div class='menuModal'><input type='radio' name='rice_M' class='form-check-input' value='" + data.category[i].menu_name + "' checked>" + data.category[i].menu_name + "</div>");
									rice_count++;
								} else{
									$("#rice" + iden).append("<div class='menuModal'><input type='radio' name='rice_M' class='form-check-input' value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name + "</div>");
									rice_count++;
								}	
								if(rice_count != 0 && rice_count % 3 == 0){
									$("#rice" + iden).append("</br>");
								}
							}
							if(data.category[i].menu_type == "S") {
								if(data.category[i].menu_name == data.todayMenu.soup){
									$("#soup" + iden).append("<div class='menuModal'><input type='radio' name='soup_M' class='form-check-input' value='" + data.category[i].menu_name + "' checked>" + data.category[i].menu_name + "</div>");
									soup_count++;								
								} else{
									$("#soup" + iden).append("<div class='menuModal'><input type='radio' name='soup_M' class='form-check-input' value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name + "</div>");
									soup_count++;
								}
								if(soup_count != 0 && soup_count % 3 == 0){
									$("#soup" + iden).append("</br>");
								}
							}
							if(data.category[i].menu_type == "D") { 
								$("#sideDish" + iden).append("<div class='menuModal'><input type='checkbox' name='sideDish_M' class='form-check-input' id='" + data.category[i].menu_name +"'value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name + "</div>");
								// 선택한 날짜 식단에 저장되어 있는 반찬이 있다면
								// 저장되어 있는 반찬 선택
								side_count++;
								if(data.sidedish.length != 0){
									for (j = 0; j < data.sidedish.length; j++ ) {
										$("input:checkbox[id='"+ data.sidedish[j].side_dish +"']").prop("checked", true);
									}
								}
								if(side_count != 0 && side_count % 3 == 0){
									$("#sideDish" + iden).append("</br>");
								}
							}
						}
						rice_count = 0;
						soup_count = 0;
						side_count = 0;
					}
					// 선택한 날짜에 저장되어 있는 식단이 없다면
					else {
						for(var i = 0; i < data.category.length; i++) {
							if(data.category[i].menu_type == "R") {
								$("#rice" + iden).append("<input type='radio' name='rice_I' class='form-check-input' value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name + "  ");								
								rice_count++;
							}
							if(data.category[i].menu_type == "S") {
								$("#soup" + iden).append("<input type='radio' name='soup_I' class='form-check-input' value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name + "  ");								
								soup_count++;
							}
							if(data.category[i].menu_type == "D") {
								$("#sideDish" + iden).append("<input type='checkbox' name='sideDish_I' class='form-check-input' value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name + "  ");								
								side_count++;
							}
							if(rice_count != 0 && rice_count % 5 == 0){
								$("#rice" + iden).append("<br>");
							}
							if(soup_count != 0 && soup_count % 5 == 0){
								$("#soup" + iden).append("<br>");
							}
							if(side_count != 0 && side_count % 5 == 0){
								$("#sideDish" + iden).append("<br>");
							}
						}
						rice_count = 0;
						soup_count = 0;
						side_count = 0;
					}
				}
			},
			error:function(request,status,error)
			{
			   alert("error");
			}
		});	
	}

	/* 선택한 날짜 식단 등록*/
	function check_input_I() {
		// 배열 초기화
		var checkArr = [];     
		
	    $("input[name='sideDish_I']:checked").each(function(i) {
	    	// 체크된 것만 값을 뽑아서 배열에 push
	        checkArr.push($(this).val());      
	    })

	    var SelectRice = $("input[name='rice_I']:checked").val();
	    var SelectSoup = $("input[name='soup_I']:checked").val();
	    var SelectDate = $("#SelectDate").val();
	    
	    if (SelectRice != null && SelectSoup && checkArr.length != 0){
	    	$.ajax({
				url: "<c:url value='/owner/menuplanSave'/>",
			    type: "POST",
			    data : {
			    	today_date : SelectDate,
			    	steamed_rice : SelectRice,
			    	soup : SelectSoup,
			    	sideDish : checkArr
			    },
			    success : function(data){
					alert(data);
				},
				complete : function(data){
					location.reload();
				},
				error:function(request,status,error)
			    {
			    	alert(error);
				}
			});
	    } else {
	    	alert("최소 1개 이상의 메뉴를 선택해주세요!");
	        return;
	    }
	}
	/* 선택한 날짜 식단 수정*/
	function check_input_M() {
		// 배열 초기화
		var checkArr = [];     
		
	    $("input[name='sideDish_M']:checked").each(function(i) {
	    	// 체크된 것만 값을 뽑아서 배열에 push
	        checkArr.push($(this).val());     
	    })

	    var SelectRice = $("input[name='rice_M']:checked").val();
	    var SelectSoup = $("input[name='soup_M']:checked").val();
	    var SelectDate = $("#SelectDate").val();
	    
	    if (SelectRice != null && SelectSoup && checkArr.length != 0){
	    	$.ajax({
				url: "<c:url value='/owner/menuPlanUpdateSave'/>",
			    type: "POST",
			    data : {
			    	today_date : SelectDate,
			    	steamed_rice : SelectRice,
			    	soup : SelectSoup,
			    	sideDish : checkArr
			    },
			    success : function(data){
					alert(data);
				},
				complete : function(data){
					location.reload();
				},
				error:function(request,status,error)
			    {
			    	alert(error);
				}
			});
	    } else {
	    	alert("최소 1개 이상의 메뉴를 선택해주세요!");
	        return;
	    }
	}
</script>
<main>  
	<div class="container-fluid px-4">
		<h1 class="mt-4" >일별 식단 등록</h1>
			<ol class="breadcrumb mb-4">
                <li class="breadcrumb-item">식당운영자</li>
            	<li class="breadcrumb-item active">일별 식단 등록</li>
			</ol>
			<div class="calendar" >
			<!--날짜 네비게이션  -->
			<div class="navigation" id="navigation">
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
				<tbody id="CalendarTable_body">
				</tbody>
			</table>
		</div>
	</div>
	<!-- 일별 식단 등록 Modal -->
	<div class="modal fade" id="menuPlannerInsert" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true"> 
		<div class="container-fluid px-4">
			<div class="modal-dialog"  style="width:auto; display:table;"> 
				<div class="modal-content"> 
					<div class="modal-header"> 
						<font size="4.6em" style="margin-left:auto; margin-right:auto;" face="Verdana" class="modal-title" id="insertSelectday"></font>
					</div> 
					<div class="modal-body">
						<form id="menuInsertForm" name="menuInsertForm" method="post" role="form">
							<input type="hidden" name="SelectDate" id="SelectDate"/>
							<div class="row">
								<strong size="3em" face="Verdana"> <strong style="color: red;">*</strong> 밥 </strong>
								<div id="rice_insert"></div>
							</div>
							<div class="row">
								<strong size="3em" face="Verdana"> <strong style="color: red;">*</strong> 국 </strong>
								<div id="soup_insert"></div>
							</div>
							<div class="row sideDishRow">
								<strong size="3em" face="Verdana"> <strong style="color: red;">*</strong> 반찬 (1개 이상 선택)</strong>
								<div id="sideDish_insert"></div>
							</div>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						</form> 	
					</div> 
					<div class="modal-footer"> 
						<input type="button" class="btn btn-primary" value="등록" onclick="check_input_I()"/>
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
					</div>
				</div> 
			</div> 
		</div>
	</div>

	<!-- 선택한 날짜의 식단 출력 Modal -->
	<div class="modal fade" id="todayMenuModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true"> 
		<div class="container-fluid px-4">
			<div class="modal-dialog"> 
				<div class="modal-content"> 
					<div class="modal-header"> 
						<font size="4.6em" style="margin-left:auto; margin-right:auto;" face="Verdana" class="modal-title" id="select_modaltitle"></font>
					</div> 
					<div class="modal-body"> 
						<table style="margin-left: auto; margin-right: auto; border:1px solid black; border-collapse:collapse; width:250px;">
							<tr>
								<td style="text-align: center; padding:0.1em; font-size:16px;"><p id="select_rice"></p></td>
							</tr>
							<tr>
								<td style="text-align: center; padding:0.1em; font-size:16px;"><p id="select_soup"></p></td>
							</tr>
							<tr>
								<td style="text-align: center; padding:0.1em; font-size:16px;"><p id="select_sideDish"></p></td>
							</tr>
						</table>
					</div> 
					<div class="modal-footer"> 
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
					</div> 
				</div> 
			</div> 
		</div>
	</div>
	
	<!-- 일별 식단 수정 Modal -->
	<div class="modal fade" id="menuPlannerModify" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true"> 
		<div class="container-fluid px-4">
			<div class="modal-dialog"> 
				<div class="modal-content"> 
					<div class="modal-header"> 
						<font size="4.6em" style="margin-left:auto; margin-right:auto;" face="Verdana" class="modal-title" id="modiSelectday"></font>
					</div>
					<div class="modal-body"> 
						<form id="menuModifyForm" name="menuModifyForm" method="post" role="form">	
							<input type="hidden" name="SelectDate" value="${todayMenu.today_date}">
							<div class="row">
								<strong size="3em" face="Verdana"><strong style="color: red;">*</strong> 밥 </strong>
								<div id="rice_modify"></div>
							</div>
							<div class="row">
								<strong size="3em" face="Verdana"><strong style="color: red;">*</strong> 국 </strong>
								<div id="soup_modify"></div>
							</div>
							<div class="row sideDishRow">
								<strong size="3em" face="Verdana"><strong style="color: red;">*</strong> 반찬 (1개 이상 선택)</strong> 
								<div id="sideDish_modify"></div>
							</div>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						</form>
					</div>
					<div class="modal-footer"> 
						<input type="button" class="btn btn-primary" value="수정" onclick="check_input_M()"/>
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
					</div>
				</div> 
			</div> 
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/layout/adminFooter.jspf" %>

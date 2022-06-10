<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/layout/ownerHeader.jspf" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/owner/calender.css"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<script type="text/javascript">
	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");
	var menu_data;
	var iden;
	
	let today = new Date();   
	let date = today.getDate();
	
	function showinsertMenuPlan(year,month,day){
		var month = month;
		var day = day;
		var select;
		//1-9일 앞에 0 붙여서 01-09로 표시
		if (parseInt(Number(day) / 10) == 0){ 
			select = year + '-' + month + '-0' + day;
		} else {
			select = year + '-' + month + '-' + day;	
		}
		
		$('input[name=SelectDate]').attr('value',select); 
		$("#insertSelectday").html("< "+select+" > 식단 등록");
		$("#modiSelectday").html("< "+select+" > 식단 수정");
		
		if(parseInt(Number(date)) <= parseInt(Number(day))){
			insertTodayMenu(select);
		} else if(parseInt(Number(date)) >= parseInt(Number(day))){
			$("#select_modaltitle").html("< "+select+" > 식단");
			showTodayMenu(select);
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
			dataType : 'json',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(data){
				if(data.check > 0){
					if(data.sidedish[0] != null){
						for (i = 0; i < data.sidedish.length; i++ ) {
							side += data.sidedish[i] + "<br>";
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
	//식단 등록하는 모달 표시 ( 오늘 날짜보다 후인 경우에)
	function insertTodayMenu(selectDate){
		var str=selectDate;
		
		$.ajax({
			url: "<c:url value='/owner/menuInCheck'/>",
			type: "POST",
			data : {selectDate : str},
			dataType : 'json',
			success : function(data){
				if(data.check == 0){
					iden = "_insert";
					$("#addRow_i").empty();
					$("#menuPlannerInsert").modal('show');
				} else if(data.check > 0){
					iden = "_modify";
					$("#addRow_m").empty();
					$("#menuPlannerModify").modal('show');
				}
				if(data.category.length > 0) {
					menu_data = data.category;
					
					$('#rice' + iden).empty();
					$('#soup' + iden).empty();
					$('#sideDish' + iden + '0').empty();
					
					option = "<option value=''>메뉴를 선택해주세요</option>";
					$('#rice' + iden).append(option);
					$('#soup' + iden).append(option);
					$('#sideDish' + iden + '0').append(option);
					
					if(data.todayMenu != "" && data.todayMenu != null) {
						var sideArr = data.todayMenu.side_dish.split("/");
						
						for(var i = 0; i < data.category.length; i++) {
							
							if(data.category[i].menu_type == "밥") {
								option = $("<option value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name + "</option>");
								$('#rice' + iden).append(option);
								$('#rice' + iden).val(data.todayMenu.steamed_rice).prop("selected",true);
							}
							
							if(data.category[i].menu_type == "국") {
								option = $("<option value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name + "</option>");
								$('#soup' + iden).append(option);
								$('#soup' + iden).val(data.todayMenu.soup).prop("selected",true);
							}
						}
						
						for(var j = 0; j < sideArr.length; j++) {
							
							if(j != 0) {
								$("#iclass").remove();
								html = "<div class=\"row sideDishRow\">";
								html += "<select class=\"form-select mb-2\" name='sideDish_modify" + j + "' id=\"sideDish_modify" + j + "\" style=\"width:85%;\">";
								html += "</select>";
								html += "<a href=\"javascript:removeSideDish('M');\" style=\"width: 10%;\" id=\"iclass\"><i class=\"fas fa-minus-square fa-2x\"></i></a>";
								html += "</div>";
								
								$("#addRow_m").append(html);
								
								option = "<option value=''>메뉴를 선택해주세요</option>";
								$('#sideDish' + iden + j).append(option);
							}
							
							for(var i = 0; i < data.category.length; i++) {
								if(data.category[i].menu_type == "반찬") {
									if(j == 0){
										option = $("<option value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name + "</option>");
										$('#sideDish' + iden + j).append(option);
									} else {
										option = $("<option value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name + "</option>");
										$('#sideDish' + iden + j).append(option);
									}
								}
							}
							
							$('#sideDish' + iden + j).val(sideArr[j]).prop("selected",true);
						}
					} else {
						for(var i = 0; i < data.category.length; i++) {
							if(data.category[i].menu_type == "밥") {
								option = $("<option value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name + "</option>");
								$('#rice' + iden).append(option);								
							}
							if(data.category[i].menu_type == "국") {
								option = $("<option value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name + "</option>");
								$('#soup' + iden).append(option);								
							}
							if(data.category[i].menu_type == "반찬") {
								option = $("<option value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name + "</option>");
								$('#sideDish' + iden + '0').append(option);								
							}
						}
					}
				}
			},
			error:function(request,status,error)
			{
			   alert("error");
			}
		});
		
	}

	function addSideDish(type) {
		var select;
		var html;
		
		if(type == "I") {
			select = $("#menuInsertForm .sideDishRow");
			if(select.length > 9){
				alert("최대 등록 갯수를 초과하였습니다.");
				return;
			}
			else {
				$("#iclass").remove();
				html = "<div class=\"row sideDishRow\">";
				html += "<select class=\"form-select mb-2\" name='sideDish_insert" + select.length + "' id=\"sideDish_insert" + select.length + "\" style=\"width:85%;\">";
				html += "</select>";
				html += "<a href=\"javascript:removeSideDish('I');\" style=\"width: 10%;\" id=\"iclass\"><i class=\"fas fa-minus-square fa-2x\"></i></a>";
				html += "</div>";
				
				$("#addRow_i").append(html);
			}
			
		} else if(type == "M") {
			select = $("#menuModifyForm .sideDishRow");
			
			if(select.length > 9){
				alert("최대 등록 갯수를 초과하였습니다.");
				return;
			}
			else {
				console.log(select.length);
				$("#iclass").remove();
				html = "<div class=\"row sideDishRow\">";
				html += "<select class=\"form-select mb-2\" name='sideDish_modify" + select.length + "' id=\"sideDish_modify" + select.length + "\" style=\"width:85%;\">";
				html += "</select>";
				html += "<a href=\"javascript:removeSideDish('M');\" style=\"width: 10%;\" id=\"iclass\"><i class=\"fas fa-minus-square fa-2x\"></i></a>";
				html += "</div>";
				
				$("#addRow_m").append(html);
			}
		}
		
		option = "<option value=''>메뉴를 선택해주세요</option>";
		
		$('#sideDish' + iden + select.length).append(option);		
		for(var i = 0; i < menu_data.length; i++) {
			if(menu_data[i].menu_type == "반찬") {
				option = $("<option value='" + menu_data[i].menu_name + "'>" + menu_data[i].menu_name + "</option>");
				$('#sideDish' + iden + select.length).append(option);								
			}
		}
	}

	function removeSideDish(type) {
		
		var html = "";
		
		if(type == "I") {
			var select = $("#menuInsertForm .sideDishRow");
			select.eq(select.length-1).remove();
			
			html = "<a href=\"javascript:removeSideDish('I');\" style=\"width: 10%;\" id=\"iclass\"><i class=\"fas fa-minus-square fa-2x\"></i></a>";
			
			if(select.length-1 > 1) {
				$("#menuInsertForm .sideDishRow > select:last").after(html);
			}
			
		} else if (type == "M") {
			var select = $("#menuModifyForm .sideDishRow");
			select.eq(select.length-1).remove();
			
			html = "<a href=\"javascript:removeSideDish('M');\" style=\"width: 10%;\" id=\"iclass\"><i class=\"fas fa-minus-square fa-2x\"></i></a>"
			
			if(select.length-1 > 1) {
				$("#menuModifyForm .sideDishRow > select:last").after(html);
			}
		}
	}

	function check_input_I() {
		
	    if (($("#rice_insert").val() != null && $("#rice_insert").val() != "") && 
			($("#soup_insert").val() != null && $("#soup_insert").val() != "") &&
			($("#sideDish_insert0").val() != null && $("#sideDish_insert0").val() != ""))
	    {
	    	$.ajax({
				url: "<c:url value='/owner/menuplanSave'/>",
			    type: "POST",
			    data : $("#menuInsertForm").serialize(),
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
	    	alert("최소 1개 이상의 메뉴를 입력해주세요!");
	        return;
	    }
	}

	function check_input_M() {
		
	    if (($("#rice_modify").val() != null && $("#rice_modify").val() != "") && 
			($("#soup_modify").val() != null && $("#soup_modify").val() != "") &&
			($("#sideDish_modify0").val() != null && $("#sideDish_modify0").val() != ""))
	    {
	    	$.ajax({
				url: "<c:url value='/owner/menuPlanUpdateSave'/>",
			    type: "POST",
			    data : $("#menuModifyForm").serialize(),
			    /*beforeSend: function (xhr) {
			    	xhr.setRequestHeader(header, token);
			           
				},*/
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
	    	alert("최소 1개 이상의 메뉴를 입력해주세요!");
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
		<form name="calendarFrm" id="calendarFrm" action="" method="GET">
			<div class="calendar" >
			<!--날짜 네비게이션  -->
			<div class="navigation">
				<a class="before_after_year" href="/owner/menuPlanInsertCalendar?year=${today_info.search_year - 1}&month=${today_info.search_month - 1}">
					&lt;&lt;
				<!-- 이전해 -->
				</a> 
				<a class="before_after_month" href="/owner/menuPlanInsertCalendar?year=${today_info.before_year}&month=${today_info.before_month}">
					&lt;
				<!-- 이전달 -->
				</a> 
				<span class="this_month">
					&nbsp;${today_info.search_year}. 
					<c:if test="${today_info.search_month < 10}">0</c:if>${today_info.search_month}
				</span>
				<a class="before_after_month" href="/owner/menuPlanInsertCalendar?year=${today_info.after_year}&month=${today_info.after_month}">
				<!-- 다음달 -->
					&gt;
				</a> 
				<a class="before_after_year" href="/owner/menuPlanInsertCalendar?year=${today_info.search_year + 1}&month=${today_info.search_month - 1}">
					<!-- 다음해 -->
					&gt;&gt;
				</a>
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
									<td class="today"  onclick="location.href='/owner/main'">
										<div class="date">
											${dateList.date}
											<div id="menuplan_day">
												<c:forEach var="ateuserList" items="${dateList.selectDayMenulist}" varStatus="ateuser_status">
													<c:if test="${ateuserList.today_date != null}"> 
														<div style="text-align:center;">
							                               <i class="fas fa-utensils" style="font-size:20px;"></i>
							                            </div>
													</c:if>
												</c:forEach>
												<br>
											</div>
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
						<td class="normal_day" onclick="showinsertMenuPlan('${today_info.search_year}','${today_info.search_month}','${dateList.date}')">
							<div class="date">
								${dateList.date}
								<div id="menuplan_day">
									<c:forEach var="ateuserList" items="${dateList.selectDayMenulist}" varStatus="ateuser_status">
										<c:if test="${ateuserList.today_date != null}"> 
											<div style="text-align:center;">
				                               <i class="fas fa-utensils" style="font-size:20px;"></i>
				                            </div>
										</c:if>
									</c:forEach>
									<br>
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
	<!-- 일별 식단 등록 Modal -->
	<div class="modal fade" id="menuPlannerInsert" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true"> 
		<div class="container-fluid px-4">
			<div class="modal-dialog"  style="width:auto;display:table;"> 
				<div class="modal-content"> 
					<div class="modal-header"> 
						<font size="4.6em" style="margin-left:auto; margin-right:auto;" face="Verdana" class="modal-title" id="insertSelectday"></font>
					</div> 
					<div class="modal-body">
						<form id="menuInsertForm" name="menuInsertForm" method="post" role="form">
						<input type="hidden" name="SelectDate" id="SelectDate"/>
						<div class="row">
							<strong size="3em" face="Verdana"> <strong style="color: red;">*</strong> 밥 </strong>
							<select class="form-select" name="rice" id="rice_insert">
							</select>
						</div>
						<div class="row">
							<strong size="3em" face="Verdana"> <strong style="color: red;">*</strong> 국 </strong>
							<select class="form-select" name="soup" id="soup_insert">
							</select>
						</div>
						<div class="row sideDishRow">
							<strong size="3em" face="Verdana"> <strong style="color: red;">*</strong> 반찬 (최상단 메뉴 필수입력) </strong>
							<select class="form-select mb-2" name="sideDish_insert0" id="sideDish_insert0" style="width: 85%;">
							</select>
							<a href="javascript:addSideDish('I');" style="width: 10%;"><i class="fas fa-plus fa-2x"></i></a>
						</div>
						<div id="addRow_i"></div>
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
							<strong size="3em" face="Verdana"> <strong style="color: red;">*</strong> 밥 </strong>
							<select class="form-select" name="rice" id="rice_modify">
							</select>
						</div>
						<div class="row">
							<strong size="3em" face="Verdana"> <strong style="color: red;">*</strong> 국 </strong>
							<select class="form-select" name="soup" id="soup_modify">
							</select>
						</div>
						<div class="row sideDishRow">
							<strong size="3em" face="Verdana"> <strong style="color: red;">*</strong> 반찬 (최상단 메뉴 필수입력)</strong> 
							<select class="form-select mb-2" name="sideDish_modify0" id="sideDish_modify0" style="width: 85%;">
							</select>
							<a href="javascript:addSideDish('M');" style="width: 10%;"><i class="fas fa-plus fa-2x"></i></a>
						</div>
						<div id="addRow_m"></div>
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
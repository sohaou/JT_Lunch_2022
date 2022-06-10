<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/layout/ownerHeader.jspf" %>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script> -->
<script type="text/javascript">
	var menu_data;
	var iden;
	var today_menu_data_total = "${todayMenu}";
	var today_menu_data_rice = "${todayMenu.steamed_rice}";
	var today_menu_data_soup = "${todayMenu.soup}";
	
	function getMenuFunction(type){
		var option;
		var rice_count = 0;
		var soup_count = 0;
		var side_count = 0;
		
		if(type == "M") {
			iden = "_modify";
		} else if(type == "I") {
			iden = "_insert";
		}
		
		$.ajax({
			type : "POST",
			url : "<c:url value='/owner/menuPlanner'/>",
			success : function(data){
				
				if(data.category.length > 0) {
					menu_data = data.category;
					
					$("#rice" + iden).empty();
					$("#soup" + iden).empty();
					$("#sideDish" + iden).empty();
					
					if(today_menu_data_total != "" && today_menu_data_total != null) {
						for(var i = 0; i < data.category.length; i++) {
							if(data.category[i].menu_type == "R") {
								if(data.category[i].menu_name == today_menu_data_rice){
									$("#rice" + iden).append("<input type='radio' name='rice_M' class='form-check-input' value='" + data.category[i].menu_name + "' checked>" + data.category[i].menu_name + "  ");
									rice_count++;
								} else{
									$("#rice" + iden).append("<input type='radio' name='rice_M' class='form-check-input' value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name + "  ");
									rice_count++;
								}	
								if(rice_count % 5 == 0){
									$("#rice" + iden).append("</br>");
								}
							}
							if(data.category[i].menu_type == "S") {
								if(data.category[i].menu_name == today_menu_data_soup){
									$("#soup" + iden).append("<input type='radio' name='soup_M' class='form-check-input' value='" + data.category[i].menu_name + "' checked>" + data.category[i].menu_name + "  ");
									soup_count++;
								} else{
									$("#soup" + iden).append("<input type='radio' name='soup_M' class='form-check-input' value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name + "  ");
									soup_count++;
								}
								if(soup_count % 5 == 0){
									$("#soup" + iden).append("</br>");
								}
							}
							if(data.category[i].menu_type == "D") {
								$("#sideDish" + iden).append("<input type='checkbox' name='sideDish_M' class='form-check-input' id='" + data.category[i].menu_name +"'value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name + "  ");
								side_count++;
								if(data.sidedish.length != 0){
									for (j = 0; j < data.sidedish.length; j++ ) {
										$("input:checkbox[id='"+ data.sidedish[j].side_dish +"']").prop("checked", true);
									}
								}
								if(side_count % 5 == 0){
									$("#sideDish" + iden).append("</br>");
								}
							}
						}
						rice_count = 0;
						soup_count = 0;
						side_count = 0;
					} else {
						for(var i = 0; i < data.category.length; i++) {
							if(data.category[i].menu_type == "R") {
								$("#rice" + iden).append("<input type='radio' name='rice_I' class='form-check-input' value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name);	
								rice_count++;
							}
							if(data.category[i].menu_type == "S") {
								$("#soup" + iden).append("<input type='radio' name='soup_I' class='form-check-input' value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name);	
								soup_count++;
							}
							if(data.category[i].menu_type == "D") {
								$("#sideDish" + iden).append("<input type='checkbox' name='sideDish_I' class='form-check-input' value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name);
								side_count++;
							}
							if(rice_count % 5 == 0){
								$("#rice" + iden).append("<br>");
							}
							if(soup_count % 5 == 0){
								$("#soup" + iden).append("<br>");
							}
							if(side_count % 5 == 0){
								$("#sideDish" + iden).append("<br>");
							}
						}
						rice_count = 0;
						soup_count = 0;
						side_count = 0;
					}
				}
			}
		});
	}
	
	function check_input_I() {
		var checkArr = [];     // 배열 초기화
		
	    $("input[name='sideDish_I']:checked").each(function(i) {
	        checkArr.push($(this).val());     // 체크된 것만 값을 뽑아서 배열에 push
	    })

	    var SelectRice = $("input[name='rice_I']:checked").val();
	    var SelectSoup = $("input[name='soup_I']:checked").val();
	    var SelectDate = $("#SelectDate").val();
	    
	    if (SelectRice != null && SelectSoup && checkArr.length != 0)
	    {
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
	
	function check_input_M() {
		var checkArr = [];     // 배열 초기화
		
	    $("input[name='sideDish_M']:checked").each(function(i) {
	        checkArr.push($(this).val());     // 체크된 것만 값을 뽑아서 배열에 push
	    })

	    var SelectRice = $("input[name='rice_M']:checked").val();
	    var SelectSoup = $("input[name='soup_M']:checked").val();
	    var SelectDate = $("#SelectDate").val();
	    
	    if (SelectRice != null && SelectSoup && checkArr.length != 0)
	    {
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
		<h1 class="mt-4">금일 식단</h1>
		<ol class="breadcrumb mb-4">
			<li class="breadcrumb-item">식당운영자</li>
            <li class="breadcrumb-item active">금일 식단 확인 및 수정</li>
		</ol>
		<div class="row">
			<div class="col-xl-6 col-md-6">
            	<div class="card bg-success text-white mb-4">
                	<div class="card-body">금일 식사 예약자 수 : ${reserveCount} 명</div>
					<div class="card-footer d-flex align-items-center justify-content-between">
                	</div>
				</div>
			</div>
			<div class="col-xl-6 col-md-6">
            	<div class="card bg-danger text-white mb-4">
                	<div class="card-body">금일 실 식사자 수 : ${ate_user} 명</div>
					<div class="card-footer d-flex align-items-center justify-content-between">
                	</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xl-6">
				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-utensils me-1"></i>
						금일 식단표
                    </div>
                    <div class="card-body">
                    	<table class="table table-borderless">
							<c:set var="menu" value="${todayMenu.today_date}"/>
							<c:if test="${menu != null}">
								<tr>
									<td style="text-align: center;"><font size="6em" face="Verdana">${todayMenu.today_date}</font></td>
								</tr>
								<tr>
									<td class="table-primary" style="text-align: center; padding:0.1em; font-size: 1.5em;">${todayMenu.steamed_rice}</td>
								</tr>
								<tr>
									<td class="table-secondary" style="text-align: center; padding:0.1em; font-size: 1.5em;">${todayMenu.soup}</td>
								</tr>
								<c:forEach items="${sidedish}" var="sd" varStatus="status">
								<tr>
									<td class="table-success" style="text-align: center; padding:0.1em; font-size: 1.5em;">${sd.side_dish}</td>
								</tr>
								</c:forEach>
							</c:if>
							<c:if test="${menu == null}">
								<tr>
									<td class="table-danger" style="text-align: center; padding:0.1em;"><font size="4em" face="Verdana">등록된 식단이 없습니다.</font></td>
								</tr>
							</c:if>
							<tr>
								<td style="text-align: center; padding:0.1em;"><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /></td>
							</tr>
						</table>
						<div style="text-align: center;">
							<c:if test="${menu != null}">
								<input style="width: 150px;" class="btn btn-primary" type="button" value="수정" data-bs-toggle="modal" data-bs-target="#menuPlannerModify" onclick="getMenuFunction('M')"/>
							</c:if>
							<c:if test="${menu == null}">
								<input style="width: 150px;" class="btn btn-primary" type="button" value="등록" data-bs-toggle="modal" data-bs-target="#menuPlannerInsert" onclick="getMenuFunction('I')"/>
							</c:if>
						</div>
                    </div>
				</div>
			</div>
		</div>
	</div>

	<!-- 식단 등록 Modal -->
	<div class="modal fade" id="menuPlannerInsert" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="container-fluid px-4">
			<div class="modal-dialog" role="document">
			    <div class="modal-content">
			    	<div class="modal-header">
			        	<h4 class="modal-title" id="myModalLabel">식단 등록</h4>
			      	</div>
					<div class="modal-body">
						<form id="menuInsertForm" name="menuInsertForm" action="/owner/menuplanSave" method="post" role="form">
						<c:set var="ymd" value="<%=new java.util.Date()%>" /> 
						<fmt:formatDate value="${ymd}" pattern="yyyy-MM-dd" var="today"/>
						<input type="hidden" id="SelectDate" name="SelectDate" value="${today}"></input>
						<div class="row">
							<strong size="3em" face="Verdana"> <strong style="color: red;">*</strong> 밥 </strong>
							<div id="rice_insert"></div>
						</div>
						<div class="row">
							<strong size="3em" face="Verdana"> <strong style="color: red;">*</strong> 국 </strong>
							<div id="soup_insert"></div>
						</div>
						<div class="row sideDishRow">
							<strong size="3em" face="Verdana"> <strong style="color: red;">*</strong> 반찬 (1개 이상 선택) </strong>
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
	
	<!-- 식단수정 Modal -->
	<div class="modal fade" id="menuPlannerModify" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="container-fluid px-4">
			<div class="modal-dialog" role="document">
			    <div class="modal-content">
			    	<div class="modal-header">
			        	<h4 class="modal-title" id="myModalLabel">식단 수정</h4>
			      	</div>
					<div class="modal-body">
						<form id="menuModifyForm" name="menuModifyForm" action="/owner/menuPlanUpdateSave" method="post" role="form">
						<input type="hidden" name="SelectDate" value="${todayMenu.today_date}">
						<div class="row">
							<strong size="3em" face="Verdana"> <strong style="color: red;">*</strong> 밥 </strong>
							<div id="rice_modify"></div>
						</div>
						<div class="row">
							<strong size="3em" face="Verdana"> <strong style="color: red;">*</strong> 국 </strong>
							<div id="soup_modify"></div>
						</div>
						<div class="row sideDishRow">
							<strong size="3em" face="Verdana"> <strong style="color: red;">*</strong> 반찬 (1개 이상 선택)</strong> 
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
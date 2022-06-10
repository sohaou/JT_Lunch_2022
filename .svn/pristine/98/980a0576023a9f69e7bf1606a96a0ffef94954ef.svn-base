<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/layout/ownerHeader.jspf" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>

<title>식당 운영자 _ 금일 식단 확인/수정</title>

<script type="text/javascript">
	var today_menu_data_total = '${todayMenu}';
	var today_menu_data_rice = '${todayMenu.steamed_rice}';
	var today_menu_data_soup = '${todayMenu.soup}';
	var today_menu_data_sideDish = '${todayMenu.side_dish}';

	window.onload = function() {
		var menuData = new Array();
		var totalLikeCount = ${total_like_count};
		
		var data = { 
				labels: [
					'${menuTop.get(0).menu_name}',
					'${menuTop.get(1).menu_name}',
					'${menuTop.get(2).menu_name}',
					'${menuTop.get(3).menu_name}',
					'${menuTop.get(4).menu_name}',
					'${menuTop.get(5).menu_name}',
					'${menuTop.get(6).menu_name}',
					'${menuTop.get(7).menu_name}',
					'${menuTop.get(8).menu_name}',
					'${menuTop.get(9).menu_name}',
					'기타'
				], 
				datasets : [ { 
					data : [ 
						${menuTop.get(0).like_count}, 
						${menuTop.get(1).like_count}, 
						${menuTop.get(2).like_count}, 
						${menuTop.get(3).like_count}, 
						${menuTop.get(4).like_count}, 
						${menuTop.get(5).like_count}, 
						${menuTop.get(6).like_count}, 
						${menuTop.get(7).like_count}, 
						${menuTop.get(8).like_count}, 
						${menuTop.get(9).like_count},
						${etc}
					], 
					backgroundColor: [ 
						"rgb(245, 172, 176)",
						"rgb(245, 171, 213)",
						"rgb(231, 171, 245)",
						"rgb(178, 166, 245)",
						"rgb(171, 201, 246)",
						"rgb(172, 245, 197)",
						"rgb(245, 241, 167)",
						"rgb(173, 246, 172)",
						"rgb(202, 245, 172)",
						"rgb(245, 202, 171)"
					], 
					borderWidth: 0, 
					label: "Dataset 1" 
					}] 
		};
	
		
		
		var ctx = document.getElementById("goodMenuStatus");
		var config = {
		  type: 'doughnut',
		  data: data,
		  options: {
		    reponsive: true,
		    legend: {
		    	display:true,
		    	position:'right',
		    	label: { 
			    	mode:"label", 
			    	fontSize: 10, 
			    },
		    },
		    title:{
		    	display: true,
		    },
		    animation:{
		    	animateScale: true,
		    	animateRotate: true
		    }
		  }
		};
		var myDoughnutChart = new Chart(ctx, config);
	};

	$( document ).ready(function() {
	});

	var menu_data;
	var iden;
	
	function getMenuFunction(type){
		
		var option;
		
		if(type == "M") {
			iden = "_modify";
			$("#addRow_m").empty();
		} else if(type == "I") {
			iden = "_insert";
			$("#addRow_i").empty();
		}
		
		$.ajax({
			type : "POST",
			url : "<c:url value='/owner/menuPlanner'/>",
			success : function(data){
				
				if(data.category.length > 0) {
					menu_data = data.category;
					
					$('#rice' + iden).empty();
					$('#soup' + iden).empty();
					$('#sideDish' + iden + '0').empty();
					
					option = "<option value=''>메뉴를 선택해주세요</option>";
					$('#rice' + iden).append(option);
					$('#soup' + iden).append(option);
					$('#sideDish' + iden + '0').append(option);
					
					if(today_menu_data_total != "" && today_menu_data_total != null) {
						var sideArr = today_menu_data_sideDish.split("/");
						
						for(var i = 0; i < data.category.length; i++) {
							
							if(data.category[i].menu_type == "밥") {
								option = $("<option value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name + "</option>");
								$('#rice' + iden).append(option);
								$('#rice' + iden).val(today_menu_data_rice).prop("selected",true);
							}
							
							if(data.category[i].menu_type == "국") {
								option = $("<option value='" + data.category[i].menu_name + "'>" + data.category[i].menu_name + "</option>");
								$('#soup' + iden).append(option);
								$('#soup' + iden).val(today_menu_data_soup).prop("selected",true);
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
								<c:forTokens var="item" items="${todayMenu.side_dish}" delims="/">
								<tr>
									<td class="table-success" style="text-align: center; padding:0.1em; font-size: 1.5em;">${item}</td>
								</tr>
								</c:forTokens>
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
			<div class="col-xl-6">
				<div class="card mb-4">
                    <div class="card-header">
                        <i class="fas fa-thumbs-up me-1"></i>
                    	좋아요 메뉴 TOP 10
					</div>
                	<div class="card-body" ><canvas id="goodMenuStatus" width="100" height="100"></canvas>
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
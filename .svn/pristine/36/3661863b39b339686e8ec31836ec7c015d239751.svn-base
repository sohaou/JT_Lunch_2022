<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file = "/WEB-INF/layout/main/mainHeader.jsp" %>

	<head>
	<title>예약 페이지</title>
	<meta charset="utf-8">
		<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/resources/css/admin/styles.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/resources/css/main/selectbox/selectbox.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/resources/css/main/label.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/resources/css/main//addMemberTable.css" rel="stylesheet" />
		<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
	</head>
	
	<body style="overflow: auto;">
	    <div class="container-fluid px-4">
			<h1 style="text-align:center; margin-top:40px; margin-bottom:10px">예약</h1> 
	        <ol class="breadcrumb mb-4">
	            <li class="breadcrumb-item active"></li>
	        </ol>
		</div>
		<div class="card mb-4">
	    	<div class="card-header">
	        	<i class="fas fa-table me-1"></i>
	            	사용자 조회
			</div>
			<div style="margin-top:20px;">
				<table class="bluetop" align="center">
	            	<colgroup>
	                	<col width="160px">
	                	<col width="100px">
	                	<col width="100px">
	                </colgroup>
	                <thead>
						<tr>
					    	<th>부서</th>
					        <th>팀</th>
					        <th>이름</th>
						</tr>
					</thead>
	            	<tbody id="addMember">
	            		<tr>
	            			<td colspan="3">인원을 선택해주세요</td>
	            		</tr>
	            	</tbody>
				</table>
			</div>
			<div class="card-body">
				<form name="searchTeam" align="center">
					<table style="margin-left: auto; margin-right: auto; margin-bottom: 20px">
						<tr>
							<td>
								<select class="select" id="department" name="department" style="font-size:8pt;" onchange="select()">
									<option value="">부서를 선택하세요</option>
								</select>
							</td>
							<td rowspan="2">
								<input type="button" onclick="searchMember()" value="선택" style="margin-left:10px;"/>
							</td>
						</tr>
						<tr>
							<td>
								<select class="select" id="team" name="team" style="font-size:8pt;">
									<option value="">팀을 선택하세요</option>
								</select>
							</td>
						</tr>
					</table>
				</form>
				<form name="reserveForm">
					<table id="datatablesSimple" style="text-align: center !important; white-space : nowrap; text-overflow: ellipsis;">
	                	<colgroup>
	                		<col width="60px">
	                		<col width="100px">
	                		<col width="100px">
	                		<col width="100px">
	                	</colgroup>
	                    <thead>
						     <tr>
						     	<th class='text-center'></th>
				            	<th class='text-center'>부서</th>
				           		<th class='text-center'>팀</th>
				           		<th class='text-center'>이름</th>
				            </tr>
	                    </thead>
	                    <tbody id=body>
	                	</tbody>
					</table>
						<div style="margin-top:20px; text-align:center;">
							<c:choose>
							<c:when test="${firstTime != 0}">
								<label class="time_obj">
				              		<input type="radio" id="timebutton_1" name="time" value="1" onclick="clickTime(this);">
				              		<span>오전 11:40</span>
				            	</label>
				            </c:when>
				            <c:when test="${firstTime == 0}">
								<label class="time_obj_disabled">
				              		<input type="radio" id="timebutton_1" name="time" value="1" disabled="disabled">
				              		<span>오전 11:40</span>
				            	</label>
				            </c:when>
				            </c:choose>
				            <c:choose>
				            	<c:when test="${secondTime != 0}">    						   
			                		<label class="time_obj">
										<input type="radio" id="timebutton_2" name="time" value="2" onclick="clickTime(this);">
			                 			<span>오후 12:00</span>
			                 		</label>
			                 	</c:when>
			                 	<c:when test="${secondTime == 0}">
			                 		<label class="time_obj_disabled">
										<input type="radio" id="timebutton_2" name="time" value="2" disabled="disabled">
			                 			<span>오후 12:00</span>
			                 		</label>
			                 	</c:when>
			                 </c:choose>
			                 <c:choose>
			                 	<c:when test="${thirdTime != 0}">
			                		 <label class="time_obj">
			                 			<input type="radio" id="timebutton_3" name="time" value="3" onclick="clickTime(this);">
			                 			<span>오후 12:20</span>
			                 		</label>
			                 	</c:when>
			                 	<c:when test="${thirdTime == 0}">
			                 		<label class="time_obj_disabled">
			                 			<input type="radio" id="timebutton_3" name="time" value="3" disabled="disabled">
			                 			<span>오후 12:20</span>
			                 		</label>
			                 	</c:when>
			                 </c:choose>
			                 <c:choose>
			                 	<c:when test="${fourthTime != 0}">
			                 		<label class="time_obj">
			                 			<input type="radio" id="timebutton_4" name="time" value="4" onclick="clickTime(this);">
			                 			<span>오후 12:40</span>
			                 		</label>
			                 	</c:when>
			                 	<c:when test="${fourthTime == 0}">
			                 		<label class="time_obj_disabled">
			                 			<input type="radio" id="timebutton_4" name="time" value="4" disabled="disabled">
			                 			<span>오후 12:40</span>
			                 		</label>
			                 	</c:when>
			                 </c:choose>
			                 <table align="center">
			                 	<tbody id="timebody">
			                 		<tr>
			                 			<td style="padding:15px; font-size:20px;"> 예약 가능한 테이블 수 : </td>
			                 		</tr>
			                 	</tbody>
			                 </table>
		                 </div>
		                 <div style="text-align: center; margin-top:10px;">
		                 	<button type="button" onclick="reserve()">예약하기</button>
		                 </div>
		            </form>
	            </div>
	        </div>
	</body>
	<script>
	var datatablesSimple = document.getElementById('datatablesSimple');
	var dataTable;
	
	window.addEventListener('DOMContentLoaded', event => {
	
	dataTable = new simpleDatatables.DataTable(datatablesSimple,
		    {
			    sortable : false,
			    searching : true,
			    paging : true,
			    perPage : 5,
			    labels: {
				    placeholder: "검색키워드 입력",
				    perPage: "",
				    noRows: "조회결과가 없습니다.",
				    info: "",
				}
			});
	});
	
	$( document ).ready(function() {
		searchMember();
		console.log('${fourthTime}');
		
	});
	
var department = JSON.parse('${Department}');
var team = JSON.parse('${Team}');

// ================================ 부 서  ===================================//
// 부서 : 소방재난서비스사업부
var depart1Arr = new Array();
var depart1Obj = new Object();

// 부서 : 사업기획부
var depart2Arr = new Array();
var depart2Obj = new Object();

// 부서 : 컨설팅 사업부
var depart3Arr = new Array();
var depart3Obj = new Object();

//=========================================================================//

for(var i=0; i<department.length; i++){
	if(department[i].department=="소방재난서비스사업부"){
		depart1Obj = new Object();
		depart1Obj.department = department[i].department;
		depart1Arr.push(depart1Obj);
	}
	else if(department[i].department=="사업기획부"){
		depart2Obj = new Object();
		depart2Obj.department = department[i].department;
		depart2Arr.push(depart2Obj);
	}
	else if(department[i].department=="컨설팅사업부"){
		depart3Obj = new Object();
		depart3Obj.department = department[i].department;
		depart3Arr.push(depart3Obj);
	}
}

var departmentSelect = $("#department");

for(var i=0; i<depart1Arr.length; i++){
	departmentSelect.append("<option value="+depart1Arr[i].department+">"
			+ depart1Arr[i].department+"</option>");
	}

for(var i=0; i<depart2Arr.length; i++){
	departmentSelect.append("<option value="+depart2Arr[i].department+">"
			+ depart2Arr[i].department+"</option>");
	}
for(var i=0; i<depart3Arr.length; i++){
	departmentSelect.append("<option value="+depart3Arr[i].department+">"
			+ depart3Arr[i].department+"</option>");
	}

//=====================================================================================//


//========================================팀===========================================//

function select(){
//소방재난서비스사업부
var team1Arr = new Array();
var team1Obj = new Object();

//사업기획
var team2Arr = new Array();
var team2Obj = new Object();

//컨설팅
var team3Arr = new Array();
var team3Obj = new Object();

for(var i=0; i<team.length; i++){
	if(team[i].department=="소방재난서비스사업부"){
		team1Obj = new Object();
		team1Obj.department = team[i].department;
		team1Obj.team = team[i].team;
		team1Arr.push(team1Obj);
	}
	else if(team[i].department=="사업기획부"){
		team2Obj = new Object();
		team2Obj.department = team[i].department;
		team2Obj.team = team[i].team;
		team2Arr.push(team2Obj);
	}
	else if(team[i].department=="컨설팅사업부"){
		team3Obj = new Object();
		team3Obj.department = team[i].department;
		team3Obj.team = team[i].team;
		team3Arr.push(team3Obj);
	}
}

var selectDepartment = $('#department').val();
var selectTeam;

if(selectDepartment == "소방재난서비스사업부"){
	var selectTeam = team1Arr;
}

if(selectDepartment == "사업기획부"){
	var selectTeam = team2Arr;
}

if(selectDepartment == "컨설팅기획부"){
	var selectTeam = team3Arr;
}

$('#team').empty();

if(selectDepartment == "사업기획부"){
	$("#team").attr("disabled", false);
}

else if(selectDepartment == "소방재난서비스사업부"){
	$("#team").attr("disabled", false);
}

else if(selectDepartment == "컨설팅사업부"){
	$("#team").attr("disabled", true);
}

for(var i=0; i<selectTeam.length; i++){
	var option = $("<option value="+selectTeam[i].team+">"+ selectTeam[i].team+"</option>");
	$('#team').append(option);
	}
}

//=====================================================================================//

// ================================ 선택한 소속 부서, 팀 조회 list ===========================//

var checkArr = new Array();

function checkBoxValue(el) {
	
	if(checkArr.indexOf(el.value) < 0) { //값이 없으면 -1 반환
		if(checkArr.length < 3){
			checkArr.push(el.value);
		}else {
			$(el).prop("checked", false);
			alert("최대 3명까지 선택할 수 있습니다.");
			return false;
		}
	} else { 
		var index = checkArr.indexOf(el.value);
		checkArr.splice(index,1);
	}
	
	var str="";
	
	if(checkArr.length > 0){	
		$.ajax({
			type: "POST",
			url: "<c:url value='/user/addMember'/>",
			data : {"check" : checkArr},
			success: function(result)
			{
				if(result.checkUser != null){
					for(var i=0; i<result.checkUser.length; i++){
						$('#addMember').empty();
						str+="<tr>";
						str+="<td>"+result.checkUser[i].department+"</td>";
						str+="<td>"+result.checkUser[i].team+"</td>";
						str+="<td>"+result.checkUser[i].user_name+"</td>";
						str+="</tr>";
						$('#addMember').append(str);
					}
				}
			}
		});
	}else{
		$('#addMember').empty();
		str+="<tr>";
		str+="<td colspan='3'> 인원을 선택해주세요 </td>";
		str+="</tr>";
		$('#addMember').append(str);
	}	
}
	

function searchMember(){
	var str="";
	var userArr = new Array();
	$.ajax({
		type: "POST",
		url : "<c:url value='/user/selectMember'/>",
		data : {"department" : $("#department").val(), "team" : $("#team").val()},
		success: function(result)
		{
			dataTable.destroy();
			$('#body').empty();
			
			if(result.selectUser != null){
				for(var i=0; i < result.selectUser.length; i++){
					str+="<tr align='center'>";
					str+="<td><input type='checkbox' id='" +result.selectUser[i].user_id + "' name='checkbox' onclick='javascript:checkBoxValue(this);' value="+result.selectUser[i].user_id+"></td>";
					str+="<td>"+result.selectUser[i].department+"</td>";
					str+="<td>"+result.selectUser[i].team+"</td>";
					str+="<td>"+result.selectUser[i].user_name+"</td>";
					str+="</tr>";
					userArr.push(result.selectUser[i].user_id);
				}
				$('#body').append(str);
				dataTable.init();
				
				if(checkArr.length > 0) {
					for(var j=0; j<checkArr.length; j++){
						$("input:checkbox[id="+checkArr[j]+"]").prop("checked", true);
					}	
				}
			}
			
		},error:function(request, status, error)
		{
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	})
}

function clickTime(ettl){
	var str="";
	var click_id = ettl.id;
	console.log(click_id);
	$.ajax({
		type: "POST",
		url: "<c:url value='/user/selectTableTime'/>",
		data : {"no" : $("input:radio[name=time]:checked").val()},
		success: function(result)
		{
			$('#timebody').empty();
			str+="<tr>";
			str+="<td style='font-size:20px; padding:15px;'> 예약 가능 테이블 수 : "+result.tableTime.count+"</td>";
			str+="</tr>";
			$('#timebody').append(str);
		}
	});
}
	
	function reserve(){
		var dataArray = new Array();
		
		$.ajax({
			type: "GET",
			url: "<c:url value='/user/reserve'/>",
			data : { "user_id" : checkArr, "no" : $("input:radio[name=time]:checked").val()},
			success : function(result)
				{
					if(result.rst_msg == "000") {
						alert("예약완료");
						location.href="/user/menuplanner";
					} else {
						alert("시스템 오류 관리자에게 문의해주세요.");
					}
				},
			error : function(request,status,error) 
				{
					alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
				}
			})
		}
</script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
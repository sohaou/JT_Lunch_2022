<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/main/mainHeader.jsp" %>
<meta charset="utf-8">
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta name="_csrf" content="${_csrf.token}"/>
<script type="text/javascript">
	var myDepartment = '${myinfo.department}';
	var myTeam = '${myinfo.team}';
	var valueArr = JSON.parse('${valueArr}');
	
	var department_data = JSON.parse('${Department}');
	var team_data = JSON.parse('${Team}');

	
	function checkTeam(){
		var selectDepartment = $('#department').val();
		
		$('#team').empty();
		var check = 0;
		for(var j = 0; j < department_data.length; j++){
			for(var i = 0; i < team_data.length; i++){
				if(selectDepartment == valueArr[j][0]){
					if(valueArr[j][i + 1] != null && valueArr[j][i + 1] != ""){
						check++;
						/*  if(valueArr[j][i] == myTeam){
							$("select[name = 'team']").append("<option selected>"+ valueArr[j][i] + "</option>"); 
						} else{  */
							$("select[name = 'team']").append("<option>"+ valueArr[j][i + 1] + "</option>"); 
						//}
					} 
				}
			}
		}
		if(check > 0){
			$("#team").attr("disabled", false);
		}else if(check == 0){
			$("#team").attr("disabled", true);
		}
		
	}
	window.onload = function() {
		for(var i = 0; i < department_data.length; i++){
			 $("select[name = 'department']").append("<option>"+ department_data[i].department + "</option>"); 
		}
		$('#department').val(myDepartment).prop("selected",true);
		checkTeam();
		$('#team').val(myTeam).prop("selected",true);
	}
	function check_input() {
		if (document.myInfoForm.user_pw.value == ""){
			alert("비밀번호를 입력해주세요!");
			document.myInfoForm.user_pw.focus();
			return;
		}
		else if (!document.myInfoForm.user_tel.value){
			alert("전화번호를 입력해주세요!");
			document.myInfoForm.user_tel.focus();
			return;
		}
		else if (!document.myInfoForm.department.value){
			alert("부서를 선택해주세요!");
			document.myInfoForm.department.focus();
			return;
		}
		if (document.myInfoForm.user_pw.value != document.myInfoForm.pw_check.value){
			alert("비밀번호가 일치하지 않습니다!");
			document.myInfoForm.pw_check.focus();
			return;
		}
		$.ajax({
			url: "<c:url value='/user/myInfoUpdate'/>",
		    type: "POST",
		    data : $("#myInfoForm").serialize(),
		    success : function(data){
				alert(data);
			},
			complete : function(data){
				location.href = '/user/menuplanner';
			},
			error:function(request,status,error)
		    {
		    	alert(error);
			}
		});
	}
</script>
<main>
	<div class="container-fluid px-4">
		<h1 class="mt-4">내 정보</h1>
	</div>
	<div class="card-body">
		<form id="myInfoForm" name="myInfoForm"  role="form" method="post" autocomplete="off">
			<table style="width:100%; margin-top:30px;">
				<tr>
					<td style="padding:15px;">아이디</td>
					<td><input type="hidden" id="user_id" name="user_id" value="${myinfo.user_id}">${myinfo.user_id }</td>
				</tr>
				<tr>
					<td style="padding:15px;">비밀번호 <font style="color:red; font-weight: bold;">*</font></td>
					<td><input type="password" id="user_pw" name="user_pw" style="height:35px; font-size:16px; width:165px;" maxlength="15"></td>
				</tr>
				<tr>
					<td style="padding:15px;">비밀번호 확인 <font style="color:red; font-weight: bold;">*</font></td>
					<td><input type="password" id="pw_check" name="pw_check" style="height:35px; font-size:16px; width:165px;" maxlength="15"></td>
				</tr>
				<tr>
					<td style="padding:15px;">이름  <font style="color:red; font-weight: bold;">*</font></td>
					<td><input type="text" id="user_name" name="user_name" value="${myinfo.user_name}" style="height:35px; font-size:16px; width:165px;"></td>
				</tr>
				<tr>
					<td style="padding:15px;">휴대전화  <font style="color:red; font-weight: bold;">*</font></td>
					<td><input type="text" id="user_tel" name="user_tel" value="${myinfo.user_tel}" maxlength="11" style="height:35px; font-size:16px; width:165px;"></td>
				</tr>
				<tr>
					<td style="padding:15px;">부서 <font style="color:red; font-weight: bold;">*</font></td>
					<td>
						<select name="department" id="department" class="dataTable-selector" onchange="checkTeam()" style="height:35px; font-size:14px; width:90%;">
								<option value="">선택</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="padding:15px;">팀 <font style="color:red; font-weight: bold;">*</font></td>
					<td>
						<select name="team" id="team" class="dataTable-selector" style="height:35px; font-size:14px; width:90%;">
						</select>
					</td>
				</tr>
			</table>
		</form>
		<div style="text-align:center; margin-top:20px;">
			<button type="button" class="btn btn-primary" style="width:80px; margin-right:10px;" onclick="check_input()"> 수 정 </button>
			<button type="button" class="btn btn-secondary" style="width:80px;" onclick="location.href='/user/menuplanner'"> 닫 기 </button>
		</div>	
	</div>
</main>
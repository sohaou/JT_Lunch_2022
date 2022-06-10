<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <meta name="_csrf_header" th:content="${_csrf.headerName}"/>
	    <meta name="_csrf" th:content="${_csrf.token}"/>
        <title>조인트리 중식 운영 시스템</title>
        
        <!-- jQuery 스크립트 -->
    	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- Core theme JS-->
        <script src="resources/js/main/scripts.js"></script>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Google fonts-->
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" />
        <!-- Simple line icons-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/simple-line-icons/2.5.5/css/simple-line-icons.min.css"  />
        <!-- Favicon -->
        <link rel="icon" type="images/x-icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" />
        <!-- Font Awesome icons (free version) -->
        <script src="https://use.fontawesome.com/releases/v5.15.3/js/all.js" crossorigin="anonymous"></script>
        <!-- Core theme CSS (includes Bootstrap)-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main/styles.css"  />
        
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main/login/login.css?ver=1">
        
        <style type="text/css">
        a > svg {
        margin-top: 15px;
        }
        
        .idForm {
        margin : 16px;
        }
        
        .passForm {
        margin : 16px;
        }
        
        .selectForm{
        margin :5px;
        }
        
        .checkIDbtn{
        background: linear-gradient(125deg,#ec81c6,#e76f5c,#ec81c6);
         color:white;
		  font-weight: bold;
		  border:none;
		  cursor:pointer;
		  transition: 0.4s;
		  display:inline;
		  font-size:14px;
		   height:30px;
        }
        </style>
        
    </head>
    
    <script>
    var idck = 0;
    
    	function checkJoin(){
    		var form = document.joinForm;
    		if(form.user_id.value == ""){
    			alert("아이디를 입력해주세요.");
    			form.user_id.focus();
    			return false;
    		}
    		if (form.user_pw.value == ""){
    			alert("비밀번호를 입력해주세요.");
    			form.user_pw.focus();
    			return false;
    		}
    		if(form.user_name.value == ""){
    			alert("이름을 입력해주세요.");
    			form.user_name.focus();
    			return false;
    		}
    		if(form.user_tel.value == ""){
    			alert("전화번호를 입력해주세요.");
    			form.user_tel.focus();
    			return false;
    		}
    		
 			if(form.department.value == ""){
 				alert("부서 선택을 해주세요.");
 				form.department.focus();
 				return false;
 			}
 			if(form.team.value == "" && $("#department").val() != "컨설팅사업부"){
 				alert("팀 선택을 해주세요.");
 				form.team.focus();
 				return false;
 			}
 			if(idck == 0){
    			alert("중복확인을 눌러주세요.");
 				return false;
    		}
    		if(form.value != "" && idck == 1){
    	    	alert("회원가입이 완료되었습니다.");
    	    	location.href='/loginform';
    		}
    		
    		form.submit();
    	}
    	 	
    	function checkTeam(){
    		var a = ['사업기획팀'];
    		var b = ['업무기획팀', '빅데이터팀', '응용개발팀', '기술총괄팀', 'PJT수행팀'];
    		
    		console.log(a);
    		console.log(b);
    		var selectDepartment = $("#department").val();
    		
    		var changeTeam;
    		
    		if(selectDepartment == "사업기획부"){
    			changeTeam = a;
    			$("#team").attr("disabled", false);
    		}
    		
    		else if(selectDepartment == "소방재난서비스사업부"){
    			changeTeam = b;
    			$("#team").attr("disabled", false);
    		}
    		
    		else if(selectDepartment == "컨설팅사업부") {
    			$("#team").attr("disabled", true);
    		}
    		
    		$('#team').empty();
    		
    		for(var count = 0; count<changeTeam.length; count++){
    			var option = $("<option>"+changeTeam[count]+"</option>");
    			$('#team').append(option);
    		}
    	}
    	
    	function checkID(){
    		var form = document.joinForm;
    		
    		$.ajax({
    			type: "POST",
    			url : "<c:url value='/checkID'/>",
    			data : {"user_id" : $("#user_id").val()},
    			success: function(result)
    			{
    				console.log($("#user_id").val())
    				if(result.checkID == null){
    					alert("사용가능한 아이디입니다!");
    					idck = 1;
    					form.user_pw.focus();
    	 				return false;
    					
    				}
    				if(result.checkID != null){
    					alert("중복된 아이디입니다!");
    					form.user_id.focus();
    	 				return false;
    				}
    			}
    		})
    	}
    	
    	function changeID() {
    		idck = 0;
    	}
    </script>
    <body style="background-color: darkseagreen; overflow: auto;">
        <!-- Header-->
        <header class="masthead d-flex align-items-center">
			
        </header>
        <form name="joinForm" action='<c:url value="/register"/>' method="post" class="joinForm">
      			<h2>회원가입</h2>
      			<div class="selectForm" style="border-bottom : none;">
					<select class="form-select" id="department" name="department" onchange="checkTeam()">
					<option value="">부서를 선택하세요</option>
					<option>사업기획부</option>
					<option>컨설팅사업부</option>
					<option>소방재난서비스사업부</option>
					</select>
				</div>
				<div class="selectForm" style="border-bottom : none;">
					<select class="form-select" id="team" name="team">
						<option value="">팀을 선택하세요</option>
					</select>
				</div>
        		<div class="idForm" style="text-align: left;">
        			<input type="text" class="id" name="user_id" id="user_id" placeholder="ID" style="width: 70%" onkeydown="javasciprt:changeID();">
        			<input type="button" class="checkIDbtn" onclick="javascript:checkID()" value="중복확인" style="float:right; width:25%"/>
        		</div>
        		<div class="passForm">
        			<input type="password" class="pw" name="user_pw" placeholder="PW">
        		</div>
				<div class="idForm">
					<input type="text" class="id" name = "user_name" placeholder="NAME">
				</div>
				<div class="idForm">
					<input type="text" class="id" name ="user_tel" placeholder="TEL (- 제외)" maxlength="11">
				</div>
        	<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
        	<input type="button" class="btn2 signUp" onclick="checkJoin()" value="Join"/>
	
		</form>
    </body>
</html>
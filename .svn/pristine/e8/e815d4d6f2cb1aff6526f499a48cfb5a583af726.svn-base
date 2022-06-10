<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />	
        <title>조인트리 중식 운영 시스템</title>
        
        <!-- jQuery 스크립트 -->
    	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- Core theme JS-->
        <script src="${pageContext.request.contextPath}/resources/js/main/scripts.js"></script>
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
        
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main/login/login.css">
        
        <style type="text/css">
	        a > svg {
	        margin-top: 15px;
	        }
        </style>
    </head>
    
    <script type="text/javascript">
    
	    function LoginPageGo() {
	    	location.href="/registerform";
	    }
	    
	    function LoginGo() {
	    	$("#loginForm").submit();
	    	return false;
	    }
	    
    </script>
    <body style="background-color: darkseagreen;">
        <!-- Header-->
        <header class="masthead d-flex align-items-center">
			
        </header>
	        <form action='<c:url value="/user/login"/>' method="POST" class="loginForm" id="loginForm" name="loginForm">
	      			<h2>로그인</h2>
	        			<div class="idForm">
	        			<input type="text" class="id" name="id" value="${id}" placeholder="ID">
	        		</div>
	        	<div class="passForm">
	        		<input type="password" class="pw" name="pw" value="${pw}" placeholder="PW">
	        	</div>
	        	<c:if test="${LoginFailMessage!=null}">
						<script type="text/javascript">
						alert('<c:out value="${LoginFailMessage}"/>');
						</script>
				</c:if>
				<!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>  -->
				<div style="padding-bottom: 20px;"><input type="checkbox" name="remember-me">자동 로그인</div>
				<button type="button" class="btn signIn" onclick="LoginGo();">Login</button>
				<button type="button" class="btn signUp" onclick="LoginPageGo();">Signup</button>
			</form>
    </body>
</html>
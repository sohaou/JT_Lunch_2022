<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %><%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
  	<title>식단표</title>
    <meta charset="utf-8">
    <!-- ><meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta name="_csrf" content="${_csrf.token}"/> -->
    
<%@ include file = "/WEB-INF/layout/main/mainHeader.jsp" %>

        <!-- 테이블 resource -->
        <link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main/table/style.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <style type="text/css">
        a > svg {
        margin-top: 15px;
        }
        </style>
        
      <!--   <script>
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(function() {
            $(document).ajaxSend(function(e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
        });
        </script>  -->
        
        <style>
        #btnSubmit{
        	width:100px;
        	margin:auto;
        	display:block;
        	text-align:center;
        }
        </style>
</head>  

	<body style="overflow: auto;">
		
	<div style="text-align:center;">
		<h2 class="heading-section" style="margin-top:10px;">금일 식단표</h2>
		<c:if test = "${rdto !=null && qrdto == null}">
			<font style="size:16px; color: red; font-weight: bold;" >금일 예약 하셨습니다.</font>
		</c:if>
		<c:if test = "${qrdto != null}">
			<font style="size:16px; color: red; font-weight: bold;" >금일 식사 확인 하셨습니다.</font>
		</c:if>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="table-wrap">
						<form name = "print">
						<table class="table">
						  <tbody id = body>
						  </tbody>
						</table>
						</form>
					</div>
				</div>
			</div>
		</div>
		<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
		<c:if test = "${rdto == null && qrdto == null}">
		<div style="text-align : center;" >
			<a href ='<c:url value="/user/reservePage"/>'><button style="font-size:25px;">예약</button></a>
		</div>
		</c:if>
		
  	    <script src='<c:url value="/resources/js/main/table/jquery.min.js"/>'></script>
  		<script src='<c:url value="/resources/js/main/table/popper.js"/>'></script>
 		<script src='<c:url value="/resources/js/main/table/bootstrap.min.js"/>'></script>
  		<script src='<c:url value="/resources/js/main/table/main.js"/>'></script>
  		 
<script type="text/javascript">
		$(document).ready(function() {
		    print();
		});
		
		 function print() {
	        	var str="";
	        	
	        	$.ajax({
	        		type: "POST",
	        		url: "<c:url value='/user/print'/>",
	        		contentType: "application/json; charset=utf-8",
	        		success : function(result)
	        		{        			
	        			console.log(result);

	                	if(result.menuplanner != null){
	                		
		                var sideDish = result.menuplanner.side_dish;
		                var splitSideDish = sideDish.split('/');
		                	
	        			str+="<tr align='center' class='thead-dark' >";
	        			str+="<th>"+result.menuplanner.today_date+"</th>";
	        			str+="</tr>";
	    				str+="<tr class='alert' role='alert' align='center'>";
	    				str+="<th scope ='row'>"+result.menuplanner.steamed_rice+"</th>";
	    				str+="</tr>";
	    				str+="<tr class='alert' role='alert' align='center'>";
	    				str+="<th scope='row'>"+result.menuplanner.soup+"</th>";
	    				str+="</tr>";
	    				for(var i=0; i<splitSideDish.length; i++){ 
	        			str+="<tr class='alert' role='alert' align='center'>";
	    				str+="<th scope='row'>"+splitSideDish[i]+"</th>";
	    				str+="</tr>";
	    					}
	    				$('#body').append(str);
	                	}
	              
	                	if(result.menuplanner == null){
	                	 str+="<tr class='alert' role='alert' align='center'>";
						 str+="<th>";
						 str+="<font style='size:18px; color: black; font-weight: bold;' >금일 식단이 등록되어있지 않습니다.</font>";
						 str+="</th>";
		        		 str+="</tr>";
						 $('#body').append(str);
	                	}
	                	
	    				
	        		},error:function(request,status,error)
					{
	        	    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        	    }
	        	})
	        } 

</script>	
	</body>
</html>


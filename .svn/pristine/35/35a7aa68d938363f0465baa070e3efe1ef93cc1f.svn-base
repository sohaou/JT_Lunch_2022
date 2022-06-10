<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/main/mainHeader.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta charset="utf-8">
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta name="_csrf" content="${_csrf.token}"/>

<script type="text/javascript">
	function check_input() {
		if (document.requestModiForm.req_title.value == ""){
			alert("제목을 입력해주세요!");
			document.requestModiForm.req_title.focus();
			return;
		}
		else if (!document.requestModiForm.req_content.value){
			alert("내용을 입력해주세요!");
			document.requestModiForm.req_content.focus();
			return;
		}
		$.ajax({
			url: "<c:url value='/user/requestUpdate'/>",
		    type: "POST",
		    data : $("#requestModiForm").serialize(),
		    success : function(data){
				alert(data);
			},
			complete : function(data){
				location.href = '/user/request';
			},
			error:function(request,status,error)
		    {
		    	alert(error);
			}
		});
	}		
</script>
<style>
	.td{width:400px;}
</style>
<main>
	<div class="container-fluid px-4">
		<h1 class="mt-4">요청사항</h1>
		<ol class="breadcrumb mb-4">
			<li class="breadcrumb-item active">요청사항 수정</li>
		</ol>
		<form id="requestModiForm" name="requestModiForm" role="form" method="post" autocomplete="off" >
		<input type="hidden" name="req_id" value="${requestDetail.req_id}"/>
			<table style="width:100%;">	
				<tr>
					<th style="width:25%; padding:5px;">제목 </th>
					<td><input type="text" name="req_title" style="width:100%; border:1; overflow:visible; text-overflow:ellipsis; padding:5px;" value="${requestDetail.req_title}"/></td>
				</tr>
				<tr>
					<th style="width:25%; padding:5px;">작성자 </th>
					<td>${requestDetail.req_writer}</td>
				</tr>
				<tr>
					<th style="width:25%; padding:10px; height:150px; padding:5px;">내용</th>
					<td><textarea style="width:100%; border:1; overflow:visible; text-overflow:ellipsis;" rows=10 name="req_content">${requestDetail.req_content}</textarea></td>
				</tr>
				<tr>
					<th style="width:25%; padding:5px;">등록일</th>
					<td>${requestDetail.up_date}</td>
				</tr>
			</table>
		</form>
		<div style="float:right;">
			<button type="button" class="btn btn-primary" style="width:70px;" onclick="check_input()"> 수 정 </button>
			<button type="button" class="btn btn-secondary" style="width:70px;" onclick="location.href='/user/request'"> 닫 기 </button>
		</div>	
	</div>
</main>
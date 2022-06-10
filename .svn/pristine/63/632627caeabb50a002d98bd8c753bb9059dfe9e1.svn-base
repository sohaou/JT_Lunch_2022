<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/ownerHeader.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta charset="utf-8">
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta name="_csrf" content="${_csrf.token}"/>

<script type="text/javascript">
	function check_input() {
		if (document.noticeModiForm.title.value == ""){
			alert("제목을 입력해주세요!");
			document.noticeModiForm.title.focus();
			return;
		}
		else if (!document.noticeModiForm.content.value){
			alert("내용을 입력해주세요!");
			document.noticeModiForm.content.focus();
			return;
		}
		$.ajax({
			url: "<c:url value='/owner/noticeUpdate'/>",
		    type: "POST",
		    data : $("#noticeModiForm").serialize(),
		    /*beforeSend: function (xhr) {
		    	xhr.setRequestHeader(header, token);
		           
			},*/
		    success : function(data){
				alert(data);
			},
			complete : function(data){
				location.href = '/owner/notice';
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
		<h1 class="mt-4">공지사항</h1>
		<ol class="breadcrumb mb-4">
			<li class="breadcrumb-item">식당운영자</li>
			<li class="breadcrumb-item active">공지사항 수정</li>
		</ol>
		<form id="noticeModiForm" name="noticeModiForm" role="form" method="post" autocomplete="off" >
		<input type="hidden" name="notice_id" value="${noticeDetail.notice_id}"/>
			<table style="width:100%;">	
				<tr>
					<th style="width:25%; padding:5px;">제목 </th>
					<td><input type="text" name="title" style="width:100%; border:1; overflow:visible; text-overflow:ellipsis; padding:5px;" value="${noticeDetail.title}"/></td>
				</tr>
				<tr>
					<th style="width:25%; padding:5px;">작성자 </th>
					<td>${noticeDetail.writer}</td>
				</tr>
				<tr>
					<th style="width:25%; padding:5px;">조회수 </th>
					<td>${noticeDetail.user_check}</td>
				</tr>
				<tr>
					<th style="width:25%; padding:10px; height:150px; padding:5px;">내용</th>
					<td><textarea style="width:100%; border:1; overflow:visible; text-overflow:ellipsis;" rows=10 name="content">${noticeDetail.content}</textarea></td>
				</tr>
				<tr>
					<th style="width:25%; padding:5px;">등록일</th>
					<td>${noticeDetail.up_date}</td>
				</tr>
				<c:if test="${noticeDetail.edit_time != null}"> 
					<tr>
						<th style="width:25%; padding:5px;">최근수정일</th>
						<td>${noticeDetail.edit_time}</td>
					</tr>
					<tr>
						<th style="width:25%; padding:5px;">수정자</th>
						<td>${noticeDetail.editor}</td>
					</tr>
				</c:if>
			</table>
		</form>
		<div style="float:right;">
			<button type="button" class="btn btn-primary" style="width:70px;" onclick="check_input();"> 수 정 </button>
			<button type="button" class="btn btn-secondary" style="width:70px;" onclick="location.href='/owner/notice'"> 닫 기 </button>
		</div>	
	</div>
</main>
<%@ include file="/WEB-INF/layout/adminFooter.jspf" %>
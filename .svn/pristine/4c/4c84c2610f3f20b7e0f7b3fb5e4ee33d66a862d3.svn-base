<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/ownerHeader.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta charset="utf-8">
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta name="_csrf" content="${_csrf.token}"/>
<style>
	.td{width:400px;}
</style>
<script type="text/javascript">
	function delete_notice(str) {
		var notice_id = str;
		var deleteStr=confirm('정말 삭제하시겠습니까?');
		
		if(deleteStr){
			$.ajax({
				url: "<c:url value='/owner/noticeDelete'/>",
			    type: "POST",
			    data : {id : notice_id},
			    success : function(data){
					alert(data);
				},
				complete : function(data){
					location.href='/owner/notice';
				},
				error:function(request,status,error)
			    {
			    	alert(error);
				}
			});
		}
	}		
</script>
<main>
	<div class="container-fluid px-4">
		<h1 class="mt-4">공지사항</h1>
		<ol class="breadcrumb mb-4">
			<li class="breadcrumb-item">식당운영자</li>
			<li class="breadcrumb-item active">공지사항 상세보기</li>
		</ol>
		<table style="width:100%;">
			<tr>
				<th style="width:27%; padding:5px;">제목 </th>
				<td>${noticeDetail.title}</td>
			</tr>
			<tr>
				<th style="width:27%; padding:5px;">작성자 </th>
				<td>${noticeDetail.writer}</td>
			</tr>
			<tr>
				<th style="width:27%; padding:5px;">조회수 </th>
				<td>${noticeDetail.user_check}</td>
			</tr>
			<tr>
				<th style="width:27%; padding:10px; height:150px; padding:5px;">내용</th>
				<td style="padding: 5px; border: 1px solid #BBBBBB;">${noticeDetail.content}</td>
			</tr>
			<tr>
				<th style="width:27%; padding:5px;">등록일</th>
				<td>${noticeDetail.up_date}</td>
			</tr>
			<c:if test="${noticeDetail.edit_time != null && noticeDetail.writer == '식당운영자'}"> 
			<tr>
				<th style="width:27%; padding:5px;">수정일</th>
				<td>${noticeDetail.edit_time}</td>
			</tr>
			<tr>
				<th style="width:27%; padding:5px;">수정자</th>
				<td>${noticeDetail.editor}</td>
			</tr>
			</c:if>
		</table>
		<c:if test="${noticeDetail.writer == '식당운영자'}"> 
			<div style="float:right;">
				<button type="button" class="btn btn-primary" style="width:70px;" onclick="location.href='/owner/noticeModi?id=${noticeDetail.notice_id}'"> 수 정 </button>
				<button type="button" class="btn btn-success" style="width:70px;" onclick="delete_notice(${noticeDetail.notice_id})"> 삭 제 </button>
				<button type="button" class="btn btn-secondary" style="width:70px;" onclick="location.href='/owner/notice'"> 닫 기 </button>
			</div>
		</c:if>
		<c:if test="${noticeDetail.writer == '관리자'}"> 
			<div style="float:right;">
				<button type="button" class="btn btn-primary" style="width:80px;" onclick="location.href='/owner/notice'"> 닫 기 </button>
			</div>
		</c:if>
	</div>
</main>
<%@ include file="/WEB-INF/layout/adminFooter.jspf" %>
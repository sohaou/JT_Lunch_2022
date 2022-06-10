<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/main/mainHeader.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta charset="utf-8">
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta name="_csrf" content="${_csrf.token}"/>
<style>
	.td{width:400px;}
</style>
<main>
	<div class="container-fluid px-4">
		<h1 class="mt-4">공지사항</h1>
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
		</table>
		<div style="float:right;">
			<button type="button" class="btn btn-secondary" style="width:70px;" onclick="location.href='/user/notice'"> 닫 기 </button>
		</div>
	</div>
</main>
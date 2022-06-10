<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/ownerHeader.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta charset="utf-8">
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta name="_csrf" content="${_csrf.token}"/>

<main>
	<div class="container-fluid px-4">
		<h1 class="mt-4">요청사항</h1>
		<ol class="breadcrumb mb-4">
			<li class="breadcrumb-item">식당운영자</li>
			<li class="breadcrumb-item active">요청사항 상세보기</li>
		</ol>
		<table style="width:100%;">
			<tr>
				<th style="width:27%; padding:10px;">제목 </th>
				<td>${requestDetail.req_title}</td>
			</tr>
			<tr>
				<th style="width:27%; padding:10px;">작성자 </th>
				<td>${requestDetail.req_writer}</td>
			</tr>
			<tr>
				<th style="width:27%; padding:10px; height:150px;">내용</th>
				<td style="padding: 5px; border: 1px solid #BBBBBB;">${requestDetail.req_content}</td>
			</tr>
			<tr>
				<th style="width:27%; padding:10px;">등록일자</th>
				<td>${requestDetail.up_date}</td>
			</tr>
			<tr>
				<th></th>
				<td style="text-align:right; width:100px;"><button type="button" class="btn btn-primary" style="width:80px;" onclick="location.href='/owner/request'"> 확 인 </button></td>
			</tr>
		</table>
	</div>
</main>
<%@ include file="/WEB-INF/layout/adminFooter.jspf" %>
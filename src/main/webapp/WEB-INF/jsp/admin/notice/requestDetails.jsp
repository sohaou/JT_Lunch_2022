<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/adminHeader.jspf" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta charset="utf-8">
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta name="_csrf" content="${_csrf.token}"/>

<style>
	#SearchResult {
		width:90%;
		margin-left:17px;
		border-collapse: collapse;
		border-top: 3px solid #168;
    }  
    #SearchResult th {
		color: #168;
		background: #f0f6f9;
		text-align: center;

    }
    #SearchResult th, #SearchResult td {
		padding: 6px;
		border: 1px solid #ddd;
    }
    #SearchResult th:first-child, #SearchResult td:first-child {
		border-left: 0;
    }
    #SearchResult th:last-child, #SearchResult td:last-child {
		border-right: 0;
    }
    #SearchResult tr td{
		font-size:12px;
    }
</style>

	<main>
		<div class="container-fluid px-4">
			<h1 class="mt-4">요청사항</h1>
			<ol class="breadcrumb mb-4">
                <li class="breadcrumb-item">관리자</li>
            	<li class="breadcrumb-item active">요청사항</li>
			</ol>
		</div>
		<table id="SearchResult" style="margin-left:auto; margin-right:auto; font-size:16px;">
			<tbody id="Result">
				<tr>
					<th class="table-center" style="width:100px;">제목</th>
					<td style="height:50px;">${request.req_title}</td>
				</tr>	
				<tr>
					<th class="table-center" style="width:100px;">내용</th>
					<td style="height:300px;">${request.req_content}</td>
				</tr>	
				<tr>
					<th class="table-center" style="width:100px;">작성자</th>
					<td style="padding:10px;">${request.req_writer}</td>
				</tr>	
				<tr>
					<th class="table-center" style="width:100px;">등록일</th>
					<td>${request.up_date}</td>
				</tr>	
			</tbody>
		</table><br>
		<div style="float:right; margin-right:80px;">
			<input type ="submit" value="목록" class="btn btn-primary" onclick="location.href='/admin/request'">
		</div>
	</main>
<%@ include file="/WEB-INF/layout/adminFooter.jspf" %>
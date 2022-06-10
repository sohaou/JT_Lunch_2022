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
		text-align: center;
		font-size:12px;
    }
</style>

	<main>
		<div class="container-fluid px-4">
			<h1 class="mt-4">공지사항 등록</h1>
			<ol class="breadcrumb mb-4">
                <li class="breadcrumb-item">관리자</li>
            	<li class="breadcrumb-item active">공지사항 등록</li>
			</ol>	
		</div>
		<form action= '<c:url value="/admin/noticeInsert"/>' method="POST">
			<table id="SearchResult" style="margin-left:auto; margin-right:auto; font-size:16px;">
				<thead>
					<tr>
						<th class="table-center" style="width:100px; padding:10px;">제목</th>
						<td><input type="text" id="title" name="title" style="border: none; margin: 0px; width: 1406px; height: 30px;"></td>
					</tr>	
				</thead>
				<tbody id="Result">
					<tr>
						<th class="table-center" style="width:100px;">내용</th>
						<td><textarea id="content" name="content" style="border:none; margin: 0px; width: 1406px; height: 300px;"></textarea></td>
					</tr>	
				</tbody>
			</table><br>
			<div style="text-align:right; margin-right:83px;">
				<input type ="submit" value="등록" class="btn btn-primary">
		</form>
			<input type ="button" value="취소" style="float:right; margin-left:10px;"class="btn btn-secondary" onclick="location.href='/admin/notice'">
		</div>
	</main>
	
<%@ include file="/WEB-INF/layout/adminFooter.jspf" %>
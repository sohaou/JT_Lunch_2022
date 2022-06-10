<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/ownerHeader.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<meta charset="utf-8">
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta name="_csrf" content="${_csrf.token}"/>

<style>
	.table-center {text-align: center !important; white-space : nowrap; text-overflow: ellipsis;}
	.table-center > a {text-align: center; white-space : nowrap; text-overflow: ellipsis;}
	.searchForm .dataTable-selector {width : 49%; display: inline-block; padding-right: 0.5rem;}
	.searchForm .dataTable-input {width : 49%; display: inline-block; float: right;}
	.text-ellipsis {display: table; table-layout: fixed; width: 100%; white-space: nowrap; overflow: hidden;text-overflow: ellipsis; white-space:nowrap;}
	.text-ellipsis > * {display: table-cell; overflow: hidden; text-overflow: ellipsis;}
</style>
<main>
	<div class="container-fluid px-4">
		<h1 class="mt-4">공지사항</h1>
		<ol class="breadcrumb mb-4">
               <li class="breadcrumb-item">식당운영자</li>
           	<li class="breadcrumb-item active">공지사항 게시판</li>
		</ol>
		<div style="padding:10px; text-align:right;">
			<button type="button" class="btn btn-primary" onclick="location.href='/owner/noticeInsertForm'">
			  등 록
			</button>
		</div>
		<div class="card mb-4">
			<div class="card-header">
				<i class="fas fa-table me-1"></i>
			</div>
			<div class="card-body">
				<table id="datatablesSimple">
					<colgroup>
						<col width="10%" />
						<col width="40%" />
						<col width="10%" />
						<col width="15%" />
						<col width="5%" />
					</colgroup>
					<thead>
			        	<tr>
							<th class="table-center">NO</th>
			        		<th class="table-center">TITLE</th>
			        		<th class="table-center">WRITER</th>
			        		<th class="table-center">DATE</th>
			        		<th class="table-center">VIEW</th>
			        	</tr>	
			        </thead>
					<tbody id="searchResult">
					<c:forEach items="${noticeAll}" var="notice" varStatus="status">
						<tr onclick="location.href='/owner/noticeDetail?id=${notice.notice_id}'">
							<td class="table-center">${notice.notice_id}</td>
							<td class="table-center"><div class="text-ellipsis">${notice.title}</div></td>
				            <td class="table-center">${notice.writer}</td>
				            <td class="table-center"><div class="text-ellipsis">${fn:substring(notice.up_date,5,7)}/${fn:substring(notice.up_date,8,10)}</div></td>
                            <td class="table-center">${notice.user_check}</td>			
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/layout/adminFooter.jspf" %>
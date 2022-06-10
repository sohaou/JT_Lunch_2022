<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/ownerHeader.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
		<h1 class="mt-4">요청사항</h1>
		<ol class="breadcrumb mb-4">
			<li class="breadcrumb-item">식당운영자</li>
           	<li class="breadcrumb-item active">요청사항 게시판</li>
		</ol>
		
		<div class="card mb-4">
			<div class="card-header">
				<i class="fas fa-table me-1"></i>
			</div>
			<div class="card-body">
				<table id="datatablesSimple">
					<colgroup >
						<col width="5%" />
						<col width="55%" />
						<col width="5%" />
						<col width="20%" />
						<col width="5%" />
					</colgroup>
					<thead>
			        	<tr>
							<th class="table-center">NO</th>
			        		<th class="table-center">TITLE</th>
			        		<th class="table-center">WRITER</th>
			        		<th class="table-center">DATE</th>
			        		<th class="table-center">CHECK</th>
			        	</tr>	
			        </thead>
					<tbody id="searchResult">
					<c:set var="count" value="0"/>
					<c:forEach items="${requestAll}" var="request" varStatus="status">
						<tr onclick="location.href='/owner/requestDetail?reqid=${request.req_id}'">
							<td class="table-center">${request.req_id}</td>
							<td class="table-center"><div class="text-ellipsis">${request.req_title}</div></td>
				            <td class="table-center">${request.req_writer}</td>
				            <td class="table-center"><div class="text-ellipsis">${fn:substring(request.up_date,5,7)}/${fn:substring(request.up_date,8,10)}</div></td>
				            <c:if test="${request.owner_check==1}"> 
                             <td class="table-center"><i class="fas fa-check"></i></td>
							</c:if>
							<c:if test="${request.owner_check==0}"> 
                             <td class="table-center">&nbsp;</td>
							</c:if>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/layout/adminFooter.jspf" %>
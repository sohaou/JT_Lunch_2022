<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/adminHeader.jspf" %>
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
	
</style>
	<main>
		<div class="container-fluid px-4">
			<h1 class="mt-4">요청사항</h1>
			<ol class="breadcrumb mb-4">
                <li class="breadcrumb-item">관리자</li>
            	<li class="breadcrumb-item active">요청사항</li>
			</ol>
			
			<div class="card mb-4">
				<div class="card-header">
					<i class="fas fa-table me-1"></i>
				</div>
				<div class="card-body">
					<table id="datatablesSimple">
							<colgroup>
							<col width="5%" />
							<col width="20%" />
							<col width="30%" />
							<col width="10%" />
							<col width="10%" />
							<col width="5%" />
							<col width="5%" />
						</colgroup>
						<thead>
				        	<tr>
								<th class="table-center">NO</th>
				        		<th class="table-center">TITLE</th>
				        		<th class="table-center">CONTENT</th>
				        		<th class="table-center">WRITER</th>
				        		<th class="table-center">DATE</th>
				        		<th class="table-center">CHECK</th>
				        		<th class="table-center">OWNER</th>
				        	</tr>
				        </thead>
						<tbody id="searchResult">
						<c:forEach items="${request}" var="request" varStatus="status">
	                        <tr onclick="location.href='/admin/requestdetails?req_id=${request.req_id}'">
	                            <td class="text-center">${request.req_id}</td>
	                            <td class="text-center">${request.req_title}</td>
	                            <td class="text-center">${request.req_content}</td>
	                            <td class="text-center">${request.req_writer}</td>
	                            <td class="text-center">${request.up_date}</td>
	                            <c:if test="${request.admin_check==1}"> 
	                            <td class="table-center"><i class="fas fa-check"></i></td>
								</c:if>
								<c:if test="${request.admin_check==0}"> 
	                            <td class="table-center">&nbsp;</td>
								</c:if>
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
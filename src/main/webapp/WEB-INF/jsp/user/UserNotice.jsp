<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/main/mainHeader.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.23/css/jquery.dataTables.min.css" />
<script src="//cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>
<meta charset="utf-8">
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta name="_csrf" content="${_csrf.token}"/>
<script>
$(document).ready(function() {
    $('#datatables').DataTable({
    	"dom": 't<"col-sm-6"f><"col-sm-6"p>'
    });
});
</script>
<style>
	.table-center {text-align: center !important; white-space : nowrap; text-overflow: ellipsis;}
	.table-center > a {text-align: center; white-space : nowrap; text-overflow: ellipsis;}
	.searchForm .dataTable-selector {width : 49%; display: inline-block; padding-right: 0.5rem;}
	.searchForm .dataTable-input {width : 49%; display: inline-block; float: right;}
	.text-ellipsis {display: table; table-layout: fixed; width: 100%; white-space: nowrap; overflow: hidden;text-overflow: ellipsis; white-space:nowrap;}
	.text-ellipsis > * {display: table-cell; overflow: hidden; text-overflow: ellipsis;}
</style>
<body style="overflow: auto;">
	<main>
		<div class="container-fluid px-4">
			<h1 class="mt-4">공지사항</h1>
			<div class="card mb-4" style="margin-top:20px;">
				<!-- <div class="card-header">
					<i class="fas fa-table me-1"></i>
				</div> -->
				<div class="card-body">
					<table id="datatables" style="width:100%;">
						<colgroup>
							<col width="5%" />
							<col width="80%" />
							<col width="10%" />
							<!-- <col width="15%" /> -->
							<col width="5%" />
						</colgroup>
						<thead>
				        	<tr>
								<th class="table-center">NO</th>
				        		<th class="table-center">TITLE</th>
				        		<!-- <th class="table-center">WRITER</th> -->
				        		<th class="table-center">DATE</th>
				        		<th class="table-center">VIEW</th>
				        	</tr>	
				        </thead>
						<tbody id="searchResult">
						<c:forEach items="${noticeAll}" var="notice" varStatus="status">
							<tr onclick="location.href='/user/noticeDetail?id=${notice.notice_id}'">
								<td class="table-center">${notice.notice_id}</td>
								<td class="table-center"><div class="text-ellipsis">${notice.title}</div></td>
					            <%-- <td class="table-center">${notice.writer}</td> --%>
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
</body>
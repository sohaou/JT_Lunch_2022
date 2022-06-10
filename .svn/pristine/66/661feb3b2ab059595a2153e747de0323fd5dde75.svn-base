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
	/* .searchForm #searchBtn {white-space : nowrap; text-overflow: ellipsis;} */
</style>
<script>
		function notice_delete(notice_id) {
			var str=notice_id;
			$.ajax({
				url: "<c:url value='/admin/noticeDelete'/>",
			    type: "POST",
			    data : {notice_id:str},
		
			    success : function(data){
			    	alert(data);
				},
				complete : function(data){
					location.reload();
				},
				error:function(request,status,error)
			    {
			    	alert(error);
				}
			});
		}
</script>
	<main>
		<div class="container-fluid px-4">
			<h1 class="mt-4">공지사항</h1>
			<ol class="breadcrumb mb-4">
                <li class="breadcrumb-item">관리자</li>
            	<li class="breadcrumb-item active">공지사항</li>
			</ol>
			<input style="width: 100px; float:right;" class="btn btn-primary" type="button" value="등록" onclick="location.href='/admin/noticeinsert'"/>
			<br><br>
			<div class="card mb-4">
				<div class="card-header">
					<i class="fas fa-table me-1"></i>
				</div>
				<div class="card-body">
					<table id="datatablesSimple">
						<colgroup>
							<col width="5%" />
							<col width="25%" />
							<col width="40%" />
							<col width="10%" />
							<col width="10%" />
							<col width="5%" />
						</colgroup>
						<thead>
				        	<tr>
								<th class="table-center">NO</th>
				        		<th class="table-center">TITLE</th>
				        		<th class="table-center">CONTENT</th>
				        		<th class="table-center">WRITER</th>
				        		<th class="table-center">DATE</th>
				        		<th class="table-center">VIEW</th>
				        	</tr>	
				        </thead>
						<tbody id="searchResult">
						<c:forEach items="${notice}" var="notice" varStatus="status">
	                        <tr onclick="location.href='/admin/noticedetails?notice_id=${notice.notice_id}'">
	                            <td class="text-center">${notice.notice_id}</td>
	                            <td class="text-center">${notice.title}</td>
	                            <td class="text-center">${notice.content}</td>
	                            <td class="text-center">${notice.writer}</td>
	                            <td class="text-center">${notice.up_date}</td>
	                            <td class="text-center">${notice.user_check}</td>
	                        </tr>
	                      </c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</main>
<%@ include file="/WEB-INF/layout/adminFooter.jspf" %>
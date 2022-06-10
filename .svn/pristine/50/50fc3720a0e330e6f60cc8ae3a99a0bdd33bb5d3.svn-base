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
<script>
	function notice_delete(noticeId) {
		var str=noticeId;
		$.ajax({
			url: "<c:url value='/admin/noticeDelete'/>",
		    type: "POST",
		    data : {notice_id:str},

		    success : function(data){
		    	alert(data);
			},
			complete : function(data){
				location.href="/admin/notice";
			},
			error:function(request,status,error)
		    {
		    	alert(error);
			}
		});
	}
	
	function notice_delete_check(noticeId) {
		var str=noticeId;
		if(confirm("삭제하시겠습니까?")==true) {
			notice_delete(str);
		}
		else{
			return;
		}
	}
</script>
	<main>
		<div class="container-fluid px-4">
			<h1 class="mt-4">공지사항</h1>
			<ol class="breadcrumb mb-4">
                <li class="breadcrumb-item">관리자</li>
            	<li class="breadcrumb-item active">공지사항</li>
			</ol>
		</div>
		<table id="SearchResult" style="margin-left:auto; margin-right:auto; font-size:16px;">
			<tbody id="Result">
				<tr>
					<th class="table-center" style="width:100px;">제목</th>
					<td style="height:50px;">${notice.title}</td>
				</tr>	
				<tr>
					<th class="table-center" style="width:100px;">내용</th>
					<td style="height:300px;">${notice.content}</td>
				</tr>	
				<tr>
					<th class="table-center" style="width:100px;">작성자</th>
					<td style="padding:10px;">${notice.writer}</td>
				</tr>	
				<tr>
					<th class="table-center" style="width:100px;">등록일</th>
					<td>${notice.up_date}</td>
				</tr>	
				<tr>
					<th class="table-center" style="width:100px;">수정자</th>
					<td>${notice.editor}</td>
				</tr>	
				<tr>
					<th class="table-center" style="width:100px;">수정일</th>
					<td>${notice.edit_time}</td>
				</tr>	
			</tbody>
		</table><br>
		<div style="float:right; margin-right:80px;">
			<input type ="submit" value="수정" class="btn btn-primary" onclick="location.href='/admin/noticeupdate?notice_id=${notice.notice_id}'">
			<input type ="submit" value="삭제" class="btn btn-secondary" onclick="notice_delete_check('${notice.notice_id}')">
			<input type ="submit" value="목록" class="btn btn-dark" onclick="location.href='/admin/notice'">
		</div>
	</main>
<%@ include file="/WEB-INF/layout/adminFooter.jspf" %>
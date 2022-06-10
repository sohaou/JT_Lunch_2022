<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/ownerHeader.jspf" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta charset="utf-8">
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta name="_csrf" content="${_csrf.token}"/>

<script type="text/javascript">
	function check_input() {
		if (document.noticeInsertForm.title.value == ""){
			alert("제목을 입력해주세요!");
			document.noticeInsertForm.title.focus();
			return;
		}
		else if (!document.noticeInsertForm.content.value){
			alert("내용을 입력해주세요!");
			document.noticeInsertForm.content.focus();
			return;
		}
		$.ajax({
			url: "<c:url value='/owner/noticeSave'/>",
		    type: "POST",
		    data : $("#noticeInsertForm").serialize(),
		    success : function(data){
				alert(data);
			},
			complete : function(data){
				location.href='/owner/notice';
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
               <li class="breadcrumb-item">식당운영자</li>
           	<li class="breadcrumb-item active">공지사항 등록</li>
		</ol>
		<form id="noticeInsertForm" name="noticeInsertForm" role="form" method="post" autocomplete="off" >
			<table style="width:100%;">	
				<tr>
					<th style="width:27%; padding:5px;">* 제목 : </th>
					<td><input type="text" name="title" style="width:100%; border:1; overflow:visible; text-overflow:ellipsis; padding:5px;"/></td>
				</tr>
				<tr>
					<th style="width:27%; padding:5px;">* 내용 : </th>
					<td><textarea style="width:100%; border:1; overflow:visible; text-overflow:ellipsis;" rows="10" name="content"></textarea></td>
				</tr>
				<tr>
					<th></th>
					<td style="height:60px;">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />&nbsp;
						<input class="btn btn-primary" type="button" style="width:80px;" value="등 록" onclick="check_input()"/>&nbsp;&nbsp;
						<input class="btn btn-secondary" type="button" style="width:80px;" value="취 소" onclick="location.href='/owner/notice'"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</main>
<%@ include file="/WEB-INF/layout/adminFooter.jspf" %>
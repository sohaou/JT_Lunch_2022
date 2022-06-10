<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/main/mainHeader.jsp" %>
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
	function request_delete(reqId) {
		var str=reqId;
		$.ajax({
			url: "<c:url value='/user/requestDelete'/>",
		    type: "POST",
		    data : {req_id:str},

		    success : function(data){
		    	alert(data);
			},
			complete : function(data){
				location.href="/user/request";
			},
			error:function(request,status,error)
		    {
		    	alert(error);
			}
		});
	}
	
	function request_delete_check(reqId) {
		var str=reqId;
		if(confirm("삭제하시겠습니까?")==true) {
			request_delete(str);
		}
		else{
			return;
		}
	}
</script>
<body style="overflow: auto;">
	<main>
		<div class="container-fluid px-4">
			<h1 class="mt-4" style="margin-top:50px;">요청사항</h1>
		</div>
		<table id="SearchResult" style="margin-left:auto; margin-right:auto; font-size:16px; margin-top:20px;">
			<tbody id="Result">
				<tr>
					<th class="table-center" style="width:118px;">제목</th>
					<td style="height:50px;">${requestDetail.req_title}</td>
				</tr>	
				<tr>
					<th class="table-center" style="width:118px;">내용</th>
					<td style="height:300px;">${requestDetail.req_content}</td>
				</tr>	
				<tr>
					<th class="table-center" style="width:118px;">작성자</th>
					<td style="padding:10px;">${requestDetail.req_writer}</td>
				</tr>	
				<tr>
					<th class="table-center" style="width:118px;">등록일</th>
					<td>${requestDetail.up_date}</td>
				</tr>	
				<tr>
					<th class="table-center" style="width:118px;">관리자 확인 </th>
					<c:if test="${requestDetail.admin_check==1}"> 
	               		<td class="table-center"><i class="fas fa-check"></i></td>
					</c:if>
					<c:if test="${requestDetail.admin_check==0}"> 
	               		<td class="table-center"><i class="fas fa-times"></i></td>
					</c:if>
				</tr>	
				<tr>
					<th class="table-center" style="width:118px;">식당 운영자 확인</th>
					<c:if test="${requestDetail.owner_check==1}"> 
	               		<td class="table-center"><i class="fas fa-check"></i></td>
					</c:if>
					<c:if test="${requestDetail.owner_check==0}"> 
	               		<td class="table-center"><i class="fas fa-times"></i></td>
					</c:if>
				</tr>	
			</tbody>
		</table>
		<c:if test="${requestDetail.req_writer == UserId.user_name}"> 
			<div style="float:right; margin-right:20px; margin-top:5px;">
				<button type="button" class="btn btn-primary" style="width:70px;" onclick="location.href='/user/requestModi?id=${requestDetail.req_id}'"> 수 정 </button>
				<button type="button" class="btn btn-success" style="width:70px;" onclick="request_delete_check(${requestDetail.req_id})"> 삭 제 </button>
				<button type="button" class="btn btn-secondary" style="width:70px;" onclick="location.href='/user/request'"> 닫 기 </button>
			</div>
		</c:if>
		<c:if test="${requestDetail.req_writer != UserId.user_name}"> 
			<div style="float:right; margin-right:20px; margin-top:5px;">
				<button type="button" class="btn btn-primary" style="width:80px;" onclick="location.href='/user/request'"> 닫 기 </button>
			</div>
		</c:if>
	</main>
</body>
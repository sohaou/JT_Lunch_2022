<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="/WEB-INF/layout/adminHeader.jspf" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/datatables-simple-demo.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/datatables-demo.js" ></script>
 --%>
 <meta charset="utf-8">

<script type="text/javascript">
	function check_input() {
		if (document.menuInsertForm.menu_type.value == ""){
			alert("분류를 선택해주세요!");
			document.menuInsertForm.menu_type.focus();

			return;
		}
		else if (!document.menuInsertForm.menu_name.value){
			alert("메뉴명을 입력해주세요!");
			document.menuInsertForm.menu_name.focus();

			return;
		}
		
		$.ajax({
			url: "<c:url value='/admin/menuSave'/>",
		    type: "POST",
		    data : $("#menuInsertForm").serialize(),
		    success : function(data){
				alert(data);
				menuInsert();
			},
			complete : function(data){
				location.reload();
			},
			error:function(request,status,error){
		    	alert(error);
				console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" +  "error:" + error);
			}
		});
	}
	
	function menuInsert() {	
		$('#menu_name').val("");
		$('#menu_type').val("");
		$('#menuInsertModal').modal('hide');
	}
	// 메뉴 수정 모달 표시
	function show(menuId){
		let str = menuId;
		$.ajax({
			url: "<c:url value='/admin/menuUpdate'/>",
			type: "POST",
			data : {menu_id : str},
			dataType : 'json',
			//contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(data){
				if(data.result.menu_type == 'D'){
					$('#menuType').html("* 분류 : 반찬");	
				} else if(data.result.menu_type == 'S'){
					$('#menuType').html("* 분류 : 국");	
				} else if(data.result.menu_type == 'R'){
					$('#menuType').html("* 분류 : 밥");	
				}
				$('input[name = menu_name]').attr('value',data.result.menu_name);
				$('input[name = menu_id]').attr('value',data.result.menu_id);			 
			},				
			error:function(request,status,error){
			   alert(error);
			   console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" +  "error:" + error);
			}
		});
		$("#menuModifyModal").modal('show');
	}
	
	// 메뉴 수정 저장
	function check_update() {
		if (!document.menuModifyForm.menu_name.value){
			alert("메뉴명을 입력해주세요!");
			document.menuModifyForm.menu_name.focus();

			return;
		}
		
		$.ajax({
			url: "<c:url value='/admin/menuUpdateSave'/>",
		    type: "POST",
		    data : $("#menuModifyForm").serialize(),
		    success : function(data){
				alert(data);
				menuUpdate();
			},
			complete : function(data){
				location.reload();
			},
			error:function(request,status,error){
		    	alert(error);
		    	console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" +  "error:" + error);
			}
		});
	}
	
	// 메뉴 수정 후 모달 숨기기
	function menuUpdate() {	
		$('#menu_name').val("");
		$('#menuModifyModal').modal('hide');
	}
	
	function menu_delete(menuId) {
		let str = menuId;
		$.ajax({
			url: "<c:url value='/admin/menuDelete'/>",
		    type: "POST",
		    data : {menu_id : str},
		    success : function(data){
		    	alert(data);
			},
			complete : function(data){
				location.reload();
			},
			error:function(request,status,error){
		    	alert(error);
		    	console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" +  "error:" + error);
			}
		});
	}
</script>
<style>
	.table-center {text-align: center !important; white-space : nowrap; text-overflow: ellipsis;}
	.table-center > a {text-align: center; white-space : nowrap; text-overflow: ellipsis;}
	.searchForm .dataTable-selector {width : 49%; display: inline-block; padding-right: 0.5rem;}
	.searchForm .dataTable-input {width : 49%; display: inline-block; float: right;}
</style>
<main>
	<div class="container-fluid px-4" id="test">
		<h1 class="mt-4">메뉴 목록</h1>
		<ol class="breadcrumb mb-4">
            <li class="breadcrumb-item">식당운영자</li>
           	<li class="breadcrumb-item active">메뉴목록</li>
		</ol>
		<div style="padding:10px; text-align:right;">
			<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#menuInsertModal" onclick="menuInsert()">
				  메뉴 등록
			</button>
		</div>
		<div class="card mb-4">
			<div class="card-header">
				<i class="fas fa-table me-1"></i>
			</div>
			<div class="card-body">
				<table id="datatablesSimple">
					<colgroup>
						<col width="25%" />
						<col width="35%" />
						<col width="10%" />
						<col width="10%" />
					</colgroup>
					<thead>
			        	<tr>
							<th class="table-center">분류</th>
			        		<th class="table-center">메뉴명</th>
			        		<th class="table-center">수정</th>
			        		<th class="table-center">삭제</th>
			        	</tr>	
			        </thead>
					<tbody id="searchResult">
						<c:forEach items="${viewAll}" var="menu" varStatus="status">
						<tr>
							<td class="table-center">
								<c:choose>
									<c:when test="${menu.menu_type eq 'S'}">국</c:when>
									<c:when test="${menu.menu_type eq 'R'}">밥</c:when>
									<c:when test="${menu.menu_type eq 'D'}">반찬</c:when>
								</c:choose>
							</td>
				            <td class="table-center">${menu.menu_name}</td>
				            <td class="table-center">
								<a onclick="show('${menu.menu_id}')"><i class="fas fa-pen" style="color:blue;"></i></a>
							</td>
							<td class="table-center">
							<a onclick="menu_delete('${menu.menu_id}')"><i class="fas fa-window-close" style="color:red;"></i></a>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<%
		Date nowTime = new Date();
		SimpleDateFormat datee = new SimpleDateFormat("yyyyMMddhhmmss");
	%>
	<!-- 메뉴 등록 Modal -->
	<div class="modal fade" id="menuInsertModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="container-fluid px-4">
			<div class="modal-dialog" role="document">
			    <div class="modal-content">
			    	<div class="modal-header">
			        	<h4 class="modal-title" id="myModalLabel">메뉴 등록</h4>
			      	</div>
					<div class="modal-body">
						<form id="menuInsertForm" name="menuInsertForm" style="display: inline-flex; width: 100%;">
							<select name="menu_type" id="menu_type" class="dataTable-selector mt-4" style="width: 50%">
								<option value="" selected>선택</option>
								<option value="R">밥</option>
								<option value="S">국</option>
								<option value="D">반찬</option>
							</select>
							<input class="dataTable-input mt-4" type="text" name="menu_name" id="menu_name" autocomplete="off" style="width: 50%"/>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<input type="hidden" name="menu_id" value="<%=datee.format(nowTime) %>">
						</form>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-primary" value="등록" onclick="check_input()"/>
				    	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
					</div>
			    </div>
			</div>
		</div>
	</div>		
	
	<!-- 메뉴 수정 Modal -->
	<div class="modal fade" id="menuModifyModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true"> 
		<div class="container-fluid px-4">
			<div class="modal-dialog"> 
				<div class="modal-content"> 
					<div class="modal-header"> 
						<h4 class="modal-title">메뉴 수정</h4> 
					</div> 
					<div class="modal-body"> 
						<form id="menuModifyForm" name="menuModifyForm" style="display: inline-flex; width: 100%;">
							<input type="hidden" name="menu_id" value="">
							<p id="menuType" style="font-size:16px; width: 40%; margin-top:28px;"></p>
							<input class="dataTable-input mt-4" type="text" name="menu_name" id="menu_name" autocomplete="off" style="width: 60%"/>
						</form>
					</div> 
					<div class="modal-footer"> 
						<input type="button" class="btn btn-primary" value="수정" onclick="check_update()"/>
					    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
					</div> 
				</div> 
			</div> 
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/layout/adminFooter.jspf" %>
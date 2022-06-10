<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/adminHeader.jspf" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $(function() {    
        // start Date 설정시 end Date 가 start Date보다 작을 경우 end Date를 start Date와 같게 설정
        $("#startDt").datepicker({
            dateFormat: "yy-mm-dd",
            defaultDate: "+1w",
            numberOfMonths: 1,
            changeMonth: true,
            showMonthAfterYear: true ,
            changeYear: true,
            dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            onClose: function( selectedDate ) {
                if ($( "#endDt" ).val() < selectedDate)
                {
                    $( "#endDt" ).val(selectedDate);
                }
            }
        }); 
        // end Date 설정시 end Date 가 start Date 보다 작을 경우 start Date를  end Date와 같게 설정
        $( "#endDt" ).datepicker({
            dateFormat: "yy-mm-dd",
            defaultDate: "+1w",
            numberOfMonths: 1,
            changeMonth: true,
            showMonthAfterYear: true ,
            changeYear: true,
            dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            onClose: function( selectedDate ) {
                if ($("#startDt" ).val() > selectedDate)
                {
                    $("#startDt" ).val(selectedDate);
                }
            }
        });
    }); 
</script>
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
<div class="container-fluid px-4">
		<h1 class="mt-4">기간별 식수 인원 조회</h1>
			<ol class="breadcrumb mb-4">
                <li class="breadcrumb-item">관리자</li>
            	<li class="breadcrumb-item active">식수 인원 조회</li>
			</ol>
</div>
<div id="printResult" style="margin-left:20px;">
	<form name="searchResultForm" role="form" method="post" autocomplete="off">	
	<table>
				<tr>
					<th style="width:20%;">
						<strong>&nbsp;*</strong>&nbsp;
						<font size="3em" face="Verdana">기간</font>
					</th>
					<td>
						<input type="text" id="startDt" style="width:103px;"/> ~ <input type="text" id="endDt" style="width:103px;"/>&nbsp;
					</td>
					<td>
						<input class="btn btn-primary" type="button" value="검색 " style="width:56px; height:32px;" onclick="check_input()"/>
					</td>
				</tr>
				</table>
				</form>
				<br>
				<table>
				<tr>
					<th>
						<strong>&nbsp;*</strong>&nbsp;
						<font size="3em" face="Verdana">총 식사 인원 :&nbsp;&nbsp;</font>
					</th>
					<td id="ateUserAll">
						
					</td>
				</tr>
				<tr><td></td><tr>
	</table>
	<br>
	<table id="SearchResult" style="margin-left:auto; margin-right:auto; font-size:16px;">
		<colgroup>
			<col width="20%" />
			<col width="23%" />
			<col width="20%" />
			<col width="15%" />
			<col width="20%" />
		</colgroup>
		<thead>
			<tr>
				<th class="table-center">날짜</th>
				<th class="table-center">부서</th>
				<th class="table-center">팀</th>
				<th class="table-center">이름</th>
				<th class="table-center">전화번호</th>
			</tr>	
		</thead>
		<tbody id="Result">
		</tbody>
	</table>
</div>
<%@ include file="/WEB-INF/layout/adminFooter.jspf" %>
<script>
function check_input() {
	var str = "";
    if (!document.searchResultForm.startDt.value && document.searchResultForm.endDt.value=="")
    {
        alert("기간을 입력해주세요!");
        document.searchResultForm.startDt.focus();
        // 화면 커서 이동
        return;
    }
	
    $.ajax({
		url: "<c:url value='/admin/totalCountSearch'/>",
	    type: "POST",
	    data :{
	    	startDt:document.searchResultForm.startDt.value,
	    	endDt:document.searchResultForm.endDt.value},
	    /*beforeSend: function (xhr) {
	    	xhr.setRequestHeader(header, token);
	           
		},*/
	    success : function(data){
			if(data.money==0){
				alert("해당 기간에 정보가 없습니다!");
				$("#ateUserAll").html("");
				$("#money").html("");	
			}
			else{
				$("#ateUserAll").html(data.ateUser+" 명");
			}
			$('#Result').empty();
			if(data.searchUser.length>0){
				console.log(data.searchUser);	
					for (i=0; i<data.searchUser.length; i++ ) {
						str+="<tr>";
						str+="<td class='table-center' style='font-size=15px;'>"+data.searchUser[i].ate_date+"</td>";
						str+="<td class='table-center'>"+data.searchUser[i].department+"</td>";
						str+="<td class='table-center'>"+data.searchUser[i].team+"</td>";
						str+="<td class='table-center'>"+data.searchUser[i].name+"</td>";
						str+="<td class='table-center'>"+data.searchUser[i].tel+"</td>";
						str+="</tr>";
						}
					}else{
						str="";
						
					}
					$('#Result').append(str);
		},
		error:function(request,status,error)
	    {
	    	alert(error);
		}
	});
 }
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/adminHeader.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<meta charset="utf-8">
<script type="text/javascript">
$(document).ready(function() {
	checkPW();
});

//비밀번호 유효성 검사
function checkPW(){
	var pw = $("#PW").val();
	var num = pw.search(/[0-9]/g);
	var eng = pw.search(/[a-z]/ig);
	var spe = pw.search(/[!@#$%^&*]/gi);
	
	if (pw.length < 8 || pw.length > 16){
		$("#pwAlert").html("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
		//alert("8 ~ 16자 이내로 입력해주세요.");
		return;
	}else if(pw.search(/\s/) != -1){
		$("#pwAlert").html("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요."); 
		//alert("비밀번호는 공백 없이 입력해주세요.");
		return;
	}else if( (num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0) ){
		$("#pwAlert").html("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요."); 
		//alert("영문 ,숫자, 특수문자 중 2가지 이상을 혼합하여 입력해주세요.");
		return;
	}else{
		$("#pwAlert").html(""); 
		$("#PWValue").val("1");
	}
}

// 비밀번호 일치 검사
function pwSameCheck(){
	var pw = $("#PW").val();
	var pwCheck = $("#PWCheck").val();
	
	if(pw == pwCheck){
		$("#pwCheck").html("");
		$('#PWCheckValue').val('1');
	} else{
		$("#pwCheck").html("비밀번호가 일치하지 않습니다.");
	}
}

$(function(){
	$('#submit').on("click",function(){
		var form1 = $("#FranchiseUpdateForm").serialize();
		var form = document.FranchiseUpdateForm;
    	
        if(!form.PW.value){
            alert("비밀번호를 입력하세요.");
            form.PW.focus();
            return;
        }
        // 비밀번호를 올바르게 입력했는지
        if(form.PWValue.value != '1' || form.PWCheckValue.value != '1'){
            alert("비밀번호를 올바르게 입력하세요.");
            form.PW.focus();
            return;
        }
        if(!form.SHOP_NAME.value){
            alert("지점명을 입력하세요.");
            form.SHOP_NAME.focus();
            return;
        }
        if(!form.phone1.value || !form.phone2.value){
            alert("전화번호를 입력하세요.");
            form.phone1.focus();
            return;
        } 
        if(!form.ZIP_CODE.value || !form.ADDRESS.value || !form.DETAIL_ADDRESS.value){
            alert("주소를 입력하세요.");
            form.ZIP_CODE.focus();
            return;
        } 
        if(!form.DATA_URL.value){
            alert("데이터 전송 주소를 입력하세요.");
            form.DATA_URL.focus();
            return;
        } 
    	 $.ajax({
 			url: "/admin/franchiseUpdate",
 		    type: "POST",
 		    data: $("#FranchiseUpdateForm").serialize(),
 		    success : function(data){
 				alert(data);
 				console.log(data);
 			},
 			complete : function(data){
 				location.href="/admin/franchiseList";
 			},
 			error:function(request,status,error)
 		    {
 				console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
 			}
 		});
        
	})
})

// 삭제
function product_delete(id) {
	var str = id;
	var deleteStr = confirm('정말 삭제하시겠습니까?');
	if(deleteStr){
		$.ajax({
			url: "/admin/franchiseDelete",
		    type: "POST",
		    data : {id : str},
		    success : function(data){
				alert(data);
			},
			complete : function(data){
				location.href='/admin/franchiseList';
			},
			error:function(request,status,error)
		    {
				alert("오류가 발생했습니다.");
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
} 
</script>
<style>
	#duplCheck, #addressBtn{
		width:60px;
		height: 32px;
	    background-color: #D3D3D3;
	    border: none;
	    color: #444444;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    padding: 3px;
	    font-size: 12px;
	    border-radius:5px 5px 5px 5px;
	    cursor: pointer;
	    font-weight: bold;
	    margin-top: 10px;
	    margin-left:5px;
	    font-family: 'Titillium Web','Noto Sans KR','Malgun Gothic','맑은 고딕',Dotum,'돋움','AppleGothic','Apple SD Gothic Neo',sans-serif;
	}
	#submit{
		width:100px;
		height: 40px;
	    background-color: #444444;
	    border: none;
	    color:#fff;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    padding: 5px;
	    font-size: 14px;
	    border-radius:5px 5px 5px 5px;
	    cursor: pointer;
	    margin-top: 10px;
	    margin-right:10px;
	    font-family: 'Titillium Web','Noto Sans KR','Malgun Gothic','맑은 고딕',Dotum,'돋움','AppleGothic','Apple SD Gothic Neo',sans-serif;
	}
	#cancel{
		width:100px;
		height: 40px;
	    background-color: #D3D3D3;
	    border: none;
	    color: #444444;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    padding: 5px;
	    font-size: 14px;
	    border-radius:5px 5px 5px 5px;
	    cursor: pointer;
	    margin-top: 10px;
	    margin-left:10px;
	    font-family: 'Titillium Web','Noto Sans KR','Malgun Gothic','맑은 고딕',Dotum,'돋움','AppleGothic','Apple SD Gothic Neo',sans-serif;
	}
	#joint td{
		padding: 15px 0 15px;
	    color: #353535;
	    vertical-align: middle;
	    font-family: 'Titillium Web','Noto Sans KR','Malgun Gothic','맑은 고딕',Dotum,'돋움','AppleGothic','Apple SD Gothic Neo',sans-serif;
	}
	#joint{
		width: 100%;
		/* border-top: 1px solid #ddd; */
		margin-left:auto;
		margin-right:auto;
		border-spacing: 0;
    	border-collapse: collapse;
	}
	#joinDiv{
		text-align: center;
		width: 700px;
		margin-left: auto;
		margin-right: auto;
	}
	#joint input{
		padding: 3px;
	}
	#idCheckDiv{
		margin-top: 3px;
		margin-bottom: -12px;
		font-size: 11px;
		font-family: 'Titillium Web','Noto Sans KR','Malgun Gothic','맑은 고딕',Dotum,'돋움','AppleGothic','Apple SD Gothic Neo',sans-serif;
	}
	#pwAlert, #pwCheck{
		font-size: 11px;
		font-family: 'Titillium Web','Noto Sans KR','Malgun Gothic','맑은 고딕',Dotum,'돋움','AppleGothic','Apple SD Gothic Neo',sans-serif;
	}
	#submitBtn{
		width:60px;
		height: 30px;
	    background-color: #444444;
	    border: none;
	    color:#fff;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    padding: 5px;
	    font-size: 12px;
	    border-radius:5px 5px 5px 5px;
	    cursor: pointer;
		margin-left: 5px;
	    font-family: 'Titillium Web','Noto Sans KR','Malgun Gothic','맑은 고딕',Dotum,'돋움','AppleGothic','Apple SD Gothic Neo',sans-serif;
	}
</style>
<main>
	<div class="container-fluid px-4">
		<h1 class="mt-4">체인점 관리</h1>
		<ol class="breadcrumb mb-4">
               <li class="breadcrumb-item">관리자</li>
           	<li class="breadcrumb-item active">체인점 수정 및 상세보기</li>
		</ol>
	</div>
	<div style="margin-left: auto; margin-right: auto; width: 60%;">
		<form id="FranchiseUpdateForm" name="FranchiseUpdateForm" style="display: inline-flex; width: 100%;">
           <table id="joint" style="width:100%;">
            <tr>
            	<td colspan="2" style="font-size:12px; text-align:right;"><p style="margin-right:70px;"><a style="color:red; font-weight:bold;">*</a>은 필수 입력 정보입니다.</p></td>
            </tr>
               <tr>
                   <td id="title">ID</td>
                   <td>
						<div id="FranchiseID">${dataList.ID }</div>
						<input type="hidden" name="ID" id="ID" value="${dataList.ID }">
                      <!--  <input type="text" name="ID" id="ID" maxlength="50" style="width: 205px;">
                       <input type="button" value="중복확인" id="duplCheck" onclick="checkID()">  
                       <input type="hidden" name="IDCheck" id="IDCheck" value="0"><br>
                       <div id="idCheckDiv" style="display: none;">사용 가능한 아이디입니다</div> -->
                   </td>
               </tr>
                       
               <tr>
                   <td id="title">PW <a style="color:red; font-weight:bold;">*</a></td>
                   <td>
                       <input type="password" name="PW" id="PW" maxlength="15" style="width: 330px;" value="${dataList.PW }" onblur="checkPW()">
                       <p id="pwAlert" style="color: red;">8~16자 영문 대 소문자, 숫자, 특수문자 중 2종류 포함.<br>(단, 특수문자는 !@#$%^&* 만 가능)</p>
                       <input type="hidden" name="PWValue" id="PWValue" value="0"><br>
                   </td>
               </tr>
               
               <tr>
                   <td id="title">PW 확인 <a style="color:red; font-weight:bold;">*</a></td>
                   <td>
                       <input type="password" name="PWCheck" id="PWCheck" maxlength="15" style="width: 330px;" value="${dataList.PW }" onblur="pwSameCheck()">
                       <p id="pwCheck" style="color: red;"></p>
                        <input type="hidden" name="PWCheckValue" id="PWCheckValue" value="0"><br>
                   </td>
               </tr>
                   
               <tr>
                   <td id="title">지점명 <a style="color:red; font-weight:bold;">*</a></td>
                   <td>
                       <input type="text" name="SHOP_NAME" id="SHOP_NAME" maxlength="50" style="width: 330px;" value="${dataList.SHOP_NAME}">
                   </td>
               </tr>
               <tr>
                   <td id="title">전화번호 <a style="color:red; font-weight:bold;">*</a></td>
                   <td>
                   010 -
                       <input type="text" name="phone1" id="phone1" maxlength="4" size="3" style="width: 133px;" value="${fn:substring(dataList.TEL,4,8) }" onKeyup="this.value=this.value.replace(/[^-0-9]/g,'');">    -
                       <input type="text" name="phone2" id="phone2" maxlength="4" size="3" style="width: 133px;" value="${fn:substring(dataList.TEL,9,13) }" onKeyup="this.value=this.value.replace(/[^-0-9]/g,'');">
                   </td>
               </tr>
               <tr> 
               	<td id="title">주소 <a style="color:red; font-weight:bold;">*</a></td> 
               	<td> 
               		<input type="text" name="ZIP_CODE" id="ZIP_CODE" placeholder="우편번호" style="width: 170px; margin-bottom:5px;" value="${dataList.ZIP_CODE}" readonly>
					<input type="button" onclick="inputAddress()" value="우편번호" id="addressBtn" ><br>
					<input type="text" name="ADDRESS" id="ADDRESS" placeholder="도로명주소" style="width: 330px; margin-bottom:5px;" value="${dataList.ADDRESS}"><br>
					<input type="text" name="DETAIL_ADDRESS" id="DETAIL_ADDRESS" placeholder="상세주소" style="width: 330px;" value="${dataList.DETAIL_ADDRESS}">
               	</td> 
               </tr>
               <tr>
               	<td id="title">데이터 전송 주소 <a style="color:red; font-weight:bold;">*</a></td>
               	<td>
               		<input type="text" name="DATA_URL" style="width: 330px;" value="${dataList.DATA_URL}">
               	</td>
               </tr>
               <tr>
               	<td colspan="2" style="text-align:center;">
               		<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
               		<input type="button" id="submit" value="수 정"/>
               		<input type="button" id="cancel" value="닫 기" onclick="location.href='/admin/franchiseList'">
               	</td>
               </tr>
           </table>
           <br>
       </form>
	</div>
</main>
<%@ include file="/WEB-INF/layout/adminFooter.jspf" %>
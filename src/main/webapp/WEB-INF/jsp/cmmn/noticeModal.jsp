<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-latest.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/noticeModal.js" ></script>

<style>
	/*모달이 표시될 때 뒤 배경을 어둡게*/
	.notice_modal{
		display: none;
		position: fixed;
		z-index: 1;
		left: 0;
		top: 0;
		width: 100%;
		height: 100%;
		overflow: auto;
		background-color: rgb(0,0,0);
		background-color: rgba(0,0,0,0.4);
	}
	#myModalLabel{
		width: 90%;
		text-align: center;
		font-weight: bold;
		font-size: 28px;
		font-family: 'Titillium Web','Noto Sans KR','Malgun Gothic','맑은 고딕',Dotum,'돋움','AppleGothic','Apple SD Gothic Neo',sans-serif;
	}
	#close{
		background-color: white;
	    border: none;
	    color:black;
	    padding: 15px 0;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 22px;
	    /*margin: 4px;*/
	    cursor: pointer;
	}
	.modal-content{
		background-color: #fefefe;
		margin: 15% auto;
		max-width: 450px;
		padding: 20px;
		border: 1px solid #888;
		width: 30%;
	}
	#content{
		text-align: center;
		font-size: 18px;
		padding: 10px;
		min-height: 350px;
		background-color: #EEEEEE;
		font-family: 'Titillium Web','Noto Sans KR','Malgun Gothic','맑은 고딕',Dotum,'돋움','AppleGothic','Apple SD Gothic Neo',sans-serif;
	}
</style>
<script>
$(function (){
	// 하루동안 보지 않기 설정시
	var check = localStorage.getItem('popUpSet');
	// 하루동안 보지 않기 체크한 날 +1
	var setDate = localStorage.getItem('today');
	// 오늘 날짜
	var today = getDate();
	
	if (check == 'show' || check == null){
		getPopup();
	} else if(check == 'hide'){
		// 하루동안 보지 않기 설정
		if(setDate != today){
			// 설정한지 하루가 지났다면
			localStorage.setItem("popUpSet",'show');
			getPopup();
		} else if(setDate == today && setDate != null && setDate != ""){
			$('#NoticeShowModal').hide();	
		}
	}
});
// 오늘 날짜 중 일만 가져오는 함수
function getDate(){
	const today = new Date();
	const saveDay = today.getDate();
	
	return saveDay;
}
// 팝업 띄우는 함수
function getPopup(){
	$.ajax({
		url: "/cmmn/noticePopup",
	    type: "POST",
	    dataType : "json",
	    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	    success : function(data){
	    	if(data.result.length > 0){
	    		for(i = 0; i < data.result.length; i++){
	    			$("#content").html(data.result[i].content);
	    		}
	    		$('#NoticeShowModal').show();
	    	}
		},
		error : function(request,status,error){
	    	alert("오류발생");
			console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" +  "error:" + error);
		}
	});
}

function modal_close(){
	var todayNotLook;
	
	if($('#todayNotLook').is(':checked')){
		// 오늘 하루 보지않기 선택
		localStorage.setItem("popUpSet",'hide');
		localStorage.setItem("today",getDate());
	} else{
		// 선택x
		localStorage.setItem("popUpSet",'show');
	}
	$('#NoticeShowModal').hide();
}

</script>
<div class="notice_modal" id="NoticeShowModal">
    <div class="modal-content">
    	<div class="modal-header">
        	<h4 class="modal-title" id="myModalLabel"><i class="fas fa-bullhorn"></i>&nbsp;&nbsp; 공&nbsp;&nbsp;지</h4>
        	<button id="close" onclick="modal_close();"> X </button>
      	</div>
		<div class="modal-body">
			<div id="content">
			</div>
		</div>
		<div class="modal-footer">
			<input type="checkbox" name="todayNotLook" id="todayNotLook"> 오늘 하루 보지않기
		</div>
    </div>
</div>
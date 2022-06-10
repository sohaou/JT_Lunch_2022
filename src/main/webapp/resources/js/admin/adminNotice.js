/**
 * 관리자 공지사항
 */
 var colM = [
	{ title: "NO", width: "10%", dataIndx: "noticeId", align: "center"},
    { title: "TITLE", width: "52%", dataIndx: "title", align: "left"},
    { title: "WRITER", width: "13%", dataIndx: "writer", align: "left" },
    { title: "DATE", width: "15%", dataIndx: "uploadDate", align: "center" },
    { title: "VIEW", width: "10%", dataIndx: "userCheck", align: "center" },
];

var dataVal;
var totalNotice;
// 현재페이지
var nowPage = 1;
// 데이터 출력 수
var rpp = 10;

var setGrid = {
	height: 'flex',
	editable: false,
	dataModel: dataVal,
	//scrollModel: { autoFit: false },
	colModel: colM,
	//numberCell: { show: false },
	menuIcon: true,
	selectionModel: { type: 'row' , fireSelectChange: true},           
	showTitle: false,
	resizable: true,
	hwrap: false,
	wrap: false,
	collapsible: false,
	//pageModel: { type: "remote", rPP: 10, strRpp: "{0}"},                   
	numberCell: { show: false, resizable: true, title: "#" },
	rowClick: function (evt, ui) {
		var selected = [],
			address = this.selection().address();
		var data = this.option('dataModel.data'),
			checked = [];
		// 상세보기 함수 호출
		DetailModalShow(data[address[0].r1].noticeId);
     },
	//fill the drop down upon creation of pqGrid.
	create: function (evt, ui) {
		var grid = this,
			$select_row = $(".select-row"),
			data = ui.dataModel.data;

		for (var i = 0; i < data.length; i++) {
			$select_row.append("<option>" + i + "</option>");
		}

		//bind select list change event.
		$select_row.bind("change", function (evt) {
			var rowIndx = $(this).val();

			grid.setSelection({ rowIndx: rowIndx, focus: true });
		}).change();
	}  
};
$(function (){
	//
	getData(null,null,null,null, nowPage, rpp);
	//jQuery 그리드 만들기
	grid = pq.grid("#grid_json", setGrid);

	$("#pop_up").change(function(){
	    if($("#pop_up").is(":checked")){
	        $("#popUpPeriod").show();
	    }else{
	        $("#popUpPeriod").hide();
	    }	
	});
	
	$("#Upop_up").change(function(){
	    if($("#Upop_up").is(":checked")){
	        $("#UpopUpPeriod").show();
	    }else{
	        $("#UpopUpPeriod").hide();
	    }	
	});
	
	// 팝업 등록 및 수정할 때 사용
	$(".startDt").datepicker({
	    dateFormat: "yy-mm-dd",
	    minDate: 0,
	    numberOfMonths: 1,
	    changeMonth: true,
	    showMonthAfterYear: true ,
	    changeYear: true,
	    dayNamesMin: ['일','월', '화', '수', '목', '금', '토'], 
	    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    onClose: function( selectedDate ) {
			// 시작일(startDt) datepicker가 닫힐 때
			$(".endDt").datepicker("option", "minDate", selectedDate);
	        if ($( ".endDt" ).val() < selectedDate){
	            $( ".endDt" ).val(selectedDate);
	        }
	    }
	}); 
	// end Date 설정시 end Date 가 start Date 보다 작을 경우 start Date를  end Date와 같게 설정
	$( ".endDt" ).datepicker({
	    dateFormat: "yy-mm-dd",
	    minDate : 0,
	    numberOfMonths: 1,
	    changeMonth: true,
	    showMonthAfterYear: true ,
	    changeYear: true,
	    dayNamesMin: ['일','월', '화', '수', '목', '금', '토'], 
	    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    onClose: function( selectedDate ) {
	        if ($(".startDt" ).val() > selectedDate){
	            $(".startDt" ).val(selectedDate);
	        }
	        if ($(".startDt" ).val() == null || $(".startDt" ).val() == ""){
	            $(".startDt" ).val(selectedDate);
	        }
	    }
	});
	// 공지사항 내용 검색할 때 사용
	$("#stDate").datepicker({
	    dateFormat: "yy-mm-dd",
	    maxDate: 0,
	    numberOfMonths: 1,
	    changeMonth: true,
	    showMonthAfterYear: true ,
	    changeYear: true,
	    dayNamesMin: ['일','월', '화', '수', '목', '금', '토'], 
	    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    onClose: function( selectedDate ) {
			// 시작일(startDt) datepicker가 닫힐 때
			$("#endDate").datepicker("option", "minDate", selectedDate);
	    }
	}); 

	$( "#endDate" ).datepicker({
	    dateFormat: "yy-mm-dd",
	    numberOfMonths: 1,
	    changeMonth: true,
	    showMonthAfterYear: true ,
	    changeYear: true,
	    dayNamesMin: ['일','월', '화', '수', '목', '금', '토'], 
	    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    onClose: function( selectedDate ) {
	        if ($("#stDate" ).val() > selectedDate){
	            $("#stDate" ).val(selectedDate);
	        }
	    }
	});
});

// 데이터 출력 개수 change
function rppChange(option){
	rpp = option;
	
	getData(null, null, null, null, nowPage, rpp);
}

// 검색초기화
function search_reset(){
	$("#stDate").val("");
	$("#endDate").val("");
	$("#stDate").datepicker('setDate', "");
	$("#endDate").datepicker('setDate', "");
	$("#keyword").val("");
	$('#searchOption').val('').prop("selected",true);
	
	getData(null, null, null, null, nowPage, rpp);
}

// 공지사항 검색
function getData(option, keyword, startDate, endDate, nowPage, cntPerPage){		
	
	$.ajax({
		url : "/admin/noticeSearch",
	    type : "POST",
	    async : false, // 결과값을 전역변수에 set하기 위해
	    // ajax는 비동기 방식. async 값을 false로 주면 동기방식으로 전역변수에 셋팅할 수 있게됨.
	    data : {
	    	"searchOption" : option,
	    	"keyword" : keyword,
	    	"startDate" : startDate,
	    	"endDate" : endDate,
	    	"nowPage" : nowPage,
	    	"cntPerPage" : cntPerPage
	    },
	    dataType : "json",
	    contentType : "application/x-www-form-urlencoded; charset=UTF-8",
	    success : function(dataJSON){
	    	setGrid.dataModel = { data: dataJSON.data };

	    	$( "#grid_json" ).pqGrid(setGrid);
	    	$( "#grid_json" ).pqGrid('refreshDataAndView');
	    	
	    	//$('#pageResult').html(dataJSON.paging);
	    	
	    	var paging = dataJSON.paging;
	    	let str = "";
	    	
	    	$("#page").empty();
	    	
	    	if(paging.startPage != 1){
				str+="<a href='javascript:void(0);' onclick='pageMove(" + Number(paging.startPage - 1) + "," + paging.cntPerPage + ")'></a>";
			}
			if(paging.startPage != paging.nowPage){
				str+="<a href='javascript:void(0);' onclick='pageMove(" + paging.startPage + "," + paging.cntPerPage + ")'>";
				str+="<img src='//img.echosting.cafe24.com/skin/base/common/btn_page_first.gif' alt='처음 페이지'>";
				str+="</a>";	
				str+="<a href='javascript:void(0);' onclick='pageMove(" + Number(paging.nowPage - 1) + "," + paging.cntPerPage + ")'>";
				str+="<img src='//img.echosting.cafe24.com/skin/base/common/btn_page_prev.gif' alt='이전 페이지'>";
				str+="</a>";	
			} else{
				str+="<img src='//img.echosting.cafe24.com/skin/base/common/btn_page_first.gif' alt='처음 페이지'>";
				str+="<img src='//img.echosting.cafe24.com/skin/base/common/btn_page_prev.gif' alt='이전 페이지'>";
			}	
			str+="<ol>";
			for(var p = paging.startPage; p <= paging.endPage; p++){
				if(p == paging.nowPage){
					str+="<li><a class='this'>" + p + "</a></li>";
				} else if(p != paging.nowPage){
					str+="<li><a href='javascript:void(0);' onclick='pageMove(" + p + "," + paging.cntPerPage + ")' class='other'>" + p + "</a></li>";
				}
			}
			if(paging.endPage != paging.lastPage){
				str+="<a href='javascript:void(0);' onclick='pageMove(" +  Number(paging.endPage + 1) + "," + paging.cntPerPage + ")'></a>";
			}
			str+="</ol>";
			if(paging.endPage != paging.nowPage){
				str+="<a href='javascript:void(0);' onclick='pageMove(" + Number(paging.nowPage + 1) + "," + paging.cntPerPage + ")'>";
				str+="<img src='//img.echosting.cafe24.com/skin/base/common/btn_page_next.gif' alt='다음 페이지'>";
				str+="</a>";
				str+="<a href='javascript:void(0);' onclick='pageMove(" + paging.endPage + "," + paging.cntPerPage + ")'>";
				str+="<img src='//img.echosting.cafe24.com/skin/base/common/btn_page_last.gif' alt='마지막 페이지'>";
				str+="</a>";	
			} else{
				str+="<img src='//img.echosting.cafe24.com/skin/base/common/btn_page_next.gif' alt='다음 페이지'>";
				str+="<img src='//img.echosting.cafe24.com/skin/base/common/btn_page_last.gif' alt='마지막 페이지'>";
			}
			
			$("#page").append(str);
		},
		error : function(request,status,error){
	    	alert(error);
			console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" +  "error:" + error);
		}
	});
}

// 검색
function check_value(){
	var option = $("#searchOption option:selected").val();
	var keyword = $("#keyword").val();
	var startDate = $("#stDate").val();
	var endDate = $("#endDate").val();
	
	if(option == "" && keyword == "" && startDate == "" && endDate == ""){
		alert('검색조건을 선택하세요'); //경고창 띄움 
 		document.searchForm.keyword.focus(); //다시 검색창으로 돌아감 
  		return;
	} else if(option != ""){
		// 검색 조건을 선택했는데
		// 검색어가 없을 경우
		if(keyword == "" || keyword == null || keyword.trim() == ""){
			alert('검색어 입력하세요'); //경고창 띄움 
	 		document.searchForm.keyword.focus(); //다시 검색창으로 돌아감 
	  		return;
		} else{
			getData(option, keyword, startDate, endDate, nowPage, rpp);
		}
	} else{
		getData(option, keyword, startDate, endDate, nowPage, rpp);
	}
}

// 공지사항 등록 모달창 표시
function InsertModalShow() {	
	$('#NoticeInsertModal').modal('show');
}

// 공지사항 등록 모달창 닫기
function InsertModalHide(){
	$('#title').val("");
	$('#content').val("");
	$('#pop_up').prop("checked", false);  
	
	$('#startDt').val("");
	$('#endDt').val("");
	
	$('#NoticeInsertModal').modal('hide');
}

// 공지사항 수정 모달창 닫기
function UpdateModalHide(){
	$('#Upop_up').prop("checked", false);  
	
	$('#UstartDt').val("");
	$('#UendDt').val("");
	
	$('#NoticeUpdateModal').modal('hide');
}

// 공지사항 내용 임력확인
function check_input() {
	if (document.noticeInsertForm.title.value == ""){
		alert("제목을 입력해주세요!");
		document.noticeInsertForm.title.focus();
		return;
	} else if (!document.noticeInsertForm.content.value){
		alert("내용을 입력해주세요!");
		document.noticeInsertForm.content.focus();
		return;
	}
	
	if($('#pop_up').is(':checked')){
		$('#pop_up').val('1');
		if(!$('#startDt').val() || !$('#endDt').val()){
			alert("팝업 등록 기간을 선택해주세요!");
			$('#startDt').focus();
			return;
		}
	} else{
		$('#pop_up').val('0');
	}
	
	$.ajax({
		url: "/admin/noticeInsert",
	    type: "POST",
	    data : $("#noticeInsertForm").serialize(),
	    success : function(data){
			alert(data);
		},
		complete : function(data){
			location.href='/admin/notice';
		},
		error:function(request,status,error) {
	    	alert("오류발생");
	    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

// 특수문자 변환
function decodeHtml(str){
	if(str != undefined && str != null && str != ''){
		str = String(str);
		
		str = str.replace(/<script[^>]*>([\S\s]*?)<\/script>/gmi,'');
		str = str.replace(/<\/?\w(?:[^"'>]|"[^"]*"|'[^']*')*>/gmi,'');
		var element = document.createElement('div');
		element.innerHTML = str;
		str = element.textContent;
		element.textContent = '';
	}
	return str;
}

//공지사항 상세보기, 수정 모달창 표시
function DetailModalShow(id){
	$.ajax({
		url: "/admin/noticedetails",
	    type: "POST",
	    data: {id : id},
	    success : function(data){
	    	$('#Utitle').val(decodeHtml(data.result.title));
	    	$('#Ucontent').val(decodeHtml(data.result.content));
	    	$('#Unotice_id').val(data.result.notice_id);
	    	
	    	if(data.result.pop_up == 1){
				$('#Upop_up').prop('checked',true);
				$('#UstartDt').val(data.result.pop_up_start_date);
				$('#UendDt').val(data.result.pop_up_end_date);
				$('#UpopUpPeriod').show();
			} else{
				$("#UpopUpPeriod").hide();
			}
		},
		error:function(request,status,error){
	    	alert("오류발생");
	    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});	
	$('#NoticeUpdateModal').modal('show');
}

// 공지사항 수정
function notice_update(){
	if (!$('#Utitle').val()){
		alert("제목을 입력해주세요!");
		$('#Utitle').focus();
		return;
	} else if (!$('#Ucontent').val()){
		alert("내용을 입력해주세요!");
		$('#Ucontent').focus();
		return;
	}
	
	if($('#Upop_up').is(':checked')){
		$('#Upop_up').val('1');
	} else{
		$('#Upop_up').val('0');
		$('#UstartDt').val("");
		$('#UendDt').val("");
	}
	
	$.ajax({
		url: "/admin/noticeUpdate",
	    type: "POST",
	    data : $("#noticeUpdateForm").serialize(),
	    success : function(data){
			alert(data);
		},
		complete : function(){
			location.href='/admin/notice';
		},
		error:function(request,status,error) {
	    	alert("오류발생");
	    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

// 공지사항 삭제
function notice_delete() {
	var str = $('#Unotice_id').val();
	var deleteStr = confirm('정말 삭제하시겠습니까?');
	
	if(deleteStr){
		$.ajax({
			url: "/admin/noticeDelete",
		    type: "POST",
		    data : {notice_id:str},
		    success : function(data){
		    	alert(data);
			},
			complete : function(){
				location.reload();
			},
			error:function(request,status,error) {
		    	alert("오류발생");
		    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
}

// excel 다운로드
function excelDownLoad(type){
	var option = $("#searchOption option:selected").val();
	var keyword = $("#keyword").val();
	var startDate = $("#stDate").val();
	var endDate = $("#endDate").val();
	
	location.href="/excel/notice?type=" + type + "&searchOption=" + option + "&keyword=" + 
					keyword + "&startDate=" + startDate + "&endDate=" + endDate;
}

function pageMove(nowPage, cntPerPage){
	var option = $("#searchOption option:selected").val();
	var keyword = $("#keyword").val();
	var startDate = $("#stDate").val();
	var endDate = $("#endDate").val();
	
	if(option != null && option != "" || keyword != null && keyword != "" || startDate != null && startDate != "" || endDate != null && endDate != ""){
		getData(option, keyword, startDate, endDate, nowPage, cntPerPage);
	} else{
		getData(null,null,null,null,nowPage,cntPerPage);
	}
	
}



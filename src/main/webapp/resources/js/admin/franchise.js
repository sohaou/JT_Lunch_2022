/**
 * 
 */
/*$(document).ready(function() {
	getData(null,null);
});*/

// 현재페이지
var nowPage = 1;
// 데이터 출력 수
var rpp = 10;

 var colM = [
	{ title: "분류", width: "7%", dataIndx: "typestr", align: "center"},
    { title: "ID", width: "10%", dataIndx: "id", align: "left"},
    { title: "지점명", width: "13%", dataIndx: "shopname", align: "left" },
    { title: "주소", width: "40%", dataIndx: "address", align: "left" },
    { title: "전화번호", width: "13%", dataIndx: "tel", align: "left" },
    { title: "데이터 전송 주소", width: "17%", dataIndx: "dataurl", align: "left" }
];

var dataVal;

var setGrid = {
     height: 'flex',
     /*toolbar: {
         items: [
         {
             type: 'select',
             label: '파일형식 : ',                
             attr: 'id = "export_format"',
             options: [{ xlsx: 'Excel', csv: 'Csv', html: 'Html', json: 'Json'}]
         },
         {
             type: 'button',
             label: "저장",
             icon: 'ui-icon-arrowthickstop-1-s',
             listener: function () {
                 var format = $("#export_format").val(),                            
                     blob = this.exportData({
                         format : format,        
                         nopqdata : true, //applicable for JSON export.                        
                         render : true
                     });
                 if(typeof blob === "string"){                            
                     blob = new Blob([blob]);
                 }
                 saveAs(blob, "pqGrid." + format);
             }
         }]
     },*/
     editable: false,
     dataModel: dataVal,
     scrollModel: { autoFit: true },
     colModel: colM,
     //numberCell: { show: false },
     menuIcon: true,
     selectionModel: { type: 'row' , fireSelectChange: true},           
     showTitle: false,
     resizable: true,
     hwrap: false,
     wrap: false,
     collapsible: false,
    // pageModel: { type: "local", rPP: 10, strRpp: "{0}", strDisplay: "{0} to {1} of {2}" },                   
     numberCell: { show: false, resizable: true, title: "#" },
     rowClick: function (evt, ui) {
         var selected = [],
             address = this.selection().address();
         var data = this.option('dataModel.data'),
         checked = [];
		//let selectRow = address[0].rl;
		DetailModalShow(data[address[0].r1].id);
		console.log(data[address[0].r1].id);
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
 
//jQuery 그리드 만들기
$(function (){
	getData(null,null,nowPage,rpp);
	grid = pq.grid("#grid_json", setGrid);
});

function getData(option, keyword, nowPage,cntPerPage){
	$.ajax({
		url: "/admin/franchiseSearch",
	    type: "POST",
	    data : {
	    	"searchOption" : option,
	    	"keyword" : keyword,
	    	"nowPage" : nowPage,
	    	"cntPerPage" : cntPerPage
	    },
	    dataType : "json",
	    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	    success : function(dataJSON){
	    	setGrid.dataModel = { data: dataJSON.data };
	    	$( "#grid_json" ).pqGrid(setGrid);
	    	$( "#grid_json" ).pqGrid('refreshDataAndView');
	    	
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

// 검색초기화
function search_reset(){
	$("#keyword").val("");
	$('#searchOption').val('').prop("selected",true);
	
	getData(null,null,nowPage,rpp);
}
// 검색
function check_value(){
	var option = $("#searchOption option:selected").val();
	var keyword = $("#keyword").val();
	
	if(keyword == "" || keyword == null || keyword.trim() == ""){ //검색어가 없을 경우  
 		alert('검색어를 입력하세요'); //경고창 띄움 
 		document.searchForm.keyword.focus(); //다시 검색창으로 돌아감 
  		return; 
  	} else{
		getData(option, keyword, nowPage, rpp);	
	}
}
// 주소 입력
function inputAddress() {
    new daum.Postcode({
        oncomplete: function(data) {
        	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById("ZIP_CODE").value = data.zonecode;
            document.getElementById("ADDRESS").value = roadAddr;
			//
			document.getElementById("UZIP_CODE").value = data.zonecode;
            document.getElementById("UADDRESS").value = roadAddr;
            document.getElementById("UDETAIL_ADDRESS").value = "";
            
            var guideTextBox = document.getElementById("guide");
        }
    }).open();
}

// 체인점 등록 모달창 표시
function InsertModalShow() {	
	$('#FranchiseInsertModal').modal('show');
}

// 체인점 등록 모달창 닫기
function InsertModalHide(){
	$('#ID').val("");
	$('#PW').val("");
	$('#PWCheck').val("");
	$('#PWCheckValue').val('1');
	$('#SHOP_NAME').val("");
	$('#phone1').val("");
	$('#phone2').val("");
	$('#ZIP_CODE').val("");
	$('#ADDRESS').val("");
	$('#DETAIL_ADDRESS').val("");
	
	$('#FranchiseInsertModal').modal('hide');
}

//아이디 중복 확인
function checkID() {
	var inputid = document.FranchiseInsertForm.ID.value;
	var regExp = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi; 
	
	if(inputid == "" || inputid == null){
		alert("아이디를 입력해주세요!");
		document.FranchiseInsertForm.ID.focus();
		return;
	} else if(regExp.test(inputid)){
		alert("특수문자는 입력하실수 없습니다.");
		$('#ID').val('');
		return;
	} else if(inputid.length < 4){
		alert("아이디는 4글자 이상 입력해주세요!");
		document.FranchiseInsertForm.ID.focus();
		return;
	}else{
		$.ajax({
			url : "/admin/franchiseIDCheck",
		    type : "POST",
		    data : {inputId : inputid},
		    success : function(data){
				if(data.length > 0){
					alert(data);
					$('#IDCheck').val('0');
					$('#ID').val('');
					$('#idCheckDiv').hide();
				} else{
					alert("사용 가능한 아이디입니다.");
					$('#IDCheck').val('1');
					$('#idCheckDiv').show();
				}
			},
			error : function(error){
		    	alert("오류 발생!");
		    	console.log(error);
			}
		});		
	}
}

function checkIDreset(){
	$('#IDCheck').val('0');
	$('#idCheckDiv').hide();
}
// 비밀번호 유효성 검사
function checkPW(str){
	if(str == "U"){
		var pw = $("#UPW").val();
		var num = pw.search(/[0-9]/g);
		var eng = pw.search(/[a-z]/ig);
		var spe = pw.search(/[!@#$%^&*]/gi);
		
		if (pw.length < 8 || pw.length > 16){
			$("#UpwAlert").html("8~16자 영문 대 소문자, 숫자, 특수문자 중 2종류 포함.<br>(단, 특수문자는 !@#$%^&* 만 가능)");
			$('#UPWValue').val('0');
			return;
		}else if(pw.search(/\s/) != -1){
			$("#UpwAlert").html("8~16자 영문 대 소문자, 숫자, 특수문자 중 2종류 포함.<br>(단, 특수문자는 !@#$%^&* 만 가능)"); 
			$('#UPWValue').val('0');
			return;
		}else if( (num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0) ){
			$("#UpwAlert").html("8~16자 영문 대 소문자, 숫자, 특수문자 중 2종류 포함.<br>(단, 특수문자는 !@#$%^&* 만 가능)"); 
			$('#UPWValue').val('0');
			return;
		}else{
			$("#UpwAlert").html(""); 
			$('#UPWValue').val('1');
		}
		pwSameCheck("U");
	} else{
		var pw = $("#PW").val();
		var num = pw.search(/[0-9]/g);
		var eng = pw.search(/[a-z]/ig);
		var spe = pw.search(/[!@#$%^&*]/gi);
		
		if (pw.length < 8 || pw.length > 16){
			$("#pwAlert").html("8~16자 영문 대 소문자, 숫자, 특수문자 중 2종류 포함.<br>(단, 특수문자는 !@#$%^&* 만 가능)");
			return;
		}else if(pw.search(/\s/) != -1){
			$("#pwAlert").html("8~16자 영문 대 소문자, 숫자, 특수문자 중 2종류 포함.<br>(단, 특수문자는 !@#$%^&* 만 가능)"); 
			return;
		}else if( (num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0) ){
			$("#pwAlert").html("8~16자 영문 대 소문자, 숫자, 특수문자 중 2종류 포함.<br>(단, 특수문자는 !@#$%^&* 만 가능)"); 
			return;
		}else{
			$("#pwAlert").html(""); 
			$('#PWValue').val('1');
		}
		pwSameCheck();	
	}
}

// 비밀번호 일치 검사
function pwSameCheck(str){
	if(str == "U"){
		var pw = $("#UPW").val();
		var pwCheck = $("#UPWCheck").val();
		
		if(pw == pwCheck){
			$("#UpwCheck").html("");
			$('#UPWCheckValue').val('1');
		} else{
			$('#UPWCheckValue').val('0');
			$("#UpwCheck").html("비밀번호가 일치하지 않습니다.");
		}
	} else{
		var pw = $("#PW").val();
		var pwCheck = $("#PWCheck").val();
		
		if(pw == pwCheck){
			$("#pwCheck").html("");
			$('#PWCheckValue').val('1');
		} else{
			$('#PWCheckValue').val('0');
			$("#pwCheck").html("비밀번호가 일치하지 않습니다.");
		}	
	}
}

// 체인점 등록
$(function(){
	// 체인점 등록
	$('#submit').on("click",function(){
		var form1 = $("#FranchiseInsertForm").serialize();
		var form = document.FranchiseInsertForm;
    	
        if(!form.ID.value){
            alert("아이디를 입력하세요.");
            form.ID.focus();
            return;
        }
        if(form.IDCheck.value == "0"){
        	alert("아이디 중복체크를 해주세요.");
        	form.ID.focus();
        	return;
        } 
        if(!form.PW.value){
            alert("비밀번호를 입력하세요.");
            form.PW.focus();
            return;
        }
        // 비밀번호를 올바르게 입력했는지
        if(form.PWValue.value != '1'){
            alert("비밀번호를 올바르게 입력하세요.");
            form.PW.focus();
            return;
        }if(form.PWCheckValue.value != '1'){
            alert("비밀번호를 동일하게 입력하세요.");
            form.PWCheck.focus();
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
        if(form.phone1.value.length != 4 || form.phone2.value.length != 4){
			alert("전화번호를 올바르게 입력하세요.");
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
        if(!form.PORT_NUM.value){
            alert("데이터 전송 포트번호를 입력하세요.");
            form.PORT_NUM.focus();
            return;
        } 
    	$.ajax({
 			url: "/admin/franchiseInsert",
 		    type: "POST",
 		    data: $("#FranchiseInsertForm").serialize(),
 		    success : function(data){
 				alert(data);
 				console.log(data);
 			},
 			complete : function(data){
 				location.href="/admin/franchiseList";
 			},
 			error:function(request,status,error)
 		    {
				alert("체인점 등록에 실패하였습니다. 다시 시도해주세요.");
 				console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
 			}
 		});
	})
	
	// 체인점 수정
	$('#Usubmit').on("click",function(){
		var form1 = $("#FranchiseUpdateForm").serialize();
		var form = document.FranchiseUpdateForm;
    	
    	checkPW('U');
    	
        if(!form.UPW.value){
            alert("비밀번호를 입력하세요.");
            form.UPW.focus();
            return;
        }
        // 비밀번호를 올바르게 입력했는지
        if(form.UPWValue.value != '1'){
            alert("비밀번호를 올바르게 입력하세요.");
            form.UPWValue.focus();
            return;
        }if(form.UPWCheckValue.value != '1'){
            alert("비밀번호를 동일하게 입력하세요.");
            form.UPWCheck.focus();
            return;
        }
        if(!form.USHOP_NAME.value){
            alert("지점명을 입력하세요.");
            form.USHOP_NAME.focus();
            return;
        }
        if(!form.Uphone1.value || !form.Uphone2.value){
            alert("전화번호를 입력하세요.");
            form.Uphone1.focus();
            return;
        } 
        if(form.Uphone1.value.length != 4 || form.Uphone2.value.length != 4){
			alert("전화번호를 올바르게 입력하세요.");
			form.Uphone1.focus();
			return;
		}
        if(!form.ZIP_CODE.value || !form.UADDRESS.value || !form.UDETAIL_ADDRESS.value){
            alert("주소를 입력하세요.");
            form.UZIP_CODE.focus();
            return;
        } 
        if(!form.UDATA_URL.value){
            alert("데이터 전송 주소를 입력하세요.");
            form.UDATA_URL.focus();
            return;
        } 
        if(!form.UPORT_NUM.value){
            alert("데이터 전송 포트번호를 입력하세요.");
            form.UPORT_NUM.focus();
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
	// 체인점 삭제
	$('#deleteBtn').on("click",function(){
		var str = $("#UID").val();
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
	})
})

//체인점 상세보기, 수정 모달창 표시
function DetailModalShow(id){
	$.ajax({
		url: "/admin/franchiseDetail",
	    type: "POST",
	    data: {id : id},
	    success : function(data){
	    	console.log(data.result);
	    	$("input:radio[name='TYPE']:radio[value='" + data.result.type + "']").prop('checked', true); // 선택하기
	    	$("#UFranchiseID").html(data.result.id);
	    	$("#UID").val(data.result.id);
	    	$("#UPW").val(data.result.pw);
	    	$("#UPWCheck").val(data.result.pw);
	    	$("#USHOP_NAME").val(data.result.shop_NAME);
	    	let tel = data.result.tel;
	    	$("#Uphone1").val(tel.substr(4,4));
	    	$("#Uphone2").val(tel.substr(9,4));
	    	$("#UZIP_CODE").val(data.result.zip_CODE);
	    	$("#UADDRESS").val(data.result.address);
	    	$("#UDETAIL_ADDRESS").val(data.result.detail_ADDRESS);
	    	$("#UDATA_URL").val(data.result.data_URL);
	    	$("#UPORT_NUM").val(data.result.port_NUM);
		},
		complete : function(data){
			checkPW("U");	
		},
		error:function(request,status,error)
	    {
	    	alert(error);
		}
	});	
	$('#FranchiseDetailModal').modal('show');
}

// 폼 닫기
function form_reset(modalID, formID){
	$("#" + formID).reset();
	$("#" + modalID).modal('hide');
}

// excel 다운로드
function excelDownLoad(type){
	var option = $("#searchOption option:selected").val();
	var keyword = $("#keyword").val();
	var startDate = $("#stDate").val();
	var endDate = $("#endDate").val();
	
	location.href="/excel/franchise?type=" + type + "&searchOption=" + option + "&keyword=" + 
					keyword + "&startDate=" + startDate + "&endDate=" + endDate;
}

function pageMove(nowPage, cntPerPage){
	getData(null,null,nowPage,cntPerPage);
}

// 데이터 출력 개수 change
function rppChange(option){
	rpp = option;
	
	getData(null, null, nowPage, rpp);
}
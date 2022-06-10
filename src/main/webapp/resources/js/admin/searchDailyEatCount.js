/**
 * 통계 - 기간 선택하여 식사자 수 검색
 */ 
var data;
var chart;
var series1;
var series2;
var xAxis;
var legend;
var franchise = "";

// 기간 선택할 때 달력 표시
// start Date 설정시 end Date 가 start Date보다 작을 경우 end Date를 start Date와 같게 설정
$(function (){
	// 오늘날짜
	var today = get_today('date',null);

	$("#startDt").datepicker({
	    dateFormat: "yy-mm-dd",
	    defaultDate: "-1D",
	    maxDate: "-1D",
	    numberOfMonths: 1,
	    changeMonth: true,
	    showMonthAfterYear: true ,
	    changeYear: true,
	    dayNamesMin: ['일','월', '화', '수', '목', '금', '토'], 
	    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    onClose: function( selectedDate ) {
			// 시작일(startDt) datepicker가 닫힐 때
			// 종료일(endDt)의 선택할 수 있는 최대 날짜를(maxDate)를 선택한 날짜에서 3개월까지로 지정
			var curDate = $("#startDt").datepicker("getDate");
			if( curDate.setMonth(curDate.getMonth() + 3) <= today.valueOf()){
				$("#endDt").datepicker("option", "maxDate", curDate);
			}
			//curDate.setDate(curDate.getYear() + 1);
			$("#endDt").datepicker("option", "minDate", selectedDate);
	        if ($( "#endDt" ).val() < selectedDate){
	            $( "#endDt" ).val(selectedDate);
	        }
	    }
	}); 
	// end Date 설정시 end Date 가 start Date 보다 작을 경우 start Date를  end Date와 같게 설정
	
	$( "#endDt" ).datepicker({
	    dateFormat: "yy-mm-dd",
	    //defaultDate: "-1D",
	    maxDate: "-1D",
	    numberOfMonths: 1,
	    changeMonth: true,
	    showMonthAfterYear: true ,
	    changeYear: true,
	    dayNamesMin: ['일','월', '화', '수', '목', '금', '토'], 
	    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    onClose: function( selectedDate ) {
	        if ($("#startDt" ).val() > selectedDate){
	            $("#startDt" ).val(selectedDate);
	        }
	    }
	});
	
	/*data = get_SearchData(null, null, null);
	$("#1m").css('color','red');*/
	last_data("1m");
	set_select();
});
/*
window.onload = function(){
	data = get_SearchData(null, null, null);
	set_select();
}
*/
// 오늘 날짜 구하기
function get_today(type, data){
	var date;
	var month;
	
	if(data != null && data != ""){
		date = data;
		month = date.getMonth();
	} else{
		date = new Date();
		month = date.getMonth();
		month += 1;
	}
	
	var year = date.getFullYear();
	
	if(month < 10){
		month = "0" + month;
	}
	var day = date.getDate();
	if(day < 10){
		day = "0" + day;
	}
	var result;
	if(type == 'date'){
		result = new Date(year, month, day);
	} else if(type == 'str'){
		result = year + '-' + month + '-' + day;
	} else if(type == 'first'){
		result = year + '-' + month + '-01';
	}
	
	return result;
}
// 체인점 이름 select box set
function set_select(){
	$.ajax({
		url : "/getSelect",
		type : "POST",
		dataType : "json",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(data){
			data = data.result;
			$("#searchOption").empty();
			$("#searchOption").append("<option value=''>----- 전 체 -----</option>");
			// 검색조건 셀렉트 박스에 지점명 추가
			for(i = 0; i < data.length; i++){
				var option = $("<option value=" + data[i].id + ">" + data[i].shopname + "</option>");
				$("#searchOption").append(option);
			}         
		},
		error : function(error){
			alert("오류발생!");
			cosole.log(error);
		}				
	});
}

// 차트그리기
am5.ready(function() {

// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("chartdiv");

// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root.setThemes([am5themes_Animated.new(root)]);

// Create chart
// https://www.amcharts.com/docs/v5/charts/xy-chart/
chart = root.container.children.push(
  am5xy.XYChart.new(root, {
    panX: false,
    panY: false,
    wheelX: "panX",
    wheelY: "zoomX",
    layout: root.verticalLayout
  })
);

// Add scrollbar
// https://www.amcharts.com/docs/v5/charts/xy-chart/scrollbars/
/*chart.set(
  "scrollbarX",
  am5.Scrollbar.new(root, {
    orientation: "horizontal"
  })
);*/

// Create axes
// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
xAxis = chart.xAxes.push(
  am5xy.CategoryAxis.new(root, {
    categoryField: "date",
    renderer: am5xy.AxisRendererX.new(root, { minGridDistance: 50 }),
    tooltip: am5.Tooltip.new(root, {})
  })
);


var yAxis = chart.yAxes.push(
  am5xy.ValueAxis.new(root, {
    min: 0,
    extraMax: 0.1,
    renderer: am5xy.AxisRendererY.new(root, {})
  })
);


// Add series
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/

series1 = chart.series.push(
  am5xy.ColumnSeries.new(root, {
    name: "전체 식사자 수",
    xAxis: xAxis,
    yAxis: yAxis,
    valueYField: "total",
    categoryXField: "date",
    tooltip:am5.Tooltip.new(root, {
      pointerOrientation:"horizontal",
      labelText:"{name} : {valueY} 명"
    })
  })
);

/*series1.columns.template.setAll({
  tooltipY: am5.percent(10),
  templateField: "columnSettings"
});*/

//series1.data.setAll(data);

series2 = chart.series.push(
  am5xy.LineSeries.new(root, {
    name: "전체 식사자 수",
    xAxis: xAxis,
    yAxis: yAxis,
    valueYField: "value",
    categoryXField: "date",
    tooltip:am5.Tooltip.new(root, {
      pointerOrientation:"horizontal",
      labelText:"{name} : {valueY} 명"
    })    
  })
);

series2.strokes.template.setAll({
  strokeWidth: 3
  /*templateField: "strokeSettings"*/
});


//series2.data.setAll(data);

series2.bullets.push(function () {
  return am5.Bullet.new(root, {
    sprite: am5.Circle.new(root, {
      strokeWidth: 3,
      stroke: series2.get("stroke"),
      radius: 5,
      fill: root.interfaceColors.get("background")
    })
  });
});

chart.set("cursor", am5xy.XYCursor.new(root, {}));

// Add legend
// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
legend = chart.children.push(
  am5.Legend.new(root, {
    centerX: am5.p50,
    x: am5.p50
  })
);
legend.data.setAll(chart.series.values);

// Make stuff animate on load
// https://www.amcharts.com/docs/v5/concepts/animations/
chart.appear(1000, 100);
series1.appear();

}); // end am5.ready()

// 검색 조건 입력 여부 확인
function check_input() {
	franchise = $("#searchOption option:selected").text();
	if(franchise == "----- 전 체 -----"){
		series2.set("name","전체 식사자 수");	
	} else{
		series2.set("name",franchise + " 식사자 수");	
	}
	var id = $("#searchOption").val();
	var startDt = $("#startDt").val();
	var endDt = $("#endDt").val();
	
	if (!startDt && endDt == ""){
        alert("기간을 입력해주세요!");
        $("#startDt").focus();
        return;
    } else{
		last_data('a');
    	get_SearchData(id, startDt, endDt);
    }
}
// 검색초기화
function search_reset(){
	$('#searchOption').val('').prop("selected",true);
	
	last_data("1m");
	set_select();
}
 // 검색
 function get_SearchData(id, startDt, endDt){
	$.ajax({
		url : "/search/dailyEatCount",
		type : "POST",
		data : {
			"id" : id,
			"startDate" : startDt,
			"endDate" : endDt
		},
		dataType : "json",
		success : function(data){
			data = data.result;
			
			if(data == null || data == "" || data.length == 0){
				alert("식사자 수 정보가 존재하지 않습니다.");
			}
			xAxis.data.setAll(data);       
			series1.data.setAll(data);
			series2.data.setAll(data);   
			legend.data.setAll(chart.series.values);
		},
		error : function(request,status,error){
	    	alert("오류발생!");
			console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" +  "error:" + error);
		}			
	});
}
// 최근 데이터 검색 (1주일, 1개월, 3개월)
function last_data(type){
	// 오늘날짜
	var today = get_today('date',null);
	// 시작날짜
	var startDt;
	// 끝날짜
	var endDt = get_today('str',null);

	franchise = $("#searchOption option:selected").text();
	if(franchise == "----- 전 체 -----"){
		series2.set("name","전체 식사자 수");	
	} else{
		series2.set("name",franchise + " 식사자 수");	
	}
	
	var id = $("#searchOption").val();
	if(type == '1w'){
		$("#1m").css('color','black');
		$("#tm").css('color','black');
		$("#1w").css('color','red');
		
/*		$("#searchOption").val("").prop("selected", true);
		series2.set("name","전체 식사자 수");*/
		
		// 최근 1주일
		today.setDate(today.getDate() - 7);
		startDt = get_today('str',today);
		$("#startDt").val(startDt);
		$("#endDt").val(endDt);
		get_SearchData(id, startDt, endDt);
	} else if(type == 'tm'){
		$("#1m").css('color','black');
		$("#tm").css('color','red');
		$("#1w").css('color','black');
		
		/*$("#searchOption").val("").prop("selected", true);
		series2.set("name","전체 식사자 수");*/
		
		// 이번달
		startDt = get_today('first',null);
		$("#startDt").val(startDt);
		$("#endDt").val(endDt);
		get_SearchData(id, startDt, endDt);
	} else if(type == '1m'){
		$("#1w").css('color','black');
		$("#tm").css('color','black');
		$("#1m").css('color','red');
		
		/*$("#searchOption").val("").prop("selected", true);
		series2.set("name","전체 식사자 수");*/
		
		// 이번달
		today.setMonth(today.getMonth() - 1);
		startDt = get_today('str',today);
		$("#startDt").val(startDt);
		$("#endDt").val(endDt);
		get_SearchData(id, startDt, endDt);
	} else if(type == 'a'){
		$("#1w").css('color','black');
		$("#tm").css('color','black');
		$("#1m").css('color','black');
	}
}
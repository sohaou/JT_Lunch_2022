<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/adminHeader.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Styles -->
<style>
#chartdiv {
  width: 100%;
  height: 500px;
  text-align: center;
}
</style>

<!-- Resources -->
<script src="https://cdn.amcharts.com/lib/5/index.js"></script>
<script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
<script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>

<!-- Chart code -->
<script>
$(function (){
var count = '${total}';
var date = '${eatDate}';

if(Number(count) == 0){
	$('#chartdiv').append("<p style='font-size: 20px; font-weight: bold;' margin-top: 60px;>" + date + " 에 식사자가 없습니다.</p>" );
} else {
am5.ready(function() {
	// Create root element
	// https://www.amcharts.com/docs/v5/getting-started/#Root_element
	var root = am5.Root.new("chartdiv");
	
	
	// Set themes
	// https://www.amcharts.com/docs/v5/concepts/themes/
	root.setThemes([
	  am5themes_Animated.new(root)
	]);
	
	
	// Create chart
	// https://www.amcharts.com/docs/v5/charts/xy-chart/
	var chart = root.container.children.push(am5xy.XYChart.new(root, {
	  panX: false,
	  panY: false,
	  wheelX: "none",
	  wheelY: "none"
	}));
	
	// We don't want zoom-out button to appear while animating, so we hide it
	chart.zoomOutButton.set("forceHidden", true);
	
	
	// Create axes
	// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
	var yRenderer = am5xy.AxisRendererY.new(root, {
	  minGridDistance: 30
	});
	
	var yAxis = chart.yAxes.push(am5xy.CategoryAxis.new(root, {
	  maxDeviation: 0,
	  categoryField: "shopName",
	  renderer: yRenderer,
	  tooltip: am5.Tooltip.new(root, { themeTags: ["axis"] })
	}));
	
	var xAxis = chart.xAxes.push(am5xy.ValueAxis.new(root, {
	  maxDeviation: 0,
	  min: 0,
	  extraMax:0.1,
	  renderer: am5xy.AxisRendererX.new(root, {})
	}));
	
	
	// Add series
	// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
	var series = chart.series.push(am5xy.ColumnSeries.new(root, {
	  name: "Series 1",
	  xAxis: xAxis,
	  yAxis: yAxis,
	  valueXField: "countEat",
	  categoryYField: "shopName",
	  tooltip: am5.Tooltip.new(root, {
	    pointerOrientation: "left",
	    labelText: "{valueX}"
	  })
	}));
	
	
	// Rounded corners for columns
	series.columns.template.setAll({
	  cornerRadiusTR: 5,
	  cornerRadiusBR: 5
	});
	
	// Make each column to be of a different color
	series.columns.template.adapters.add("fill", function(fill, target) {
	  return chart.get("colors").getIndex(series.columns.indexOf(target));
	});
	
	series.columns.template.adapters.add("stroke", function(stroke, target) {
	  return chart.get("colors").getIndex(series.columns.indexOf(target));
	});
	
	
	// Set data
	var data = ${chartJSON};
	
	yAxis.data.setAll(data);
	series.data.setAll(data);
	sortCategoryAxis();
	
	// Get series item by category
	function getSeriesItem(category) {
	  for (var i = 0; i < series.dataItems.length; i++) {
	    var dataItem = series.dataItems[i];
	    if (dataItem.get("categoryY") == category) {
	      return dataItem;
	    }
	  }
	}
	
	chart.set("cursor", am5xy.XYCursor.new(root, {
	  behavior: "none",
	  xAxis: xAxis,
	  yAxis: yAxis
	}));
	
	
	// Axis sorting
	function sortCategoryAxis() {
	
	  // Sort by value
	  series.dataItems.sort(function(x, y) {
	    return x.get("valueX") - y.get("valueX"); // descending
	    //return y.get("valueY") - x.get("valueX"); // ascending
	  })
	
	  // Go through each axis item
	  am5.array.each(yAxis.dataItems, function(dataItem) {
	    // get corresponding series item
	    var seriesDataItem = getSeriesItem(dataItem.get("category"));
	
	    if (seriesDataItem) {
	      // get index of series data item
	      var index = series.dataItems.indexOf(seriesDataItem);
	      // calculate delta position
	      var deltaPosition = (index - dataItem.get("index", 0)) / series.dataItems.length;
	      // set index to be the same as series data item index
	      dataItem.set("index", index);
	      // set deltaPosition instanlty
	      dataItem.set("deltaPosition", -deltaPosition);
	      // animate delta position to 0
	      dataItem.animate({
	        key: "deltaPosition",
	        to: 0,
	        duration: 1000,
	        easing: am5.ease.out(am5.ease.cubic)
	      })
	    }
	  });
	
	  // Sort axis items by index.
	  // This changes the order instantly, but as deltaPosition is set,
	  // they keep in the same places and then animate to true positions.
	  yAxis.dataItems.sort(function(x, y) {
	    return x.get("index") - y.get("index");
	  });
	}
	
	// Make stuff animate on load
	// https://www.amcharts.com/docs/v5/concepts/animations/
	series.appear(1000);
	chart.appear(1000, 100);
}); // end am5.ready()
}
});
</script>
<main>
	<div class="container-fluid px-4">
		<h1 class="mt-4">일간 식사자 수</h1>
		<ol class="breadcrumb mb-4">
        	<li style="font-size: 24px; margin-left: 10px; font-weight: bold;">${eatDate}</li>
        	<li style="font-size: 25px; font-weight: bold;">
        		<ul style="float: right;">Total : <font style="text-decoration: underline;">${total}</font> 명</ul>
        	</li>
		</ol>
	</div>
	<div id="chartdiv"></div>
</main>
<%@ include file="/WEB-INF/layout/adminFooter.jspf" %>
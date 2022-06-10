<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/adminHeader.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Resources -->
<script src="https://cdn.amcharts.com/lib/5/index.js"></script>
<script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
<script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/searchDailyEatCount.js?ver=4" ></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/admin/searchDailyEatCount.css?ver=2"> 
<main>
	<div class="container-fluid px-4">
		<h1 class="mt-4">통계</h1>
	</div>
	<div class="periodDiv" id="PeriodSet">
       	<form method="post" name="SearchForm" role="form" autocomplete="off">
       		<select class="select" name="searchOption" id="searchOption" style="margin-left: 10px;">
				<option value="">----- 전 체 -----</option>
			</select>
        	<input type="text" id="startDt" name="startDt"/> ~ <input type="text" id="endDt" name="endDt"/>
			<input id="searchBtn" type="button" value="조 회 " onclick="check_input()"/>
			<input id="resetBtn" type="button" value="초 기 화 " onclick="search_reset()"/>
		</form>
		<ul class="lastData">
			<li id="1w" onclick="last_data('1w')">최근 1주일 </li>
			<li>|</li>
			<li id="tm" onclick="last_data('tm')">이번달 </li>
			<li>|</li>
			<li id="1m" onclick="last_data('1m')">최근 1개월 </li>
			<li></li>
		</ul>
		<!-- <input id="before1Month" type="button" value="최근 1개월" onclick=""/> --> 
		<!-- <p style="font-size: 15px; padding: 5px; margin-left: 135px;">검색기간은 최대 3개월입니다.</p> -->
	</div>
	<!-- <div class="periodDiv" id="lastDataSet">
		<ul class="lastData">
			<li id="1w" onclick="last_data('1w')">최근 1주일 </li>
			<li>|</li>
			<li id="tm" onclick="last_data('tm')">이번달 </li>
			<li>|</li>
			<li id="1m" onclick="last_data('1m')">최근 1개월 </li>
			<li></li>
		</ul>
	</div> -->
	<div class="periodDiv" id="chartdiv"></div>
</main>
<%@ include file="/WEB-INF/layout/adminFooter.jspf" %>
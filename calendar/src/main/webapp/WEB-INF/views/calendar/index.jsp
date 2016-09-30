<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Insert title here</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>
	<body class="container">
		<h1>CALENDAR</h1>
		<h2>
			<small><a href="/calendar/index?ddayYear=${ddayYear}&ddayMonth=${ddayMonth}&ddayOption=prev">◁</a></small>
			${ddayYear}년 ${ddayMonth+1}월
			<small><a href="/calendar/index?ddayYear=${ddayYear}&ddayMonth=${ddayMonth}&ddayOption=next">▷</a></small>
		</h2>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>일</th>
					<th>월</th>
					<th>화</th>
					<th>수</th>
					<th>목</th>
					<th>금</th>
					<th>토</th>
				</tr>
			</thead>
			<tbody>
				<tr class="something">
					<c:forEach var="oneDay" items="${oneDayList}" varStatus="status">
						<%-- <c:choose>
							<c:when test="${todayMonth == oneDay.month && todayDay == oneDay.day}">
								<td style="background-color: #E6FFFF;">
							</c:when>
							<c:otherwise>
								<td>
							</c:otherwise>
						</c:choose> --%>
						<td class="col-md-1">
							<c:choose>
								<c:when test="${oneDay.scheduleList == null}">
									<div style="color:#BDBDBD;">${oneDay.day}</div>
								</c:when>
								<c:when test="${oneDay.scheduleList != null}">
										<c:choose>
											<c:when test="${oneDay.scheduleList.size() == 0}">
												<a href="/calendar/scheduleList?scheduleDday=${oneDay.year}-${oneDay.month}-${oneDay.day}">
													<c:choose>
														<c:when test="${status.count % 7 == 1}">
														<!-- 일요일 -->
															<div style="color:#FF0000">${oneDay.day}</div>
														</c:when>
														
														<c:when test="${status.count % 7 == 0}">
														<!-- 토요일 -->
															<div style="color:#0000FF">${oneDay.day}</div>
														</c:when>
														
														<c:otherwise>
														<!-- 월화수목금 -->
															<div style="color:#000000">${oneDay.day}</div>
														</c:otherwise>
													</c:choose>
												</a>
											</c:when>
											<c:when test="${oneDay.scheduleList.size() > 0}">
												<a href="/calendar/scheduleList?scheduleDday=${oneDay.year}-${oneDay.month}-${oneDay.day}">
													<c:choose>
														<c:when test="${status.count % 7 == 1}">
														<!-- 일요일 -->
															<div style="color:#FF0000;font-weight:bold;">${oneDay.day}</div>
														</c:when>
														
														<c:when test="${status.count % 7 == 0}">
														<!-- 토요일 -->
															<div style="color:#0000FF;font-weight:bold;">${oneDay.day}</div>
														</c:when>
														
														<c:otherwise>
														<!-- 월화수목금 -->
															<div style="color:#000000;font-weight:bold;">${oneDay.day}</div>
														</c:otherwise>
													</c:choose>
												</a>
												<c:forEach var="schedule" items="${oneDay.scheduleList}">
													<div style="color:${schedule.scheduleTitleColor}">
														<c:if test="${schedule.scheduleTitle.length() > 5}">
															${schedule.scheduleTitle.substring(0,5)}..
														</c:if>
														<c:if test="${schedule.scheduleTitle.length() <= 5}">
															${schedule.scheduleTitle}
														</c:if>
													</div>
												</c:forEach>
											</c:when>
										</c:choose>
								</c:when>
							</c:choose>
						</td>
						<c:if test="${status.count % 7 == 0 && status.count < oneDayList.size()}">
							</tr><tr>
						</c:if>
					</c:forEach>
				</tr>
			</tbody>
		</table>
	</body>
</html>
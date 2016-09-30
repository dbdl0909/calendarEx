<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Insert title here</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>
	<body class="container">
		<div>
			<!-- schedule 입력폼 -->
			<h2>Schedule 입력</h2>
			<form action="/calendar/scheduleAdd" method="post">
				<table class="table">
					<tr>
						<th>날짜</th>
						<td><input type="text" name="scheduleDate" value="${scheduleDday}" readonly="readonly"/></td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="scheduleTitle"/></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea rows="3" cols="22" name="scheduleContent"></textarea></td>
					</tr>
					<tr>
						<th>장소</th>
						<td><input type="text" name="schedulePlace"/></td>
					</tr>
					<tr>
						<th>폰트색상</th>
						<td><input type="color" name="scheduleTitleColor"/></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" id="addButton" value="등록"/></td>
					</tr>
				</table>
			</form>
		</div>
		<div>
			<!-- scheduleList 출력 -->
			<h2>Schedule List</h2>
			날짜 : ${scheduleDday}
			<table class="table">
				<thead>
					<tr>
						<th>NO</th>
						<th>제목</th>
						<th>내용</th>
						<th>장소</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="schedule" items="${scheduleList}">
						<tr>
							<td>${schedule.scheduleNo}</td>
							<td>${schedule.scheduleTitle}</td>
							<td>${schedule.scheduleContent}</td>
							<td>${schedule.schedulePlace}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>
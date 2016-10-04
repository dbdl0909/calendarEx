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
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
		<script>
			$(document).ready(function() {			
				$('#repeat').click(function() {
					//console.log($('#repeatCheck').is(':checked'));
					if($('#repeat:checked').val()) {	//체크한 값이 true일 때
						$('#scheduleDate').val($('#originScheduleDate').val().substring(5));
					} else {								//체크한 값이 false일 때
						$('#scheduleDate').val($('#originScheduleDate').val());
					}
				});
				
				$('#addButton').click(function() {
					$('#scheduleAddForm').submit();
				});
			});
		</script>
	</head>
	<body class="container">
		<div>
			<a href="./">목록으로</a>
			<!-- schedule 입력폼 -->
			<h2>Schedule 입력</h2>
			<form id="scheduleAddForm" action="/calendar/scheduleAdd" method="post">
				<input type="hidden" id="originScheduleDate" name="originScheduleDate" value="${scheduleDday}"/>
				<table class="table">
					<tr>
						<th>날짜</th>
						<td>
							<input type="text" id="scheduleDate" name="scheduleDate" value="${scheduleDday}" readonly="readonly"/>
							<input type="checkbox" id="repeat" name="repeat" value="repeat"/>매년 반복
						</td>
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
						<th>제목폰트색상</th>
						<td><input type="color" name="scheduleTitleColor"/></td>
					</tr>
					<tr>
						<td colspan="2"><input type="button" id="addButton" value="등록"/></td>
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
						<th>삭제</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="schedule" items="${scheduleList}">
						<tr>
							<td>${schedule.scheduleNo}</td>
							<td>
								<c:if test='${schedule.repeat eq "repeat"}'>
									<img src="/resources/imgs/repeat.png">
								</c:if>
							 	${schedule.scheduleTitle}</td>
							<td>${schedule.scheduleContent}</td>
							<td>${schedule.schedulePlace}</td>
							<td><a href="">삭제</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>
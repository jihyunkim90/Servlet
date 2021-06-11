<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="modify" method="post">
		<table border="1">
			<tr>
				<th>게시판종류</th>
				<td>${nt.board}</td>
			</tr>
			<tr>
				<th>글쓴이</th>
				<td>${nt.id}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${nt.title}"></td>
			</tr>
			<tr>
				<th>등록일</th>
				<td>${nt.regdate}</td>
			</tr>
			<tr>
				<th colspan=2>내용</th>
			</tr>
			<tr>
				<td colspan=2>
				<td><textarea name="content">${nt.content}</textarea></td>
			</tr>
		</table>
		<input type="hidden" name="id" value="${nt.id}"/>
		<input type="submit" value="저장" />
	</form>
</body>
</html>
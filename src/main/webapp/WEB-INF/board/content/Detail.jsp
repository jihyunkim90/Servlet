<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

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
			<td>${nt.title}</td>
		</tr>
			<tr>
			<th>조회수</th>
			<td>${nt.hit}</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${nt.regdate}</td>
		</tr>
		<tr>
			<th colspan=2>내용</th>
		</tr>
		<tr>
			<td colspan=2>${nt.content}</td>
		</tr>
	</table>
	<div>
		<input type="button" onclick="location.href='/board/content/list?p=${p}&q=${q}'" value="목록으로">
		<input type="button" onclick="location.href='/board/content/modify?id=${nt.id}&q=${q}&f=${f}'" value="수정">
	</div>
</body>
</html>
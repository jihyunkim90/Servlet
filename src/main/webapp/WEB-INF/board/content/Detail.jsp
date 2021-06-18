<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function fn_alert(){
		alert('이미 좋아요를 눌렀습니다');
		return false;
	}
</script>
</head>
<body>

	<table border="1">
		<tr>
			<th>게시판종류</th>
			<td>${nt.board}</td>
		</tr>
		<tr>
			<th>글쓴이</th>
			<td>${nt.writer_id}</td>
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
			<th>첨부파일</th>
			<td>
			 	<a download href="../../../upload/${nt.files}">${nt.files}</a>
			</td>
		</tr>
		<tr>
			<td> <!-- 좋아요 버튼 -->
			<c:if test="${lcount eq 0}">
				<input type="button" onclick="location.href='/board/content/like?pid=${nt.id}'" value="like it">
			</c:if>
			<c:if test="${lcount !=0 }">
				<input type="button" onclick="fn_alert()" value="like it"/>
			</c:if>
			</td>
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
		<!-- 수정버튼 -->
		<c:if test="${sessionScope.userID==nt.writer_id}">
		<input type="button" onclick="location.href='/board/content/modify?id=${nt.id}&q=${q}&f=${f}'" value="수정">
		</c:if>
		<c:if test="${sessionScope.userID!=nt.writer_id}">
		<c:if test="${sessionScope.userRank=='A'}">		
		<input type="button" onclick="location.href='/board/content/modify?id=${nt.id}&q=${q}&f=${f}'" value="수정">
			</c:if>
			<c:if test="${sessionScop.userRank!='A'}">
			</c:if>
		</c:if>
	</div>
	<!-- 댓글 -->
	<c:import url="/board/content/comment?id=${nt.id}"></c:import>
	
</body>
</html>
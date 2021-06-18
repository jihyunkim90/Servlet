<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:set var="root" value="${pageContext.request.contextPath}" />
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.0/examples/dashboard/">
<!-- Bootstrap core CSS -->
<link href="${root}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${root}/css/board/dashboard.css" rel="stylesheet">
</head>
<body>
	<section>
		<article>
			<!--  댓글 입력창 -->
			<form action="/board/content/comment" method="post">
				<input type="text" name="comment" id="comment" /> 
				<input type="hidden" name="pid" value="${pid}" />
				 <input type="submit" value="입력" />
				 
			</form>
		</article>
		<article>
			<c:if test="${count!=0}">
				<!-- 댓글 출력 -->
				<table>
					<c:forEach var="cl" items="${colist}">
						<tr>
							<td>${cl.cid}</td>
							<!-- 몇번째로 쓴 댓글 -->
							<td>${cl.comment}</td>
							<!-- 내용 -->
							<td>${cl.regdate}</td>
							<!-- 날짜 -->
							<td>${cl.writeid }</td>
							<td>${cl.rating}</td>
							<!-- 별점 -->
							
							<!-- 삭제 -->
							<c:if test="${sessionScope.userID==cl.writeid}">
								<td><input type="button" onclick="location.href='/board/content/comment?userID=${cl.writeid}&q=${q}&f=${f}'" value="삭제"></td>
							</c:if>
							<c:if test="${sessionScope.userID!=cl.writeid}">
							</c:if>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<c:if test="${count eq 0 }">
			댓글이 없습니다
			</c:if>
		</article>
	</section>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%@page import="java.util.List"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write</title>
 <!-- root Route -->
<c:set var="root" value="${pageContext.request.contextPath}" />
 <!-- Ck Editor Route -->
<script type="text/javascript" src="${root}/js/ckeditor/ckeditor.js"></script>

</head>
<body>
<!-- header -->
	<jsp:include page="/WEB-INF/board/etc/header.jsp"></jsp:include>
	
	<jsp:include page="/WEB-INF/board/etc/sidebar.jsp"></jsp:include>
	
	<div class="container">
		<div class="row">
			<div class="col-2"></div>
			<div class="col-10">
			<form action="regedit" method="post" >
	<table border="1">
		<tbody>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<th>글내용</th>
				<td><!-- content <text-->
					<textarea class="form-control" name="content"></textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<input type="submit" value="저장"/>
	</form>
	<script type="text/javascript" defer>CKEDITOR.replace('content', {height: 400});</script>
			</div>	
		</div>
	</div>
</body>
</html>
​
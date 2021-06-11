<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- header -->
<c:set var="root" value="${pageContext.request.contextPath}" />
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.0/examples/dashboard/">
<!-- Bootstrap core CSS -->
<link href="${root}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${root}/css/board/dashboard.css" rel="stylesheet">
<!-- Bootstrap js -->
<script src="${root}/js/bootstrap/bootstrap.bundle.min.js" defer></script>

</head>
<body>
	<!-- header -->
	<jsp:include page="/WEB-INF/board/etc/header.jsp"></jsp:include>
	<!-- sidebar -->
	<jsp:include page="/WEB-INF/board/etc/sidebar.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<c:forEach var="li" items="${list}">
				<div class="col-3"></div>
				<img src="${root}${li.path}">
			</c:forEach>
		</div>
	</div>
</body>
</html>
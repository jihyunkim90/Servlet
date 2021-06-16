<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- root route -->
<c:set var="root" value="${pageContext.request.contextPath}" />
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.0/examples/dashboard/">
<!-- Bootstrap core CSS -->
<link href="${root}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${root}/css/board/dashboard.css" rel="stylesheet">
<!-- Bootstrap js -->
<script src="${root}/js/bootstrap/bootstrap.bundle.min.js" defer></script>
<!-- icon js -->
    <script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous" defer></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js" integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha" crossorigin="anonymous" defer></script>
    <!-- Dashboard js -->
    <script src="${root}/js/board/dashboard.js" defer></script>
</head>
<body>
<div class="container-fluid">
		<div class="row">
			<nav id="sidebarMenu"
				class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
				<div class="position-sticky pt-3">
					<ul class="nav flex-column">
				<br>
					<c:forEach var="mn" items="${menu}">
						<li class="nav-item">
							<a class="nav-link" aria-current="page" href="/board/content/list?b=${mn.boardid}">
						<c:choose>
						  <c:when test="${mn.boardid eq 1}">
						  	 <span data-feather="home"></span>
						  </c:when>
						    <c:when test="${mn.boardid eq 2}">
						  	 <span data-feather="file"></span>
						  </c:when>
						    <c:when test="${mn.boardid eq 3}">
						  	 <span data-feather="file"></span>
						  </c:when>
						    <c:when test="${mn.boardid eq 4}">
						  	 <span data-feather="layers"></span>
						  </c:when>
						</c:choose>
						 ${mn.boardName}
						</a>
						</li>
						</c:forEach>
					</ul>

				</div>
			</nav>
			</div>
			</div>
</body>
</html>
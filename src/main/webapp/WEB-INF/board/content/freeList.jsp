<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	
	<div class="container-fluid">
		<div class="row">
			
		

			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

				<h2>Free list</h2>
				<div class="table-responsive">
					<table class="table table-striped table-sm">
						<thead>
							<tr>
								<th>ID</th>
								<th>Title</th>
								<th>Writer</th>
								<th>Content</th>
								<th>DATE</th>
								<th>HIT</th>
							</tr>
						</thead>
						<!--  data grid start -->
						<tbody>
							<c:forEach var="li" items="${list}">
								<tr>
									<td>${li.id}</td>
									<td><a href="detail?id=${li.id}&q=${param.q}&f=${param.f}">${li.title}</a></td>
									<td>${li.writer_id}</td>
									<td>${li.content }</td>
									<td><fmt:formatDate pattern="yyyy년 MM월 dd일 hh시 mm분"
											value="${li.regdate}" /></td>
									<td>${li.hit}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				
		 <div class="col-12">
      	<div class="row">
      		<div class="col-4">
      			<!-- 변수선언 -->
      			<c:set var="page" value="${empty param.p?1:param.p}"></c:set>
				<c:set var="startNum" value="${page-(page-1)%5}"></c:set>
				<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10),'.')}"></c:set>
						 
				<!-- 현재 페이지 -->
					<div>
						<span>${page} </span> / ${lastNum} pages
					</div>
			</div>
      		<div class="col-5">
      			<!-- 페이징처리 시작 -->
				<section aria-label="Page navigation example">
	  				<ul class="pagination">
					<!-- 이전 페이지 -->
					<li class="page-item">
						<c:if test="${startNum > 1 }">
							<a class="page-link" href="?p=${startNum-1}&f=${param.f}&q=${param.q}">Prev</a>
						</c:if>
						<c:if test="${startNum <= 1 }">
							<a class="page-link" href="#" onclick="alert('첫 페이지입니다.');">Prev</a>
						</c:if>
					</li>
				
					<!-- 숫자 페이지 -->
					<c:forEach var="i" begin="0" end="4">
					<li class="page-item">
						<c:if test="${param.p==(startNum+i)}">
							<c:set var="style" value="font-weight:bold; color:red;" />
						</c:if>
						<c:if test="${param.p!=(startNum+i)}">
							<c:set var="style" value="" />
						</c:if>
						<c:if test="${(startNum+i) <=lastNum }">
							<a style="${style}" class="page-link" href="?p=${startNum+i}&f=${param.f}&q=${param.q}">${startNum+i}</a>
						</c:if>
					</li>
					</c:forEach>
					<!-- 다음 페이지 -->
					
					 <li class="page-item">
					<c:if test="${startNum+5 <= lastNum }">
						<a class="page-link" href="?p=${startNum+5}&f=${param.f}&q=${param.q}">Next</a>
					</c:if>
					<c:if test="${startNum+5 >lastNum }">
						<a class="page-link" href="#" onclick="alert('마지막 페이지입니다.');">Next</a>
					</c:if>
					</li>
		 			</ul>
				</section>
				<!--  검색 -->
				<form action="" method="get">
				<div>
					<select name="f">
						<option ${(param.f=="title")?"selected":""} value="title">제목</option>
						<option ${(param.f=="writeid")?"selected":""} value="writer_id">글쓴이</option>
					</select> <input type="text" name="q" /> <span><input type="submit" value="검색"></span>
				</div>
				</form>
      		</div>
      		<div class="col-2"></div>
      		<div class="col-1">
      		<!-- 글쓰기 버튼 -->
				<button class="btn btn-primary" type="button" onclick="location.href='regedit'">Write</button>
      		</div>
      	</div>
      </div>
    </main>
  </div> 
</div>
</body>
</html>
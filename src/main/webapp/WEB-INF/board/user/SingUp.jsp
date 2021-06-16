<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="UTF-8">
<title>SingUP</title>



<!-- Bootstrap core CSS -->
<link href="${root}/css/bootstrap/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${root}/css/user/form-validation.css" rel="stylesheet">
<!-- root Route -->
<script type="text/javascript" src="${root}/js/user/Signup.js"></script>
</head>
<body>

	<div class="container">
		<main>
			<div class="py-5 text-center">
				<img class="d-block mx-auto mb-4"
					src="../assets/brand/bootstrap-logo.svg" alt="" width="72"
					height="57">
				<h2>SignUp form</h2>
				<p class="lead">회원가입을 환영합니다</p>
			</div>

			<div class="row g-5">
				<div class="col-3"></div>
				<div class="col-6">
					<div class="col-md-11 col-lg-12">
						<h4 class="mb-3">회원가입</h4>
						<form id="joinform" name="joinform" action="" method="post"
							onsubmit="return createFrom(this)">
							<div class="row g-3">
							
							<!-- ID -->
								<div class="col-12">
									<label for="ID" class="form-label">ID</label>
									<div class="input-group has-validation">
										<span class="input-group-text">@</span> <input type="text"
											class="form-control" name="id" id="id" placeholder="ID" required>
										<button type="button" onclick="idCheck(joinform, '${root}')">아이디
											중복</button>
										<div class="invalid-feedback"></div>
									</div>
								</div>

							<!-- password -->

								<div class="col-12">
									<label for="Password" class="form-label">Password</label> <input
										type="password" class="form-control" name="password"
										id="password" placeholder="password" required>
									<div class="invalid-feedback"></div>
								</div>
							<!-- password check -->
								<div class="col-12">
									<label for="passwordCheck" class="form-label">passwordCheck
										<span class="text-muted"></span>
									</label> <input type="password" class="form-control" id="passwordCheck"
										placeholder="Please writer Password again">
								</div>
							<!-- 이름입력 -->
								<div class="col-12">
									<label for="Name" class="form-label">name</label> <input
										type="text" class="form-control" name="name" id="name"
										placeholder="Please write your name" value="" required>
									<div class="invalid-feedback"></div>
								</div>

							<!-- 이메일 입력 -->

								<div class="col-12">
									<label for="email" class="form-label">Email <span
										class="text-muted"></span></label> <input type="email"
										class="form-control" name="email" id="email" placeholder="you@example.com">
									<div class="invalid-feedback"></div>
								</div>




								<hr class="my-4">
								<!-- primary파란색,secondary 회색, success녹색 warning 노란색 info하늘색 -->
								<button class="w-80 btn btn-primary btn-lg" type="submit">Sing
									Up</button>
						</form>
					</div>
				</div>

			</div>
			<div class="col-3"></div>
	</div>

	</main>


</body>
</html>
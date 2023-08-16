<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<c:set var="resourcePath" value="${contextPath }/resources" />

<!DOCTYPE html>
<html dir="ltr" lang="en">

<c:import url="layout/head.jsp" />

<body>
	<div class="preloader">
		<div class="lds-ripple">
			<div class="lds-pos"></div>
			<div class="lds-pos"></div>
		</div>
	</div>
	<div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5"
		data-sidebartype="full" data-sidebar-position="absolute"
		data-header-position="absolute" data-boxed-layout="full">
		<c:import url="layout/header.jsp" />
		<c:import url="layout/sidebar.jsp" />
		<div class="page-wrapper">
			<div class="container-fluid">
				<div
					class="row justify-content-center align-items-center min-vh-100">
					<form class="col-md-6 col-lg-4 form-horizontal form-material"
						action="register" method="post">
						<input id="id" name="id" type="hidden" value="0"
							class="form-control p-0 border-0">
						<div class="form-group mb-4">
							<label class="col-md-12 p-0">Username</label>
							<div class="col-md-12 border-bottom p-0">
								<input readonly name="username" type="text"
									placeholder="Username" value="${member.username }"
									class="form-control p-0 border-0">
							</div>
						</div>
						<div class="form-group mb-4">
							<label for="example-email" class="col-md-12 p-0">Email</label>
							<div class="col-md-12 border-bottom p-0">
								<input readonly name="email" type="email"
									placeholder="johnathan@admin.com" value="${member.email }"
									class="form-control p-0 border-0" name="example-email">
							</div>
						</div>
						<div class="form-group mb-4">
							<label class="col-md-12 p-0">Password</label>
							<div class="col-md-12 border-bottom p-0">
								<input readonly name="password" type="password"
									value="${member.password }" class="form-control p-0 border-0">
							</div>
						</div>
						<div class="form-group mb-4">
							<label class="col-md-12 p-0">Phone Number</label>
							<div class="col-md-12 border-bottom p-0">
								<input readonly name="phoneNumber" type="text"
									placeholder="123 456 7890" value="${member.phoneNumber }"
									class="form-control p-0 border-0">
							</div>
						</div>
						<div class="form-group mb-4">
							<div class="col-sm-12">
								<button class="btn btn-success">Update Profile</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<c:import url="layout/footer.jsp" />
		</div>
	</div>
	<c:import url="layout/script.jsp" />
</body>

</html>
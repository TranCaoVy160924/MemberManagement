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
				<div class="row">
					<div class="col-md-6 mx-auto">
						<div class="white-box">
							<h3 class="box-title">${title }</h3>
							<p class="text-muted">If you are satisfied with this content,
								please click the 'Confirm' button</p>
							<form class="cform-horizontal form-material"
								action="${contextPath }/${confirmAction }" method="post">
								<input id="id" name="id" type="hidden" value="${member.id }"
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
											placeholder="Old Password" value="${member.password }"
											class="form-control p-0 border-0">
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
									<div class="col-sm-12 row">
										<div class="col-sm-9">
											<button class="btn btn-success">Confirm</button>
										</div>
										<div class="col-sm-3">
											<button formaction="${contextPath }/${cancelAction }"
												formmethod="get" class="btn btn-danger">Cancel</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<c:import url="layout/footer.jsp" />
		</div>
	</div>
	<c:import url="layout/script.jsp" />
</body>

</html>
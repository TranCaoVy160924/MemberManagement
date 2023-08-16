<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<c:set var="resourcePath" value="${contextPath }/resources" />

<header class="topbar" data-navbarbg="skin5">
	<nav class="navbar top-navbar navbar-expand-md navbar-dark">
		<div class="navbar-header" data-logobg="skin6">
			<!-- ============================================================== -->
			<!-- Logo -->
			<!-- ============================================================== -->
			<a class="navbar-brand" href="${contextPath }"> <!-- Logo icon -->
				<b class="logo-icon"> <!-- Dark Logo icon --> <img
					src="${resourcePath }/plugins/images/logo-icon.png" alt="homepage" />
			</b> <!--End Logo icon --> <!-- Logo text --> <span class="logo-text">
					<!-- dark Logo text --> <img
					src="${resourcePath }/plugins/images/logo-text.png" alt="homepage" />
			</span>
			</a>
			<!-- ============================================================== -->
			<!-- End Logo -->
			<!-- ============================================================== -->
		</div>
		<!-- ============================================================== -->
		<!-- End Logo -->
		<!-- ============================================================== -->
		<div class="navbar-collapse collapse" id="navbarSupportedContent"
			data-navbarbg="skin5">
			<ul class="navbar-nav d-none d-md-block d-lg-none">
				<li class="nav-item"><a
					class="nav-toggler nav-link waves-effect waves-light text-white"
					href="javascript:void(0)"><i class="ti-menu ti-close"></i></a></li>
			</ul>
			<!-- ============================================================== -->
			<!-- Right side toggle and nav items -->
			<!-- ============================================================== -->
			<ul class="navbar-nav ms-auto d-flex align-items-center">

				<!-- ============================================================== -->
				<!-- Search -->
				<!-- ============================================================== -->
				<li class=" in">
					<form role="search" class="app-search d-none d-md-block me-3">
						<input type="text" placeholder="Search..."
							class="form-control mt-0"> <a href="" class="active">
							<i class="fa fa-search"></i>
						</a>
					</form>
				</li>
			</ul>
		</div>
	</nav>
</header>
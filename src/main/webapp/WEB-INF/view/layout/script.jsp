<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<c:set var="resourcePath" value="${contextPath }/resources" />

<script
	src="${resourcePath}/plugins/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap tether Core JavaScript -->
<script src="${resourcePath}/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
<script src="${resourcePath}/js/app-style-switcher.js"></script>
<!--Wave Effects -->
<script src="${resourcePath}/js/waves.js"></script>
<!--Menu sidebar -->
<script src="${resourcePath}/js/sidebarmenu.js"></script>
<!--Custom JavaScript -->
<script src="${resourcePath}/js/custom.js"></script>
<%@page import="com.google.gson.Gson"%>
<%@page import="dxc.assignment.model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<c:set var="resourcePath" value="${contextPath }/resources" />
<%
String memberJson = request.getParameter("memberJson");
Member member = new Gson().fromJson(memberJson, Member.class);
%>

<div class="col-lg-8 col-xlg-9 col-md-12">
	<div class="card">
		<div class="card-body">
			<form class="form-horizontal form-material" action="register"
				method="post">
				<input id="id" name="id" type="hidden" value="${member.id }"
					class="form-control p-0 border-0">
				<div class="form-group mb-4">
					<label class="col-md-12 p-0">Username</label>
					<div class="col-md-12 border-bottom p-0">
						<input name="username" type="text" placeholder="hah"
							value="${member.username }" class="form-control p-0 border-0">
					</div>
				</div>
				<div class="form-group mb-4">
					<label for="example-email" class="col-md-12 p-0">Email</label>
					<div class="col-md-12 border-bottom p-0">
						<input name="email" type="email" placeholder="johnathan@admin.com"
							class="form-control p-0 border-0" name="example-email"
							value="${member.email }">
					</div>
				</div>
				<div class="form-group mb-4">
					<label class="col-md-12 p-0">Password</label>
					<div class="col-md-12 border-bottom p-0">
						<input name="password" type="password" value="${member.password }"
							class="form-control p-0 border-0">
					</div>
				</div>
				<div class="form-group mb-4">
					<label class="col-md-12 p-0">Phone Number</label>
					<div class="col-md-12 border-bottom p-0">
						<input name="phoneNumber" type="text" placeholder="123 456 7890"
							value="${member.phoneNumber }" class="form-control p-0 border-0">
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
</div>
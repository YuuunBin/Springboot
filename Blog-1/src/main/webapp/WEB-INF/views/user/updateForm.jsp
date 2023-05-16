<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<div class="container">
	<form>
	<input type="hidden" id="id" value="${principal}"/>
		<div class="form-group"></div>
			<label for="username">Username</label> 
			<input type="text" value="${principal.user.username}" class="form-control" id="username" placeholder="Enter username" name="username" required readonly>
			<div class="valid-feedback">Valid.</div>
		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>
		<div class="form-group">
			<label for="email">Email Address:</label> 
			<input type="email" value="${principal.user.email}" class="form-control" id="email" placeholder="Enter email" name="email" required>
			<div class="valid-feedback">Valid.</div>
			
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>
		<div class="form-group form-check">
		<input class="form-check-input" type="checkbox" name="remember" required> I agree on register.
      <div class="valid-feedback">Valid.</div>
      <div class="invalid-feedback">Check this checkbox to continue.</div>
		 </div>
</form>
 <button id="btn-save" class="btn btn-primary">회원수정완료</button>

</div>

<script src="/js/user.js"></script>
<!--  /.. << 한 칸 위로 올라가서 찾는다는 뜻 -->
<%@ include file="../layout/footer.jsp"%>


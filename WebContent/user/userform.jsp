<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<a href="user/viewAllUsers.jsp">View All Records</a>
<br />


<h1>User</h1>

<c:if test="${user ne null}">
	<form action="EditUserServlet" method="post">
	<input type="hidden" name="id" value="${user.id}" />
</c:if>
<c:if test="${user eq null}">
	<form action="user/AddUserServlet" method="post">
</c:if>
<table>
	<tr>
		<td>Name:</td>
		<td><input type="text" name="name" value="${user.name}" /></td>
	</tr>
	<tr>
		<td>Password:</td>
		<td><input type="password" name="password"
			value="${user.password}" /></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td><input type="email" name="email" value="${user.email}" /></td>
	</tr>
	<tr>
		<td>Sex:</td>
		<td><input type="radio" name="sex" value="${user.sex}" />Male <input
			type="radio" name="sex" value="female" />Female</td>
	</tr>
	<tr>
		<td>Country:</td>
		<td><select name="country" style="width: 155px"
			value="${user.country}">
				<option>India</option>
				<option>Pakistan</option>
				<option>Afghanistan</option>
				<option>Berma</option>
				<option>Other</option>
		</select></td>
	</tr>
	<tr>
		<c:if test="${user ne null}">
			<td colspan="2"><input type="submit" value="Update User" /></td>
		</c:if>
		<c:if test="${user eq null}">
			<td colspan="2"><input type="submit" value="Add User" /></td>
		</c:if>
	</tr>
</table>
</form>

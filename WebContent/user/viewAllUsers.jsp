<%@page import="com.assimilate.user.models.User"%>
<%@page import="java.util.List"%>
<%@page import="com.assimilate.user.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		UserDao userDao = new UserDao();
		List<User> list = userDao.getAllUsers();
		request.setAttribute("users", list);
	%>
	<table border="1" width="90%">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Password</th>
			<th>Email</th>
			<th>Sex</th>
			<th>Country</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach items="${users}" var="u">
			<tr>
				<td>${u.getId()}</td>
				<td>${u.getName()}</td>
				<td>${u.getPassword()}</td>
				<td>${u.getEmail()}</td>
				<td>${u.getSex()}</td>
				<td>${u.getCountry()}</td>
				<td><a href="EditUserServlet?id=${u.getId()}">Edit</a></td>
				<td><a href="DeleteUserServlet?id=${u.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<a href="userform.jsp">Add New User</a>
</body>
</html>
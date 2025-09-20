<%@page import="com.EduTestPortal.model.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
	<header style="text-align:right;"><a href="logout">Logout</a></header>
	<h1>Welcome ${currentStudent.name}</h1>
</body>
</html>
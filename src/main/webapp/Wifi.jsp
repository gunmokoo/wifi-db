<%@page import="db.JsonTest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	JsonTest jt = new JsonTest();
    int cnt = jt.dbInsert();
    
    out.write("<h1>" + cnt + "개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>");
    %>
    <br></br>
	<a href="http://localhost:8080/jsp-study/">홈으로 가기</a>
</body>
</html>
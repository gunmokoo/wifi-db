<%@page import="java.util.Date"%>
<%@page import="db.History"%>
<%@page import="java.util.ArrayList"%>
<%@page import="db.JsonTest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table{
		width: 100%;
	    border: 1px solid;
	    border-collapse: collapse;
	    table-layout: fixed;
	}
	th, td{
		border: 1px solid black;
		padding: 5px 23px;
		white-space: nowrap;
		text-align:center;
	}
	th {
		background-color: #ABDEE6;
		text-align:center;
	}
	td {
		text-overflow: ellipsis;
		overflow: hidden;
	}
</style>
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	<a href="http://localhost:8080/jsp-study/">홈</a>
	<span>|</span>
	<a href="http://localhost:8080/jsp-study/History.jsp">위치 히스토리 목록</a>
	<span>|</span>
	<a href="http://localhost:8080/jsp-study/Wifi.jsp">Open API 와이파이 목록 가져오기</a>
	<br></br>
	<table>
		<tr>
			<th>ID</th>
			<th>X좌표</th>
			<th>Y좌표</th>
			<th>조회일자</th>
			<th>비고</th>
		</tr>
		<tr>
		<%
		JsonTest jt = new JsonTest();
	    
	    String x = request.getParameter("x");
	    String y = request.getParameter("y");
	    ArrayList<History> list = jt.hitoryDb();
	    
	    for(int i=0;i<list.size();i++)
	    {
	    	out.write("<tr>");
	    	
	    	int id = list.size()-i;
	    	Date date = list.get(i).getDatetime();
	    	
	    	out.write("<td>" + id + "</td>");
	    	out.write("<td>" + list.get(i).getX_coordinate() + "</td>");
	    	out.write("<td>" + list.get(i).getY_coordinate() + "</td>");
	    	out.write("<td>" + date + "</td>");
	    	out.write("<td><form action=\"History.jsp\">");
	    	out.write("<input type=\"submit\" value=\"삭제\" />");
	    	out.write("</form></td>");
	    	out.write("</tr>");
	    }
	    %>
		</tr>
	</table>

</body>
</html>
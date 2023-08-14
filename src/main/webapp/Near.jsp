<%@page import="db.JsonTest"%>
<%@page import="db.Wifi"%>
<%@page import="java.util.ArrayList"%>
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
	<form action="Near.jsp">
    	<label for="x">x좌표 : </label>
		<input type="text" name="x" required />
    	<label for="y">y좌표 : </label>
		<input type="text" name="y" required />
		<input type="submit" value="근처 WIFI 정보 보기" />
	</form>
	<br></br>
	<table>
		<tr>
			<th>거리</th>
			<th>관리번호</th>
			<th>자치구</th>
			<th>와이파이명</th>
			<th>도로명주소</th>
			<th>상세주소</th>
			<th>설치위치(층)</th>
			<th>설치유형</th>
			<th>설치기관</th>
			<th>서비스구분</th>
			<th>망종류</th>
			<th>설치년도</th>
			<th>실내외구분</th>
			<th>wifi접속환경</th>
			<th>X좌표</th>
			<th>Y좌표</th>
			<th>작업일자</th>
		</tr>
		<%
		JsonTest jt = new JsonTest();
	    
	    String x = request.getParameter("x");
	    String y = request.getParameter("y");
	    ArrayList<Wifi> list = jt.dbUpdate(Double.parseDouble(x), Double.parseDouble(y));
	    
	    for(int i=0;i<list.size();i++)
	    {
	    	out.write("<tr>");
	    	
	    	out.write("<td>" + list.get(i).getDistance() + "</td>");
	    	out.write("<td>" + list.get(i).getNumber() + "</td>");
	    	out.write("<td>" + list.get(i).getBorough() + "</td>");
	    	out.write("<td>" + list.get(i).getName() + "</td>");
	    	out.write("<td>" + list.get(i).getStreet_address() + "</td>");

	    	out.write("<td>" + list.get(i).getDetail_address() + "</td>");
	    	out.write("<td>" + list.get(i).getInstallation_location() + "</td>");
	    	out.write("<td>" + list.get(i).getInstallation_type() + "</td>");
	    	out.write("<td>" + list.get(i).getInstallation_agency() + "</td>");
	    	out.write("<td>" + list.get(i).getService() + "</td>");

	    	out.write("<td>" + list.get(i).getNet() + "</td>");
	    	out.write("<td>" + list.get(i).getYear_of_installation() + "</td>");
	    	out.write("<td>" + list.get(i).getIn_and_out() + "</td>");
	    	out.write("<td>" + list.get(i).getConnection_environment() + "</td>");
	    	out.write("<td>" + list.get(i).getX_coordinate() + "</td>");

	    	out.write("<td>" + list.get(i).getY_coordinate() + "</td>");
	    	out.write("<td>" + list.get(i).getWorking_date() + "</td>");
	    	
	    	out.write("</tr>");
	    }
	    %>
	</table>
</body>
</html>
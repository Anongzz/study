<%@page import="org.eclipse.jdt.internal.compiler.env.IRecordComponent"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*, java.io.*"%>
<%@ page import="com.example.study.user.ParkingPrice"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@commonProperties['spring.datasource.name']" var="name"/>
<spring:eval expression="@commonProperties['spring.datasource.password']" var="password"/>
<spring:eval expression="@commonProperties['spring.datasource.url']" var="url"/>
<spring:eval expression="@commonProperties['spring.datasource.driver-class-name']" var="driver"/>

<%
request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>주차 요금 정산</title>
<style type="text/css">
@import
	url('https://fonts.googleapis.com/css2?family=Poor+Story&display=swap')
	;

* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: "Poor Story", sans-serif;
}

body {
	margin: 0;
	padding: 0;
	background: linear-gradient(120deg, #87ceeb, #b0e0e6);
	height: 100vh;
	overflow: hidden;
}

table {
	border-collapse: separate;
	border-spacing: 0;
	width: 100%;
}

th, td {
	padding: 6px 15px;
	font-size: 20px;
}

th {
	background: #42444e;
	color: #fff;
	text-align: center;
}

tr:first-child th:first-child {
	border-top-left-radius: 6px;
}

tr:first-child th:last-child {
	border-top-right-radius: 6px;
}

td {
	border-right: 1px solid #c6c9cc;
	border-bottom: 1px solid #c6c9cc;
}

td:first-child {
	border-left: 1px solid #c6c9cc;
}

tr:nth-child(even) td {
	background: #eaeaed;
}

tr:last-child td:first-child {
	border-bottom-left-radius: 6px;
}

tr:last-child td:last-child {
	border-bottom-right-radius: 6px;
}

.buildObj1 {
	cursor: pointer;
	width: 200px;
	height: 280px;
	background: #2FDDE5;
	border-radius: 10px;
	margin: 20px 40px;
	display: inline-flex;
	padding: 10px 10px;
	text-align: left;
}

.buildObj2 {
	cursor: pointer;
	width: 200px;
	height: 280px;
	border-radius: 10px;
	margin: 20px 40px;
	display: inline-block;
}

.buildings {
	position: absolute;
	top: 55%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 1150px;
	height: 650px;
	border-radius: 10px;
	box-shadow: 10px 10px 15px rgba(0, 0, 0, 0.05);
}

.centerLogin {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 1200px;
	height: 800px;
	background: white;
	border-radius: 10px;
	box-shadow: 10px 10px 15px rgba(0, 0, 0, 0.05);
}

.centerLogin h1 {
	text-align: center;
	padding: 20px 0;
	border-bottom: 1px solid silver;
}

.inputValueZone {
	position: relative;
	margin: 10px;
	width: 600px;
	height: 650px;
	border-radius: 10px;
	background-color: #F0F0F0;
	left: 550px;
	top: 25px;
}

.inputZone {
	position: absolute;
	margin: 10px;
	width: 500px;
	height: 700px;
	border-color: black;
}

.inputZone h2 {
	text-align: center;
	font-size: 25pt;
	border-bottom: 3px solid silver;
}

.inputZone p {
	font-size: 20pt;
	border-bottom: 1px solid black;
}

.priceAndTime{
	font-size: 20pt;
	text-align: center;
}
</style>

</head>
<body>


	<%
	Object getData = session.getAttribute("id");
	Object getData2 = session.getAttribute("buildingName");

	String ObjToStringValue = (String) getData;
	String ObjBuildingValue = (String) getData2;

	if (ObjToStringValue == null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href='/';");
		script.println("</script>");
	}
	String year = request.getParameter("year");
	String month = request.getParameter("month");
	String day = request.getParameter("day");
	String no = request.getParameter("no");

	String mysqlPW = (String) pageContext.getAttribute("password");
	String mysqlName = (String) pageContext.getAttribute("name");
	String driver = (String) pageContext.getAttribute("driver");
	String url = (String) pageContext.getAttribute("url");

	Class.forName(driver);

	Connection conn = DriverManager.getConnection(url,mysqlName,mysqlPW);

	String strSQL = "SELECT parkingTime, carNumber, isParking FROM parkinginformation WHERE userID='" + ObjToStringValue
			+ "' AND buildingName='" + ObjBuildingValue + "'";

	PreparedStatement pstmt = conn.prepareStatement(strSQL);
	ResultSet rs = pstmt.executeQuery(strSQL);
	%>
	<p>
		LoginID:
		<%=ObjToStringValue%></p>
		<input type="button" value="로그아웃" onclick="location.href='LogOut.jsp'"
			style="position: absolute; right: 10px;">
	<div class="centerLogin">
		<h1><%=ObjBuildingValue %>의 주차 현황</h1>
		<div class="inputZone">
			<h2>입 출차 목록</h2>
			<p>출차 하지 않은 차량은 23:59분에 출차 처리됩니다.</p>
			<table>
				<thead>
					<tr>
						<th>시간</th>
						<th>차량번호</th>
						<th>상태</th>
					</tr>
				</thead>
				<tbody>
					<%
					int index = 0;
					List<String> list = new ArrayList<>();
					while (rs.next()) {
						
						String parkingTime = rs.getString("parkingTime");
						String CarNumber = rs.getString("CarNumber");
						String isParking = rs.getString("IsParking");
						list.add(index, parkingTime+" "+CarNumber+" "+isParking);
						index++;
					%>
					<tr>
						<td><b><%=parkingTime%></b></td>
						<td><b><%=CarNumber%></b></td>
						<td><b><%=isParking%></b></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
			<%
			rs.close();
			pstmt.close();
			
			String strSQL2 = "SELECT basicPrice, basicTime, addPrice, addTime FROM parkingprice WHERE userID='" + ObjToStringValue
					+ "' AND buildingName='" + ObjBuildingValue + "'";

			pstmt = conn.prepareStatement(strSQL2);
			rs = pstmt.executeQuery(strSQL2);
			
			String basicPrice ="";
			String basicTime ="";
			String addPrice ="";
			String addTime ="";
			
			while(rs.next()){
				basicPrice = rs.getString("basicPrice");
				basicTime = rs.getString("basicTime");
				addPrice = rs.getString("addPrice");
				addTime = rs.getString("addTime");
			}
			
			rs.close();
			pstmt.close();
			%>
		</div>
		
		<div class="inputValueZone">
		<%
		
		%>
			<form action="" method="post" style="padding: 10px 10px 10px 10px;">
				<div style="position: absolute; left: 75px; top: 50px;">
					<label for="" style="font-size: 30px; padding: 10px 10px 10px 10px;"><b>기본시간(분)</b></label><br> 
					<p class="priceAndTime"><%=basicTime %></p>
				</div>
				<div style="position: absolute; right: 75px; top: 50px;">
					<label for="" style="font-size: 30px; padding: 10px 10px 10px 10px;"><b>기본요금(원)</b></label><br> 
					<p class="priceAndTime"><%=basicPrice %></p>
				</div>
				<div style="position: absolute; left: 75px; top: 150px;">
					<label for="" style="font-size: 30px; padding: 10px 10px 10px 10px;"><b>추가시간(분)</b></label><br> 
					<p class="priceAndTime"><%=addTime %></p>
				</div>
				<div style="position: absolute; right: 75px; top: 150px;">
					<label for="" style="font-size: 30px; padding: 10px 10px 10px 10px;"><b>추가요금(원)</b></label><br> 
					<p class="priceAndTime"><%=addPrice %></p>
				</div>
				<div style="position: absolute; right: 240px; top: 450px;">
					<label for="" style="text-align: center; font-size: 30px; padding: 10px 10px 10px 10px;"><b>정산결과</b></label><br>
					<p style="text-align: center; font-size: 40px">
					<%
						
					
						String[] records = list.toArray(new String[list.size()]);
					
						
					
						int[] fees = {Integer.parseInt(basicTime), Integer.parseInt(basicPrice),Integer.parseInt(addTime),Integer.parseInt(addPrice)};
						ParkingPrice pp =new ParkingPrice();
						String value = "";
						value = pp.solution(fees, records); 
						
					%>
						<b><%=value %>원</b>
					</p>
				</div>


			</form>
		</div>


	</div>
	<%
	rs.close();
	pstmt.close();
	conn.close();
	%>
</body>
</html>

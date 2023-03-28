<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*, java.util.*, java.io.*" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<% request.setCharacterEncoding("utf-8"); %>
<spring:eval expression="@commonProperties['spring.datasource.name']" var="name"/>
<spring:eval expression="@commonProperties['spring.datasource.password']" var="password"/>
<spring:eval expression="@commonProperties['spring.datasource.url']" var="url"/>
<spring:eval expression="@commonProperties['spring.datasource.driver-class-name']" var="driver"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>주차 요금 정산</title>
<style type="text/css">
@import
	url('https://fonts.googleapis.com/css2?family=Poor+Story&display=swap');

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
th,
td {
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

.buildObj1{
	cursor: pointer;
	width: 200px;
	height: 280px;
	background: #2FDDE5;
	border-radius: 10px;
	margin: 20px 40px;
	display: inline-flex;
	text-align: left;
	
}

.buildObj2{
	cursor:pointer;
	width: 200px;
	height: 280px;
	border-radius: 10px;
	margin: 20px 40px;
	display: inline-block;
	
}

.buildings{
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
.inputValueZone{
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
	border: 1px solid;
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
</style>

</head>
<body>
<%
Object getData = session.getAttribute("id");

String ObjToStringValue = (String)getData;
if(ObjToStringValue==null){
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("location.href='/';");
	script.println("</script>");
}
String mysqlPW = (String) pageContext.getAttribute("password");
String mysqlName = (String) pageContext.getAttribute("name");
String driver = (String) pageContext.getAttribute("driver");
String url = (String) pageContext.getAttribute("url");

Class.forName(driver);

Connection conn = DriverManager.getConnection(url,mysqlName,mysqlPW);

String sql = "SELECT buildingName, buildingCreateTime FROM buildingInformation WHERE userID='"+ObjToStringValue+"'";

PreparedStatement pstmt = conn.prepareStatement(sql);

ResultSet rs = pstmt.executeQuery();

%>
<p>로그인ID: <%=ObjToStringValue %></p>
<input type="button" value="로그아웃" onclick="location.href='LogOut.jsp'"
			style="position: absolute; right: 10px;">
	<div class="centerLogin">
		<h1><%=ObjToStringValue %>님의 주차장 목록</h1>
		<div class="buildings">
			
			<%while(rs.next()){ 
				String bName = rs.getString("buildingName");
				String bDate = String.valueOf(rs.getDate("buildingCreateTime")); 
			%>
			<div class="buildObj1" >
				
				<div id = "<%=bName %>"  style="position: absolute;" >
					<div onclick="location.href='userIndependentBuilding.mvc?data1=<%=bName%>'" style="position: absolute; width: 200px; height: 280px;">
						<p style="position: absolute; width: 180px; margin: 10px 10px;"><b><%=bName %></b><br><br>등록일: <%=bDate %></p>
					</div>
				<img alt="edit_building" src="edit.png" width="30" height="30" style="position: absolute; left: 200px; " onclick="location.href='editBuilding.mvc?data1=<%=ObjToStringValue%>&data2=<%=bName %>'">
				<img alt="delete_building" src="delete.png" width="30" height="30" style="position: absolute; left: 200px; top: 40px;" id="<%=bName %>"
				onclick="(function(){if(confirm('데이터를 삭제하시겠습니까?')){location.href='deleteBuilding.mvc?data1=<%=ObjToStringValue%>&data2=<%=bName %>';}else{}})();">
					
				</div>
			</div>
			<%} %>
			
			<div class="buildObj2" onclick="location.href='CreateBuildings.jsp'">
				<img alt="add_building" src="plus.png" width="200" height="200" style="position: absolute;">
			</div>
			
		</div>
	</div>

</body>
</html>

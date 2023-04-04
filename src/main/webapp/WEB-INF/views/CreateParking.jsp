<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*, java.util.*, java.io.*" %>
<% request.setCharacterEncoding("utf-8"); %>

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
	background: gray;
	border-radius: 10px;
	margin: 20px 40px;
	display: inline-flex;
	padding: 10px 10px;
	text-align: center;
	
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

form .txt_field{
  position: relative;
  border-bottom: 2px solid #adadad;
  margin: 30px 0;
}
.txt_field input{
  width: 100%;
  padding: 0 5px;
  height: 40px;
  font-size: 20px;
  border: none;
  background: none;
  outline: none;
}
.txt_field label{
  position: absolute;
  top: 50%;
  left: 5px;
  color: #adadad;
  font:bold;
  transform: translateY(-50%);
  font-size: 20px;
  pointer-events: none;
  transition: .5s;
}
.txt_field span::before{
  content: '';
  position: absolute;
  top: 40px;
  left: 0;
  width: 0%;
  height: 2px;
  background: #2691d9;
  transition: .5s;
}
.txt_field input:focus ~ label,
.txt_field input:valid ~ label{
  top: -5px;
  color: #2691d9;
}
.txt_field input:focus ~ span::before,
.txt_field input:valid ~ span::before{
  width: 100%;
}
.pass{
  margin: -5px 0 20px 5px;
  color: #a6a6a6;
  cursor: pointer;
}
.pass:hover{
  text-decoration: underline;
}
input[type="submit"]{
  width: 100%;
  height: 50px;
  border: 1px solid;
  background: #2691d9;
  border-radius: 25px;
  font-size: 18px;
  color: #e9f4fb;
  font-weight: 700;
  cursor: pointer;
  outline: none;
}
input[type="submit"]:hover{
  border-color: #2691d9;
  transition: .5s;
}

.signup_link{
  margin: 30px 0;
  text-align: center;
  font-size: 16px;
  color: #666666;
}
.signup_link a{
  color: #2691d9;
  text-decoration: none;
}
.signup_link a:hover{
  text-decoration: underline;
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

%>
<p>로그인ID: <%=ObjToStringValue %></p>

	<div class="centerLogin">
		<h1>새로운 주차장 정보 생성</h1>
		<form method="post" action="/create">
        <div class="txt_field" >
          <input type="text" name="userParking" required>
          <span></span>
          <label>주차장 이름(주차장 이름은 생성 후 변경할 수 없습니다.)</label>
        </div>
        <div class="txt_field" >
          <input type="number" name="basicPrice" required>
          <span></span>
          <label>기본 요금</label>
        </div>
        <div class="txt_field" >
          <input type="number" name="basicTime" required>
          <span></span>
          <label>기본 주차 시간(분)</label>
        </div>
        <div class="txt_field" >
          <input type="number" name="addPrice" required>
          <span></span>
          <label>추가 요금</label>
        </div>
        <div class="txt_field" >
          <input type="number" name="addTime" required>
          <span></span>
          <label>추가 요금당 시간(분)</label>
        </div>
        <input type="submit" name="commit" value="추가하기">
        <div class="signup_link">
				<a href="/userBuildings" style="font-size: 20px;">뒤로가기</a>
			</div>
      </form>
	</div>
</body>
</html>

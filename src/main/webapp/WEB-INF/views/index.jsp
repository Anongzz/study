<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import = "java.io.PrintWriter"%>
<%
    session.removeAttribute("id"); //안뇨쇼 깃 첨써바용 -.-
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>주차 요금 정산</title>
    <link rel="stylesheet" href="style.css">
    <style type="text/css">
        @keyframes example {
            0%   	{left:50%; top: 55%;}
            100% 	{left:50%; top: 50%;}
        }
        .anim{
            animation-name: example;
            animation-duration: 0.5s;
        }

    </style>
</head>
<body>
<%
    Object getData = session.getAttribute("id");
    String ObjToStringValue = (String)getData;
%>
<p>sessionValue: <%=ObjToStringValue %></p>
<div class="center anim">
    <h2>주차 요금 정산 프로그램</h2>
    <form method="post" action="userLoginAction.jsp">
        <div class="txt_field" >
            <input type="text" name="userID" required>
            <span></span>
            <label>ID</label>
        </div>
        <div class="txt_field" >
            <input type="password" name="userPW" required>
            <span></span>
            <label>PW</label>
        </div>
        <div class="pass">비밀번호를 잊으셨나요?(준비중)</div>
        <input type="submit" name="commit" value="로그인">
        <div class="signup_link">
            회원이 아니신가요? <a href="/join">회원가입 하기</a>
        </div>
    </form>
</div>
</body>
</html>
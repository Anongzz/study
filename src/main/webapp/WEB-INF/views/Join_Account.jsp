<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>주차 요금 정산</title>
<link rel="stylesheet" href="style.css">
</head>
<script type="text/javascript">

function check_pw(){
	//패스워드에 입력한 정보를 pw로 선언한다
    var pw = document.getElementById('pw').value;

	//비밀번호는 6자리 이상 16자리 이하만 가능하다
    if(pw.length < 6 || pw.length>16){
        window.alert('비밀번호는 6글자 이상, 16글자 이하만 이용 가능합니다.');
        document.getElementById('pw').value='';
    }
    
    //나중에 비밀번호 확인까지 만들면 쓸거
    if(document.getElementById('pw').value !='' && document.getElementById('pw2').value!=''){
        if(document.getElementById('pw').value==document.getElementById('pw2').value){
            document.getElementById('check').innerHTML='비밀번호가 일치합니다.'
            document.getElementById('check').style.color='blue';
        }
        else{
            document.getElementById('check').innerHTML='비밀번호가 일치하지 않습니다.';
            document.getElementById('check').style.color='red';
        }
    }
}
</script>
<body>
	<div class="center">
		<h1>회원가입</h1>
		<form method="post" action="/account">
			<p>회원가입에 필요한 정보를 입력해주세요
			<div class="txt_field">
				<input type="text" name="userName" required> <span></span> <label>이름</label>
			</div>
			<div class="txt_field">
				<input type="text" name="userID" required> <span></span> <label>아이디</label>
			</div>
			<div class="txt_field">
				<input type="password" name="userPW" id="pw" onchange="check_pw()"
					required> <span></span> <label>비밀번호</label>
			</div>

			<!-- <div class="txt_field">
				<input type="password" name="userPW_Check" id="pw2"
					onchange="check_pw()" required> <label>비밀번호 확인</label> <span id="check"></span>
			</div> -->
			<input type="submit" name="commit" value="회원가입">
			<div class="signup_link">
				<a href="index.jsp">뒤로가기</a>
			</div>
		</form>
	</div>

</body>
</html>
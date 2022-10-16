<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스트랩 : 로그인</title>
<!-- CDN -->
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- css -->
<link rel="stylesheet" type="text/css" href="/resources/css/common.css">
	<style>
		.loginView{
			margin: 100px auto;
			text-align: center;
			width: 400px;
			height: 400px;
			line-height: 50%;
		}
		.loginView button,.loginView input{
			width: 200px;
		}
		a{
			text-decoration: none;
			color: black;
		}
		.kakao{
			width: 200px;
			height: 40px;
		}
		
		
	</style>
</head>
<body>
	<div class="wrap container">
		<!-- 헤더&메뉴바 -->
		<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
		<!-- 컨텐츠 -->
		<div class="contents">
			<div class="loginView">
					<h3>로그인</h3>
					<hr><br><br>
					<input type="text" id="memberId" name="memberId" placeholder="아이디"><br><br>
					<input type="password" id="memberPwd" name="memberPwd" placeholder="비밀번호"><br><br><br><br>
					<button class="btn btn-primary" id="loginBtn" type="button">로그인</button><br><br>
					<a href="https://kauth.kakao.com/oauth/authorize?client_id=21529b6c9fe8f1a51afa1b59f685c562&redirect_uri=http://localhost:9999/member/socialEnroll.strap&response_type=code">
						<img class="kakao login" src="/resources/image/kakao_login_medium_narrow.png"><br><br><br>
					</a>
					<a href="/member/findId.strap">아이디 찾기</a> <a href="/member/findPwd.strap">비밀번호 찾기</a><br><br><br><br>
					<button class="btn btn-primary" type="button" onclick="location.href='/member/enroll.strap'">회원 가입</button>
			</div>
		</div>
		<!-- 푸터 -->
		<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	</div>
	<script>
		$("#loginBtn").on("click",function(){
			var memberId = $("#memberId").val();
			var memberPwd = $("#memberPwd").val();
			$.ajax({
				url:"/member/login.strap",
				type:"post",
				data:{"memberId":memberId, "memberPwd":memberPwd},
				success:function(result){
					if(result == "ok"){
						location.href="/"
					} else{
						window.alert("아이디 또는 비밀번호를 확인해주세요")
					}
				}
			});
		})
	</script>
</body>
</html>
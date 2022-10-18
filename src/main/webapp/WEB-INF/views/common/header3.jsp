<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<header class="header">
	<div class="logo" style="cursor: pointer;" onclick="location.href='/';" >
		<img src="/resources/images/logo.png" style="width: 450px; height: 110px; object-fit: cover;">
	</div>
	<div class="login-wrap">
		<div id="text-menu">
			<c:if test="${sessionScope.memberNick eq null}">
				<li style="cursor: pointer;" onclick="location.href='/member/loginView.strap';">로그인</li>
			</c:if>
			<c:if test="${sessionScope.memberNick ne null}">
				<li class="session">
					<b>${memberNick }</b><br>
					<c:if test="${sessionScope.access_token eq null}">
						<a href="/member/logout.strap">로그아웃</a>
					</c:if>
					<c:if test="${sessionScope.access_token ne null}">
						<a href="/member/kakaoLogout.strap?token=${sessionScope.access_token}">로그아웃</a>
					</c:if>
				</li>
			</c:if>
	        <li style="cursor: pointer;" onclick="location.href='/order/list.strap';">마이쇼핑</li>
		</div>
		<div id="icon-menu">
			<div class="menu-img">
				<img class="icon-img" src="/resources/images/mypage.png">
			</div>
			<div class="menu-img">
				<img class="icon-img" src="/resources/images/cart.png">
			</div>
		</div>
	</div>
</header>
<menu>
    <div class="navBar">
			<li onclick="location.href='#';">매칭</li>
	  		<li onclick="location.href='/product/list.strap';">보충제</li>
	  		<li onclick="location.href='/board/list.strap';">게시판</li>
	</div>
</menu>

</body>
</html>
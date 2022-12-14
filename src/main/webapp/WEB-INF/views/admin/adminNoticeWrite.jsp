<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="/resources/image/s.png">
<title>스트랩(관리자) : 공지사항 등록</title>
<style>
input{

}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/adminHeader.jsp"></jsp:include>
	<div class="container">
		<div id="essential info">
			<br>
			<p style="text-align: left; font-size: 20px;">공지사항 작성</p>
			<hr>
		</div>
		
		<form action="/admin/registerNotice.strap" method="post" enctype="multipart/form-data">
			<div class="form-floating">
				<input type="text" class="form-control" id="noticeTitleInput" name="noticeTitle" placeholder="제목 입력">
				<label for="noticeTitleInput"> 공지사항 제목</label>
			</div>
			<br>
			사진 등록
			<input class="form-control" type="file" id="formProfile" name="uploadFile">
			<br>
			<div div class="col" align="center" >
				<div class="form-floating">
					<textarea class="form-control" placeholder="내용을 입력해주세요" id="notice-textarea" name="noticeContents" style="height: 400px"></textarea>
					<label for="notice-textarea">내용 입력</label>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col" align="center">
					<button class="btn btn-dark" type="submit">작성 완료</button>
				</div>
			</div>
			<br>
			<br>
		</form>
	</div>
</body>
</html>
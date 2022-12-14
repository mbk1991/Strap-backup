<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<meta charset="UTF-8">

<html>
<head>
<link rel="icon" href="/resources/image/s.png">
<title>스트랩(관리자) : 상품관리</title>
<script src="/resources/js/jquery-3.6.1.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> -->
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
body { 
	background-color: #EAEAEA;
}
#title-img{
 	width:250px;
 	height: 100px;
 	object-fit: contain;
	cursor: pointer;"
}

.navbar{
	background-color:black;
}
.nav-menu{
	font-weight: 1000;
	cursor:pointer;
}
.admin-title{
	text-decoration:none;
	color:black;
}
.admin-menu{
	color: white;
	text-decoration:none;
}
#admin-logout{
	color: black;
	text-decoration:none;
}
 .pagination a{
	 	color:#c0c0c0;
	 	border-style:none;
 }
.product-menu{
	width:200px;
	background-color:black;
	padding:6px;
	border-radius:3px;
}
.p-menu{
	color:white;
	text-decoration:none;
}
.p-menu:hover{
	color:gray
}
</style>
</head>
<body>
<!-- 헤더&메뉴바 -->
<jsp:include page="/WEB-INF/views/common/adminHeader.jsp"></jsp:include>
<div class="wrap container">
	<!-- 컨텐츠 -->
	<div class="row text-center mt-3 mb-3">
		<div class="col">
			<span class="product-menu"><a class="p-menu" href="/admin/productView.strap">상품관리</a></span>
		</div>
		<div class="col">
			<span class="product-menu"><a class="p-menu" href="/admin/orderView.strap">주문관리</a></span>
		</div>
		<div class="col">
			<span class="product-menu"><a class="p-menu" href="/admin/couponView.strap">쿠폰관리</a></span>
		</div>
	</div>
	<div id="contents" class="row">
		<div class="contents-side col">
			<div id="contents-wrap">
				<div id="title">
					<div style="font-size:20px;">상품관리 (${paging.totalCount })</div><hr>
				</div>
				<div id="searchArea" style="text-align:center;">
					<div class="mb-3" id="search_border" style="border:2px solid darkorange;padding:3px; border-radius:4px; display:inline-block;height:40px;">
						<form id="search-form" action="/admin/productView.strap" method="get">
							<input id="searchVal" name="searchVal" type="text" value="${search.searchVal }" placeholder=" 브랜드명 or 상품명 검색." style="border-style:none; padding:4px; width:400px;">
							<input id="searchColumn"  	name="searchColumn" 	type="hidden">
							<input id="orderCondition"  name="orderCondition" 	type="hidden">
							<button style="background-color:white;border-style:none;" ><i class="fa-solid fa-magnifying-glass"></i></button>
						</form>
					</div>
				</div>
				<div id="adminProductBtnWrap" style="background-color:rgb(165,165,165);padding:12px;text-align:center;">
					<button style="width:150px;font-weight:bold;color:white;background-color:darkorange;border-style:none;border-radius:4px;" onclick="location.href='/admin/product/registerView.strap';">상품등록</button>
					<button style="width:150px;font-weight:bold;color:white;background-color:darkorange;border-style:none;border-radius:4px;" onclick="renewSales();">상품판매량갱신</button>
				</div>
				<div id="productList">
					<table style="text-align:center; font-size:14px; width:100%;">
						<tr style="height:30px; border-bottom:1px solid #c0c0c0;">
<!-- 								<th class="col-2" onclick="orderSubmit('product_name','asc');">상품명</th> -->
<!-- 								<th class="col-1" onclick="orderSubmit('product_price','asc');">상품가격</th> -->
<!-- 								<th class="col-1" onclick="orderSubmit('product_sales','asc');">판매량</th> -->
<!-- 								<th class="col-1" onclick="orderSubmit('grade_aver','asc');">평점</th> -->
<!-- 								<th class="col-1" >수정</th> -->
<!-- 								<th class="col-1">삭제</th> -->
<!-- 								<th class="col-1" onclick="orderSubmit('product_modi_date','asc');">수정일</th> -->
<!-- 								<th class="col-1" onclick="orderSubmit('product_regi_date','asc');">등록일</th> -->
								<c:choose>
									<c:when test="${search.searchColumn eq 'product_no' and search.orderCondition eq 'desc' }">
										<th class="col-1" onclick="orderSubmit('product_no','asc');">상품번호<span>▽</span></th>
									</c:when>
									<c:otherwise>
										<th class="col-1" onclick="orderSubmit('product_no','desc');">상품번호<span>△</span></th>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${search.searchColumn eq 'product_brand' and search.orderCondition eq 'desc' }">
										<th class="col-1" onclick="orderSubmit('product_brand','asc');">브랜드<span>▽</span></th>
									</c:when>
									<c:otherwise>
										<th class="col-1" onclick="orderSubmit('product_brand','desc');">브랜드<span>△</span></th>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${search.searchColumn eq 'product_name' and search.orderCondition eq 'desc' }">
										<th class="col-1" onclick="orderSubmit('product_name','asc');">상품명<span>▽</span></th>
									</c:when>
									<c:otherwise>
										<th class="col-1" onclick="orderSubmit('product_name','desc');">상품명<span>△</span></th>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${search.searchColumn eq 'product_price' and search.orderCondition eq 'desc' }">
										<th class="col-1" onclick="orderSubmit('product_price','asc');">상품가격<span>▽</span></th>
									</c:when>
									<c:otherwise>
										<th class="col-1" onclick="orderSubmit('product_price','desc');">상품가격<span>△</span></th>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${search.searchColumn eq 'product_sales' and search.orderCondition eq 'desc' }">
										<th class="col-1" onclick="orderSubmit('product_sales','asc');">판매량<span>▽</span></th>
									</c:when>
									<c:otherwise>
										<th class="col-1" onclick="orderSubmit('product_sales','desc');">판매량<span>△</span></th>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${search.searchColumn eq 'grade_aver' and search.orderCondition eq 'desc' }">
										<th class="col-1" onclick="orderSubmit('grade_aver','asc');">평점<span>▽</span></th>
									</c:when>
									<c:otherwise>
										<th class="col-1" onclick="orderSubmit('grade_aver','desc');">평점<span>△</span></th>
									</c:otherwise>
								</c:choose>
								
								<th class="col-1" >수정</th>
								<th class="col-1">삭제</th>
								
								<c:choose>
									<c:when test="${search.searchColumn eq 'product_modi_date' and search.orderCondition eq 'desc' }">
										<th class="col-1" onclick="orderSubmit('product_modi_date','asc');">수정일<span>▽</span></th>
									</c:when>
									<c:otherwise>
										<th class="col-1" onclick="orderSubmit('product_modi_date','desc');">수정일<span>△</span></th>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${search.searchColumn eq 'product_regi_date' and search.orderCondition eq 'desc' }">
										<th class="col-1" onclick="orderSubmit('product_regi_date','asc');">등록일<span>▽</span></th>
									</c:when>
									<c:otherwise>
										<th class="col-1" onclick="orderSubmit('product_regi_date','desc');">등록일<span>△</span></th>
									</c:otherwise>
								</c:choose>
						</tr>
						<c:forEach items="${pList}" var="product">
							<tr>
								<td>${product.productNo }</td>
								<td>${product.productBrand }</td>
								<td>${product.productName }</td>
								<td>
									<fmt:formatNumber value="${product.productPrice }" pattern="#,###"/>
									<span>원</span>
								</td>
								<td>${product.productSales }</td>
								<td>${product.gradeAver }</td>
								<td onclick="location.href='/admin/product/modifyView.strap?productNo=${product.productNo}';"><i class="fa-regular fa-pen-to-square"></i>수정</td>
								<td onclick="deleteProduct(${product.productNo});"><i class="fa-regular fa-trash-can"></i>삭제</td>
								<td>${product.productModiDate }</td>
								<td>${product.productRegiDate }</td>
							</tr>
						</c:forEach>						
					</table>
				</div>
				<nav aria-label="Page navigation example" style="width:200px;margin:10px auto; border-style:none; color:#c0c0c0;">
				  <ul class="pagination">
				    <li class="page-item">
				    <c:if test="${paging.startNavi > paging.startPage }">
				      <a class="page-link" href="/admin/productView.strap?page=${paging.startNavi-1 }&searchVal=${search.searchVal}" aria-label="<">
				        <span aria-hidden="true">&laquo;</span>
				      </a>
				     </c:if>
				    </li>
				    <c:forEach begin="${paging.startNavi }" end="${paging.endNavi }" var="n">
				    <li class="page-item"><a class="page-link" <c:if test="${paging.page eq n }">style="font-weight:bold;color:darkorange"</c:if>  href="/admin/productView.strap?page=${n }&searchVal=${search.searchVal}">${n }</a></li>
				    </c:forEach>
				    <c:if test="${paging.endNavi < paging.endPage }">
				    <li class="page-item">
				      <a class="page-link" href="/admin/productView.strap?page=${paging.endNavi+1 }&searchVal=${search.searchVal}" aria-label=">">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				    </c:if>
				  </ul>
				</nav>
			</div>
		</div>
	</div>
	<!-- 푸터 -->
<!-- 	<div id="footer" class="row"> -->
<!-- 		<div class="col"> -->
<%-- 			<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include> --%>
<!-- 		</div> -->
<!-- 	</div> -->
</div>
</body>
<script>
function deleteProduct(thisNo){
	var productNo = thisNo;
	if(confirm("상품을 삭제하시겠습니까?")){
		$.ajax({
			url:"/admin/product/remove.strap",
			data:{"productNo":productNo},
			type:"get",
			success:function(result){
				if(result == "success"){
					location.reload();
				}else{
					
				}
			},
			error:function(){}
		});
	}
}

//정렬
function orderSubmit(column,order){ 
	var searchForm = document.querySelector("#search-form");
	var columnName = document.querySelector("#searchColumn");
	var orderCon = document.querySelector("#orderCondition");
	columnName.value = column;
	orderCon.value = order;
	searchForm.submit();
}

//판매량 집계
function renewSales(){
	$.ajax({
		url:"/admin/product/sales.strap",
		data:{},
		type:"post",
	  	beforeSend: function() {
              //통신을 시작할때 처리되는 함수 
              $('html').css("cursor","wait");   // 현재 html 문서위에 있는 마우스 커서를 로딩 중 커서로 변경
      		},
//         complete: function() {
//               //통신이 완료된 후 처리되는 함수
//               $('html').css("cursor","auto"); // 통신이 완료 된 후 다시 일반적인 커서 모양으로 변경
//          },
		success:function(result){
			if(result == "success"){
				alert("판매량 갱신이 완료되었습니다.");
				location.reload();
			}else{
				
			}
		},
		error:function(){}
	});
}


</script>

</html>

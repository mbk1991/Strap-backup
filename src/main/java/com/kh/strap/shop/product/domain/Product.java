package com.kh.strap.shop.product.domain;

import java.sql.Date;

import com.kh.strap.shop.review.domain.Review;

public class Product {

	private Review review;					//이 상품의 베스트 리뷰
	private int productNo;					//상품번호
	private String productName;				//상품이름
	private String productBrand;			//상품브랜드
	private int productPrice;				//상품가격
	private String productDesc;				//상품설명
	private int gradeSum;					//상품 평점 합계
	private Date productRegiDate;			//상품 등록일
	private Date productModiDate;			//상품 수정일
	private int productSales;				//상품 판매량
	private String mainImgName;				//메인이미지 이름
	private String mainImgReName;			//메인이미지 저장이름
	private String mainImgRoot;				//메인이미지 경로
	private String couponList;				//쿠폰 리스트
	private String productDelete;  			//상품 삭제여부
	private double gradeAver;					//평점 평균
	private int reviewCount;				//리뷰개수
	private int orderQty;					//주문상품 수량체크용

	public Product() {
	}
	public Product(int productNo) {
		super();
		this.productNo = productNo;
	}
	public Product(Review review, int productNo, String productName, String productBrand, int productPrice,
			String productDesc, int gradeSum, Date productRegiDate, Date productModiDate, int productSales,
			String mainImgName, String mainImgReName, String mainImgRoot, String couponList, String productDelete,
			double gradeAver, int reviewCount, int orderQty) {
		super();
		this.review = review;
		this.productNo = productNo;
		this.productName = productName;
		this.productBrand = productBrand;
		this.productPrice = productPrice;
		this.productDesc = productDesc;
		this.gradeSum = gradeSum;
		this.productRegiDate = productRegiDate;
		this.productModiDate = productModiDate;
		this.productSales = productSales;
		this.mainImgName = mainImgName;
		this.mainImgReName = mainImgReName;
		this.mainImgRoot = mainImgRoot;
		this.couponList = couponList;
		this.productDelete = productDelete;
		this.gradeAver = gradeAver;
		this.reviewCount = reviewCount;
		this.orderQty = orderQty;
	}
	public Review getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review = review;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public int getGradeSum() {
		return gradeSum;
	}
	public void setGradeSum(int gradeSum) {
		this.gradeSum = gradeSum;
	}
	public Date getProductRegiDate() {
		return productRegiDate;
	}
	public void setProductRegiDate(Date productRegiDate) {
		this.productRegiDate = productRegiDate;
	}
	public Date getProductModiDate() {
		return productModiDate;
	}
	public void setProductModiDate(Date productModiDate) {
		this.productModiDate = productModiDate;
	}
	public int getProductSales() {
		return productSales;
	}
	public void setProductSales(int productSales) {
		this.productSales = productSales;
	}
	public String getMainImgName() {
		return mainImgName;
	}
	public void setMainImgName(String mainImgName) {
		this.mainImgName = mainImgName;
	}
	public String getMainImgReName() {
		return mainImgReName;
	}
	public void setMainImgReName(String mainImgReName) {
		this.mainImgReName = mainImgReName;
	}
	public String getMainImgRoot() {
		return mainImgRoot;
	}
	public void setMainImgRoot(String mainImgRoot) {
		this.mainImgRoot = mainImgRoot;
	}
	public String getCouponList() {
		return couponList;
	}
	public void setCouponList(String couponList) {
		this.couponList = couponList;
	}
	public String getProductDelete() {
		return productDelete;
	}
	public void setProductDelete(String productDelete) {
		this.productDelete = productDelete;
	}
	public double getGradeAver() {
		return gradeAver;
	}
	public void setGradeAver(double gradeAver) {
		this.gradeAver = gradeAver;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public int getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}
	@Override
	public String toString() {
		return "Product [review=" + review + ", productNo=" + productNo + ", productName=" + productName
				+ ", productBrand=" + productBrand + ", productPrice=" + productPrice + ", productDesc=" + productDesc
				+ ", gradeSum=" + gradeSum + ", productRegiDate=" + productRegiDate + ", productModiDate="
				+ productModiDate + ", productSales=" + productSales + ", mainImgName=" + mainImgName
				+ ", mainImgReName=" + mainImgReName + ", mainImgRoot=" + mainImgRoot + ", couponList=" + couponList
				+ ", productDelete=" + productDelete + ", gradeAver=" + gradeAver + ", reviewCount=" + reviewCount
				+ ", orderQty=" + orderQty + "]";
	}
	
	
}

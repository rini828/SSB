package com.ssb.Mitem;

//수정 필요
public class ItemDTO {
	
	// item 테이블
	private int item_id; // 제품 ID 
	private String item_name; // 제품 이름
	private String item_img_main; // 대표 이미지
	private String item_img_sub; // 상세 이미지
	private String item_img_logo; // 로고 이미지
	
	///////////////////////////////////////////
	private int item_price;
	/////////////////////////////////////
	
	// category 테이블
	private int category_id; // 카테고리 ID
	private int category_code; // 카테고리 구분 (렌탈or판매)
	private String category_sport; // 스포츠
	private String category_major; // 대분류 (옷/용품/장비)
	private String category_sub; // 소분류 (상/하의/양말/신발)
	private String category_brand; // 브랜드
	// options 테이블
	private int options_id; // 옵션 ID
	private int options_price; // 가격
	private int options_quantity; // 재고
	private String options_name; // 옵션명
	private String options_value; // 옵션값
	
	
	
	
	// alt shift s + r  
	// => set/get 메서드 자동생성
	
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getCategory_code() {
		return category_code;
	}
	public void setCategory_code(int category_code) {
		this.category_code = category_code;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_img_main() {
		return item_img_main;
	}
	public void setItem_img_main(String item_img_main) {
		this.item_img_main = item_img_main;
	}
	public String getItem_img_sub() {
		return item_img_sub;
	}
	public void setItem_img_sub(String item_img_sub) {
		this.item_img_sub = item_img_sub;
	}
	public String getItem_img_logo() {
		return item_img_logo;
	}
	public void setItem_img_logo(String item_img_logo) {
		this.item_img_logo = item_img_logo;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_sport() {
		return category_sport;
	}
	public void setCategory_sport(String category_sport) {
		this.category_sport = category_sport;
	}
	public String getCategory_major() {
		return category_major;
	}
	public void setCategory_major(String category_major) {
		this.category_major = category_major;
	}
	public String getCategory_sub() {
		return category_sub;
	}
	public void setCategory_sub(String category_sub) {
		this.category_sub = category_sub;
	}
	public String getCategory_brand() {
		return category_brand;
	}
	public void setCategory_brand(String category_brand) {
		this.category_brand = category_brand;
	}
	public int getOptions_id() {
		return options_id;
	}
	public void setOptions_id(int options_id) {
		this.options_id = options_id;
	}
	public int getOptions_price() {
		return options_price;
	}
	public void setOptions_price(int options_price) {
		this.options_price = options_price;
	}
	public int getOptions_quantity() {
		return options_quantity;
	}
	public void setOptions_quantity(int options_quantity) {
		this.options_quantity = options_quantity;
	}
	public String getOptions_name() {
		return options_name;
	}
	public void setOptions_name(String options_name) {
		this.options_name = options_name;
	}
	public String getOptions_value() {
		return options_value;
	}
	public void setOptions_value(String options_value) {
		this.options_value = options_value;
	}

	/////////////////////////////////////
	public int getItem_price() {
		return item_price;
	}
	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}
	////////////////////////////
	
	

}

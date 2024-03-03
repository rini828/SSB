package com.ssb.cart.db;

public class cartDTO {
	//cart테이블
	private int cart_id;//장바구니ID
	private int item_id;//제품ID
	private int cart_quantity;//장바구니 구매수량
	//item테이블
	private String item_name;//제품이름
	private String item_img_main;//제품이미지
	private int item_price;//제품가격
	//option테이블
	private int options_id;//옵션ID
	private String options_name;//옵션이름
	private String options_value;//옵션값
	private int options_price;//옵션가격
	private int options_quantity;//재고수량
	
	private int total_price; // 데이터테이블에 없는데 임의로 생성함.
	
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getCart_quantity() {
		return cart_quantity;
	}
	public void setCart_quantity(int cart_quantity) {
		this.cart_quantity = cart_quantity;
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
	public int getItem_price() {
		return item_price;
	}
	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}
	public int getOptions_id() {
		return options_id;
	}
	public void setOptions_id(int options_id) {
		this.options_id = options_id;
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
	
	@Override
	public String toString() {
		return "cartDTO [cart_id=" + cart_id + ", item_id=" + item_id + ", cart_quantity=" + cart_quantity
				+ ", item_name=" + item_name + ", item_img_main=" + item_img_main + ", item_price=" + item_price
				+ ", options_id=" + options_id + ", options_name=" + options_name + ", options_value=" + options_value
				+ ", options_price=" + options_price + ", options_quantity=" + options_quantity + "]";
	}
}

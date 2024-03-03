package com.ssb.wishlist.db;

public class wishlistDTO {
	private int wishlist_id;
	private int item_id;
	
	private String item_name;//제품이름
	private String item_img_main;//제품이미지
	private int item_price;//제품가격
	
	public int getWishlist_id() {
		return wishlist_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public String getItem_img_main() {
		return item_img_main;
	}
	public int getItem_price() {
		return item_price;
	}
	public void setWishlist_id(int wishlist_id) {
		this.wishlist_id = wishlist_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public void setItem_img_main(String item_img_main) {
		this.item_img_main = item_img_main;
	}
	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}
	
	@Override
	public String toString() {
		return "wishlistDTO [wishlist_id=" + wishlist_id + ", item_id=" + item_id + ", item_name=" + item_name
				+ ", item_img_main=" + item_img_main + ", item_price=" + item_price + "]";
	}
}

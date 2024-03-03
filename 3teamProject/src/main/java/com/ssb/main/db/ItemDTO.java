package com.ssb.main.db;

public class ItemDTO {

	private int item_id;
	private String item_name;
	private String item_img_main;
	private int item_price;
	private String item_img_sub;
	private String item_img_logo;
	private int category_id;
	
	
	public int getItem_id() {
		return item_id;
	}
	public int getItem_price() {
		return item_price;
	}
	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
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
	@Override
	public String toString() {
		return super.toString();
	}
	
	
	
	
	
	
}

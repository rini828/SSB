package com.ssb.cart.db;

public class orderDTO {
	private String item_name;
	private String options_name;
	private String options_value;
	private int cart_quantity;
	private int options_price;
	private int item_price;
	
	
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
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
	public int getCart_quantity() {
		return cart_quantity;
	}
	public void setCart_quantity(int cart_quantity) {
		this.cart_quantity = cart_quantity;
	}
	public int getOptions_price() {
		return options_price;
	}
	public void setOptions_price(int options_price) {
		this.options_price = options_price;
	}
	public int getItem_price() {
		return item_price;
	}
	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}
	
	@Override
	public String toString() {
		return "orderDTO [item_name=" + item_name + ", options_name=" + options_name + ", options_value="
				+ options_value + ", cart_quantity=" + cart_quantity + ", options_price=" + options_price
				+ ", item_price=" + item_price + "]";
	}
	
}

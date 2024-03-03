package com.ssb.option.db;

public class optionsDTO {
	private int options_id;
	private String options_name;
	private String options_value;
	private int options_price;
	private int options_quantity;
	private int item_id;
	
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
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	
	@Override
	public String toString() {
		return "optionsDTO [options_id=" + options_id + ", options_name=" + options_name + ", options_value="
				+ options_value + ", options_price=" + options_price + ", options_quantity=" + options_quantity
				+ ", item_id=" + item_id + "]";
	}
	
}

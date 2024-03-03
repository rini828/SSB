package com.ssb.rental.db;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

public class RentalDTO {

	private int rental_item_id;
	private String rental_item_name;
	private int rental_item_price;
	private int rental_opt_quantity;
	private String rental_opt_name;
	private String rental_opt_value;
	private String rental_img_main;
	private String rental_img_sub;
	private String rental_img_logo;
	private int rental_days;
	
	// category 테이블
	private int category_id;
	private int category_code; 
	private String category_sport; 
	private String category_major;
	private String category_sub; 
	private String category_brand;
	

	
	// reservePage 출력용으로 만듦.
	private LocalDate rental_str; 
	private LocalDate rental_end; 
	
	
		
	public int getRental_days() {
		return rental_days;
	}
	
	public void setRental_days(int rental_days) {
		this.rental_days = rental_days;
	}
	
	public LocalDate getRental_str() {
		return rental_str;
	}

	public void setRental_str(LocalDate rental_str) {
		this.rental_str = rental_str;
	}

	public LocalDate getRental_end() {
		return rental_end;
	}

	public void setRental_end(LocalDate rental_end) {
		this.rental_end = rental_end;
	}

	public int getRental_item_id() {
		return rental_item_id;
	}

	public void setRental_item_id(int rental_item_id) {
		this.rental_item_id = rental_item_id;
	}

	public String getRental_item_name() {
		return rental_item_name;
	}

	public void setRental_item_name(String rental_item_name) {
		this.rental_item_name = rental_item_name;
	}

	public int getRental_item_price() {
		return rental_item_price;
	}

	public void setRental_item_price(int rental_item_price) {
		this.rental_item_price = rental_item_price;
	}

	public int getRental_opt_quantity() {
		return rental_opt_quantity;
	}

	public void setRental_opt_quantity(int rental_opt_quantity) {
		this.rental_opt_quantity = rental_opt_quantity;
	}

	public String getRental_opt_name() {
		return rental_opt_name;
	}

	public void setRental_opt_name(String rental_opt_name) {
		this.rental_opt_name = rental_opt_name;
	}

	public String getRental_opt_value() {
		return rental_opt_value;
	}

	public void setRental_opt_value(String rental_opt_value) {
		this.rental_opt_value = rental_opt_value;
	}

	public String getRental_img_main() {
		return rental_img_main;
	}

	public void setRental_img_main(String rental_img_main) {
		this.rental_img_main = rental_img_main;
	}

	public String getRental_img_sub() {
		return rental_img_sub;
	}

	public void setRental_img_sub(String rental_img_sub) {
		this.rental_img_sub = rental_img_sub;
	}

	public String getRental_img_logo() {
		return rental_img_logo;
	}

	public void setRental_img_logo(String rental_img_logo) {
		this.rental_img_logo = rental_img_logo;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public int getCategory_code() {
		return category_code;
	}

	public void setCategory_code(int category_code) {
		this.category_code = category_code;
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


	@Override
	public String toString() {
		return "RentalDTO [rental_item_id=" + rental_item_id + ", rental_item_name=" + rental_item_name
				+ ", rental_item_price=" + rental_item_price + ", rental_opt_quantity=" + rental_opt_quantity
				+ ", rental_opt_name=" + rental_opt_name + ", rental_opt_value=" + rental_opt_value
				+ ", rental_img_main=" + rental_img_main + ", rental_img_sub=" + rental_img_sub + ", rental_img_logo="
				+ rental_img_logo + ", category_id=" + category_id + ", category_code=" + category_code
				+ ", category_sport=" + category_sport + ", category_major=" + category_major + ", category_sub="
				+ category_sub + ", category_brand=" + category_brand + "]";
	}

	public void setRental_end1(Object add) {
		// TODO Auto-generated method stub
		
	} 
	
	
}
package com.ssb.cart.db;

public class totalDTO {
	
	private int totalPrice;

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void priceUpdate(int price) {
		this.totalPrice +=price;
	}
	
}

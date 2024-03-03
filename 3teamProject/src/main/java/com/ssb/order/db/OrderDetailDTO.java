package com.ssb.order.db;

import java.nio.file.spi.FileSystemProvider;
import java.sql.Date;

import com.ssb.order.vo.ItemType;

public class OrderDetailDTO {
	
	private Long orderD_id;
	private Long orders_id;
	private int item_id;
	private String item_name; //DB엔 없음 말그대로 DTO용
	private Long options_id;
	private int rental_itemId;
	private int quantity;
	private int price;
	private int totalPrice; //DB에 없어용
	private ItemType type;
	private Date rental_str;
	private Date rental_end;

	
	
	public OrderDetailDTO() {
	}


	public static OrderDetailDTO createSaleItem(Long ordersId, int itemId, String itemName ,int quantity, int price, long optionsId) {
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.orders_id = ordersId;
		orderDetailDTO.item_id = itemId;
		orderDetailDTO.item_name = itemName;
		orderDetailDTO.options_id = optionsId;
		orderDetailDTO.quantity = quantity;
		orderDetailDTO.price = price;
		orderDetailDTO.type = ItemType.SALE;
		orderDetailDTO.totalPrice = OrderDetailDTO.calTotalPrice(price, quantity);
		
		return orderDetailDTO;
	}
	
	public static OrderDetailDTO createRentalItem(Long ordersId, int rentalItemId,String itemName, int price, Date rentalStr, Date rentalEnd) {
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.orders_id = ordersId;
		orderDetailDTO.rental_itemId=rentalItemId;
		orderDetailDTO.item_name = itemName;
		orderDetailDTO.quantity = 1;
		orderDetailDTO.price = price;
		orderDetailDTO.type = ItemType.RENTAL;
		orderDetailDTO.rental_str = rentalStr;
		orderDetailDTO.rental_end = rentalEnd;
		orderDetailDTO.totalPrice = OrderDetailDTO.calTotalPrice(price, 1);
		
		return orderDetailDTO; 
	}
	
	


	public static OrderDetailDTO receiveInfo(Long id, Long orders_id, int item_id, String item_name,long optionsId, int rental_itemId, int quantity,
			int price, Date rental_str, Date rental_end) {
		OrderDetailDTO dto = new OrderDetailDTO();
		dto.orderD_id = id;
		dto.orders_id = orders_id;
		dto.item_id = item_id;
		dto.item_name = item_name;
		dto.options_id = optionsId;
		dto.rental_itemId = rental_itemId;
		dto.quantity = quantity;
		dto.price = price;
		dto.rental_str = rental_str;
		dto.rental_end = rental_end;
		
		return dto;
	}

	private static int calTotalPrice(int price , int quantity) {
		return price * quantity; 
	}




	public Long getOrderD_id() {
		return orderD_id;
	}


	public Long getOrders_id() {
		return orders_id;
	}


	


	public int getItem_id() {
		return item_id;
	}


	public int getTotalPrice() {
		return totalPrice;
	}


	public int getRental_itemId() {
		return rental_itemId;
	}


	public int getQuantity() {
		return quantity;
	}


	public int getPrice() {
		return price;
	}


	public ItemType getType() {
		return type;
	}


	public Date getRental_str() {
		return rental_str;
	}


	public Date getRental_end() {
		return rental_end;
	}


	public String getItem_name() {
		return item_name;
	}


	public Long getOptions_id() {
		return options_id;
	}

	//----------------------------setter----------------------------

	public void setOrderD_id(Long orderD_id) {
		this.orderD_id = orderD_id;
	}


	public void setOrders_id(Long orders_id) {
		this.orders_id = orders_id;
	}


	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}


	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}


	public void setOptions_id(Long options_id) {
		this.options_id = options_id;
	}


	public void setRental_itemId(int rental_itemId) {
		this.rental_itemId = rental_itemId;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}


	public void setType(ItemType type) {
		this.type = type;
	}


	public void setRental_str(Date rental_str) {
		this.rental_str = rental_str;
	}


	public void setRental_end(Date rental_end) {
		this.rental_end = rental_end;
	}
	
	

	
	


	
	
}

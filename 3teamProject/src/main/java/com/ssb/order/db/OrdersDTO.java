package com.ssb.order.db;


import java.sql.Date;
import java.time.LocalDateTime;

import com.ssb.order.vo.OrderCheckState;
import com.ssb.order.vo.OrdersSort;
import com.ssb.order.vo.OrdersState;

public class OrdersDTO {

	private long id;
	private long member_id;
	private String member_user_name;
	private OrdersState orders_state;
	private Date orders_date;
	private OrdersSort orders_sort;
	private int original_total_price;
	private int total_price;
	private int location_id;
	
	
	
	public OrdersDTO() {
		
	}


	//판매 주문 생성
	public static OrdersDTO createSaleOrder(long id, long memberId, int location_id) { //파라미터 추가
		OrdersDTO orderDTO = new OrdersDTO();
		orderDTO.id =id;
		orderDTO.member_id = memberId;
		orderDTO.orders_state = OrdersState.STANDBY;
		orderDTO.orders_sort = OrdersSort.SALE;
		orderDTO.total_price= 0;
		
		//------------------------11월 16일 추가---------------------------
		orderDTO.location_id = location_id;
		//--------------------------------------------------------
		return orderDTO;
	}
	
	//랜탈 주문 생성
	public static OrdersDTO createRentalOrder(long id, long memberId , int location_id) {
		OrdersDTO orderDTO = new OrdersDTO();
		orderDTO.id = id;
		orderDTO.member_id = memberId;
		orderDTO.orders_state = OrdersState.STANDBY;
		orderDTO.orders_sort = OrdersSort.RENTAL;

		orderDTO.location_id = location_id;
		
		return orderDTO;
	}
	
	
	public void orderCancel(OrderDetailDTO ...detailDTOs) {
		this.orderCancel();
		
		for(OrderDetailDTO detailDTO : detailDTOs) {
			
		}
		
	}
	
	



	public static OrdersDTO receiveInfo(long id, long memberId, OrdersState ordersState, Date ordersDate,
			OrdersSort ordersSort, int price, int location_id) {
		OrdersDTO dto = new OrdersDTO();
		dto.id = id;
		dto.member_id = memberId;
		dto.orders_state = ordersState;
		dto.orders_date = ordersDate;
		dto.orders_sort = ordersSort;
		dto.total_price = price;
		dto.location_id = location_id;
		return dto;
	}
	
	public void changeTotalPrice(int totalPrice) {
		this.total_price = totalPrice;
	}
	
	public void changeOrderState(OrdersState ordersState) {
		this.orders_state = ordersState;
	}


	public long getId() {
		return id;
	}


	public long getMember_id() {
		return member_id;
	}


	public OrdersState getOrders_state() {
		return orders_state;
	}


	public Date getOrders_date() {
		return orders_date;
	}


	public OrdersSort getOrders_sort() {
		return orders_sort;
	}


	public int getTotal_price() {
		return total_price;
	}

	
	//==================================================

	public void setId(long id) {
		this.id = id;
	}


	public void setMember_id(long member_id) {
		this.member_id = member_id;
	}


	public void setOrders_state(OrdersState orders_state) {
		this.orders_state = orders_state;
	}


	public void setOrders_date(Date orders_date) {
		this.orders_date = orders_date;
	}


	public void setOrders_sort(OrdersSort orders_sort) {
		this.orders_sort = orders_sort;
	}


	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}


	public String getMember_user_name() {
		return member_user_name;
	}


	public void setMember_user_name(String member_user_name) {
		this.member_user_name = member_user_name;
	}


	public int getLocation_id() {
		return location_id;
	}


	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}


	public int getOriginal_total_price() {
		return original_total_price;
	}


	public void setOriginal_total_price(int original_total_price) {
		this.original_total_price = original_total_price;
	}

	

	
	
}

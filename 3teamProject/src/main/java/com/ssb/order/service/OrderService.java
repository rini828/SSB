package com.ssb.order.service;

import java.util.List;

import com.ssb.cart.db.cartDTO;
import com.ssb.option.db.optionsDAO;
import com.ssb.option.db.optionsDTO;
import com.ssb.order.exception.StockLackException;
import com.ssb.order.vo.OrderCheckState;
import com.ssb.rental.db.RentalDAO;
import com.ssb.rental.db.RentalDTO;

public class OrderService {

	public  static OrderCheckState stockCheck(List<cartDTO> cartItems) throws StockLackException {
		
		OrderCheckState orderCheckState = OrderCheckState.STANDBY; 
		
		
		for(cartDTO cartDTO : cartItems) {
			
			optionsDTO optionsDTO = new optionsDTO();
			optionsDAO optionsDAO = new optionsDAO();
			
			optionsDTO = optionsDAO.findById(cartDTO.getOptions_id());
			
			
			if(cartDTO.getCart_quantity()> optionsDTO.getOptions_quantity()) {
				
				orderCheckState = OrderCheckState.LOQ;
				throw new StockLackException("수량이 부족합니다.");		
			}else {
				orderCheckState = OrderCheckState.PASS;
			}
		}
		
		
		return orderCheckState;
		
	}
	
	
	//렌탈 아이템 수량 체크
	public static OrderCheckState rentalStockCheck(int rental_id) throws StockLackException {
		
		OrderCheckState orderCheckState = OrderCheckState.STANDBY; 
		
		RentalDAO rentalDAO = new RentalDAO();
		RentalDTO rentalDTO = rentalDAO.getRentalItem(rental_id);
		
		
		if(rentalDTO.getRental_opt_quantity()<1) {
			orderCheckState = OrderCheckState.LOQ;
			throw new StockLackException("렌탈 아이템 수량이 부족합니다");
		}else {
			orderCheckState = OrderCheckState.PASS;
		}
		
		return orderCheckState;
	}
	
	
}

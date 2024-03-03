package com.ssb.order.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ssb.order.vo.RefundResult;
import com.ssb.order.vo.TurnInResult;

public class OrderDetailDAO {
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	
	

	//-------------------- 판매 아이템 주문생성 시작------------------------
	public void saveSaleDetail(OrderDetailDTO dto) {
		
		try {
			con=getCon();
			sql = "insert into order_detail "
					+ "(orders_id, item_id, options_id, orderD_quantity, "
					+ "orderD_price)"
					+ "values (?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1,dto.getOrders_id());
			pstmt.setLong(2,dto.getItem_id());
			pstmt.setLong(3, dto.getOptions_id());
			pstmt.setInt(4,dto.getQuantity());
			pstmt.setInt(5, dto.getPrice());

			pstmt.execute();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
	}
	
	//-------------------- 판매 아이템 주문생성 끝------------------------
	
	
	
	//-------------------- 렌탈 아이템 주문생성 시작------------------------
	public void saveRentalDetail(OrderDetailDTO dto) {
		
		try {
			con=getCon();
			sql = "insert into order_detail "
					+ "(orders_id, rental_item_id, orderD_quantity, "
					+ "orderD_price, rental_str_date, rental_end_date)"
					+ "values (?,?,1,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1,dto.getOrders_id());
			pstmt.setLong(2,dto.getRental_itemId());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setDate(4,dto.getRental_str());
			pstmt.setDate(5, dto.getRental_end());
			
			pstmt.execute();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
	}
	
	//-------------------- 판매 아이템 주문생성 끝------------------------
	
	
	
	//------------------주문번호(판매상품)에 해당하는 상세정보 모두 긁어옴
	public List<OrderDetailDTO> findByOrdersId(long id){
		
		List<OrderDetailDTO> dtos = new ArrayList<>();

		//---------------------------------------데이터 확인용
//		System.out.println("DAO : 입력받은 id : " + id);
		//----------------------------------------
		
		
		try {
			
			con=getCon();
			
			sql = "select "
					+ "od.orderD_id, od.orders_id, od.item_id ,od.options_id ,im.item_name, od.rental_item_id, im.item_price, "
					+ "od.rental_item_id, od.orderD_quantity, od.orderD_price, od.rental_str_date, od.rental_end_date "
					+ "FROM order_detail od "
					+ "INNER JOIN item im "
					+ "ON od.item_id = im.item_id  "
					+ "where orders_id = ?";
			
			pstmt= con.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				
				OrderDetailDTO dto = new OrderDetailDTO();
				dto.setOrderD_id(rs.getLong("orderD_id"));
				dto.setOrders_id(rs.getLong("orders_id"));
				dto.setItem_id(rs.getInt("item_id"));
				dto.setItem_name(rs.getString("item_name"));
				dto.setOptions_id(rs.getLong("options_id"));
				dto.setRental_itemId(rs.getInt("rental_item_id"));
				dto.setQuantity(rs.getInt("orderD_quantity"));
				dto.setPrice(rs.getInt("item_price"));
				dto.setRental_str(rs.getDate("rental_str_date"));
				dto.setRental_end(rs.getDate("rental_end_date"));
				
				
				
				
				
//				
//				OrderDetailDTO dto = OrderDetailDTO.receiveInfo(
//						rs.getLong("orderD_id"), 
//						rs.getLong("orders_id"), 
//						rs.getInt("item_id"), //아이템 번호 
//						rs.getString("item_name"), 
//						rs.getLong("options_id"), 
//						rs.getLong("rental_item_id"), 
//						rs.getInt("orderD_quantity"), 
//						rs.getInt("orderD_price"),
//						rs.getDate("rental_str_date"), 
//						rs.getDate("rental_end_date"));
//				
				
				dtos.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
		
		
		return dtos;
	}
	
	
	
	
	//--------------------------------환불조치 (주문상품의 수량 원상복구)-------------------------
	public RefundResult refundOrderDetail(OrderDetailDTO dto) {
		RefundResult refundResult = RefundResult.ERROR;
		try {
			// 디비 연결이옵시다
			con = getCon();
			sql ="update options "
					+ "set options_quantity = options_quantity +  (select orderD_quantity from order_detail where orderD_id = ?) "
					+ "where options_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, dto.getOrderD_id());
			pstmt.setLong(2, dto.getOptions_id());
			pstmt.executeUpdate();
			refundResult = RefundResult.SUCCESS;
			
		} catch (Exception e) {
			refundResult = RefundResult.ERROR;
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
		return refundResult;
	}
	
	//--------------------------------환불조치 (주문상품의 수량 원상복구)-------------------------
	
	
	
	//--------------------------------렌탈반납 (주문상품의 수량 원상복구)-------------------------
	public TurnInResult turnRentalInOrderDetail(OrderDetailDTO dto) {
		TurnInResult result = TurnInResult.ERROR;
		try {
			// 디비 연결이옵시다
			con = getCon();
			sql ="update options "
					+ "set options_quantity = options_quantity +  (select orderD_quantity from order_detail where orderD_id = ?) "
					+ "where options_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, dto.getOrderD_id());
			pstmt.setLong(2, dto.getOptions_id());
			pstmt.executeUpdate();
			result = TurnInResult.SUCCESS;
			
		} catch (Exception e) {
			result = TurnInResult.ERROR;
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
		return result;
	}
	
	//--------------------------------렌탈반납 (주문상품의 수량 원상복구)-------------------------
	
	
	
	
	
	//------------------주문번호(렌탈상품)에 해당하는 상세정보 모두 긁어옴
		public List<OrderDetailDTO> findByOrdersIdForRental(long id){
			
			List<OrderDetailDTO> dtos = new ArrayList<>();

			//---------------------------------------데이터 확인용
//			System.out.println("DAO : 입력받은 id : " + id);
			//----------------------------------------
			
			
			try {
				
				con=getCon();
				
				sql = "select "
						+ "od.orderD_id, od.orders_id, od.item_id ,od.options_id ,im.rental_item_name, od.rental_item_id, im.rental_item_price, "
						+ "od.rental_item_id, od.orderD_quantity, od.orderD_price, od.rental_str_date, od.rental_end_date "
						+ "FROM order_detail od "
						+ "INNER JOIN rental_item im "
						+ "ON od.rental_item_id = im.rental_item_id  "
						+ "where orders_id = ?";
				
				pstmt= con.prepareStatement(sql);
				pstmt.setLong(1, id);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					
					OrderDetailDTO dto = new OrderDetailDTO();
					dto.setOrderD_id(rs.getLong("orderD_id"));
					dto.setOrders_id(rs.getLong("orders_id"));
					dto.setItem_id(rs.getInt("rental_item_id"));
					dto.setItem_name(rs.getString("rental_item_name"));
					dto.setOptions_id(rs.getLong("options_id"));
					dto.setRental_itemId(rs.getInt("rental_item_id"));
					dto.setQuantity(rs.getInt("orderD_quantity"));
					dto.setPrice(rs.getInt("rental_item_price"));
					dto.setRental_str(rs.getDate("rental_str_date"));
					dto.setRental_end(rs.getDate("rental_end_date"));
					
					
					
					
//					
//					OrderDetailDTO dto = OrderDetailDTO.receiveInfo(
//							rs.getLong("orderD_id"), 
//							rs.getLong("orders_id"), 
//							rs.getInt("item_id"), //아이템 번호 
//							rs.getString("item_name"), 
//							rs.getLong("options_id"), 
//							rs.getLong("rental_item_id"), 
//							rs.getInt("orderD_quantity"), 
//							rs.getInt("orderD_price"),
//							rs.getDate("rental_str_date"), 
//							rs.getDate("rental_end_date"));
//					
					
					dtos.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			
			
			
			return dtos;
		}
	
	
	
	

	//--------------------데이터 베이스 연결 시작-----------------
	private Connection getCon() throws Exception {
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/ssb");
		con = ds.getConnection();
		return con;
	}
	//--------------------데이터 베이스 연결 끝-----------------
	
	//--------------------자원 할당 해제-----------------
	public void CloseDB() {

			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}
	//--------------------자원 할당 해제-----------------
	
	
	
	
}

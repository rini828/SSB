package com.ssb.order.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ssb.order.exception.OrderPriceException;
import com.ssb.order.service.OrderService;
import com.ssb.order.vo.OrdersSort;
import com.ssb.order.vo.OrdersState;

public class OrdersDAO {
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	

	
	
	//-------------------------- 렌탈 주문 생성 시작 --------------------------------------
		public void saveRentalOrders(OrdersDTO dto) throws OrderPriceException{
			
			try {
				// 1.2 디비 연결
				con = getCon();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {		
				if(!(dto.getTotal_price()>0)) {
					throw new OrderPriceException("주문 토탈 가격에 문제 발생");
				}
				
				// 3. SQL 작성
				sql = "insert into orders (orders_id, member_id, orders_state, orders_date, orders_sort, orders_total_price, location_id) values (?,?,?,now(),?,?, ?)";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setLong(1, dto.getId());
				pstmt.setLong(2, dto.getMember_id());
				pstmt.setString(3, dto.getOrders_state().toString());
				pstmt.setString(4, dto.getOrders_sort().toString());
				pstmt.setInt(5, dto.getTotal_price());
				
				//----------------11월 16일 추가-----------
				pstmt.setInt(6, dto.getLocation_id());
				//----------------11월 16일 추가--------------------
				
				pstmt.executeUpdate();
				
				
				
				//트랜잭션 커밋
				//con.commit();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			
		}
		//-------------------------- 주문 생성 끝 --------------------------------------
	
	
	
	
	
	//-------------------------- 주문 생성 시작 --------------------------------------
	public void saveSaleOrders(OrdersDTO dto) throws OrderPriceException{
		
		try {
			// 1.2 디비 연결
			con = getCon();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {		
			if(!(dto.getTotal_price()>0)) {
				throw new OrderPriceException("주문 토탈 가격에 문제 발생");
			}
			
			// 3. SQL 작성
			sql = "insert into orders (orders_id, member_id, orders_state, orders_date, orders_sort, orders_total_price, location_id) values (?,?,?,now(),?,?, ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setLong(1, dto.getId());
			pstmt.setLong(2, dto.getMember_id());
			pstmt.setString(3, dto.getOrders_state().toString());
			pstmt.setString(4, dto.getOrders_sort().toString());
			pstmt.setInt(5, dto.getTotal_price());
			
			//----------------11월 16일 추가-----------
			pstmt.setInt(6, dto.getLocation_id());
			//----------------11월 16일 추가--------------------
			
			pstmt.executeUpdate();
			
			
			
			//트랜잭션 커밋
			//con.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
	}
	//-------------------------- 주문 생성 끝 --------------------------------------
	
	
	// --------------------------최신 주문번호 찾기 시작------------------------------
	public long findByTodayDate(long date){
	
		long orderId = 0;
		
		try {
			con =getCon();
			sql = "select max(orders_id) from orders where orders_id like ?  ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, date+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				orderId = rs.getLong(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseDB();
		}

		return orderId;
	}
	
	// --------------------------주문번호 찾기 시작------------------------------
	
	//---------------------------주문 번호로 호출 시작-----------------------------
	
	public OrdersDTO findById(long id) {
		
		OrdersDTO dto = null;
		
		try {
			//DB 연결
			con = getCon();
			
			//sql 작성
			sql = "select * from orders where orders_id = ? ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setLong(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
						dto = OrdersDTO.receiveInfo(
						rs.getLong("orders_id"),
						rs.getLong("member_id"), 
						transStateStringToEnum(rs.getString("orders_state")), 
						rs.getDate("orders_date"), 
						transSortStringToEnum(rs.getString("orders_sort")),
						rs.getInt("orders_total_price"),
						
						//-----------------11월 16일 추가-----------------------
						rs.getInt("location_id")
						
						//-----------------------------------------------------
						);
						
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
		return dto;
	}
	
	//-----------------------------유저 주문 정보 호출 끝
	
	
	//----------------------------유저 번호, 주문상태에 따른 내역 가져오기(결제 완료상태)--------------
	public List<OrdersDTO> findByUserAndState(long userNum , String state ,int s, int p) {
		
		List<OrdersDTO> orders = new ArrayList<OrdersDTO>();
		//sql 다시 더 상세하게 해야함
		
		try {
			con = getCon();
			sql = "SELECT os.orders_id, os.member_id, mb.member_user_id, os.orders_state, os.orders_date, os.orders_sort ,os.location_id ,os.orders_total_price "
					+ "FROM orders os "
					+ "JOIN member mb "
					+ "ON os.member_id = mb.member_id"
					+ " WHERE mb.member_id = ? and os.orders_state = ? "
					+ "order by orders_id desc limit ?,? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, userNum);
			pstmt.setString(2, state.toString());
			pstmt.setInt(3, s-1); // 시작행 번호-1
			pstmt.setInt(4, p); // 페이지 개수
			
			
			rs= pstmt.executeQuery();
				
				while(rs.next()) {
					
					
					
					OrdersDTO dto = new OrdersDTO();
					dto.setId(rs.getLong("orders_id"));
					dto.setMember_id(rs.getLong("member_id"));
					dto.setMember_user_name(rs.getString("member_user_id"));
					dto.setOrders_state(transStateStringToEnum(rs.getString("orders_state")));
					dto.setOrders_date(rs.getDate("orders_date"));
					dto.setOrders_sort(transSortStringToEnum(rs.getString("orders_sort")));
					
					//---------------------11월 16일 추가-----------------------------------
					dto.setLocation_id(rs.getInt("location_id"));
					//----------------------------------------------------------------------
					
					dto.setTotal_price(rs.getInt("orders_total_price"));
					
//					
//					
//					
//					OrdersDTO dto = OrdersDTO.receiveInfo(
//							rs.getLong("orders_id"),
//							rs.getLong("member_id"),
//							transStateStringToEnum(rs.getString("orders_state")),
//							rs.getDate("orders_date"), 
//							transSortStringToEnum(rs.getString("orders_sort")),
//							rs.getInt("total_price"));
//					
//					
					orders.add(dto);
				}
			
		
			
		}catch(Exception e) {
			
		}finally {
			CloseDB();
		}
		return orders;
	}
	//----------------------------유저 번호, 주문상태에 따른 내역 가져오기(결제 완료상태)--------------
	
	
	
	//----------------------------주문상태에 따른 내역 가져오기(관리자용)--------------
	public List<OrdersDTO> findByStateForAdmin(String state, int s , int p ) {
		
		List<OrdersDTO> orders = new ArrayList<OrdersDTO>();
		//sql 다시 더 상세하게 해야함
		
		try {
			con = getCon();
			sql = "SELECT os.orders_id, os.member_id, mb.member_user_id, os.orders_state, os.orders_date, os.orders_sort, os.orders_total_price, os.location_id "
					+ "FROM orders os "
					+ "JOIN member mb "
					+ "ON os.member_id = mb.member_id"
					+ " WHERE os.orders_state = ? "
					+ " order by orders_id desc limit ?,?";
			
			
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, state);
			pstmt.setInt(2, s-1); // 시작행 번호-1
			pstmt.setInt(3, p); // 페이지 개수
			
			
			rs= pstmt.executeQuery();
				
				while(rs.next()) {
					
					
					
					OrdersDTO dto = new OrdersDTO();
					dto.setId(rs.getLong("orders_id"));
					dto.setMember_id(rs.getLong("member_id"));
					dto.setMember_user_name(rs.getString("member_user_id"));
					dto.setOrders_state(transStateStringToEnum(rs.getString("orders_state")));
					dto.setOrders_date(rs.getDate("orders_date"));
					dto.setOrders_sort(transSortStringToEnum(rs.getString("orders_sort")));
					dto.setTotal_price(rs.getInt("orders_total_price"));
					
					//-------------------------11월 16일 추가---------------------------
					dto.setLocation_id(rs.getInt("location_id"));
					//-----------------------------------------------------------------
					
					
					orders.add(dto);
				}
			
		
			
		}catch(Exception e) {
			
		}finally {
			CloseDB();
		}
		return orders;
	}
	//----------------------------유저 번호, 주문상태에 따른 내역 가져오기(관리자용)--------------
	
	
	
	
	
	//----------------------------유저 번호, 주문상태, 카테고리 타입에 따른 내역 가져오기(결제 완료상태)--------------
	public List<OrdersDTO> findByUserAndStateAndCategory(long userNum , OrdersState state, int code ) {
		
		List<OrdersDTO> orders = new ArrayList();
		//sql 다시 더 상세하게 해야함
		
		try {
			con = getCon();
			sql = "SELECT os.orders_id, os.member_id, mb.member_user_id, os.orders_state, os.orders_date, os.orders_sort  "
					+ "FROM orders os "
					+ "JOIN member mb "
					+ "ON os.member_id = mb.member_id "
					+ "WHERE mb.member_id = ? and os.orders_state = ? && os.category_code = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, userNum);
			pstmt.setString(2, state.toString());
			pstmt.setInt(3, code);
			
			rs= pstmt.executeQuery();
				
				while(rs.next()) {
					
					
					OrdersDTO dto = new OrdersDTO();
					dto.setId(rs.getLong("orders_id"));
					dto.setMember_id(rs.getLong("member_id"));
					dto.setMember_user_name(rs.getString("member_user_id"));
					dto.setOrders_state(transStateStringToEnum(rs.getString("orders_state")));
					dto.setOrders_date(rs.getDate("orders_date"));
					dto.setOrders_sort(transSortStringToEnum(rs.getString("orders_sort")));
					
					
					
					
//					
//					OrdersDTO dto = OrdersDTO.receiveInfo(
//							rs.getLong("orders_id"),
//							rs.getLong("member_id"),
//							transStateStringToEnum(rs.getString("orders_state")), 
//							rs.getDate("orders_date"), 
//							transSortStringToEnum(rs.getString("orders_sort")),
//							rs.getInt("total_price"));
//					
					orders.add(dto);
				}
			
		
			
		}catch(Exception e) {
			
		}finally {
			CloseDB();
		}
		return orders;
	}
	//----------------------------유저 번호, 주문상태에 따른 내역 가져오기(결제 완료상태)--------------
	
	
	
	
	
	//--------------------주문서 주문상태 변경 업데이트-----------------------------------
	public void updateOrdersState(long ordersId,OrdersState state) {
		
		try {
			con = getCon();
			sql = "update orders set orders_state = ? where orders_id = ? ";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, state.toString());
			pstmt.setLong(2, ordersId);
			
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
	}
	//--------------------주문서 주문상태 변경 업데이트-----------------------------------
	
	
	
	// 주문 개수 계산 메서드 - 
	public int getOrderCount(String ordersState) {
		int result = 0;	
				
		try {
			con = getCon();		
					
			sql = "select count(*) from orders where orders_state= ?";
			pstmt = con.prepareStatement(sql);			
			pstmt.setString(1,ordersState.toString());
			
			rs = pstmt.executeQuery();
					
			if(rs.next()) {
				result = rs.getInt(1);
			}
					
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
				
		return result;		
	}
	// 주문 개수 계산 메서드 - getNoticeCount()
	
	
	
	//-------------------------주문번호 생성 로직---------------------------------
	public long createOrdersId() {
		long orderNumber=0L;
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String todayDate = localDate.format(formatter);
		
		if(findByTodayDate(Long.parseLong(todayDate))==0) {
			todayDate+="000001";
			orderNumber=Long.parseLong(todayDate);
		}else {
			Long findNumber = findByTodayDate(Long.parseLong(todayDate));
			orderNumber = findNumber+1;
		};
		
		return orderNumber;
	}
	//-------------------------주문번호 생성 로직---------------------------------
	
	
	
	//------------------------테스트 코드
	
	public Long testCode(){
		
		long orderId = 0;
		
		try {
			con =getCon();
			sql = "select max(orders_id) from orders";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				orderId = rs.getLong(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseDB();
		}

		return orderId;
	}
	
	//-------------------------------------
	
	
	
	
	
	
	//-------------------------OrdersState 데이터 타입 변환---------------------
	private OrdersState transStateStringToEnum(String type) {
		OrdersState state = null;
		if(type.equals("STANDBY")) {
			state = OrdersState.STANDBY;
		}else if(type.equals("PAYMENT")) {
			state = OrdersState.PAYMENT;
		}else if(type.equals("CANCEL")) {
			state = OrdersState.CANCEL;
		}else if(type.equals("REFUND")) {
			state = OrdersState.REFUND;
		}else if(type.equals("PURCHASE")) {
			state = OrdersState.PURCHASE;
		}else if(type.equals("DETERMINE")) {
			state = OrdersState.DETERMINE;
		}else if(type.equals("TURNIN")) {
			state = OrdersState.TURNIN;
		}else if(type.equals("DELIVERY")) {
			state = OrdersState.DELIVERY;
		}else if(type.equals("BEDELIVERED")) {
			state = OrdersState.BEDELIVERED;
		}
		return state;
	}
	
	//-------------------------OrdersState 데이터 타입 변환---------------------
	
	
	//-------------------------OrdersSort 데이터 타입 변환---------------------
	private OrdersSort transSortStringToEnum(String type) {
		OrdersSort state = null;
		if(type.equals("SALE")) {
			state = OrdersSort.SALE;
		}else if(type.equals("RENTAL")) {
			state = OrdersSort.RENTAL;
		}
		return state;
	}
	
	//-------------------------OrdersSort 데이터 타입 변환---------------------
	
	
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
	
	
	/*
	 * "FROM orders os "
					+ "JOIN member mb "
					+ "ON os.member_id = mb.member_id "
					+ "WHERE mb.member_id = ?
	 */
	
	
	
	// 주문 개수 계산 메서드(마이 페이지용) - 
		public int getOrderCountForMyPage(int id , String ordersState) {
			int result = 0;	
					
			try {
				con = getCon();		
						
				sql = "select count(*) FROM orders "
						//+ "JOIN member mb"
						//+ "ON os.member_id = mb.member_id "
						+ "where member_id = ? and orders_state = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,id);
				pstmt.setString(2,ordersState.toString());
				
				rs = pstmt.executeQuery();
						
				if(rs.next()) {
					result = rs.getInt(1);
				}
						
						
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}
					
			return result;		
		}
		// 주문 개수 계산 메서드(마이페이지용) - getNoticeCount()
	
	
	
	

}

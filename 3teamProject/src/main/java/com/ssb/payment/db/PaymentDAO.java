package com.ssb.payment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PaymentDAO {
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	
	//----------------------결제 내역 저장-----------------------------------
	public void savePaymentInfo(PaymentDTO dto) {
		
		try {
			
			con = getCon();
			sql = "insert into payment"
					+ "(merchant_uid, paid_amount, status, pay_method, success, pg_provider) "
					+ "values (?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setLong(1, dto.getMerchantUid());
			pstmt.setInt(2, dto.getPaidAmount());
			pstmt.setString(3, dto.getStatus());
			pstmt.setString(4, dto.getPaidMethod());
			pstmt.setBoolean(5, dto.isSuccess());
			pstmt.setString(6, dto.getPgProvider());
			
			pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
	}
	//------------------------결제내역 저장 끝--------------------------
	
	
	
	//-----------------------결제내역 호출-----------------------------
	public PaymentDTO findById(long id) {
		
		PaymentDTO dto = new PaymentDTO();
		
		try {
			con = getCon();
			sql = "select * from payment where merchant_uid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto.setMerchantUid(rs.getLong("merchant_uid"));
				dto.setPaidAmount(rs.getInt("paid_amount"));
				dto.setPaidMethod(rs.getString("pay_method"));
				dto.setStatus(rs.getString("status"));
				dto.setSuccess(rs.getBoolean("success"));
				dto.setPgProvider(rs.getString("pg_provider"));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
	}
	
	//-----------------------결제내역 호출-----------------------------
	
	
	
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

package com.ssb.option.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class optionsDAO {

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	
	
	//----------------------------옵션 내용 조회-----------------------
	public optionsDTO findById(int id) {
		
		optionsDTO optionsDTO = new optionsDTO();
		
		try {
			
			con = getCon();
			sql ="select * from options where options_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				optionsDTO.setOptions_id(rs.getInt("options_id"));
				optionsDTO.setOptions_name(rs.getString("options_name"));
				optionsDTO.setOptions_price(rs.getInt("options_price"));
				optionsDTO.setOptions_value(rs.getString("options_value"));
				optionsDTO.setItem_id(rs.getInt("item_id"));
				optionsDTO.setOptions_quantity(rs.getInt("options_quantity"));
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		return optionsDTO;
	}
	//----------------------------옵션 내용 조회-----------------------
	
	
	
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

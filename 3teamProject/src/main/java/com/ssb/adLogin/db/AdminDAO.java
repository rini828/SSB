package com.ssb.adLogin.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminDAO {
	
	// 공통 변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
		
	// 공통) 디비 연결하기(CP)
	private Connection getCon() throws Exception {
			
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/ssb");
		con = ds.getConnection();
			
		return con;			
	}
		
	// 공통) 디비 자원해제
	public void CloseDB() {
			
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close(); 
			if(con != null) con.close(); 
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
				
	// 로그인 체크 메서드 - loginAdmin(AdminDTO adto)
	public int loginAdmin(AdminDTO adto) {
		int result = -1;
			
		try {
			// 1.2. 디비 연결
			con = getCon();
				
			// 3. SQL 구문 작성(select) & pstmt 객체 생성
			sql = "select admin_pw from admin where admin_user_id=?";
			pstmt = con.prepareStatement(sql);
				
			// ?
			pstmt.setString(1, adto.getAdmin_user_id());
				
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
				
			// 5. 데이터 처리
			if(rs.next()) { // 정보 있음
				if(adto.getAdmin_pw().equals(rs.getString("admin_pw"))) { // 비밀번호 일치
					result = 1;
				} else { // 비밀번호 불일치
					result = 0;
				}
			} else { // 정보 없음
				result = -1;
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
			
		return result;
	}
	// 로그인 체크 메서드 - loginAdmin(AdminDTO adto)		
}
package com.ssb.cart.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ssb.option.db.optionsDTO;

public class cartAjaxDAO {
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
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 옵션 선택 메서드
	public ArrayList<optionsDTO> getOptions(String item_id) {
		ArrayList<optionsDTO> dtoArr = new ArrayList<optionsDTO>();
		optionsDTO dto = null;
		try {
			con = getCon();
			sql = "SELECT options_id,options_name,options_value,options_price,options_quantity FROM options WHERE item_id = ? ORDER BY options_id";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, item_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new optionsDTO();
				dto.setOptions_id(rs.getInt("options_id"));
				dto.setOptions_name(rs.getString("options_name"));
				dto.setOptions_value(rs.getString("options_value"));
				dto.setOptions_price(rs.getInt("options_price"));
				dto.setOptions_quantity(rs.getInt("options_quantity"));
				dtoArr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return dtoArr;
	}

	// 장바구니 수정 메서드
	public int updateCart(String cart_id, String item_id, String option_id, String cart_quantity) {
		int result = -1;
		try {
			con = getCon();
			sql = "UPDATE cart SET options_id = (SELECT options_id FROM options WHERE item_id = ? AND options_id = ?),cart_quantity = ? WHERE cart_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, item_id);
			pstmt.setString(2, option_id);
			pstmt.setString(3, cart_quantity);
			pstmt.setString(4, cart_id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return result;
	}
	//장바구니 삭제 메서드
	public int deleteCart(String cart_id,String member_id) {
		int result = -1;
		try {
			con = getCon();
			sql = "DELETE FROM cart WHERE cart_id IN("+ cart_id +") AND member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return result;
	}
}

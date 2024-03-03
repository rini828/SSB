package com.ssb.ajax.db;

import java.util.ArrayList;

import com.ssb.cart.db.optionsDTO;
import com.ssb.util.DAO;

public class ajaxDAO extends DAO {
	
	// 옵션 선택 메서드
	public ArrayList<optionsDTO> getOptions(String item_id) {
		ArrayList<optionsDTO> dtoArray = new ArrayList<optionsDTO>();
		optionsDTO dto = null;
		try {
			con = getCon();
			sql = "SELECT options_id FROM options WHERE item_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, item_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
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
					dtoArray.add(dto);
				}
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return dtoArray;
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

	// 장바구니 삭제 메서드
	public int deleteCart(String cart_id, String member_id) {
		int result = -1;
		try {
			con = getCon();
			sql = "DELETE FROM cart WHERE cart_id IN(" + cart_id + ") AND member_id = ?";
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

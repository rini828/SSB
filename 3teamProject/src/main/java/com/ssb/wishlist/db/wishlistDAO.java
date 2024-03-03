package com.ssb.wishlist.db;

import java.util.ArrayList;

import com.ssb.util.DAO;

public class wishlistDAO extends DAO {

	// 위시리스트 조회 메서드
	public ArrayList<wishlistDTO> getWishlist(String member_id) {
		ArrayList<wishlistDTO> dtoArray = new ArrayList<wishlistDTO>();
		wishlistDTO dto = null;
		try {
			con = getCon();
			sql = "SELECT wishlist_id, W.item_id, item_name, item_img_main, item_price FROM wishlist W JOIN item I ON W.item_id = I.item_id WHERE member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(member_id));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new wishlistDTO();
				dto.setWishlist_id(rs.getInt("wishlist_id"));
				dto.setItem_id(rs.getInt("item_id"));
				dto.setItem_name(rs.getString("item_name"));
				dto.setItem_img_main(rs.getString("item_img_main"));
				dto.setItem_price(rs.getInt("item_price"));
				dtoArray.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return dtoArray;
	}

	public String switchWishlist(String item_id, String member_id) {
		String result = null;
		try {
			con = getCon();
			sql = "SELECT * FROM wishlist WHERE item_id = ? AND member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, item_id);
			pstmt.setString(2, member_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sql = "DELETE FROM wishlist WHERE wishlist_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt("wishlist_id"));
				if (pstmt.executeUpdate() == 1) {
					result = "deleted";
				} else {
					result = "deletedFail";
				}
			} else {
				sql = "INSERT INTO wishlist VALUES(DEFAULT,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, item_id);
				pstmt.setString(2, member_id);
				if (pstmt.executeUpdate() == 1) {
					result = "inserted";
				} else {
					result = "insertedFail";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return result;
	}

	public ArrayList<Integer> getWishlist(int member_id) {
		ArrayList<Integer> item_idArray = new ArrayList<Integer>();
		try {
			con = getCon();
			sql = "SELECT item_id FROM wishlist WHERE member_id = ? ORDER BY wishlist_id";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				item_idArray.add(rs.getInt("item_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return item_idArray;
	}

	public int deleteWishlist(String item_idArr, int member_id) {
		int result = -1;
		try {
			con = getCon();
			sql = "DELETE FROM wishlist WHERE item_id IN(" + item_idArr + ") AND member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member_id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return result;
	}

}

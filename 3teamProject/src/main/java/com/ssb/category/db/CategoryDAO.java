package com.ssb.category.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ssb.adItem.db.ItemDTO;

public class CategoryDAO {

	
	

	// Connection Pool
	// (0) 공통 변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	// (1) 디비 연결하기(CP)
	private Connection getCon() throws Exception {

		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/ssb");
		con = ds.getConnection();

		return con;
	}

	// (2) 디비 자원해제
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

	// ================================================================================
	
	
	
	
	// (1) 카테고리 목록을 불러오는 메서드 - getCategoryList() 시작
	public ArrayList<ItemDTO> getCategoryList() {
	    
		ArrayList<ItemDTO> CategoryList = new ArrayList<>();

	    try {
	        con = getCon();
	    
	        sql = "SELECT * FROM category";
	        
	        pstmt = con.prepareStatement(sql);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            ItemDTO dto = new ItemDTO();
	            dto.setCategory_id(rs.getInt("category_id"));
	            dto.setCategory_code(rs.getInt("category_code"));
	            dto.setCategory_sport(rs.getString("category_sport"));
	            dto.setCategory_major(rs.getString("category_major"));
	            dto.setCategory_sub(rs.getString("category_sub"));
	            dto.setCategory_brand(rs.getString("category_brand"));
	            CategoryList.add(dto);
	        } // while
	        
	        
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    } finally {
	        CloseDB();
	    }

	    return CategoryList;
	}

	// (1) 카테고리 목록을 불러오는 메서드 - getCategoryList() 끝
	
	
	
	
	
	// (2) 기존의 카테고리ID를 찾아오는 메서드 - findCategoryId() 시작
 	public int findCategoryId(String category_sport, String category_major, String category_sub, String category_brand, int category_code) {
 	    int categoryId = -1; // 기본값 설정 (오류 발생 시 -1을 리턴)

 	    try {
 	        con = getCon();
 	        // 새로 등록되는 상품에 (모든 카테고리 조건을 만족하는) 기존의 카테고리ID를 부여함
 	        sql = "SELECT category_id FROM category WHERE category_sport LIKE ? AND category_major LIKE ? AND category_sub LIKE ? AND category_brand LIKE ? AND category_code = ?";
 	        pstmt = con.prepareStatement(sql);

 	        pstmt.setString(1, "%" + category_sport + "%");
 	        pstmt.setString(2, category_major);
 	        pstmt.setString(3, category_sub);
 	        pstmt.setString(4, category_brand);
 	        pstmt.setInt(5, category_code);

 	        rs = pstmt.executeQuery();

 	        if (rs.next()) {
 	            categoryId = rs.getInt("category_id");
 	        }


 	    } catch (Exception e) {
 	        e.printStackTrace();
 	    } finally {
 	        CloseDB();
 	    }

 	    return categoryId;
 	}
     // (2) 기존의 카테고리ID를 찾아오는 메서드 - findCategoryId() 끝

	
	
	
 	 // (3) 기존의 카테고리ID를 찾다가 만약 없으면 새로운 카테고리를 생성하여 그 ID를 반환하는 메서드 - findOrCreateCategory() 시작
 	public int findOrCreateCategory(String category_sport, String category_major, String category_sub, String category_brand, int category_code) {
			
 		int categoryId = findCategoryId(category_sport, category_major, category_sub, category_brand, category_code);

			if (categoryId == -1) {				
				categoryId = createNewCategory(category_sport, category_major, category_sub, category_brand, category_code);
			}

			return categoryId;
		}
 	// (3) 기존의 카테고리ID를 찾다가 만약 없으면 새로운 카테고리를 생성하여 그 ID를 반환하는 메서드 - findOrCreateCategory() 끝

 	
 	
 	
 	
 	// (4) 이어서 새로운 카테고리를 생성하는 메서드 - createNewCategory() 시작
		public int createNewCategory(String category_sport, String category_major, String category_sub, String category_brand, int category_code) {
			int newCategoryId = -1; // 초기값

			try {
				con = getCon();

				sql = "INSERT INTO category (category_code, category_sport, category_major, category_sub, category_brand) VALUES (?,?,?,?,?);";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, category_code);
				pstmt.setString(2, category_sport);
				pstmt.setString(3, category_major);
				pstmt.setString(4, category_sub);
				pstmt.setString(5, category_brand);
				pstmt.executeUpdate();

				// 새로 추가된 카테고리의 ID를 가져옴
				sql = "SELECT LAST_INSERT_ID() AS newCategoryId"; // MySQL 기준

				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					newCategoryId = rs.getInt("newCategoryId");
				}


			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}

			return newCategoryId;
		}
		// (4) 이어서 새로운 카테고리를 생성하는 메서드 - createNewCategory() 끝

	
	
	
	
	
	
}

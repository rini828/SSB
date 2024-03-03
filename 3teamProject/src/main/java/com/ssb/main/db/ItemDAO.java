package com.ssb.main.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ssb.rental.db.RentalDTO;

public class ItemDAO {
	
	// 공통변수 선언
	
		private Connection con = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		private String sql = "";
		
		// 디비연결수행
		private Connection getCon() throws Exception{
		
		
		Context initCTX = new InitialContext(); // 프로젝트의 정보를 확인(JNDI)
		DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/ssb");
		con = ds.getConnection();
		
		
		
		return con;
		
		}
		
		// 디비 자원해제
		public void CloseDB() {
			try {
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
					
		 	} catch (SQLException e) {
		 		e.printStackTrace();
			}
		}
	
	
	
	// 메인화면 출력 메서드 - mainPrint()
	public ArrayList getMainItemList() {
		ArrayList itemlList = new ArrayList();
		
		try {
			con = getCon();
			sql = "select * from item order by item_id";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ItemDTO idto = new ItemDTO();
				idto.setItem_id(rs.getInt("item_id"));
				idto.setItem_name(rs.getString("item_name"));
				idto.setItem_price(rs.getInt("item_price"));
				idto.setItem_img_main(rs.getString("item_img_main"));
				idto.setItem_img_sub(rs.getString("item_img_sub"));
				idto.setItem_img_logo(rs.getString("item_img_logo"));
				idto.setCategory_id(rs.getInt("category_id"));
				
				itemlList.add(idto);
				
			}
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
		return itemlList;
		
		
			
	}
	
// 검색어 유무에 따른 글 정보 목록을 가져오는 메서드 -getItemList(String search)
	public ArrayList getItemList(String search) {
		ArrayList itemList = new ArrayList();
		
		try {
			con = getCon();
			sql = "select * from item i join category c on i.category_id = c.category_id where item_name like ? or category_brand like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setString(2, "%"+search+"%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ItemDTO idto = new ItemDTO();
				idto.setItem_id(rs.getInt("item_id"));
				idto.setItem_name(rs.getString("item_name"));
				idto.setItem_price(rs.getInt("item_price"));
				idto.setItem_img_main(rs.getString("item_img_main"));
				idto.setItem_img_sub(rs.getString("item_img_sub"));
				idto.setItem_img_logo(rs.getString("item_img_logo"));
				idto.setCategory_id(rs.getInt("category_id"));
				
				itemList.add(idto);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
		return itemList;
	}
	
	
	
	
// 검색어 유무에 따른 글 정보 목록을 가져오는 메서드 -getItemList(String search)

// 스포츠 카테고리별 제품 목록을 보여주는 메서드 - getCategoryItem(category)
	public ArrayList getCategoryItem(String category) {
		ArrayList categoryList = new ArrayList();


		try {
			con = getCon();
			sql="select * from item i join category c on i.category_id = c.category_id where category_sport=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
		
			rs = pstmt.executeQuery();

			while(rs.next()) {
				ItemDTO idto = new ItemDTO();
				idto.setItem_id(rs.getInt("item_id"));
				idto.setItem_name(rs.getString("item_name"));
				idto.setItem_price(rs.getInt("item_price"));
				idto.setItem_img_main(rs.getString("item_img_main"));
				idto.setItem_img_sub(rs.getString("item_img_sub"));
				idto.setItem_img_logo(rs.getString("item_img_logo"));
				idto.setCategory_id(rs.getInt("category_id"));

				categoryList.add(idto);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}


		return categoryList;
	}
// 스포츠 카테고리별 제품 목록을 보여주는 메서드 - getCategoryItem(category)	

// 스포츠 중분류 카테고리별 제품 목록을 보여주는 메서드 - getMiddleCategoryItem(category)	
	public ArrayList getMiddleCategoryItem(String category, String midCategory) {
		ArrayList categoryList = new ArrayList();


		try {
			con = getCon();
			sql="select * from item i join category c on i.category_id = c.category_id where category_sport=? and category_major=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, midCategory);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				ItemDTO idto = new ItemDTO();
				idto.setItem_id(rs.getInt("item_id"));
				idto.setItem_name(rs.getString("item_name"));
				idto.setItem_price(rs.getInt("item_price"));
				idto.setItem_img_main(rs.getString("item_img_main"));
				idto.setItem_img_sub(rs.getString("item_img_sub"));
				idto.setItem_img_logo(rs.getString("item_img_logo"));
				idto.setCategory_id(rs.getInt("category_id"));

				categoryList.add(idto);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}


		return categoryList;
	}
	
	

	
	
// 스포츠 중분류 카테고리별 제품 목록을 보여주는 메서드 - getMiddleCategoryItem(category)	
	
// 소분류(옷 분류)카테고리별 제품 목록을 보여주는 메서드 - getCategoryItem(category_sub)
		public ArrayList getCategorySubItem(String category_sub) {
			ArrayList categorySubList = new ArrayList();


			try {
				con = getCon();
				sql="select * from item i join category c on i.category_id = c.category_id where category_sub=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, category_sub);
				rs = pstmt.executeQuery();

				while(rs.next()) {
					ItemDTO idto = new ItemDTO();
					idto.setItem_id(rs.getInt("item_id"));
					idto.setItem_name(rs.getString("item_name"));
					idto.setItem_price(rs.getInt("item_price"));
					idto.setItem_img_main(rs.getString("item_img_main"));
					idto.setItem_img_sub(rs.getString("item_img_sub"));
					idto.setItem_img_logo(rs.getString("item_img_logo"));
					idto.setCategory_id(rs.getInt("category_id"));

					categorySubList.add(idto);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CloseDB();
			}


			return categorySubList;
		}
//소분류(옷 분류)카테고리별 제품 목록을 보여주는 메서드 - getCategoryItem(category_sub)	
	
// 중분류(신발/옷/용품)카테고리별 제품 목록을 보여주는 메서드 - getCategoryMajorItem(category_major)
		public ArrayList getCategoryMajorItem(String category_major) {
			ArrayList categorySubList = new ArrayList();


			try {
				con = getCon();
				sql="select * from item i join category c on i.category_id = c.category_id where category_major=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, category_major);
				rs = pstmt.executeQuery();

				while(rs.next()) {
					ItemDTO idto = new ItemDTO();
					idto.setItem_id(rs.getInt("item_id"));
					idto.setItem_name(rs.getString("item_name"));
					idto.setItem_price(rs.getInt("item_price"));
					idto.setItem_img_main(rs.getString("item_img_main"));
					idto.setItem_img_sub(rs.getString("item_img_sub"));
					idto.setItem_img_logo(rs.getString("item_img_logo"));
					idto.setCategory_id(rs.getInt("category_id"));

					categorySubList.add(idto);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CloseDB();
			}


			return categorySubList;
		}
// 중분류(신발/옷/용품)카테고리별 제품 목록을 보여주는 메서드 - getCategoryItem(category_major)	
				
}

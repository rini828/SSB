package com.ssb.Mitem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ItemDAO {
	
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
				
	//================================================================================

	
	
	// (1) 상품 관리 페이지를 가져오는 메서드 - getItemMgt(int startRow,int pageSize) 시작
	public ArrayList getItemMgt(int startRow, int pageSize) {
		
		// 상품 정보를 저장하는 배열
		ArrayList ItemMgt = new ArrayList();
		
		try {
			// 디비연결정보
			// 1. 드라이버 로드
			// 2. 디비 연결
			con = getCon();
			// 3. SQL 작성(select) & pstmt 객체
			sql = " select c.category_code,i,item_price, i.item_id, i.item_img_main, o.options_price, c.category_id, o.options_id, "
					+ "o.options_name, o.options_value, o.options_quantity, c.category_sport, c.category_major, "
					+ "c.category_sub, c.category_brand, i.item_name from options o join item i on i.item_id=o.item_id "
					+ "join category c on i.category_id=c.category_id order by item_id desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			// ???? 처리
			pstmt.setInt(1, startRow - 1); // 시작행번호-1
			pstmt.setInt(2, pageSize); // 개수
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			// 상품 정보 전부 가져오기
			// 객체 여러개 => ArrayList 저장
			while (rs.next()) {
				// rs -> dto -> list
				ItemDTO dto = new ItemDTO();
				
				dto.setItem_id(rs.getInt("item_id"));
				dto.setItem_name(rs.getString("item_name"));
				dto.setItem_img_main(rs.getString("item_img_main"));
				dto.setCategory_id(rs.getInt("category_id"));
				dto.setCategory_code(rs.getInt("category_code"));
				dto.setCategory_sport(rs.getString("category_sport"));
				dto.setCategory_major(rs.getString("category_major"));
				dto.setCategory_sub(rs.getString("category_sub"));
				dto.setCategory_brand(rs.getString("category_brand"));
				dto.setOptions_id(rs.getInt("options_id"));
				dto.setOptions_price(rs.getInt("options_price"));
				dto.setOptions_name(rs.getString("options_name"));
				dto.setOptions_value(rs.getString("options_value"));
				dto.setOptions_quantity(rs.getInt("options_quantity"));
				// rs -> dto 저장완료
	            ItemMgt.add(dto);
	            // dto -> list 저장완료
		
			} // while

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}

		return ItemMgt;
	}
	// (1) 상품 관리 페이지를 가져오는 메서드 - getItemMgt(int startRow,int pageSize) 끝
	

	
	

    // (2) 상품 관리 페이지를 가져오는 메서드 (검색 기능) - getItemMgt (int startRow, int pageSize, String search) 시작
    public ArrayList getItemMgt(int startRow, int pageSize, String search) {
        ArrayList ItemMgt = new ArrayList();
		try {
			con = getCon();
			sql = " select c.category_code, i.item_id, i.item_img_main, o.options_price, c.category_id, o.options_id, "
					+ "o.options_name, o.options_value, o.options_quantity, c.category_sport, c.category_major, "
					+ "c.category_sub, c.category_brand, i.item_name from options o join item i on i.item_id=o.item_id "
					+ "join category c on i.category_id=c.category_id where i.item_name like ? order by item_id desc limit ?,?";
            pstmt = con.prepareStatement(sql);
            
			pstmt.setString(1, "%"+search+"%"); // %상품명 검색어%
			pstmt.setInt(2, startRow - 1); // 시작행번호-1
			pstmt.setInt(3, pageSize); // 개수
            rs = pstmt.executeQuery();
           
            while (rs.next()) {
                ItemDTO dto = new ItemDTO();
                
				dto.setItem_id(rs.getInt("item_id"));
				dto.setItem_name(rs.getString("item_name"));
				dto.setItem_img_main(rs.getString("item_img_main"));
				dto.setCategory_id(rs.getInt("category_id"));
				dto.setCategory_code(rs.getInt("category_code"));
				dto.setCategory_sport(rs.getString("category_sport"));
				dto.setCategory_major(rs.getString("category_major"));
				dto.setCategory_sub(rs.getString("category_sub"));
				dto.setCategory_brand(rs.getString("category_brand"));
				dto.setOptions_id(rs.getInt("options_id"));
				dto.setOptions_price(rs.getInt("options_price"));
				dto.setOptions_name(rs.getString("options_name"));
				dto.setOptions_value(rs.getString("options_value"));
				dto.setOptions_quantity(rs.getInt("options_quantity"));
                ItemMgt.add(dto);
				
            } // while
            
			
		} catch (Exception e) {
			e.printStackTrace();
        } finally {
            CloseDB();
        }
        
        return ItemMgt;
    }
    // (2) 상품 관리 페이지를 가져오는 메서드 (검색 기능) - getItemMgt (int startRow, int pageSize, String search) 끝
	
	
	
	

	// (3) 상품 개수 계산 메서드 - getItemCount() 시작
	public int getItemCount() {
		int result = 0;

		try {
			// 1. 드라이버 로드
			// 2. 디비 연결
			con = getCon();

			// 3. sql 작성(select) & pstmt 객체
			sql = "select count(*) from options";
			pstmt = con.prepareStatement(sql);

			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 - 개수를 저장
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}

		return result;
	}
	// (3) 상품 개수 계산 메서드 - getItemCount() 끝

	
	
	
	// (4) 상품 개수 계산 메서드 - getItemCount(String search) 시작
	public int getItemCount(String search) {
		int result = 0;
		
		try {
			// 1. 드라이버 로드
			// 2. 디비 연결
			con = getCon();
			
			// 3. sql 작성(select) & pstmt 객체
			sql = " select count(*) from options o join item i on i.item_id=o.item_id where item_name like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%"); // %상품명 검색어%	
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 - 개수를 저장
			if (rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return result;
	}
	// (4) 상품 개수 계산 메서드 - getItemCount(String search) 끝
    
    
    
	
    
	// (5) 특정 상품 정보를 가져오는 메서드 - getItem(ino) 시작
	
	//수정 
	public ItemDTO getItemV2(int ino) {
		ItemDTO dto = new ItemDTO();
		
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 구문 작성(select) & pstmt 객체 
			sql = "select * from options o join item i on i.item_id = o.item_id "
					+ "join category c on c.category_id=i.category_id where i.item_id = ?; ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ino);
			
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 (rs -> dto)
			if(rs.next()) {
				dto = new ItemDTO();
				/* 아이템 + 카테고리 + 옵션 */
				dto.setItem_id(rs.getInt("item_id"));
				dto.setItem_name(rs.getString("item_name"));
				dto.setItem_img_main(rs.getString("item_img_main"));
				dto.setItem_img_main(rs.getString("item_img_sub"));
				dto.setItem_img_main(rs.getString("item_img_logo"));
				dto.setCategory_id(rs.getInt("category_id"));
				dto.setCategory_code(rs.getInt("category_code"));
				dto.setCategory_sport(rs.getString("category_sport"));
				dto.setCategory_major(rs.getString("category_major"));
				dto.setCategory_sub(rs.getString("category_sub"));
				dto.setCategory_brand(rs.getString("category_brand"));
				dto.setOptions_id(rs.getInt("options_id"));
				dto.setOptions_price(rs.getInt("options_price"));
				dto.setOptions_name(rs.getString("options_name"));
				dto.setOptions_value(rs.getString("options_value"));
				dto.setOptions_quantity(rs.getInt("options_quantity"));
				
				
				//////////////////// 신규 추가  ////////////////////
				dto.setItem_price(rs.getInt("item_price"));
				/////////////////////////////////////
				
				
				
			}// if
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return dto;
	}
	// (5) 특정 상품 정보를 가져오는 메서드 - getItem(ino) 시작
	
	
	
	
	
	// (6) 특정 상품 정보[재고 수량]을 수정하는 메서드 - quantityItem 시작
	public int quantityItem(int optionsId, int newQuantity) {
	    int result = 0;

	    try {
	        con = getCon();

	        sql = "update options SET options_quantity = ? WHERE options_id = ?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, newQuantity);
	        pstmt.setInt(2, optionsId);

	        result = pstmt.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        CloseDB();
	    }

	    return result;
	}
	// (6) 특정 상품 정보[재고 수량]을 수정하는 메서드 - quantityItem 끝

	
	
	
	
	
	//======================================================
	
	// (7) 상품 등록 메서드 - addItem(ItemDTO) 쓰는중
	public void addItem(ItemDTO dto) {
		int ino = 0;

		try {
			// 1.2. 디비연결
			con = getCon();

			// * ino 계산하기 => 1부터 1씩 증가
			// 3. sql구문(select) & pstmt 객체
			sql = "select max(o.options_id) from options o join item i on i.item_id = o.item_id "
					+ "join category c on c.category_id=i.category_id; ";
			pstmt = con.prepareStatement(sql);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if (rs.next()) {
			 // ino = rs.getInt("max(ino)")+1;
				ino = rs.getInt(1) + 1;
			}


			// 3. sql 구문(insert) & pstmt 객체
			sql = "insert into itwill_board(bno,name,pass,subject,content,readcount,"
					+ "re_ref,re_lev,re_seq,date,ip,file) values(?,?,?,?,?,?,?,?,?,now(),?,?)";
			pstmt = con.prepareStatement(sql);


			// 4. sql 실행
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}

	}
	// (7) 상품 등록 메서드 - addItem(ItemDTO) 끝

	
	
	
	
} // ItemDAO


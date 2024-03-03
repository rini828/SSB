package com.ssb.adItem.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ssb.board.db.BoardDTO;
import com.ssb.location.db.locationDTO;
import com.ssb.option.db.optionsDTO;

/**
 * ItemDAO : DB관련 처리를 수행하는 객체 (Data Access Object)
 */

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

	// ================================================================================

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
			sql = " select c.category_code, i.item_id, i.item_img_main, i.item_price, c.category_id, o.options_id, "
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
				dto.setItem_price(rs.getInt("Item_price")); // 기존 가격(옵션테이블) -> (아이템테이블)
				dto.setItem_img_main(rs.getString("item_img_main"));
				dto.setCategory_id(rs.getInt("category_id"));
				dto.setCategory_code(rs.getInt("category_code"));
				dto.setCategory_sport(rs.getString("category_sport"));
				dto.setCategory_major(rs.getString("category_major"));
				dto.setCategory_sub(rs.getString("category_sub"));
				dto.setCategory_brand(rs.getString("category_brand"));
				dto.setOptions_id(rs.getInt("options_id"));
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
			sql = " select c.category_code, i.item_id, i.item_img_main, i.item_price, c.category_id, o.options_id, "
					+ "o.options_name, o.options_value, o.options_quantity, c.category_sport, c.category_major, "
					+ "c.category_sub, c.category_brand, i.item_name from options o join item i on i.item_id=o.item_id "
					+ "join category c on i.category_id=c.category_id where i.item_name like ? order by item_id desc limit ?,?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, "%" + search + "%"); // %상품명 검색어%
			pstmt.setInt(2, startRow - 1); // 시작행번호-1
			pstmt.setInt(3, pageSize); // 개수
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ItemDTO dto = new ItemDTO();

				dto.setItem_id(rs.getInt("item_id"));
				dto.setItem_name(rs.getString("item_name"));
				dto.setItem_price(rs.getInt("item_price")); // 기존 가격(옵션테이블) -> (아이템테이블)
				dto.setItem_img_main(rs.getString("item_img_main"));
				dto.setCategory_id(rs.getInt("category_id"));
				dto.setCategory_code(rs.getInt("category_code"));
				dto.setCategory_sport(rs.getString("category_sport"));
				dto.setCategory_major(rs.getString("category_major"));
				dto.setCategory_sub(rs.getString("category_sub"));
				dto.setCategory_brand(rs.getString("category_brand"));
				dto.setOptions_id(rs.getInt("options_id"));
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
			pstmt.setString(1, "%" + search + "%"); // %상품명 검색어%
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
	public ItemDTO getItem(int item_id) {
	    ItemDTO dto = null;

	    try {
	        // 1.2. 디비연결
	        con = getCon();
	        // 3. sql 구문 작성(select) & pstmt 객체
	        sql = "SELECT * FROM item i LEFT JOIN options o ON i.item_id = o.item_id JOIN category c ON c.category_id = i.category_id where i.item_id= ?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, item_id);
	        // 4. sql 실행
	        rs = pstmt.executeQuery();
	        // 5. 데이터 처리 (rs -> dto)
	        if (rs.next()) {
	            dto = new ItemDTO();
	            dto.setItem_id(rs.getInt("item_id"));
	            dto.setItem_name(rs.getString("item_name"));
	            dto.setItem_price(rs.getInt("item_price"));
	            dto.setItem_img_main(rs.getString("item_img_main"));
	            dto.setItem_img_sub(rs.getString("item_img_sub"));
	            dto.setItem_img_logo(rs.getString("item_img_logo"));
	            dto.setOptions_id(rs.getInt("options_id"));
	            dto.setOptions_name(rs.getString("options_name"));
	            dto.setOptions_value(rs.getString("options_value"));
	            dto.setOptions_quantity(rs.getInt("options_quantity"));
	            dto.setOptions_price(rs.getInt("options_price"));
	            dto.setCategory_id(rs.getInt("category_id"));
	            dto.setCategory_code(rs.getInt("category_code"));
	            dto.setCategory_sport(rs.getString("category_sport"));
	            dto.setCategory_major(rs.getString("category_major"));
	            dto.setCategory_sub(rs.getString("category_sub"));
	            dto.setCategory_brand(rs.getString("category_brand"));

	        } 
	        

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        CloseDB();
	    }

	    return dto;
	}
	// (5) 특정 상품 정보를 가져오는 메서드 - getItem(ino) 종료

	
	
	
	
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

	
	
	
	
	// (7) 아이템(상품) 정보를 등록하는 메서드 - addItem 시작
	public int addItem(ItemDTO dto) {
	    int itemId = -1;

	    try {
	        // 1. 디비 연결
	        con = getCon();

	        // 2. SQL 실행 
	        sql = "INSERT INTO item (item_name, item_price, item_img_main, item_img_sub, item_img_logo, category_id) VALUES (?,?,?,?,?,?);";
	        pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	        pstmt.setString(1, dto.getItem_name());
	        pstmt.setInt(2, dto.getItem_price());
	        pstmt.setString(3, dto.getItem_img_main());
	        pstmt.setString(4, dto.getItem_img_sub());
	        pstmt.setString(5, dto.getItem_img_logo());
	        pstmt.setInt(6, dto.getCategory_id());
	        pstmt.executeUpdate();

	        // 3. 생성된 키 가져오기
	        ResultSet generatedKeys = pstmt.getGeneratedKeys();

	        // 4. 생성된 키 확인
	        if (generatedKeys.next()) {
	            itemId = generatedKeys.getInt(1);
	        }


	        if (itemId != -1) {
	            addOption(dto, itemId);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        CloseDB();
	    }

	    return itemId;
	}
	// (7) 아이템(상품) 정보를 등록하는 메서드 - addItem 끝

	
	
	
	
	// (8) (아이템+)옵션 정보를 등록하는 메서드 - addOptions 시작
	public void addOption(ItemDTO dto, int itemId) {
	    try {
	        // 1. 디비 연결
	        con = getCon();

	        // 2. SQL 실행 
	        sql = "INSERT INTO options (options_name, options_value, options_price, options_quantity, item_id) VALUES (?,?,?,?,?);";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, dto.getOptions_name());
	        pstmt.setString(2, dto.getOptions_value());
	        pstmt.setInt(3, dto.getOptions_price());
	        pstmt.setInt(4, dto.getOptions_quantity());
	        pstmt.setInt(5, itemId);
	        pstmt.executeUpdate();


	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        CloseDB();
	    }
	}
	// (8) 옵션 정보를 등록하는 메서드 - addOptions 끝
	
	
	
	
	
	// (9) 옵션과 상품 정보를 삭제하는 메서드 - deleteItem 시작
    public void deleteItem(int options_id) {
    		// 상품 1개에 옵션 n개 존재함
    	    // 리스트에서 삭제 버튼 누르면 -> 옵션테이블 기준으로 옵션만 하나씩 삭제된다 (한번에 한개만 삭제 가능) 
    	    // 선택한게 해당 상품의 마지막 옵션일때, 옵션과 상품테이블 행을 동시에 삭제함 (상품만 옵션없이 덜렁 남지 않도록)
        try {
        	 con = getCon();
        	 
            // 1) 삭제하려는 옵션의 상품ID 조회
            String getItemId = "SELECT item_id FROM options WHERE options_id = ?";
            pstmt = con.prepareStatement(getItemId);
            pstmt.setInt(1, options_id);
            rs = pstmt.executeQuery();

            int item_id = 0;
            
            if (rs.next()) {
                item_id = rs.getInt("item_id");
            }

            // 2) 삭제하려는 상품의 옵션 개수 조회
            String getCountOptions = "SELECT COUNT(*) FROM options WHERE item_id = ?";
            pstmt = con.prepareStatement(getCountOptions);
            pstmt.setInt(1, item_id);
            rs = pstmt.executeQuery();

            int optionsCount = 0;
            if (rs.next()) {
                optionsCount = rs.getInt(1);
            }

            // 3) 해당 옵션이 마지막인 경우, 상품 테이블 정보까지 동시에 삭제함 
            if (optionsCount == 1) {
                
            	// 외래 키 제약 조건 위반을 막기 위해 옵션을 먼저 삭제한다
                String deleteOptions = "DELETE FROM options WHERE item_id = ?";
                pstmt = con.prepareStatement(deleteOptions);
                pstmt.setInt(1, item_id);
                pstmt.executeUpdate();

                // 그담에 상품 테이블에서도 삭제함
                String deleteItem = "DELETE FROM item WHERE item_id = ?";
                pstmt = con.prepareStatement(deleteItem);
                pstmt.setInt(1, item_id);
                pstmt.executeUpdate();
            } 
            
        } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        CloseDB();
	    }
        
    }
    // (9) 옵션과 상품 정보를 삭제하는 메서드 - deleteItem 끝

	
	
	
    // (10) 특정 상품의 옵션 목록 메서드 - getOptList() 시작
 	public ArrayList getOptList(int item_id) {

 		ArrayList getOptList = new ArrayList();

 		try {
 			con = getCon();

 			sql = " select * from options where item_id=? ";
 			
 			pstmt = con.prepareStatement(sql);
 			pstmt.setInt(1, item_id);
 			rs = pstmt.executeQuery();

 			while (rs.next()) {

 				ItemDTO dto = new ItemDTO();

 				dto.setOptions_id(rs.getInt("options_id"));
 				dto.setOptions_name(rs.getString("options_name"));
 				dto.setOptions_value(rs.getString("options_value"));
 				dto.setOptions_quantity(rs.getInt("options_quantity"));
 				dto.setOptions_price(rs.getInt("options_price"));
 				dto.setItem_id(rs.getInt("item_id"));
 				getOptList.add(dto);

 			} 


 		} catch (Exception e) {
 			e.printStackTrace();
 		} finally {
 			CloseDB();
 		}

 		return getOptList;
 	}
 	// (10) 특정 상품의 옵션 목록 메서드 - getOptList() 끝

 	
 	
 	// (11)옵션 정보 가져오기
	public ItemDTO getOptions(int optionsId) {
		ItemDTO dto = null; 
			
		try {	
			con = getCon();
			sql = "select * from options where options_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, optionsId);		
			rs = pstmt.executeQuery();
				
			if(rs.next()) { 				
				dto = new ItemDTO();
				dto.setOptions_id(rs.getInt("options_id"));
				dto.setOptions_name(rs.getString("options_name"));
				dto.setOptions_value(rs.getString("options_value"));
				dto.setOptions_price(rs.getInt("options_price"));							
				dto.setOptions_quantity(rs.getInt("options_quantity"));
				dto.setItem_id(rs.getInt("item_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return dto;		
	}	

	
	
	
	
	// 옵션 수정하기
 	public void editOptions(ItemDTO dto) {
 	    try {
 	        con = getCon();

 	        sql = "UPDATE options SET options_name=?, options_value=?, options_price=?, options_quantity=? WHERE options_id=?";
 	        pstmt = con.prepareStatement(sql);
 	        pstmt.setString(1, dto.getOptions_name());
 	        pstmt.setString(2, dto.getOptions_value());
 	        pstmt.setInt(3, dto.getOptions_price());
 	        pstmt.setInt(4, dto.getOptions_quantity());
 	        pstmt.setInt(5, dto.getOptions_id());
 	        pstmt.executeUpdate();

 	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		CloseDB();
	}
}	


 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
	
} // ItemDAO

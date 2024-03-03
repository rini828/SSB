package com.ssb.rental.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ssb.main.db.ItemDTO;

public class RentalDAO {

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

	// 메인화면에 출력할 렌탈 제품 정보 조회 - getRentalList()
	public ArrayList getRentalList() {
		ArrayList rentalList = new ArrayList();

		try {
			con = getCon();
			sql = "select * from rental_item order by rental_item_id";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				RentalDTO rdto = new RentalDTO();
				rdto.setRental_item_id(rs.getInt("rental_item_id"));
				rdto.setRental_item_name(rs.getString("rental_item_name"));
				rdto.setRental_item_price(rs.getInt("rental_item_price"));
				rdto.setRental_opt_quantity(rs.getInt("rental_opt_quantity"));
				rdto.setRental_opt_name(rs.getString("rental_opt_name"));;
				rdto.setRental_opt_value(rs.getString("rental_opt_value"));
				rdto.setRental_img_main(rs.getString("rental_img_main"));
				rdto.setRental_img_sub(rs.getString("rental_img_sub"));
				rdto.setRental_img_logo(rs.getString("rental_img_logo"));
				rdto.setCategory_id(rs.getInt("category_id"));

				rentalList.add(rdto);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}finally {
			CloseDB();
		}

		return rentalList;

	}
	// 메인화면에 출력할 렌탈 제품 정보 조회 - getRentalList()

	// 검색어 유무에 따른 글 정보 목록을 가져오는 메서드 -getRentalList(String search)
	public ArrayList getRentalList(String search) {
		ArrayList itemList = new ArrayList();

		try {
			con = getCon();
			sql = "select * from rental_item where rental_item_name like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			rs = pstmt.executeQuery();

			while(rs.next()) {
				RentalDTO rdto = new RentalDTO();
				rdto.setRental_item_id(rs.getInt("rental_item_id"));
				rdto.setRental_item_name(rs.getString("rental_item_name"));
				rdto.setRental_item_price(rs.getInt("rental_item_price"));
				rdto.setRental_opt_quantity(rs.getInt("rental_opt_quantity"));
				rdto.setRental_opt_name(rs.getString("rental_opt_name"));;
				rdto.setRental_opt_value(rs.getString("rental_opt_value"));
				rdto.setRental_img_main(rs.getString("rental_img_main"));
				rdto.setRental_img_sub(rs.getString("rental_img_sub"));
				rdto.setRental_img_logo(rs.getString("rental_img_logo"));
				rdto.setCategory_id(rs.getInt("category_id"));

				itemList.add(rdto);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}

		return itemList;
	}




	// 검색어 유무에 따른 글 정보 목록을 가져오는 메서드 -getRentalList(String search)






	// 특정 렌탈 아이템 정보를 조회하는 메서드 - getRentalItem()
	public RentalDTO getRentalItem(int rental_item_id) {
		RentalDTO rdto = null;

		try {
			con =getCon();
			sql = "select * from rental_item where rental_item_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rental_item_id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				rdto = new RentalDTO();
				rdto.setRental_item_id(rental_item_id);
				rdto.setRental_item_name(rs.getString("rental_item_name"));
				rdto.setRental_item_price(rs.getInt("rental_item_price"));
				rdto.setRental_opt_quantity(rs.getInt("rental_opt_quantity"));
				rdto.setRental_opt_name(rs.getString("rental_opt_name"));;
				rdto.setRental_opt_value(rs.getString("rental_opt_value"));
				rdto.setRental_img_main(rs.getString("rental_img_main"));
				rdto.setRental_img_sub(rs.getString("rental_img_sub"));
				rdto.setRental_img_logo(rs.getString("rental_img_logo"));
				rdto.setCategory_id(rs.getInt("category_id"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}


		return rdto;
	}

	// 특정 렌탈 아이템 정보를 조회하는 메서드 - getRentalItem()


	// 카테고리별 제품 목록을 보여주는 메서드 - getCategoryItem(category)
	public ArrayList getCategoryItem(String category) {
		ArrayList categoryList = new ArrayList();


		try {
			con = getCon();
			sql="select * from rental_item ri join category c on ri.category_id = c.category_id where category_sport=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
	
			rs = pstmt.executeQuery();

			while(rs.next()) {
				RentalDTO rdto = new RentalDTO();
				rdto.setRental_item_id(rs.getInt("rental_item_id"));
				rdto.setRental_item_name(rs.getString("rental_item_name"));
				rdto.setRental_item_price(rs.getInt("rental_item_price"));
				rdto.setRental_opt_quantity(rs.getInt("rental_opt_quantity"));
				rdto.setRental_opt_name(rs.getString("rental_opt_name"));;
				rdto.setRental_opt_value(rs.getString("rental_opt_value"));
				rdto.setRental_img_main(rs.getString("rental_img_main"));
				rdto.setRental_img_sub(rs.getString("rental_img_sub"));
				rdto.setRental_img_logo(rs.getString("rental_img_logo"));
				rdto.setCategory_id(rs.getInt("category_id"));

				categoryList.add(rdto);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}


		return categoryList;
	}
	// 카테고리별 제품 목록을 보여주는 메서드 - getCategoryItem(category)
	
	// 스포츠 중분류 카테고리별 제품 목록을 보여주는 메서드 - getMiddleCategoryItem(category, category_major)	
		public ArrayList getMiddleCategoryItem(String category, String category_major) {
			ArrayList categoryList = new ArrayList();


			try {
				con = getCon();
				sql="select * from rental_item i join category c on i.category_id = c.category_id where category_sport=? and category_major=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, category);
				pstmt.setString(2, category_major);
				rs = pstmt.executeQuery();

				while(rs.next()) {
					RentalDTO rdto = new RentalDTO();
					rdto.setRental_item_id(rs.getInt("rental_item_id"));
					rdto.setRental_item_name(rs.getString("rental_item_name"));
					rdto.setRental_item_price(rs.getInt("rental_item_price"));
					rdto.setRental_opt_quantity(rs.getInt("rental_opt_quantity"));
					rdto.setRental_opt_name(rs.getString("rental_opt_name"));;
					rdto.setRental_opt_value(rs.getString("rental_opt_value"));
					rdto.setRental_img_main(rs.getString("rental_img_main"));
					rdto.setRental_img_sub(rs.getString("rental_img_sub"));
					rdto.setRental_img_logo(rs.getString("rental_img_logo"));
					rdto.setCategory_id(rs.getInt("category_id"));

					categoryList.add(rdto);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CloseDB();
			}


			return categoryList;
		}
		// 스포츠 중분류 카테고리별 제품 목록을 보여주는 메서드 - getMiddleCategoryItem(category, category_major)	
		
		// 스포츠 소분류 카테고리별 제품 목록을 보여주는 메서드 - getSubCategoryItem(category, category_sub)
		public ArrayList getSubCategoryItem(String category, String category_sub) {
			ArrayList categoryList = new ArrayList();


			try {
				con = getCon();
				sql="select * from rental_item i join category c on i.category_id = c.category_id where category_sport=? and category_sub=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, category);
				pstmt.setString(2, category_sub);
				rs = pstmt.executeQuery();

				while(rs.next()) {
					RentalDTO rdto = new RentalDTO();
					rdto.setRental_item_id(rs.getInt("rental_item_id"));
					rdto.setRental_item_name(rs.getString("rental_item_name"));
					rdto.setRental_item_price(rs.getInt("rental_item_price"));
					rdto.setRental_opt_quantity(rs.getInt("rental_opt_quantity"));
					rdto.setRental_opt_name(rs.getString("rental_opt_name"));;
					rdto.setRental_opt_value(rs.getString("rental_opt_value"));
					rdto.setRental_img_main(rs.getString("rental_img_main"));
					rdto.setRental_img_sub(rs.getString("rental_img_sub"));
					rdto.setRental_img_logo(rs.getString("rental_img_logo"));
					rdto.setCategory_id(rs.getInt("category_id"));

					categoryList.add(rdto);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CloseDB();
			}


			return categoryList;
		}
		
		// 스포츠 소분류 카테고리별 제품 목록을 보여주는 메서드 - getSubCategoryItem(category, category_sub)		
		
		
		
		
		

	// 렌탈 상품을 등록하는 메서드 - addRental 시작
	public void addRental(RentalDTO rdto) {
		try {
			// 1. 디비 연결
			con = getCon();

			// 2. SQL 실행 
			sql = "Insert Into rental_item (rental_item_name, rental_item_price, "
					+ "rental_opt_quantity, rental_opt_name, rental_opt_value, rental_img_main, rental_img_sub, "
					+ "rental_img_logo, category_id, rental_days ) VALUES (?,?,?,?,?,?,?,?,?,?);";

			// pstmt 초기화
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, rdto.getRental_item_name());
			pstmt.setInt(2, rdto.getRental_item_price());
			pstmt.setInt(3, rdto.getRental_opt_quantity());
			pstmt.setString(4, rdto.getRental_opt_name());
			pstmt.setString(5, rdto.getRental_opt_value());
			pstmt.setString(6, rdto.getRental_img_main());
			pstmt.setString(7, rdto.getRental_img_sub());
			pstmt.setString(8, rdto.getRental_img_logo());
			pstmt.setInt(9, rdto.getCategory_id());
			pstmt.setInt(10, rdto.getRental_days());
			pstmt.executeUpdate();


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
	}
	// 렌탈 상품을 등록하는 메서드 - addRental 끝

	// 특정 렌탈 아이템 정보를 조회하는 메서드 - getRentalItem()
	public RentalDTO getReserveRentalItem(int rItemId, LocalDate strd) {
		RentalDTO rdto = null;

		try {
			con =getCon();
			sql = "select * from rental_item where rental_item_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rItemId);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				rdto = new RentalDTO();
				rdto.setRental_item_id(rItemId);
				rdto.setRental_item_name(rs.getString("rental_item_name"));
				rdto.setRental_item_price(rs.getInt("rental_item_price"));
				rdto.setRental_opt_quantity(rs.getInt("rental_opt_quantity"));
				rdto.setRental_opt_name(rs.getString("rental_opt_name"));;
				rdto.setRental_opt_value(rs.getString("rental_opt_value"));
				rdto.setRental_img_main(rs.getString("rental_img_main"));
				rdto.setRental_img_sub(rs.getString("rental_img_sub"));
				rdto.setRental_img_logo(rs.getString("rental_img_logo"));
				rdto.setCategory_id(rs.getInt("category_id"));
				rdto.setRental_days(rs.getInt("rental_days"));
				rdto.setRental_str(strd);
				if(rs.getInt("rental_days")==2) {						
					rdto.setRental_end(strd.plusDays(2));					
				}else if(rs.getInt("rental_days")==3) {
					rdto.setRental_end(strd.plusDays(3));											
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}


		return rdto;
	}

	// 특정 렌탈 아이템 정보를 조회하는 메서드 - getRentalItem()


	// (1)상품 관리 페이지의 렌탈아이템 목록을 가져오는 메서드 - getItemMgt(int startRow,int pageSize) 시작
	public ArrayList rGetItemMgt(int startRow, int pageSize) {

		// 상품 정보를 저장하는 배열
		ArrayList rItemMgt = new ArrayList();

		try {
			
			con = getCon();
			sql = "select * from rental_item r join category c on r.category_id = c.category_id order by rental_item_id desc limit ?,? ";
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
				RentalDTO rdto = new RentalDTO();

				rdto.setRental_item_id(rs.getInt("rental_item_id"));
				rdto.setRental_item_name(rs.getString("rental_item_name"));
				rdto.setRental_item_price(rs.getInt("rental_item_price")); // 기존 가격(옵션테이블) -> (아이템테이블)
				rdto.setRental_img_main(rs.getString("rental_img_main"));
				rdto.setCategory_id(rs.getInt("category_id"));
				rdto.setCategory_code(rs.getInt("category_code"));
				rdto.setCategory_sport(rs.getString("category_sport"));
				rdto.setCategory_major(rs.getString("category_major"));
				rdto.setCategory_sub(rs.getString("category_sub"));
				rdto.setCategory_brand(rs.getString("category_brand"));
				rdto.setRental_opt_name(rs.getString("rental_opt_name"));
				rdto.setRental_opt_quantity(rs.getInt("rental_opt_quantity"));
				rdto.setRental_opt_value(rs.getString("rental_opt_value"));
				rdto.setRental_days(rs.getInt("rental_days"));
				// rs -> dto 저장완료
				rItemMgt.add(rdto);
				// dto -> list 저장완료

			} // while


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}

		return rItemMgt;
	}
	//  (1)상품 관리 페이지의 렌탈아이템 목록을 가져오는 메서드 - getItemMgt(int startRow,int pageSize) 시작

	
	// (2) 상품 관리 페이지를 가져오는 메서드 (검색 기능) - rGetItemMgt (int startRow, int pageSize, String search) 시작
		public ArrayList rGetItemMgt(int startRow, int pageSize, String search) {
			ArrayList rItemMgt = new ArrayList();
			try {
				con = getCon();
				sql = "select * from rental_item r join category c on  r.category_id = c.category_id where r.rental_item_name like ? order by rental_item_id desc limit ?,?";
				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, "%" + search + "%"); // %상품명 검색어%
				pstmt.setInt(2, startRow - 1); // 시작행번호-1
				pstmt.setInt(3, pageSize); // 개수
				rs = pstmt.executeQuery();

				while (rs.next()) {
					RentalDTO rdto = new RentalDTO();

					rdto.setRental_item_id(rs.getInt("rental_item_id"));
					rdto.setRental_item_name(rs.getString("rental_item_name"));
					rdto.setRental_item_price(rs.getInt("rental_item_price")); // 기존 가격(옵션테이블) -> (아이템테이블)
					rdto.setRental_img_main(rs.getString("rental_img_main"));
					rdto.setCategory_id(rs.getInt("category_id"));
					rdto.setCategory_code(rs.getInt("category_code"));
					rdto.setCategory_sport(rs.getString("category_sport"));
					rdto.setCategory_major(rs.getString("category_major"));
					rdto.setCategory_sub(rs.getString("category_sub"));
					rdto.setCategory_brand(rs.getString("category_brand"));
					rdto.setRental_opt_name(rs.getString("rental_opt_name"));
					rdto.setRental_opt_quantity(rs.getInt("rental_opt_quantity"));
					rdto.setRental_opt_value(rs.getString("rental_opt_value"));
					rdto.setRental_days(rs.getInt("rental_days"));
					// rs -> dto 저장완료
					
					rItemMgt.add(rdto);

				} // while


			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}

			return rItemMgt;
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
				sql = "select count(*) from rental_item";
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
				sql = " select count(*) from rental_item where rental_item_name like ?";
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
		
		
		//------------------------------임시메서드----------------------------------------

		//------------------------------수량 변경-----------------------------------------

		public RentalDTO decreaseQuantity( int itemId) {

			try {

				con = getCon();
				sql = "update rental_item "
						+ "set rental_opt_quantity = rental_opt_quantity - 1 "
						+"where rental_item_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, itemId);
				pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CloseDB();
			}



			return null;
		}

		//------------------------------수량 변경-----------------------------------------

		//------------------------------임시메서드----------------------------------------
}

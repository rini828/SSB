package com.ssb.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ssb.main.db.ItemDTO;
import com.ssb.rental.db.RentalDTO;

public class BoardDAO {

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
	
	
	// [관리자] 공지 작성하기 메서드 - insertNotice(BoardDTO bdto)
	public void insertNotice(BoardDTO bdto) {
		int boardId = 0;
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// board_id 계산
			// 3. SQL 구문(select) & pstmt 객체
			sql = "select max(board_id) from board_remaster";
			pstmt = con.prepareStatement(sql);
						
			// 4. SQL 실행
			rs = pstmt.executeQuery();
						
			// 5. 데이터 처리
			if(rs.next()) {
				// board_id = rs.getInt("max(bno)") + 1;
				// => getInt(int columnIndex)
				boardId = rs.getInt(1) + 1; 
			} else {
				boardId = 1;
			}
							
				
			// 3. SQL 구문(insert) & pstmt 객체
			sql = "insert into board_remaster(board_id, admin_user_id, "
					+ "board_type, board_subject, board_content, "
					+ "board_writeTime, board_readCount) "
					+ "values(?, ?, ?, ?, ?, now(), ?)";
			pstmt = con.prepareStatement(sql);
				
			// ?
			pstmt.setInt(1, boardId);
			pstmt.setString(2, bdto.getAdmin_user_id());
			pstmt.setString(3, "N");
			pstmt.setString(4, bdto.getBoard_subject());
			pstmt.setString(5, bdto.getBoard_content());
			pstmt.setInt(6, 0);
				
			// 4. SQL 실행
			pstmt.executeUpdate();
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
			
	} 
	// [관리자] 공지 작성하기 메서드 - insertNotice(BoardDTO bdto)
	
		
	// 공지글 개수 계산 메서드 - getNoticeCount()
	public int getNoticeCount() {
		int result = 0;	
				
		try {
			// 1.2. 디비 연결
			con = getCon();		
					
			// 3. SQL 구문 작성(select) & pstmt 객체
			sql = "select count(*) from board_remaster where board_type='N'";
			pstmt = con.prepareStatement(sql);			
			
			// 4. SQL 실행
			rs = pstmt.executeQuery();
					
			// 5. 데이터 처리 - 개수를 저장
			if(rs.next()) {
				result = rs.getInt(1);
				// result = rs.getInt("count(*)"); 
			}
					
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
				
		return result;		
	}
	// 공지글 개수 계산 메서드 - getNoticeCount()

	
	// 공지글 조회수 1 증가 메서드 - updateReadCount(int boardId) 
	public void updateReadCount(int boardId) {
				
		try {	
			// 1.2. 디비 연결
			con = getCon();
					
			// 3. SQL 구문 작성(update) & pstmt 객체
			// 특정 글의 조회수를 1 증가
			sql = "update board_remaster set board_readCount=board_readCount+1 where board_id=?";
			pstmt = con.prepareStatement(sql);
					
			// ?
			pstmt.setInt(1, boardId);
					
			// 4. SQL 실행
			pstmt.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
				
	}	
	// 공지글 조회수 1 증가 메서드 - updateReadCount(int boardId)	
	
	
	// 공지사항 목록 가져오는 메서드 - getNoticeList(int startRow, int pageSize)
	public ArrayList getNoticeList(int startRow, int pageSize) {
		// 글정보를 저장하는 배열
		ArrayList noticeList = new ArrayList();
				
		try {
			// 1.2. 디비 연결
			con = getCon();
					
			// 3. SQL 구문(select) 작성 & pstmt 객체
			sql = "select board_id, admin_name, board_subject, board_content, board_writeTime, board_readCount "
					+ "from admin a join board_remaster br "
					+ "on a.admin_user_id = br.admin_user_id where board_type='N' "
					+ "order by board_id desc limit ?,?";
			pstmt = con.prepareStatement(sql);
					
			// ?
			pstmt.setInt(1, startRow-1); // 시작행 번호-1
			pstmt.setInt(2, pageSize); // 페이지 개수
					
			// 4. SQL 실행
			rs = pstmt.executeQuery();
					
			// 5. 데이터 처리
			// 글정보 전부 가져오기
			// 여러 개의 정보 => ArrayList 저장
			while(rs.next()) {
				// 글 하나의 정보 => BoardDTO 저장
				BoardDTO bdto = new BoardDTO();
						
				bdto.setBoard_id(rs.getInt("board_id"));
				bdto.setAdmin_name(rs.getString("admin_name"));
				bdto.setBoard_subject(rs.getString("board_subject"));
				bdto.setBoard_content(rs.getString("board_content"));
				bdto.setBoard_writeTime(rs.getDate("board_writeTime"));
				bdto.setBoard_readCount(rs.getInt("board_readCount"));
						
				// 글 하나의 정보를 배열의 한 칸에 저장
				noticeList.add(bdto);								
			} // while
					
								
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
				
		return noticeList;
	}	
	// 공지사항 목록 가져오는 메서드 - getNoticeList(int startRow, int pageSize)

	
	// 특정 번호에 해당하는 글정보 가져오기 메서드 - getBoard(int boardId)
	public BoardDTO getBoard(int boardId) {
		BoardDTO bdto = null; 
			
		try {	
			// 1.2. 디비 연결
			con = getCon();
				
			// 3. SQL 구문 작성(select) & pstmt 객체
			sql = "select * from board_remaster where board_id=?";
			pstmt = con.prepareStatement(sql);
				
			// ?
			pstmt.setInt(1, boardId);		
				
			// 4. SQL 실행
			rs = pstmt.executeQuery();
				
			// 5. 데이터 처리 (rs -> BoardDTO)
			if(rs.next()) { // 데이터가 존재할 때				
				bdto = new BoardDTO();
					
				bdto.setBoard_id(rs.getInt("board_id"));
				bdto.setMember_user_id(rs.getString("member_user_id"));
				bdto.setInquiry_type(rs.getString("inquiry_type"));
				bdto.setBoard_subject(rs.getString("board_subject"));							
				bdto.setBoard_content(rs.getString("board_content"));
				bdto.setBoard_writeTime(rs.getDate("board_writeTime"));
				bdto.setBoard_readCount(rs.getInt("board_readCount"));
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
			
		return bdto;		
	}	
	// 특정 번호에 해당하는 글정보 가져오기 메서드 - getBoard(int boardId)
		
	
	// [관리자] 특정 번호에 해당하는 공지 수정하는 메서드 - updateNotice(BoardDTO bdto)
	public int updateNotice(BoardDTO bdto) {
		int result = -1; // -1(글정보 없음), 1(정상처리)
			
		try {
			// 1.2. 디비 연결
			con = getCon();
				
			// 3. SQL 구문 작성(select) & pstmt 객체 
			sql = "select * from board_remaster where board_id=?";
			pstmt = con.prepareStatement(sql);
				
			// ?
			pstmt.setInt(1, bdto.getBoard_id());
				
			// 4. SQL 실행
			rs = pstmt.executeQuery();
				
			// 5. 데이터 처리
			if(rs.next()) {
					// 3. SQL 구문 작성(update) & pstmt 객체
					sql = "update board_remaster set board_subject=?, board_content=? "
							+ "where board_id=?";
					pstmt = con.prepareStatement(sql);
						
					// ?
					pstmt.setString(1, bdto.getBoard_subject());
					pstmt.setString(2, bdto.getBoard_content());
					pstmt.setInt(3, bdto.getBoard_id());
						
					// 4. SQL 구문 실행
					result = pstmt.executeUpdate();
					
					// result = 1
			} else { // 게시판에 글 없음
				result = -1;
			}
				
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
			CloseDB();
		}
			
		return result;
	} 
	// [관리자] 특정 번호에 해당하는 공지 수정하는 메서드 - updateNotice(BoardDTO bdto)
	
	
	// [관리자] 특정 번호에 해당하는 공지 삭제하는 메서드 - deleteNotice(BoardDTO bdto)
	public int deleteNotice(BoardDTO bdto) {
		int result = -1; // -1(글정보 없음), 1(정상처리)
			
		try {
			// 1.2. 디비 연결
			con = getCon();
				
			// 3. SQL 구문 작성(select) & pstmt 객체 
			sql = "select * from board_remaster where board_id=?";
			pstmt = con.prepareStatement(sql);
				
			// ?
			pstmt.setInt(1, bdto.getBoard_id());
				
			// 4. SQL 실행
			rs = pstmt.executeQuery();
				
			// 5. 데이터 처리
			if(rs.next()) {
					// 3. SQL 구문 작성(update) & pstmt 객체
					sql = "delete from board_remaster where board_id=?";
					pstmt = con.prepareStatement(sql);
						
					// ?
					pstmt.setInt(1, bdto.getBoard_id());
						
					// 4. SQL 구문 실행
					result = pstmt.executeUpdate();
					
					// result = 1;					
			} else { // 게시판에 글 없음
					result = -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
			
		return result;
	}
	// [관리자] 특정 번호에 해당하는 공지 삭제하는 메서드 - deleteNotice(BoardDTO bdto)


	// 제품 문의글 작성하기 메서드 - insertInquiryItem(BoardDTO bdto)
	public void insertInquiryItem(BoardDTO bdto) {
		int boardId = 0;
				
		try {
			// 1.2. 디비 연결
			con = getCon();
		
			// board_id 계산
			// 3. SQL 구문(select) & pstmt 객체
			sql = "select max(board_id) from board_remaster";
			pstmt = con.prepareStatement(sql);
			
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				// board_id = rs.getInt("max(bno)") + 1;
				// => getInt(int columnIndex):
				boardId = rs.getInt(1) + 1; 
			} else {
				boardId = 1;
			}
			
			
			// 3. SQL 구문(insert) & pstmt 객체
			sql = "insert into board_remaster(board_id, member_user_id, item_id, "
					+ "board_type, inquiry_type, answer_state, "
					+ "board_subject, board_content, board_writeTime) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, now())";
			pstmt = con.prepareStatement(sql);
					
			// ?		
			pstmt.setInt(1, boardId);
			pstmt.setString(2, bdto.getMember_user_id());
			pstmt.setInt(3, bdto.getItem_id());
			pstmt.setString(4, "Q");
			pstmt.setString(5, bdto.getInquiry_type());
			pstmt.setString(6, "답변예정");
			pstmt.setString(7, bdto.getBoard_subject());
			pstmt.setString(8, bdto.getBoard_content());
					
			// 4. SQL 실행
			pstmt.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
				
	} 
	// 제품 문의글 작성하기 메서드 - insertInquiryItem(BoardDTO bdto)
	

	// 렌탈제품 문의글 작성하기 메서드 - insertInquiryRItem(BoardDTO bdto)
	public void insertInquiryRItem(BoardDTO bdto) {
		int boardId = 0;
				
		try {
			// 1.2. 디비 연결
			con = getCon();
		
			// board_id 계산
			// 3. SQL 구문(select) & pstmt 객체
			sql = "select max(board_id) from board_remaster";
			pstmt = con.prepareStatement(sql);
			
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				// board_id = rs.getInt("max(bno)") + 1;
				// => getInt(int columnIndex):
				boardId = rs.getInt(1) + 1; 
			} else {
				boardId = 1;
			}
			
			
			// 3. SQL 구문(insert) & pstmt 객체
			sql = "insert into board_remaster(board_id, member_user_id, rental_item_id, "
					+ "board_type, inquiry_type, answer_state, "
					+ "board_subject, board_content, board_writeTime) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, now())";
			pstmt = con.prepareStatement(sql);
					
			// ?		
			pstmt.setInt(1, boardId);
			pstmt.setString(2, bdto.getMember_user_id());
			pstmt.setInt(3, bdto.getRental_item_id());
			pstmt.setString(4, "Q");
			pstmt.setString(5, bdto.getInquiry_type());
			pstmt.setString(6, "답변예정");
			pstmt.setString(7, bdto.getBoard_subject());
			pstmt.setString(8, bdto.getBoard_content());
					
			// 4. SQL 실행
			pstmt.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
				
	} 
	// 렌탈제품 문의글 작성하기 메서드 - insertInquiryQBoard(BoardDTO bdto)
	
	
	// [관리자] 문의글 개수 계산 메서드 - getInquiryCount()
	public int getInquiryCount() {
		int result = 0;	
		
		try {
			// 1.2. 디비 연결
			con = getCon();		
			
			// 3. SQL 구문 작성(select) & pstmt 객체
			sql = "select count(*) from board_remaster where board_type='Q'";
			pstmt = con.prepareStatement(sql);			
			
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리 - 개수를 저장
			if(rs.next()) {
				result = rs.getInt(1);
				// result = rs.getInt("count(*)"); 
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return result;		
	}
	// [관리자] 문의글 개수 계산 메서드 - getInquiryCount()	


	// 특정 제품 문의글 개수 계산 메서드 - getItemInquiryCount(int item_id)
	public int getItemInquiryCount(int item_id) {
		int result = 0;	
		
		try {
			// 1.2. 디비 연결
			con = getCon();		
			
			// 3. SQL 구문 작성(select) & pstmt 객체
			sql = "select count(*) from board_remaster where board_type='Q' and item_id=?";
			pstmt = con.prepareStatement(sql);			
			
			// ?
			pstmt.setInt(1, item_id);
			
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리 - 개수를 저장
			if(rs.next()) {
				result = rs.getInt(1);
				// result = rs.getInt("count(*)"); 
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return result;		
	}
	// 특정 제품 문의글 개수 계산 메서드 - getItemInquiryCount(int item_id)	
	
	
	// 특정 렌탈제품 문의글 개수 계산 메서드 - getRItemInquiryCount(int rItemId)
	public int getRItemInquiryCount(int rItemId) {
		int result = 0;	
		
		try {
			// 1.2. 디비 연결
			con = getCon();		
			
			// 3. SQL 구문 작성(select) & pstmt 객체
			sql = "select count(*) from board_remaster where board_type='Q' and rental_item_id=?";
			pstmt = con.prepareStatement(sql);			
			
			// ?
			pstmt.setInt(1, rItemId);
			
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리 - 개수를 저장
			if(rs.next()) {
				result = rs.getInt(1);
				// result = rs.getInt("count(*)"); 
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return result;		
	}
	// 특정 렌탈제품 문의글 개수 계산 메서드 - getRItemInquiryCount(int rItemId)
	
	
	// [관리자] 문의글 목록을 가져오는 메서드 - getAdInquiryList(int startRow, int pageSize)
	public ArrayList getAdInquiryList(int startRow, int pageSize) {
		// 글정보를 저장하는 배열
		ArrayList inquiryList = new ArrayList();
					
		try {
			// 1.2. 디비 연결
			con = getCon();
						
			// 3. SQL 구문(select) 작성 & pstmt 객체
			sql = "select * from board_remaster where board_type='Q' order by board_id desc limit ?,?";
			pstmt = con.prepareStatement(sql);
						
			// ?
			pstmt.setInt(1, startRow-1); // 시작행 번호-1
			pstmt.setInt(2, pageSize); // 페이지 개수
						
			// 4. SQL 실행
			rs = pstmt.executeQuery();
						
			// 5. 데이터 처리
			// 글정보 전부 가져오기
			// 여러 개의 정보 => ArrayList 저장
			while(rs.next()) {
				// 글 하나의 정보 => BoardDTO 저장
				BoardDTO bdto = new BoardDTO();
							
				bdto.setBoard_id(rs.getInt("board_id"));
				bdto.setMember_user_id(rs.getString("member_user_id"));
				bdto.setBoard_type(rs.getString("board_type"));
				bdto.setInquiry_type(rs.getString("inquiry_type"));
				bdto.setAnswer_state(rs.getString("answer_state"));
				bdto.setBoard_subject(rs.getString("board_subject"));
				bdto.setBoard_content(rs.getString("board_content"));
				bdto.setBoard_writeTime(rs.getDate("board_writeTime"));
							
				// 글 하나의 정보를 배열의 한 칸에 저장
				inquiryList.add(bdto);				
			} // while
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
					
		return inquiryList;
	}	
	// [관리자] 문의글 목록을 가져오는 메서드 - getAdInquiryList(int startRow, int pageSize)
	

	// 제품 문의글 목록을 가져오는 메서드 - getItemInquiryList(int item_id, int startRow, int pageSize)
	public ArrayList getItemInquiryList(int item_id, int startRow, int pageSize) {
		// 글정보를 저장하는 배열
		ArrayList inquiryList = new ArrayList();
					
		try {
			// 1.2. 디비 연결
			con = getCon();
						
			// 3. SQL 구문(select) 작성 & pstmt 객체
			sql = "select board_id, member_name, board_type, inquiry_type, answer_state, "
					+ "board_subject, board_content, board_writeTime "
					+ "from member m join board_remaster br "
					+ "on m.member_user_id  = br.member_user_id "
					+ "where board_type='Q' and item_id=? "
					+ "order by board_id desc limit ?,?";
			pstmt = con.prepareStatement(sql);
						
			// ?
			pstmt.setInt(1, item_id);
			pstmt.setInt(2, startRow-1); // 시작행 번호-1
			pstmt.setInt(3, pageSize); // 페이지 개수
						
			// 4. SQL 실행
			rs = pstmt.executeQuery();
						
			// 5. 데이터 처리
			// 글정보 전부 가져오기
			// 여러 개의 정보 => ArrayList 저장
			while(rs.next()) {
				// 글 하나의 정보 => BoardDTO 저장
				BoardDTO bdto = new BoardDTO();
							
				bdto.setBoard_id(rs.getInt("board_id"));
				bdto.setMember_name(rs.getString("member_name"));
				bdto.setBoard_type(rs.getString("board_type"));
				bdto.setInquiry_type(rs.getString("inquiry_type"));
				bdto.setAnswer_state(rs.getString("answer_state"));
				bdto.setBoard_subject(rs.getString("board_subject"));
				bdto.setBoard_content(rs.getString("board_content"));
				bdto.setBoard_writeTime(rs.getDate("board_writeTime"));
							
				// 글 하나의 정보를 배열의 한 칸에 저장
				inquiryList.add(bdto);				
			} // while
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
					
		return inquiryList;
	}	
	// 제품 문의글 목록을 가져오는 메서드 - getItemInquiryList(int item_id, int startRow, int pageSize)
	
	
	// 렌탈제품 문의글 목록을 가져오는 메서드 - getRItemInquiryList(int rItemId, int startRow, int pageSize)
	public ArrayList getRItemInquiryList(int rItemId, int startRow, int pageSize) {
		// 글정보를 저장하는 배열
		ArrayList inquiryList = new ArrayList();
					
		try {
			// 1.2. 디비 연결
			con = getCon();
						
			// 3. SQL 구문(select) 작성 & pstmt 객체
			sql = "select board_id, member_name, board_type, inquiry_type, answer_state, "
					+ "board_subject, board_content, board_writeTime "
					+ "from member m join board_remaster br "
					+ "on m.member_user_id  = br.member_user_id "
					+ "where board_type='Q' and rental_item_id=? "
					+ "order by board_id desc limit ?,?";
			pstmt = con.prepareStatement(sql);
						
			// ?
			pstmt.setInt(1, rItemId);
			pstmt.setInt(2, startRow-1); // 시작행 번호-1
			pstmt.setInt(3, pageSize); // 페이지 개수
						
			// 4. SQL 실행
			rs = pstmt.executeQuery();
						
			// 5. 데이터 처리
			// 글정보 전부 가져오기
			// 여러 개의 정보 => ArrayList 저장
			while(rs.next()) {
				// 글 하나의 정보 => BoardDTO 저장
				BoardDTO bdto = new BoardDTO();
							
				bdto.setBoard_id(rs.getInt("board_id"));
				bdto.setMember_name(rs.getString("member_name"));
				bdto.setBoard_type(rs.getString("board_type"));
				bdto.setInquiry_type(rs.getString("inquiry_type"));
				bdto.setAnswer_state(rs.getString("answer_state"));
				bdto.setBoard_subject(rs.getString("board_subject"));
				bdto.setBoard_content(rs.getString("board_content"));
				bdto.setBoard_writeTime(rs.getDate("board_writeTime"));
							
				// 글 하나의 정보를 배열의 한 칸에 저장
				inquiryList.add(bdto);				
			} // while
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
					
		return inquiryList;
	}	
	// 렌탈제품 문의글 목록을 가져오는 메서드 - getRItemInquiryList(int rItemId, int startRow, int pageSize)
	

	// 제품 리뷰 작성하기 메서드 - insertReviewItem(BoardDTO bdto)
	public void insertReviewItem(BoardDTO bdto) {
		int boardId = 0;
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. SQL 구문(select) & pstmt 객체
			sql = "select max(board_id) from board_remaster";
			pstmt = con.prepareStatement(sql);
			
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				// boardId = rs.getInt("max(bno)") + 1;
				// => getInt(int columnIndex):
				boardId = rs.getInt(1) + 1; 
			} else {
				boardId = 1;
			}
				
			// 3. SQL 구문(insert) & pstmt 객체
			sql = "insert into board_remaster(board_id, member_user_id, item_id, board_type, "
					+ "board_content, board_writeTime, board_readCount, board_file, rating) "
					+ "values(?, ?, ?, ?, ?, now(), ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
			// ?		
			pstmt.setInt(1, boardId);
			pstmt.setString(2, bdto.getMember_user_id());
			pstmt.setInt(3, bdto.getItem_id());
			pstmt.setString(4, "R");
			pstmt.setString(5, bdto.getBoard_content());
			pstmt.setInt(6, 0);
			pstmt.setString(7, bdto.getBoard_file());
			pstmt.setDouble(8, bdto.getRating()/2);
			
			// 4. SQL 실행
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
	} 
	// 제품 리뷰 작성하기 메서드 - insertReviewItem(BoardDTO bdto)


	// 렌탈제품 리뷰 작성하기 메서드 - insertReviewRItem(BoardDTO bdto)
	public void insertReviewRItem(BoardDTO bdto) {
		int boardId = 0;
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. SQL 구문(select) & pstmt 객체
			sql = "select max(board_id) from board_remaster";
			pstmt = con.prepareStatement(sql);
			
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				// boardId = rs.getInt("max(bno)") + 1;
				// => getInt(int columnIndex):
				boardId = rs.getInt(1) + 1; 
			} else {
				boardId = 1;
			}
				
			// 3. SQL 구문(insert) & pstmt 객체
			sql = "insert into board_remaster(board_id, member_user_id, rental_item_id, board_type, "
					+ "board_content, board_writeTime, board_readCount, board_file, rating) "
					+ "values(?, ?, ?, ?, ?, now(), ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
			// ?		
			pstmt.setInt(1, boardId);
			pstmt.setString(2, bdto.getMember_user_id());
			pstmt.setInt(3, bdto.getRental_item_id());
			pstmt.setString(4, "R");
			pstmt.setString(5, bdto.getBoard_content());
			pstmt.setInt(6, 0);
			pstmt.setString(7, bdto.getBoard_file());
			pstmt.setDouble(8, bdto.getRating()/2);
			
			// 4. SQL 실행
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
	} 
	// 렌탈제품 리뷰 작성하기 메서드 - insertReviewRItem(BoardDTO bdto)
	
	
	// 특정 제품 리뷰글 개수 계산 메서드 - getItemReviewCount(int item_id)
	public int getItemReviewCount(int item_id) {
		int result = 0;	
				
		try {
			// 1.2. 디비 연결
			con = getCon();		
					
			// 3. SQL 구문 작성(select) & pstmt 객체
			sql = "select count(*) from board_remaster where board_type='R' and item_id=?";
			pstmt = con.prepareStatement(sql);		
			
			// ?
			pstmt.setInt(1, item_id);
			
			// 4. SQL 실행
			rs = pstmt.executeQuery();
					
			// 5. 데이터 처리 - 개수를 저장
			if(rs.next()) {
				result = rs.getInt(1);
				// result = rs.getInt("count(*)"); 
			}
					
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
				
		return result;		
	}
	// 특정 제품 리뷰글 개수 계산 메서드 - getItemReviewCount(int item_id)
	
	
	// 특정 렌탈제품 리뷰글 개수 계산 메서드 - getRItemReviewCount(int rItemId)
	public int getRItemReviewCount(int rItemId) {
		int result = 0;	
				
		try {
			// 1.2. 디비 연결
			con = getCon();		
					
			// 3. SQL 구문 작성(select) & pstmt 객체
			sql = "select count(*) from board_remaster where board_type='R' and rental_item_id=?";
			pstmt = con.prepareStatement(sql);		
			
			// ?
			pstmt.setInt(1, rItemId);
			
			// 4. SQL 실행
			rs = pstmt.executeQuery();
					
			// 5. 데이터 처리 - 개수를 저장
			if(rs.next()) {
				result = rs.getInt(1);
				// result = rs.getInt("count(*)"); 
			}
					
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
				
		return result;		
	}
	// 특정 렌탈제품 리뷰글 개수 계산 메서드 - getRItemReviewCount(int rItemId)

	
	// 리뷰글 조회수 1 증가 메서드 - updateReviewReadCount(int boardId) 
	public void updateReviewReadCount(int boardId) {
				
		try {	
			// 1.2. 디비 연결
			con = getCon();
					
			// 3. SQL 구문 작성(update) & pstmt 객체
			// 특정 글의 조회수를 1 증가
			sql = "update board_remaster set board_readCount=board_readCount+1 where board_id=?";
			pstmt = con.prepareStatement(sql);
					
			// ?
			pstmt.setInt(1, boardId);
					
			// 4. SQL 실행
			pstmt.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
				
	}	
	// 리뷰글 조회수 1 증가 메서드 - updateReviewReadCount(int boardId) 	
				

	// 특정 제품 리뷰글 목록을 가져오는 메서드 - getItemReviewList(int item_id, int startRow, int pageSize)
	public ArrayList getItemReviewList(int item_id, int startRow, int pageSize) {
		// 글정보를 저장하는 배열
		ArrayList reviewList = new ArrayList();
					
		try {
			// 1.2. 디비 연결
			con = getCon();
						
			// 3. SQL 구문(select) 작성 & pstmt 객체
			sql = "select board_id, member_name, board_type, board_content, board_writeTime, "
					+ "board_readCount, board_file, rating "
					+ "from member m join board_remaster br "
					+ "on m.member_user_id  = br.member_user_id "
					+ "where board_type='R' and item_id=? "
					+ "order by board_id desc limit ?,?";
			pstmt = con.prepareStatement(sql);
						
			// ?
			pstmt.setInt(1, item_id);
			pstmt.setInt(2, startRow-1); // 시작행 번호-1
			pstmt.setInt(3, pageSize); // 페이지 개수
						
			// 4. SQL 실행
			rs = pstmt.executeQuery();
						
			// 5. 데이터 처리
			// 글정보 전부 가져오기
			// 여러 개의 정보 => ArrayList 저장
			while(rs.next()) {
				// 글 하나의 정보 => BoardDTO 저장
				BoardDTO bdto = new BoardDTO();
							
				bdto.setBoard_id(rs.getInt("board_id"));
				bdto.setMember_name(rs.getString("member_name"));
				bdto.setBoard_type(rs.getString("board_type"));
				bdto.setBoard_content(rs.getString("board_content"));
				bdto.setBoard_writeTime(rs.getDate("board_writeTime"));
				bdto.setBoard_readCount(rs.getInt("board_readCount"));
				bdto.setBoard_file(rs.getString("board_file"));
				bdto.setRating(rs.getDouble("rating"));
							
				// 글 하나의 정보를 배열의 한 칸에 저장
				reviewList.add(bdto);				
			} // while
						
									
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
					
		return reviewList;
	}	
	// 특정 제품 리뷰글 목록을 가져오는 메서드 - getItemReviewList(int item_id, int startRow, int pageSize)
	
	
	// 특정 렌탈제품 리뷰글 목록을 가져오는 메서드 - getRItemReviewList(int rItemId, int startRow, int pageSize)
	public ArrayList getRItemReviewList(int rItemId, int reviewStartRow, int reviewPageSize) {
		// 글정보를 저장하는 배열
		ArrayList reviewList = new ArrayList();
					
		try {
			// 1.2. 디비 연결
			con = getCon();
						
			// 3. SQL 구문(select) 작성 & pstmt 객체
			sql = "select board_id, member_name, board_type, board_content, board_writeTime, "
					+ "board_readCount, board_file, rating "
					+ "from member m join board_remaster br "
					+ "on m.member_user_id  = br.member_user_id "
					+ "where board_type='R' and rental_item_id=? "
					+ "order by board_id desc limit ?,?";
			pstmt = con.prepareStatement(sql);
						
			// ?
			pstmt.setInt(1, rItemId);
			pstmt.setInt(2, reviewStartRow-1); // 시작행 번호-1
			pstmt.setInt(3, reviewPageSize); // 페이지 개수
						
			// 4. SQL 실행
			rs = pstmt.executeQuery();
						
			// 5. 데이터 처리
			// 글정보 전부 가져오기
			// 여러 개의 정보 => ArrayList 저장
			while(rs.next()) {
				// 글 하나의 정보 => BoardDTO 저장
				BoardDTO bdto = new BoardDTO();
							
				bdto.setBoard_id(rs.getInt("board_id"));
				bdto.setMember_name(rs.getString("member_name"));
				bdto.setBoard_type(rs.getString("board_type"));
				bdto.setBoard_content(rs.getString("board_content"));
				bdto.setBoard_writeTime(rs.getDate("board_writeTime"));
				bdto.setBoard_readCount(rs.getInt("board_readCount"));
				bdto.setBoard_file(rs.getString("board_file"));
				bdto.setRating(rs.getDouble("rating"));
							
				// 글 하나의 정보를 배열의 한 칸에 저장
				reviewList.add(bdto);				
			} // while
						
									
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
					
		return reviewList;
	}	
	// 특정 렌탈제품 리뷰글 목록을 가져오는 메서드 - getRItemReviewList(int rItemId, int startRow, int pageSize)
	
		
	// [관리자] 답변상태 변경하기 메서드 - updateAnswerState(BoardDTO bdto)
	public BoardDTO updateAnswerState(BoardDTO bdto) {
		
		try {
			// 1.2. 디비 연결
			con = getCon();
		
			// 3. SQL 구문 작성(select) & pstmt 객체 생성
			sql = "select * from board_remaster where board_id=?";
			pstmt = con.prepareStatement(sql);
		
			// ?
			pstmt.setInt(1, bdto.getBoard_id()); 
			
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				// 3. SQL 구문 작성(update) & pstmt 객체
				sql = "update board_remaster set answer_state=? where board_id=?";
				pstmt = con.prepareStatement(sql);
				
				// ?
				pstmt.setString(1, "답변완료");
				pstmt.setInt(2, bdto.getBoard_id());
				
				// 4. SQL 구문 실행
				pstmt.executeUpdate();
			}
			
		} catch (Exception e) { 
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return bdto;
	}
	// [관리자] 답변상태 변경하기 메서드 - updateAnswerState(BoardDTO bdto)
	
	
	// [관리자_문의] 제품 정보 불러오기 - getItemInquiry(int boardId)
	public ItemDTO getItemInquiry(int boardId) {
		ItemDTO idto = null; 
		
		try {	
			// 1.2. 디비 연결
			con = getCon();
				
			// 3. SQL 구문 작성(select) & pstmt 객체
			sql = "select item_name, item_img_main "
					+ "from item i join board_remaster br "
					+ "on i.item_id = br.item_id "
					+ "where board_type='Q' and board_id=?";
			pstmt = con.prepareStatement(sql);
				
			// ?
			pstmt.setInt(1, boardId);		
				
			// 4. SQL 실행
			rs = pstmt.executeQuery();
				
			// 5. 데이터 처리 (rs -> ItemDTO)
			if(rs.next()) { // 데이터가 존재할 때				
				idto = new ItemDTO();
					
				idto.setItem_name(rs.getString("item_name"));
				idto.setItem_img_main(rs.getString("item_img_main"));
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
			
		return idto;
	}
	// [관리자_문의] 제품 정보 불러오기 - getItemInquiry(int boardId)
	
	
	// [관리자_문의] 렌탈제품 정보 불러오기 - getRItemInquiry(int boardId)
	public RentalDTO getRItemInquiry(int boardId) {
		RentalDTO rdto = null; 
		
		try {	
			// 1.2. 디비 연결
			con = getCon();
				
			// 3. SQL 구문 작성(select) & pstmt 객체
			sql = "select rental_item_name, rental_img_main "
					+ "from rental_item ri join board_remaster br "
					+ "on ri.rental_item_id = br.rental_item_id "
					+ "where board_type='Q' and board_id=?";
			pstmt = con.prepareStatement(sql);
				
			// ?
			pstmt.setInt(1, boardId);		
				
			// 4. SQL 실행
			rs = pstmt.executeQuery();
				
			// 5. 데이터 처리 (rs -> RentalDTO)
			if(rs.next()) { // 데이터가 존재할 때				
				rdto = new RentalDTO();
					
				rdto.setRental_item_name(rs.getString("rental_item_name"));
				rdto.setRental_img_main(rs.getString("rental_img_main"));
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
			
		return rdto;
	}
	// [관리자_문의] 렌탈제품 정보 불러오기 - getRItemInquiry(int boardId)	
}
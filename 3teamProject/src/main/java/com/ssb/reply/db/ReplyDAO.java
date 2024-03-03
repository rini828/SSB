package com.ssb.reply.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReplyDAO {

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
	
	
	// 문의글 답변 작성하기 메서드 - insertInquiryABoard(ReplyDTO rdto)
	public void insertInquiryABoard(ReplyDTO rdto) {
		int replyId = 0;
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// reply_id 계산
			// 3. SQL 구문(select) & pstmt 객체
			sql = "select max(reply_id) from reply";
			pstmt = con.prepareStatement(sql);
						
			// 4. SQL 실행
			rs = pstmt.executeQuery();
						
			// 5. 데이터 처리
			if(rs.next()) {
				// reply_id = rs.getInt("max(reply_id)") + 1;
				// => getInt(int columnIndex):
				replyId = rs.getInt(1) + 1; 
			} else {
				replyId = 1;
			}
						
			
			// 3. SQL 구문(insert) & pstmt 객체
			sql = "insert into reply(reply_id, board_id, admin_user_id, reply_content, reply_writeTime) "
					+ "values(?, ?, ?, ?, now())";
			pstmt = con.prepareStatement(sql);
			
			// ?		
			pstmt.setInt(1, replyId);
			pstmt.setInt(2, rdto.getBoard_id());
			pstmt.setString(3, rdto.getAdmin_user_id());
			pstmt.setString(4, rdto.getReply_content());
			
			// 4. SQL 실행
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
	} 	
	// 문의글 답변 작성하기 메서드 - insertInquiryABoard(ReplyDTO rdto)

	
	// 특정 번호에 해당하는 글정보 가져오기 메서드 - getReply(int boardId)	
	public ReplyDTO getReply(int boardId) {
		ReplyDTO rdto = null; 
			
		try {	
			// 1.2. 디비 연결
			con = getCon();
				
			// 3. SQL 구문 작성(select) & pstmt 객체
			sql = "select * from reply where board_id=?";
			pstmt = con.prepareStatement(sql);
				
			// ?
			pstmt.setInt(1, boardId);		
				
			// 4. SQL 실행
			rs = pstmt.executeQuery();
				
			// 5. 데이터 처리
			if(rs.next()) { // 데이터가 존재할 때				
				rdto = new ReplyDTO();
				
				rdto.setReply_id(rs.getInt("reply_id"));
				rdto.setBoard_id(rs.getInt("board_id"));						
				rdto.setReply_content(rs.getString("reply_content"));
				rdto.setReply_writeTime(rs.getDate("reply_writeTime"));
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
			
		return rdto;		
	}	
	// 특정 번호에 해당하는 글정보 가져오기 메서드 - getReply(int boardId)	
	
	
	// 특정 문의글에 해당하는 답변 정보 가져오기 메서드 - getItemReply(int item_id)
	public ReplyDTO getItemReply(int item_id) {
		ReplyDTO rdto = null; 
			
		try {	
			// 1.2. 디비 연결
			con = getCon();
				
			// 3. SQL 구문 작성(select) & pstmt 객체
			sql = "select rp.board_id, reply_content, reply_writeTime "
					+ "from reply rp join board_remaster br "
					+ "on rp.board_id = br.board_id "
					+ "where item_id=?";
			pstmt = con.prepareStatement(sql);
				
			// ?
			pstmt.setInt(1, item_id);		
				
			// 4. SQL 실행
			rs = pstmt.executeQuery();
				
			// 5. 데이터 처리
			if(rs.next()) { // 데이터가 존재할 때				
				rdto = new ReplyDTO();
				
				rdto.setBoard_id(rs.getInt("board_id"));						
				rdto.setReply_content(rs.getString("reply_content"));
				rdto.setReply_writeTime(rs.getDate("reply_writeTime"));
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
			
		return rdto;		
	}	
	// 특정 문의글에 해당하는 답변 정보 가져오기 메서드 - getItemReply(int item_id)
	

	// [렌탈제품] 특정 문의글에 해당하는 답변 정보 가져오기 메서드 - getRItemReply(int rItemId)
	public ReplyDTO getRItemReply(int rItemId) {
		ReplyDTO rdto = null; 
			
		try {	
			// 1.2. 디비 연결
			con = getCon();
				
			// 3. SQL 구문 작성(select) & pstmt 객체
			sql = "select rp.board_id, reply_content, reply_writeTime "
					+ "from reply rp join board_remaster br "
					+ "on rp.board_id = br.board_id "
					+ "where rental_item_id=?";
			pstmt = con.prepareStatement(sql);
				
			// ?
			pstmt.setInt(1, rItemId);		
				
			// 4. SQL 실행
			rs = pstmt.executeQuery();
				
			// 5. 데이터 처리
			if(rs.next()) { // 데이터가 존재할 때				
				rdto = new ReplyDTO();
				
				rdto.setBoard_id(rs.getInt("board_id"));
				rdto.setReply_content(rs.getString("reply_content"));
				rdto.setReply_writeTime(rs.getDate("reply_writeTime"));
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
			
		return rdto;		
	}	
	// [렌탈제품] 특정 문의글에 해당하는 답변 정보 가져오기 메서드 - getRItemReply(int rItemId)
	
}
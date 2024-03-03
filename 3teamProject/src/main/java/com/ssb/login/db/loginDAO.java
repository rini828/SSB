package com.ssb.login.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ssb.member.db.MemberDTO;

public class loginDAO {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private String sql = "";
    
    
    // 데이터베이스 연결을 위한 메서드
    private Connection getCon() throws Exception {
        try {
            Context initCTX = new InitialContext();
            DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/ssb");
            con = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
    
    // 데이터베이스 자원을 닫기 위한 메서드
    public void closeDB() {
        try {
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
            if (con != null)
                con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 로그인 메서드1
    public int login(String member_user_id, String member_pw) {
        int result = 0;
        
      try {
            con = getCon();
            sql = "SELECT * FROM member WHERE member_user_id = ? AND member_pw = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member_user_id);
            pstmt.setString(2, member_pw);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // 로그인 성공
                result = 1;
                
            } else {
                // 로그인 실패
                result = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
       return result;
    }

    
    // 로그인 체크 메서드 - loginMember(dto)
 	public int loginMember(MemberDTO dto) {
 		int result = -1; // -1 0 1
 		
 		try {
 			// 1.2. 디비연결
 			con = getCon();
 			// 3. sql 작성 & pstmt 객체
 			sql = "select member_pw, member_situation from member where member_user_id = ?";
 			pstmt = con.prepareStatement(sql);
 			pstmt.setString(1, dto.getMember_user_id());
 			// 4. sql 실행
 			rs = pstmt.executeQuery();
 			// 5. 데이터 처리
 			if(rs.next()) { // 회원정보가 있음
 				if(rs.getString("member_situation").equals("탈퇴")) {
 					// 상태가 '탈퇴'한 회원일 경우
 					result = 2;
 				}else if(dto.getMember_pw().equals(rs.getString("member_pw"))) {
 					// 비밀번호 같음
 					result = 1;
 				}else {
 					// 비밀번호 다름
 					result = 0;
 				}
 			}else {// 회원정보 없음
 				result = -1;
 			}
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 		} finally {
 			closeDB();
 		}
 		return result;
 	}
}

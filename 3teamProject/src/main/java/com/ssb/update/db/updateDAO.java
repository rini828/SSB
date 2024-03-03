package com.ssb.update.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ssb.member.db.MemberDTO;

public class updateDAO {

    // 공통변수
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private String sql = "";

    // 공통메서드(기능)
	// 공통) 디비 연결하기(CP)
    // 커넥션 풀을 이용하여 데이터베이스에 연결하는 메서드입니다.
	private Connection getCon() throws Exception {
			
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/ssb");
		con = ds.getConnection();
			
		return con;			
	}

	// 공통) 디비 자원해제
	// 데이터베이스 자원을 해제하는 메서드입니다.
	public void CloseDB() {
			
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close(); 
			if(con != null) con.close(); 
		} catch(SQLException e) {
			e.printStackTrace();
		}
			
	}
	
    // 회원정보 조회 메서드 - getMember(id)
	// 사용자 아이디에 해당하는 회원 정보를 조회하는 메서드입니다.
    public MemberDTO getMember(String id) {
        MemberDTO dto = null;

        try {
        	 // 1. 디비 연결
            con = getCon();
            // 3. sql 작성(select) & pstmt 객체
            sql = "select * from member where member_user_id=?";
            pstmt = con.prepareStatement(sql);
            // ???
            pstmt.setString(1, id);
            // 4. sql 실행
            rs = pstmt.executeQuery();
            // 5. 데이터 처리 (DB에 저장된 정보(rs)를 DTO로 저장)
            if (rs.next()) {
                dto = new MemberDTO();
                // rs => dto 저장
                dto.setMember_user_id(rs.getString("member_user_id"));
                dto.setMember_pw(rs.getString("member_pw"));
                dto.setMember_name(rs.getString("member_name"));
                dto.setMember_birth(rs.getDate("member_birth"));
                dto.setMember_gender(rs.getString("member_gender"));
                dto.setMember_email(rs.getString("member_email"));
                dto.setMember_phone(rs.getString("member_phone"));
                dto.setMember_regdate(rs.getTimestamp("member_regdate"));
                dto.setMember_payment(rs.getInt("member_payment"));
                dto.setMember_point(rs.getInt("member_point"));
                dto.setMember_grade(rs.getString("member_grade"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseDB();
        }

        return dto;
    }
    // 회원정보 조회 메서드 - getMember(id)

    // 비밀번호 확인 메서드 - checkPassword(userId, password)
    // 사용자 아이디와 비밀번호를 받아와 해당 회원의 비밀번호와 비교하여 일치 여부를 확인하는 메서드입니다.
    public boolean checkPassword(String userId, String password) {
        boolean result = false;

        try {
            // 3. sql 작성(select) & pstmt 객체
            sql = "select member_pw from member where member_user_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);
            // 4. sql 실행
            rs = pstmt.executeQuery();

            // 5. 데이터처리
            if (rs.next()) {
                result = password.equals(rs.getString("member_pw"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseDB();
        }

        return result;
    }

    // 회원정보 수정 메서드 - updateMember(mdto)
    // 사용자 아이디와 입력된 비밀번호를 이용하여 회원의 비밀번호를 확인하고, 일치하면 회원 정보를 업데이트하는 메서드입니다. 결과에 따라 성공 여부를 반환합니다.
    public int updateMember(String userId , MemberDTO mdto) {
        int result = -1; // -1 0 1

        try {
            // 1.2. 디비연결
        	getCon(); // 데이터베이스 연결 설정

            // 3. sql 작성(select) & pstmt 객체
            sql = "select member_pw from member where member_user_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            
            // 5. 데이터처리
            if (rs.next()) {
                // 비밀번호 확인
                if (mdto.getMember_pw().equals(rs.getString("member_pw"))) {
                    // 3. sql 작성(update) & pstmt객체
            	sql = "update member set member_name=?, member_phone=?, member_pw=?, member_email=? where member_user_id=?";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, mdto.getMember_name());
                pstmt.setString(2, mdto.getMember_phone());
                pstmt.setString(3, mdto.getMember_pw());
                pstmt.setString(4, mdto.getMember_email());
                pstmt.setString(5, userId);
                // 4. sql 실행
                result = pstmt.executeUpdate();
            }else {
            	 // 비밀번호 불일치
                result = 0;
            	}
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseDB();
        }

        return result;
    }
    // 회원정보 수정 메서드 - updateMember(dto)
}

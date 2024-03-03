package com.ssb.location.db;

import java.util.ArrayList;

import com.ssb.util.DAO;

public class locationDAO extends DAO{
	
	// 배송지 조회 메서드
	public ArrayList<locationDTO> getlocation(String member_id) {
		ArrayList<locationDTO> dtoArray = new ArrayList<locationDTO>();
		locationDTO dto = null;
		try {
			con = getCon();
			sql = "SELECT * FROM location WHERE member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new locationDTO();
				dto.setLocation_id(rs.getInt("location_id"));
				dto.setLocation_name(rs.getString("location_name"));
				dto.setLocation_phone(rs.getString("location_phone"));
				dto.setLocation_postcode(rs.getString("location_postcode"));
				dto.setLocation_add(rs.getString("location_add"));
				dto.setLocationD_add(rs.getString("locationD_add"));
				dto.setLocation_title(rs.getString("location_title"));
				dto.setLocation_requested(rs.getString("location_requested"));
				dto.setMember_id(rs.getInt("member_id"));
				dtoArray.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return dtoArray;
	}

	// 배송지 등록 메서드
	public int insertLocation(locationDTO dto) {
		int result = -1;
		try {
			con = getCon();
			sql = "INSERT INTO location VALUES(DEFAULT,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getLocation_name());
			pstmt.setString(2, dto.getLocation_phone());
			pstmt.setString(3, dto.getLocation_postcode());
			pstmt.setString(4, dto.getLocation_add());
			pstmt.setString(5, dto.getLocationD_add());
			pstmt.setString(6, dto.getLocation_title());
			pstmt.setString(7, dto.getLocation_requested());
			pstmt.setInt(8, dto.getMember_id());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return result;
	}
	
	//배송지 수정 메서드
	public int rewriteLocation(locationDTO dto) {
		int result = -1;
		try {
			con = getCon();
			sql = "UPDATE location SET location_name = ?,location_phone = ?,location_postcode = ?,location_add = ?,locationD_add = ?,location_title = ?,location_requested = ? WHERE location_id = ? AND member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getLocation_name());
			pstmt.setString(2, dto.getLocation_phone());
			pstmt.setString(3, dto.getLocation_postcode());
			pstmt.setString(4, dto.getLocation_add());
			pstmt.setString(5, dto.getLocationD_add());
			pstmt.setString(6, dto.getLocation_title());
			pstmt.setString(7, dto.getLocation_requested());
			pstmt.setInt(8, dto.getLocation_id());
			pstmt.setInt(9, dto.getMember_id());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return result;
	}
	
	//배송지 수정 페이지 이동 메서드
	public locationDTO getReWrite(int location_id, String member_id) {
		locationDTO dto = null;
		try {
			con = getCon();
			sql = "SELECT * FROM location WHERE location_id = ? AND member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, location_id);
			pstmt.setInt(2, Integer.parseInt(member_id));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new locationDTO();
				dto.setLocation_id(rs.getInt("location_id"));
				dto.setLocation_name(rs.getString("location_name"));
				dto.setLocation_phone(rs.getString("location_phone"));
				dto.setLocation_postcode(rs.getString("location_postcode"));
				dto.setLocation_add(rs.getString("location_add"));
				dto.setLocationD_add(rs.getString("locationD_add"));
				dto.setLocation_title(rs.getString("location_title"));
				dto.setLocation_requested(rs.getString("location_requested"));
				dto.setMember_id(rs.getInt("member_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return dto;
	}
	
	//배송지 삭제 메서드
	public int deleteLocation(int location_id, int member_id) {
		int result = -1;
		try {
			con = getCon();
			sql = "DELETE FROM location WHERE location_id = ? AND member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, location_id);
			pstmt.setInt(2, member_id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return result;
	}
	
	
	// 주문 아이디 값으로 해당 주문정보 모두 호출 
	
	public locationDTO findById(int location_id) {
		locationDTO locationDTO = new locationDTO();
		
		try {
			con = getCon();
			sql = "select * from location where location_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, location_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				locationDTO = new locationDTO();
				locationDTO.setLocation_id(rs.getInt("location_id"));
				locationDTO.setLocation_name(rs.getString("location_name"));
				locationDTO.setLocation_phone(rs.getString("location_postcode"));
				locationDTO.setLocation_add(rs.getString("location_add"));
				locationDTO.setLocationD_add(rs.getString("locationD_add"));
				locationDTO.setLocation_title(rs.getString("location_title"));
				locationDTO.setLocation_requested(rs.getString("location_requested"));
				locationDTO.setMember_id(rs.getInt("member_id"));
				
				
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return locationDTO;
		
	}
	
	// 렌탈 예약용 배송지 조회 메서드
			public ArrayList getlocationRental(String member_id) {
				ArrayList locaList = new ArrayList();
				locationDTO ldto = null;
				try {
					con = getCon();
					sql = "SELECT * FROM location l join member m on l.member_id = m.member_id WHERE member_user_id = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, member_id);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						ldto = new locationDTO();
						ldto.setLocation_id(rs.getInt("location_id"));
						ldto.setLocation_name(rs.getString("location_name"));
						ldto.setLocation_phone(rs.getString("location_phone"));
						ldto.setLocation_postcode(rs.getString("location_postcode"));
						ldto.setLocation_add(rs.getString("location_add"));
						ldto.setLocationD_add(rs.getString("locationD_add"));
						ldto.setLocation_title(rs.getString("location_title"));
						ldto.setLocation_requested(rs.getString("location_requested"));
						ldto.setMember_id(rs.getInt("member_id"));
						locaList.add(ldto);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					CloseDB();
				}
				return locaList;
			}
}

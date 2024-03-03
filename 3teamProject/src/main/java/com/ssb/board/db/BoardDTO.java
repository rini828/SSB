package com.ssb.board.db;

import java.sql.Date;

public class BoardDTO {

	private int board_id; 			
	private String admin_user_id;
	private String member_user_id;
	
	private String admin_name; // 관리자 테이블
	private String member_name; // 회원 테이블
	
	private int item_id; // 제품 테이블
	private int rental_item_id; // 렌탈제품 테이블
	
	private String board_type;
	private String inquiry_type;
	private String answer_state;
	
	private String board_subject;
	private String board_content;
	private Date board_writeTime;
	private int board_readCount;
	private String board_file;	
	private double rating;
	
	
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	
	public String getAdmin_user_id() {
		return admin_user_id;
	}
	public void setAdmin_user_id(String admin_user_id) {
		this.admin_user_id = admin_user_id;
	}
	
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	
	public String getMember_user_id() {
		return member_user_id;
	}
	public void setMember_user_id(String member_user_id) {
		this.member_user_id = member_user_id;
	}
	
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
		
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	
	public int getRental_item_id() {
		return rental_item_id;
	}
	public void setRental_item_id(int rental_item_id) {
		this.rental_item_id = rental_item_id;
	}
	
	public String getBoard_type() {
		return board_type;
	}
	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}
	
	public String getInquiry_type() {
		return inquiry_type;
	}
	public void setInquiry_type(String inquiry_type) {
		this.inquiry_type = inquiry_type;
	}
	
	public String getAnswer_state() {
		return answer_state;
	}
	public void setAnswer_state(String answer_state) {
		this.answer_state = answer_state;
	}
	
	public String getBoard_subject() {
		return board_subject;
	}
	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}
	
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	
	public Date getBoard_writeTime() {
		return board_writeTime;
	}
	public void setBoard_writeTime(Date board_writeTime) {
		this.board_writeTime = board_writeTime;
	}
	
	public int getBoard_readCount() {
		return board_readCount;
	}
	public void setBoard_readCount(int board_readCount) {
		this.board_readCount = board_readCount;
	}
	
	public String getBoard_file() {
		return board_file;
	}
	public void setBoard_file(String board_file) {
		this.board_file = board_file;
	}
	
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	
	@Override
	public String toString() {
		return "BoardDTO [board_id=" + board_id + ", admin_user_id=" + admin_user_id + ", member_user_id="
				+ member_user_id + ", admin_name=" + admin_name + ", member_name=" + member_name + ", item_id="
				+ item_id + ", rental_item_id=" + rental_item_id + ", board_type=" + board_type + ", inquiry_type="
				+ inquiry_type + ", answer_state=" + answer_state + ", board_subject=" + board_subject
				+ ", board_content=" + board_content + ", board_writeTime=" + board_writeTime + ", board_readCount="
				+ board_readCount + ", board_file=" + board_file + ", rating=" + rating + "]";
	}	
	
}
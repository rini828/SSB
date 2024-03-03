package com.ssb.member.db;

import java.sql.Date;
import java.sql.Timestamp;

public class MemberDTO {
	
	private int member_id;
	private String member_user_id;
	private String member_pw;
	private String member_name;
	private Date member_birth;
	private String member_gender;
	private String member_email;
	private String member_phone;
	private Timestamp member_regdate;
	private int member_payment;
	private int member_point;
	private String member_grade;
	private String member_situation;
	private Timestamp member_outdate;
	private String member_agree;
	private String new_member_pw;
	
	public MemberDTO() {
	}
	
	
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public String getMember_user_id() {
		return member_user_id;
	}
	public void setMember_user_id(String member_user_id) {
		this.member_user_id = member_user_id;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public Date getMember_birth() {
		return member_birth;
	}
	public void setMember_birth(Date member_birth) {
		this.member_birth = member_birth;
	}
	public String getMember_gender() {
		return member_gender;
	}
	public void setMember_gender(String member_gender) {
		this.member_gender = member_gender;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	public Timestamp getMember_regdate() {
		return member_regdate;
	}
	public void setMember_regdate(Timestamp member_regdate) {
		this.member_regdate = member_regdate;
	}
	public int getMember_payment() {
		return member_payment;
	}
	public void setMember_payment(int member_payment) {
		this.member_payment = member_payment;
	}
	public int getMember_point() {
		return member_point;
	}
	public void setMember_point(int member_point) {
		this.member_point = member_point;
	}
	public String getMember_grade() {
		return member_grade;
	}
	public void setMember_grade(String member_grade) {
		this.member_grade = member_grade;
	}
	public String getMember_situation() {
		return member_situation;
	}
	public void setMember_situation(String member_situation) {
		this.member_situation = member_situation;
	}
	public Timestamp getMember_outdate() {
		return member_outdate;
	}
	public void setMember_outdate(Timestamp member_outdate) {
		this.member_outdate = member_outdate;
	}
	public String getMember_agree() {
		return member_agree;
	}
	public void setMember_agree(String member_agree) {
		this.member_agree = member_agree;
	}
	@Override
	public String toString() {
		return "MemberDTO [member_id=" + member_id + ", member_user_id=" + member_user_id + ", member_pw=" + member_pw
				+ ", member_name=" + member_name + ", member_birth=" + member_birth + ", member_gender=" + member_gender
				+ ", member_email=" + member_email + ", member_phone=" + member_phone + ", member_regdate="
				+ member_regdate + ", member_payment=" + member_payment + ", member_point=" + member_point
				+ ", member_grade=" + member_grade + ", member_situation=" + member_situation + ", member_outdate="
				+ member_outdate + ", member_agree=" + member_agree + "]";
	}


	// 회원정보 수정시 비밀번호 변경할 때 필요한 것! null 이어도 상관없음! 비번 변경때만 사용
	public String getNew_member_pw() {
		return new_member_pw;
	}


	public void setNew_member_pw(String new_member_pw) {
		this.new_member_pw = new_member_pw;
	}
	
	
	
	
}

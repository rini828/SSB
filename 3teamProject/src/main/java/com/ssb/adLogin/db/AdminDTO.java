package com.ssb.adLogin.db;

public class AdminDTO {

	private int admin_id;
	private String admin_user_id;
	private String admin_pw;
	private String admin_name;
	private int admin_authority;
	
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_user_id() {
		return admin_user_id;
	}
	public void setAdmin_user_id(String admin_user_id) {
		this.admin_user_id = admin_user_id;
	}
	public String getAdmin_pw() {
		return admin_pw;
	}
	public void setAdmin_pw(String admin_pw) {
		this.admin_pw = admin_pw;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public int getAdmin_authority() {
		return admin_authority;
	}
	public void setAdmin_authority(int admin_authority) {
		this.admin_authority = admin_authority;
	}

	@Override
	public String toString() {
		return "AdminDTO [admin_id=" + admin_id + ", admin_user_id=" + admin_user_id + ", admin_pw=" + admin_pw
				+ ", admin_name=" + admin_name + ", admin_authority=" + admin_authority + "]";
	}	
	
}
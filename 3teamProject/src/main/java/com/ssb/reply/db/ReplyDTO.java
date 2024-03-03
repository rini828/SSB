package com.ssb.reply.db;

import java.sql.Date;

public class ReplyDTO {

	private int reply_id;
	private int board_id;
	
	private String admin_user_id;
	private String member_user_id;
	
	private String reply_content;
	private Date reply_writeTime;
	
	
	public int getReply_id() {
		return reply_id;
	}
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	
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
	
	public String getMember_user_id() {
		return member_user_id;
	}
	public void setMember_user_id(String member_user_id) {
		this.member_user_id = member_user_id;
	}
	
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	
	public Date getReply_writeTime() {
		return reply_writeTime;
	}
	public void setReply_writeTime(Date reply_writeTime) {
		this.reply_writeTime = reply_writeTime;
	}
	
	
	@Override
	public String toString() {
		return "ReplyDTO [reply_id=" + reply_id + ", board_id=" + board_id + ", admin_user_id=" + admin_user_id
				+ ", member_user_id=" + member_user_id + ", reply_content=" + reply_content + ", reply_writeTime="
				+ reply_writeTime + "]";
	}
		
}
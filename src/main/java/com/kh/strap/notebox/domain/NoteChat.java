package com.kh.strap.notebox.domain;

import java.util.Date;

public class NoteChat {
	private int chatNo;
	private String senderId;
	private String senderNick;
	private String chatContents;
	private Date senderDate;
	private String chatStatus;
	
	
	public String getChatStatus() {
		return chatStatus;
	}
	public void setChatStatus(String chatStatus) {
		this.chatStatus = chatStatus;
	}
	public int getChatNo() {
		return chatNo;
	}
	public void setChatNo(int chatNo) {
		this.chatNo = chatNo;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getSenderNick() {
		return senderNick;
	}
	public void setSenderNick(String senderNick) {
		this.senderNick = senderNick;
	}
	public String getChatContents() {
		return chatContents;
	}
	public void setChatContents(String chatContents) {
		this.chatContents = chatContents;
	}
	public Date getSenderDate() {
		return senderDate;
	}
	public void setSenderDate(Date senderDate) {
		this.senderDate = senderDate;
	}
	
	@Override
	public String toString() {
		return "NoteChat [chatNo=" + chatNo + ", senderId=" + senderId + ", senderNick=" + senderNick
				+ ", chatContents=" + chatContents + ", senderDate=" + senderDate + ", chatStatus=" + chatStatus + "]";
	}
	
	
	

}

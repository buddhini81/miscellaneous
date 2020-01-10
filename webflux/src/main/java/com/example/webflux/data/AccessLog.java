package com.example.webflux.data;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="accesslog")
public class AccessLog {

	@Id
	private String id;
	
	private String user;
	
	private String ip;
	
	private String action;
	
	private Date date;
	

	public AccessLog() {
		
	}


	public AccessLog(String id, String user, String ip, String action, Date date) {
		this.id = id;
		this.user = user;
		this.ip = ip;
		this.action = action;
		this.date = date;
	}


	public AccessLog(String user, String ip, String action, Date date) {
		this.user = user;
		this.ip = ip;
		this.action = action;
		this.date = date;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "AccessLog [id=" + id + ", user=" + user + ", ip=" + ip + ", action=" + action + ", date=" + date + "]";
	}
	
}

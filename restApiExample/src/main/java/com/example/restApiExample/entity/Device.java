package com.example.restApiExample.entity;

public class Device {
	
	public String id; 
	public String no;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	
	@Override
	public String toString() {
		return "Device [id=" + id + ", no=" + no + "]";
	}
	
	
}

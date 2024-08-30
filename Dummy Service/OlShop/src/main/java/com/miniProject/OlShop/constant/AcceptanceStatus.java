package com.miniProject.OlShop.constant;

public enum AcceptanceStatus {
	
	ACC("ACC", "Accepted"),
	DCL("DCL", "Declined"),
	PND("PND", "Pending");
	
	private String code;
	private String name;
	
	AcceptanceStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getName() {
		return this.name;
	}

}

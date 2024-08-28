package com.miniProject.OlShop.constant;

public enum Roles {
	
	CU("CUS", "Customer"),
	SA("SAD", "Admin"),
	SP("SUP", "Supplier");
	
	private String code;
	private String name;
	
	Roles(String code, String name) {
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

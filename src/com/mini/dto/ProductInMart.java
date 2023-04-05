package com.mini.dto;

public class ProductInMart {
	private String martName;
	private int serialNumber;
	private int stock;
	
	public ProductInMart(String martName, int serialNumber, int stock) {
		super();
		this.martName = martName;
		this.serialNumber = serialNumber;
		this.stock = stock;
	}
	
	public String getMartName() {
		return martName;
	}
	
	public void setMartName(String martName) {
		this.martName = martName;
	}
	
	public int getSerialNumber() {
		return serialNumber;
	}
	
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	@Override
	public String toString() {
		return "ProductInMart [martName=" + martName + ", serialNumber=" + serialNumber + ", stock=" + stock + "]";
	}
	
}

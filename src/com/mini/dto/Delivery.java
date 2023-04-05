package com.mini.dto;

public class Delivery {
	private String deliveryNumber;
	private String martName;
	private String customerId;
	private String productName;
	private int price;
	private int stock;
	private int total;
	
	public Delivery(String deliveryNumber, String martName, String customerId, String productName, int price, int stock,
			int total) {
		super();
		this.deliveryNumber = deliveryNumber;
		this.martName = martName;
		this.customerId = customerId;
		this.productName = productName;
		this.price = price;
		this.stock = stock;
		this.total = total;
	}
	
	public String getDeliveryNumber() {
		return deliveryNumber;
	}
	
	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}
	
	public String getMartName() {
		return martName;
	}
	
	public void setMartName(String martName) {
		this.martName = martName;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	@Override
	public String toString() {
		return "Delivery [deliveryNumber=" + deliveryNumber + ", martName=" + martName + ", customerId=" + customerId
				+ ", productName=" + productName + ", price=" + price + ", stock=" + stock + ", total=" + total + "]";
	}
	
	
}

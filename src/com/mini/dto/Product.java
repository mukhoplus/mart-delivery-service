package com.mini.dto;

public class Product {
	private int serialNumber;
	private String name;
	private int price;
	
	public Product(int serialNumber, String name, int price) {
		super();
		this.serialNumber = serialNumber;
		this.name = name;
		this.price = price;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [serialNumber=" + serialNumber + ", name=" + name + ", price=" + price + "]";
	}

}

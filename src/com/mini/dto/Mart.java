package com.mini.dto;

import java.util.ArrayList;

public class Mart {
	private String name;
	private String address;
	private ArrayList<ProductInMart> productList;
	
	public Mart(String name, String address) {
		super();
		this.name = name;
		this.address = address;
		this.productList = new ArrayList<>();
	}
	
	public Mart(String name, String address, ArrayList<ProductInMart> productList) {
		super();
		this.name = name;
		this.address = address;
		this.productList = productList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ProductInMart> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<ProductInMart> productList) {
		this.productList = productList;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Mart [name=" + name + ", address=" + address + ", productList=" + productList + "]";
	}
	
}

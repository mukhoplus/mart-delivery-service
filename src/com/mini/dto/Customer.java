package com.mini.dto;

import java.util.ArrayList;

public class Customer {
	private String id;
	private String password;
	private String name;
	private String address;
	private ArrayList<ProductInMart> list;
	
	public Customer(String id, String password, String name, String address, ArrayList<ProductInMart> list) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.list = list;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<ProductInMart> getList() {
		return list;
	}

	public void setList(ArrayList<ProductInMart> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", password=" + password + ", name=" + name + ", address=" + address + "]";
	}
	
}

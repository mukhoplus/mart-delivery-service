package com.mini.client;

import com.mini.dao.impl.DeliveryServiceDAOImpl;
import com.mini.dto.Mart;

import config.ServerInfo;

public class DeliveryServiceTest {

	public static void main(String[] args) {
		DeliveryServiceDAOImpl ds = DeliveryServiceDAOImpl.getInstance();
		
		try {
			ds.saveMart(new Mart("mukho", "mukhodong"));
			ds.deleteMart("mukho");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	static {
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
			System.out.println("Server is running.");
		} catch(ClassNotFoundException e){
			System.out.println("Server is not running.");
		}
	}
}

package com.mini.client;

import com.mini.dao.impl.DeliveryServiceDAOImpl;
import com.mini.dto.Mart;

import config.ServerInfo;

public class DeliveryServiceTest {

	public static void main(String[] args) {
		DeliveryServiceDAOImpl ds = DeliveryServiceDAOImpl.getInstance();
		
		// 테스트 코드
		try {
			ds.saveMart(new Mart("묵호", "동해시"));
			ds.deleteMart("묵호");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	static {
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
			System.out.println("서버가 켜졌어요.");
		} catch(ClassNotFoundException e){
			System.out.println("서버가 안켜졌어요.");
		}
	}
}

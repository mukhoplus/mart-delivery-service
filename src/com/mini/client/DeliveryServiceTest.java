package com.mini.client;

import java.util.ArrayList;

import com.mini.dao.impl.DeliveryServiceDAOImpl;
import com.mini.dto.Mart;
import com.mini.dto.Product;
import com.mini.dto.ProductInMart;

import config.ServerInfo;

public class DeliveryServiceTest {

	public static void main(String[] args) {
		DeliveryServiceDAOImpl ds = DeliveryServiceDAOImpl.getInstance();
		
		try {
			//ds.saveMart(new Mart("mukho", "mukhodong"));
			//ds.deleteMart("mukho");
//			System.out.println(ds.getMartByName("mukho"));
//			System.out.println(ds.getMartByAddress("mukhodong"));
			//System.out.println(ds.getMartByName("mukhodong"));
//			ds.saveMart(new Mart("sl", "seolleung"));
//			ArrayList<Mart> allMarts = ds.getAllMarts();
//			for(Mart mart: allMarts)
//				System.out.println(mart);
			
			//ds.saveProductInMart("mukho1", 3, 1);
			//ds.saveProductInMart("mukho", 1, 5);
			//ds.saveProduct(new Product(3, "coffee", 2500));
			//ds.saveProduct(new Product(3, "coffee", 2500));
			
//			ArrayList<ProductInMart> pim = ds.getAllProductsInMartByName("mukho");
//			for(ProductInMart p: pim)
//				System.out.println(p);
//			
//			ds.updateProductInMart("mukho", 2, 4);
//			
//			pim = ds.getAllProductsInMartByName("mukho");
//			for(ProductInMart p: pim)
//				System.out.println(p);
			ds.getTotal();
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

package com.mini.client;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mini.dao.impl.DeliveryServiceDAOImpl;
import com.mini.dto.Product;
import com.mini.exception.NotExistException;

import config.ServerInfo;

public class DeliveryServiceTest {

	public static void main(String[] args) {
		DeliveryServiceDAOImpl ds = DeliveryServiceDAOImpl.getInstance();

		// �׽�Ʈ �ڵ�
//		try {
//			ds.saveMart(new Mart("mukho", "mukhodong"));
//			ds.deleteMart("mukho");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}

		
		
		// Product
		//saveProduct / deleteProduct
//		try {
//			ds.saveProduct(new Product(777, "CocaCola", 1000));
//			ds.deleteProduct(777);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}

		//updateProduct
//		try {
//			ds.updateProduct(new Product(777, "PepsiCola", 1100));
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}

		try {
			ds.getProductBySerialNumber(777);
		}  catch (NotExistException | SQLException e) {
			System.out.println(e.getMessage());
		}
		

		try {
			ArrayList<Product> list = ds.getAllProducts();
			for (Product c : list)
				System.out.println(c);
		} catch (Exception e) {

		}

	}

	static {
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
			System.out.println("Server is running.");
		} catch (ClassNotFoundException e) {
			System.out.println("Server is not running.");
		}
	}
}

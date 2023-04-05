package com.mini.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mini.dao.DeliveryServiceDAO;
import com.mini.dto.Customer;
import com.mini.dto.Mart;
import com.mini.dto.Product;
import com.mini.dto.ProductInMart;
import com.mini.exception.CannotUpdateException;
import com.mini.exception.DuplicateException;
import com.mini.exception.NotAvailableException;
import com.mini.exception.NotExistException;

import config.ServerInfo;

public class DeliveryServiceDAOImpl implements DeliveryServiceDAO {
	// Singleton
	private static DeliveryServiceDAOImpl dao = new DeliveryServiceDAOImpl();
	private DeliveryServiceDAOImpl() {}
	public static DeliveryServiceDAOImpl getInstance() {
		return dao;
	}
	
	@Override
	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASS);
		System.out.println("Database is connected.");
		return conn;
	}

	@Override
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		if(ps != null) ps.close();
	    if(conn != null) conn.close();
	}

	@Override
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		if(rs != null) rs.close();
	    closeAll(ps, conn);
	}

	// Customer
	@Override
	public void saveCustomer(Customer customer) throws SQLException, DuplicateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomer(String id) throws SQLException, NotExistException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCustomer(Customer customer) throws SQLException, NotExistException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer getCustomerById(String id) throws SQLException, NotExistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Customer> getCustomersByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Customer> getAllCustomer() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	// Mart
	private boolean isExistMart(String name, Connection conn) throws SQLException {
		String sql = "SELECT name FROM mart WHERE name=?";
		
	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setString(1, name);
	    ResultSet rs = ps.executeQuery();
	    
		return rs.next();
	}
	
	@Override
	public void saveMart(Mart mart) throws SQLException, DuplicateException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = getConnect();
			if(isExistMart(mart.getName(), conn)) throw new DuplicateException("A mart with that name already exists.");
			
			String query = "INSERT INTO customer VALUES(?, ?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, mart.getName());
			ps.setString(2, mart.getAddress());
			
			System.out.println(ps.executeUpdate() + "-row is saved.");
		} finally {
			closeAll(ps, conn);
		}
	}

	@Override
	public void deleteMart(String name) throws SQLException, NotExistException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = getConnect();
			if(!isExistMart(name, conn)) throw new NotExistException("There is no mart with that name.");
			
			String query = "DELETE FROM mart WHERE name=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, name);

			System.out.println(ps.executeUpdate() + "-row is deleted.");
		} finally {
			closeAll(ps, conn);
		}
	}

	@Override
	public Mart getMartByName(String name) throws SQLException, NotExistException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Mart mart = null;
		
		try {
			conn = getConnect();
			if(!isExistMart(name, conn)) throw new NotExistException("There is no mart with that name.");
			
			String query = "SELECT name, address FROM mart WHERE name=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, name);

			rs = ps.executeQuery();
			while(rs.next()) {
				mart = new Mart(rs.getString("name"), rs.getString("address"));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		
		return mart;
	}

	@Override
	public Mart getMartByAddress(String address) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Mart mart = null;
		
		try {
			conn = getConnect();
			
			String query = "SELECT name, address FROM mart WHERE address=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, address);

			rs = ps.executeQuery();
			while(rs.next()) {
				mart = new Mart(rs.getString("name"), rs.getString("address"));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		
		return mart;
	}

	@Override
	public ArrayList<Mart> getAllMarts() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Mart> martList = new ArrayList<>();
		
		try {
			conn = getConnect();
			
			String query = "SELECT name, address FROM mart";
			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();
			while(rs.next()) {
				martList.add(new Mart(rs.getString("name"), rs.getString("address")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		
		return martList;
	}

	// Product
	@Override
	public void saveProduct(Product product) throws SQLException, DuplicateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(int serialNumber) throws SQLException, NotExistException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProduct(Product product) throws SQLException, NotExistException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product getProductBySerialNumber(int serialNumber) throws SQLException, NotExistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Product> getAllProducts() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void saveProductInList() throws SQLException, NotExistException, NotAvailableException {
		// TODO Auto-generated method stub
		
	}
	
	// ProductInList
	@Override
	public void saveProductInMart() throws SQLException, DuplicateException, NotExistException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProductInMart()
			throws SQLException, DuplicateException, NotExistException, CannotUpdateException {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public ArrayList<ProductInMart> getAllProductsInMartByName(String name) throws SQLException, NotAvailableException {
		// TODO Auto-generated method stub
		return null;
	}

	// Delivery
	@Override
	public void saveDelivery(String delivaryNumber, String martName, String customerName, ArrayList<ProductInMart> list)
			throws SQLException, NotExistException {
		// TODO Auto-generated method stub
		
	}

}

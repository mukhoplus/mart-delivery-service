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
	private boolean isExistCustomer(String id, Connection conn) throws SQLException{
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	        
	    String query = "SELECT id FROM customer WHERE id=?";
	    ps = conn.prepareStatement(query);
	    ps.setString(1, id);
	    rs = ps.executeQuery();
	        
	    return rs.next();
	}
	
	@Override
	public void saveCustomer(Customer customer) throws SQLException, DuplicateException {
		Connection conn = null;
	    PreparedStatement ps = null;
	    try {
	        conn = getConnect();
	        
	        if(isExistCustomer(customer.getId(),conn)) throw new DuplicateException("A Customer with that id already exists.");
	        
	        String query = "INSERT INTO customer(id, password, name, address) VALUES(?,?,?,?)";
	        ps=conn.prepareStatement(query);
	        ps.setString(1, customer.getId());
	        ps.setString(2, customer.getPassword());
	        ps.setString(3, customer.getName());
	        ps.setString(4, customer.getAddress());
	            
	        System.out.println(ps.executeUpdate() + "-row is saved.");
	        
	    }finally {
	        closeAll(ps, conn);
	    }
	}

	@Override
	public void deleteCustomer(String id) throws SQLException, NotExistException {
		Connection conn = null;
	    PreparedStatement ps = null;
	    try {
	        conn = getConnect();
	        if(!isExistCustomer(id,conn)) throw new NotExistException("There is no customer with that id.");
	        String query = "DELETE FROM customer WHERE id=?";
	        ps=conn.prepareStatement(query);
	        ps.setString(1, id);
	    
	        System.out.println(ps.executeUpdate() + "-row is deleted.");
	        
	    }finally {
	        closeAll(ps, conn);
	    }
	}

	@Override
	public void updateCustomer(Customer customer) throws SQLException, NotExistException {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    try {
	        conn = getConnect();
	        if(!isExistCustomer(customer.getId(),conn)) throw new NotExistException("There is no customer with that id.");
	        String query = "UPDATE customer SET password = ?, name = ?, address = ? WHERE id = ?";
	        ps=conn.prepareStatement(query);
	        ps.setString(1, customer.getPassword());
	        ps.setString(2, customer.getName());
	        ps.setString(3, customer.getAddress());
	        ps.setString(4, customer.getId());
	            
	        System.out.println(ps.executeUpdate() + "-row is updated.");
	        
	    }finally {
	        closeAll(ps, conn);
	    }
	}

	@Override
	public Customer getCustomerById(String id) throws SQLException, NotExistException {
		Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Customer customer = null;
	    
	    try {
	        conn = getConnect();
	        if(!isExistCustomer(id, conn)) throw new NotExistException("There is no customer with that id.");
	        
	        String query = "SELECT id, password, name, address FROM customer WHERE id=?";
	        ps = conn.prepareStatement(query);
	        ps.setString(1, id);

	        rs = ps.executeQuery();
	        while(rs.next()) {
	             customer = new Customer(rs.getString("id"), rs.getString("password"), rs.getString("name"), rs.getString("address"));
	        }
	    } finally {
	        closeAll(rs, ps, conn);
	    }
	    
	    return customer;
	}

	@Override
	public ArrayList<Customer> getCustomersByName(String name) throws SQLException {
		Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    ArrayList<Customer> customerList = new ArrayList<>();
	    
	    try {
	        conn = getConnect();
	        
	        String query = "SELECT id, password, name, address FROM customer WHERE name = ?";
	        ps = conn.prepareStatement(query);
	        ps.setString(1, name);
	        rs = ps.executeQuery();
	        while(rs.next()) {
	            customerList.add(new Customer(rs.getString("id"), rs.getString("password"), rs.getString("name"), rs.getString("address")));
	        }
	    } finally {
	        closeAll(rs, ps, conn);
	    }
	    
	    return customerList;
	}

	@Override
	public ArrayList<Customer> getAllCustomer() throws SQLException {
		Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    ArrayList<Customer> customerList = new ArrayList<>();
	    
	    try {
	        conn = getConnect();
	        
	        String query = "SELECT id, password, name, address FROM customer";
	        ps = conn.prepareStatement(query);

	        rs = ps.executeQuery();
	        while(rs.next()) {
	            customerList.add(new Customer(rs.getString("id"), rs.getString("password"), rs.getString("name"), rs.getString("address")));
	        }
	    } finally {
	        closeAll(rs, ps, conn);
	    }
	    
	    return customerList;
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
			
			String query = "INSERT INTO mart VALUES(?, ?)";
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
	private boolean isExistProduct(int serialNumber, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT SERIAL_NUMBER FROM product WHERE SERIAL_NUMBER=?";

		ps = conn.prepareStatement(sql);
		ps.setInt(1, serialNumber);
		rs = ps.executeQuery();

		return rs.next();
	}
	
	@Override
	public void saveProduct(Product product) throws SQLException, DuplicateException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnect();
			if (!isExistProduct(product.getSerialNumber(), conn)) {
				String query = "INSERT INTO product(SERIAL_NUMBER, name, price) VALUES(?,?,?)";

				ps = conn.prepareStatement(query);
				ps.setInt(1, product.getSerialNumber());
				ps.setString(2, product.getName());
				ps.setInt(3, product.getPrice());

				System.out.println(ps.executeUpdate() + "-row is saved.");

			} else {
				throw new DuplicateException("이미 존재하는 상품입니다.");
			}
		} finally {
			closeAll(ps, conn);
		}
	}

	@Override
	public void deleteProduct(int serialNumber) throws SQLException, NotExistException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnect();
			if (isExistProduct(serialNumber, conn)) {
				String query = "DELETE product WHERE SERIAL_NUMBER=?";

				ps = conn.prepareStatement(query);
				ps.setInt(1, serialNumber);
				System.out.println(ps.executeUpdate() + "-row is deleted.");
			} else {
				throw new NotExistException("삭제할 상품이 존재하지 않습니다.");
			}
		} finally {
			closeAll(ps, conn);
		}
	}

	@Override
	public void updateProduct(Product product) throws SQLException, NotExistException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnect();
			if (isExistProduct(product.getSerialNumber(), conn)) {
				String query = "UPDATE product SET name=?, price=? WHERE SERIAL_NUMBER=?";
				ps = conn.prepareStatement(query);

				ps.setString(1, product.getName());
				ps.setInt(2, product.getPrice());
				ps.setInt(3, product.getSerialNumber());

				System.out.println(ps.executeUpdate() + " row UPDATE OK");

			} else {
				throw new NotExistException("수정할 상품이 존재하지 않습니다.");
			}
		} finally {
			closeAll(ps, conn);
		}
	}

	@Override
	public Product getProductBySerialNumber(int serialNumber) throws SQLException, NotExistException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product product = null;
		try {
			conn = getConnect();
			String query = "SELECT SERIAL_NUMBER, name, price FROM product WHERE SERIAL_NUMBER=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, serialNumber);
			rs = ps.executeQuery();
			if (rs.next()) {
				product = new Product(serialNumber, rs.getString("name"), rs.getInt("price"));
			} else {
				throw new NotExistException("");
			}
		} finally {
			closeAll(ps, conn);
		}

		return product;
	}

	@Override
	public ArrayList<Product> getAllProducts() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Product> productList = new ArrayList<>();

		try {
			conn = getConnect();
			String query = "SELECT SERIAL_NUMBER, name, price FROM product";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				productList.add(new Product(rs.getInt("SERIAL_NUMBER"), rs.getString("name"), rs.getInt("price")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}

		return productList;
	}
	
	// ProductInList
	private boolean isExistProductInMart(String name, int serialNumber, Connection conn) throws SQLException {
		String query = "SELECT stock FROM productinmart WHERE mart_name=? AND serial_number=?";
		
	    PreparedStatement ps = conn.prepareStatement(query);
	    ps.setString(1, name);
	    ps.setInt(2, serialNumber);
	    ResultSet rs = ps.executeQuery();
	    
		return rs.next();
	}
	
	@Override
	public void saveProductInMart(String martName, int serialNumber, int stock) throws SQLException, DuplicateException, NotExistException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = getConnect();
			
			if(!isExistMart(martName, conn)) throw new NotExistException("There is no mart with that name.");
			//if(isExistProduct(serialNumber, conn)) throw new NotExistException("There is no product with that name.");
			if(isExistProductInMart(martName, serialNumber, conn)) throw new DuplicateException("A product already exists in mart.");
			
			String query = "INSERT INTO productinmart VALUES(?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, martName);
			ps.setInt(2, serialNumber);
			ps.setInt(3, stock);
		
			System.out.println(ps.executeUpdate() + "-row is saved.");
		} finally {
			closeAll(ps, conn);
		}
	}
	
	private Integer getStockByNameAndSerialNumber(String martName, int serialNumber, Connection conn) throws SQLException{
		String query = "SELECT stock FROM productinmart WHERE mart_name=? AND serial_number=?";
		
	    PreparedStatement ps = conn.prepareStatement(query);
	    ps.setString(1, martName);
	    ps.setInt(2, serialNumber);
	    ResultSet rs = ps.executeQuery();
	    int stock = 0;
	    while(rs.next()) {
	    	stock = rs.getInt("stock");
	    }
	    
	    return stock;
	}
	
	private boolean canUpdateStock(int stock, int stockDiff) {
		if(stock < stockDiff) return false;
		if(stock - stockDiff < 0) return false;
		return true;
	}
	
	// 사용자가 구매 확정시 재고를 감소시키는 함수
	@Override
	public void updateProductInMart(String martName, int serialNumber, int stockDiff)
			throws SQLException, DuplicateException, NotExistException, CannotUpdateException, NotAvailableException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = getConnect();
			
			if(!isExistMart(martName, conn)) throw new NotExistException("There is no mart with that name.");
			if(!isExistProductInMart(martName, serialNumber, conn)) throw new NotExistException("There is no product in mart with that name.");
			
			int stock = getStockByNameAndSerialNumber(martName, serialNumber, conn);
			if(!canUpdateStock(stock, stockDiff)) throw new NotAvailableException("Unvailable to change.");
			
			String query = "Update productinmart SET stock=? WHERE mart_name=? AND serial_number=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, stock-stockDiff);
			ps.setString(2, martName);
			ps.setInt(3, serialNumber);
			
			System.out.println(ps.executeUpdate() + "-row is updated.");
		} finally {
			closeAll(ps, conn);
		}
	}
	
	@Override
	public ArrayList<ProductInMart> getAllProductsInMartByName(String name) throws SQLException, NotExistException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProductInMart> productInMartList = new ArrayList<>();
		
		try {
			conn = getConnect();
			
			if(!isExistMart(name, conn)) throw new NotExistException("There is no mart with that name.");
			
			String query = "SELECT * FROM productinmart WHERE mart_name=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, name);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				productInMartList.add(new ProductInMart(rs.getString("mart_name"), rs.getInt("serial_number"), rs.getInt("stock")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return productInMartList;
	}

	// Delivery
	@Override
	public void saveDelivery(String delivaryNumber, String martName, String customerName, ArrayList<ProductInMart> list)
			throws SQLException, NotExistException {
		// TODO Auto-generated method stub
		
	}

}

package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Product;

public class ProductDao {
	private Connection connection;
	private static final String SQL_SELECT_ALL = "SELECT product_id, product_name, price FROM products ORDER BY product_id";
	private static final String SQL_INSERT_USER = "INSERT INTO products(product_name, price) VALUES(?, ?)";
	private static final String SQL_SELECT_WHERE_PRODUCT_ID = "SELECT * FROM products WHERE product_id = ?";
	private static final String SQL_SELECT_WHERE_PRODUCT_NAME = "SELECT * FROM products WHERE product_name = ?";
	private static final String SQL_SELECT_WHERE_PRICE = "SELECT * FROM products WHERE price = ?";
	private static final String SQL_SELECT_WHERE_PRODUCT_NAME_AND_PRICE = "SELECT * FROM products WHERE product_name = ? AND price = ?";
	private static final String SQL_DELETE_WHERE_PRODUCT_ID = "DELETE FROM products WHERE product_id = ?";
	private static final String SQL_UPDATE_WHERE_PRODUCT_ID = "UPDATE products SET product_name = ?, price = ? WHERE product_id = ?";
	public ProductDao(Connection connection) {
		this.connection = connection;
	}
	
	public List<Product> findAll() {
		  List<Product> list = new ArrayList<Product>();

	        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                Product u = new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getInt("price"));
	                list.add(u);
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	        return list;
	}
	
	public Product findById(int productId) {
    	try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_WHERE_PRODUCT_ID)) {
    		stmt.setInt(1, productId);
    		ResultSet rs = stmt.executeQuery();
    		
    		if(rs.next()) {
    			return new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getInt("price"));
    		}
    	} catch (SQLException e) {
    		throw new RuntimeException(e);
    	}
    	
        return null;
    }
	
	public List<Product> findByName(String name) {
		List<Product> list = new ArrayList<Product>();
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_WHERE_PRODUCT_NAME)) {
        	stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product u = new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getInt("price"));
                list.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
	}
	
	public List<Product> findByPrice(int price) {
		List<Product> list = new ArrayList<Product>();
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_WHERE_PRICE)) {
        	stmt.setInt(1, price);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product u = new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getInt("price"));
                list.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
	}
	
	public List<Product> findByNameAndPrice(String name, int price) {
		List<Product> list = new ArrayList<Product>();

        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_WHERE_PRODUCT_NAME_AND_PRICE)) {
        	stmt.setString(1, name);
        	stmt.setInt(2, price);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product u = new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getInt("price"));
                list.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
	}
	
	public void register(Product product) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT_USER)) {
    		stmt.setString(1, product.getProduct_name());
    		stmt.setInt(2, product.getPrice());
    		
    		stmt.executeUpdate();
    	} catch (SQLException e) {
    		throw new RuntimeException(e);
    	}
	}
	
	public int delete(int id) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_DELETE_WHERE_PRODUCT_ID)) {
    		stmt.setInt(1, id);
    		return stmt.executeUpdate();
    	} catch (SQLException e) {
    		throw new RuntimeException(e);
    	}
	}
	
	public int update(int id, String name, int price) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE_WHERE_PRODUCT_ID)) {
    		stmt.setString(1, name);
    		stmt.setInt(2, price);
    		stmt.setInt(3, id);
    		
    		return stmt.executeUpdate();
    	} catch (SQLException e) {
    		throw new RuntimeException(e);
    	}
	}
}

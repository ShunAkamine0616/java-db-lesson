package main;

import java.sql.Connection;
import java.util.List;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class Main {
	private static Connection connection;
	private static ProductDao productDao;
	public static void main(String args[]) throws Exception{
		connection = DbUtil.getConnection();

		productDao = new ProductDao(connection);
 
		Product newProduct = new Product("ボールペン", 120);
		productDao.register(newProduct);

		List<Product> list = productDao.findAll();
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println("product_id:" + list.get(i).getProduct_id() + "，product_name:" + list.get(i).getProduct_name() + "，price:" + list.get(i).getPrice());
		}
	
	}
}

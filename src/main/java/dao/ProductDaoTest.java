package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Product;
import util.DbUtil;

public class ProductDaoTest {
	private Connection connection;
	private ProductDao productDao;
	
	@BeforeEach
	public void setUp() throws Exception {
		connection = DbUtil.getConnection();
		connection.setAutoCommit(false);

		try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM products")) {
			stmt.executeUpdate();
		}

		try (Statement stmt = connection.createStatement()) {
			stmt.addBatch("INSERT INTO users(product_name, price) VALUES ('鉛筆', 60)");
			stmt.addBatch("INSERT INTO users(product_name, price) VALUES ('消しゴム', '100')");
			stmt.executeBatch();
		}

		productDao = new ProductDao(connection);
	}

	@AfterEach
	public void tearDown() throws Exception {
		connection.rollback();
	}

	@Test
	public void findAllで全件取得できる() {
		List<Product> list = productDao.findAll();
		assertEquals(2, list.size());

		Product p = list.get(0);
		assertEquals(Integer.valueOf(1), p.getProduct_id());
		assertEquals("鉛筆", p.getProduct_name());
		assertEquals(Integer.valueOf(60), p.getPrice());
		
		p = list.get(1);
		assertEquals(Integer.valueOf(2), p.getProduct_id());
		assertEquals("消しゴム", p.getProduct_name());
		assertEquals(Integer.valueOf(100), p.getPrice());
	}

    @Test
    public void findAllはデータがないと空のリストを返す() throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM users")) {
            stmt.executeUpdate();
        }

        List<Product> list = productDao.findAll();
        assertEquals(0, list.size());
    }

	

	@Test
	public void insertでデータを登録できる() {
		Product newProduct = new Product("机", 10000);
		productDao.register(newProduct);

		Product getProduct = productDao.findById(3);
//		assertEquals(newProduct.getUserId(), getUser.getUserId());
		assertEquals(newProduct.getProduct_id(), getProduct.getProduct_id());
		assertEquals(newProduct.getProduct_name(), getProduct.getProduct_name());
		assertEquals(newProduct.getPrice(), getProduct.getPrice());
	}

	@Test
	public void insertで主キーが重複していると例外発生() {
		Product newProduct = new Product(1, "赤ペン", 160);
		assertThrows(RuntimeException.class, () -> productDao.register(newProduct));
	}



}

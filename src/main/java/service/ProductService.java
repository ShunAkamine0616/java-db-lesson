package service;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class ProductService {

    public Product authentication(int id) {
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao ProductDao = new ProductDao(conn);
            Product Product = ProductDao.findById(id);

            return Product;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Product> find() {
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao ProductDao = new ProductDao(conn);
            return ProductDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public void register(Product Product) {
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao ProductDao = new ProductDao(conn);
            ProductDao.register(Product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

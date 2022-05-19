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
    
    

    public List<Product> findByName(String name) {
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao ProductDao = new ProductDao(conn);
            return ProductDao.findByName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
    
    
    public List<Product> findByPrice(int price) {
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao ProductDao = new ProductDao(conn);
            return ProductDao.findByPrice(price);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
    
    public List<Product> findByNameAndPrice(String name, int price) {
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao ProductDao = new ProductDao(conn);
            return ProductDao.findByNameAndPrice(name, price);
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

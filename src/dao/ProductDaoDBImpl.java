package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import model.Product;

public class ProductDaoDBImpl implements ProductDao {

	private DataSource ds;
	
	private String getProductByIdSQL = "SELECT * FROM products WHERE id=?";
	private String getAllProductsSQL = "SELECT * FROM products";
	private String deleteProductByIdSQL = "DELETE FROM products WHERE id=?";
	private String insertProductSQL = "INSERT INTO products(title,isbn_10) VALUES (?,?)";
	private String updateProductByIdSQL = 
			"UPDATE products SET title=?, isbn_10=?, description=?, img=?, sydney_stock_level=?, melbourne_stock_level=?, inventory_level=? WHERE id=?";
	
	Log log = LogFactory.getLog(ProductDaoDBImpl.class);
	
	public ProductDaoDBImpl() throws Exception {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			// Look up data source
			ds = (DataSource)envCtx.lookup("jdbc/Shopping");
		} catch(NamingException e) {
			throw new Exception("cannot find database.products");
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(getAllProductsSQL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				products.add(new Product(rs.getInt("id"),
						rs.getString("title"),
						rs.getString("isbn_10"),
						rs.getString("description"),
						rs.getString("img"),
						rs.getInt("sydney_stock_level"),
						rs.getInt("melbourne_stock_level"),
						rs.getInt("inventory_level")));
			}
			rs.close();
			ps.close();
			conn.close();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("cannot get products");
			return null;
		}
	}

	@Override
	public void addProduct(Product p) {
		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(insertProductSQL);
			ps.setString(1, p.getTitle());
			ps.setString(2, p.getIsbn_10());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			log.error("cannot insert product " + p);
		}
		
	}

	@Override
	public Product getProductById(int productId) {
		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(getProductByIdSQL);
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			Product p = null;
			if (rs.next()) {
				p = new Product(rs.getInt("id"),
						rs.getString("title"),
						rs.getString("isbn_10"),
						rs.getString("description"),
						rs.getString("img"),
						rs.getInt("sydney_stock_level"),
						rs.getInt("melbourne_stock_level"),
						rs.getInt("inventory_level"));
			}
			rs.close();
			ps.close();
			conn.close();
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("cannot get product: " + productId);
			return null;
		}
	}

	@Override
	public void updateProduct(Product p) {

		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(updateProductByIdSQL);
			ps.setString(1, p.getTitle());
			ps.setString(2, p.getIsbn_10());
			ps.setString(3, p.getDescription());
			ps.setString(4, p.getImgUrl());
			ps.setInt(5, p.getSydney_stock_level());
			ps.setInt(6, p.getMelbourne_stock_level());
			ps.setInt(7, p.getInventory_level());
			ps.setInt(8, p.getProductId());
			
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			log.error("cannot update product: " + p);
		}
	}

	@Override
	public void deleteProduct(Product p) {
		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(deleteProductByIdSQL);
			ps.setInt(1, p.getProductId());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			log.error("cannot delete product@" + p.getProductId());
		}
		
	}

}

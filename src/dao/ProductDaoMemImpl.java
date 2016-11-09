package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import model.Product;

/**
 * An in-memory implementation of the DAO
 * @author gyua0818
 *
 */
public class ProductDaoMemImpl implements ProductDao {

	private List<Product> products;
	private int lastId = 0;
	
	public ProductDaoMemImpl() {
		products = new ArrayList<Product>();
	}
	
	@Override
	public void addProduct(Product p) {
		lastId++;
		p.setProductId(lastId);
		products.add(p);
	}
	
	@Override
	public List<Product> getAllProducts() {

		return products;
	}

	@Override
	public Product getProductById(int productId) {

		for (Product prod : products) {
			if (prod.getProductId() == productId) {
				return prod;
			}
		}
		return null;
	}

	@Override
	public void updateProduct(Product p) {

		int prodId = p.getProductId();
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getProductId() == prodId) {
				products.set(i, p);
				break;
			}
		}
	}

	@Override
	public void deleteProduct(Product p) {
		
		int prodId = p.getProductId();
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getProductId() == prodId) {
				products.remove(i);
				break;
			}
		}
		
	}

}

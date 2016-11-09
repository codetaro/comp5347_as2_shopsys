package model;

import java.util.*;

public class Cart {

	private Map<Integer, CartItem> carts;
	
	public Cart() {
		carts = new HashMap<Integer, CartItem>();
	}
	
	public void addItem(Product p) {
		int productId = p.getProductId();
		CartItem ci = carts.get(productId);
		if (ci == null) {
			carts.put(productId, new CartItem(p, 1));
		} else {
			ci.increaseQuantity();
		}
	}
	
	public void removeItem(Product p) {
		int productId = p.getProductId();
		CartItem ci = carts.get(productId);
		if (ci != null) {
			ci.decreaseQuantity();
			if (ci.getQuantity() == 0) {
				carts.remove(productId);
			}
		}
	}
	
	public Collection<CartItem> getItems() {
		return carts.values();
	}
	
	public int getTotal() {
		int total = 0;
		for (CartItem ci : carts.values()) {
			total += ci.getQuantity();
		}
		return total;
	}
	
}

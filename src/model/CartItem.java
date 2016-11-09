package model;

public class CartItem {

	private Product product;
	private int quantity;
	
	/* Constructors */
	public CartItem() {}
	
	public CartItem(Product p, int q) {
		this.product = p;
		this.quantity = q;
	}

	/* Getters and Setters */
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void increaseQuantity() {
		quantity++;
	}
	
	public void decreaseQuantity() {
		quantity--;
	}
}

package model;

import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@XmlRootElement
public class Product {
	
	private int productId = -1;  //default id will be modified by the storage
	
	@NotEmpty(message="You must supply a title!")
	private String title;
	
	@NotEmpty(message="You must supply an ISBN!")
	private String isbn_10;
	
	private String description;
	private String imgUrl;
	
	@Min(value=0, message="Stock level should be a positive number!")
	private int sydney_stock_level;
	
	@Min(value=0, message="Stock level should be a positive number!")
	private int melbourne_stock_level;
	
	private int inventory_level;

	/* Constructors */
	public Product(int productId, String title, String isbn_10, String description, String imgUrl,
			int sydney_stock_level, int melbourne_stock_level, int stock_level) {
		this.productId = productId;
		this.title = title;
		this.isbn_10 = isbn_10;
		this.description = description;
		this.imgUrl = imgUrl;
		this.sydney_stock_level = sydney_stock_level;
		this.melbourne_stock_level = melbourne_stock_level;
		this.inventory_level = stock_level;
	}

	/* Getters and Setters */
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn_10() {
		return isbn_10;
	}

	public void setIsbn_10(String isbn_10) {
		this.isbn_10 = isbn_10;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getSydney_stock_level() {
		return sydney_stock_level;
	}

	public void setSydney_stock_level(int sydney_stock_level) {
		this.sydney_stock_level = sydney_stock_level;
	}

	public int getMelbourne_stock_level() {
		return melbourne_stock_level;
	}

	public void setMelbourne_stock_level(int melbourne_stock_level) {
		this.melbourne_stock_level = melbourne_stock_level;
	}

	
	public int getInventory_level() {
		return inventory_level;
	}

	public void setInventory_level(int stock_level) {
		this.inventory_level = stock_level;
	}

	// toString()
	public String toString() {
		return title + "@" + isbn_10;
	}
}

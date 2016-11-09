package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Package {
	
	private int package_id = -1;  // default id
	
	private String warehouse_add;
	private String delivery_add;
	private String items;
	private String status;
	private int tracking_num;
	
	/* Constructors */
	/**
	 * The default constructor must be supplied
	 * or a 500 server error will show when
	 * trying to retrieve objects through RESTful
	 * web service
	 */
	public Package() {}
	
	public Package(int package_id, String warehouse_add, String delivery_add, String items, String status,
			int tracking_num) {
		this.package_id = package_id;
		this.warehouse_add = warehouse_add;
		this.delivery_add = delivery_add;
		this.items = items;
		this.status = status;
		this.tracking_num = tracking_num;
	}

	public Package(String warehouse_add, String delivery_add, String items) {
		this.warehouse_add = warehouse_add;
		this.delivery_add = delivery_add;
		this.items = items;
	}

	/**
	 * Cannot retrieve tracking_num from shipping system,
	 * so generate a random 4 digits tracking_num from
	 * shopping system. The two Package model with the
	 * same name are slightly different actually.
	 * 
	 */
	public Package(String warehouse_add, String delivery_add, String items, int tracking_num) {
		this.warehouse_add = warehouse_add;
		this.delivery_add = delivery_add;
		this.items = items;
		this.tracking_num = tracking_num;
	}

	/* Getters & Setters */
	public int getPackage_id() {
		return package_id;
	}

	public void setPackage_id(int package_id) {
		this.package_id = package_id;
	}

	public String getWarehouse_add() {
		return warehouse_add;
	}

	public void setWarehouse_add(String warehouse_add) {
		this.warehouse_add = warehouse_add;
	}

	public String getDelivery_add() {
		return delivery_add;
	}

	public void setDelivery_add(String delivery_add) {
		this.delivery_add = delivery_add;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTracking_num() {
		return tracking_num;
	}

	public void setTracking_num(int tracking_num) {
		this.tracking_num = tracking_num;
	}

}

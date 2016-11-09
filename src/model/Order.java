package model;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@XmlRootElement
public class Order {

	private int orderId = -1;
	@NotEmpty(message="You must supply a delivery address")
	private String address;
	@NotEmpty(message="Shopping cart cannot be empty")
	private String items;
	private String status;
	private String user;
	private String tracking_num;
	
	/* Constructors */
	public Order() {}
	public Order(int orderId, String address, String items, String status, String user, String tracking_num) {
		this.orderId = orderId;
		this.address = address;
		this.items = items;
		this.status = status;
		this.user = user;
		this.tracking_num = tracking_num;
	}

	/* Getters and Setters */
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTracking_num() {
		return tracking_num;
	}
	public void setTracking_num(String tracking_num) {
		this.tracking_num = tracking_num;
	}
	
	// toString()
	public String toString() {
		return orderId + "@" + status;
	}
}

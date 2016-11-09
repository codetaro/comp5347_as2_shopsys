package dao;

import java.util.ArrayList;
import java.util.List;

import model.Order;

public interface OrderDao {

	public List<Order> getAllOrders();
	public ArrayList<Order> getOrdersByUser(String user);
	public void addOrder(Order o);
	public Order getOrderById(int orderId);
	public void updateOrder(Order o);
	public void deleteOrder(Order o);
}

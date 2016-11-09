package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import model.Order;

public class OrderDaoDBImpl implements OrderDao {

	private DataSource ds;
	
	private String getOrderByIdSQL = "SELECT * FROM orders WHERE id=?";
	private String getAllOrdersSQL = "SELECT * FROM orders";
	private String getOrdersByUser = "SELECT * FROM orders WHERE user=?";
	private String deleteOrderByIdSQL = "DELETE FROM orders WHERE id=?";
	private String insertOrderSQL = "INSERT INTO orders(address,items,user) VALUES (?,?,?)";
	private String updateOrderByIdSQL = 
			"UPDATE orders SET address=?,items=?,status=?,tracking_num=? WHERE id=?";
	
	Log log = LogFactory.getLog(OrderDaoDBImpl.class);
	
	public OrderDaoDBImpl() throws Exception {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds = (DataSource)envCtx.lookup("jdbc/Shopping");
		} catch(NamingException e) {
			throw new Exception("cannot find database.orders");
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public List<Order> getAllOrders() {

		List<Order> orders = new ArrayList<Order>();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(getAllOrdersSQL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				orders.add(new Order(rs.getInt("id"),
						rs.getString("address"),
						rs.getString("items"),
						rs.getString("status"),
						rs.getString("user"),
						rs.getString("tracking_num")));
			}
			rs.close();
			ps.close();
			conn.close();
			return orders;
		} catch(Exception e) {
			e.printStackTrace();
			log.error("cannot get orders");
			return null;
		}
	}
	
	@Override
	public ArrayList<Order> getOrdersByUser(String user) {
		
		ArrayList<Order> orders = new ArrayList<Order>();
		
		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(getOrdersByUser);
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				orders.add(new Order(rs.getInt("id"),
						rs.getString("address"),
						rs.getString("items"),
						rs.getString("status"),
						rs.getString("user"),
						rs.getString("tracking_num")));
			}
			rs.close();
			ps.close();
			conn.close();
			return orders;
		} catch(Exception e) {
			e.printStackTrace();
			log.error("cannot get orders for user:" + user);
			return null;
		}
	}

	@Override
	public void addOrder(Order o) {

		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(insertOrderSQL);
			ps.setString(1, o.getAddress());
			ps.setString(2, o.getItems());
			ps.setString(3, o.getUser());
			ps.executeUpdate();
			ps.clearBatch();
			conn.close();
		} catch(SQLException e) {
			log.error("cannot insert order " + o);
		}
	}

	@Override
	public Order getOrderById(int orderId) {

		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(getOrderByIdSQL);
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();
			Order o = null;
			if(rs.next()) {
				o = new Order(rs.getInt("id"),
						rs.getString("address"),
						rs.getString("items"),
						rs.getString("status"),
						rs.getString("user"),
						rs.getString("tracking_num"));
			}
			rs.close();
			ps.close();
			conn.close();
			return o;
		} catch(SQLException e) {
			e.printStackTrace();
			log.error("cannot get order: " + orderId);
			return null;
		}
	}

	@Override
	public void updateOrder(Order o) {

		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(updateOrderByIdSQL);
			ps.setString(1, o.getAddress());
			ps.setString(2, o.getItems());
			ps.setString(3, o.getStatus());
			ps.setString(4, o.getTracking_num());
			ps.setInt(5, o.getOrderId());
			
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch(SQLException e) {
			log.error("cannot update order: " + o);
		}
	}

	@Override
	public void deleteOrder(Order o) {

		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(deleteOrderByIdSQL);
			ps.setInt(1, o.getOrderId());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch(SQLException e) {
			log.error("cannot delete order@" + o.getOrderId());
		}
	}
	
}

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.fabric.Response;

import dao.DaoFactory;
import dao.OrderDao;
import dao.ProductDao;
import model.Order;
import model.Package;
import model.Product;
import rest.client.PackageRestClient;

@Controller
@RequestMapping("/products")
public class ProductController {

	private final ProductDao pdao = DaoFactory.getInstance().getProductDao();
	private final OrderDao odao = DaoFactory.getInstance().getOrderDao();
	
	@RequestMapping(method = RequestMethod.GET)
	public String listAll(Model model) {
		
		List<Product> products = pdao.getAllProducts();
		model.addAttribute("products", products);
		
		List<Order> orders = odao.getAllOrders();
		model.addAttribute("orders", orders);
		
		return "productlist";
	}

	@RequestMapping("/pack/{orderId}")
	public String pack(@PathVariable int orderId, Model model) {
		
		Order order = odao.getOrderById(orderId);
		model.addAttribute("order", order);
		
		String[] items = order.getItems().split("\t");
		int productId;
		Product product;
		ArrayList<Product> products = new ArrayList<Product>();
		for (String item : items) {
			productId = Integer.parseInt(item.split("\\^")[0]);
			product = pdao.getProductById(productId);
			products.add(product);
		}
		model.addAttribute("products", products);
		
		return "package";
	}
	
	@RequestMapping("/ship")
	public String ship(@RequestParam("warehouse_add") String warehouse_add,
			@RequestParam("delivery_add") String delivery_add,
			@RequestParam("items") String items,
			@RequestParam("orderId") int orderId,
			@RequestParam("ship") String ship, Model model) {
		
		PackageRestClient prc = new PackageRestClient();
		
		
		int tracking_num = (int)(1000 + Math.random() * 8999);
		Package pac = new Package(warehouse_add, delivery_add, items, tracking_num);
		prc.addPackage(pac);
		
		// Set order status & tracking_num accordingly
		Order order = odao.getOrderById(orderId);
		if (ship.equals("Ship Partial")) {
			order.setStatus("partially shipped");
		} else {
			order.setStatus("shipped");
		}
		String trackingNum = order.getTracking_num();
		if (trackingNum == null) {
			order.setTracking_num(Integer.toString(tracking_num));
		} else {
			order.setTracking_num(trackingNum + "\t" + tracking_num);
		}
		odao.updateOrder(order);
		
		// Change warehouse stock level accordingly 
		int productId;
		int quantity;
		Product product;
		int sydney_stock_level;
		int melbourne_stock_level;
		String[] itemList = items.split("\t");
		for (String item : itemList) {
			productId = Integer.parseInt(item.split("\\^")[0]);
			quantity = Integer.parseInt(item.split("\\^")[1]);
			product = pdao.getProductById(productId);
			if (warehouse_add.equals("sydney")) {
				sydney_stock_level = product.getSydney_stock_level();
				product.setSydney_stock_level(sydney_stock_level - quantity);
			} else {
				melbourne_stock_level = product.getMelbourne_stock_level();
				product.setMelbourne_stock_level(melbourne_stock_level - quantity);
			}
			pdao.updateProduct(product);
		}
		
		List<Product> products = pdao.getAllProducts();
		model.addAttribute("products", products);
		
		List<Order> orders = odao.getAllOrders();
		model.addAttribute("orders", orders);
		
		return "productlist";
	}
}

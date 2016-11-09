package controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import dao.DaoFactory;
import dao.OrderDao;
import dao.ProductDao;
import model.Cart;
import model.CartItem;
import model.Order;
import model.Product;
import model.Package;
import rest.client.PackageRestClient;

@Controller
@SessionAttributes("cart")
public class OrderController {
	
	private final ProductDao pdao = DaoFactory.getInstance().getProductDao();
	private final OrderDao odao = DaoFactory.getInstance().getOrderDao();

	@RequestMapping("/makeOrder")
	public String makeOrder(@ModelAttribute("cart") Cart cart, 
			@Valid @ModelAttribute("order") Order order, BindingResult result) {
		
		if(result.hasErrors()) {
			return "cart_ship";
		} else {
			odao.addOrder(order);
			
			// update products inventory
			int id;
			int quantity;
			Product p;
			int stockLevel;
			for (CartItem ci : cart.getItems()) {
				id = ci.getProduct().getProductId();
				quantity = ci.getQuantity();
				p = pdao.getProductById(id);
				stockLevel = p.getInventory_level();
				p.setInventory_level(stockLevel - quantity);
				pdao.updateProduct(p);
			}
			
			// clear shopping cart
			cart.getItems().clear();
			return "thankyou";
		}
	}

	@RequestMapping("/checkOrder")
	public String checkOrder(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user = auth.getName();
		
		ArrayList<Order> orders = odao.getOrdersByUser(user);
		model.addAttribute("orders", orders);
		
		PackageRestClient prc = new PackageRestClient();
		ArrayList<Package> pacs = new ArrayList<Package>();
		String trackingNumList;
		String[] tracking_num_list;
		for (Order order : orders) {
			trackingNumList = order.getTracking_num();
			if (trackingNumList != null) {
				tracking_num_list = trackingNumList.split("\t");
				for (String tracking_num : tracking_num_list) {
					pacs.add(prc.getPackageByTrackingNum(tracking_num));
				}
			}
		}
		model.addAttribute("pacs", pacs);
		
		return "orderList_nopt";
	}
}

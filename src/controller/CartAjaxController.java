package controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import model.Cart;
import model.CartItem;
import model.Order;
import model.Product;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import dao.DaoFactory;
import dao.OrderDao;
import dao.ProductDao;

@Controller
@RequestMapping("/cartsAjax")
@SessionAttributes("cart")
public class CartAjaxController {

	private final ProductDao pdao = DaoFactory.getInstance().getProductDao();
	private final OrderDao odao = DaoFactory.getInstance().getOrderDao();
	
	@ModelAttribute("cart")
	public Cart createCart() {
		return new Cart();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String show(Model model, @ModelAttribute("cart") Cart cart) {
		List<Product> products = pdao.getAllProducts();
		model.addAttribute("products", products);
		return "catalogueAjax";
	}
	
	@RequestMapping("/add/{productId}")
	public String add(@PathVariable int productId, @ModelAttribute("cart") Cart cart) {
		Product p = pdao.getProductById(productId);
		cart.addItem(p);
		return "cart_partial";
	}
	
	@RequestMapping("/remove/{productId}")
	public String remove(@PathVariable int productId, @ModelAttribute("cart") Cart cart) {
		Product p = pdao.getProductById(productId);
		cart.removeItem(p);
		return "cart_partial";
	}
	
	@RequestMapping("/checkOut")
	public String checkOut(Model model, @ModelAttribute("cart") Cart cart) {
		
		ArrayList<CartItem> info = new ArrayList<CartItem>();
		
		int id;
		int quantity;
		Product p;
		int stock_level;
		for (CartItem ci : cart.getItems()) {
			id = ci.getProduct().getProductId();
			quantity = ci.getQuantity();
			p = pdao.getProductById(id);
			stock_level = p.getInventory_level();
			if (quantity > stock_level) {
				info.add(new CartItem(p, stock_level));
			}
		}
		
		if (info.size() != 0) {
			model.addAttribute("info", info);
			return "cart_info";
		} else {
			Order order = new Order();
//			String title;
			StringBuilder sb = new StringBuilder();
			for (CartItem ci : cart.getItems()) {
				id = ci.getProduct().getProductId();
//				title = ci.getProduct().getTitle();
				quantity = ci.getQuantity();
				sb.append(id + "^" + quantity + "\t");
			}
			order.setItems(sb.toString());
			// set user
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String user = auth.getName();
			order.setUser(user);
			model.addAttribute("order", order);
			return "cart_ship";
		}
	}
	
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
			int stock_level;
			for (CartItem ci : cart.getItems()) {
				id = ci.getProduct().getProductId();
				quantity = ci.getQuantity();
				p = pdao.getProductById(id);
				stock_level = p.getInventory_level();
				p.setInventory_level(stock_level - quantity);
				pdao.updateProduct(p);
			}
			
			// clear shopping cart
			cart.getItems().clear();
			return "thankyou";
		}
	}
	
}

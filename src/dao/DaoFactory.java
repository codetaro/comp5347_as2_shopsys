package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import model.*;

/**
 * The Factory responsible for creating proper DAO
 * @author gyua0818
 *
 */
public class DaoFactory {

	private static DaoFactory df;
	private ProductDao pDao = null;
	private OrderDao oDao = null;
	static Log log = LogFactory.getLog(DaoFactory.class);
	String classname = this.getClass().getName();
	
	private DaoFactory() {}
	
	public static DaoFactory getInstance() {
		if (df == null) {
			df = new DaoFactory();
		}
		return df;
	}
	
	public ProductDao getProductDao() {
		if (pDao == null) {
			Properties properties = new Properties();
			try {
				properties.load(this.getClass().getResourceAsStream("/shopping.properties"));
				String className = properties.getProperty("dao.ProductDaoName");
				if (className != null) {
					pDao = (ProductDao)Class.forName(className).newInstance();
					log.info("Using " + className + " to get ProductInfo...");
				} else {
					log.info("Property not found, using default implementation.");
					System.out.println("Property not found, using default implementation.");
					pDao = new ProductDaoMemImpl();
				}
			} catch (Exception e) {
				log.info(e.getMessage());
				e.printStackTrace();
				pDao = new ProductDaoMemImpl();
				System.out.print("Exception, using default implementation!");
				return pDao;
			}
		}
		return pDao;
	}
	
	public OrderDao getOrderDao() {
		if(oDao == null) {
			Properties properties = new Properties();
			try {
				properties.load(this.getClass().getResourceAsStream("/shopping.properties"));
				String className = properties.getProperty("dao.OrderDaoName");
				oDao = (OrderDao)Class.forName(className).newInstance();
				log.info("Using" + className + " to get OrderInfo...");
			} catch(Exception e) {
				log.info(e.getMessage());
				e.printStackTrace();
			}
			return oDao;
		} else {
			return oDao;
		}
	}
	
	public static void main(String[] args) {

		DaoFactory df = DaoFactory.getInstance();
		OrderDao oDao = df.getOrderDao();
		List<Order> orders = oDao.getAllOrders();
	}
	
}

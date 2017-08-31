package products;

import java.util.HashMap;

public class ShoppingOrder {
	public HashMap<Product, Integer> orders;
	public static int idNum = 1;
	public int orderID;
	
	public ShoppingOrder(HashMap<Product, Integer> orders) {
		orderID = idNum;
		this.orders = orders;
		idNum++;
	}
	
}

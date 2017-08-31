package products;

import java.awt.Image;
import java.io.Serializable;

public class Invoice extends Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5820261726294575567L;
	private Integer quantity;
	private Double cost;

	public Invoice(String category, Image image, int iD, String description, Double price, Integer quantity) {
		super(category, image, iD, description, price);
		// TODO Auto-generated constructor stub
		this.quantity = quantity;
		this.cost = price * this.quantity;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getDescription() + ", quantity=" + quantity + ", price=" + this.getPrice() + ", cost=" + cost;
	}

}


/**
 * 
 */
package distributioncenter;

import java.io.Serializable;
import java.util.ArrayList;

import products.Product;

/**
 *
 */
public class DistributionCenter implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7352586202481046249L;
	String centerName;
	String centerCity;
	String centerID;
	ArrayList<Product> products;
	
	/**
	 * Creates a DistributionCenter object.
	 * @param centerName the name of this DistributionCenter
	 * @param centerCity the city of this DistributionCenter
	 * @param centerID the id of this DistributionCenter
	 * @param products the products that this DistributionCenter contains
	 */
	public DistributionCenter(String centerName, String centerCity, String centerID, ArrayList<Product> products){
		this.centerName = centerName;
		this.centerCity = centerCity;
		this.centerID = centerID;
		this.products = products;
	}
	
	/**
	 * Sets the name of this DistributionCenter.
	 * @param name name of this DistributionCenter
	 */
	public void setName(String name){
		this.centerName = name;
	}
	
	/**
	 * Sets the city of this DistributionCenter.
	 * @param city city of this DistributionCenter
	 */
	public void setCity(String city){
		this.centerCity = city;
	}
	
	/**
	 * Sets the ID of this DistributionCenter.
	 * @param ID ID of this DistributionCenter
	 */
	public void setID(String ID){
		this.centerID = ID;
	}
	
	/**
	 * Returns the name of this DistributionCenter.
	 * @return name of this DistributionCenter
	 */
	public String getName(){
		return this.centerName;
	}
	
	/**
	 * Returns the city of this DistributionCenter.
	 * @return city of this DistributionCenter
	 */
	public String getCity(){
		return this.centerCity;
	}
	
	/**
	 * Returns the ID of this DistributionCenter.
	 * @return ID of this DistributionCenter
	 */
	public String getID(){
		return this.centerID;
	}
	
	/**
	 * Returns all the products in this DistributionCenter.
	 * @return products
	 */
	public ArrayList<Product> getAllProducts(){
		return products;
	}
	
	//Distribution Center latest version
	
}



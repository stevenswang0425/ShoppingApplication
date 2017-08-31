package finalproject;

import java.io.EOFException;

import GUI.ChopDictionary;
import distributioncenter.DistributionCenterManager;
import products.Product;
import products.ProductManager;

public class PortalAPI {

	
	public static ProjectV1 project = new ProjectV1();
	
	
	public static void main(String[] args){
		
		ShoppingScreen sampleGUI;
		try {
			sampleGUI = new ShoppingScreen();
			sampleGUI.setVisible(true);
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		ChopDictionary<Product> c = new ChopDictionary<Product>(ProductManager.products);
//		
//		System.out.println(c.getList().size());
		
		
//		Project p = new Project();
//		int i = p.login("admin", "admin");
//		//p.addDistributionCenter("Chicago", i);
//		System.out.println(DistributionCenterManager.distributionmanagers);
//		try {
//			p.logout(i);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//System.out.println(ProductManager.products.toString());
		
		
	}
}
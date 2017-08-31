package GUI;

import java.util.ArrayList;
import java.util.Map;

import products.Category;
import products.CategoryManager;
import products.Product;
import products.ProductManager;
import users.User;
import users.UserManager;

public class GUIHelper {

	public GUIHelper(){
		
	}

	public static ArrayList<String> search(String key, ArrayList<String> words) {
		
		key = key.toLowerCase();
		ArrayList<String> related = new ArrayList<String>();
		
		for (int i = 0; i < words.size(); i++) {
			
			String word = words.get(i);
			word = word.toLowerCase();

			if (word.contains(key)) {
				related.add(word);
			}
		}
		return related;
	}	
	
	public ArrayList<Product> getLlist(){
		ArrayList<Product> productList = new ArrayList<Product>();
		
		
		for (Map.Entry<String, Product> productEntry: ProductManager.products.entrySet()){
			productList.add(productEntry.getValue());
		}
		return productList;
	}
	
	public static ArrayList<Product[][]> getProductList(){
		ArrayList<Product> productList = new ArrayList<Product>();
		
		
		for (Map.Entry<String, Product> productEntry: ProductManager.products.entrySet()){
			productList.add(productEntry.getValue());
		}
		int pagenumber = (int) Math.floor(productList.size()/6);
		Product[][] productArray = new Product[2][3];
		ArrayList<Product[][]> pageinproduct = new ArrayList<Product[][]>();
		while(!productList.isEmpty()){
		for (int row=0; row<2; row++){
			for (int col=0; col<productArray[row].length; col++){
				
				//productArray[row][col] = productList.remove(productList.size()-1);
			}
		}
		pageinproduct.add(productArray);
		productArray = new Product[2][3];
		}
		
		
		
		return pageinproduct;
	}
	
	public static ArrayList<User> getUserList(){
		ArrayList<User> userList = new ArrayList<User>();
		for (Map.Entry<String, User> userEntry: UserManager.userMap.entrySet()){
			userList.add(userEntry.getValue());
		}
		return userList;
	}
	
	public static ArrayList<Category> getCategoryList(){
		ArrayList<Category> categoryList = new ArrayList<Category>();
		for (Map.Entry<String, Category> categoryEntry: CategoryManager.categories.entrySet()){
			categoryList.add(categoryEntry.getValue());
		}
		return categoryList;
	}
	
	
	public static void searchAlgorithm(){
		
	}
	
	
	
	
}

package users;

import java.io.*;
import java.util.*;
import java.util.logging.*;

public class UserManager {
    public static Map<String, User> userMap = new HashMap<String, User>() {
    	/**
		 * 
		 */
		private static final long serialVersionUID = 1511118901563695556L;

		{	Administrator admin = new Administrator("admin", "admin");
			admin.setSessionID(-1);
			put("admin", admin);
			
    	
    	}
    };

	public static Logger logger = Logger.getLogger(UserManager.class.getName()); 
    private static Handler handler = new ConsoleHandler();
    public static List<Integer> activeSessionIDs = new ArrayList<Integer>();
    public User user;
    //getLogger assigns the logger to the class. 
    
    /**
     * Creates a new UserManager.
     */
    public UserManager(){
    	handler.setLevel(Level.ALL);
    	logger.setLevel(Level.ALL);	// setting level to ALL makes the program save everything blow by blow. 
    	logger.addHandler(handler); 
    }
	
    public String toString(){
    	String str = ""; 
    	for (Object value: userMap.values()){
    		str += value.toString(); 
    	}
    	return str; 
    }
    
    /**
     * Enables checking out of the specified Shopper's shopping cart.
     * Converts the Shopper into a Customer.
     * @param shopper Shopper whose shopping cart to check out
     * @param city city of specified Shopper
     * @param street street of specified Shopper
     */
//    public static void shopperCheckout(Shopper shopper, String city, String street) {
//    	HashMap<Order, Integer> orderMap = new HashMap<Order, Integer>();
//    	int id = idNum;
//    	Customer customer = null;
//    	
//    	for (Product p: shopper.getShoppingCart().keySet()) {
//    		Order newO = new Order(p.getCategory(), p.getImage(), p.getID(), p.getDescription(), p.getPrice());
//    		newO.quantityDistributionMap = p.quantityDistributionMap;
//    		
//    		customer = new Customer(shopper.getID(), shopper.getPassword(), city, street, id);
//    		
//    		newO.setCustomer(customer);
//    		customer.setCustomerID(id);
//    		orderMap.put(newO, shopper.getShoppingCart().get(p));
//    	}
//    	
//    	(customer).setOrderMap(orderMap);
//    	userMap.put(shopper.getID(), customer);
//    	
//    	idNum++;
//    }
    
    /**
     * Creates orders for newly generated Customer from their previous status
     * as Shopper.
     * @param customer Customer that was newly generated
     */
//    public static void customerCheckout(Customer customer) {
//    	HashMap<Order, Integer> orderMap = new HashMap<Order, Integer>();
//    	
//    	for (Product p: customer.getShoppingCart().keySet()) {
//    		Order newO = new Order(p.getCategory(), p.getImage(), p.getID(), p.getDescription(), p.getPrice());
//    		newO.setCustomer(customer);
//    		orderMap.put(newO, customer.getShoppingCart().get(p));
//    	}
//    	
//    	customer.setOrderMap(orderMap);
//    }
    
    /**
     * Attempts to login user into the app.
     * @param username username of user to be logged in 
     * @param password password of user to be logged in 
     * @return whether user is successfully logged in or not
     */
//    public static boolean userLogin(String username, String password) {
//    	Integer sessionID;
//    	User user = null;
//    	if (userMap.containsKey(username)) {
//    		user = userMap.get(username);
//    		
//    		if (password.equals(user.getPassword())) {
//    			Random random = new Random();
//    			Integer randomNum = new Integer(100000 + random.nextInt(900000));
//    			sessionID = randomNum;
//    			
//    			while (activeSessionIDs.contains(sessionID)) {
//    				sessionID = new Integer(100000 + random.nextInt(900000));
//    			}
//    			
//    			user.setSessionID(sessionID);
//    			activeSessionIDs.add(sessionID);
//    			return true;
//    		}
//    	}
//    	return false;
//    }
    
    /**
     * Attempts to logout user from the app.
     * @param username username of user to be logged out 
     * @param password password of user to be logged out 
     */
//    public static void userLogout(String username, String password) {
//    	activeSessionIDs.remove(user.getSessionID());
//    	user.setSessionID(-1);
//    }
    
    /**
     * Attempts to register an administrator into the app system.
     * @param username username of administrator to be registered
     * @param password password of administrator to be registered 
     * @return true or false, depending on if the administrator is successfully
     * registered or not
     */
//    public static boolean adminRegistration(String username, String password) {
//    	if (!username.equals("") && !password.equals("")) {
//    		if (!userMap.containsKey(username)) {
//        		userMap.put(username, new Administrator(username, password));
//        		logger.log(Level.FINE, "Add new Administrator " + username);
//        		return true;
//        	}
//    	}
//    	
//    	return false;
//    }
    
    /**
     * Attempts to register an shopper into the app system.
     * @param username username of shopper to be registered
     * @param password password of shopper to be registered 
     * @return true or false, depending on if the shopper is successfully
     * registered or not
     */
//    public static boolean shopperRegistration(String username, String password) {
//    	if (!username.equals("") && !password.equals("")) {
//    		if (!userMap.containsKey(username)) {
//        		userMap.put(username, new Shopper(username, password));
//        		logger.log(Level.FINE, "Add new Shopper " + username);
//        		return true;
//        	}
//    	}
//    	
//    	return false;
//    }
    
    /**
	 * @return the userMap
	 */
	public static Map<String, User> getUserMap() {
		return userMap;
	}

	/**
	 * @param userMap the userMap to set
	 */
	public static void setUserMap(Map<String, User> userMap) {
		UserManager.userMap = userMap;
	}

	/**
     * Serializes all the users in the app system.
     * @throws IOException
     */
    public static void saveToFile() throws IOException{
    	OutputStream file = new FileOutputStream("users");    
    	OutputStream buffer = new BufferedOutputStream(file); 
    	ObjectOutput writer = new ObjectOutputStream(buffer); 
    	
    	writer.writeObject(userMap);
    	writer.close();
    	buffer.close();
    	file.close();
    }
    
    /**
     * Deserializes all of the users in the byte file.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
	public static void readFromFile() throws EOFException, IOException, ClassNotFoundException{
    	
    	InputStream file = new FileInputStream("users"); 
    	InputStream buffer = new BufferedInputStream(file); 
    	ObjectInput reader = new ObjectInputStream(buffer); 
    	
    	userMap = (HashMap<String, User>) reader.readObject();    
    	file.close(); 
    	logger.log(Level.SEVERE, "Cannot read from input");
    }
    
}

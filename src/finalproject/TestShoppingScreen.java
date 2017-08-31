/**
 * 
 */
package finalproject;

import java.io.EOFException;
import java.io.IOException;

import users.UserManager;

/**
 *
 */
public class TestShoppingScreen {

	
	//This is the portal of our API
	public static ProjectV1 project = new ProjectV1();
	
	/**
	 * @param args
	 */
	
	public static void main(String[] args) throws EOFException {
		
		
		
		
		
		
		
//		boolean result = project.addUser("Anya A.","pass",true);		// the result must be true
//		System.out.println(result);
//		boolean result2 = project.addUser("Anya A.","pass",true);	// the result must be false; user "Ilir" exists already
//		System.out.println(result2);
//		
//		int mySession = project.login("Anya A.","pass"); 	// expected result is a positive integer (session id)
//		System.out.println(mySession);
//		
//		int catID = project.addCategory("PENCILS",mySession); 		// expected result is a positive integer, the category ID you have assigned to the new category "TSHIRT"
//		System.out.println(catID);
//		int someID = project.addCategory("PENCILS", mySession); 	// expected result is -1; "TSHIRT" exists
//		System.out.println(someID);
//		
//		boolean result3 = project.addUser("iii","pass",false);	// expected result true
//		System.out.println(result3);
//		UserManager u = new UserManager();
//		int johnnySession = project.login("iii","pass");	// expect a session ID, positive integer, not equal to mySesssion
//		System.out.println(johnnySession);
//		
//		int someID2 = project.addCategory("PENCILS",johnnySession);	// expect -1 because Johnny is not administrator
//		System.out.println(someID2);
//		
//		try {
//			project.logout(johnnySession);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	// now user Johhny cannot do anything else because he logged out. All Johhny data must be saved in the files.
//		try {
//			project.logout(mySession);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		// user Ilir is also out
//		
//		int someID3 = project.addCategory("PENCILS",mySession);		// must fail (return -1) because user Ilir logged out
//		System.out.println(someID3);
	}

}

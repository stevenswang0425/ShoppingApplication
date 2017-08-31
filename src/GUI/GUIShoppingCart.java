package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import finalproject.PortalAPI;
import finalproject.ProjectV1.ShoppingCartItem;
import users.Customer;
import users.Shopper;
import users.User;
import users.UserManager;

public class GUIShoppingCart extends JFrame implements ActionListener{

	int cartnumber;
	JButton button_checkout, button_savecart;
	JPanel[] panels_cart = new JPanel[3];
	ArrayList<ShoppingCartItem> shoppinglist = (ArrayList<ShoppingCartItem>) PortalAPI.project.getShoppingCart("");
	Integer sessionID;
	User user;
	
	public GUIShoppingCart(int cartnumber, Integer sessionID) {
		super("Shopping Cart");
		this.cartnumber = cartnumber;
		
		this.sessionID = sessionID;
		
		for (User u: UserManager.userMap.values()) {
			if (u.getSessionID().equals(sessionID)) {
				if (u instanceof Shopper) {
					user = (Shopper) u;
				} else if (u instanceof Customer) {
					user = (Customer) u;
				}
				break;
			}
		}
		
		setSize(400,700);
		setLocation(800,0);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		add(panel_logo(),BorderLayout.PAGE_START);
		add(panel_cartbody(),BorderLayout.CENTER);
		add(panel_actions(),BorderLayout.PAGE_END);
	}
	
	private JPanel panel_logo(){
		JPanel logo = new JPanel();
		logo.setSize(getWidth(), 100);
		logo.setBackground(Color.darkGray);
		logo.add(new JLabel(new ImageIcon("fbayshopcart.png")));
		return logo;
	}
	
	private JPanel panel_cartbody(){
		JPanel cartbody = new JPanel();
		cartbody.setBackground(Color.white);
		
		int page = GUIShopping.pagenumber;
		
		String[] currentCargo = new String[6];
		currentCargo[0] = "item1";
		System.out.println(this.cartnumber);
		if (this.cartnumber == 0){
			cartbody.setLayout(new GridLayout(1,1));
			cartbody.add(new JLabel("Empty"));
		}
		else{
			panels_cart = new JPanel[this.cartnumber];
			cartbody.setLayout(new GridLayout(this.cartnumber,1));
			for (int i=0; i<this.cartnumber; i++){
				panels_cart[i] = new JPanel();
				if (page == 1){
					panels_cart[i].add(new JLabel("Item "+(i+1)+":"));
				}
				else if (page == 2){
					panels_cart[i].add(new JLabel("Item "+(i+1+6)+":"));
				}
				else if (page == 3){
					panels_cart[i].add(new JLabel("Item "+(i+1+6+6)+":"));
				}
				
				cartbody.add(panels_cart[i]);
			}
		}
		
		
		return cartbody;
	}
	
	private JPanel panel_actions(){
		JPanel actions = new JPanel();
		actions.setLayout(new BorderLayout());
		actions.setBackground(Color.black);
		button_checkout = new JButton("Checkout");
		button_checkout.addActionListener(this);
		button_savecart = new JButton("Save cart");
		button_savecart.addActionListener(this);
		actions.add(button_checkout,BorderLayout.LINE_END);
		actions.add(button_savecart,BorderLayout.LINE_START);
		return actions;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == button_checkout){
//*******System API from here add information to our record
//CheckOut button event
			if (this.cartnumber == 0){
				JOptionPane.showMessageDialog(null, "Error! Your shopping cart is empty",
						"Checkout",JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				Integer temp = JOptionPane.showConfirmDialog(null, "Are you sure you want to checkout?", 
						"Checkout Confirmation", JOptionPane.YES_NO_OPTION);
				if (temp == 0) {
					if (user instanceof Shopper) {
						PortalAPI.project.checkout(((Shopper) user).getCustomerID(), sessionID);
					} else if (user instanceof Customer) {
						PortalAPI.project.checkout(((Customer) user).getCustomerID(), sessionID);
					}
					GUIInvoice invoice = new GUIInvoice(this.cartnumber);
					invoice.setVisible(true);
				} 
			}
		}
		
		if (e.getSource() == button_savecart){
			if (cartnumber == 0) {
				JOptionPane.showMessageDialog(null, "Cannot save an empty cart!","Error", 
						JOptionPane.ERROR_MESSAGE);
			} else {
				if (user instanceof Shopper) {
					try {
						((Shopper) user).saveCart();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (user instanceof Customer) {
					try {
						((Customer) user).saveCart();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		
	}

}

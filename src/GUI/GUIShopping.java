package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import finalproject.ShoppingScreen;
import finalproject.PortalAPI;
import products.Category;
import products.CategoryManager;
import products.Product;
import products.ProductManager;
import users.*;

public class GUIShopping extends JFrame implements ActionListener,MouseListener{

	JPanel[][] allproducts;
	JButton[][] addtocart;
	
	
	public static int pagenumber = 1,cartnumber=0;
	JButton button_search, button_lastpage,button_nextpage,button_checkCart;
	JLabel pagelabel;
	JTextField textfield_search;
	
	private boolean loggedin;
	JComboBox<String> searchfilter;
	
	JComboBox<String> cate,price;
	final String NOTSET = "Not Set";
	
	Integer sessionID;
	ChopDictionary<Product> c;
	
	
	//This will be a product container for all of our products 
	ArrayList<String> objectcontainer = new ArrayList<String>();
	
	
	public GUIShopping(boolean loggedin, Integer sessionID){
		super("Shopping, It's just that easy");
		this.loggedin = loggedin;
		this.sessionID = sessionID;
		setSize(600,600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		
		button_lastpage = new JButton("Last Page");
		button_nextpage = new JButton("Next Page");
		
		//System.out.println(GUIHelper.getProductList());
		
		
		if (ProductManager.products.isEmpty()){
			page_constrains(1);
		}
		else{
			
			
			c = new ChopDictionary<Product>(ProductManager.products);
			
			Integer numb = c.getList().size();
			ArrayList<Product> products = new ArrayList<Product>();
			for (Map.Entry<String, Product> entryProduct : ProductManager.products.entrySet()){
				products.add(entryProduct.getValue());
			}
			int page = 1;
			if (products.size() % 6 == 0){
				page = products.size() / 6;
			}
			else{
				page = ((int)Math.floor(products.size() / 6)) + 1;
				
			}
			//Comments
			//System.out.println(GUIHelper.getProductList());
			//System.out.println(products.size());
			page_constrains(page);
		}
		
		
		add(categorySearch(), BorderLayout.PAGE_START);
		add(productlookthrough(), BorderLayout.CENTER);
		add(actionButtons(),BorderLayout.PAGE_END);
		
		
		
	}
	
	public boolean getloginstatus(){
		return this.loggedin;
	}
	
	public void setloginstatus(boolean login) throws EOFException{
		this.loggedin = login;
		if (login){
			enableeverything();
		}
		this.setVisible(true);
		
	}
	
	private JPanel categorySearch(){
		JPanel categorysearch = new JPanel();
		categorysearch.setSize(this.getWidth(), 200);
		
		categorysearch.setLayout(new GridLayout(2,1));
		
		JPanel searchPanel = new JPanel();
		JPanel categoryPanel = new JPanel();
		searchPanel.setBackground(Color.darkGray);
		categoryPanel.setBackground(Color.darkGray);
		categoryPanel.setLayout(new GridLayout(1,3));
		
		searchfilter = new JComboBox<String>();
		searchfilter.addItem("Filter");
		searchfilter.addItem("Category");
		searchfilter.addItem("Product");
		searchfilter.addItem("Price");

		
		textfield_search = new JTextField(25);
		textfield_search.setText("Search any product you want");
		textfield_search.setForeground(Color.lightGray);
		
		button_search = new JButton("Search");
		
		textfield_search.addMouseListener(this);
		button_search.addActionListener(this);
		
		cate = new JComboBox<String>();
		
		cate.addItem("Category");
		//Add available category to the set
		if(!CategoryManager.categories.isEmpty()){
			for (Map.Entry<String, Category> cateset : CategoryManager.categories.entrySet()){
				cate.addItem(cateset.getKey());
			}
		}
		price = new JComboBox<String>();
		
		
		price.addItem("Price");
		price.addItem("Ascending");
		price.addItem("Decending");
		
		searchPanel.add(searchfilter);
		searchPanel.add(textfield_search);
		searchPanel.add(button_search);
		
		JLabel category_label = new JLabel("Sort by");
		category_label.setForeground(Color.white);
		
		
		categoryPanel.add(category_label);
		
		categoryPanel.add(cate);
		categoryPanel.add(price);
		
		
		
		categorysearch.add(searchPanel);
		categorysearch.add(categoryPanel);
		
		return categorysearch;
	}
	
	private JPanel productlookthrough(){
		allproducts = new JPanel[2][3];
		addtocart = new JButton[2][3];
		
		
		JPanel productslook = new JPanel();
		productslook.setBackground(Color.gray);
		productslook.setSize(this.getWidth(),300);
		
		productslook.setLayout(new GridLayout(2,3));
		
		//This is Fake product
		
		
		
		
		//
		for (int row=0; row<2; row++){
			for (int col=0; col<allproducts[row].length; col++){
				allproducts[row][col] = new JPanel();
				
				addtocart[row][col] = new JButton("Add");
				addtocart[row][col].addActionListener(this);
				addtocart[row][col].setEnabled(true);
				
				//Quantity add the number of available quantity here
			
				
				allproducts[row][col].setLayout(new BorderLayout());
				
				allproducts[row][col].setBackground(Color.WHITE);
				allproducts[row][col].setBorder(BorderFactory.createLineBorder(Color.black));
				if (this.loggedin == false){
					addtocart[row][col].setEnabled(true);
				}
				
				//Add layout to the screen
				productslook.add(allproducts[row][col]);
			}
			
			
		}
		//Portal for putting the picture and others to our grid
		
		//ProductManager.products.size() != 0
		if (ProductManager.products.size() ==0){
			
			//addProducttoPanel(allproducts[row][col], "",product.getImage(),"description",product.getPrice(),"",addtocart[row][col]);
			
			//allproducts[row][col].add(addtocart[row][col],BorderLayout.SOUTH);
			
			
			for (int row=0; row<2; row++){
				for (int col=0; col<allproducts[row].length; col++){
			
					allproducts[row][col].add(new JLabel(new ImageIcon("fbaylogo.png")));
					
				}
			}

		
		
		
		
		
		}
		else{
			shoppingscreenTest(pagenumber);
		}
		
		
		
		
		
		
			
			
			
		
			
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return productslook;
	}
	
	private void removeAllINPanel(){
		for (int row=0; row<2; row++){
			for (int col=0; col<allproducts[row].length; col++){
				allproducts[row][col].removeAll();
			}
			}
	}
	
	
	private void validateAllINPanel(){
		for (int row=0; row<2; row++){
			for (int col=0; col<allproducts[row].length; col++){
				allproducts[row][col].revalidate();
			}
			}
	}
	
	
	//***NOT API. This is GUI related
	private void addProducttoPanel(JPanel panel, String name, Image image, String description, double price, String category, JButton button){
		panel.setLayout(new BorderLayout());
		JPanel linefirst = new JPanel(new GridLayout(2,1));
		JPanel nameCate = new JPanel(new BorderLayout());
		
		nameCate.add(new JLabel(name), BorderLayout.LINE_START);
		nameCate.add(new JLabel(category), BorderLayout.LINE_END);
		linefirst.add(nameCate);
		JTextField textdes = new JTextField(5);
		
		textdes.setText(description);
		
		
		linefirst.add(textdes);
		
		
		JPanel linelast = new JPanel(new GridLayout(1,2));
		linelast.add(new JLabel("$"+Double.toString(price)));
		
		
		
		linelast.add(button);
		
		panel.add(linefirst, BorderLayout.PAGE_START);
		panel.add(new JLabel(new ImageIcon("profile.png")), BorderLayout.CENTER);
		panel.add(linelast, BorderLayout.PAGE_END);
		
		
		
		
		
		
	}
	
	private JPanel actionButtons(){
		JPanel actions = new JPanel();
		actions.setLayout(new BorderLayout());
		actions.setBackground(Color.darkGray);
		
		button_checkCart = new JButton("Shopping Cart "+"("+cartnumber+")");
		
		pagelabel = new JLabel(Integer.toString(pagenumber));
		pagelabel.setForeground(Color.white);
		button_lastpage.addActionListener(this);
		button_nextpage.addActionListener(this);
		button_checkCart.addActionListener(this);
		button_lastpage.setEnabled(false);
		
		
		if (this.loggedin == false){
			button_checkCart.setEnabled(false);
		}
		else{
			button_checkCart.setEnabled(true);
		}
		
		
		JPanel pagepanel = new JPanel();
		pagepanel.setBackground(Color.darkGray);
		pagepanel.add(button_lastpage);
		pagepanel.add(pagelabel);
		pagepanel.add(button_nextpage);
		
		actions.add(pagepanel, BorderLayout.CENTER);
		actions.add(button_checkCart, BorderLayout.LINE_END);
		
		
		return actions;
	}
	
	//HELPER methods and main screen
	private void page_constrains(int constrains){
		if (pagenumber == 1){
			button_lastpage.setEnabled(false);
		}
		else{
			button_lastpage.setEnabled(true);
		}
		
		if (pagenumber == constrains){
			button_nextpage.setEnabled(false);
		}
		else{
			button_nextpage.setEnabled(true);
		}
		
	}
	
	private void enableeverything() throws EOFException {
		ShoppingScreen sc = new ShoppingScreen();
		
		
		button_checkCart.setEnabled(true);
		for (int row=0; row<2; row++){
			for (int col=0; col<addtocart[row].length; col++){
				addtocart[row][col].setEnabled(true);
			}
			}
	}
	
	
	private void shoppingscreenTest(int page){
		
//		for (int i=0; i<c.getList().size(); i++){
//			for (int row=0; row<2; row++){
//				for (int col=0; col<allproducts[row].length; col++){
//					Product[][] p = ((Product[][]) (c.getList().get(i)[row][col]);
//					addProducttoPanel(allproducts[row][col],p.getCategory(),null,p.getDescription(),p.getPrice(),p.getCategory(),addtocart[row][col]);
//				}
//			}
//			
//		}
		
		
		
		
		
		
		
		
		ArrayList<Product> products = new ArrayList<Product>();
		for (Map.Entry<String, Product> entry : ProductManager.products.entrySet()){
			products.add(entry.getValue());
		}
		int[][] itemindex = new int[2][3];
		
				
				
					for (int row=0; row<2; row++){
						for (int col=0; col<allproducts[row].length; col++){
							if (page == 1){
								for (int i=0; i<6-1; i++){
								addProducttoPanel(allproducts[row][col],products.get(i).getDescription(),null,products.get(i).getCategory(),products.get(i).getPrice(),products.get(i).getDescription(),addtocart[row][col]);
							
								}}
							else{
								for (int index=page * 2; index<page*6-1; index++){
								addProducttoPanel(allproducts[row][col],products.get(index).getDescription(),null,products.get(index).getCategory(),products.get(index).getPrice(),products.get(index).getDescription(),addtocart[row][col]);
					//System.out.println(index);
								
							}
				}
			}
					
		}
		//System.out.println(GUIHelper.getProductList());
		
		
		
		
			for (int row=0; row<2; row++){
				for (int col=0; col<allproducts[row].length; col++){
					for (int i =(page-1)*6; i<(page-1)*6+6; i++){
//						System.out.println(products.get(i));
					
//			for (int i=(page-1)*6; i<((page-1)*6)+6; i++){
//						
//				
//				}
			}
		}
		}
//		int num = 0;
//		
//			for (int row =0; row<2; row++){
//				for (int col=0; col<allproducts[row].length; col++){
//					if (page == 1){
//					addProducttoPanel(allproducts[row][col], "Name"+row+" "+col, null, "this",1.5+num,"this item", addtocart[row][col]);
//					
//				}
//					if (page == 2){
//						addProducttoPanel(allproducts[row][col], "Name"+row+" "+col, null, "this",1.5+num,"this item", addtocart[row][col]);
//					}
//					if (page == 3){
//						addProducttoPanel(allproducts[row][col], "Name"+row+" "+col, null, "this",1.5+num,"this item", addtocart[row][col]);
//					}
//					num++;
//					
//			}
//			
//		}
					
				
				
			
				
				
				
//				if (page == 3){
//					for (int i=(page-1)*6; i<((page-1)*6)+6; i++){
//						
//						addProducttoPanel(allproducts[row][col],products.get(i).getCategory(),null,products.get(i).getDescription(),products.get(i).getPrice(),products.get(i).getCategory(),addtocart[row][col]);
//					}
//					
//				}
				
				

		
		
		
		
		
		
		
		
		
		
			
			
			
		}
		
		
		
		
		
	

	
	
	
	
	
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		if (e.getSource() == button_lastpage && pagenumber > 1){
			pagenumber--;
			
			removeAllINPanel();
			shoppingscreenTest(pagenumber);
			validateAllINPanel();
			this.repaint();
			page_constrains(c.getList().size());
			
		
		}
		else if (e.getSource() == button_nextpage){
			pagenumber++;
			
			
			removeAllINPanel();
			shoppingscreenTest(pagenumber);
			validateAllINPanel();
			this.repaint();
			page_constrains(c.getList().size());
			
			
			
			
			
			
			

			
			
			
			}
		
		pagelabel.setText(Integer.toString(pagenumber));
		
		
		
		
		
		if (e.getSource() == button_checkCart){
			
//*******System API from here add information to our record
//Get shopping cart
			GUIShoppingCart shoppingCart = new GUIShoppingCart(cartnumber, sessionID);
			if (!shoppingCart.isShowing()){
				shoppingCart.setVisible(true);
			}
		}
		
		
		//Actions for search
		if (e.getSource() == button_search){
			String filter = (String) searchfilter.getSelectedItem();
			if (filter.equals("Filter")){
				JOptionPane.showMessageDialog(null, "Please select search type", "Error 404", JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null, "The "+filter+": \n\n"+textfield_search.getText()+"\n\ndoes not exist", "Error 404", JOptionPane.INFORMATION_MESSAGE);
			}
			}
		
		
		
		for (int row=0; row<2; row++){
			for (int col=0; col<3; col++){
				if (e.getSource() == addtocart[row][col]){
					if (pagenumber == 1){
						
					}
						cartnumber++;
						button_checkCart.setText("Shopping Cart "+"("+cartnumber+")");
						//PortalAPI.project.addToShoppingCart(productID, quantity, row, custID);
						
						
					}
					
//******** API addToCart
//PortalAPI.project.addToShoppingCart(productID, quantity, sessionID, custID);
					
				}
			}
		
		repaint();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == textfield_search){
			textfield_search.setText("");
			textfield_search.setForeground(Color.BLACK);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}

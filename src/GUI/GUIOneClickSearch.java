package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIOneClickSearch extends JFrame implements ActionListener{

	private String item; 
	
	JTextField search_text;
	JButton search_button;
	
	public GUIOneClickSearch(String item){
		super("Search " +item);
		this.item = item;
		setSize(620,200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		
		
		add(imagepanel(), BorderLayout.PAGE_START);
		add(searchpanel(), BorderLayout.CENTER);
	}
	
	private JPanel imagepanel(){
		JPanel imagep = new JPanel();
		imagep.add(new JLabel(new ImageIcon("fbaylogo.png")));
		return imagep;
		
	}
	
	private JPanel searchpanel(){
		JPanel search = new JPanel();
		search_text=new JTextField(20);
		search_text.addActionListener(this);
		search_button = new JButton("Search");
		search_button.addActionListener(this);
		search.add(search_text);
		search.add(search_button);
		return search;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == search_button){
			
		}
		
		
	}

}

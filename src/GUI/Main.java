package GUI;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.sql.*;

public class Main {
	private JFrame frame;
	private JPanel mainPanel;
	Connection con;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public Main() {
		connectToDB();
		initialize();
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(-7, 0,(int) screenSize.getWidth() + 15,(int)screenSize.getHeight() + 7);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel(new CardLayout());
		frame.getContentPane().add(mainPanel);
		
		//add panels 
		//Routing
//		JPanel Home = new JPanel();
//		Home.setBackground(new Color(0, 0, 0));
//		mainPanel.add(Home,"Home");

		

		
		OrderedByManager orderedByMng = new OrderedByManager(con);
		JPanel ordersPanel = orderedByMng.getPanel();
		mainPanel.add(ordersPanel ,"OrderedByManager");
		
		ManagerOrder managerOrder = new ManagerOrder(con);
		JPanel MngOrder = managerOrder.getPanel();
		mainPanel.add(MngOrder,"ManagerOrder");
		

		Books books = new Books(con);
		JPanel booksPanel = books.getPanel();
		mainPanel.add(booksPanel,"showBooks");
//		JButton editBtn= new JButton();
//		editBtn.setForeground(new Color(0, 51, 102));
//		editBtn.setBackground(new Color(204, 204, 204));
//		editBtn.setBounds(206, 494, 90, 41);
//		Home.add(editBtn);
//		editBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				navigate("ManagerOrder");
//			
//			}
//		});

	}
	public void navigate(String nav)
	{
		CardLayout cards = (CardLayout) mainPanel.getLayout();
		cards.show(mainPanel, nav);
	}
	public void connectToDB()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.con =  DriverManager.getConnection("jdbc:mysql://localhost/store", "root", "root");
			System.out.println("connected to db");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

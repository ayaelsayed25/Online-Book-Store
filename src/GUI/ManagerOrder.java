package GUI;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ModuleLayer.Controller;
import java.sql.Connection;
import java.util.LinkedList;

import javax.swing.*;

import Controllers.OrderController;
import Utils.Message;
import Utils.Order;

public class ManagerOrder {
	private JPanel panel;
	private JTextField publisherName;
	private LinkedList<JTextField> isbns;
	private LinkedList<JTextField> copies;
	private JButton addBtn;
	private JLabel message;
	OrderController ordercontroller;
	private static final int x1 = 340, width = 200, height = 40, x2 = 490, x3 = 740, x4 = 920;
	private int y = 100;
	
	public ManagerOrder(Connection con)
	{
		ordercontroller = new OrderController(con);
		panel = new JPanel();
		panel.setBackground(Style.backgroundColor);
		panel.setLayout(null);
		
		JLabel title = new JLabel("Order From Publisher");
		title.setFont(Style.boldFont);
		title.setForeground(Style.fontColorWhite);
		title.setBounds(70, 10, 337, 70);
		panel.add(title);
		
		JLabel publisherlbl = new JLabel("Publisher Name:");
		publisherlbl.setBounds(140, 90, 200, 40);
		publisherlbl.setForeground(Style.fontColorGrey);
		publisherlbl.setFont(Style.normalFont);
		panel.add(publisherlbl);
		
		publisherName = new JTextField();
		publisherName.setForeground(Style.fontColorGrey);
		publisherName.setFont(Style.normalFont);
		publisherName.setBounds(340, 90, 200, 40);
		panel.add(publisherName);
		publisherName.setColumns(10);
		
		isbns = new LinkedList<>();
		copies = new LinkedList<>();
		
		JLabel booklbl = new JLabel("Books To Order:");
		booklbl.setBounds(140, 150, 200, 40);
		booklbl.setForeground(Style.fontColorGrey);
		booklbl.setFont(Style.normalFont);
		panel.add(booklbl);		
		addBtn = new JButton("Add Book");
		addBtn.setBackground(Style.color);
		addBtn.setFont(Style.normalFont);
		addBtn.setForeground(Style.fontColorWhite);
		panel.add(addBtn);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isbns.size() <= 15)
				{ 
					addField();
				}
			}
			
		});
		addField();
		message = new JLabel("");
		message.setFont(Style.normalFont);
		message.setBounds(650, 650, 300, 40);
		panel.add(message);
		
		
		JButton order = new JButton("Order Books");
		order.setBackground(Style.color);
		order.setFont(Style.normalFont);
		order.setForeground(Style.fontColorWhite);
		order.setBounds(650, 600 , 170, 40);
		panel.add(order);
		order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleOrder();
			}
			
		});	
		


	}
	public void handleOrder() {
		if(publisherName.getText().equals(""))
		{
			showMessage(new Message(false, "Enter publisher name"));
			return;
		}
		LinkedList<Order> list = new LinkedList<>();

		for(int i=0; i<isbns.size(); i++)
		{
			String isbn = isbns.get(i).getText();
			String copy = copies.get(i).getText();
			if( isbn.equals("") || copy.equals(""))
			{
				showMessage(new Message(false, "Fill all required info"));
				return;
			}
			else if(!isbn.matches("-?\\d+(\\.\\d+)?")  || !copy.matches("-?\\d+(\\.\\d+)?"))
			{
				showMessage(new Message(false, "Make sure you entered valid info"));
				return;
			}
			Order order = new Order(Integer.parseInt(isbn), Integer.parseInt(copy));
			list.add(order);
		}
		String manager_id = "root";
		Message msg = ordercontroller.orderManagerBooks(manager_id, publisherName.getText(), list);
		showMessage(msg);
		list = null;
		
	}
	public void showMessage(Message msg)
	{
		if(msg.success)
			message.setForeground(Color.GREEN);
		else
			message.setForeground(Color.RED);
		message.setText(msg.content);

		panel.repaint();
	}
	public JPanel getPanel()
	{
		return this.panel;
	}
	public void addField()
	{
		y += 50;
		addBtn.setBounds(650, y + 50 , 170, 40);
		JLabel booklbl = new JLabel("Book ISBN:");
		booklbl.setBounds(x1, y, width, height);
		booklbl.setForeground(Style.fontColorGrey);
		booklbl.setFont(Style.normalFont);
		panel.add(booklbl);
		
		JTextField book = new JTextField();
		book.setForeground(Style.fontColorGrey);
		book.setFont(Style.normalFont);
		book.setBounds(x2, y, width, height);
		panel.add(book);
		book.setColumns(10);
		isbns.add(book);
		
		JLabel copylbl = new JLabel("Number of copies:");
		copylbl.setBounds(x3, y, width, height);
		booklbl.setForeground(Style.fontColorGrey);
		copylbl.setFont(Style.normalFont);
		panel.add(copylbl);
		
		JTextField copy = new JTextField();
		copy.setForeground(Style.fontColorGrey);
		copy.setFont(Style.normalFont);
		copy.setBounds(x4, y, width, height);
		panel.add(copy);
		copies.add(copy);
		panel.repaint();
		
		if(isbns.size() > 15)
		{
			addBtn.setBackground(new Color(185, 185, 185));
		}
		
	}

}

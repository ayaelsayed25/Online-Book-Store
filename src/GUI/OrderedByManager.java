package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;

import Controllers.OrderController;
import Utils.Message;

public class OrderedByManager {
	JPanel panel;
	JTable orders;
	JScrollPane scrollPane;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	OrderController controller;
	public OrderedByManager(Connection con)
	{
		controller = new OrderController(con);
		panel = new JPanel();
		panel.setBackground(Style.backgroundColor);
		panel.setLayout(null);
		
		JLabel title = new JLabel("Ordered Books");
		title.setFont(Style.boldFont);
		title.setForeground(Style.fontColorWhite);
		title.setBounds(70, 10, 337, 70);
		panel.add(title);
		
		//table
        //books table
		orders = new JTable();
        String[] columnNames = {"Order ID","Publisher", "Order Date"};
        String manager_id = "root";
        Object[][] data = controller.getOrders(manager_id);
        orders = new JTable(data, columnNames);
        orders.setGridColor(Style.backgroundColor);
        orders.setSelectionBackground(Style.color);
        orders.setSelectionForeground(Style.fontColorWhite);
        JTableHeader header = orders.getTableHeader();
        header.setBackground(Style.color);
        header.setForeground(Style.fontColorWhite);
        header.setFont(new Font("Tahoma", Font.PLAIN, 18) );
        orders.setRowHeight(20);
        orders.setFont(new Font("Tahoma", Font.PLAIN, 18));
        orders.setBorder(new LineBorder(new Color(102, 102, 102), 2, true));
        orders.setFillsViewportHeight(true);
        
        //show orders in table
        scrollPane = new JScrollPane(orders);
        scrollPane.setBounds(200, 100,600,(int)screenSize.getHeight()-300);
        scrollPane.setBackground(Style.fontColorGrey);
        panel.add(scrollPane);
        
        //confirm button
        JButton confirm = new JButton("Confirm Selected");
        confirm.setBackground(new Color(247, 56, 89));
        confirm.setFont(Style.normalFont);
        confirm.setForeground(Style.fontColorWhite);
        confirm.setBounds(850, 350, 200, 40);
		panel.add(confirm);
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleConfirm();
			}
			
		});	
	}
	public void handleConfirm() {
		if(orders.getSelectedRow() == -1)
		{
			JOptionPane.showMessageDialog(panel, "Select an order");
			return;
		}
		int order_id = (Integer) orders.getModel().getValueAt(orders.getSelectedRow(), 0);
		Message msg = controller.confirmOrder(order_id);
		if(msg.success)
		{
			JOptionPane.showMessageDialog(panel, msg.content);
			//update orders table
		}
		else
			JOptionPane.showMessageDialog(panel,msg.content);
	}
	public JPanel getPanel()
	{
		return this.panel;
	}
}

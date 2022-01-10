package Controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;


import Utils.Message;
import Utils.Order;

public class OrderController{
	private Connection con;
	public OrderController(Connection con)
	{
		this.con = con;
	}
	public Message orderManagerBooks(String manager_id, String publisher, LinkedList<Order> books)
	{
		//check if publisher exists
		String query = "";
		try {
			java.sql.PreparedStatement stmt = con.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return new Message(false, "an error occurred");
		}
		//check if books exist
		
		//insert in manager_order table
		
		//insert in manager_order_bookd_table
		
		return new Message(false, "Error");
	}
}

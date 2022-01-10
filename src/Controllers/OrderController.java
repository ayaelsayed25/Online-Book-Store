package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import java.sql.Connection;

import Utils.Message;
import Utils.Order;

public class OrderController{
	private Connection con;
	public OrderController(Connection con2)
	{
		this.con = con2;
	}
	public Message orderManagerBooks(String manager_id, String publisher, LinkedList<Order> books)
	{
		String query = "";
		try {
			//check if publisher exists
			query = "SELECT * FROM PUBLISHER WHERE PUBLISHER.name = ?";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, publisher);
			ResultSet rs = stm.executeQuery();		      
	        if (rs.next() == false) {
	        	System.out.println("No publisher with that name.");
	        	return new Message(false, "No publisher with that name");
	         }			
			//insert in manager_order table
	        query = "INSERT INTO MANAGER_ORDER(manager_id, publisher) VALUES(?, ?)";
	        stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        stm.setString(1, manager_id);
	        stm.setString(2, publisher);
	        stm.executeUpdate();
	        rs = stm.getGeneratedKeys();
	        int order_id =0;
	        if (rs.next()) {
	            order_id = rs.getInt(1);
	        }
			//insert in manager_order_book_table
	        query = "INSERT INTO MANAGER_ORDER_BOOKS(order_id, ISBN, number_of_copies) VALUES(?, ?, ?)";
	        stm = con.prepareStatement(query);
	        for(Order book : books)
	        {
	        	stm.setInt(1, order_id);
	        	stm.setInt(2, book.ISBN);
	        	stm.setInt(3, book.num_of_copies);
	        	stm.executeUpdate();
	        }
		} catch (SQLException e) {
			e.printStackTrace();
			return new Message(false, "an error occurred");
		}
		return new Message(true, "Ordered successfully");
	}
}

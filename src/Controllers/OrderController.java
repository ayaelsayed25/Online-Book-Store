package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
	        //check if books exist
	        if(!checkBooks(books)) return new Message(false, "Make sure all books exist");
			//insert in manager_order table
	        query = "INSERT INTO MANAGER_ORDER(manager_id, publisher, created) VALUES(?, ?, ?)";
	        stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        stm.setString(1, manager_id);
	        stm.setString(2, publisher);
	        Date date = new Date();
	        Timestamp timestamp = new Timestamp(date.getTime());
	        stm.setTimestamp(3, timestamp);
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
	
	public boolean checkBooks(LinkedList<Order> books) {
		String query = "";
		try {
			for(Order book : books)
			{
				//check if publisher exists
				query = "SELECT * FROM BOOK WHERE BOOK.ISBN = ?";
				PreparedStatement stm = con.prepareStatement(query);
				stm.setInt(1,book.ISBN);
				ResultSet rs = stm.executeQuery();		      
		        if (rs.next() == false) {
		        	System.out.println("No publisher with that name.");
		        	return false;
		         }
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public Message confirmOrder(int order_id)
	{
		//delete from manager_orders
        String query = "DELETE FROM MANAGER_ORDER WHERE MANAGER_ORDER.order_id = ?";
        PreparedStatement stm;
		try {
			stm = con.prepareStatement(query);
	        stm.setInt(1, order_id);
	        stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return new Message(false, "Something went wrong");
		}

		return new Message(true, "Order confirmed successfully");
	}
	public Object[][] getOrders(String manager_id)
	{
		String query = "";
        List<Object[]> d = new ArrayList<>();
        Object[] column = new Object[3];
        Object[][] data = new Object[0][];
		try {
			//check if publisher exists
			query = "SELECT * FROM MANAGER_ORDER WHERE MANAGER_ORDER.manager_id = ?";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, manager_id);
			ResultSet rs = stm.executeQuery();		      
            while (rs.next())
            {
            	column = new Object[3];
                column[0] = rs.getInt("order_id");
                column[1] = rs.getString("publisher");
                column[2] = rs.getTimestamp("created");
                d.add(column);
            }
            data = new Object[d.size()][3];
            for(int i = 0; i < d.size(); i++){
                data[i] = d.get(i);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
}

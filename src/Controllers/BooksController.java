package Controllers;

import Utils.Message;
import Utils.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BooksController{
    private Connection con;
    public BooksController(java.sql.Connection con2)
    {
        this.con = con2;
    }
    public Object[][] showBooks(String query)
    {
        List<Object[]> d = new ArrayList<>();
        Object[][] data = new Object[0][];
        try {
            //get books
            PreparedStatement stm = con.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next())
            {
                Object[] column = new Object[6];
                int isbn = rs.getInt("ISBN");
                column[0] = isbn;
                String title = rs.getString("title");
                column[1] = title;
                String publisher = rs.getString("publisher");
                column[2] = publisher;
                Date publication_year = rs.getDate("publication_year");
                column[3] = publication_year;
                double price = rs.getDouble("price");
                column[4] = price;
                String category = rs.getString("category");
                column[5] = category;

                d.add(column);
            }
            data = new Object[d.size()][6];
            for(int i = 0; i < d.size(); i++){
                data[i] = d.get(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

}

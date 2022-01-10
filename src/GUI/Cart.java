package GUI;

import Controllers.CartConroller;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Cart implements ActionListener {
    private JPanel cartPanel;
    private JScrollPane cartScrollPane;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JTable books;
    private JButton deleteButton;
    private JButton confirmButton;
    private JButton continueButton;
    private JLabel priceLabel;
    private JLabel price;
    //need to be combobox
    private JTextField category;
    private JLabel categoryLabel;
    private JTextField author;
    private JLabel authorLabel;
    private JTextField publisher;
    private JLabel publisherLabel;
    CartConroller cartConroller;
    Main main;
    Object[][] data = {};
    String totalPrice = "0";
    String[] columnNames = {"ISBN","Title","Publisher","Publication Year","Price","Category"};
    public Cart(Connection con, Main main)
    {
        cartConroller = new CartConroller(con);
        this.main = main;
        cartPanel = new JPanel();
        cartPanel.setBackground(Style.backgroundColor);
        cartPanel.setLayout(null);

        //books table
        books = new JTable(data, columnNames);
        books.setGridColor(Style.backgroundColor);
        books.setSelectionBackground(Style.color);
        books.setSelectionForeground(Style.fontColorWhite);
        JTableHeader header = books.getTableHeader();
        header.setBackground(Style.color);
        header.setForeground(Style.fontColorWhite);
        header.setFont(new Font("Tahoma", Font.PLAIN, 18) );
        books.setRowHeight(20);
        books.setFont(new Font("Tahoma", Font.PLAIN, 18));
        books.setBorder(new LineBorder(new Color(102, 102, 102), 2, true));
        books.setFillsViewportHeight(true);

        //to display books
        cartScrollPane = new JScrollPane(books);
        cartScrollPane.setBounds(20, 50,(int) screenSize.getWidth()-40 ,(int)screenSize.getHeight()-180);
        cartScrollPane.setBackground(Style.fontColorGrey);
        cartPanel.add(cartScrollPane);

        ///deleteButton
        deleteButton = new JButton("Delete book");
        deleteButton.setBounds(500, 750, 200, 40);
        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        deleteButton.setBackground(Style.color);
        deleteButton.setForeground(Style.fontColorWhite);
        cartPanel.add(deleteButton);
        deleteButton.addActionListener( this);
        deleteButton.setActionCommand("delete");

        ///confirmButton
        confirmButton = new JButton("Buy");
        confirmButton.setBounds(800, 750, 200, 40);
        confirmButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        confirmButton.setBackground(Style.color);
        confirmButton.setForeground(Style.fontColorWhite);
        cartPanel.add(confirmButton);
        confirmButton.addActionListener( this);
        confirmButton.setActionCommand("buy");

        ///confirmButton
        continueButton = new JButton("Continue shopping");
        continueButton.setBounds(20, 8, 250, 30);
        continueButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        continueButton.setBackground(Style.color);
        continueButton.setForeground(Style.fontColorWhite);
        cartPanel.add(continueButton);
        continueButton.addActionListener( this);
        continueButton.setActionCommand("continue");

        //price label
        priceLabel = new JLabel("Total Price");
        priceLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        priceLabel.setForeground(Style.color);
        priceLabel.setBounds(1, 750, 300, 50);
        cartPanel.add(priceLabel);

        //price
        price = new JLabel(totalPrice);
        price.setFont(new Font("Tahoma", Font.BOLD, 15));
        price.setBackground(Style.color);
        price.setForeground(Style.fontColorWhite);
        price.setBounds(100, 750, 300, 50);
        cartPanel.add(price);

//        ///cartButton
//        cartButton = new JButton("Show cart");
//        cartButton.setBounds(1380, 10, 150, 35);
//        cartButton.setFont(new Font("Tahoma", Font.BOLD, 18));
//        cartButton.setBackground(new Color(247, 56, 89));
//        cartButton.setForeground(Style.fontColorWhite);
//        cartPanel.add(cartButton);
//        cartButton.addActionListener( this);
//        cartButton.setActionCommand("cart");
//
//        //ISBN text field
//        isbn = new JTextField();
//        isbn.setBounds(45, 10, 70, 30);
//        isbn.setColumns(1);
//        cartPanel.add(isbn);
//

//
//        //title text field
//        title = new JTextField();
//        title.setBounds(170, 10, 150, 30);
//        title.setColumns(1);
//        cartPanel.add(title);
//
//        //title label
//        titleLabel = new JLabel("Title");
//        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
//        titleLabel.setForeground(Style.color);
//        titleLabel.setBounds(128, 0, 300, 50);
//        cartPanel.add(titleLabel);
//
//        //publisher text field
//        publisher = new JTextField();
//        publisher.setBounds(412, 10, 150, 30);
//        publisher.setColumns(1);
//        cartPanel.add(publisher);
//
//        //publisher label
//        publisherLabel = new JLabel("Publisher");
//        publisherLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
//        publisherLabel.setForeground(Style.color);
//        publisherLabel.setBounds(335, 0, 300, 50);
//        cartPanel.add(publisherLabel);
//
//        //author text field
//        author = new JTextField();
//        author.setBounds(635, 10, 150, 30);
//        author.setColumns(1);
//        cartPanel.add(author);
//
//        //author label
//        authorLabel = new JLabel("Author");
//        authorLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
//        authorLabel.setForeground(Style.color);
//        authorLabel.setBounds(575, 0, 300, 50);
//        cartPanel.add(authorLabel);
//
//        //category text field
//        category = new JTextField();
//        category.setBounds(880, 10, 150, 30);
//        category.setColumns(1);
//        cartPanel.add(category);
//
//        //category label
//        categoryLabel = new JLabel("Category");
//        categoryLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
//        categoryLabel.setForeground(Style.color);
//        categoryLabel.setBounds(805, 0, 300, 50);
//        cartPanel.add(categoryLabel);
//
//        ///searchButton
//        searchButton = new JButton("Search");
//        searchButton.setBounds(1050, 10, 100, 32);
//        searchButton.setFont(new Font("Tahoma", Font.BOLD, 18));
//        searchButton.setBackground(Style.color);
//        searchButton.setForeground(Style.fontColorWhite);
//        cartPanel.add(searchButton);
//        searchButton.setActionCommand("search");
//        searchButton.addActionListener( this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "delete":
                int index = books.getSelectedRow();
                //delete row and delete from data
                totalPrice = cartConroller.updatePrice(((Double) data[index][4])*-1,totalPrice);
                price = new JLabel(totalPrice);
                break;
            case "buy":
                break;
            case "continue":
                main.navigate("showBooks");
        }
    }

    public JPanel getPanel()
    {
        return this.cartPanel;
    }

    public void buyBook(Object[] book){
        books.setModel(new DefaultTableModel());
        totalPrice = cartConroller.updatePrice((Double) book[4],totalPrice);
        price = new JLabel(totalPrice);
    }


}

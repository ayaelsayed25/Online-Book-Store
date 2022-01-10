package GUI;

import Controllers.BooksController;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Books implements ActionListener {
    private JPanel booksPanel;
    private JScrollPane booksScrollPane;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JTable books;
    private JButton addButton;
    private JButton cartButton;
    private JButton searchButton;
    private JTextField isbn;
    private JLabel isbnLabel;
    private JTextField title;
    private JLabel titleLabel;
    //need to be combobox
    private JComboBox category;
    private JLabel categoryLabel;
    private JTextField author;
    private JLabel authorLabel;
    private JTextField publisher;
    private JLabel publisherLabel;
    String query= "SELECT ISBN,title,publisher,publication_year,price,category FROM BOOK";
    BooksController booksController;
    Main main;
    Cart cart;
    Object[][] data;
    String[] columnNames = {"ISBN","Title","Publisher","Publication Year","Price","Category"};
    public Books(Connection con, Main main, Cart cart)
    {
        this.main = main;
        this.cart = cart;
        booksController = new BooksController(con);
        booksPanel = new JPanel();
        booksPanel.setBackground(Style.backgroundColor);
        booksPanel.setLayout(null);

        //books table
        data = booksController.showBooks(query);
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
        booksScrollPane = new JScrollPane(books);
        booksScrollPane.setBounds(20, 50,(int) screenSize.getWidth()-40 ,(int)screenSize.getHeight()-180);
        booksScrollPane.setBackground(Style.fontColorGrey);
        booksPanel.add(booksScrollPane);

        ///AddButton
        addButton = new JButton("Add to cart");
        addButton.setBounds(680, 750, 200, 40);
        addButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        addButton.setBackground(Style.color);
        addButton.setForeground(Style.fontColorWhite);
        booksPanel.add(addButton);
        addButton.addActionListener( this);
        addButton.setActionCommand("add");

        ///cartButton
        cartButton = new JButton("Show cart");
        cartButton.setBounds(1380, 10, 150, 35);
        cartButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        cartButton.setBackground(new Color(247, 56, 89));
        cartButton.setForeground(Style.fontColorWhite);
        booksPanel.add(cartButton);
        cartButton.addActionListener( this);
        cartButton.setActionCommand("cart");

        //ISBN text field
        isbn = new JTextField();
        isbn.setBounds(45, 10, 70, 30);
        isbn.setColumns(1);
        booksPanel.add(isbn);

        //ISBN label
        isbnLabel = new JLabel("ISBN");
        isbnLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        isbnLabel.setForeground(Style.color);
        isbnLabel.setBounds(1, 0, 300, 50);
        booksPanel.add(isbnLabel);

        //title text field
        title = new JTextField();
        title.setBounds(170, 10, 150, 30);
        title.setColumns(1);
        booksPanel.add(title);

        //title label
        titleLabel = new JLabel("Title");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        titleLabel.setForeground(Style.color);
        titleLabel.setBounds(128, 0, 300, 50);
        booksPanel.add(titleLabel);

        //publisher text field
        publisher = new JTextField();
        publisher.setBounds(412, 10, 150, 30);
        publisher.setColumns(1);
        booksPanel.add(publisher);

        //publisher label
        publisherLabel = new JLabel("Publisher");
        publisherLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        publisherLabel.setForeground(Style.color);
        publisherLabel.setBounds(335, 0, 300, 50);
        booksPanel.add(publisherLabel);

        //author text field
        author = new JTextField();
        author.setBounds(635, 10, 150, 30);
        author.setColumns(1);
        booksPanel.add(author);

        //author label
        authorLabel = new JLabel("Author");
        authorLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        authorLabel.setForeground(Style.color);
        authorLabel.setBounds(575, 0, 300, 50);
        booksPanel.add(authorLabel);

        //category text field
        category = new JComboBox();
        category.setBounds(880, 10, 150, 30);
        category.setModel(new DefaultComboBoxModel(new String[]{"Science", "Art", "Religion", "History", "Geography"}));
        category.setBackground(Style.fontColorWhite);
        booksPanel.add(category);

        //category label
        categoryLabel = new JLabel("Category");
        categoryLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        categoryLabel.setForeground(Style.color);
        categoryLabel.setBounds(805, 0, 300, 50);
        booksPanel.add(categoryLabel);

        ///searchButton
        searchButton = new JButton("Search");
        searchButton.setBounds(1050, 10, 100, 32);
        searchButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        searchButton.setBackground(Style.color);
        searchButton.setForeground(Style.fontColorWhite);
        booksPanel.add(searchButton);
        searchButton.setActionCommand("search");
        searchButton.addActionListener( this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "add":
                if (books.getSelectedRow() != -1){
                    int bookIndex = books.getSelectedRow();
                    cart.buyBook(data[bookIndex]);
                }
                break;
            case "search":
                if (!(isbn.getText().equals("")) || !(title.getText().equals("")) || !(publisher.getText().equals("")) || !(author.getText().equals("")) || !(category.getSelectedItem().equals(""))) {
                    query += " WHERE ";
                    if (!isbn.getText().equals("")) {
                        query += " ISBN = " + isbn.getText();
                    }
                    if (!title.getText().equals("")) {
                        if (!isbn.getText().equals("")) {
                            query += ",";
                        }
                        query += " title = "+'"'+  title.getText()+'"';
                    }
                    if (!publisher.getText().equals("")) {
                        if (!isbn.getText().equals("") || !title.getText().equals("")) {
                            query += ",";
                        }
                        query += " publisher = " +'"' + publisher.getText()+'"';
                    }
                    if (!category.getSelectedItem().equals("")) {
                        if (!(isbn.getText().equals("")) || !(title.getText().equals("")) || !(publisher.getText().equals(""))) {
                            query += ",";
                        }
                        query += " category = " +'"'+ category.getSelectedItem()+'"';
                    }
                    if (!author.getText().equals("")) {
                        if (!(isbn.getText().equals("")) || !(title.getText().equals("")) || !(publisher.getText().equals(""))) {
                            query += ",";
                        }
                        query += " author = " +'"'+ author.getText()+'"';
                        query.replace("FROM BOOK","FROM BOOK NATURAL JOIN AUTHOR");
                    }
                    data = booksController.showBooks(query);
                    DefaultTableModel model = (DefaultTableModel) books.getModel();
                    model.fireTableDataChanged();
                    books.setModel(model);
                    books.revalidate();
                    booksPanel.updateUI();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Search fields are empty!");
                }
                break;
            case "cart":
                main.navigate("cart");
                break;
        }
    }

    public JPanel getPanel()
    {
        return this.booksPanel;
    }


}

package GUI;

import Controllers.BooksController;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class Books {
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
    private JTextField category;
    private JLabel categoryLabel;
    private JTextField author;
    private JLabel authorLabel;
    private JTextField publisher;
    private JLabel publisherLabel;

    public Books(java.sql.Connection con)
    {
        BooksController booksController = new BooksController(con);
        booksPanel = new JPanel();
        booksPanel.setBackground(Style.backgroundColor);
        booksPanel.setLayout(null);

        //books table
        String[] columnNames = {"ISBN","Title","Publisher","Publication Year","Price","Category"};
        Object[][] data = booksController.showBooks();
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
//        addButton.addActionListener( this);
        addButton.setActionCommand("add");

        ///cartButton
        cartButton = new JButton("Show cart");
        cartButton.setBounds(1380, 10, 150, 35);
        cartButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        cartButton.setBackground(new Color(247, 56, 89));
        cartButton.setForeground(Style.fontColorWhite);
        booksPanel.add(cartButton);
//        addButton.addActionListener( this);
        addButton.setActionCommand("cart");

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
        category = new JTextField();
        category.setBounds(880, 10, 150, 30);
        category.setColumns(1);
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
//        addButton.addActionListener( this);
        searchButton.setActionCommand("search");

    }

    public JPanel getPanel()
    {
        return this.booksPanel;
    }

}

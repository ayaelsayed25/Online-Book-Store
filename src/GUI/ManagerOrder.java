package GUI;


import javax.swing.*;

public class ManagerOrder {
	private JPanel panel;
	private JTextField publisherName;

	public ManagerOrder()
	{
		panel = new JPanel();
		panel.setBackground(Style.backgroundColor);
		panel.setLayout(null);
		
		JLabel title = new JLabel("Order From Publisher");
		title.setFont(Style.boldFont);
		title.setForeground(Style.fontColorWhite);
		title.setBounds(370, 10, 337, 70);
		panel.add(title);
		
		JLabel publisherlbl = new JLabel("Publisher Name:");
		publisherlbl.setBounds(34, 60, 71, 40);
		publisherlbl.setForeground(Style.fontColorGrey);
		publisherlbl.setFont(Style.normalFont);
		panel.add(publisherlbl);
		
		publisherName = new JTextField();
		publisherName.setForeground(Style.fontColorGrey);
		publisherName.setFont(Style.normalFont);
		publisherName.setBounds(130, 69, 219, 32);
		panel.add(publisherName);
		publisherName.setColumns(10);

	}
	public JPanel getPanel()
	{
		return this.panel;
	}

}

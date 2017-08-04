import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class Search implements ActionListener{
JFrame frame, frame1;
JTextField textbox;
JLabel label;
JButton button;
JPanel panel;
static JTable table;

//String driverName = "com.mysql.jdbc.Driver";
//String url = "jdbc:mysql://localhost:3306/veer";
//String userName = "veer";
//String password = "";
String[] columnNames = {"Id", "Product", "Description ", "Price","Category"};

public void createUI()
{
frame = new JFrame("Database Search Result");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setLayout(null);
textbox = new JTextField();
textbox.setBounds(120,30,150,20); 
label = new JLabel("Enter Product");
label.setBounds(10, 30, 100, 20);
button = new JButton("search");
button.setBounds(120,130,150,20);
button.addActionListener(this);

frame.add(textbox);
frame.add(label);
frame.add(button);
frame.setVisible(true);
frame.setSize(500, 400); 
} 

public void actionPerformed(ActionEvent ae)
{
button = (JButton)ae.getSource();
System.out.println("Showing Table Data.......");
showTableData(); 
} 

public void showTableData()
{

frame1 = new JFrame("Database Search Result");
frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame1.setLayout(new BorderLayout()); 
//TableModel tm = new TableModel();
DefaultTableModel model = new DefaultTableModel();
model.setColumnIdentifiers(columnNames);
//DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnNames()); 
//table = new JTable(model);
table = new JTable();
table.setModel(model); 
table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
table.setFillsViewportHeight(true);
JScrollPane scroll = new JScrollPane(table);
scroll.setHorizontalScrollBarPolicy(
JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
scroll.setVerticalScrollBarPolicy(
JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
//String textvalue = textbox.getText();
String id= "";
String product= "";
String description = "";
String price = "";
String category = "";
try
{ 
	
	 Class.forName("com.mysql.jdbc.Driver");

     Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/veer","root","");
     
String sql = "select * from Products where title like '"+textbox.getText()+"%'";
PreparedStatement ps = con.prepareStatement(sql);
ResultSet rs = ps.executeQuery();
int i =0;
while(rs.next())
{
id= rs.getString("id");
product = rs.getString("title");
description = rs.getString("description");
price = rs.getString("price"); 
category = rs.getString("category");
model.addRow(new Object[]{id,product,description,price,category});
i++; 
}
if(i <1)
{
JOptionPane.showMessageDialog(null, "No Record Found","Error",
JOptionPane.ERROR_MESSAGE);
}
if(i ==1)
{
System.out.println(i+" Record Found");
}
else
{
System.out.println(i+" Records Found");
}
}
catch(Exception ex)
{
JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
JOptionPane.ERROR_MESSAGE);
}
frame1.add(scroll);
frame1.setVisible(true);
frame1.setSize(400,300);
}

public static void main(String args[])
{
Search sr = new Search();
sr.createUI(); 
}
}
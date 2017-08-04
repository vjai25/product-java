import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.sun.xml.internal.ws.api.Component;

public class Work extends JFrame{
	private JComponent table[][];
	private static int COLS = 6;
	private static String[] HEADERS={"ID", "ProductName" , "Description" , "Price" , "Categroy" , "Action"};
	private JTextField textField;
	
	public Work()
	{
		setSize(600, 300);
		setLocation(50, 50);
		setTitle("EditableList");
		getContentPane().setLayout(null);
		
		JButton btnSearch = new JButton("search");
		btnSearch.setBounds(34, 11, 89, 23);
		getContentPane().add(btnSearch);
		
		textField = new JTextField();
		textField.setBounds(149, 12, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		createTable();
		generateTable();
		printTable();
	}
	
	private void createTable()
	{
		DataAccessObject dao = new DataAccessObject();
		int rows = dao.count("select count(id) from Products");
		table = new JComponent[rows + 1][COLS];
	}
	
	private void generateTable()
	{
		DataAccessObject dao = new DataAccessObject();
		ResultSet rs=dao.select("select * from Products order by id asc");
		for(int j = 0; j < COLS; j++)
		{
			table[0][j] = new JLabel(HEADERS[j]);
		}
		for(int i = 1; i < table.length; i++)
		{
			try {
				rs.next();
				int j;
				for(j=0;j<table[i].length - 1;j++)
				{
					JTextField jtf = new JTextField(10);
					switch(j+1)
					{
					case 1:
					case 4:
						jtf.setText(rs.getInt(j+1) + "");
						break;
					default:
						jtf.setText(rs.getString(j+1));
					}
					table[i][j]=jtf;
				}
				table[i][j]=new JButton("Edit");
				table[i][j]=new JButton("Delete");
				
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
	
	private void printTable()
	{
		for(int i = 0; i < table.length; i++)
		{
			for(int j = 0; j < table[i].length; j++)
			{
				getContentPane().add(table[i][j]);
			}
		}
	}
	
	public static void main(String[] args)
	{
		JFrame frm = new Work();
		frm.setSize(600, 300);
		frm.setLocation(50, 50);
		 frm.setVisible(true);
	}
}

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.sun.xml.internal.ws.api.Component;

public class EditableList extends JFrame{
	private JComponent table[][];
	private static int COLS = 6;
	private static String[] HEADERS={"ID", "ProductName" , "Description" , "Price" , "Categroy" , "Action"};
	private int rows;
	private EditableList self;
	
	public EditableList()
	{
		self = this;
		setSize(1000, 300);
		setLocation(50, 50);
		setTitle("EditableList");
		createTable();
		generateTable();
		printTable();
		setVisible(true);
	}
	
	private void createTable()
	{
		DataAccessObject dao = new DataAccessObject();
		rows = dao.count("select count(id) from Products");
		table = new JComponent[rows + 1][COLS];
	}
	
	private void generateTable()
	{
		DataAccessObject dao = new DataAccessObject();
		ResultSet rs=dao.select("select * from Products");
		for(int j = 0; j < COLS; j++)
		{
			table[0][j] = new JLabel(HEADERS[j]);
		}
		for(int i = 1; i < table.length; i++)
		{
			try {
				rs.next();
				int j;
				for(j = 0; j < table[i].length - 1; j++)
				{
					JTextField jtf = new JTextField(10);
					jtf.setEditable(false);
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
				JPanel jp = new JPanel();
				jp.setLayout(new FlowLayout());
				JButton b1 = new JButton("Update");
				b1.setName(i+"");
				b1.addActionListener(new ActionListener(){

					public void actionPerformed(ActionEvent ae) {
						JButton b = (JButton)ae.getSource();
						int r = Integer.parseInt(b.getName());
						if(b.getText().equals("Update"))
						{
							b.setText("Save");;
							for(int c = 1; c < EditableList.COLS - 1; c++)
							{
								((JTextField)table[r][c]).setEditable(true);
							}
						}
						else
						{
							b.setText("Update");
							String[] stra = new String[COLS - 2];
							for(int c = 1; c < EditableList.COLS - 1; c++)
							{
								((JTextField)table[r][c]).setEditable(false);
								stra[c - 1] = ((JTextField)table[r][c]).getText();
							}
							String id = ((JTextField)table[r][0]).getText();
							DataAccessObject dao = new DataAccessObject();
							dao.update("update Products set title=?,description=?,price=?,category=? where id="+id, stra);
						}
						
					}
					
				});
				JButton b2 = new JButton("Delete");
				b2.setName(i+"");
				b2.addActionListener(new ActionListener(){

					public void actionPerformed(ActionEvent ae) {
						JButton b = (JButton)ae.getSource();
						int r = Integer.parseInt(b.getName());
						String id = ((JTextField)table[r][0]).getText();
						DataAccessObject dao = new DataAccessObject();
						dao.delete("delete from Products where id=" + id);	
						//self.removeAll();
//						createTable();
//						generateTable();
//						printTable();
						self.dispose();
						new EditableList();
					}
					
				});
				jp.add(b1);
				jp.add(b2);
				table[i][j]=jp;
				
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
	
	private void printTable()
	{
		JPanel jp = new JPanel();
		JScrollPane sp = new JScrollPane(jp);
		sp.setSize(300, 300);
		getContentPane().add(sp);
		jp.setLayout(new GridLayout(table.length, COLS));
		for(int i = 0; i < table.length; i++)
		{
			for(int j = 0; j < table[i].length; j++)
			{
				jp.add(table[i][j]);
			}
		}
	}
	
	public static void main(String[] args)
	{
		JFrame frm = new EditableList();
	}
}

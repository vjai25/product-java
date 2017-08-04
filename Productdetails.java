import java.sql.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.sun.corba.se.pept.transport.Connection;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.awt.Color;

public class Productdetails {

	private JFrame frmProductDetails;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Productdetails window = new Productdetails();
					window.frmProductDetails.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Productdetails() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProductDetails = new JFrame();
		frmProductDetails.getContentPane().setBackground(Color.YELLOW);
		frmProductDetails.setBackground(Color.WHITE);
		frmProductDetails.getContentPane().setForeground(Color.BLACK);
		frmProductDetails.setTitle("Product Details");
		frmProductDetails.setBounds(100, 100, 450, 300);
		frmProductDetails.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProductDetails.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product_id :");
		lblNewLabel.setBounds(115, 26, 86, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmProductDetails.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(211, 24, 86, 20);
		frmProductDetails.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(211, 73, 86, 20);
		frmProductDetails.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton(" Submit");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(150, 187, 89, 23);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		frmProductDetails.getContentPane().add(btnNewButton);
		
		JLabel lblProductName = new JLabel("Product Name :");
		lblProductName.setBounds(98, 75, 103, 14);
		lblProductName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmProductDetails.getContentPane().add(lblProductName);
		
		
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String id=textField.getText();
				String name=textField_1.getText();
				
				if(id.length()==0 | name.length()==0)
				{
					//Sound.failure();
					JOptionPane.showMessageDialog(new JDialog(),"All fields are mandatory");
					return;
				}
				DatabaseHandler q=new DatabaseHandler();
				q.insert(Integer.parseInt(id),name);
				textField_1.setText("");
				textField.setText("");
				
			}
		});
	}
}	
class DatabaseHandler 
{
	static java.sql.Connection con;
	
	public static void getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/veer","root","");

			
			Statement stmt=((java.sql.Connection) con).createStatement();
			String sql="CREATE TABLE IF NOT EXISTS Employee3592(id integer Primary key,name varchar(255))";
			stmt.executeUpdate(sql);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(new JDialog(),""+ e);
		}
	}
	
	public void insert(int id,String name)
	{
		try
		{
			getConnection();
			
			String q="insert into Employee3592(id,name) values(?,?)";
			PreparedStatement pst=((java.sql.Connection) con).prepareStatement(q);
			pst.setInt(1,id);
			pst.setString(2,name);
			
			int i=pst.executeUpdate();
			
			//Sound.success();
			JOptionPane.showMessageDialog(new JDialog(),"1 Record Added");
		}
		catch(Exception e)
		{
			//Sound.failure();
			JOptionPane.showMessageDialog(new JDialog(),"Record Already Exists");
		}
	}
	
	public String query()
	{
		StringBuffer sb=new StringBuffer();
		try
		{
			getConnection();
			
			String q="Select * from Employee3592 order by id";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(q);
			
			sb.append("ID:"+"\t"+"NAME:"+"\n");
			while(rs.next())
			{
				sb.append(rs.getString(1)+"\t"+rs.getString(2)+"\n");
			}
			rs.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(new JDialog(),""+e);
		}
		
		return sb.toString();
	}
}

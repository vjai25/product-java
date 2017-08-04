import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import com.sun.xml.internal.ws.org.objectweb.asm.Label;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class Customer {

	private JFrame frmCustomerEnquiry;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField txtSubmit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer window = new Customer();
					window.frmCustomerEnquiry.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Customer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCustomerEnquiry = new JFrame();
		frmCustomerEnquiry.setTitle("Customer Enquiry");
		frmCustomerEnquiry.setBackground(Color.WHITE);
		frmCustomerEnquiry.getContentPane().setForeground(Color.BLACK);
		frmCustomerEnquiry.getContentPane().setBackground(Color.YELLOW);
		frmCustomerEnquiry.setBounds(100, 100, 450, 300);
		frmCustomerEnquiry.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCustomerEnquiry.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Customer Name :");
		lblNewLabel_2.setBounds(95, 25, 106, 17);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmCustomerEnquiry.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel(" Email-Id        :");
		lblNewLabel_1.setBounds(105, 91, 106, 18);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmCustomerEnquiry.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("            Product interested  :");
		lblNewLabel.setBounds(27, 120, 163, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmCustomerEnquiry.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("Description     :");
		lblNewLabel_4.setBounds(105, 150, 95, 14);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmCustomerEnquiry.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Quantity :");
		lblNewLabel_5.setBounds(128, 181, 62, 14);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmCustomerEnquiry.getContentPane().add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(211, 24, 86, 20);
		frmCustomerEnquiry.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(211, 55, 86, 20);
		frmCustomerEnquiry.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(211, 88, 86, 20);
		frmCustomerEnquiry.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(211, 117, 86, 20);
		frmCustomerEnquiry.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(211, 148, 86, 20);
		frmCustomerEnquiry.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(211, 179, 86, 20);
		frmCustomerEnquiry.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		txtSubmit = new JTextField();
		txtSubmit.setBounds(152, 230, 86, 20);
		txtSubmit.setBackground(SystemColor.activeCaption);
		txtSubmit.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtSubmit.setText("   SUBMIT");
		frmCustomerEnquiry.getContentPane().add(txtSubmit);
		txtSubmit.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Mobile number  :");
		lblNewLabel_3.setBounds(95, 54, 95, 26);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmCustomerEnquiry.getContentPane().add(lblNewLabel_3);
	}
}

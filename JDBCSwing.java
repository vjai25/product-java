import java.awt.Container;

	import java.awt.GridLayout;

	import java.awt.event.ActionEvent;

	import java.awt.event.ActionListener;

	import java.sql.Connection;

	import java.sql.DriverManager;

	import java.sql.ResultSet;

	import java.sql.Statement;

	 

	import javax.swing.BoxLayout;

	import javax.swing.JButton;

	import javax.swing.JFrame;

	import javax.swing.JLabel;

	import javax.swing.JOptionPane;

	import javax.swing.JPanel;

	import javax.swing.JTextField;

	 

	public class JDBCSwing implements ActionListener {

	 

	    JLabel lblFName,lblLname,lblAddress,lblSalary,lblF,lblL,lblA,lblS,

	    lblFVal,lblLVal,lblAVal,lblSVal;

	    JTextField txtFName,txtLName,txtAddress,txtSalary;

	    JButton btnAdd,btnUpdate,btnDelete,btnPrev,btnNext;

	    ResultSet rs;

	    public static void main(String[] args) {

	        JDBCSwing obj = new JDBCSwing();

	        obj.createUI();

	    }

	    private void createUI()

	    {

	        JFrame frame = new JFrame("JDBC All in One");

	 

	        JPanel pnlInput = new JPanel(new GridLayout(4,2));

	 

	        lblFName = new JLabel("  First Name : ");

	        txtFName = new JTextField(15);

	 

	        lblLname = new JLabel("  Last Name : ");

	        txtLName = new JTextField();

	 

	        lblAddress = new JLabel("  Address : ");

	        txtAddress = new JTextField();

	 

	        lblSalary = new JLabel("  Salary : ");

	        txtSalary = new JTextField();

	 

	        pnlInput.add(lblFName);

	        pnlInput.add(txtFName);

	 

	        pnlInput.add(lblLname);

	        pnlInput.add(txtLName);

	 

	        pnlInput.add(lblAddress);

	        pnlInput.add(txtAddress);

	 

	        pnlInput.add(lblSalary);

	        pnlInput.add(txtSalary);

	 

	        JPanel pnlButton = new JPanel(new GridLayout(1,3));

	 

	        btnAdd = new JButton("Add");

	        btnAdd.addActionListener(this);

	 

	        btnUpdate = new JButton("Update");

	        btnUpdate.addActionListener(this);

	 

	        btnDelete = new JButton("Delete");

	        btnDelete.addActionListener(this);

	 

	        pnlButton.add(btnAdd);

	        pnlButton.add(btnUpdate);

	        pnlButton.add(btnDelete);

	 

	        JPanel pnlNavigate = new JPanel(new GridLayout(1,2));

	        btnPrev = new JButton(" << ");

	        btnPrev.setActionCommand("Prev");

	        btnPrev.addActionListener(this);

	 

	        btnNext = new JButton(" >> ");

	        btnNext.setActionCommand("Next");

	        btnNext.addActionListener(this);

	 

	        pnlNavigate.add(btnPrev);

	        pnlNavigate.add(btnNext);

	 

	        JPanel pnlNavAns = new JPanel(new GridLayout(4,2));

	 

	        lblF = new JLabel("  First Name : ");

	        lblFVal = new JLabel("Val");

	 

	        lblL = new JLabel("  Last Name : ");

	        lblLVal = new JLabel("Val");

	 

	        lblA = new JLabel("  Address : ");

	        lblAVal = new JLabel("Val");

	 

	        lblS = new JLabel("  Salary : ");

	        lblSVal = new JLabel("Val");

	 

	        pnlNavAns.add(lblF);
	        pnlNavAns.add(lblFVal);

	 

	        pnlNavAns.add(lblL);

	        pnlNavAns.add(lblLVal);

	 

	        pnlNavAns.add(lblA);

	        pnlNavAns.add(lblAVal);

	 

	        pnlNavAns.add(lblS);

	        pnlNavAns.add(lblSVal);

	 

	        Container cn = frame.getContentPane();

	        cn.setLayout(new BoxLayout(cn,BoxLayout.Y_AXIS));

	 

	        frame.add(pnlInput);
	        frame.add(pnlButton);

	        frame.add(pnlNavAns);

	        frame.add(pnlNavigate);

	 

	        //If this will not be written, the only frame will be closed

	        // but the application will be active.

	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        frame.pack();
        frame.setVisible(true);

	    }

	    @Override

	    public void actionPerformed(ActionEvent evt) {

	 

	        String action = evt.getActionCommand();

	        if(action.equals("Add"))

	        {

	            addOperation();

	        }else if(action.equals("Update"))

	        {

	            updateOperation();

	        }else if(action.equals("Delete"))

	        {

	            deleteOperation();

	        }else if(action.equals("Prev"))

	        {

	            preNavigation();

	        }else if(action.equals("Next"))

        {

	            nextNavigation();

	        }

	    }

	    private void addOperation()

	    {

	        try

	        {

	            //Load Jdbc Odbc Driver

	            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

	            Connection con = DriverManager.getConnection("jdbc:odbc:ShivaEvening");

	 

	            String sql = "INSERT INTO Employee (FName,LName,Address,Salary) " +

	                    "Values ('"+txtFName.getText()+"'," +

	                            "'"+txtLName.getText()+"'," +

	                            "'"+txtAddress.getText()+"'," +

	                            "'"+txtSalary.getText()+"')";

	 

	            Statement st = con.createStatement();

	            st.execute(sql);

	 

	            JOptionPane.showMessageDialog(null, "Record Added Succesfully.","Record Added",

	                        JOptionPane.INFORMATION_MESSAGE);

	            clearControls();

	        }catch(Exception e)

	        {

	            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",

	                        JOptionPane.ERROR_MESSAGE);

	        }
	    }

	    private void updateOperation()

	    {

	        try

	        {

	            //Load Jdbc Odbc Driver

	            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

	            Connection con = DriverManager.getConnection("jdbc:odbc:ShivaEvening");

	 

	            String sql = "Update Employee " +

	                            "SET LName = '"+txtLName.getText()+"'," +

	                            "Address = '"+txtAddress.getText()+"'," +

	                            "Salary = '"+txtSalary.getText()+"'" +

	                            "Where FName = '"+txtFName.getText()+"'";

	 

	            JOptionPane.showMessageDialog(null, sql,"Record Updated",

	                        JOptionPane.INFORMATION_MESSAGE);

	            Statement st = con.createStatement();

	            st.execute(sql);

	 

	            JOptionPane.showMessageDialog(null, "Record Update Succesfully.",

	                        "Record Updated",JOptionPane.INFORMATION_MESSAGE);

	            clearControls();

	        }catch(Exception e)
        {

	            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",

	                        JOptionPane.ERROR_MESSAGE);

	        }

	    }

	    private void deleteOperation()

	    {

	        int ans = JOptionPane.showConfirmDialog(null,

	                "Are you sure to delete the Record ?", "Delete Record",

	                           JOptionPane.YES_NO_OPTION);

	        if(ans == JOptionPane.YES_OPTION)

	        {

	            try{

	            //Load Jdbc Odbc Driver

	           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

	            Connection con = DriverManager.getConnection("jdbc:odbc:ShivaEvening");

	 
	            String sql = "Delete FROM Employee where FName = '"+txtFName.getText()+"'";

	 

	            Statement st = con.createStatement();

	            st.execute(sql);
	            }catch(Exception e)

	            {

	                JOptionPane.showMessageDialog(null, e.getMessage(),"Error",

	                                JOptionPane.ERROR_MESSAGE);

	            }

	            JOptionPane.showMessageDialog(null, "Record Deleted","Success",

	                        JOptionPane.INFORMATION_MESSAGE);

	        }

	        else

	        {

	            JOptionPane.showMessageDialog(null, "Operation Canceled","Cancel",

	                        JOptionPane.INFORMATION_MESSAGE);

	        }

	    }

	    private void preNavigation()

	    {

	        try{

	            if(rs == null)

	            {

	            //Load Jdbc Odbc Driver

	            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

	            Connection con = DriverManager.getConnection("jdbc:odbc:ShivaEvening");

	 
        String sql = "SELECT * FROM Employee";

	 

	            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,

	                        ResultSet.CONCUR_UPDATABLE);

	            rs = st.executeQuery(sql);

	            }

	            if(rs.previous())

	            {

	                populateValue();

	            }

	            }catch(Exception e)

	            {

	                JOptionPane.showMessageDialog(null, e.getMessage(),"Error",

	                                JOptionPane.ERROR_MESSAGE);

	            }

	    }

	    private void nextNavigation()

	    {

	        try{

	            if(rs == null)

	            {

	            //Load Jdbc Odbc Driver

	            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            Connection con = DriverManager.getConnection("jdbc:odbc:ShivaEvening");

	 

	            String sql = "SELECT * FROM Employee";

	 

	            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,

	                        ResultSet.CONCUR_UPDATABLE);

	            rs = st.executeQuery(sql);

	            }

	           if(rs.next())

	            {

	                populateValue();

	            }

	            }catch(Exception e)

	            {

	                JOptionPane.showMessageDialog(null, e.getMessage(),"Error",

	                                JOptionPane.ERROR_MESSAGE);

	            }

	    }

	    private void populateValue() throws Exception

	    {

	        String fName = rs.getString("FName");

	        String lName = rs.getString("LName");

	        String add = rs.getString("Address");

	        String sal = rs.getString("Salary");

	 

	        lblFVal.setText(fName);

	        lblLVal.setText(lName);

	        lblAVal.setText(add);

	        lblSVal.setText(sal);

	 

	        txtFName.setText(fName);

	        txtLName.setText(lName);

	        txtAddress.setText(add);

	        txtSalary.setText(sal);

	    }

	    private void clearControls()
	    {

	        txtFName.setText("");

	        txtLName.setText("");

	        txtAddress.setText("");

	        txtSalary.setText("");

	    }

	}
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

	 

	public class JDBCSwings  implements ActionListener {


	    JLabel lblId,lblProduct,lblDescription,lblPrice,lblCategory,lblI,lblT,lblD,lblP,lblC,

	    lblIVal,lblTVal,lblDVal,lblPVal,lblCVal;
//id integer Primary key,title varchar(255), description varchar(255), price varchar(255), category varchar(255)";
	    JTextField txtId,txtTitle,txtDescription,txtPrice,txtCategory;

	    JButton btnAdd,btnUpdate,btnDelete,btnPrev,btnNext;

	    ResultSet rs;

	    public static void main(String[] args) {

	        JDBCSwings obj = new JDBCSwings();

	        obj.createUI();
	        
	        obj.fetch();
	        try {
				obj.populateValue();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    private void fetch()
	    {
	    	try
	    	{
	    	Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/veer","root","");

 
            String sql = "SELECT * FROM Products";

           

            Statement st=((java.sql.Connection) con).createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,

                        ResultSet.CONCUR_UPDATABLE);
            

            rs = st.executeQuery(sql);
            rs.next();
            
	    	}
	    	catch(Exception e)
	    	{
	    		System.out.println(e);
	    	}
	    }

	    private void createUI()

	    {

	        JFrame frame = new JFrame("Products details ");

	 

	        JPanel pnlInput = new JPanel(new GridLayout(5,2));

	 

	        lblId = new JLabel("  Id : ");

	        txtId = new JTextField(15);

	 

	        lblProduct = new JLabel("  Product : ");

	        txtTitle = new JTextField();

	 

	        lblDescription = new JLabel("  Description  : ");

	        txtDescription = new JTextField();

	 

	        lblPrice = new JLabel("  Price  : ");

	        txtPrice = new JTextField();


	        lblCategory = new JLabel("  Category : ");

	        txtCategory = new JTextField();


	        pnlInput.add(lblId);

	        pnlInput.add(txtId);

	 

	        pnlInput.add(lblProduct);

	        pnlInput.add(txtTitle);

	 

	        pnlInput.add(lblDescription);

	        pnlInput.add(txtDescription);

	 

	        pnlInput.add(lblPrice);

	        pnlInput.add(txtPrice);
	        
	        pnlInput.add(lblCategory);

	        pnlInput.add(txtCategory);

	 

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

	 

	        JPanel pnlNavAns = new JPanel(new GridLayout(5,2));

	 

	        lblI = new JLabel("  Id : ");

	        lblIVal = new JLabel("Val");

	 

	        lblT = new JLabel("  Product : ");

	        lblTVal = new JLabel("Val");

	 

	        lblD = new JLabel("  Description : ");

	        lblDVal = new JLabel("Val");

	 

	        lblP = new JLabel("  Price : ");

	        lblPVal = new JLabel("Val");
	        
	        
	        
	        lblC = new JLabel("  Category : ");

	        lblCVal = new JLabel("Val");

	 

	        pnlNavAns.add(lblI);
	        pnlNavAns.add(lblIVal);

	 

	        pnlNavAns.add(lblT);

	        pnlNavAns.add(lblTVal);

	 

	        pnlNavAns.add(lblD);

	        pnlNavAns.add(lblDVal);

	 

	        pnlNavAns.add(lblP);

	        pnlNavAns.add(lblPVal);
	        
	        pnlNavAns.add(lblC);

	        pnlNavAns.add(lblCVal);
	        
	        

	 

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

	            Class.forName("com.mysql.jdbc.Driver");

	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/veer","root","");
	            
				String sql="CREATE TABLE IF NOT EXISTS Products(id integer Primary key,title varchar(255), description varchar(255), price int, category varchar(255)";

	 

	            String sql1 = "INSERT INTO Products (id,title,description,price,category) " +

	                    "Values ('"+txtId.getText()+"'," +

	                            "'"+txtTitle.getText()+"'," +

	                            "'"+txtDescription.getText()+"'," +
	                            
	                            "'"+txtPrice.getText()+"'," +

	                            "'"+txtCategory.getText()+"')";

	 

	            Statement st=((java.sql.Connection) con).createStatement();
	            st.execute(sql1);

	 

	            JOptionPane.showMessageDialog(null, "Record Added Succesfully.","Record Added",

	                        JOptionPane.INFORMATION_MESSAGE);

	            clearControls();

	        }catch(Exception e)

	        {

	            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",

	                        JOptionPane.ERROR_MESSAGE);

	        }
	        fetch();
	    }

	    private void updateOperation()

	    {

	        try

	        {

	            //Load Jdbc Odbc Driver

	            Class.forName("com.mysql.jdbc.Driver");

	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/veer","root","");

	 

	            String sql = "Update Products " +

	                           "SET Title = '"+txtTitle.getText()+"'," +

	                            "  Description = '"+txtDescription.getText()+"'," +

	                            "  Price = '"+txtPrice.getText()+"'," +
	                            
 								" Category = '"+txtCategory.getText()+"'" +

	                            "Where Id = '"+txtId.getText()+"'";

	 

	            JOptionPane.showMessageDialog(null, sql,"Record Updated",

	                        JOptionPane.INFORMATION_MESSAGE);

	            Statement st=((java.sql.Connection) con).createStatement();

	            st.execute(sql);

	 

	            JOptionPane.showMessageDialog(null, "Record Update Succesfully.",

	                        "Record Updated",JOptionPane.INFORMATION_MESSAGE);

	            clearControls();

	        }catch(Exception e)
        {

	            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",

	                        JOptionPane.ERROR_MESSAGE);

	        }
	        fetch();

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

	           Class.forName("com.mysql.jdbc.Driver");

	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/veer","root","");

	 
	            String sql = "Delete FROM Products where Id = '"+txtId.getText()+"'";

	 

	            Statement st=((java.sql.Connection) con).createStatement();


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
	        fetch();

	    }

	    private void preNavigation()

	    {

	        try{


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

	        String Id = rs.getString("id");

	        String Product = rs.getString("title");

	        String Description = rs.getString("description");

	        String Price = rs.getString("price");
	        
	        String Category = rs.getString("category");

	 

	        lblIVal.setText(Id);

	        lblTVal.setText(Product);

	        lblDVal.setText(Description);

	        lblPVal.setText(Price);
	        
	        lblCVal.setText(Category);

	 

//	        txtId.setText(Id);
//
//	        txtTitle.setText(Product);
//
//	        txtDescription.setText(Description);
//
//	        txtPrice.setText(Price);
//	        
//	        txtCategory.setText(Category);

	    }

	    private void clearControls()
	    {

	        txtId.setText("");

	        txtTitle.setText("");

	        txtDescription.setText("");

	        txtPrice.setText("");
	        
	        txtCategory.setText("");

	    }

	}
   import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import java.sql.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.io.IOException;
import java.util.Date.*;
import java.util.EventObject;

    public class Table extends JFrame 
    {
    	PreparedStatement ps;
    	Connection con;
    	ResultSet rs;
    	Statement st;
    	
    	String title,descrition,category;
    	int id,price;
    	Date d1,d2;
        int rows = 0;
    	Object data1[][];
    	JScrollPane scroller;
    	JTable table;
    	  
    	  
        public Table()
        {
        	
         
                Container cp = getContentPane();
        		cp.setLayout(new BorderLayout());
       			
       			 setSize(600,600);
        		 setLocation(50,50);
       		     setLayout(new BorderLayout());
       			 setTitle("List");
       		
       	    	
    		
    			try{
    			Class.forName("com.mysql.jdbc.Driver");
    			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/veer","root","");
    		}catch(Exception e){}
    		
    		
    		try {
    			st = con.createStatement ();	//Creating Statement Object.
    		}
    		catch (SQLException sqlex) {			//If Problem then Show the User a Message.
     			JOptionPane.showMessageDialog (null, "A Problem Occurs While Loading Form.");
     			dispose ();				//Closing the Form.
    	 	}
        
        	
        	
        	try{
        	
    			
        	    rs=st.executeQuery("SELECT * from Products");
        	    	
        	while(rs.next())
        	{
        	 
        	  
        	  int id = rs.getInt(1);
        	  String title = rs.getString(2);
        	  String description = rs.getString(3);
        	  int price = rs.getInt(4);
        	  String category = rs.getString(5);
        	 
        	  rows++;
        	
        	}  
        		
        
        
        		
        		
    				data1=new Object[rows][5];
    				
    				Object[] Colheads={"Id","Product Name","Descripstion","Price","Category"};
    				 rs=st.executeQuery("Select * from Products");
    				
    				for(int i1=0;i1<rows;i1++)
    				{
    						rs.next();
    						int j1;
    						for(j1=0;j1<5;j1++)
    						{
    							data1[i1][j1]=rs.getString(j1+1);
    						}
    				}
    				JTable table=new JTable(data1,Colheads);
    				 AcceptRejectRenderer renderer = new AcceptRejectRenderer();
 	                table.getColumnModel().getColumn(3).setCellRenderer(renderer);
 	                table.getColumnModel().getColumn(3).setCellEditor(new AcceptRejectEditor());
 	                table.setRowHeight(renderer.getTableCellRendererComponent(table, null, true, true, 0, 0).getPreferredSize().height);
    				int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
    				int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
    				
    				JScrollPane jsp=new JScrollPane(table,v,h);
    				
    		
    			 getContentPane().add(jsp);
    		
        		}catch(Exception e)
        	{
        	}
        
        try{
        	data1=new Object[rows][6];
    				
    				Object[] Colheads={"Id","Product Name","Descripstion","Price","Category","Action"};
    				 rs=st.executeQuery("Select * from Products");
    				
    				for(int i1=0;i1<rows;i1++)
    				{
    						rs.next();
    						for(int j1=0;j1<5;j1++)
    						{
    							data1[i1][j1]=rs.getString(j1+1);
    						}
    				}
    				
    				TableModel i = null;
					TableColumnModel j = null;
					JTable table=new JTable(i,j);
    				 AcceptRejectRenderer renderer = new AcceptRejectRenderer();
 	                table.getColumnModel().getColumn(3).setCellRenderer(renderer);
 	                table.getColumnModel().getColumn(3).setCellEditor(new AcceptRejectEditor());
 	                table.setRowHeight(renderer.getTableCellRendererComponent(table, null, true, true, 0, 0).getPreferredSize().height);
    				int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
    				int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
    				
    				JScrollPane jsp=new JScrollPane(table,v,h);
    				
    			
    				getContentPane().add(jsp);
        }
        catch(Exception e){
        }
        	
        	
        	setVisible(true);
        	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        }
        public class AcceptRejectPane extends JPanel {

            private JButton accept;
            private JButton reject;
            private String state;

            public AcceptRejectPane() {
                setLayout(new GridBagLayout());
                accept = new JButton("Accept");
                accept.setActionCommand("accept");
                reject = new JButton("Reject");
                reject.setActionCommand("reject");

                add(accept);
                add(reject);

                ActionListener listener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        state = e.getActionCommand();
                        System.out.println("State = " + state);
                    }
                };

                accept.addActionListener(listener);
                reject.addActionListener(listener);
            }

            public void addActionListener(ActionListener listener) {
                accept.addActionListener(listener);
                reject.addActionListener(listener);
            }

            public String getState() {
                return state;
            }
        }

        public class AcceptRejectRenderer extends DefaultTableCellRenderer {

            private AcceptRejectPane acceptRejectPane;

            public AcceptRejectRenderer() {
                acceptRejectPane = new AcceptRejectPane();
            }

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (isSelected) {
                    acceptRejectPane.setBackground(table.getSelectionBackground());
                } else {
                    acceptRejectPane.setBackground(table.getBackground());
                }
                return acceptRejectPane;
            }
        }

        public class AcceptRejectEditor extends AbstractCellEditor implements TableCellEditor {

            private AcceptRejectPane acceptRejectPane;

            public AcceptRejectEditor() {
                acceptRejectPane = new AcceptRejectPane();
                acceptRejectPane.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                stopCellEditing();
                            }
                        });
                    }
                });
            }

            @Override
            public Object getCellEditorValue() {
                return acceptRejectPane.getState();
            }

            @Override
            public boolean isCellEditable(EventObject e) {
                return true;
            }

            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                if (isSelected) {
                    acceptRejectPane.setBackground(table.getSelectionBackground());
                } else {
                    acceptRejectPane.setBackground(table.getBackground());
                }
                return acceptRejectPane;
            }
        }
    
        public static void main(String args[])
        {
         JFrame frm = new Table();
           			 frm.setSize(600, 600);
           			 frm.setLocation(50,50);
           			 BufferedImage image = null;
            try {
                image = ImageIO.read(frm.getClass().getResource("girl.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            frm.setIconImage(image);
           			 frm.setVisible(true);
           			 frm.show();
        }
     }
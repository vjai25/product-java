import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DataAccessObject {
	private PreparedStatement ps;
	private Connection con;
	
	public DataAccessObject()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/veer", "root", "");
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		
	}
	
	public void insert(String query)
	{
		
	}
	public void update(String query, String[] stra)
	{
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, stra[0]);
			ps.setString(2, stra[1]);
			ps.setInt(3, Integer.parseInt(stra[2]));
			ps.setString(4, stra[3]);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	public void delete(String query)
	{
		try {
			ps = con.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	public ResultSet select(String query)
	{
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return rs;	
	}
	public int count(String query)
	{
		int rows = 0;
		try {
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			rs.next();
			rows = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}

}
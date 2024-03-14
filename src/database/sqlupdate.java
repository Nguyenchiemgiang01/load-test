package database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlupdate {

	static Connection con;
	public static String query;
	
	static int count;
	public  sqlupdate(Connection con,String query) {
		sqlupdate.con=con;
		sqlupdate.query=query;
		
	}
	public int updatequerytest() {
		if(con!=null)
		{
		try {
			
			PreparedStatement st= con.prepareStatement(query);
			count=st.executeUpdate(query);
			st.close();			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}}
		else {return -1;}
		
	return count;
	}
	
	
}

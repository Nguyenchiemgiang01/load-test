package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlinsert {

	public static Connection con;
	public static String query;
	
	
	static int count;
	public  sqlinsert(Connection con,String query) {
		sqlinsert.con=con;
		sqlinsert.query=query;
	}
	public int insertquerytest() {
		if(con!=null) {
		try {
			Statement st= con.createStatement();
			 count =st.executeUpdate(query);

			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {return -1;}
	return count;
	}
}

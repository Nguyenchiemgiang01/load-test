
package database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class sqldelete {

		static Connection con;
		public static String query;
		
		static int count;
		public  sqldelete(Connection con,String query) {
			sqldelete.con=con;
			sqldelete.query=query;
			
		}
		public int deletequerytest() {
			if(con!=null) {
			try {
				
				PreparedStatement st= con.prepareStatement(query);
				count=st.executeUpdate(query);
				
				st.close();			
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}}
			else {return -1;
			}
			
		return count;
		}
		
		
	}



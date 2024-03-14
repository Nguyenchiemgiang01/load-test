package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class sqlselect {

	public static Connection con;
	public static String query;
	public static int  sumcol;
	static int count;
	public  sqlselect(Connection con,String query) {
		sqlselect.con=con;
		sqlselect.query=query;
	}
	public ArrayList<String> selectquerytest() {
		
			ArrayList<String> result= new ArrayList<String>();
			
		try {
			Statement st= con.createStatement();
			
			
			ResultSet rs=st.executeQuery(query);
			ResultSetMetaData rsMetaData = rs.getMetaData();
			 sumcol=rsMetaData.getColumnCount();//lay tong so hang truy van duoc
			
			while(rs.next()) {
				int i=1;
				String rowstring="";
				while(i<=sumcol) {
				rowstring =rowstring+rs.getString(i++)+"";
				}
				
				result.add(rowstring+"\n");
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return result;
	}
}

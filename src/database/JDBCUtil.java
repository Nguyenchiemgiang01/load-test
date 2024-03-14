package database;

import java.io.IOException;
import java.sql.Connection;
import org.apache.commons.dbcp2.BasicDataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.concurrent.Callable;

import javax.sql.DataSource;



public class JDBCUtil implements Callable<List<String>> {
			public static String URLdatabase;
			public static String JDBCDriverclass;
			public static String username;
			public static String password;
			public static String str;
			public static int maxwait;
			public static int exurlconnnection,extimeout;
			//public static BasicDataSource ds;
			
			
		
			long ts = 0;
			long t = 0;
			
			
			int status=0;
			static Exception exceptions;
			
			public static Connection c;

			
			public JDBCUtil(String uRLdatabase, String jDBCDriverclass, String username, String password,String query,int maxwait) {
				
				JDBCUtil.URLdatabase = uRLdatabase;
				JDBCUtil.JDBCDriverclass = jDBCDriverclass;
				JDBCUtil.username = username;
				JDBCUtil.password = password;
				JDBCUtil.str=query;
				JDBCUtil.maxwait=maxwait;
				

				
			}

			
			
		
			public static Connection  Connecttion() throws ClassNotFoundException {
				
				Connection con = null;
				

				Class.forName(JDBCDriverclass);
				try {
					DriverManager.setLoginTimeout(maxwait/1000);
					con=DriverManager.getConnection(URLdatabase,username+"",password+"");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					exurlconnnection=e1.getErrorCode();
					
					//e1.printStackTrace();
					
				}

				return con;
			}
			
			
			
			
			public static List<String> query(String sql) throws SQLException, ClassNotFoundException{
				List<String> re= new ArrayList<String>();

				c=Connecttion();

				if (c==null) {
					System.out.print(" c null \n");
					re.add("can't connect");
					return re;
				}
				else {
				
									
				if(sql.contains("select")) {
					sqlselect sqls= new sqlselect(c, sql);
					re=sqls.selectquerytest();
					
				}
				else if(sql.contains("update")){
					int count=-1;
					sqlupdate sqlu=new sqlupdate(c, sql);
					count=sqlu.updatequerytest();
					if(count==-1) {
						re.add("error");
					}else {
						re.add(String.valueOf("row affected "+count));
					}
					
				}
				else if(sql.contains("insert")) {
					int count=-1;
					sqlinsert sqli= new sqlinsert(c,sql);
					count=sqli.insertquerytest();
					if(count==-1) {
						re.add("error");
					}else {
						re.add(String.valueOf(count+" updates"));
					}
					
					}
				else if(sql.contains("delete")) {
					int count=-1;
					sqldelete sqld= new sqldelete(c,sql);
					count=sqld.deletequerytest();
					System.out.print(count+"\n");	
					if(count==-1) {
						re.add("error");
					}else {
						re.add(String.valueOf(count+" deleted"));
					}
					
					}
				}
				return re;
			}
			
			public List<String> call() throws SQLException, ClassNotFoundException{
				List<String> res = new ArrayList<String>();
				long t_start= System.currentTimeMillis();
				List<String> re= new ArrayList<String>();
				re=query(str);
				this.t=System.currentTimeMillis()-t_start;
				this.ts = t_start;
				res.add(t+"");
				res.add(this.ts+"");
				if(re!=null)
				{
				res.addAll(re);}
				else {
					res.add("no response!");
				}
				System.out.print("res:"+res+"\n");
				return res;
				
			}
		
		
}

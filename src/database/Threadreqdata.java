package database;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import Test.request;

public class Threadreqdata {
	public static String URLdatabase;
	
	public static String JDBCDriverclass;
	public static String username;
	public static String password;
	public static int maxconnetion;
	public static int maxwait;
	public static int numberthread;
	public static String str;
	
	
	public static Exception exceptions;
	
	public Threadreqdata(String url, String JDBCDriver, String username,String password, int maxconnection,int maxwait,int numberthread,String query) {
		Threadreqdata.URLdatabase=url;
		Threadreqdata.JDBCDriverclass=JDBCDriver;
		Threadreqdata.username=username;
		Threadreqdata.password=password;
		Threadreqdata.maxconnetion=maxconnection;
		Threadreqdata.maxwait=maxwait;
		Threadreqdata.numberthread=numberthread;
		Threadreqdata.str=query;
	}

	public ArrayList<List<String>> requestdata() throws   ClassNotFoundException, SQLException, ExecutionException 
	{

		
		ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(maxconnetion);
		executorService.setKeepAliveTime(maxconnetion, TimeUnit.MILLISECONDS);
		executorService.setMaximumPoolSize(maxconnetion);
		ArrayList<List<String>>  list= new ArrayList<List<String>>();
		ArrayList<Future<List<String>>> listFuture = new ArrayList<Future<List<String>>>();
		
		
	//	ThreadPoolExecutor executor=(ThreadPoolExecutor) Executors.newFixedThreadPool(maxconnetion);
		 //final CountDownLatch latch = new CountDownLatch(maxconnetion);
		
		for (int i = 0; i < numberthread; i++) {
			 
			 listFuture.add(executorService.submit(new  JDBCUtil(URLdatabase,JDBCDriverclass,username,password,str,maxwait)));
			//exceptions=sq.exceptions;
		}
		
		 
//		
		for (Future future : listFuture) {
			 
			try {
				list.add( (List<String>) future.get());
				//System.out.print("1:::"+future.get()+"\n");
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// khi thread ket thuc thi in ra nul
			future.cancel(false);
		      
		    }
		System.out.print(executorService.toString());
			executorService.shutdown();
		
		
		 return list;

	}
	
	
	
}

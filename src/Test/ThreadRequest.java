package Test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.lang.model.element.NestingKind;

class ThreadRequest {
	static String rprotocol;
	static String rip;
	static String rmethod;
	static String rpath;
	static int rnumberthread;
	static long rrampup = 0;
	static String rpost_parras;
	public ThreadRequest(String protocol,String ip,String method,String path,int numberthread, long rampup,String post_parras) {
		rprotocol=protocol;
		rip=ip;
		rmethod=method;
		rpath=path;
		rnumberthread=numberthread;
		rrampup = rampup ; 
		rpost_parras=post_parras;
	}

	public static List<List<Long>> request() throws InterruptedException, ExecutionException 
	{
		ExecutorService executorService = Executors.newFixedThreadPool(rnumberthread);
		List<List<Long>> list= new ArrayList<List<Long>>();
		List<Future<List<Long>>> listFuture = new ArrayList<Future<List<Long>>>();
		for (int i = 0; i < rnumberthread; i++) {
			request sq= new request(rprotocol, rip, rmethod,rpath, rnumberthread,rpost_parras);
	        listFuture.add(executorService.submit(sq));
	        Thread.sleep(1000*rrampup/rnumberthread);
		}
		for (Future future : listFuture) {
			 
			 try {
				list.add((List<Long>) future.get());
				
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}
		      
		    }
		 return list;

	}
}


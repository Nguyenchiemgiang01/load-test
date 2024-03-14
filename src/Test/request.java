package Test;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


public class request implements Callable<List<Long>> {
	String rprotocol;
	String rip;
	String rmethod;
	int rnumberthread;
	String rpath;
	long ts = 0;
	long t = 0;
	String rpost_parras;
	
	List<Long> res = new ArrayList<Long>();
		
	public request(String protocol,String ip,String method,String path,int numberthread,String post_parras) {
		rprotocol=protocol;
		rip=ip;
		rmethod=method;
		rpath=path;
		rnumberthread=numberthread;
		rpost_parras=post_parras;
		
	}
	public static HttpURLConnection con;
	public static long call_me(String protocol,String ip,String method,String path,int numberthread,String post_parras) throws Exception {
//		String ur = "http://httpbin.org/ip";
		 
		String url = ""+protocol+"://"+ip+"/"+path+"";
        URL obj=null;
        int status;
        obj = new URL(url);
        
        final String POST_PARAMS = post_parras;
        
        
		con = (HttpURLConnection) obj.openConnection();
		
		con.setConnectTimeout(5000);
		
		con.setRequestMethod(""+method);
		//con.setDoOutput(true);
	 

        
		status = con.getResponseCode();
		
		if (status == HttpURLConnection.HTTP_OK && method.equalsIgnoreCase("GET")==true) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer(); 

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
//			in.close();
			System.out.print("get");
			// print result
			System.out.println(response.toString());
		}
		
		else if(status == HttpURLConnection.HTTP_OK && method.equalsIgnoreCase("POST")==true) {
			status = 700;
			post post= new post(url,POST_PARAMS);
			post.POSTRequest();
			status=post.responseCode;
		    } 
		    
		
		else {
			System.out.println("request not worked");
		}
        return (long)status;
        }
	
	public List<Long> call() {
	
	long ans = 0;
	long t_start= System.currentTimeMillis();
	long status_code = 0;
	try {
		status_code = call_me(rprotocol,rip,rmethod,rpath,rnumberthread,rpost_parras);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	this.t=System.currentTimeMillis()-t_start;
	this.ts = t_start;
	res.add(t);
	res.add(this.ts);
	res.add(status_code);
	return res;
}
	
}





	


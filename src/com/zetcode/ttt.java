package com.zetcode;
//
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//
//public class ttt {
//
//	public static String url;
//	public static String post_para;
//	public ttt(String url,String post_para) {
//		ttt.url=url;
//		ttt.post_para=post_para;
//	}
//  
//    public static void POSTRequest() throws IOException {
//
//
//        URL obj = new URL(url);
//        
//        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
//        postConnection.setRequestMethod("POST");
//        postConnection.setRequestProperty("userId", "a1bcdefgh");
//        postConnection.setRequestProperty("Content-Type", "application/json");
//
//
//        postConnection.setDoOutput(true);
//        OutputStream os = postConnection.getOutputStream();
//        os.write(post_para.getBytes());
//        os.flush();
//        os.close();
//
//
//        int responseCode = postConnection.getResponseCode();
//        System.out.println("POST Response Code :  " + responseCode);
//        System.out.println("POST Response Message : " + postConnection.getResponseMessage());
//
//        if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
//            BufferedReader in = new BufferedReader(new InputStreamReader(
//                postConnection.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//
//            while ((inputLine = in .readLine()) != null) {
//                response.append(inputLine);
//            } in .close();
//
//            // print result
//            System.out.println(response.toString());
//        } else {
//            System.out.println("POST NOT WORKED");
//        }
//    } 
//}
public class ttt{
	public static void main(String args[] ) throws InterruptedException {
		 System.out.println("Main thread start");
         long startMain = System.currentTimeMillis();
         
         Thread.sleep(1000);
         for (int i=0;i<10;i++) {
        	 Thread t = new Thread(() -> {
                 System.out.println("Child thread start");
                 long start = System.currentTimeMillis();
				
				 System.out.println("Child thread:" + (System.currentTimeMillis() - start));
             });
             
             t.start();
             Thread.sleep(1000);
         }
        
         System.out.println("Main thread:" + (System.currentTimeMillis() - startMain));
}
}

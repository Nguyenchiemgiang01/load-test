package Test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class post {

	public static String url;
	public static String post_para;
	public static int responseCode;
	public static String responseMessage;
	public post(String url,String post_para) {
		post.url=url;
		post.post_para=post_para;
	}
  
    public static void POSTRequest() throws IOException {


        URL obj = new URL(url);
        
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        postConnection.setRequestMethod("POST");
        postConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        postConnection.setDoOutput(true);
        OutputStream os = postConnection.getOutputStream();
        os.write(post_para.getBytes(StandardCharsets.UTF_8));
        os.flush();
        os.close();


         responseCode = postConnection.getResponseCode();
         responseMessage=postConnection.getResponseMessage();
        System.out.println("POST Response Code :  " + responseCode);
        System.out.println("POST Response Message : " + postConnection.getResponseMessage());

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                postConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST NOT WORKED");
        }
    } 
}
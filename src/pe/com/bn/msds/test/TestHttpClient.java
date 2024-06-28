package pe.com.bn.msds.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.json.JSONException;
import org.json.JSONObject;

public class TestHttpClient {

	public static void main(String[] args) throws ClientProtocolException, IOException, JSONException {

		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://10.7.12.75:80/account-statements/accountStatementId");
		
		JSONObject json = new JSONObject();
		json.put("accountStatementId", "07229985-202201-202212");
		json.put("token", "ambpWYU8546uwb!6653thaBD76qaet");
		/**
		{"accountStatementId":"07229985-202201-202212",
		"token":"ambpWYU8546uwb!6653thaBD76qaet"}
		**/
		
		StringEntity stringEntity = new StringEntity(json.toString());
		
		httpPost.setEntity(stringEntity);
		httpPost.setHeader("Content-Type", "application/json");
		
		HttpResponse httpResponse = client.execute(httpPost);
		
		StatusLine statusLine = httpResponse.getStatusLine();
		
		System.out.println("StatusCode: " + statusLine.getStatusCode());
		System.out.println("ReasonPhrase: " + statusLine.getReasonPhrase());
		
//		BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
//		
//		String line = "";
//		
//		while ((line = rd.readLine()) != null) {
//			System.out.println(line);
//		}
		
		JsonFactory jsonf = new JsonFactory();
        InputStream inputStream = httpResponse.getEntity().getContent();
        
        		
        // try - finally is not strictly necessary here 
        // but is a good practice
        JsonParser jsonParser = jsonf.createJsonParser(inputStream);
    	
        if (statusLine.getStatusCode() == 200) {
			
			jsonParser.nextToken();
			jsonParser.nextToken();
			System.out.println("getText 1: " + jsonParser.getText());

			jsonParser.nextToken();
			System.out.println("getText 2: " + jsonParser.getText());
			
		} else {
			/**
			getText 1: timestamp
			getText 2: 2023-02-15T23:23:34.976+0000
			getText 3: message
			getText 4: No existe accountStatementId
			**/
			
			jsonParser.nextToken();
			jsonParser.nextToken();
			System.out.println("getText 1: " + jsonParser.getText());

			jsonParser.nextToken();
			System.out.println("getText 2: " + jsonParser.getText());

			jsonParser.nextToken();
			System.out.println("getText 3: " + jsonParser.getText());

			jsonParser.nextToken();
			System.out.println("getText 4: " + jsonParser.getText());
		}
        
//        jsonParser.nextToken();
//		jsonParser.nextToken();
//		System.out.println("getText 1: " + jsonParser.getText());
//
//		jsonParser.nextToken();
//		System.out.println("getText 2: " + jsonParser.getText());
//
//		jsonParser.nextToken();
//		System.out.println("getText 3: " + jsonParser.getText());
//
//		jsonParser.nextToken();
//		System.out.println("getText 4: " + jsonParser.getText());
//		
//		jsonParser.nextToken();
//		System.out.println("getText 5: " + jsonParser.getText());
//
//		jsonParser.nextToken();
//		System.out.println("getText 6: " + jsonParser.getText());
//		
//		jsonParser.nextToken();
//		System.out.println("getText 7: " + jsonParser.getText());
//
//		jsonParser.nextToken();
//		System.out.println("getText 8: " + jsonParser.getText());
//		
    	inputStream.close();
        
        
	}
}

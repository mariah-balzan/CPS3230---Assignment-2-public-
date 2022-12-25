package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.google.gson.*;

public class Test{
	
	public static void main(String[] args) throws IOException{

		
		Test test = new Test();
		System.out.println("Testing 1 - Send Http POST request");
	    test.sendPost();
	    
	    System.out.println("Testing 2 - Send Http GET request");
	    test.sendGet();
	    
//	    System.out.println("Testing 3 - Send Http DELETE request");
//	    test.sendDelete();
//	    
//	    System.out.println("Verify GET request");
//	    test.sendGet();
	}
	
public void sendPost() throws IOException{
	String url = "https://api.marketalertum.com/Alert";
	
	Alerts alerts = new Alerts(5, "WeFine Puppy Dog Chew Toys Teething Training, 10pcs Dog Rope Toys 100% Natural Cotton Rope for Small and Medium Dog ", "", "https://www.amazon.co.uk/WeFine-Teething-Training%EF%BC%8C10pcs-Natural-Cotton/dp/B07WD8QPRB/ref=sr_1_5?crid=2O4UBUX7JYK78&keywords=WeFine+Puppy+Dog+Chew+Toys+Teething+Training%2C+10pcs+Dog+Rope+Toys+100%25+Natural+Cotton+Rope+for+Small+and+Medium+Dog&qid=1668250494&sprefix=wefine+puppy+dog+chew+toys+teething+training+10pcs+dog+rope+toys+100%25+natural+cotton+rope+for+small+and+medium+dog%2Caps%2C134&sr=8-5", "https://m.media-amazon.com/images/I/61CMYjFxkfL._AC_SX679_.jpg", "d98f7243-8f53-4523-b11f-f80fe9fe8230", 1199);
	
	String json = new Gson().toJson(alerts);
	OkHttpClient httpClient = new OkHttpClient();
 
	
	// form parameters
    RequestBody body = RequestBody.create( MediaType.parse("application/json ; charset=utf-8"), json);
	
    Request request = new Request.Builder()
    .url(url)
    .addHeader("Content-Type", "application/json")
    .post(body)
    .build();
    

    try (Response response = httpClient.newCall(request).execute()) {

        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        // Get response body
        System.out.println(response.body().string());
    }
}

//private static Alerts getAlertsObject(){
	//Storing pre-processed json (Added slashes)
//	String AlertsJson = "{\"alertType\":5," +
//			"\"heading\":\"WeFine Puppy Dog Chew Toys Teething Training, 10pcs Dog Rope Toys 100% Natural Cotton Rope for Small and Medium Dog \"," +
//			"\"description\":\" \"," +
//			"\"url\":\"https://www.amazon.co.uk/WeFine-Teething-Training%EF%BC%8C10pcs-Natural-Cotton/dp/B07WD8QPRB/ref=sr_1_5?crid=2O4UBUX7JYK78&keywords=WeFine+Puppy+Dog+Chew+Toys+Teething+Training%2C+10pcs+Dog+Rope+Toys+100%25+Natural+Cotton+Rope+for+Small+and+Medium+Dog&qid=1668250494&sprefix=wefine+puppy+dog+chew+toys+teething+training+10pcs+dog+rope+toys+100%25+natural+cotton+rope+for+small+and+medium+dog%2Caps%2C134&sr=8-5\"," +
//			"\"imageUrl\":\"https://m.media-amazon.com/images/I/61CMYjFxkfL._AC_SX679_.jpg\"," +
//			"\"postedBy\":\"d98f7243-8f53-4523-b11f-f80fe9fe8230\",\"priceInCents\":1199}";

	
//	//Creating Gson object
//	Gson gson = new Gson();
//	
//	//Converting json to object 
//	//first parameter should be preprocessed json
//	//and second should be mapping class
//	Alerts alerts = gson.fromJson(AlertsJson, Alerts.class);
//	
//	//Return object
//	return alerts;
//}

public void sendGet(){
String url = "https://api.marketalertum.com/EventsLog/d98f7243-8f53-4523-b11f-f80fe9fe8230";
		 try {
			HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
			httpClient.setRequestMethod("GET");
			httpClient.setRequestProperty("Content-Type", "application/json");
			try (BufferedReader in = new BufferedReader(
	                new InputStreamReader(httpClient.getInputStream()))) {
	            StringBuilder response = new StringBuilder();
	            String line;

	            while ((line = in.readLine()) != null) {
	                response.append(line);
	            }

	            //print result
	            System.out.println(response.toString());

	        }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
public  void sendDelete()throws IOException{
String url = "https://api.marketalertum.com/Alert";
	
	Alerts alerts = new Alerts(5, "WeFine Puppy Dog Chew Toys Teething Training, 10pcs Dog Rope Toys 100% Natural Cotton Rope for Small and Medium Dog ", "", "https://www.amazon.co.uk/WeFine-Teething-Training%EF%BC%8C10pcs-Natural-Cotton/dp/B07WD8QPRB/ref=sr_1_5?crid=2O4UBUX7JYK78&keywords=WeFine+Puppy+Dog+Chew+Toys+Teething+Training%2C+10pcs+Dog+Rope+Toys+100%25+Natural+Cotton+Rope+for+Small+and+Medium+Dog&qid=1668250494&sprefix=wefine+puppy+dog+chew+toys+teething+training+10pcs+dog+rope+toys+100%25+natural+cotton+rope+for+small+and+medium+dog%2Caps%2C134&sr=8-5", "https://m.media-amazon.com/images/I/61CMYjFxkfL._AC_SX679_.jpg", "d98f7243-8f53-4523-b11f-f80fe9fe8230", 1199);
	
	String json = new Gson().toJson(alerts);
	OkHttpClient httpClient = new OkHttpClient();
 
	
	// form parameters
    RequestBody body = RequestBody.create( MediaType.parse("application/json ; charset=utf-8"), json);
	
    Request request = new Request.Builder()
    .url(url)
    .addHeader("Content-Type", "application/json")
    .delete(body)
    .build();
    

    try (Response response = httpClient.newCall(request).execute()) {

        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        // Get response body
        System.out.println(response.body().string());
    }
    
	}
}

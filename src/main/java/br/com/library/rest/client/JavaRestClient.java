package br.com.library.rest.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;

public class JavaRestClient {
	
	private static final String GET_BY_ID_URL = "http://localhost:8080/library/v1/protected/book/1";

	public static void main(String[] args) {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		
		try {
			URL url = new URL(GET_BY_ID_URL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("Authorization", "Basic " + encodeUsernameAndPassword("jader", "senha"));
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			StringBuilder jsonSB = new StringBuilder();
			String line;
			
			while((line = reader.readLine()) != null) {
				jsonSB.append(line);
			}
			
			System.out.println(jsonSB.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			IOUtils.closeQuietly(reader);
			if(connection != null) {
				connection.disconnect();
			}
		}
	}
	
	private static String encodeUsernameAndPassword(String username, String password) {
		String userPassword = username + ":" + password;
		return new String(Base64.encodeBase64(userPassword.getBytes()));
	}
}

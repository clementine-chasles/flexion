package flexion.integrationteam.handler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import flexion.integrationteam.App;
import flexion.integrationteam.exception.ErrorResponseException;
import flexion.integrationteam.util.Strings;

@Component
public class HttpHandler {
	
	@Value("${url.flexion.api}")
	private String urlFlexion;

	private final static int HTTP_OK = 200;
	private Map<String, String> args;

	protected HttpHandler() {}

	protected HttpHandler(String urlFlexion) {
		this.urlFlexion = urlFlexion;
	}

	protected void addArgs(String key, String value) {
		if (args == null) {
			args = new LinkedHashMap<String, String>();
		}
		args.put(key, value);
	}

	private final String USER_AGENT = "Mozilla/5.0";

	private String loadArgs() {
		String res = urlFlexion;
		if (args != null) {
			Iterator<Map.Entry<String, String>> it = args.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> pair = (Map.Entry<String, String>) it
						.next();
				res += "/"
						+ pair.getKey()
						+ (!Strings.isNullOrEmpty(pair.getValue()) ? ("/" + pair
								.getValue()) : "");
				it.remove();
			}
		}
		return res;
	}

	// HTTP GET request
	protected JSONObject sendGet() throws Exception {

		String url = loadArgs();
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("Sending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		if (HTTP_OK != responseCode) {
			throw new ErrorResponseException(responseCode);
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return (JSONObject) JSONValue.parse(response.toString());

	}

	// HTTP POST request
	protected JSONObject sendPost() throws Exception {

		String url = loadArgs();
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		// Send post request
		con.setDoOutput(true);

		int responseCode = con.getResponseCode();
		System.out.println("Sending 'POST' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

		in.close();
		return (JSONObject) JSONValue.parse(response.toString());

	}

}
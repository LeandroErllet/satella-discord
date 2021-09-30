package br.com.craftlife.satella.discord.util;

import org.json.JSONObject;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class NetUtils {

	public static final int JSON_ERROR_CODE = 603;

	public static Document getDoc(String url) throws IOException {
		return Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
				.timeout(20_000)
				.get();
	}

	public static Response getResponse(String url) throws IOException {
		return Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
				.timeout(20_000)
				.ignoreContentType(true)
				.ignoreHttpErrors(true)
				.execute();
	}

	public static String getBody(String url) throws IOException {
		return NetUtils.getResponse(url).body();
	}

	public static JSONObject getJSON(String url) throws IOException {
		String json = NetUtils.getBody(url);
		if(json.isEmpty() || json.charAt(0) != '{' && json.charAt(0) != '[') {
			return null;
		}
		return new JSONObject(json);
	}

	public static String encode(String str) throws UnsupportedEncodingException {
		return URLEncoder.encode(str, "UTF-8");
	}

	public static boolean isValidURL(String url) {
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.connect();
			return true;

		} catch (Exception err) {
			return false;

		} finally {
			if(conn != null) {
				conn.disconnect();
			}
		}
	}

}
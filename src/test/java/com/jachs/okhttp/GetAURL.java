package com.jachs.okhttp;

import java.io.IOException;

import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/***
 * 
 * @author zhanchaohan
 *
 */
public class GetAURL {
	private static final String URL="http://127.0.0.1:8080/get/A?pam1='你'&pam2='好'";
	OkHttpClient client = new OkHttpClient();
	
	@Test
	public void test1() throws IOException {
		System.out.println(run(URL));
	}
	public String run(String url) throws IOException {
		 Request request = new Request.Builder()
		      .url(url)
		      .build();

		Response response = client.newCall(request).execute();
		return response.body().string();
	}
}

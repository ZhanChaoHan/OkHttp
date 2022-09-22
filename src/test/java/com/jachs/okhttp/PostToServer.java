package com.jachs.okhttp;

import java.io.IOException;
import org.junit.Test;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/***
 * 
 * @author zhanchaohan
 *
 */
public class PostToServer {
	private static final String URL="http://127.0.0.1:4000/addUser";
	private static final String PAM="{realname:'你還',email:'cc'}";
	public static final MediaType JSON= MediaType.get("application/json; charset=utf-8");
	
	@Test
	public void test1() throws IOException {
		System.out.println(post(URL,PAM));
	}
	
	OkHttpClient client = new OkHttpClient();
	public String post(String url, String json) throws IOException {
	  RequestBody body = RequestBody.create(json, JSON);
	  Request request = new Request.Builder()
	      .url(url)
	      .post(body)
	      .build();
	  Response response = client.newCall(request).execute();
	  return response.body().string();
	}
}

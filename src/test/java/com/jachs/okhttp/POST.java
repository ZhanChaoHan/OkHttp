package com.jachs.okhttp;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/***
 * 
 * @author zhanchaohan
 *
 */
public class POST {
	
	//POST方式提交String
	@Test
	public void test1() throws InterruptedException {
		MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
		String requestBody = "I am Jdqm.";
		Request request = new Request.Builder()
		        .url("https://api.github.com/markdown/raw")
		        .post(RequestBody.create(mediaType, requestBody))
		        .build();
		OkHttpClient okHttpClient = new OkHttpClient();
		okHttpClient.newCall(request).enqueue(new Callback() {
		    public void onFailure(Call call, IOException e) {
		        System.out.println("onFailure: " + e.getMessage());
		    }
		    public void onResponse(Call call, Response response) throws IOException {
		        System.out.println( response.protocol() + " " +response.code() + " " + response.message());
		    	Headers headers = response.headers();
		        for (int i = 0; i < headers.size(); i++) {
		          System.out.println(headers.name(i) + ":" + headers.value(i));
		        }
		        System.out.println("onResponse: " + response.body().string());
		    }
		});
		Thread.sleep(10000);
	}
	//POST方式提交流
	@Test
	public void test2() throws InterruptedException {
		RequestBody requestBody = new RequestBody() {
		    @Override
		    public MediaType contentType() {
		        return MediaType.parse("text/x-markdown; charset=utf-8");
		    }
		    @Override
		    public void writeTo(BufferedSink sink) throws IOException {
		        sink.writeUtf8("I am Jdqm.");
		    }
		};

		Request request = new Request.Builder()
		        .url("https://api.github.com/markdown/raw")
		        .post(requestBody)
		        .build();
		OkHttpClient okHttpClient = new OkHttpClient();
		okHttpClient.newCall(request).enqueue(new Callback() {
		    public void onFailure(Call call, IOException e) {
		        System.out.println("onFailure: " + e.getMessage());
		    }
		    public void onResponse(Call call, Response response) throws IOException {
		        System.out.println(response.protocol() + " " +response.code() + " " + response.message());
		        Headers headers = response.headers();
		        for (int i = 0; i < headers.size(); i++) {
		           System.out.println(headers.name(i) + ":" + headers.value(i));
		        }
		        System.out.println("onResponse: " + response.body().string());
		    }
		});
		Thread.sleep(10000);
	}
	//POST提交文件
	@Test
	public void test3() throws InterruptedException {
		MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
		OkHttpClient okHttpClient = new OkHttpClient();
		File file = new File("test.md");
		Request request = new Request.Builder()
		        .url("https://api.github.com/markdown/raw")
		        .post(RequestBody.create(mediaType, file))
		        .build();
		okHttpClient.newCall(request).enqueue(new Callback() {
		    public void onFailure(Call call, IOException e) {
		        System.out.println("onFailure: " + e.getMessage());
		    }
		    public void onResponse(Call call, Response response) throws IOException {
		        System.out.println(response.protocol() + " " +response.code() + " " + response.message());
		        Headers headers = response.headers();
		        for (int i = 0; i < headers.size(); i++) {
		            System.out.println(headers.name(i) + ":" + headers.value(i));
		        }
		        System.out.println("onResponse: " + response.body().string());
		    }
		});
		Thread.sleep(10000);
	}
	//POST方式提交表单
	@Test
	public void test5() throws InterruptedException {
		OkHttpClient okHttpClient = new OkHttpClient();
		RequestBody requestBody = new FormBody.Builder()
		        .add("realname", "Jurassic Park")
		        .add("email", "aa")
		        .build();
		Request request = new Request.Builder()
		        .url("http://127.0.0.1:4000/addUser")
		        .post(requestBody)
		        .build();

		okHttpClient.newCall(request).enqueue(new Callback() {
		    public void onFailure(Call call, IOException e) {
		        System.out.println("onFailure: " + e.getMessage());
		    }
		    public void onResponse(Call call, Response response) throws IOException {
		        System.out.println(response.protocol() + " " +response.code() + " " + response.message());
		        Headers headers = response.headers();
		        for (int i = 0; i < headers.size(); i++) {
		            System.out.println(headers.name(i) + ":" + headers.value(i));
		        }
		        System.out.println("onResponse: " + response.body().string());
		    }
		});
		Thread.sleep(10000);
	}
}

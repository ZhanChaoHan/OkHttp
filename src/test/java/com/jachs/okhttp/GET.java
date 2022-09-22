package com.jachs.okhttp;

import java.io.IOException;

import org.junit.Test;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/***
 * 
 * @author zhanchaohan
 *
 */
public class GET {
	//异步GET请求
	@Test
	public void test1() throws InterruptedException {
		String url = "http://wwww.baidu.com";
		OkHttpClient okHttpClient = new OkHttpClient();
		final Request request = new Request.Builder()
		        .url(url)
		        .get()//默认就是GET请求，可以不写
		        .build();
		Call call = okHttpClient.newCall(request);
		call.enqueue(new Callback() {
		    public void onFailure(Call call, IOException e) {
		        System.out.println("onFailure");
		    }
		    public void onResponse(Call call, Response response) throws IOException {
		        System.out.println("onResponse: " + response.body().string());
		    }
		});
		Thread.sleep(10000);
	}
	//同步GET请求
	@Test
	public void test2() throws InterruptedException {
		String url = "http://wwww.baidu.com";
		OkHttpClient okHttpClient = new OkHttpClient();
		final Request request = new Request.Builder()
		        .url(url)
		        .build();
		final Call call = okHttpClient.newCall(request);
		new Thread(new Runnable() {
		    public void run() {
		        try {
		            Response response = call.execute();
		            System.out.println("run: " + response.body().string());
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}).start();
		Thread.sleep(10000);
	}
}

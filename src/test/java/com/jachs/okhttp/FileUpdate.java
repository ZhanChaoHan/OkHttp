package com.jachs.okhttp;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/***
 * 
 * @author zhanchaohan
 *
 */
public class FileUpdate {

	@Test
	public void t1() throws IOException {
		String url = "http://127.0.0.1:8080";
		File f=new File("C:\\Users\\79951\\Desktop\\sucai\\UIYR8(_U4LD3[1Y2$_DP}T2.jpg");
		
		OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("image/jpg");
        RequestBody fileBody = RequestBody.create(mediaType, f);
        RequestBody requestBody = new MultipartBody.Builder()
        	.setType(MultipartBody.FORM)
        	.addFormDataPart("file", f.getName(), fileBody)
        	.build();
        Request request = new Request.Builder()
        		.addHeader("X-Access-Token", "aaaa")//添加head内容
                .url(url)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        String responseData = response.body().string();
        
        System.out.println(responseData);
	}
}

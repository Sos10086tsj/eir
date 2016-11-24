package com.chinesedreamer.eir;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 15, 2016
**/
public class Zamzar {
	
	private static String apiKey = "fe5f026f9a5b1164354465be2d46da0b984da031";
	public static void main(String[] args){
//		getTarget();
		
		try {
			startJob();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			checkStatus();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			download();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void download() throws Exception {
		String endpoint = "https://sandbox.zamzar.com/v1/files/" + 16058236 + "/content";
		String localFilename = "C:/Users/Paris/Desktop/NGB 1645-1-5.xlsx";
		// Create HTTP client and request object
		CloseableHttpClient httpClient = getHttpClient(apiKey);
		HttpGet request = new HttpGet(endpoint);

		// Make request
		CloseableHttpResponse response = httpClient.execute(request);

		// Extract body from response
		HttpEntity responseContent = response.getEntity();

		// Save response content to file on local disk
		BufferedInputStream bis = new BufferedInputStream(responseContent.getContent());
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(localFilename));
		int inByte;
		while ((inByte = bis.read()) != -1) {
			bos.write(inByte);
		}

		// Print success message
		System.out.println("File downloaded");

		// Finalise response, client and streams
		response.close();
		httpClient.close();
		bos.close();
		bis.close();
	}
	
	private static void checkStatus() throws Exception {
		String endpoint = "https://sandbox.zamzar.com/v1/jobs/" + 559214;
		CloseableHttpClient httpClient = getHttpClient(apiKey);
		HttpGet request = new HttpGet(endpoint);
		
		System.out.println("开始发送");
		CloseableHttpResponse response = httpClient.execute(request);

		// Extract body from response
		HttpEntity responseContent = response.getEntity();
		String result = EntityUtils.toString(responseContent, "UTF-8");

		// Parse result as JSON
		System.out.println("收到反馈");
		JSONObject jsonObject = JSON.parseObject(result);
		System.out.println("result:\n" + jsonObject);

		// Finalise response and client
		response.close();
		httpClient.close();
	}
	
	private static void startJob() throws Exception {
        String endpoint = "https://sandbox.zamzar.com/v1/jobs";
        String sourceFile = "C:/Users/Paris/Desktop/NGB 1645-1-5.pdf";
        String targetFormat = "xlsx";
        
        CloseableHttpClient httpClient = getHttpClient(apiKey);
        HttpEntity requestContent = 
        		MultipartEntityBuilder.create()
        		.addPart("source_file", new FileBody(new File(sourceFile)))
        		.addPart("target_format", new StringBody(targetFormat, ContentType.TEXT_PLAIN))
        		.build();
        HttpPost request = new HttpPost(endpoint);
        request.setEntity(requestContent);
        System.out.println("开始发送");
        CloseableHttpResponse response = httpClient.execute(request);
        
        HttpEntity responseContent = response.getEntity();
        
        String result = EntityUtils.toString(responseContent, "UTF-8");
        System.out.println("收到反馈");
        JSONObject jsonObject = JSON.parseObject(result);
        System.out.println("result:\n" + jsonObject);
        
        response.close();
        httpClient.close();
	}
	
	private static void getTarget() {
		String endpoint = "https://sandbox.zamzar.com/v1/formats/gif";
		
		CloseableHttpClient httpClient = getHttpClient(apiKey);
		HttpGet request = new HttpGet(endpoint);
		
		try {
			CloseableHttpResponse response = httpClient.execute(request);
			
			HttpEntity responseContent = response.getEntity();
			String result = EntityUtils.toString(responseContent, "UTF-8");
			
			JSONObject jsonObject = JSON.parseObject(result);
			System.out.println("result:" + jsonObject);
			
			response.close();
			httpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static CloseableHttpClient getHttpClient(String apiKey) {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(apiKey, ""));

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

        return httpClient;
    }
}

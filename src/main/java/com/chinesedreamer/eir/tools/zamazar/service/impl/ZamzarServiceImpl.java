package com.chinesedreamer.eir.tools.zamazar.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinesedreamer.eir.constant.ApplicationConstant;
import com.chinesedreamer.eir.domain.dao.ApiZamzarDao;
import com.chinesedreamer.eir.domain.dao.EirFileDao;
import com.chinesedreamer.eir.domain.model.EirFile;
import com.chinesedreamer.eir.tools.zamazar.model.ZamzarResponse;
import com.chinesedreamer.eir.tools.zamazar.service.ZamzarService;
import com.chinesedreamer.eir.util.PropertiesUtil;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 24, 2016
**/
@Service
public class ZamzarServiceImpl implements ZamzarService{
	private Logger logger = LoggerFactory.getLogger("EIR");
	
	private int HTTP_CONN_TIME_OUT = 120000;
	private int HTTP_TRANSFER_TIME_OUT = 120000;
	
	@Resource
	private ApiZamzarDao apiZamzarDao;
	@Resource
	private EirFileDao eirFileDao;

	@Override
	public ZamzarResponse zamzarFile(String sourceFile, String targetFormat) {
		ZamzarResponse zamzarResponse = new ZamzarResponse();
		
		PropertiesUtil pu = new PropertiesUtil(ApplicationConstant.APPLICATION_PROPERTY_FILE);
		String endpoint = pu.getProperty(ApplicationConstant.PROPERTY_ZAMZAR_URL_JOB);
		String apiKey = this.apiZamzarDao.findAvailableApiKey().getApiKey();
		CloseableHttpClient httpClient = this.getHttpClient(apiKey);
		HttpEntity requestContent = MultipartEntityBuilder.create()
				.addPart("source_file", new FileBody(new File(pu.getProperty(ApplicationConstant.PROPERTY_FILE_UPLOAD_ROOT_KEY) + sourceFile)))
				.addPart("target_format", new StringBody(targetFormat, ContentType.TEXT_PLAIN)).build();
		HttpPost request = new HttpPost(endpoint);
		request.setConfig(RequestConfig.custom().setSocketTimeout(HTTP_TRANSFER_TIME_OUT).setConnectTimeout(HTTP_CONN_TIME_OUT).build());
		request.setEntity(requestContent);
		this.logger.debug("Start sending file {}.",sourceFile);
		try {
			CloseableHttpResponse response = httpClient.execute(request);
			HttpEntity responseContent = response.getEntity();
			String result = EntityUtils.toString(responseContent, "UTF-8");
			this.convertJobResponse(zamzarResponse, result);
			response.close();
			httpClient.close();
		} catch (Exception e) {
			this.logger.error("{}",e);
			zamzarResponse.setSuccess(Boolean.FALSE);
			zamzarResponse.setErrors("Error happened.");
		}
		return zamzarResponse;
	}

	@Override
	public EirFile saveDownloadFile(String fileId) {
		EirFile file = new EirFile();
		
		PropertiesUtil pu = new PropertiesUtil(ApplicationConstant.APPLICATION_PROPERTY_FILE);
		String endpoint = pu.getProperty(ApplicationConstant.PROPERTY_ZAMZAR_URL_FILE_DOWNLOAD_PREFIX) + fileId + pu.getProperty(ApplicationConstant.PROPERTY_ZAMZAR_URL_FILE_DOWNLOAD_SUFFIX);
		String apiKey = this.apiZamzarDao.findAvailableApiKey().getApiKey();
		CloseableHttpClient httpClient = getHttpClient(apiKey);
		HttpGet request = new HttpGet(endpoint);
		
		Calendar calendar = Calendar.getInstance();
		String dayFoler = calendar.get(Calendar.YEAR) + File.separator
				+ (calendar.get(Calendar.MONTH) + 1) + File.separator
				+ calendar.get(Calendar.DAY_OF_MONTH) + File.separator;
		StringBuilder builder = new StringBuilder();
		builder.append(UUID.randomUUID().toString())
		.append(".xlsx");
		String saveFileName = builder.toString();
		File folder = new File(pu.getProperty(ApplicationConstant.PROPERTY_FILE_UPLOAD_ROOT_KEY) + dayFoler);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		try {
			File saveFile = new File(pu.getProperty(ApplicationConstant.PROPERTY_FILE_UPLOAD_ROOT_KEY) + dayFoler + saveFileName);
			if (!saveFile.exists()) {
				saveFile.createNewFile();
			}
			
			CloseableHttpResponse response = httpClient.execute(request);
			HttpEntity responseContent = response.getEntity();
			BufferedInputStream bis = new BufferedInputStream(responseContent.getContent());
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(pu.getProperty(ApplicationConstant.PROPERTY_FILE_UPLOAD_ROOT_KEY) + dayFoler + saveFileName));
			int inByte;
			while ((inByte = bis.read()) != -1) {
				bos.write(inByte);
			}
			response.close();
			httpClient.close();
			bos.flush();
			bos.close();
			bis.close();
			
			file.setFileName(saveFileName);
			file.setFilePath(dayFoler + saveFileName);
			
			this.eirFileDao.save(file);
		} catch (Exception e) {
			this.logger.error("{}",e);
		}
		
		return file;
	}

	@Override
	public ZamzarResponse zamzarStatus(String jobId) {
		ZamzarResponse zamzarResponse = new ZamzarResponse();
		PropertiesUtil pu = new PropertiesUtil(ApplicationConstant.APPLICATION_PROPERTY_FILE);
		String endpoint = pu.getProperty(ApplicationConstant.PROPERTY_ZAMZAR_URL_STATUS) + jobId;
		String apiKey = this.apiZamzarDao.findAvailableApiKey().getApiKey();
		CloseableHttpClient httpClient = this.getHttpClient(apiKey);
		HttpGet request = new HttpGet(endpoint);
		request.setConfig(RequestConfig.custom().setSocketTimeout(HTTP_TRANSFER_TIME_OUT).setConnectTimeout(HTTP_CONN_TIME_OUT).build());
		try {
			CloseableHttpResponse response = httpClient.execute(request);
			HttpEntity responseContent = response.getEntity();
			String result = EntityUtils.toString(responseContent, "UTF-8");
			this.convertJobResponse(zamzarResponse, result);
			response.close();
			httpClient.close();
		} catch (Exception e) {
			this.logger.error("{}",e);
			zamzarResponse.setSuccess(Boolean.FALSE);
			zamzarResponse.setErrors("Error happened.");
		}
		return zamzarResponse;
	}

	private CloseableHttpClient getHttpClient(String apiKey) {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials(apiKey, ""));
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();
        return httpClient;
    }
	
	private void convertJobResponse(ZamzarResponse zamzarResponse, String result) {
		if (null == zamzarResponse) {
			zamzarResponse = new ZamzarResponse();
		}
		if (StringUtils.isEmpty(result)) {
			zamzarResponse.setSuccess(Boolean.FALSE);
			zamzarResponse.setErrors("No respone.");
			return;
		}
		JSONObject jsonObject = JSON.parseObject(result);
		if (StringUtils.isNotEmpty(jsonObject.getString("errors"))) {
			zamzarResponse.setSuccess(Boolean.FALSE);
			zamzarResponse.setErrors(jsonObject.getString("errors"));
		}else {
			zamzarResponse.setId(jsonObject.getString("id"));
			zamzarResponse.setKey(jsonObject.getString("key"));
			zamzarResponse.setStatus(jsonObject.getString("status"));
			zamzarResponse.setSandbox(jsonObject.getBoolean("sandbox"));
			zamzarResponse.setCreatedDate(jsonObject.getString("created_at"));
			zamzarResponse.setFinishedDate(jsonObject.getString("finished_at"));
			zamzarResponse.setSourceFile(jsonObject.getString("source_file"));
			zamzarResponse.setTargetFiles(jsonObject.getString("target_files"));
			zamzarResponse.setTargetFormat(jsonObject.getString("target_format"));
			zamzarResponse.setCreditCost(jsonObject.getString("credit_cost"));
		}
	}
}

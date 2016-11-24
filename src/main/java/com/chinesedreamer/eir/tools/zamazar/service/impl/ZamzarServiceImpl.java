package com.chinesedreamer.eir.tools.zamazar.service.impl;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
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
import com.chinesedreamer.eir.tools.zamazar.model.ZamzarResponse;
import com.chinesedreamer.eir.tools.zamazar.model.ZamzarStatus;
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
			zamzarResponse.setErrors(e.getMessage());
		}
		return zamzarResponse;
	}

	@Override
	public File download(String fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ZamzarStatus zamzarStatus(String fileId) {
		// TODO Auto-generated method stub
		return null;
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

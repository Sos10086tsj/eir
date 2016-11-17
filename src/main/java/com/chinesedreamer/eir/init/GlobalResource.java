package com.chinesedreamer.eir.init;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinesedreamer.eir.exception.ErrorMessage;
import com.chinesedreamer.eir.util.FileUtil;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 16, 2016
**/
public class GlobalResource {
	private static Logger logger = LoggerFactory.getLogger("EIR");
	public static ConcurrentHashMap<String, Map<String,ErrorMessage>> errorMapping = new ConcurrentHashMap<String, Map<String,ErrorMessage>>();
	
	public static void initErrorMapping() {
		try {
			JSONObject jsonObject = FileUtil.getJsonFromFile(FileUtil.getResouceRootPath("json/errorMessage.json"));
			GlobalResource.errorMapping.clear();
			JSONObject errorConfigs = jsonObject.getJSONObject("errorCode");
			for (String key : errorConfigs.keySet()) {
				JSONArray keyErrorMessages = errorConfigs.getJSONArray(key);
				Map<String,ErrorMessage> map = new HashMap<String, ErrorMessage>();
				for (Object object : keyErrorMessages) {
					JSONObject obj = (JSONObject)object;
					for (String codeKey : obj.keySet()) {
						JSONObject emObj = obj.getJSONObject(codeKey);
						map.put(codeKey, new ErrorMessage(emObj.getString("code"), emObj.getString("message")));
					}
				}
				GlobalResource.errorMapping.put(key, map);
			}
		} catch (Exception e) {
			logger.error("{}",e);
		}
	}
}

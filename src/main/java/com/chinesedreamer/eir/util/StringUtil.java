package com.chinesedreamer.eir.util;

import java.text.MessageFormat;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinesedreamer.eir.constant.ApplicationConstant;
import com.chinesedreamer.eir.exception.ErrorMessage;
import com.chinesedreamer.eir.exception.ErrorMessageCode;
import com.chinesedreamer.eir.init.GlobalResource;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 16, 2016
**/
public class StringUtil {
	private static Logger logger = LoggerFactory.getLogger("EIR");
	
	public static ErrorMessage getErrorMessage(String groupCode, String code, Object... objects) {
		ErrorMessage message = null;
		if (GlobalResource.errorMapping.containsKey(groupCode)) {
			Map<String,ErrorMessage> map = GlobalResource.errorMapping.get(groupCode);
			if (map.containsKey(code)) {
				ErrorMessage errorMessage = map.get(code);
				if (null == objects || objects.length == 0) {
					message = new ErrorMessage(code,errorMessage.getMessage());
				}else {
					message = new ErrorMessage(code,MessageFormat.format(errorMessage.getMessage(), objects));
				}
			}
		}
		if (null == message) {
			message = getErrorMessageFromFile(groupCode, code, objects);
		}
		return message;
	}
	
	public static ErrorMessage getErrorMessageFromFile(String groupCode, String code, Object... objects) {
		ErrorMessage errorMessage = null;
		try {
			JSONObject jsonObject = FileUtil.getJsonFromFile(FileUtil.getResouceRootPath("json/errorMessage.json"));
			GlobalResource.errorMapping.clear();
			JSONObject errorConfigs = jsonObject.getJSONObject("errorCode");
			if (errorConfigs.containsKey(groupCode)) {
				JSONArray keyErrorMessages = errorConfigs.getJSONArray(groupCode);
				for (Object object : keyErrorMessages) {
					JSONObject obj = (JSONObject)object;
					if (obj.containsKey(code)) {
						JSONObject emObj = obj.getJSONObject(code);
						if (null == objects || objects.length == 0) {
							errorMessage = new ErrorMessage(code,emObj.getString("message"));
						}else {
							errorMessage = new ErrorMessage(code,MessageFormat.format(emObj.getString("message"), objects));
						}
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error("{}",e);
		}
		if (null == errorMessage) {
			errorMessage = new ErrorMessage(ErrorMessageCode.SYSTEM.UNEXPECTED_ERROR_MESSAGE_CODE, ErrorMessageCode.SYSTEM.UNEXPECTED_ERROR_MESSAGE_MSG);
		}
		return errorMessage;
	}
	
	public static boolean matchKeyWord(String word, String keyWrods) {
		if (StringUtils.isEmpty(keyWrods) || StringUtils.isEmpty(word)) {
			return false;
		}
		String[] words = keyWrods.split(ApplicationConstant.PO_CONFIG_DELIMITER);
		for (String keyWord : words) {
			if (word.equalsIgnoreCase(keyWord)) {
				return true;
			}
		}
		return false;
	}
}

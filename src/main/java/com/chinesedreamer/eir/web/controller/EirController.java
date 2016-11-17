package com.chinesedreamer.eir.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chinesedreamer.eir.exception.ErrorMessage;
import com.chinesedreamer.eir.exception.ErrorMessageCode;
import com.chinesedreamer.eir.service.FileService;
import com.chinesedreamer.eir.service.JobService;
import com.chinesedreamer.eir.util.ResponseUtil;
import com.chinesedreamer.eir.util.StringUtil;
import com.chinesedreamer.eir.vo.model.job.JobPoVo;
import com.chinesedreamer.eir.vo.model.job.JobVo;
import com.chinesedreamer.eir.vo.query.JobQueryVo;
import com.chinesedreamer.eir.vo.response.ResponseVo;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 16, 2016
**/
@Controller
@RequestMapping(value="eir")
public class EirController {
	@Resource
	private JobService jobService;
	@Resource
	private FileService fileService;
	
	/**
	 * 进入打单页面
	 * @return
	 */
	@RequestMapping(value = "")
	public String eir(){
		return "";
	}
	
	/**
	 * get job list
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "job")
	public ResponseVo job(JobQueryVo queryVo){
		if (null == queryVo) {
			queryVo = new JobQueryVo();
		}
		return ResponseUtil.success(this.jobService.getJobs(queryVo));
	}
	
	
	@ResponseBody
	@RequestMapping(value = "job/save")
	public ResponseVo saveJob(JobVo vo){
		if (StringUtils.isNotEmpty(vo.getJobId())) {
			this.jobService.update(vo);
		}else {
			this.jobService.save(vo);
		}
		return ResponseUtil.success();
	}
	
	/**
	 * 
	 * @param jobId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "job/po/{jobId}")
	public ResponseVo jobPo(@PathVariable(value="jobId") Long jobId){
		List<JobPoVo> vos = this.jobService.getJobPos(jobId);
		return ResponseUtil.success(vos);
	}
	
	/**
	 * 上传po
	 * @param jobId
	 * @param po
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "job/po/upload/{jobId}")
	public ResponseVo jobPoUpload(@PathVariable(value="jobId") Long jobId,@RequestParam(value = "po", required = true)MultipartFile po){
		Long fileId = this.fileService.update2UploadFile(po);
		if (null == fileId) {//文件保存失败
			ErrorMessage errorMessage = StringUtil.getErrorMessage(ErrorMessageCode.FILE.CODE, ErrorMessageCode.FILE.SAVE_FAILURE, po.getOriginalFilename());
			return ResponseUtil.failure(errorMessage.getCode(), errorMessage.getMessage());
		}
		this.jobService.update2UploadJobPo(jobId, fileId);
		return ResponseUtil.success();
	}
	
	@ResponseBody
	@RequestMapping(value = "job/po/detail/{poId}")
	public ResponseVo jobPoDetail(@PathVariable(value="poId") Long poId){
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "job/po/parse/{poId}")
	public ResponseVo jobPoParse(@PathVariable(value="poId") Long poId){
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "job/manufactory/{jobId}")
	public ResponseVo jobManufactory(@PathVariable(value="jobId") Long jobId){
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "job/manufactory/upload/{jobId}")
	public ResponseVo jobManufactoryUpload(@PathVariable(value="jobId") Long jobId){
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "job/manufactory/detail/{manufactoryId}")
	public ResponseVo jobManufactoryDetail(@PathVariable(value="manufactoryId") Long manufactoryId){
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "job/manufactory/parse/{manufactoryId}")
	public ResponseVo jobManufactoryParse(@PathVariable(value="manufactoryId") Long manufactoryId){
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "job/report/{jobId}")
	public ResponseVo jobReport(@PathVariable(value="jobId") Long jobId){
		return null;
	}
}

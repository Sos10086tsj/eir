package com.chinesedreamer.eir.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chinesedreamer.eir.domain.model.EirFile;
import com.chinesedreamer.eir.service.FileService;
import com.chinesedreamer.eir.service.PoService;
import com.chinesedreamer.eir.util.ResponseUtil;
import com.chinesedreamer.eir.util.SessionUtil;
import com.chinesedreamer.eir.vo.model.po.PoVo;
import com.chinesedreamer.eir.vo.query.Pagination;
import com.chinesedreamer.eir.vo.query.PoQueryVo;
import com.chinesedreamer.eir.vo.response.ResponseVo;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 21, 2016
**/
@Controller
@RequestMapping(value="po")
public class PoController {
	
	@Resource
	private FileService fileService;
	@Resource
	private PoService poService;

	/**
	 * 进入po列表页
	 * @return
	 */
	@RequestMapping(value="")
	public String po(){
		return "po/po";
	}
	
	@ResponseBody
	@RequestMapping(value="list")
	public ResponseVo poList(PoQueryVo queryVo){
		if (null == queryVo) {
			queryVo = new PoQueryVo();
		}
		queryVo.setCreateUser(SessionUtil.getUserId());
		Pagination<PoVo> vos = this.poService.findPage(queryVo);
		return ResponseUtil.success(vos);
	}
	
	/**
	 * 上传po文件
	 * @param vo
	 * @param file
	 * @return
	 */
	@RequestMapping(value="upload")
	public String uploadPo(PoVo vo,@RequestParam("file")MultipartFile file){
		EirFile eirFile = this.fileService.save(file);
		vo.setFileId(eirFile.getId());
		this.poService.savePo(vo);
		return "redirect:/po";
	}
}

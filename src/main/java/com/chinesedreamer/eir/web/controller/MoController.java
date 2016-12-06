package com.chinesedreamer.eir.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chinesedreamer.eir.domain.model.EirFile;
import com.chinesedreamer.eir.service.FileService;
import com.chinesedreamer.eir.service.MoService;
import com.chinesedreamer.eir.util.ResponseUtil;
import com.chinesedreamer.eir.util.SessionUtil;
import com.chinesedreamer.eir.vo.model.mo.MoVo;
import com.chinesedreamer.eir.vo.model.po.PoDetailVo;
import com.chinesedreamer.eir.vo.query.MoQueryVo;
import com.chinesedreamer.eir.vo.query.Pagination;
import com.chinesedreamer.eir.vo.response.ResponseVo;

/**
 * Description: manufactory order,匹配pocontroller
 * Auth:Paris
 * Date:Nov 29, 2016
**/
@Controller
@RequestMapping(value="mo")
public class MoController {
	
	@Resource
	private MoService moService;
	@Resource
	private FileService fileService;
	
	@RequestMapping(value="")
	public String mo(){
		return "mo/list";
	}
	
	@ResponseBody
	@RequestMapping(value="list")
	public ResponseVo moList(MoQueryVo queryVo){
		if (null == queryVo) {
			queryVo = new MoQueryVo();
		}
		queryVo.setCreateUser(SessionUtil.getUserId());
		Pagination<MoVo> vos = this.moService.findMos(queryVo);
		return ResponseUtil.success(vos);
	}
	
	@RequestMapping(value="upload")
	public String uploadPo(MoVo vo,@RequestParam("file")MultipartFile file){
		EirFile eirFile = this.fileService.save(file);
		vo.setFileId(eirFile.getId());
		this.moService.saveMo(vo);
		return "redirect:/mo";
	}
	
	@RequestMapping(value="detail/{moId}")
	public String poDetail(@PathVariable(value="moId")Long moId, Model model){
		model.addAttribute("moId", moId);
		return "mo/detail";
	}
	
	@ResponseBody
	@RequestMapping(value="detail/data/{moId}")
	public ResponseVo poDetailInfo(@PathVariable(value="moId")Long moId){
		PoDetailVo vo = this.moService.findPoDetail(moId);
		return ResponseUtil.success(vo);
	}
}

package com.bc.pmpheep.back.commuser.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.common.controller.BaseController;
import com.bc.pmpheep.back.commuser.user.bean.WriterUserCertificationVO;
import com.bc.pmpheep.back.commuser.user.service.WriterUserService;

/**
 * @author tyc
 * @CreateDate 2017年11月30日 下午17:29:37
 * 
 **/
@Controller
@RequestMapping(value = "/teacherCertification")
public class TeacherCertification extends BaseController {
	
	@Autowired
	private WriterUserService writerUserService;
	
	@ResponseBody
    @RequestMapping(value = "/showTeacherCertification", method = RequestMethod.GET)
    public ModelAndView showTeacherCertification(@RequestParam("id") Long id) throws Exception {
		ModelAndView model = this.getModelAndView();
		WriterUserCertificationVO showWriterUserCertification = writerUserService.showTeacherCertification(id);
		model.addObject("showWriterUserCertification", showWriterUserCertification);
		return model;
    }
	
	@ResponseBody
    @RequestMapping(value = "/updateTeacherCertification", method = RequestMethod.GET)
    public ModelAndView updateTeacherCertification(WriterUserCertificationVO writerUserCertificationVO) 
    		throws Exception {
		ModelAndView model = this.getModelAndView();
		WriterUserCertificationVO updateWriterUserCertification = 
				writerUserService.updateTeacherCertification(writerUserCertificationVO);
		model.addObject("updateWriterUserCertification", updateWriterUserCertification);
		return model;
    }
}

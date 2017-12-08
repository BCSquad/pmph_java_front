package com.bc.pmpheep.back.commuser.user.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.user.bean.WriterUserCertification;
import com.bc.pmpheep.back.commuser.user.bean.WriterUserCertificationVO;
import com.bc.pmpheep.back.commuser.user.service.WriterUserService;
import com.bc.pmpheep.general.controller.BaseController;

/**
 * @author tyc
 * @CreateDate 2017年11月30日 下午17:29:37
 * 
 **/
@Controller
@RequestMapping(value = "/teacherCertification")
public class TeacherCertification extends BaseController {

    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.user.service.WriterUserServiceImpl")
    private WriterUserService writerUserService;

    @RequestMapping(value = "/showTeacherCertification", method = RequestMethod.GET)
    public ModelAndView showTeacherCertification(@RequestParam("userId") Long userId) {
        ModelAndView model = new ModelAndView();
        model.setViewName("authadmin/teacherauth/teacherAttest");
        WriterUserCertificationVO showWriterUserCertification =
        writerUserService.showTeacherCertification(userId);
        model.addObject("showWriterUserCertification", showWriterUserCertification);
        return model;
    }

    @RequestMapping(value = "/updateTeacherCertification", method = RequestMethod.POST)
    public ModelAndView updateTeacherCertification(
    		WriterUserCertification WriterUserCertification) throws IOException {
        ModelAndView model = new ModelAndView();
        WriterUserCertification updateWriterUserCertification =
        writerUserService.updateTeacherCertification(WriterUserCertification);
        model.addObject("updateWriterUserCertification", updateWriterUserCertification);
        return model;
    }
}

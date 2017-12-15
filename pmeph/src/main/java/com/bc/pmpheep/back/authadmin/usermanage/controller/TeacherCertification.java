package com.bc.pmpheep.back.authadmin.usermanage.controller;

import com.bc.pmpheep.back.authadmin.usermanage.bean.WriterUserCertification;
import com.bc.pmpheep.back.authadmin.usermanage.bean.WriterUserCertificationVO;
import com.bc.pmpheep.back.authadmin.usermanage.service.WriterUserService;
import com.bc.pmpheep.general.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

/**
 * @author tyc
 * @CreateDate 2017年11月30日 下午17:29:37
 * 
 **/
@Controller
@RequestMapping(value = "/teacherCertification")
public class TeacherCertification extends BaseController {

    @Autowired
    @Qualifier("com.bc.pmpheep.back.authadmin.usermanage.service.WriterUserServiceImpl")
    private WriterUserService writerUserService;

    /**
	 * 查看学校教师认证信息
	 * @author tyc
     * @createDate 2017年11月30日 上午10:44:09
	 * @return
	 */
    @RequestMapping(value = "/showTeacherCertification", method = RequestMethod.GET)
    public ModelAndView showTeacherCertification( ) {
    	Map <String,Object> map = this.getUserInfo() ;
    	Long userId = new Long(String.valueOf(map.get("id")));
        ModelAndView model = new ModelAndView();
        model.setViewName("authadmin/teacherauth/teacherAttest");
        WriterUserCertificationVO showWriterUserCertification =
        writerUserService.showTeacherCertification(userId);
        model.addObject("showWriterUserCertification", showWriterUserCertification);
        return model;
    }

    /**
	 * 修改学校教师认证
	 * @author tyc
     * @createDate 2017年11月30日 上午10:44:09
	 * @param writerUserCertification
	 * @param realName
	 * @return
	 */
    @RequestMapping(value = "/updateTeacherCertification", method = RequestMethod.POST)
    public ModelAndView updateTeacherCertification(WriterUserCertification WriterUserCertification,
    		@RequestParam("realName") String realName) throws IOException {
        ModelAndView model = new ModelAndView();
        com.bc.pmpheep.back.authadmin.usermanage.bean.WriterUserCertification updateWriterUserCertification =
        writerUserService.updateTeacherCertification(WriterUserCertification, realName);
        model.addObject("updateWriterUserCertification", updateWriterUserCertification);
        return model;
    }
}

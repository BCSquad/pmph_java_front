package com.bc.pmpheep.back.commuser.personalcenter.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.general.controller.BaseController;

//积分
@Controller
@RequestMapping("/integral")
public class IntegralController extends BaseController {

  /*  @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService")
    private PersonalService personalService;*/

    @RequestMapping("/toPage")
    public ModelAndView toPage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
//        Map<String, Object> permap = this.getUserInfo();
        mv.setViewName("commuser/personalcenter/integral");
        return mv;
    }


   
}

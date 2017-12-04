package com.bc.pmpheep.back.commuser.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.common.controller.BaseController;
import com.bc.pmpheep.back.commuser.user.service.WriterUserService;

/**
 * @author tyc
 * @CreateDate 2017年12月4日 上午10:37:28
 * 
 **/
@Controller
@RequestMapping(value = "/WriterUserController")
public class WriterUserController extends BaseController {
	
	@Autowired
	private WriterUserService writerUserService;
	
	@ResponseBody
    @RequestMapping(value = "/updateUserPassWord", method = RequestMethod.POST)
    public ModelAndView updateUserPassWord(@RequestParam("id") Long id, 
    		@RequestParam("username") String username) {
		ModelAndView model = this.getModelAndView();
		Integer writerUser = writerUserService.updateUserPassWord(id, username);
		model.addObject("writerUser", writerUser);
		return model;
    }
}

package com.bc.pmpheep.back.authadmin.usermanage.controller;

import com.bc.pmpheep.back.authadmin.usermanage.service.WriterUserService;
import com.bc.pmpheep.general.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    /**
     * 根据id和用户名修改用户密码
     * 
     * @author tyc
     * @createDate 2017年12月4日 上午10:47:43
     * @param id
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateUserPassWord", method = RequestMethod.POST)
    public ModelAndView updateUserPassWord(@RequestParam("id") Long id,
    @RequestParam("username") String username) {
        ModelAndView model = new ModelAndView();
        Integer writerUser = writerUserService.updateUserPassWord(id, username);
        model.addObject("writerUser", writerUser);
        return model;
    }
}

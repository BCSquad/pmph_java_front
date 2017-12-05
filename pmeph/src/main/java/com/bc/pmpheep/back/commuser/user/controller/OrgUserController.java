package com.bc.pmpheep.back.commuser.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.user.bean.WriterUser;
import com.bc.pmpheep.back.commuser.user.service.OrgUserService;
import com.bc.pmpheep.back.commuser.user.service.WriterUserService;
import com.bc.pmpheep.general.controller.BaseController;

/**
 * @author mr
 * @Date 2017年12月2日 下午16:00:00
 * 
 **/
@Controller
@RequestMapping(value = "/orgUser")
public class OrgUserController extends BaseController {

    @Autowired
    private WriterUserService writerUserService;
    @Autowired
    private OrgUserService    orgUserService;

    /**
     * 根据机构id 查询该机构下的申报的人数
     * 
     * @param orgId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam("orgId") Long orgId) {
        ModelAndView model = new ModelAndView();
        WriterUser writerUser = writerUserService.getOrg(orgId);
        model.addObject("writerUser", writerUser);
        return model;
    }

    /**
     * 根据条件搜索该机构下的作家用户
     * 
     * @param writerUser
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/writerList", method = RequestMethod.GET)
    public ModelAndView writerList(WriterUser writerUser) {
        ModelAndView model = new ModelAndView();
        WriterUser writerUsers = writerUserService.getByOrgId(writerUser);
        model.addObject("writerUsers", writerUsers);
        return model;
    }

    /**
     * 根据id和机构代码修改机构用户密码
     * 
     * @author tyc
     * @createDate 2017年12月4日 上午10:14:57
     * @param id
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateOrgUserPassWord", method = RequestMethod.POST)
    public ModelAndView updateOrgUserPassWord(@RequestParam("id") Long id,
    @RequestParam("username") String username) {
        ModelAndView model = new ModelAndView();
        Integer orgUser = orgUserService.updateOrgUserPassWord(id, username);
        model.addObject("orgUser", orgUser);
        return model;
    }
}

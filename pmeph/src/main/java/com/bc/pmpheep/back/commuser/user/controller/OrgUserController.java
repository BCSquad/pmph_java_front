package com.bc.pmpheep.back.commuser.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.user.bean.OrgUser;
import com.bc.pmpheep.back.commuser.user.bean.WriterUser;
import com.bc.pmpheep.back.commuser.user.service.OrgUserService;
import com.bc.pmpheep.back.commuser.user.service.WriterUserService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * @author mr
 * @Date 2017年12月2日 下午16:00:00
 * 
 **/
@Controller
@RequestMapping(value = "/user")
public class OrgUserController extends  com.bc.pmpheep.general.controller.BaseController{
	
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.user.service.WriterUserServiceImpl")
	private WriterUserService writerUserService;
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.user.service.OrgUserServiceImpl")
	private OrgUserService orgUserService;
	
	/**
	 * 根据机构id 查询该机构下的申报的人数
	 * @param orgId
	 * @return
	 */
    @RequestMapping(value = "/writerLists")
    public ModelAndView writerLists(Integer pageSize, Integer pageNumber,WriterUser writerUser)
    		throws Exception{
		ModelAndView model = new ModelAndView();
		//获取当前用户 
		Map<String,Object> writerUserMap=this.getUserInfo();
		OrgUser orgUser=new OrgUser();
		orgUser.setOrgId(Long.parseLong(writerUserMap.get("id").toString()));
		PageParameter<WriterUser> pageParameter = new PageParameter<>(pageNumber, pageSize);
		writerUser.setOrgId(orgUser.getOrgId());
		pageParameter.setParameter(writerUser);
	    String pageUrl = "commuser/user/writerLists";
        try {
        	PageResult<WriterUser> page = writerUserService.getOrg(pageParameter);
            model.setViewName(pageUrl);
            model.addObject("page", page);
        } catch (CheckedServiceException e) {
            throw new CheckedServiceException(e.getBusiness(), e.getResult(), e.getMessage(),
                                              pageUrl);
        }
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

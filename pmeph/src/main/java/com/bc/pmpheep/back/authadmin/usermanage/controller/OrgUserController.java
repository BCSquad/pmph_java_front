package com.bc.pmpheep.back.authadmin.usermanage.controller;

import com.bc.pmpheep.back.authadmin.usermanage.bean.OrgUser;
import com.bc.pmpheep.back.authadmin.usermanage.bean.WriterUser;
import com.bc.pmpheep.back.authadmin.usermanage.service.OrgUserService;
import com.bc.pmpheep.back.authadmin.usermanage.service.WriterUserService;
import com.bc.pmpheep.back.commuser.mymessage.service.MyMessageService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**	用户管理（机构用户）
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
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.mymessage.service.MyMessageServiceImpl")
	MyMessageService myMessageService;
	
	
	/**
	 * 根据机构id 查询该机构下的申报的人数
	 * @param orgId
	 * @return
	 */
    @RequestMapping(value = "/writerLists",method = RequestMethod.GET)
    public ModelAndView writerLists(Integer pageSize, Integer pageNumber,String username)
    		throws Exception{
		ModelAndView model = new ModelAndView();
		if(null==pageSize){
			pageSize=10;
		}
		PageParameter<WriterUser> pageParameter = new PageParameter<>(pageNumber, pageSize);
		String userName="";
		if(username!=null){
			 userName = java.net.URLDecoder.decode(username,"UTF-8");
		}
		//获取当前用户 
		Map<String,Object> writerUserMap=this.getUserInfo();
		OrgUser orgUser=new OrgUser();
		WriterUser writerUser=new WriterUser();
		orgUser.setOrgId(Long.parseLong(writerUserMap.get("org_id").toString()));
		writerUser.setOrgId(orgUser.getOrgId());
		writerUser.setName(userName);
		pageParameter.setParameter(writerUser);
	    String pageUrl = "commuser/usermanage/writerLists";
        try {
        	PageResult<WriterUser> page = writerUserService.getOrg(pageParameter);
            model.setViewName(pageUrl);
            model.addObject("page", page);
            if (null == pageNumber) {
                pageNumber = 1;
            }
            model.addObject("pageNumber", pageNumber);
            model.addObject("pageSize", pageSize);
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
    
    /**
     * 发送消息
     * @param friendId 接受人ID
     * @param friendIdType
     * @param title
     * @param content
     * @return
     */
    @ResponseBody
	@RequestMapping(value = "/senNewMsg", method = RequestMethod.POST)
	public String senNewMsg(Short friendIdType,HttpServletRequest request) {
    	String content=request.getParameter("content");
    	Long friendId=Long.valueOf(request.getParameter("friendId"));
    	String title=request.getParameter("title");
		Map<String, Object> writerUser = this.getUserInfo();
		Long thisId = new Long(String.valueOf(writerUser.get("id")));
		myMessageService.senNewMsg(thisId, friendId, friendIdType, title, content);
		return "success";
	}
}

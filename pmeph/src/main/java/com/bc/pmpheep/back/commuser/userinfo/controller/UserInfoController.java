package com.bc.pmpheep.back.commuser.userinfo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bc.pmpheep.general.controller.BaseController;
import com.bc.pmpheep.general.service.FileService;
import com.bc.pmpheep.general.service.MessageService;
import com.bc.pmpheep.general.service.UserService;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.authadmin.accountset.bean.OrgAdminUser;
import com.bc.pmpheep.back.commuser.userinfo.service.UserInfoService;
import com.bc.pmpheep.back.util.Const;
import com.bc.pmpheep.back.util.DesRun;
import com.bc.pmpheep.controller.bean.ResponseBean;
import com.mongodb.gridfs.GridFSDBFile;


/**
 * 个人资料修改控制层
 */
@Controller
@RequestMapping("userinfo")
public class UserInfoController extends BaseController {

    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.userinfo.service.UserInfoServiceImpl")
    private UserInfoService userinfoService;
    @Autowired
    private FileService fileService;
    
    @Autowired
    UserService userService;
    
    /**
     * 无权限 跳转地址
     * @param request
     * @return
     */
    @RequestMapping("toNoAccessToAuthority")
    public ModelAndView toNoAccessToAuthority(HttpServletRequest request){
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("pageType", request.getParameter("pageType"));
    	modelAndView.setViewName("comm/no_access_to_authority");
    	 return modelAndView;
    }
    /**
     * 根据ID查询作家相关信息
     *
     * @param request
     * @return map
     */
    @RequestMapping("touser")
    public ModelAndView toperson(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Map <String,Object> map1 = getUserInfo();
        Map<String, Object> map =new HashMap<String, Object>();
        if (null!=map1) {
        	 map = userinfoService.queryWriter(map1.get("id").toString());
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey().toString();
                String value = entry.getValue().toString();
                if(value.equals("-")){
                    map.put(key,"");
                }
            }
		}
        modelAndView.addObject("map", map);
        modelAndView.setViewName("commuser/userinfo/userinfo");
        return modelAndView;
    }


	
	/**
	 * 根据ID修改头像
	 * @param request
	 */
	@RequestMapping("updateavatar")
	@ResponseBody	
	public Map<String, Object> updateavatar(HttpServletRequest request){
		Map<String, Object> map=new HashMap<String, Object>();
		return map;
	}
	

    /**
     * 根据ID改变普通作家信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> zmap = new HashMap<String, Object>();
        String id = request.getParameter("id");
        String realName = request.getParameter("realname");
        String experience = request.getParameter("experience");
        String fax = request.getParameter("fax");
        String sex = request.getParameter("sex");
        String title = request.getParameter("title");
        String handphone = request.getParameter("handphone");
        String postCode = request.getParameter("postcode");
        String birthday = request.getParameter("birthday");
        String email = request.getParameter("email");
        String position = request.getParameter("position");
        String address = request.getParameter("address");
        String note = request.getParameter("note");
        String signature = request.getParameter("signature");
        String workplace = request.getParameter("workplace");
        String telephone = request.getParameter("telephone");
        String fileid = request.getParameter("fileid");
        String tags=request.getParameter("tags");
        String hastag=request.getParameter("hastag");
        map.put("signature", signature);
        if (StringUtils.isEmpty(id) ||
                StringUtils.isEmpty(realName) ||
                StringUtils.isEmpty(handphone) ||
                StringUtils.isEmpty(workplace)
                /* StringUtils.isEmpty(birthday) ||
                 StringUtils.isEmpty(sex) ||
                StringUtils.isEmpty(experience) ||
                StringUtils.isEmpty(fax) ||
                StringUtils.isEmpty(title) ||
                StringUtils.isEmpty(postCode) ||
                StringUtils.isEmpty(email) ||
                StringUtils.isEmpty(position) ||
                StringUtils.isEmpty(address) ||
                StringUtils.isEmpty(note) ||
                StringUtils.isEmpty(telephone)*/
                ) {
            zmap.put("returncode", "DEFAULT");
        } else {
            map.put("id", id);
            map.put("realname", "".equals(realName) ? null:realName);
            map.put("experience", "".equals(experience) ? null:experience);
            map.put("fax", "".equals(fax) ? null:fax);
            map.put("sex", "".equals(sex) ? null:sex);
            map.put("title", "".equals(title) ? null:title);
            map.put("handphone", "".equals(handphone) ? null:handphone);
            map.put("postcode", "".equals(postCode) ? null:postCode);
            map.put("birthday", "".equals(birthday) ? null:birthday);
            map.put("email", "".equals(email) ? null:email);
            map.put("position", "".equals(position) ? null:position);
            map.put("address", "".equals(address) ? null:address);
            map.put("note", "".equals(note) ? null:note);
            map.put("workplace", "".equals(workplace) ? null:workplace);
            map.put("telephone", "".equals(telephone) ? null:telephone);
            map.put("avatar", "".equals(fileid) ? null:fileid);
            map.put("tags", "".equals(tags) ? null:tags);
            map.put("hastag", "".equals(hastag) ? null:hastag);
            zmap = userinfoService.update(map);
            Map<String, Object> user=getUserInfo();
   		 	user = userService.getUserInfo(MapUtils.getString(user, "username"), "1");
   		 	HttpSession session = request.getSession();
   		 	session.setAttribute(Const.SESSION_USER_CONST_WRITER, user);
            session.setAttribute(Const.SESSION_USER_CONST_TYPE, "1");
        }
        return zmap;
    }
    
    
    /**
     * 跳转到修改密码页面
     * @param request
     * @return
     */
    @RequestMapping(value = "comchangepwd")
    public ModelAndView tochangepwd(HttpServletRequest request){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("commuser/userinfo/comchangepwd");
        return mv;
    }
    
    /**
     * 机构用户修改密码
     * @param orgUser
     * @return
     */
    @RequestMapping(value = "/updateorguserpassword",method = RequestMethod.POST/*,consumes = "application/json"*/)
    @ResponseBody
    public ResponseBean<Map<String, Object>> updateOrgUserPassword(HttpServletRequest request) throws IOException {
        ResponseBean<Map<String, Object>> responseBean=new ResponseBean<Map<String, Object>>();
        Map <String,Object> map = new HashMap<String, Object>();
        Map <String,Object> map1 = this.getUserInfo() ;
    	Long userId = new Long(String.valueOf(map1.get("id")));
    	map.put("id", userId);
    	String password=request.getParameter("password");
     /*   DesRun desRun=new DesRun("",password);
        map.put("password", desRun.enpsw);
        userinfoService.updatePassword(map);*/
        userService.modifyUser(MapUtils.getString(map1,"username"),password);
        return responseBean;
    }
}

package com.bc.pmpheep.back.commuser.userinfo.controller;

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
		}
        //头像回显
        //图片为空则显示默认图片
        /*  if (("").equals(map.get("avatar"))) {
            map.put("avatar", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
        }*/
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
//		String avatar=request.getParameter("avatar");
//		String id=request.getParameter("id");
//		map=userinfoService.updateavatar(avatar, id);
//		 Map<String, Object> user=getUserInfo();
//		 user = userService.getUserInfo(MapUtils.getString(user, "username"), "1");
//		 HttpSession session = request.getSession();
//		 session.setAttribute(Const.SESSION_USER_CONST_WRITER, user);
//         session.setAttribute(Const.SESSION_USER_CONST_TYPE, "1");
        
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
       /* String org_id = request.getParameter("org_id");*/
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
                StringUtils.isEmpty(experience) ||
                StringUtils.isEmpty(fax) ||
                StringUtils.isEmpty(sex) ||
                StringUtils.isEmpty(title) ||
                StringUtils.isEmpty(handphone) ||
                StringUtils.isEmpty(postCode) ||
                StringUtils.isEmpty(birthday) ||
                StringUtils.isEmpty(email) ||
               /* StringUtils.isEmpty(org_id) ||*/
                StringUtils.isEmpty(position) ||
                StringUtils.isEmpty(address) ||
                StringUtils.isEmpty(note) ||
                StringUtils.isEmpty(workplace) ||
                StringUtils.isEmpty(telephone)
                ) {
            zmap.put("returncode", "DEFAULT");
        } else {
            map.put("id", id);
            map.put("realname", realName);
            map.put("experience", experience);
            map.put("fax", fax);
            map.put("sex", sex);
            map.put("title", title);
            map.put("handphone", handphone);
            map.put("postcode", postCode);
            map.put("birthday", birthday);
            map.put("email", email);
           /* map.put("org_id", org_id);*/
            map.put("position", position);
            map.put("address", address);
            map.put("note", note);
            map.put("workplace", workplace);
            map.put("telephone", telephone);
            map.put("avatar", fileid);
            map.put("tags", tags);
            map.put("hastag", hastag);
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
    public ResponseBean<Map<String, Object>> updateOrgUserPassword(HttpServletRequest request){
        ResponseBean<Map<String, Object>> responseBean=new ResponseBean<Map<String, Object>>();
        Map <String,Object> map = new HashMap<String, Object>();
        Map <String,Object> map1 = this.getUserInfo() ;
    	Long userId = new Long(String.valueOf(map1.get("id")));
    	map.put("id", userId);
    	String password=request.getParameter("password");
        DesRun desRun=new DesRun("",password);
        map.put("password", desRun.enpsw);
        userinfoService.updatePassword(map);
        return responseBean;
    }
}

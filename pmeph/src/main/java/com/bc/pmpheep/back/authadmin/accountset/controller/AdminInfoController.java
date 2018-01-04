package com.bc.pmpheep.back.authadmin.accountset.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.authadmin.accountset.bean.OrgAdminUser;
import com.bc.pmpheep.back.authadmin.accountset.service.AdminInfoService;
import com.bc.pmpheep.back.util.DesRun;
import com.bc.pmpheep.controller.bean.ResponseBean;
import com.bc.pmpheep.general.controller.BaseController;
import com.bc.pmpheep.general.service.FileService;
import com.mongodb.gridfs.GridFSDBFile;

/**
 * @Author: SuiXinYang
 * @Description:
 * @Date: Created in 16:25 2017/11/29
 * @Modified: SuiXinYang
 **/
@Controller
@RequestMapping(value="/admininfocontroller")
public class AdminInfoController extends BaseController {
    @Autowired
    @Qualifier("com.bc.pmpheep.back.authadmin.accountset.service.AdminInfoServiceImpl")
    private AdminInfoService adminInfoService;
    @Autowired
    @Qualifier("com.bc.pmpheep.general.service.FileService")
    FileService fileService;
    /**
     * 个人资料修改页面
     * @param request
     * @return
     */
    @RequestMapping(value="/toadmininfo")
    public ModelAndView toadmininfopage(HttpServletRequest request){
        ModelAndView mv=new ModelAndView();
        Map <String,Object> map = this.getUserInfo() ;
    	Long userId = new Long(String.valueOf(map.get("id")));
        
        mv.addObject("admininfo",adminInfoService.getOrgUserById(userId));
        mv.setViewName("authadmin/accountset/admininfo");
        return mv;
    }

    /**
     * 跳转到学校管理员认证界面
     * @param request
     * @return
     */
    @RequestMapping(value = "/toadminattest")
    public ModelAndView toadminattest(HttpServletRequest request){
        ModelAndView mv=new ModelAndView();
//        Long id=Long.parseLong("1267");
        Map <String,Object> map1 = this.getUserInfo() ;
    	Long userId = new Long(String.valueOf(map1.get("id")));
        Map<String,Object> map = adminInfoService.getOrgUserById(userId);
        
        if(null!=map&&map.size()>0){
        	String fileId = (String) map.get("proxy");
        	if(null!=fileId&&!fileId.equals("")){
        		GridFSDBFile file = fileService.get(fileId);
        		if(null!=file){
        			map.put("proxyName", file.getFilename());
        		}
        	
        	}
        }
        
        mv.addObject("admininfo",map);
        mv.setViewName("authadmin/accountset/adminattest");
        return mv;
    }

    /**
     * 跳转到修改密码页面
     * @param request
     * @return
     */
    @RequestMapping(value = "tochangepwd")
    public ModelAndView tochangepwd(HttpServletRequest request){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("authadmin/accountset/changepwd");
        return mv;
    }

    /**
     * 修改机构用户基本信息
     * @param orgUser
     * @return
     */
    @RequestMapping(value = "updateorguser",method = RequestMethod.POST/*,consumes="application/json"*/)
    @ResponseBody
    public String updateOrgUser(/*@RequestBody*/ OrgAdminUser orgUser){
    	Map <String,Object> map1 = this.getUserInfo() ;
    	Long userId = new Long(String.valueOf(map1.get("id")));
        orgUser.setId(userId);
        String code ="";
        if (StringUtils.isEmpty(orgUser.getPosition())||
        	StringUtils.isEmpty(orgUser.getTelephone())||
        	StringUtils.isEmpty(orgUser.getHandphone())||
        	StringUtils.isEmpty(orgUser.getPostCode())||
        	StringUtils.isEmpty(orgUser.getEmail())||
        	StringUtils.isEmpty(orgUser.getFax())||	
        	StringUtils.isEmpty(orgUser.getId())||	
        	/*StringUtils.isEmpty(orgUser.getBirthday())||
        	StringUtils.isEmpty(orgUser.getExperience())||	
        	StringUtils.isEmpty(orgUser.getWorkplace())||	*/
        	StringUtils.isEmpty(orgUser.getAddress())){
        	
        	code="fail";
            
        }else{
        	 adminInfoService.updateOrgUser(orgUser);
             code="success";
        }
       
        return code;
    }

    /**
     * 机构用户修改密码
     * @param orgUser
     * @return
     */
    @RequestMapping(value = "/updateorguserpassword",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public ResponseBean<OrgAdminUser> updateOrgUserPassword(@RequestBody OrgAdminUser orgUser){
        ResponseBean<OrgAdminUser> responseBean=new ResponseBean<>();
//        orgUser.setId(Long.parseLong("1267"));
        Map <String,Object> map1 = this.getUserInfo() ;
    	Long userId = new Long(String.valueOf(map1.get("id")));
        orgUser.setId(userId);
        DesRun desRun=new DesRun("",orgUser.getPassword());
        orgUser.setPassword(desRun.enpsw);
        adminInfoService.updatePassword(orgUser);
        return responseBean;
    }
    
    //上传委托书
    @RequestMapping(value ="/uploadProxy",method = RequestMethod.POST)
    @ResponseBody
    public String uploadProxy(HttpServletRequest request) throws IOException{
    	String code = "";
    	String fileId  = request.getParameter("fileid");
    	String id  = request.getParameter("id");
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("fileId", fileId);
    	map.put("id", id);
    	
    	try{
    		adminInfoService.uploadProxy(map);
    		code="1";
    	}catch(Exception e){
    		
    	}
        return code;
    }
    //下载委托书
    @RequestMapping(value ="/downLoad",method = RequestMethod.POST)
    @ResponseBody
    public void downLoad(HttpServletRequest request) throws IOException{
    	String id  = request.getParameter("id");
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("id", id);
    	
    	try{
    		adminInfoService.uploadProxy(map);
    	}catch(Exception e){
    		
    	}
    }
}
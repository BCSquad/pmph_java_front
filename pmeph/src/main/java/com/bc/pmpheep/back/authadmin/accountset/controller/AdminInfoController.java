package com.bc.pmpheep.back.authadmin.accountset.controller;

import com.bc.pmpheep.back.authadmin.accountset.bean.OrgUser;
import com.bc.pmpheep.back.authadmin.accountset.service.AdminInfoService;
import com.bc.pmpheep.back.util.DesRun;
import com.bc.pmpheep.controller.bean.ResponseBean;
import com.bc.pmpheep.general.bean.FileType;
import com.bc.pmpheep.general.controller.BaseController;
import com.bc.pmpheep.general.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
        Long id=Long.parseLong("1267");
        mv.addObject("admininfo",adminInfoService.getOrgUserById(id));
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
        Long id=Long.parseLong("1267");
        mv.addObject("admininfo",adminInfoService.getOrgUserById(id));
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
    @RequestMapping(value = "updateorguser",method = RequestMethod.POST,consumes="application/json")
    @ResponseBody
    public ResponseBean<OrgUser> updateOrgUser(@RequestBody OrgUser orgUser){
        ResponseBean<OrgUser> responseBean=new ResponseBean<>();
        orgUser.setId(Long.parseLong("1267"));
        if (StringUtils.isEmpty(orgUser.getPosition())){
            orgUser.setPosition("-");
        }
        if (StringUtils.isEmpty(orgUser.getTelephone())){
            orgUser.setTelephone("-");
        }
        if (StringUtils.isEmpty(orgUser.getHandphone())){
            orgUser.setHandphone("-");
        }
        if (StringUtils.isEmpty(orgUser.getPostCode())){
            orgUser.setPostCode("-");
        }
        if (StringUtils.isEmpty(orgUser.getEmail())){
            orgUser.setEmail("-");
        }
        if (StringUtils.isEmpty(orgUser.getFax())){
            orgUser.setFax("-");
        }
        if (StringUtils.isEmpty(orgUser.getAddress())){
            orgUser.setAddress("-");
        }
        adminInfoService.updateOrgUser(orgUser);
        return responseBean;
    }

    /**
     * 机构用户修改密码
     * @param orgUser
     * @return
     */
    @RequestMapping(value = "/updateorguserpassword",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public ResponseBean<OrgUser> updateOrgUserPassword(@RequestBody OrgUser orgUser){
        ResponseBean<OrgUser> responseBean=new ResponseBean<>();
        orgUser.setId(Long.parseLong("1267"));
        DesRun desRun=new DesRun("",orgUser.getPassword());
        orgUser.setPassword(desRun.enpsw);
        adminInfoService.updatePassword(orgUser);
        return responseBean;
    }
    @RequestMapping(value = "/uploadProxy",method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean<OrgUser> uploadProxy(@RequestParam(value = "id",required = true)String id,
                                             @RequestParam("file") MultipartFile file) throws IOException {
        String fileId=fileService.save(file, FileType.ORGUSER_PIC, 0);
        return new ResponseBean<OrgUser>();
    }
}
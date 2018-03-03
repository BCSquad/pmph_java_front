package com.bc.pmpheep.back.commuser.myfriend.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bc.pmpheep.back.commuser.user.bean.CommuserWriterUser;
import com.bc.pmpheep.back.util.RouteUtil;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.bc.pmpheep.back.commuser.myfriend.service.MyFriendService;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * <pre>
 * 功能描述：好友 控制器
 * 使用示范：
 *
 *
 * @author (作者) nyz
 *
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @date (开发日期) 2017-11-29
 * @modify (最后修改时间)
 * @修改人 ：nyz
 * @审核人 ：
 * </pre>
 */
@Controller
@RequestMapping(value = "/myFriend")
public class MyFriendController extends com.bc.pmpheep.general.controller.BaseController {
    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.myfriend.service.MyFriendServiceImpl")
    MyFriendService myFriendService;

    /**
     * <pre>
     * 功能描述：获取当前用户好友列表
     * 使用示范：
     *
     * @return
     * @throws Exception
     * </pre>
     */
    @RequestMapping(value = "/listMyFriend", method = RequestMethod.GET)
    public ModelAndView listMyFriend(HttpServletRequest request) throws Exception {
        // ModelAndView model = this.getModelAndView();
        ModelAndView model = new ModelAndView();
        // WriterUser writerUser =
        // (WriterUser) this.getRequest().getSession().getAttribute(Const.SESSION_WRITER_USER);

        // 获取用户
        Map<String, Object> writerUserMap = this.getUserInfo();
        CommuserWriterUser writerUser = new CommuserWriterUser();
        writerUser.setId(Long.parseLong(writerUserMap.get("id").toString()));
        //writerUser.setId(645L);
        String pageUrl = "commuser/myfriend/myFriend";
        String groupId = request.getParameter("groupId");
        if("invite".equals(request.getParameter("pageType"))){
        	pageUrl = "commuser/myfriend/inviteMyFriend";
        	 model.addObject("groupId", groupId);
        }
        try {
            int startrow = 0;
            List<Map<String, Object>> listFriends = myFriendService.listMyFriend(groupId,writerUser, startrow);
            for(Map<String, Object> map:listFriends){
            	/*if("DEFAULT".equals(map.get("avatar").toString())){
    				map.put("avatar", "statics/pictures/head.png");
    			}else{
    				map.put("avatar", "file/download/"+map.get("avatar")+".action");
    			}*/
                map.put("avatar", RouteUtil.userAvatar(MapUtils.getString(map, "avatar")));
            }
            int remainCount = myFriendService.listMyFriendCount(groupId,writerUser, startrow+listFriends.size());
            model.setViewName(pageUrl);
            model.addObject("row", startrow);
            model.addObject("id", writerUser.getId());
            model.addObject("realname", writerUserMap.get("realname").toString());
            model.addObject("more", listFriends.size());
            model.addObject("listFriends", listFriends);
            model.addObject("listSize", remainCount);
        } catch (CheckedServiceException e) {
            throw new CheckedServiceException(e.getBusiness(), e.getResult(), e.getMessage(),
                    pageUrl);
        }
        return model;
    }

    /**
     * 加载更多
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("more")
    @ResponseBody
    public List<Map<String, Object>> more(HttpServletRequest request) throws Exception {
        int row = Integer.parseInt(request.getParameter("row"));
        String id = request.getParameter("id");
        String groupId = request.getParameter("groupId");
        CommuserWriterUser writerUser = new CommuserWriterUser();
        writerUser.setId(Long.parseLong(id.toString()));
        List<Map<String, Object>> listFriends = myFriendService.listMyFriend(groupId,writerUser, row + 15);
        int remainCount = myFriendService.listMyFriendCount(groupId,writerUser, row+15+listFriends.size());
        for (int i = 0; i < listFriends.size(); i++) {
            listFriends.get(i).put("row", row + 15);
            listFriends.get(i).put("queryid", id);
            listFriends.get(i).put("remainCount", remainCount);
        }
        
        return listFriends;
    }
    
    /**
     * 将好友拉入小组中
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("invite")
    @ResponseBody
    public String invite(HttpServletRequest request) throws Exception{
    	String flag = "1";
    	String id = request.getParameter("id");
    	String groupId = request.getParameter("groupId");
    	  // 获取用户
        Map<String, Object> writerUserMap = this.getUserInfo();
        CommuserWriterUser writerUser = new CommuserWriterUser();
        writerUser.setId(Long.parseLong(writerUserMap.get("id").toString()));
      try {
    	  myFriendService.invite(id,groupId);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		flag= "0";
	}
    	 
    	return flag;
    }
}

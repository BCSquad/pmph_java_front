package com.bc.pmpheep.back.commuser.myfriend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.myfriend.bean.WriterFriendVO;
import com.bc.pmpheep.back.commuser.myfriend.service.MyFriendService;
import com.bc.pmpheep.back.commuser.user.bean.WriterUser;
import com.bc.pmpheep.back.util.CollectionUtil;
import com.bc.pmpheep.back.util.RouteUtil;
import com.bc.pmpheep.general.controller.BaseController;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * 
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
public class MyFriendController extends BaseController {
    @Autowired
    MyFriendService myFriendService;

    /**
     * 
     * <pre>
     * 功能描述：获取当前用户好友列表
     * 使用示范：
     *
     * @return
     * @throws Exception
     * </pre>
     */
    @RequestMapping(value = "/listMyFriend", method = RequestMethod.GET)
    public ModelAndView listMyFriend() throws Exception {
        ModelAndView model = new ModelAndView();
        WriterUser writerUser = new WriterUser();
        writerUser.setId(Long.parseLong(this.getUserInfo().get("id").toString()));
        String pageUrl = "";
        try {
            List<WriterFriendVO> listFriends = myFriendService.listMyFriend(writerUser);
            if (CollectionUtil.isNotEmpty(listFriends)) {
                for (WriterFriendVO writerFriendVO : listFriends) {
                    writerFriendVO.setAvatar(RouteUtil.DEFAULT_USER_AVATAR);
                }
            }
            model.setViewName(pageUrl);
            model.addObject("listFriends", listFriends);
        } catch (CheckedServiceException e) {
            throw new CheckedServiceException(e.getBusiness(), e.getResult(), e.getMessage(),
                                              pageUrl);
        }
        return model;
    }
}

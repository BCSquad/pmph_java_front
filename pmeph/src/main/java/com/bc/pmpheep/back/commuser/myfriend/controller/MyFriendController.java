package com.bc.pmpheep.back.commuser.myfriend.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bc.pmpheep.back.commuser.user.bean.CommuserWriterUser;
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
    public ModelAndView listMyFriend() throws Exception {
        // ModelAndView model = this.getModelAndView();
        ModelAndView model = new ModelAndView();
        // WriterUser writerUser =
        // (WriterUser) this.getRequest().getSession().getAttribute(Const.SESSION_WRITER_USER);

        // 获取用户
        Map<String, Object> writerUserMap = this.getUserInfo();
        CommuserWriterUser writerUser = new CommuserWriterUser();
        writerUser.setId(Long.parseLong(writerUserMap.get("id").toString()));
        writerUser.setId(12180L);
        String pageUrl = "commuser/myfriend/myFriend";
        try {
            int startrow = 0;
            List<Map<String, Object>> listFriends = myFriendService.listMyFriend(writerUser, startrow);
            model.setViewName(pageUrl);
            model.addObject("row", startrow);
            model.addObject("id", writerUser.getId());
            model.addObject("more", listFriends.size());
            model.addObject("listFriends", listFriends);
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
        CommuserWriterUser writerUser = new CommuserWriterUser();
        writerUser.setId(Long.parseLong(id.toString()));
        List<Map<String, Object>> listFriends = myFriendService.listMyFriend(writerUser, row + 15);
        for (int i = 0; i < listFriends.size(); i++) {
            listFriends.get(i).put("row", row + 15);
            listFriends.get(i).put("queryid", id);
        }
        return listFriends;
    }
}

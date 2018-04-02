package com.bc.pmpheep.back.commuser.mymessage.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.bc.pmpheep.back.util.Const;
import com.bc.pmpheep.back.util.RouteUtil;
import com.bc.pmpheep.general.controller.BaseController;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.authadmin.message.service.AllMessageService;
import com.bc.pmpheep.back.authadmin.message.service.AllMessageServiceImpl;
import com.bc.pmpheep.back.commuser.mymessage.service.NoticeMessageService;
import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.ContentService;
import com.bc.pmpheep.general.service.MessageService;


@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {

    @Autowired
    MessageService mssageService;

    @Autowired
    ContentService contentService;

    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.mymessage.service.NoticeMessageServiceImpl")
    NoticeMessageService noticeMessageService;

    @Autowired
    @Qualifier("com.bc.pmpheep.back.authadmin.message.service.AllMessageServiceImpl")
    AllMessageService allMessageServiceImpl;

    //查询申请列表
    @RequestMapping(value = "/applyMessageList")
    public ModelAndView toScheduleList(HttpServletRequest request) {

        Map<String, Object> map = getUserInfo();
        Long userId = new Long(String.valueOf(map.get("id")));
        //Long userId = (long) 24967;

        String condition = request.getParameter("condition");
        String para = request.getParameter("addPara");
        int addPara = 0;
        if (null != para && !para.equals("")) {
            addPara = Integer.parseInt(para);
        } else {
            addPara = 3;
        }
        Map<String, Object> paraMap = new HashMap<String, Object>();
        ModelAndView mv = new ModelAndView();
        paraMap.put("condition", condition);
        paraMap.put("userId", userId);
        paraMap.put("addPara", addPara);
        paraMap.put("startPara", 0);
        //数据列表
        List<Map<String, Object>> list = noticeMessageService.selectApplyMessage(paraMap);
        //不带分页的数据总量
        int count = noticeMessageService.selectSysMessageTotalCount(paraMap);
        //处理消息发送者头像
        for (Map<String, Object> map1 : list) {
            map1.put("avatar", RouteUtil.userAvatar(MapUtils.getString(map1, "avatar")));
            /*if(null==map1.get("avatar")||"DEFAULT".equals(map1.get("avatar").toString())){
                map1.put("avatar", "statics/pictures/head.png");
			}else{
				//map1.put("avatar", "file/download/"+map1.get("avatar")+".action");
				map1.put("avatar", "image/"+map1.get("avatar")+".action");
			}*/
        }
        mv.addObject("count", count - list.size());
        mv.addObject("list", list);
        mv.addObject("listSize", list.size());
        mv.addObject("condition", condition);
        mv.addObject("addPara", addPara);

        mv.setViewName("commuser/message/applyMessage");
        return mv;
    }

    //查询更多申请列表
    @RequestMapping(value = "/loadMoreApply")
    @ResponseBody
    public List<Map<String, Object>> loadMoreApply(HttpServletRequest request) {
        String condition = request.getParameter("condition");
        String para = request.getParameter("startPara");
        Map<String, Object> map = getUserInfo();
        Long userId = new Long(String.valueOf(map.get("id")));
        //Long userId = (long) 1609;
        int startPara = 0;
        Map<String, Object> paraMap = new HashMap<String, Object>();
        if (null != para && !para.equals("")) {
            startPara = Integer.parseInt(para);

            paraMap.put("startPara", startPara);

        } else {
            startPara = 8;
            paraMap.put("startPara", startPara);
        }


        paraMap.put("condition", condition);
        paraMap.put("userId", userId);

        List<Map<String, Object>> list = noticeMessageService.selectApplyMessage(paraMap);
        //不带分页的数据总量
        int count = noticeMessageService.selectSysMessageTotalCount(paraMap);
        //处理消息发送者头像
        for (Map<String, Object> map1 : list) {
            map1.put("avatar", RouteUtil.userAvatar(MapUtils.getString(map1, "avatar")));
            /*if(null==map1.get("avatar")||"DEFAULT".equals(map1.get("avatar").toString())){
				map1.put("avatar", "statics/pictures/head.png");
			}else{
				//map1.put("avatar", "file/download/"+map1.get("avatar")+".action");
				map1.put("avatar", "image/"+map1.get("avatar")+".action");
			}*/
        }

        //控制显示“加载更多”
        if (list.size() > 0) {
            for (int i = 0; i <= list.size(); i++) {
                if (i == 0) {
                    list.get(0).put("count", count - (list.size() + startPara));
                }

            }
        }
        return list;
    }


    //更新申请信息状态
    @RequestMapping(value = "/updateApplyMessage")
    public ModelAndView updateApplyMessage(HttpServletRequest request) {
        String id = request.getParameter("id");
        Map<String, Object> map = getUserInfo();
        Long userId = new Long(String.valueOf(map.get("id")));
        //Long userId = (long) 24967;
        //1：忽略    2：接受
        String flag = request.getParameter("flag");
        ModelAndView mv = new ModelAndView();
        Map<String, Object> paraMap = new HashMap<String, Object>();
        if (null != id && !id.equals("")) {
            long applyId = Long.valueOf(id).longValue();
            paraMap.put("flag", flag);
            paraMap.put("applyId", applyId);
            noticeMessageService.updateApplyMessage(paraMap);
        }

        paraMap.put("userId", userId);
        List<Map<String, Object>> list = noticeMessageService.selectApplyMessage(paraMap);
        for (Map<String, Object> map1 : list) {
            map1.put("avatar", RouteUtil.userAvatar(MapUtils.getString(map1, "avatar")));
			/*if(null==map1.get("avatar")||"DEFAULT".equals(map1.get("avatar").toString())){
				map1.put("avatar", "statics/pictures/head.png");
			}else{
				//map1.put("avatar", "file/download/"+map1.get("avatar")+".action");
				map1.put("avatar", "image/"+map1.get("avatar")+".action");
			}*/
        }
        mv.addObject("list", list);
        mv.addObject("listSize", list.size());
        mv.setViewName("commuser/message/applyMessage");
        return mv;
    }

    //查询通知列表
    @RequestMapping(value = "/noticeMessageList")
    public ModelAndView toNoticeMessageList(HttpServletRequest request) throws ParseException {

        Map<String, Object> map = getUserInfo();
        Long userId = new Long(String.valueOf(map.get("id")));
        //Long userId = (long) 1609;
        String condition = request.getParameter("condition");
        Map<String, Object> paraMap = new HashMap<String, Object>();
        ModelAndView mv = new ModelAndView();

        paraMap.put("condition", condition);
        paraMap.put("userId", userId);
        paraMap.put("startPara", 0);
        List<Map<String, Object>> list = noticeMessageService.selectNoticeMessage(paraMap);
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map1 = list.get(i);
            //取出内容，去掉图片后放置在tcontent中
            delMsgContentToTcontent(map1,"(</?img[^>]*>)|(</br[^>]*>)");

            if (map1.get("msgType").toString().equals("4")) {
                String endTimeStr = map1.get("deadline").toString();


                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                Date currentTime = new Date();


                Date date = sdf.parse(endTimeStr);
                if (currentTime.before(date)) {
                    mv.addObject("notEnd", 1);
                } else {
                    mv.addObject("notEnd", 0);
                }


            }


            map1.put("avatar", RouteUtil.userAvatar(MapUtils.getString(map1, "avatar")));

        }
        //不带分页的数据总量
        int count = noticeMessageService.selectNoticeMessageTotalCount(paraMap);

        mv.addObject("count", count - list.size());
        mv.addObject("listSize", list.size());
        mv.addObject("list", list);
        mv.addObject("condition", condition);

        mv.setViewName("commuser/message/noticeMessage");
        return mv;
    }

    /**
     * 取出map1中的fId，查monggdb内容，去掉内容中匹配regEx_to_replace的内容，存进map1，key为tcontent
     * @param map1
     * @param regEx_to_replace
     */
	private void delMsgContentToTcontent(Map<String, Object> map1,String regEx_to_replace) {
		//处理消息 消息内容
		//if(map1.get("msgType").toString().equals("1")||map1.get("msgType").toString().equals("0")){
			//mongoDB查询通知内容
			Message message = mssageService.get(map1.get("fId").toString());
			if(null!=message){
				String str=message.getContent();
				//regEx_html="(</?img[^>]*>)|(</br[^>]*>)"; //定义HTML的img标签的正则表达式 
				Pattern p_html=Pattern.compile(regEx_to_replace,Pattern.CASE_INSENSITIVE); 
		        Matcher m_html=p_html.matcher(str); 
		        str=m_html.replaceAll(""); //过滤标签 
				map1.put("tcontent",str);
			}else{
				map1.put("tcontent","内容空!");
			}
		//}
	}

    //查询通知列表
    @RequestMapping(value = "/messageIsRead")
    @ResponseBody
    public Map<String,Object> messageIsRead(HttpServletRequest request) throws ParseException {
       Map<String,Object>  returnMap = new HashMap<String,Object>();
        Map<String, Object> map = getUserInfo();
        Long userId = new Long(String.valueOf(map.get("id")));
        //Long userId = (long) 1609;
        String condition = request.getParameter("condition");
        String is_read = request.getParameter("is_read");
        Map<String, Object> paraMap = new HashMap<String, Object>();
        ModelAndView mv = new ModelAndView();

        paraMap.put("condition", condition);
        paraMap.put("userId", userId);
        paraMap.put("is_read", is_read);
        paraMap.put("startPara", 0);
        List<Map<String, Object>> list = noticeMessageService.selectNoticeMessage(paraMap);
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map1 = list.get(i);
          //取出内容，去掉图片后放置在tcontent中
            delMsgContentToTcontent(map1,"(</?img[^>]*>)|(</br[^>]*>)");

            if (map1.get("msgType").toString().equals("4")) {
                String endTimeStr = map1.get("deadline").toString();


                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                Date currentTime = new Date();


                Date date = sdf.parse(endTimeStr);
                if (currentTime.before(date)) {
                    mv.addObject("notEnd", 1);
                } else {
                    mv.addObject("notEnd", 0);
                }


            }


            map1.put("avatar", RouteUtil.userAvatar(MapUtils.getString(map1, "avatar")));

        }
        //不带分页的数据总量
        int count = noticeMessageService.selectNoticeMessageTotalCount(paraMap);
        count = count - list.size();
        returnMap.put("count",count);
        int listSize = list.size();
        returnMap.put("listSize",listSize);
        returnMap.put("list", list);
        returnMap.put("condition",condition);
        return returnMap;
    }

    //查询更多通知列表
    @RequestMapping(value = "/loadMore")
    @ResponseBody
    public List<Map<String, Object>> loadMore(HttpServletRequest request) {
        Map<String, Object> map = getUserInfo();
        Long userId = new Long(String.valueOf(map.get("id")));
        //Long userId = (long) 1609;
        String condition = request.getParameter("condition");
        String para = request.getParameter("startPara");
        String is_read = request.getParameter("is_read");
        int startPara = 0;
        Map<String, Object> paraMap = new HashMap<String, Object>();
        if (null != para && !para.equals("")) {
            startPara = Integer.parseInt(para);

            paraMap.put("startPara", startPara);

        } else {
            startPara = 8;
            paraMap.put("startPara", startPara);
        }

        paraMap.put("condition", condition);
        paraMap.put("userId", userId);
        paraMap.put("is_read", is_read);

        List<Map<String, Object>> list = noticeMessageService.selectNoticeMessage(paraMap);
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map1 = list.get(i);
          //取出内容，去掉图片后放置在tcontent中
            delMsgContentToTcontent(map1,"(</?img[^>]*>)|(</br[^>]*>)");

            if (map1.get("msgType").toString().equals("4")) {
                String endTimeStr = map1.get("deadline").toString();


                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                Date currentTime = new Date();

                try {


                    Date date = sdf.parse(endTimeStr);
                    if (currentTime.before(date)) {
                        map1.put("notEnd", 1);
                    } else {
                        map1.put("notEnd", 0);
                    }

                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            //处理消息发送者头像
			/*if(null==map1.get("avatar")||"DEFAULT".equals(map1.get("avatar").toString())){
				map1.put("avatar", "statics/pictures/head.png");
			}else{
				//map1.put("avatar", "file/download/"+map1.get("avatar")+".action");
				map1.put("avatar", "image/"+map1.get("avatar")+".action");
			}*/
            map1.put("avatar", RouteUtil.userAvatar(MapUtils.getString(map1, "avatar")));
        }
        //不带分页的数据总量
        int count = noticeMessageService.selectNoticeMessageTotalCount(paraMap);
        //控制显示“加载更多”
        if (list.size() > 0) {
            for (int i = 0; i <= list.size(); i++) {
                if (i == 0) {
                    list.get(0).put("count", count - (list.size() + startPara));
                }

            }
        }

        return list;
    }

    //更新申请信息状态(逻辑删除)
    @RequestMapping(value = "/deleteNoticeMessage")
    @ResponseBody
    public String deleteNoticeMessage(HttpServletRequest request) {
        String id = request.getParameter("nid");
        Map<String, Object> map = getUserInfo();
        Long userId = new Long(String.valueOf(map.get("id")));
        //Long userId = (long) 1609;
        Map<String, Object> paraMap = new HashMap<String, Object>();
        if (null != id && !id.equals("")) {
            long noticeId = Long.valueOf(id).longValue();
            paraMap.put("noticeId", noticeId);
            noticeMessageService.deleteNoticeMessage(paraMap);
        }

        paraMap.put("userId", userId);
        //List<Map<String,Object>> list = OrgMessageService.selectNoticeMessage(paraMap);
        //mv.addObject("list",list);
        //mv.setViewName("authadmin/message/noticeMessage");
        String code = "OK";
        return code;
    }

    //查询公告详情
    @RequestMapping(value = "/noticeMessageDetail")
    public ModelAndView toNoticeMessageDetail(HttpServletRequest request) {
        String materialId = request.getParameter("materialId");
        String cmsId = request.getParameter("cmsId");
        String umid = request.getParameter("umid");
        //String flag=request.getParameter("flag");
        String notEnd = request.getParameter("notEnd");
        Map<String, Object> user = getUserInfo();
        //String is_material_entry=request.getParameter("is_material_entry");
        ModelAndView mv = new ModelAndView();
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("cmsId", cmsId);
        paraMap.put("userid", (user != null ? user.get("id") : ""));
        //标题、时间、邮寄地址、备注
        Map<String, Object> mapTitle = new HashMap<String, Object>();
        mapTitle = noticeMessageService.queryNoticeMessageDetail(paraMap);
        mv.addObject("is_material_entry", mapTitle.get("is_material_entry"));
        mv.addObject("firsttag", "个人中心");
        mv.addObject("secondtag", "消息通知");
        mv.addObject("firstpath", "personalhomepage/tohomepage.action");
        mv.addObject("secondpath", "message/noticeMessageList.action");
        mv.addObject("materialId", mapTitle.get("material_id"));
        if ("no".endsWith(mapTitle.get("ended").toString()) &&
                "false".equals(mapTitle.get("is_all_textbook_published").toString()) &&
                "false".equals(mapTitle.get("is_force_end").toString())) {
            mv.addObject("notEnd", 1);
        } else {
            mv.addObject("notEnd", 0);
        }
        if (mapTitle != null && mapTitle.size() > 0 && mapTitle.get("is_material_entry").toString() == "true") {

            paraMap.put("materialId", mapTitle.get("material_id"));
            //备注附件
            List<Map<String, Object>> listAttachment = noticeMessageService.queryNoticeMessageDetailAttachment(paraMap);
            for (Map<String, Object> map : listAttachment) {
                map.put("attachmentId", "file/download/" + map.get("attachment") + ".action");
            }
            //联系人
            List<Map<String, Object>> listContact = noticeMessageService.queryNoticeMessageDetailContact(paraMap);

            mv.addObject("listAttachment", listAttachment);
            mv.addObject("listContact", listContact);
//            mv.addObject("notEnd", notEnd);

        }

        mv.addObject("map", mapTitle);
        //cms附件
        List<Map<String, Object>> cmsAttach = noticeMessageService.queryCMSAttach(paraMap);
        mv.addObject("cmsAttach", cmsAttach);
	/*	Message message = new Message();
		message.setContent(" 人民卫生出版社建社50年来，累计出版图书2万余种，总印数约67000万册，每年出书1000余种，年发行量1000多万册， 年产值超过5亿元。出书品种主要包括： 医学教材、参考书和医学科普读物等，涉及现代医药学和中国传统医药学的所有领域， 体系完整，品种齐全。人卫社不断加强管理，优化选题，提高质量，多出精品，加强服务，已成为国内唯一涵盖医学各领域,各层次的出版机构,能满足不同读者的需求。使读者享受到一流的作者、一流的质量、一流的服务。人卫社的品牌已成为优质图书的代名词。人民卫生出版社出版医学教材有着优良的传统。 从建社伊始的20世纪50年代， 翻译前苏联的医学教材以满足国内教学需要， 到组织国内一流作者自编教材至今已有50年的历史。一代代的医学生都是伴随着人卫社出版的教材成长起来的。");
		message.setId("5a15c32dc5482247f0b8dca2");
		mssageService.add(message);*/

        Content content = contentService.get(mapTitle.get("mongoId").toString());
        if (null != content) {
            mv.addObject("content", content.getContent());
        }

        //更新通知点击量
        noticeMessageService.updateNoticeClicks(cmsId);
        noticeMessageService.updateTitleMessage(umid);

        //mv.addObject("message",message);
        //mv.addObject("flag",flag);

        mv.setViewName("commuser/message/noticeMessageDetail");
        return mv;
    }

    /**
     * 点击详情 修改已读 未读
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateIsreaded")
    @ResponseBody
  public String updateIsreaded(HttpServletRequest request){
      String uid = request.getParameter("uid");
      Map<String, Object> paraMap = new HashMap<String, Object>();
      paraMap.put("id", uid);
      Map<String, Object> user = getUserInfo();
      String user_const_type = (String) request.getSession().getAttribute(Const.SESSION_USER_CONST_TYPE);
      user_const_type = String.valueOf((Integer.parseInt(user_const_type)+1));
      Map<String, Object> map1 = noticeMessageService.queryTitleMessage(paraMap);
      int count = 0;
      String flag = "ok";
      try {
          //只有接收者读了，才标记为已读。（发送者也可以查看）
          if (user_const_type.equals(map1.get("receiver_type").toString())&&user.get("id").equals(map1.get("receiver_id"))) {
              count = allMessageServiceImpl.updateIsRead(uid);
          }
          String isread = "no";
          if (count > 0) {
              isread = "yes";
          }
      }catch (Exception e){
          flag = "error";
      }
      return flag;

  }

    //系统消息标题弹框回显
    @RequestMapping(value = "/queryTitleMessage")
    @ResponseBody
    public Map<String, Object> queryTitleMessage(HttpServletRequest request) {
        String uid = request.getParameter("uid");
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("id", uid);
        Map<String, Object> user = getUserInfo();
        String user_const_type = (String) request.getSession().getAttribute(Const.SESSION_USER_CONST_TYPE);
        user_const_type = String.valueOf((Integer.parseInt(user_const_type)+1));
        Map<String, Object> map1 = noticeMessageService.queryTitleMessage(paraMap);
        int count = 0;
        //只有接收者读了，才标记为已读。（发送者也可以查看）
        if (user_const_type.equals(map1.get("receiver_type").toString())&&user.get("id").equals(map1.get("receiver_id"))) {
        	count = allMessageServiceImpl.updateIsRead(uid);
		}
        String isread = "no";
        if (count > 0) {
            isread = "yes";
        }
        map1.put("isread", isread);
        if (map1.get("msg_type").toString().equals("1") || map1.get("msg_type").toString().equals("0")) {
            //mongoDB查询通知内容
            Message message = mssageService.get(map1.get("msg_id").toString());
            if (null != message) {
                String str = message.getContent();
           /*     String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
                Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
                Matcher m_html = p_html.matcher(str);
                str = m_html.replaceAll(""); //过滤html标签*/
                map1.put("tContent", str);
                //附件
                paraMap.put("msg_id", map1.get("msg_id").toString());
                List<Map<String, Object>> attaList = noticeMessageService.queryTitleMessageAttach(paraMap);
                for (Map<String, Object> map : attaList) {
                    map.put("attachment", "file/download/" + map.get("attachment") + ".action");
                }
                map1.put("attaList", attaList);

                noticeMessageService.updateTitleMessage(uid);

            } else {
                map1.put("tContent", "内容空!");
                //map1.put("attachmentId","附件空!");
            }
            return map1;
        }
        return map1;
    }
			
			
			/*
			//点击标题已读
					@RequestMapping(value="/updateTitleMessage")
					@ResponseBody
					public String updateTitleMessage(HttpServletRequest  request){
						String id=request.getParameter("messid");
							 noticeMessageService.updateTitleMessage(id);
							return "OK";
					}*/

}

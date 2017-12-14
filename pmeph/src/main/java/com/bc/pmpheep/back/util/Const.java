package com.bc.pmpheep.back.util;

import org.springframework.context.ApplicationContext;

/**
 * 
 * <pre>
 * 功能描述：常量工具类
 * 使用示范：
 * 
 * 
 * @author (作者) nyz
 * 
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @date (开发日期) 2017-9-20
 * @modify (最后修改时间) 
 * @修改人 ：nyz 
 * @审核人 ：
 * </pre>
 */
public class Const {
    // 小组默认头像路径（现在没有数据没有路径，等有路径的时候加入）
    public static final String       DEFAULT_GROUP_IMAGE        = "5a012a17a201ee3ed7590351";
    // 新建用户默认头像（现在没有数据没有路径，等有路径的时候加入）
    public static final String       DEFAULT_USER_AVATAR        = "5a012a17a201ee3ed7590351";
    public static final String       DEFAULT_PASSWORD           = "123456";
    public static final String       WEB_PROJECT_NAME           = "PMPH_PROJECT";
    // 社内部门根节点id
    public static final Long         PMPH_DEPARTMENT_ROOT_ID    = 0L;
    // 书籍类别根节点id
    public static final Long         MATERIAL_TYPE_ID           = 0L;
    // 默认
    public static final Integer      PAGE_TOTAL                 = 0;
    // 默认
    public static final Integer      PAGE_NUMBER                = 1;
    // 默认
    public static final Integer      PAGE_SIZE                  = 5;
    // 单元测试数据是否回滚
    public static final boolean      ISROLLBACK                 = true;
    // 用户SessionId
    public static final String       USER_SEESION_ID            = "userSessionId";
    public static final String       SESSION_ID                 = "sessionId";
    // PMPH_USER_TOKEN
    public static final String       SEESION_PMPH_USER_TOKEN    = "sessionPmphUserToken";
    // WRITER_USER_TOKEN
    public static final String       SEESION_WRITER_USER_TOKEN  = "sessionWriterUserToken";
    // PMPH_USER_TOKEN
    public static final String       SESSION_SECURITY_CODE      = "sessionSecCode";
    // PMPH_USER
    public static final String       SESSION_PMPH_USER          = "sessionPmphUser";
    // WRITER_USER
    public static final String       SESSION_WRITER_USER        = "sessionWriterUser";
    public static final String       SESSION_ROLE_RIGHTS        = "sessionRoleRights";
    // 当前菜单
    public static final String       SESSION_MENU_LIST          = "menuList";
    // 全部菜单
    public static final String       SESSION_ALL_MENU_LIST      = "allMenuList";
    public static final String       SESSION_USERPDS            = "userpds";
    // 用户对象
    public static final String       SESSION_USERROL            = "USERROL";
    // 用户名
    public static final String       SESSION_USERNAME           = "USERNAME";
    public static final String       TRUE                       = "T";
    public static final String       FALSE                      = "F";
    // 登录地址
    public static final String       LOGIN                      = "/login_toLogin.do";
    // 图片上传路径
    public static final String       FILE_PATH_IMG              = "uploadFiles/uploadImgs/";
    // 文件上传路径
    public static final String       MSG_FILE_PATH_FILE         = "d:/msg/uploadFiles/file/";
    // 文件上传路径
    public static final String       FILE_PATH_FILE             = "uploadFiles/file/";
    // 不对匹配该值的访问路径拦截（正则）
    public static final String       NO_INTERCEPTOR_PATH        =
                                                                ".*/((login)|(logout)|(code)|(app)|(static)|(main)|(websocket)).*";
    // 该值会在web容器启动时由WebAppContextListener初始化
    public static ApplicationContext WEB_APP_CONTEXT            = null;
    // 普通文件下载控制器方法
    public static final String       FILE_DOWNLOAD              = "/file/download/";
    /**
     * 系统消息类型
     */
    // 系统消息
    public static final Short        MSG_TYPE_0                 = 0;
    // 站内群发
    public static final Short        MSG_TYPE_1                 = 1;
    // 站内私信(作家和机构用户不能群发)
    public static final Short        MSG_TYPE_2                 = 2;
    // 小组互动
    public static final Short        MSG_TYPE_3                 = 3;
    /**
     * 发送者类型
     */
    // 系统
    public static final Short        SENDER_TYPE_0              = 0;
    // 社内用户
    public static final Short        SENDER_TYPE_1              = 1;
    // 作家用户
    public static final Short        SENDER_TYPE_2              = 2;
    // 机构用户
    public static final Short        SENDER_TYPE_3              = 3;
    /**
     * 接收者类型
     */
    // 社内用户
    public static final Short        RECEIVER_TYPE_1            = 1;
    // 作家用户
    public static final Short        RECEIVER_TYPE_2            = 2;
    // 机构用户
    public static final Short        RECEIVER_TYPE_3            = 3;
    /**
     * 发送消息类型
     */
    // 新增
    public static final Short        SEND_MSG_TYPE_0            = 0;
    // 撤回
    public static final Short        SEND_MSG_TYPE_1            = 1;
    // 删除
    public static final Short        SEND_MSG_TYPE_2            = 2;
    /**
     * 发送消息页面（发送对象）
     */
    // 学校管理员
    public static final Integer      SEND_OBJECT_1              = 1;
    // 所有人
    public static final Integer      SEND_OBJECT_2              = 2;
    // 特定对象
    public static final Integer      SEND_OBJECT_3              = 3;
    // 教材报名者
    public static final Integer      SEND_OBJECT_4              = 4;
    /**
     * 用户登录类型
     */
    // 社内用户
    public static final Short        LOGIN_TYPE_PMPH            = 1;
    // 作家用户
    public static final Short        LOGIN_TYPE_WRITER          = 2;
    // 机构用户
    public static final Short        LOGIN_TYPE_ORG             = 3;
    public static final String       LOGIN_USER_IS_ADMINS       = "超级管理员";
    public static final String       LOGIN_USER_IS_ADMIN        = "管理员";
    public static final String       LOGIN_SYS_USER_IS_ADMIN    = "系统管理员";
    /**
     * 学校审核
     * 
     * 0=待审核/1=通过/2=退回
     */
    public static final Integer      ORG_USER_PROGRESS_0        = 0;
    public static final Integer      ORG_USER_PROGRESS_1        = 1;
    public static final Integer      ORG_USER_PROGRESS_2        = 2;
    /**
     * 教师审核
     * 
     * 0=未提交/1=已提交/2=被退回/3=通过
     */
    public static final Short        WRITER_PROGRESS_0          = 0;
    public static final Short        WRITER_PROGRESS_1          = 1;
    public static final Short        WRITER_PROGRESS_2          = 2;
    public static final Short        WRITER_PROGRESS_3          = 3;
    /**
     * CMS
     */
    // CMS 类型
    public static final String       CMS_TYPE                   = "cms";
    // material 教材类型
    public static final String       MATERIAL_TYPE              = "material";
    // CMS 附件下载Controller方法
    public static final String       CMS_FILE_DOWNLOAD          = "/file/cms/download/";
    // material教材通知附件下载Controller方法
    public static final String       MATERIAL_FILE_DOWNLOAD     = "/file/material/download/";
    // 作者类型--系统
    public static final Short        CMS_AUTHOR_TYPE_0          = 0;
    // 作者类型--编辑
    public static final Short        CMS_AUTHOR_TYPE_1          = 1;
    // 作者类型--作家
    public static final Short        CMS_AUTHOR_TYPE_2          = 2;
    // CMS-CONTENT 表上级id
    public static final Long         CMS_CONTENT_PARENT_ID      = 0l;
    // CMS-CONTENT 表 根节点路径
    public static final String       CMS_CONTENT_PATH           = "0";
    // CMS 页面搜索状态(0:是否发布,1:是否审核,2:是否置顶,3:是否热门,4:是否推荐,5:是否隐藏)
    public static final Integer      CMS_PAGE_SEARCH_0          = 0;
    public static final Integer      CMS_PAGE_SEARCH_1          = 1;
    public static final Integer      CMS_PAGE_SEARCH_2          = 2;
    public static final Integer      CMS_PAGE_SEARCH_3          = 3;
    public static final Integer      CMS_PAGE_SEARCH_4          = 4;
    public static final Integer      CMS_PAGE_SEARCH_5          = 5;
    // CMS 审核状态(0=待审核/1=审核未通过/2=通过)
    public static final Short        CMS_AUTHOR_STATUS_0        = 0;
    public static final Short        CMS_AUTHOR_STATUS_1        = 1;
    public static final Short        CMS_AUTHOR_STATUS_2        = 2;
    // Cms_Category_Role permissionType字段(1:后台操作权限,2:审核权限)
    public static final Short        CMS_CATEGORY_PERMISSSION_1 = 1;
    public static final Short        CMS_CATEGORY_PERMISSSION_2 = 2;
    public static final Long         CMS_MATERIAL_ID            = 0l;
    /**
     * CMS栏目类型（0：评论，1：内容管理，2：信息快报管理，3：公告管理）
     */
    public static final Long         CMS_CATEGORY_ID_0          = 0l;
    public static final Long         CMS_CATEGORY_ID_1          = 1l;
    public static final Long         CMS_CATEGORY_ID_2          = 2l;
    public static final Long         CMS_CATEGORY_ID_3          = 3l;


    public static final String       SESSION_USER_CONST         ="SESSION_USER_CONST_";

}

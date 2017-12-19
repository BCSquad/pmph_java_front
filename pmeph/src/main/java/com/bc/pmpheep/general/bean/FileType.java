package com.bc.pmpheep.general.bean;

/**
 * @Author: SuiXinYang
 * @Description: 用于MongoDB文件存取服务的文件类型枚举
 * @Date: Created in 9:06 2017/11/15
 * @Modified: SuiXinYang
 **/
public enum FileType {
    /**
     * 小组文件
     */
    GROUP_FILE("小组文件"),
    /**
     * 消息附件
     */
    MSG_FILE("消息附件"),
    /**
     * 消息内嵌图片
     */
    MSG_PIC("消息内嵌图片"),
    /**
     * 教材通知内容附件
     */
    MATERIAL_NOTICE_ATTACHMENT("教材通知内容附件"),
    /**
     * 教材备注附件
     */
    MATERIAL_NOTE_ATTACHMENT("教材备注附件"),
    /**
     * 教学大纲
     */
    SYLLABUS("教学大纲"),
    /**
     * CMS附件
     */
    CMS_ATTACHMENT("CMS附件"),
    /**
     * 机构用户委托书
     */
    ORGUSER_PIC("机构用户委托书");

    private final String type;

    private FileType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

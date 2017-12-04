package com.bc.pmpheep.back.util;

/**
 * 
 * 
 * 功能描述： 小组/用户默认路径与前缀
 * 
 * 
 * 
 * @author (作者) 曾庆峰
 * 
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @date (开发日期) 2017年11月1日
 * @modify (最后修改时间)
 * @修改人 ：曾庆峰
 * @审核人 ：
 *
 */
public class RouteUtil {
	// 小组默认头像路径（现在没有数据没有路径，等有路径的时候加入）
	public static final String DEFAULT_GROUP_IMAGE = "/statics/group.png";
	// 新建用户默认头像（现在没有数据没有路径，等有路径的时候加入）
	public static final String DEFAULT_USER_AVATAR = "/statics/user.png";
	// mongoDB图片的前缀
	public static final String MONGODB_IMAGE = "/image/";
	// mongoDB文件的前缀(小组)
	public static final String MONGODB_GROUP_FILE = "/groupfile/download/";
	// mongoDB文件的前缀(普通)
	public static final String MONGODB_FILE = "/file/download/";

	/**
	 * 
	 * 
	 * 功能描述：判断小组头像是否是默认头像并为小组头像附上路径
	 *
	 * @param gruopImage
	 * @return
	 *
	 */
	public static String gruopImage(String gruopImage) {
		if (!DEFAULT_GROUP_IMAGE.equals(gruopImage)) {
			gruopImage = MONGODB_IMAGE + gruopImage;
		}
		return gruopImage;
	}

	/**
	 * 
	 * 
	 * 功能描述：判断用户头像是否是默认头像并为用户头像附上路径
	 *
	 * @param avatar
	 * @return
	 *
	 */
	public static String userAvatar(String avatar) {
		if (!DEFAULT_USER_AVATAR.equals(avatar)) {
			avatar = MONGODB_IMAGE + avatar;
		}
		return avatar;
	}

}

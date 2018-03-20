package com.bc.pmpheep.back.util;

import org.springframework.util.StringUtils;

/**
 * 
 * 
 * 功能描述： 小组/用户默认路径与前缀
 * 文章封面路径
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
	public static final String DEFAULT_GROUP_IMAGE = "statics/image/default_image.png";
	// 新建用户默认头像（现在没有数据没有路径，等有路径的时候加入）
	public static final String DEFAULT_USER_AVATAR = "statics/image/default_image.png";
	// 新建文章封面默认头像（现在没有数据没有路径，等有路径的时候加入）
	public static final String DEFAULT_ARTICLE_AVATAR = "statics/testfile/p2.png";
	// mongoDB图片的前缀
	public static final String MONGODB_IMAGE = "image/";
	// mongoDB文件的前缀(小组)
	public static final String MONGODB_GROUP_FILE = "groupfile/download/";
	// mongoDB文件的前缀(普通)
	public static final String MONGODB_FILE = "file/download/";
	//图书 默认图片
	public static final String DEFAULT_BOOK = "statics/image/564f34b00cf2b738819e9c35_122x122!.jpg";

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
		if (StringUtil.isEmpty(gruopImage)||"DEFAULT".equals(gruopImage)|| gruopImage.contains("statics")||gruopImage.contains("default_image")||gruopImage.contains("png")) {
			gruopImage = DEFAULT_GROUP_IMAGE;
		}
		if (!DEFAULT_GROUP_IMAGE.equals(gruopImage)) {
			gruopImage = MONGODB_IMAGE + gruopImage + ".action";
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
		if (StringUtil.isEmpty(avatar)||"DEFAULT".equals(avatar)) {
			avatar = DEFAULT_USER_AVATAR;
		}
		if (!DEFAULT_USER_AVATAR.equals(avatar)) {
			avatar = MONGODB_IMAGE + avatar + ".action";
		}
		return avatar;
	}
	
	
	/**
	 * 
	 * 
	 * 功能描述：判断文章封面是否是默认头像并为文章封面附上路径
	 *
	 * @param cover
	 * @return
	 *
	 */
	public static String articleAvatar(String cover) {
		if (StringUtil.isEmpty(cover)||"DEFAULT".equals(cover)) {
			cover = DEFAULT_ARTICLE_AVATAR;
		}
		if (!DEFAULT_ARTICLE_AVATAR.equals(cover)) {
			cover = MONGODB_IMAGE + cover + ".action";
		}
		return cover;
	}

	/**
	 * 功能描述: 默认图像
	 * @param image
	 * @return
	 */
	public static String bookAvatar(Object image){
		String returnValue = "";
		if (StringUtils.isEmpty(image)||"DEFAULT".equals(image.toString())) {
			returnValue = DEFAULT_BOOK;
		}
		if (!DEFAULT_BOOK.equals(returnValue)) {
			returnValue = MONGODB_IMAGE + image.toString() + ".action";
		}
		return returnValue;
	}

}

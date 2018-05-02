package com.bc.pmpheep.back.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.bc.pmpheep.general.pojo.Content;

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
		
		Pattern pattern = Pattern.compile("^[A-z0-9]{24}$");
		Matcher matcher = pattern.matcher(avatar);
		if (matcher.matches()) {
			avatar = MONGODB_IMAGE + avatar + ".action";
		}else{
			avatar = DEFAULT_USER_AVATAR;
		}
		
		/*if (StringUtil.isEmpty(avatar)||"DEFAULT".equals(avatar)) {
			avatar = DEFAULT_USER_AVATAR;
		}
		if (!DEFAULT_USER_AVATAR.equals(avatar)) {
			avatar = MONGODB_IMAGE + avatar + ".action";
		}*/
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
	
	
	/**
	 * 获取html字符串中的第一张图片的全路径，包括mdb图片或其他网络图片，输入为Content
	 * @param content
	 * @param contextpath
	 * @return
	 */
	 	public static String getFirstImgUrlFromHtmlStr(Content content,String contextpath) {
	 		String img_url = 
	 				"";
	 		if (content != null) {
	 			img_url = getFirstImgUrlFromHtmlStr(content
	 					.getContent(),contextpath);
	 			
	 		}
	 		return img_url;
	 	}
	 	
	 	/**
	 	 * 获取html字符串中的第一张图片的全路径，包括mdb图片或其他网络图片，输入为String张
	 	 * @param html
	 	 * @param contextpath
	 	 * @return
	 	 */
	 	public static String getFirstImgUrlFromHtmlStr(String html,String contextpath){
	 		String img_url = 
	 				"";
	 		if ("/".equals(contextpath.substring(contextpath.length()-1))) {
    			contextpath = contextpath.substring(0,contextpath.length()-1);
			}
	 		String img = "";
	        Pattern p_image;
	        Matcher m_image;
	        List<String> pics = new ArrayList<>(16);
	        //String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址  
	        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
	        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
	        m_image = p_image.matcher(html);
	        while (m_image.find()) {
	            img = img + "," + m_image.group();
	            // Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img); //匹配src  
	            
	            Matcher m = Pattern.compile("src\\s*=\\s*\".*?\"").matcher(img);
	            while (m.find()) {
	            	String first_src = m.group();
	            	Matcher mdb_src = Pattern.compile("src\\s*=\\s*\".*?([A-z0-9]{24}?)").matcher(first_src);
	            	Matcher http_src = Pattern.compile("src\\s*=\\s*\"(.*?)\"").matcher(first_src);
	            	
	            	if (mdb_src.find()) {
	            		
	            		pics.add(contextpath+"/image/"+mdb_src.group(1)+".action");
					}else if(http_src.find()){
						pics.add(http_src.group(1));
					}else{
						pics.add("");
	            }
	                
	        }
	        }
	        
	        if (pics.size()>0) {
	        	img_url = pics.get(0);
			}
	        return img_url;
	 	}

}

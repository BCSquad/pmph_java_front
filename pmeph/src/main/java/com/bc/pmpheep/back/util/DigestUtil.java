package com.bc.pmpheep.back.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


/**
 *
 *
 * @Filename DigestUtil.java
 *
 * @Description 签名工具类
 *
 * @Version 1.0
 *
 *
 */
public class DigestUtil {

    private static final Logger logger = LoggerFactory
            .getLogger(DigestUtil.class.getName());

    /**
     * 签名编码
     */
    public static final String UTF8 = "utf-8";
    /**
     * 签名key
     */
    public static final String SIGN_KEY = "sign";

    /**
     * 渠道id key
     */
    public static final String CHANNEL_ID_KEY = "channelId";

    /**
     * 签名类型key，支持DigestALGEnum
     */
    public static final String SIGN_TYPE_KEY = "signType";

    /**
     * utc时间key
     */
    public static final String TIMESTAMP_KEY = "utc_time_stamp";

    /**
     *
     * 签名算法
     *
     * @Filename DigestUtil.java
     *
     * @Description
     *
     * @Version 1.0
     *
     * @Author bohr.qiu
     *
     * @Email qzhanbo@yiji.com
     *
     * @History <li>Author: bohr.qiu</li> <li>Date: 2013-1-5</li> <li>Version:
     *          1.0</li> <li>Content: create</li>
     *
     */
    public static enum DigestALGEnum {
        SHA256("SHA-256"), MD5("MD5");
        private String name;

        DigestALGEnum(String name) {
            this.name = name;
        }

        public static DigestALGEnum getByName(String name) {
            for (DigestALGEnum _enum : values()) {
                if (_enum.getName().equals(name)) {
                    return _enum;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 以Map中key的字符顺序排序后签名，如果secretKey不为空，排在最后面签名。<br/>
     * 比如：Map中值如下：<br/>
     * keyA=valueA<br/>
     * keyB=valueB<br/>
     * keyA1=valueA1<br/>
     * <br/>
     * security_check_code为yjf<br/>
     *
     * 待签名字符串为：<br/>
     * keyA=valueA&keyA1=valueA1&keyB=valueByjf<br/>
     * <b>注意:</b>SIGN_KEY不会被签名
     *
     *
     * @param dataMap
     * @param securityCheckKey
     *            密钥
     * @param de
     *            摘要算法
     * @return
     */
    public static <T> String digest(Map<String, T> dataMap,
                                    String securityCheckKey, DigestALGEnum de, String encoding) {
        if (dataMap == null) {
            throw new IllegalArgumentException("数据不能为空");
        }
        if (securityCheckKey == null) {
            throw new IllegalArgumentException("安全校验码数据不能为空");
        }
        if (de == null) {
            throw new IllegalArgumentException("摘要算法不能为空");
        }
        if (StringUtils.isBlank(encoding)) {
            throw new IllegalArgumentException("字符集不能为空");
        }

        TreeMap<String, T> treeMap = new TreeMap<String, T>(dataMap);
        StringBuilder sb = new StringBuilder(securityCheckKey);
        for (Entry<String, T> entry : treeMap.entrySet()) {
            logger.debug(entry.getKey()+":"+entry.getValue()+" ; ");
            if (entry.getValue() == null) {
                throw new IllegalArgumentException("待签名值不能为空");
            }
            if (entry.getKey().equals(SIGN_KEY)) {
                continue;
            }
            sb.append(entry.getKey()).append(entry.getValue().toString()).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);

        sb.append(securityCheckKey);

        byte[] toDigest;
        try {
            String str = sb.toString();
            toDigest = str.getBytes(encoding);
            if (logger.isDebugEnabled()) {
                logger.debug("待签名url:" + str);
            }
            MessageDigest md = MessageDigest.getInstance(de.getName());
            md.update(toDigest);
            return new String(Hex.encodeHex(md.digest()));
        } catch (Exception e) {
            throw new RuntimeException("签名失败", e);
        }
    }

    public static <T> String digest(Map<String, T> dataMap,String securityCheckKey) {
        return digest(dataMap,securityCheckKey, DigestALGEnum.MD5,"UTF-8");
    }

    public static String sign(String org) throws Exception {

        String str = org;
        byte[] toDigest = str.getBytes("utf-8");
        if (logger.isDebugEnabled()) {
            logger.debug("待签名url:" + str);
        }
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(toDigest);
        return new String(Hex.encodeHex(md.digest()));

    }


    private void transeURL(String url) {
        //URL url=new URL(url);

    }

    public static void main(String[] arg) throws Exception{

        Map<String,String> api=new HashMap<String,String>();
        api.put("app_key","nmkt8v9NkWbQ9WPFl3l6lFNsyThsfcep");
        api.put("format","json");
        api.put("method","com.ai.ecp.pmph.staff.scoreCal");
        api.put("session","MDzjI012CaqX4HG1HbOj35ps1yOYxJ7KfL1ezjKT89OLZZe0f22S6LY6eZ4DleBR");
        api.put("sign_method","md5");
        api.put("timestamp", DateUtil.formatTimeStamp("yyy-MM-dd HH:mm:ss",DateUtil.getCurrentTime()));
        api.put("v","1.0");
        String securityCheckKey = "hbP5YsbmiWnkOP4IPtXE126JiIaFRCWD4gpfrcULPbs5hytCw06T2SooKfcUnc2g";

        TreeMap<String, String> treeMap = new TreeMap<String, String>(api);
        StringBuilder sb = new StringBuilder(securityCheckKey);
        for (Entry<String, String> entry : treeMap.entrySet()) {
            logger.debug(entry.getKey()+":"+entry.getValue()+" ; ");
            if (entry.getValue() == null) {
                throw new IllegalArgumentException("待签名值不能为空");
            }
            if (entry.getKey().equals(SIGN_KEY)) {
                continue;
            }
            sb.append(entry.getKey()).append(entry.getValue().toString()).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);

        sb.append(securityCheckKey);
        String sing="";
        byte[] toDigest;
        try {
            String str = sb.toString();
            toDigest = str.getBytes("UTF-8");
            if (logger.isDebugEnabled()) {
                logger.debug("待签名url:" + str);
            }
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(toDigest);
            sing=new String(Hex.encodeHex(md.digest()));
        } catch (Exception e) {
            throw new RuntimeException("签名失败", e);
        }
        StringBuffer apitStr=new StringBuffer();
        apitStr.append("http://shoptest1.pmph.com/route/rest?");
        for (Entry<String, String> entry : treeMap.entrySet()) {
            logger.debug(entry.getKey()+":"+entry.getValue()+" ; ");
            if (entry.getValue() == null) {
                throw new IllegalArgumentException("待签名值不能为空");
            }
            if (entry.getKey().equals(SIGN_KEY)) {
                continue;
            }
            apitStr.append(entry.getKey()).append("=").append(entry.getValue().toString()).append("&");
        }
        apitStr.deleteCharAt(apitStr.length() - 1);

        System.out.println(sing);
        System.out.println(sb.toString());
        apitStr.append("sign").append("=").append(sing);
        HashMap<String, Object> biz_content = new HashMap<>();
        biz_content.put("staff_code","username");
        biz_content.put("score","count");
        HashMap<String, Object> params = new HashMap<>();
        params.put("biz_content",biz_content);
        String s1 = params.toString();

        String encode = URLEncoder.encode(apitStr.toString(), "utf-8");

        System.out.println(encode);




        //String appkey="iX38RFcMV7XW5inxvomwJXyIADIcm2gl";
        String secret="testsecret";


        //appKey=UDISJJSJLIKJSOUTWNUNM, timestamp=2015-03-06 14:48:51
        Map<String,String> data=new HashMap<String,String>();
        data.put("appKey", "UDISJJSJLIKJSOUTWNUNM");
        data.put("timestamp", "2015-03-06 14:48:51");

        System.out.println(DigestUtil.digest(data,secret));



        data.clear();
        data.put("app_key", "testappkey");
        data.put("timestamp", "2014-04-02 13:22:39");
        data.put("format", "json");
        data.put("method", "com.fjunicom.test");
        data.put("name", "test");
        data.put("session", "testsession");
        data.put("sign_method", "MD5");
        data.put("v", "1.0");
        System.out.println(DigestUtil.digest(data,"testsecret"));

        String s="testapp_keytest&formatjson&methodaip.user.seller.get&sessiontest&sign_methodmd5&timestamp2013-05-06 13:52:03&v1.0test";
        System.out.println(DigestUtil.sign(s));

        //MockHttpServletRequest request=new MockHttpServletRequest("POST", "/route/rest?notifyTime=2013-09-13 16:59&easylifeOrderNo=20130913000000010935&partnerOrderNo=2013091375192647");
        //request.addParameters(data);
        //request.getParameterMap();
		/*data.clear();
		Map<String,String[]> params=request.getParameterMap();
		for(Entry<String, String[]> param:params.entrySet()) {
			System.out.println(param.getKey()+"==="+ param.getValue()[0]);
			data.put(param.getKey(), param.getValue()[0]);
		}
		System.out.println(DigestUtil.digest(data,secret));
		*/
		/*//f53e6b545f2776a399f2172ce3559bb6
		String sing=DigestUtil.digest(data, "915aeedda09aed0da468cc78707f9bc8",DigestUtil.DigestALGEnum.getByName("MD5"), "UTF-8");
		System.out.println(sing);

		try {
			String x=URLEncoder.encode(FileUtil.readFile("/home/yangqx/work/share/workspace/chongqing/epay/epay/src/item.txt"), "utf-8");
			System.out.println(x);
			String sign=	sign(FileUtil.readFile("/home/yangqx/work/share/workspace/chongqing/epay/epay/src/file.txt"));
			System.out.println(sign);
		} catch (Exception e) {
			// TODO: handle exception
		}*/

    }

    public static <T> String getUrlApi(Map<String,T> api){
        StringBuffer apitStr=new StringBuffer();
        TreeMap<String, T> treeMap = new TreeMap<String, T>(api);
        for (Map.Entry<String, T> entry : treeMap.entrySet()) {
            if (entry.getValue() == null) {
                throw new IllegalArgumentException("待签名值不能为空");
            }
            if (entry.getKey().equals("sign")) {
                continue;
            }
            if(entry.getKey().equals("timestamp")){
                apitStr.append(entry.getKey()).append("=").append(CodecUtil.encodeURL(entry.getValue().toString()).toString()).append("&");
                continue;
            }
            apitStr.append(entry.getKey()).append("=").append(entry.getValue().toString()).append("&");
        }
        apitStr.deleteCharAt(apitStr.length() - 1);
        return apitStr.toString();
    }

}

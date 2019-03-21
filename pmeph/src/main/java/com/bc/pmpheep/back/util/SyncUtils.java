package com.bc.pmpheep.back.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.KeyGenerator;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Map;
import java.util.TreeMap;

public class SyncUtils {



    static Logger logger = LoggerFactory.getLogger(SyncUtils.class);

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

    public static String getSign(Map<String, String> api, String securityCheckKey){
        TreeMap<String, String> treeMap = new TreeMap<String, String>(api);
        StringBuilder sb = new StringBuilder(securityCheckKey);
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            logger.debug(entry.getKey()+":"+entry.getValue()+" ; ");
            if (entry.getValue() == null) {
                throw new IllegalArgumentException("待签名值不能为空");
            }
            if (entry.getKey().equals("sign")) {
                continue;
            }
            sb.append(entry.getKey()).append(entry.getValue().toString()).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);

        sb.append(securityCheckKey);
        String sign="";
        byte[] toDigest;
        try {
            String str = sb.toString();
            toDigest = str.getBytes("UTF-8");
            if (logger.isDebugEnabled()) {
                logger.debug("待签名url:" + str);
            }
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(toDigest);
            return new String(Hex.encodeHex(md.digest()));
        } catch (Exception e) {
            throw new RuntimeException("签名失败", e);
        }
    }

    /**
     * post请求，参数为json字符串
     * @param url 请求地址
     * @param jsonString json字符串
     * @return 响应
     */
    public static String postJson(String url, String jsonString)
    {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            StringEntity stringEntity = new StringEntity(jsonString, "UTF-8");// 解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            post.setEntity(stringEntity);
            response = httpClient.execute(post);
            if(response != null && response.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity entity = response.getEntity();
                result = entityToString(entity);
            }
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
                if(response != null)
                {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }
    public static String entityToString(HttpEntity entity) throws IOException {
        String result = null;
        if(entity != null)
        {
            long lenth = entity.getContentLength();
            if(lenth != -1 && lenth < 2048)
            {
                result = EntityUtils.toString(entity,"UTF-8");
            }else {
                InputStreamReader reader1 = new InputStreamReader(entity.getContent(), "UTF-8");
                CharArrayBuffer buffer = new CharArrayBuffer(2048);
                char[] tmp = new char[1024];
                int l;
                while((l = reader1.read(tmp)) != -1) {
                    buffer.append(tmp, 0, l);
                }
                result = buffer.toString();
            }
        }
        return result;
    }


    /**
     * 发送 get请求
     */
    public static String StringGet(String param, String URL) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        System.out.println(URL+"?"+param);
        String obj=null;
        try {
            HttpUriRequest httpget = new HttpGet(URL+"?"+param);

            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    obj= EntityUtils.toString(entity);
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }


    public static Key getKey(String keySeed) {
        if (keySeed == null) {
            keySeed = System.getenv("AES_SYS_KEY");
        }
        if (keySeed == null) {
            keySeed = System.getProperty("AES_SYS_KEY");
        }
        if (keySeed == null || keySeed.trim().length() == 0) {
            keySeed = "abcd1234!@#$";// 默认种子
        }
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(keySeed.getBytes());
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(secureRandom);
            return generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}

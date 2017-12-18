import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by lihuan on 2017/12/18.
 */

public class HttpClientTest {

    @Test
    public void testHttpClient() {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 创建httpget.
            HttpGet httpget = new HttpGet("http://sso.ipmph.com/ServiceValidate.jsp?ServiceID=yixuejiaoyujiaohu&ST=ST-b9039b1ca4614304b0a2cad29e50cbf3");
            System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                System.out.println("--------------------------------------");
                // 打印响应状态
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    // 打印响应内容长度
                    System.out.println("Response content length: " + entity.getContentLength());
                    // 打印响应内容
                    System.out.println("Response content: " + EntityUtils.toString(entity));
                }
                System.out.println("------------------------------------");
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
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
    }

    @Test
    public void domTest() throws Exception {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<zas><message><var name=\"Status\">FAIL</var><var name=\"Message\">票据未找到或已过期</var></message></zas>";
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        DocumentBuilder db = dbf.newDocumentBuilder();
        //通过DocumentBuilder对象的parser方法加载books.xml文件到当前项目下
        Document document = db.parse(new ByteArrayInputStream(xml.getBytes()));
        NodeList bookList = document.getElementsByTagName("var");
        String status = null;
        String message = null;
        for (int i = 0; i < bookList.getLength(); i++) {
            Node node = bookList.item(i);
            NamedNodeMap attrs = node.getAttributes();
            if ("Status".equals(attrs.getNamedItem("name").getNodeValue())) {
                status = node.getTextContent();
            }
            if ("Message".equals(attrs.getNamedItem("name").getNodeValue())) {
                message = node.getTextContent();
            }
        }

        System.out.println(status);
        System.out.println(message);
    }
}

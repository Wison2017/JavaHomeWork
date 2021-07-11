package netty;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpServerHelper {
    public static String getResponseContent() {
        String requestUrl = "http://localhost:8801";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(requestUrl);
        CloseableHttpResponse response = null;
        String content = "";
        try {
            // 发起post请求
            response = httpClient.execute(httpPost);
            content = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(httpClient, response);
        }
        return content;
    }

    private static void closeResource(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse) {
        try {
            if (httpClient != null) {
                httpClient.close();
            }
            if (httpResponse != null) {
                httpResponse.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}

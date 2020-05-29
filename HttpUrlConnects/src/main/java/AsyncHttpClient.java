import com.alibaba.fastjson.JSONObject;
import lombok.Cleanup;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.*;



public class AsyncHttpClient {
    //异步请求
    public static void main(String[] args) throws IOException {
        //设置请求超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).build();
        HttpGet get = new HttpGet("http://localhost:8889/v1/getDemo?name=daine&age=26");
        CloseableHttpAsyncClient closeableHttpAsyncClient = HttpAsyncClients.createDefault();
        //开启
        closeableHttpAsyncClient.start();
        closeableHttpAsyncClient.execute(get, new FutureCallback<HttpResponse>() {

            @Override
            public void completed(HttpResponse httpResponse) {
                try {
                    String result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
                    System.out.println(result);
                    //请求成功后关闭连接
                    closeableHttpAsyncClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void failed(Exception e) {
                System.out.println("请求失败");
            }

            @Override
            public void cancelled() {

            }
        });



    }
    //同步请求
    @Test
    public void test() throws IOException {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("name","daine"));
        list.add(new BasicNameValuePair("age","26"));
        String params =EntityUtils.toString(new UrlEncodedFormEntity(list));
        HttpGet get = new HttpGet("http://localhost:8889/v1/getDemo"+"?"+params);
        HttpResponse response = closeableHttpClient.execute(get);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }
    @Test
    public void test1() throws IOException {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        JSONObject map = new JSONObject();
        map.put("name","james");
        map.put("age","23");
        StringEntity entity = new StringEntity(map.toString());
        HttpPost post = new HttpPost("http://localhost:8889/postDemo");
        post.setEntity(entity);
        post.setHeader("content-type","application/json");
        HttpResponse response = closeableHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);




    }


}

package com.cases;

import com.action.GetAction;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.config.DataUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import org.testng.annotations.Test;
import org.testng.internal.thread.ThreadUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.io.IOException;
import java.net.CookieStore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class GetTest {

    private  static GetAction getAction = new GetAction();


    @Test
    public void test() throws IOException, InterruptedException {

        Call<ResponseBody> call = getAction.getInfo(DataUtil.getTestData("getdemo.json",0));
        Response<ResponseBody> responseBodyResponse = call.execute();
        String result = null;
        if (responseBodyResponse.body() != null) {
            result = responseBodyResponse.body().string();
        }
        System.out.println(result);


    }
    //异步请求
    @Test
    public  void test05() throws IOException {

        Call<ResponseBody> call = getAction.postInfo(DataUtil.getTestData("postsecond.json",0));
        call.enqueue(new Callback<ResponseBody>() {
            @Override //成功时调用
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println(response.body().string());
//                    call.cancel();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override //失败时调用
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                log.info("请求失败");

            }
        });

    }

    @Test
    public void test01() throws IOException {
        Call<ResponseBody> call = getAction.postInfo(DataUtil.getTestData("postsecond.json",0));
        Response<ResponseBody> response = call.execute();
        if (response.body() != null){
            System.out.println(response.body().string());
        }

    }


    @Test
    public void test02() throws IOException {
        Call<ResponseBody> call = getAction.postJson(DataUtil.getTestData("postdemo.json",0));
        Response<ResponseBody> response = call.execute();
        String result = null;
        if (response.body() != null){
            result = response.body().string();

        }
        System.out.println(result);
    }
    @Test
    public void getCookies() throws IOException {
        Call<ResponseBody> call = getAction.getCookies();
        Response<ResponseBody> response = call.execute();
        System.out.println(response.raw().header("Set-Cookie"));
        System.out.println(response.body().string());
    }

    @Test
    public void setCookies() throws IOException {
        Call<ResponseBody> call = getAction.setCookies();
        Response<ResponseBody> response = call.execute();
        System.out.println(response.body().string());
    }

    public void lu(){
        System.out.println("123");
    }





}

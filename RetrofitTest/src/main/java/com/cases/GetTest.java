package com.cases;

import com.action.GetAction;
import com.config.DataUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
public class GetTest {

    private GetAction getAction = new GetAction();


    @Test
    public void test() throws IOException {

        Call<ResponseBody> call = getAction.getInfo(DataUtil.getTestData("getdemo.json",0));
        Response<ResponseBody> responseBodyResponse = call.execute();
        String result = null;
        if (responseBodyResponse.body() != null) {
            result = responseBodyResponse.body().string();
        }
        System.out.println(result);
        log.info("实际结果是{}",result);

    }
    //异步请求
    @Test
    public void test05() throws IOException {

        Call<ResponseBody> call = getAction.getInfo(DataUtil.getTestData("postsecond.json",0));
        call.enqueue(new Callback<ResponseBody>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null){
                    System.out.println(response.body().string());
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                System.out.println(throwable.getMessage());

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





}

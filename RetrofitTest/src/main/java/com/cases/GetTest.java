package com.cases;

import com.action.GetAction;
import com.config.DataUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import org.testng.annotations.Test;
import retrofit2.Call;
import retrofit2.Response;
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

    @Test
    public void test01() throws IOException {
        Call<ResponseBody> call = getAction.postInfo(DataUtil.getTestData("postsecond.json",0));
        Response<ResponseBody> response = call.execute();
        String result = null;
        if (response.body() != null){
             result = response.body().string();
        }
        System.out.println(result);
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

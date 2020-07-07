package com.cases;

import com.action.GetAction;
import com.alibaba.fastjson.JSONObject;
import okhttp3.ResponseBody;
import org.testng.annotations.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class LoginTest {

    private static GetAction action = new GetAction();



    @Test
    public void login() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("base","s8DjVxB/ZU3ZQY7L11gdh1FH4jtNFyFtUTOL6iGewhTF7MkN4EacCL952wycAbG8U78nnuQnA/AmwSRYdOaKbnYRQRxBs4I9KC13eu+X0zI+XhfCOwz90f5mqwgFYR0Ip6DZn0MCpx9GQ3arT4C/QHfFLkkBmgUT8H+ag4SevoazVq1/9v8f+fhxFhOni76prnV5iPSznOwiwwhX18UIPBe1BTJqzRUMBjbuK3j3j6o=");
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("data","E7ZnPJlChStwsa8QyMHlMY3xIYt61c4F75nnUEXO6U7jAnhy5acyNhALXY6W1ZWz");
        Call<ResponseBody> call = action.login(jsonObject1,jsonObject);
        Response<ResponseBody> response = call.execute();
        System.out.println(response.body().string());

    }

    public void two(){
        System.out.println("hello");
    }




}

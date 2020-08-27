package com.rrtv.cases;

import com.alibaba.fastjson.JSONObject;
import com.rrtv.action.${actionName};
import com.rrtv.utils.BeanContainerUtil;
import com.rrtv.utils.EncryptionUtil;
import com.rrtv.utils.ParamsEncUtil;
import com.rrtv.utils.TestDataUtil;
import lombok.extern.log4j.Log4j2;
import okhttp3.ResponseBody;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import retrofit2.Call;
import retrofit2.Response;
import java.io.IOException;


@Log4j2
public class ${caseName} {

    private ${actionName} action = BeanContainerUtil.getBean(${actionName}.class);
    private JSONObject result;



    @Test(dataProvider = "getTestData")
    public void ${methodName}(JSONObject jsonObject) throws IOException {
        Call<ResponseBody> responseBodyCall = action
                .${methodName}(ParamsEncUtil.getDataParams(jsonObject), ParamsEncUtil.getBaseParams());
        Response<ResponseBody> response = responseBodyCall.execute();
        if (response.body() != null) {
            result = EncryptionUtil.decrypt(response.body().string());
            log.info("活动列表"+result);
        }


    }

    @DataProvider
    private Object[][] getTestData() {
         return new Object[][]{
                   {TestDataUtil.getTestData("data.json",0)}
         };
    }

}

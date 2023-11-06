package com;

import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class Two {
    public static void main(String[] args) {

    }



}

1.jmeter:将异常响应信息写入到jmeter.log,方便排查错误：

package org.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
try{
	String statusCode = prev.getResponseCode();
	if(!statusCode.equals("200")){
		log.error("response code is :"+statusCode);
	}else{
		String res = prev.getResponseDataAsString();
		JSONObject js = OrderRandom.strToJson(res);
		String code = js.getString("code");
		if(!code.equals("200")){
			log.error("response is :"+res);
		}
	}
}catch(Exception e){
	throw new RuntimeException(e);
}


2.jmeter将响应的数据追加写入csv文件：
package org.example;

import com.alibaba.fastjson.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;
import org.apache.commons.lang.StringUtils;

try{
	String a = vars.get("data");
	String data = StringUtils.strip(a,"[]");

	log.info("---data---"+data);
	FileWriter fileWriter = new FileWriter(new File("test.csv"), true);
	BufferedWriter writer = new BufferedWriter(fileWriter);
	if(data != "null" && data != "" && data != null){
		writer.write(data+"\n");
		writer.flush();
		writer.close();
	}
}catch(Exception e){
	throw new Exception(e);
}

















@Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sampleResult = new SampleResult();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("", OrderRandom.getCount());
        jsonObject.put("", OrderRandom.getTotal());
        jsonObject.put("", 0.01);
        jsonObject.put("", 2);
        jsonObject.put("", OrderRandom.getOrderNo());
        //设置显示请求数据
        sampleResult.setSamplerData(JSON.toJSONString(jsonObject));
        sampleResult.setSentBytes(JSON.toJSONString(jsonObject).getBytes().length);
        //请求开始计时
        sampleResult.sampleStart();
        JSONObject res = HttpUtil.doPost(url, jsonObject);
        //请求结束计时
        sampleResult.sampleEnd();
        System.out.println(Config.getDate()+" 响应结果: "+res);
        if (!(res.getIntValue("code") == 200)){
            sampleResult.setSuccessful(false);
            sampleResult.setResponseData(JSON.toJSONString(res));
        }else {
//            sampleResult.setResponseOK();
            sampleResult.setSuccessful(true);
            sampleResult.setResponseData(JSON.toJSONString(res));
            try {
                String redPackData = res.getString("data");
                String actualPack = redPackData.replace("[","");
                String finalActualPack = actualPack.replace("]","");
                writeData(finalActualPack);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        sampleResult.setContentType("application/json");
        return sampleResult;
    }



package com.demo;

import org.apache.commons.lang.text.StrBuilder;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Second implements JavaSamplerClient {

    private static final String URLNAME = "URL";
    private static final String DEFAULTNAME = "http://www.baidu.com";
    private String resultData;
    private String inputUrl;
    /**
     *这个方法决定jmeter中要显示哪些属性
     */
    public Arguments getDefaultParameters() {
        //System.out.println("getDefaultParameters run!");
        Arguments arguments = new Arguments();
        arguments.addArgument(URLNAME,DEFAULTNAME);
        return arguments;
    }
    /**
     * 这个方法主要做初始化工作
     * @param javaSamplerContext
     */


    public void setupTest(JavaSamplerContext javaSamplerContext) {
        inputUrl = javaSamplerContext.getParameter(URLNAME,DEFAULTNAME);

        System.out.println("setupTest run!");
        System.out.println("用户输入的url是："+inputUrl);

    }

    /**
     * 这个方法为实现具体逻辑
     * @param javaSamplerContext
     * @return
     */
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult result = new SampleResult();
        StringBuffer str = new StringBuffer();
        try {
            URL url = new URL(inputUrl);
            URLConnection coon = url.openConnection();
            byte[] buffer = new byte[1024];
            int len;

            result.sampleStart();//标记事务的开始
            InputStream in = coon.getInputStream();
            while ((len = in.read(buffer)) != -1){
                resultData = new String(buffer,"UTF-8");

                str.append(resultData);

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       // resultData = "这就是响应结果";
        resultData = str.toString();
        result.setSampleLabel("自定义java请求");
        result.setResponseData(resultData,"utf-8");
        result.setDataType(SampleResult.TEXT);
        result.setSuccessful(true);
        System.out.println("runTest run!");
        return result;
    }

    /**
     * 主要做一些收尾工作
     * @param javaSamplerContext
     */
    public void teardownTest(JavaSamplerContext javaSamplerContext) {
        System.out.println("teardownTest run!");

    }


}

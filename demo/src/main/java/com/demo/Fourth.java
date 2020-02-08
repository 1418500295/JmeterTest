package com.demo;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class Fourth implements JavaSamplerClient {


    public void setupTest(JavaSamplerContext javaSamplerContext) {
        System.out.println("这是我的第一个java请求");


    }

    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {

        String url = javaSamplerContext.getParameter("url");
        String userName = javaSamplerContext.getParameter("userName");
        String passWord = javaSamplerContext.getParameter("passWord");
        SampleResult result = new SampleResult();
        result.sampleStart();
        result.setSampleLabel("daineTest");
        result.setSuccessful(true);
        result.setDataType(SampleResult.TEXT);
        result.setDataEncoding("utf-8");
        result.setResponseCode("200");
        result.setResponseData(userName,passWord);



        return result;
    }

    public void teardownTest(JavaSamplerContext javaSamplerContext) {
        System.out.println("自定义请求完成");

    }

    public Arguments getDefaultParameters() {
        Arguments arguments = new Arguments();
        arguments.addArgument("url","https://wwww.daine.com");
        arguments.addArgument("userName","diane");
        arguments.addArgument("passWord","123456");
        return arguments;
    }
}

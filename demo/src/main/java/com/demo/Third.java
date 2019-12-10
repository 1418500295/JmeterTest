package com.demo;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.text.MessageFormat;

public class Third implements JavaSamplerClient {
    private String inputName;
    private String password;



    public void setupTest(JavaSamplerContext javaSamplerContext) {
        inputName = javaSamplerContext.getParameter("name");
        password = javaSamplerContext.getParameter("password");
        System.out.println("姓名是"+inputName);
        System.out.println("年龄是"+password);
        System.out.println("测试开始");

    }

    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult result = new SampleResult();
        result.sampleStart();
        result.setResponseData(inputName+":"+password,"utf-8");
        result.setSuccessful(true);
        result.setDataType(SampleResult.TEXT);
        result.setResponseCode("200");

     //   result.setDataEncoding("utf-8");
        result.setSampleLabel("我的java请求");
        return result;
    }

    public void teardownTest(JavaSamplerContext javaSamplerContext) {
        System.out.println("测试结束");
    }

    public Arguments getDefaultParameters() {
        Arguments arguments = new Arguments();
        arguments.addArgument("name","daine");
        arguments.addArgument("password","12");


        return arguments;
    }
}

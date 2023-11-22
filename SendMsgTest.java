package com.im.socket;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class SendMsgTest extends AbstractJavaSamplerClient {


    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sampleResult = new SampleResult();
        String ip = javaSamplerContext.getParameter("ip");
        int port = javaSamplerContext.getIntParameter("port");
        NettyClient client = new NettyClient(ip, port);
        client.connect();
        sampleResult.setSuccessful(true);
        sampleResult.setResponseOK();
        return sampleResult;
    }

    @Override
    public Arguments getDefaultParameters() {
        Arguments arguments = new Arguments();
        arguments.addArgument("ip", "localhost");
        arguments.addArgument("port", "12345");
        return arguments;
    }
}

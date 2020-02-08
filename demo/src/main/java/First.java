import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class First implements JavaSamplerClient {

    private static final String URLNAME = "URL";
    private static final String DEFAULTURL = "https://www.baidu.com";
    public void setupTest(JavaSamplerContext javaSamplerContext) {
        System.out.println("---请求开始---");
    }

    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
//        String url = javaSamplerContext.getParameter(URLNAME);
        String userName  = javaSamplerContext.getParameter("userName");
        String passWord = javaSamplerContext.getParameter("passWord");
        SampleResult result = new SampleResult();
        result.sampleStart();
        result.setSampleLabel("diane定义的请求");
        result.setDataType(SampleResult.TEXT);
        result.setSuccessful(true);
        result.setResponseData(userName,passWord);
        result.setResponseCode("200");
        result.setDataEncoding("utf-8");

        return result;
    }

    public void teardownTest(JavaSamplerContext javaSamplerContext) {
        System.out.println("---请求完成---");

    }

    public Arguments getDefaultParameters() {
        Arguments arguments = new Arguments();
        arguments.addArgument(URLNAME,DEFAULTURL);
        arguments.addArgument("userName","diane");
        arguments.addArgument("passWord","123456");
        return arguments;
    }
}

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义生成模板代码
 */
@Mojo(name = "daine",defaultPhase = LifecyclePhase.PACKAGE)
public class DemoMojo extends AbstractMojo {
    @Parameter
    private String serviceName;

    @Parameter
    private String actionName;

    @Parameter
    private String caseName;

    @Parameter
    private String methodName;

    public  void getServiceModel() {
        try {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
            configuration.setClassForTemplateLoading(DemoMojo.class,"/model");
            configuration.setDefaultEncoding("utf-8");
            Template template = configuration.getTemplate("serviceModel.ftl");
            Map<String,String> map = new HashMap<>();
            map.put("serviceName",serviceName);
            map.put("methodName",methodName);
            FileWriter fileWriter = new FileWriter(new File(System.getProperty("user.dir")+"/src/main/java/com/rrtv/interfaces/"+serviceName+".java"));
            template.process(map,fileWriter);
            fileWriter.close();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public  void getActionModel() {
        try {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
            configuration.setClassForTemplateLoading(DemoMojo.class,"/model");
            configuration.setDefaultEncoding("utf-8");
            Template template = configuration.getTemplate("actionModel.ftl");
            Map<String,String> map = new HashMap<>();
            map.put("serviceName",serviceName);
            map.put("actionName",actionName);
            map.put("methodName",methodName);
            FileWriter fileWriter = new FileWriter(new File(System.getProperty("user.dir")+"/src/main/java/com/rrtv/action/"+actionName+".java"));
            template.process(map,fileWriter);
            fileWriter.close();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public  void getCaseModel() {
        try {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
            configuration.setClassForTemplateLoading(DemoMojo.class,"/model");
            configuration.setDefaultEncoding("utf-8");
            Template template = configuration.getTemplate("caseModel.ftl");
            Map<String,String> map = new HashMap<>();
            map.put("caseName",caseName);
            map.put("actionName",actionName);
            map.put("methodName",methodName);
            FileWriter fileWriter = new FileWriter(new File(System.getProperty("user.dir")+"/src/main/java/com/rrtv/cases/"+caseName+".java"));
            template.process(map,fileWriter);
            fileWriter.close();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void execute() {
        this.getServiceModel();
        this.getActionModel();
        this.getCaseModel();
//        demoMojo.getActionModel();
//        demoMojo.getCaseModel();



    }
}

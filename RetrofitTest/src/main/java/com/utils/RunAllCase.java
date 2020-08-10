package com.utils;

import com.cases.GetTest;
import org.apache.commons.io.FileUtils;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RunAllCase {

    public static void main(String[] args) throws IOException {

        TestNG testNG = new TestNG();
//        Class[] classes  = {GetTest.class};
//        testNG.setTestClasses(classes);


        InputStream inputStream = RunAllCase.class.getClassLoader().getResourceAsStream("testng.xml");
        File xmlFile = new File("run.xml");
        //新建文件xmlFile，将读取的流文件添加到新文件中
        if (inputStream != null) {
            FileUtils.copyInputStreamToFile(inputStream,xmlFile);
        }
        List<String> suites = new ArrayList<>();
        suites.add(xmlFile.getAbsolutePath());
        testNG.setTestSuites(suites);
        testNG.run();



    }
}

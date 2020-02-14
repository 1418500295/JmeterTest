package com.imooc.functions;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.lang.management.CompilationMXBean;
import java.util.*;

public class FirstFunction extends AbstractFunction {
    private Object[] values;
    private CompoundVariable a;
    private CompoundVariable b;
    private static final List<String> desc = new ArrayList<>();
    static {
        desc.add("这是一个自定义函数");
        desc.add("这是第二个参数");
    }


    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {
        a = (CompoundVariable) values[0];
        b = (CompoundVariable) values[1];
        int count = new Integer(a.execute().trim()) + new Integer(b.execute().trim());
        System.out.println(count);

        return String.valueOf(count);
    }

    @Override
    public void setParameters(Collection<CompoundVariable> collection) throws InvalidVariableException {
        checkMinParameterCount(collection,2);
        values = collection.toArray();
        System.out.println("setParameters run!!!");

    }

    @Override
    public String getReferenceKey() {
        System.out.println("getReferenceKey run !!!");
        return "__TTtest";
    }

    @Override
    public List<String> getArgumentDesc() {

        return desc;
    }
}

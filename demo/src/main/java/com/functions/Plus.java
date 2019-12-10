package com.functions;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Plus extends AbstractFunction {
    private Object[] values;
    private CompoundVariable first;
    private CompoundVariable second;



    /**
     * 执行方法
     * @param sampleResult
     * @param sampler
     * @return
     * @throws InvalidVariableException
     */
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {
        first = (CompoundVariable) values[0];
        System.out.println("第一个参数是"+first);
        second = (CompoundVariable) values[1];
        System.out.println("第二个参数是"+second);
        int count;
        count = new Integer(first.execute().trim())+new Integer(second.execute().trim());
        System.out.println("execute run!");
        System.out.println("两数相加之和是"+count);
        return Integer.toString(count);
    }

    /**
     * 设置参数，接收用户传递的参数
     * @param collection
     * @throws InvalidVariableException
     */
    public void setParameters(Collection<CompoundVariable> collection) throws InvalidVariableException {
        checkParameterCount(collection,2);
        values = collection.toArray();

        System.out.println("setParameters run!");

    }

    /**
     * 功能名称
     * @return
     */
    public String getReferenceKey() {
        System.out.println("getReferenceKey run!");

        return "__Daine";
    }

    /**
     * 功能描述，参数描述
     * @return
     */
    public List<String> getArgumentDesc() {
        List desc = new ArrayList();
        desc.add("第一个函数");
        desc.add("第二个函数");
        System.out.println("getArgumentDesc run!");
        return desc;
    }
}

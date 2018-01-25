package com.subiin.framework.demo2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: subiin
 * @date: 2018/1/25 下午9:50
 * @description:
 */
public class CglibTest {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        List<String> methodName = getMethodName();
        System.out.println(methodName);
    }

    public static List<String> getMethodName() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        HashMap<String, Object> propertyMap = new HashMap();
        propertyMap.put("id", Class.forName("java.lang.Integer"));
        propertyMap.put("name", Class.forName("java.lang.String"));
        propertyMap.put("address", Class.forName("java.lang.String"));

        CglibBean bean = new CglibBean(propertyMap);

        bean.setValue("id", new Integer(123));
        bean.setValue("name", "454");
        bean.setValue("address", "789");

        System.out.println(">> id =" + bean.getValue("id"));
        System.out.println(">> name =" + bean.getValue("name"));
        System.out.println(">> address =" + bean.getValue("address"));

        Object object = bean.getObject();

        List<String> result = new ArrayList<>();
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("get")) {
                methodName = methodName.split("get")[1];
                methodName = String.valueOf(Character.toLowerCase(methodName.charAt(0))) + methodName.substring(1);
                result.add(methodName);
                Object invoke = method.invoke(object, null);
                System.out.println(invoke);
            }
        }
        return result;
    }
}

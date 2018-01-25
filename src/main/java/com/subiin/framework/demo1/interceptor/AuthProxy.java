package com.subiin.framework.demo1.interceptor;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: subiin
 * @date: 2018/1/25 下午9:23
 * @description:
 */
public class AuthProxy implements MethodInterceptor {
    private String name;

    public AuthProxy(String name) {
        this.name = name;
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (!"zhangsan".equals(name)) {
            System.out.println("no auth");
            return null;
        }
        return methodProxy.invokeSuper(o, objects);
    }
}

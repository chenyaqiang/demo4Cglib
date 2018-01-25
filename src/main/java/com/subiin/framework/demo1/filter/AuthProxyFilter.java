package com.subiin.framework.demo1.filter;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * @author: subiin
 * @date: 2018/1/25 下午9:33
 * @description: CallbackFilter 中的 accept 方法, 根据不同的 method 返回不同的值 i,
 * 这个值是在 callbacks 中的顺序, 就是调用了 callbacks[i]
 */
public class AuthProxyFilter implements CallbackFilter {
    public int accept(Method method) {
        if (!"query".equalsIgnoreCase(method.getName())) {
            return 0;
        }
        return 1;
    }
}

package com.subiin.framework.demo3;

import net.sf.cglib.proxy.Mixin;

import java.lang.reflect.Method;

/**
 * @author: subiin
 * @date: 2018/1/26 下午10:41
 * @description: Minix 允许多个对象绑定到一个单个的大对象上。
 * 在代理中对方法的调用委托到下面相应的对象中。
 * 这是一种将多个接口混合在一起的方式, 实现了多个接口。
 */
public class MixinDemo {

    public static void main(String[] args) {

        // 接口数组
        Class<?>[] interfaces = new Class[] { MyInterfaceA.class, MyInterfaceB.class };

        // 实例对象数组
        Object[] instances = new Object[] { new MyInterfaceAImpl(), new MyInterfaceBImpl()};

        //Minix 组合为对象
        Mixin mixin = Mixin.create(interfaces, instances);

        MyInterfaceA a = (MyInterfaceA) mixin;
        a.methodA();

        MyInterfaceB b = (MyInterfaceB) mixin;
        b.methodB();

        System.out.println("输出 Mixin 对象的结构...");
        Class<? extends Mixin> clazz = mixin.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        System.out.println(clazz);
    }
}

interface MyInterfaceA {

    public void methodA();

}



interface MyInterfaceB {

    public void methodB();

}



class MyInterfaceAImpl implements MyInterfaceA {

    @Override

    public void methodA() {

        System.out.println("MyInterfaceAImpl.methodA()");

    }

}



class MyInterfaceBImpl implements MyInterfaceB {

    @Override

    public void methodB() {

        System.out.println("MyInterfaceBImpl.methodB()");

    }

}
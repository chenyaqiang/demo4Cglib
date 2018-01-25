package com.subiin.framework.demo2;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

import java.util.Map;
import java.util.Set;

/**
 * @author: subiin
 * @date: 2018/1/25 下午9:49
 * @description: 动态添加bean属性
 */
public class CglibBean {
    public Object object = null;

    public BeanMap beanMap = null;

    public CglibBean() {
        super();
    }

    public CglibBean(Map<String, Object> propertyMap) {
        this.object = generateBean(propertyMap);
        this.beanMap = BeanMap.create(this.object);
    }

    public void setValue(String property, Object value) {
        beanMap.put(property, value);
    }

    public Object getValue(String property) {
        return beanMap.get(property);
    }

    public Object getObject() {
        return object;
    }

    private Object generateBean(Map propertyMap) {
        BeanGenerator generator = new BeanGenerator();
        Set keySet = propertyMap.keySet();
        for (Object aKeySet : keySet) {
            String key = (String) aKeySet;
            generator.addProperty(key, (Class) propertyMap.get(key));
        }
        return generator.create();
    }
}

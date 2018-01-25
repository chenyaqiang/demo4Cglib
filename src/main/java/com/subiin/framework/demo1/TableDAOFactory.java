package com.subiin.framework.demo1;

import com.subiin.framework.demo1.dao.TableDAO;
import com.subiin.framework.demo1.filter.AuthProxyFilter;
import com.subiin.framework.demo1.interceptor.AuthProxy;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * @author: subiin
 * @date: 2018/1/25 下午9:21
 * @description:
 */
public class TableDAOFactory {
    private static TableDAO tableDAO = new TableDAO();

    public static TableDAO getInstance() {
        return tableDAO;
    }

    public static TableDAO getAuthInstance(AuthProxy authProxy) {
        Enhancer enhancer = new Enhancer();
        //进行代理
        enhancer.setSuperclass(TableDAO.class);
        enhancer.setCallback(authProxy);
        //生成代理
        return (TableDAO) enhancer.create();
    }

    public static TableDAO getAuthInstanceByFilter(AuthProxy authProxy) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TableDAO.class);
        enhancer.setCallbacks(new Callback[]{authProxy, NoOp.INSTANCE});
        enhancer.setCallbackFilter(new AuthProxyFilter());
        return (TableDAO) enhancer.create();
    }
}

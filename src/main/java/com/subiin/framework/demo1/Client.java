package com.subiin.framework.demo1;

import com.subiin.framework.demo1.dao.TableDAO;
import com.subiin.framework.demo1.interceptor.AuthProxy;

/**
 * @author: subiin
 * @date: 2018/1/25 下午9:21
 * @description:
 */
public class Client {
    public static void main(String[] args) {
//        TableDAO tableDAO = TableDAOFactory.getInstance();
//        doMethod(tableDAO);
//        haveAuth();
//        haveNoAuth();
        haveAuthByFilter();
    }

    public static void doMethod(TableDAO dao) {
        dao.create();
        dao.query();
        dao.update();
        dao.delete();
    }

    public static void haveAuth() {
        TableDAO zhangSanDao = TableDAOFactory.getAuthInstance(new AuthProxy("zhangsan"));
        doMethod(zhangSanDao);
    }

    public static void haveNoAuth() {
        TableDAO lisiDao = TableDAOFactory.getAuthInstance(new AuthProxy("lisi"));
        doMethod(lisiDao);
    }

    public static void haveAuthByFilter() {
        TableDAO zhangsan = TableDAOFactory.getAuthInstanceByFilter(new AuthProxy("zhangsan"));
        doMethod(zhangsan);

        TableDAO lisi = TableDAOFactory.getAuthInstanceByFilter(new AuthProxy("lisi"));
        doMethod(lisi);
    }
}

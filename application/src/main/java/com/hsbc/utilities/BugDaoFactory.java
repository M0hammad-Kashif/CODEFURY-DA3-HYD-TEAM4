package com.hsbc.utilities;

import com.hsbc.dao.BugDao;
import com.hsbc.dao.BugDaoImpl;

public class BugDaoFactory {
    public static BugDao getBugDao(){
        BugDao bugDao = new BugDaoImpl();
        return bugDao;
    }
}

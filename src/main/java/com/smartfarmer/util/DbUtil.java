package com.smartfarmer.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbUtil {
    private static DbUtil dbUtil;
    private DataSource dataSource;


    private DbUtil(){
        try {
            InitialContext initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:jboss/datasources/farm_management_system");

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static DbUtil getInstance(){
        if( dbUtil== null){
            dbUtil = new DbUtil();
        }
        return dbUtil;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}

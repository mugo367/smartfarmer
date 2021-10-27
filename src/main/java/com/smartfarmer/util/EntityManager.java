package com.smartfarmer.util;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EntityManager {
    private PreparedStatement statement;
    @Resource(lookup = "java:jboss/datasources/farm_management_system")
    private DataSource dataSource;
    //To initialize connection to the database

    public Connection getConnection() throws SQLException {
       return dataSource.getConnection();
    }
    //To close the connection to the database
    @Override
    protected void finalize() throws Throwable {
        statement.close();
        getConnection().close();
    }

}

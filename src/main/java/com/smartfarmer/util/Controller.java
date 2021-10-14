package com.smartfarmer.util;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
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
        this.closeConnection();
    }
    //To read data from the database
    /** Example
     * -> SELECT * || [column_names] FROM table_name
     * -> SELECT * || [column_names] FROM table_name WHERE [Condition (And||Or||Not)]
     * -> SELECT  * || [column_names] FROM table_name ORDER BY [column_names] ASC||DESC
     * */
    public ResultSet readData(String query) throws SQLException {
        statement = getConnection().prepareStatement(query);
        return statement.executeQuery();
    }
    //To write data to the database
    //To delete data from the database
    //To update date in the database
    /** Example
     * -> INSERT INTO table_name ([column_names]) VALUES ([values])
     * -> UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition
     * -> DELETE FROM table_name WHERE condition
     * */
    public int writeData(String query) throws SQLException {
        statement = getConnection().prepareStatement(query);
        return statement.executeUpdate();
    }
    //To close the connection
    private void closeConnection() throws SQLException {
        statement.close();
        getConnection().close();
    }

}

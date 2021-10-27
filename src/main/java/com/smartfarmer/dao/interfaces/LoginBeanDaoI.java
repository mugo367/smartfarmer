package com.smartfarmer.dao.interfaces;


import com.smartfarmer.bean.LoginBean;
import com.smartfarmer.model.Farmer;

import java.sql.SQLException;

public interface LoginBeanDaoI {
    boolean checkUser(LoginBean login) throws Exception;
    Farmer getFarmerDetails(LoginBean login) throws SQLException;
}

package com.smartfarmer.dao;


import com.smartfarmer.bean.LoginBean;
import com.smartfarmer.model.Farmer;

public interface LoginBeanDaoI {
    boolean checkUser(LoginBean login) throws Exception;
    Farmer getFarmerDetails(LoginBean login);
}

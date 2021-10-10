package com.smartfarmer.bean;


import com.smartfarmer.model.Farmer;

public interface LoginBeanI {
    boolean checkUser(LoginBean login) throws Exception;
    Farmer getFarmerDetails(LoginBean login);
}

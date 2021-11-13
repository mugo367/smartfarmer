package com.smartfarmer.dao.interfaces;


import com.smartfarmer.entities.Farmer;
import com.smartfarmer.model.LoginBean;

public interface LoginBeanDaoI {

    Farmer getUser(LoginBean login);
}

package com.smartfarmer.ejb.interfaces;

import com.smartfarmer.model.LoginBean;
import com.smartfarmer.model.LoginResponse;

import javax.servlet.http.HttpSession;

public interface LoginEjbI {
    LoginResponse authenticate(LoginBean loginBean, HttpSession session);
}

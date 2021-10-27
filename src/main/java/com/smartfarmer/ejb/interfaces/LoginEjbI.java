package com.smartfarmer.ejb.interfaces;

import com.smartfarmer.model.LoginResponse;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface LoginEjbI {
    LoginResponse authenticate(Map<String, String[]> params, HttpSession session);
}

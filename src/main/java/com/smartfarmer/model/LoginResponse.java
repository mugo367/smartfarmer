package com.smartfarmer.model;

import com.smartfarmer.entities.Farmer;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private Farmer details;

    private int sessionId;

    private String username;

    private String redirectPage;

    private boolean loginError;

    private String loginErrorMsg;

    public Farmer getDetails() {
        return details;
    }

    public void setDetails(Farmer details) {
        this.details = details;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRedirectPage() {
        return redirectPage;
    }

    public void setRedirectPage(String redirectPage) {
        this.redirectPage = redirectPage;
    }

    public boolean isLoginError() {
        return loginError;
    }

    public void setLoginError(boolean loginError) {
        this.loginError = loginError;
    }

    public String getLoginErrorMsg() {
        return loginErrorMsg;
    }

    public void setLoginErrorMsg(String loginErrorMsg) {
        this.loginErrorMsg = loginErrorMsg;
    }
}

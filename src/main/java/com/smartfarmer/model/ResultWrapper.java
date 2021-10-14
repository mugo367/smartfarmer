package com.smartfarmer.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultWrapper<T> implements Serializable {

    private List<T> list = new ArrayList<T>();
    private boolean success = true;

    private String message = "Done";

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

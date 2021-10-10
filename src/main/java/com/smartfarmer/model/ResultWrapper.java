package com.smartfarmer.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultWrapper<T> implements Serializable {

    private List<T> list = new ArrayList<T>();

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

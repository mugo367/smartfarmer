package com.smartfarmer.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class ResultWrapper<T> implements Serializable {

    private List<T> list = new ArrayList<T>();
    private boolean success = true;

    private String message = "Done";

}

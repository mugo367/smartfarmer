package com.smartfarmer.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ModelListWrapper<T> implements Serializable {

    private List<T> list;

    private int count;

}

package com.smartfarmer.dao;

public interface GenericDaoI<T> {
    T save(T t);

    Boolean delete(T t) throws Exception;

    ModelListWrapper<T> list(T filter,int start, int limit );

    T edit(T t) throws Exception;

    T find(int tId);
}

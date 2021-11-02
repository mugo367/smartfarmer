package com.smartfarmer.dao.interfaces;

import com.smartfarmer.util.ModelListWrapper;

import java.util.Optional;

public interface DaoI<T, P> extends Repository<T, P> {
    T save(T t);
    T edit(T t);
    void delete(T t);
    void deleteById(P id) ;
    ModelListWrapper<T> list(T filter, int start, int limit );
    boolean existsById(P id);
    Optional<T> findById(P id);
    int count();
}

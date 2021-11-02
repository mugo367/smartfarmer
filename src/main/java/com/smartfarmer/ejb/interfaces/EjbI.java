package com.smartfarmer.ejb.interfaces;

import com.smartfarmer.util.ModelListWrapper;

import java.util.Optional;

public interface EjbI<T> {
    T add(T t) throws Exception;
    T edit(T t);
    ModelListWrapper<T> list(T filter, int start, int limit);
    void delete(Long id);
    Optional<T> findById(Long id);
    boolean existsById(Long id);
}

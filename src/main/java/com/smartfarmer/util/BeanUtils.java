package com.smartfarmer.util;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class BeanUtils {
    public static void populate(Object obj, Map<String, String[]> formData)
            throws IllegalAccessException, InvocationTargetException {

        DateTimeConverter dateConverter = new DateConverter(null);
        dateConverter.setPatterns(new String[] {"yyyy-MM-dd"});
        ConvertUtils
                .register(dateConverter, java.util.Date.class);
        BeanUtilsBean.getInstance().populate(obj, formData);
    }
}

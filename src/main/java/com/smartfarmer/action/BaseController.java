package com.smartfarmer.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartfarmer.model.ResultWrapper;
import com.smartfarmer.util.BeanUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class BaseController extends HttpServlet {

    ObjectMapper jsonMapper = new ObjectMapper();

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");


    @SuppressWarnings("rawtypes")
    ResultWrapper resultWrapper = new ResultWrapper<Object>();

    public void transform(Object bean, Map<String, String[]> params){
        try {
            BeanUtils.populate(bean, params);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void handleResponse(HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        res.getWriter().print(jsonMapper.writeValueAsString(resultWrapper));
    }

    @SuppressWarnings("unchecked")
    public void handleResponse(HttpServletResponse res, Object obj) throws IOException {
        res.setContentType("application/json");
        if (obj instanceof Collection<?>){
            resultWrapper.setList((List<?>) obj);
            res.getWriter().print(jsonMapper.writeValueAsString(resultWrapper));
        }else
            res.getWriter().print(jsonMapper.writeValueAsString(obj));
    }

    public void exceptionResponse(HttpServletResponse res, boolean success, String message) throws IOException {
        res.setContentType("application/json");
        resultWrapper.setSuccess(success);
        resultWrapper.setMessage(message);
        res.getWriter().print(jsonMapper.writeValueAsString(resultWrapper));
    }


}
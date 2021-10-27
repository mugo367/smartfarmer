package com.smartfarmer.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartfarmer.model.ResultWrapper;

import javax.servlet.http.HttpServlet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BaseController extends HttpServlet {

    ObjectMapper jsonMapper = new ObjectMapper();

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    ResultWrapper resultWrapper = new ResultWrapper();

}
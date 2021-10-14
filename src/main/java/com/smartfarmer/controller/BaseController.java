package com.smartfarmer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartfarmer.model.ResultWrapper;

import javax.servlet.http.HttpServlet;

public class BaseController extends HttpServlet {

    ObjectMapper jsonMapper = new ObjectMapper();

    ResultWrapper resultWrapper = new ResultWrapper();

}
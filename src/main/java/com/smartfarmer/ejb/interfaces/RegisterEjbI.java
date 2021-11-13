package com.smartfarmer.ejb.interfaces;

import com.smartfarmer.entities.Farmer;
import com.smartfarmer.model.RegisterResponse;
import com.smartfarmer.util.AppException;

public interface RegisterEjbI {

   RegisterResponse register (Farmer farmer) throws AppException;

}

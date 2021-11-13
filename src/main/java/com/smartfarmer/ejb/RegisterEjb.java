package com.smartfarmer.ejb;

import com.smartfarmer.dao.interfaces.FarmerDaoI;
import com.smartfarmer.ejb.interfaces.RegisterEjbI;
import com.smartfarmer.entities.Farmer;
import com.smartfarmer.model.RegisterResponse;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RegisterEjb implements RegisterEjbI {
   @Inject
   FarmerDaoI farmerDao;

   @Override
   public RegisterResponse register(Farmer farmer){
      RegisterResponse registerResponse = new RegisterResponse();

      try {
         if(farmerDao.findUsername(farmer) != null){
            registerResponse.setRegisterError(true);
            registerResponse.setRegisterErrorMsg("Username Exists!!");
         }
         else{
            farmerDao.add(farmer);
            registerResponse.setRegisterError(false);
            registerResponse.setRedirectPage("./login.jsp");

         }
      }catch(Exception ex){
         registerResponse.setRegisterError(true);
         registerResponse.setRegisterErrorMsg(ex.getMessage());
      }
      return registerResponse;
   }
}

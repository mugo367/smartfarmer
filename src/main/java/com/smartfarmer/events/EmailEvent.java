package com.smartfarmer.events;

import com.smartfarmer.model.Email;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

@Stateless
public class EmailEvent {
   @Asynchronous
   public void sendSms(@Observes Email email) {
      if (email == null)
         return;

      System.out.println("Email to send to: " + email.getEmail());
      System.out.println("Message to send: " + email.getMessage());

   }
}
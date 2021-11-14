package com.smartfarmer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor

public class Response implements Serializable {
   private boolean success;
   private String message;
   private Object object;


   public Response(boolean success, String message) {
      this.message = message;
      this.success = success;
   }
}

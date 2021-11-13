package com.smartfarmer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RegisterResponse implements Serializable {

   private String redirectPage;

   private boolean registerError;

   private String registerErrorMsg;
}

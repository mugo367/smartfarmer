package com.smartfarmer.model;

import com.smartfarmer.entities.Farmer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor

public class LoginResponse implements Serializable {

    private Farmer details;

    private int sessionId;

    private String username;

    private String redirectPage;

    private boolean loginError;

    private String loginErrorMsg;


}

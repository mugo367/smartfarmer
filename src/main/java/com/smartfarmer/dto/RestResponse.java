package com.smartfarmer.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RestResponse implements Serializable {

    private boolean success = true;
    private String message = "Done";
    private Object data;

    public RestResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}

package com.smartfarmer.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RestResponse implements Serializable {

    private boolean success = true;
    private String message = "Done";
    private Object data;
    private long totalCount;

}

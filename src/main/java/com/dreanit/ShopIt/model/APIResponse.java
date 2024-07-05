package com.dreanit.ShopIt.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T>{
    private boolean success;
    private String message;
    private T data;
    public APIResponse(boolean success, T data,String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }


}

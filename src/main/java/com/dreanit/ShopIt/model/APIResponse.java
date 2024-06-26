package com.dreanit.ShopIt.model;

import lombok.Data;

@Data
public class APIResponse<T>{
    private boolean success;
    private T data;

    public APIResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }


}

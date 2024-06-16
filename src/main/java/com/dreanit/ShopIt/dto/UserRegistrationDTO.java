package com.dreanit.ShopIt.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Setter
public class UserRegistrationDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Getter
    private List<AddressDto> addresses = new ArrayList<>();;


    // Other fields and methods...


}


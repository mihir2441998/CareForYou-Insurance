package com.careforyou.claimsservice.claims.dto;

import lombok.Data;

@Data
public class CustomerInfoDto {

    private int id;
    private String customerId;
    private String firstName;
    private String middleName;
    private String lastName;

}

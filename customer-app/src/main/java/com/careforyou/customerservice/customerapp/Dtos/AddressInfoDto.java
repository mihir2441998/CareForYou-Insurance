package com.careforyou.customerservice.customerapp.Dtos;

import lombok.Data;

@Data
public class AddressInfoDto {
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String state;
    private int pinCode;
}

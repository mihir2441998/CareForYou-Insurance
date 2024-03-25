package com.careforyou.claimsservice.claims.dto.customer;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDto {

    private String customerNumber;
    private NameInfoDto nameInfoDto;
    private AddressInfoDto addressInfoDto;
    private EligibilityDto eligibilityDto;
    private Date birthDate;
}

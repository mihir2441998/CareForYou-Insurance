package com.careforyou.claimsservice.claims.dto.customer;

import lombok.Data;

@Data
public class CustomerValidationResponse {

    private String claimNumber;

    private String validationConstant;

    private String failureReason;

    private CustomerDto customerDto;
}

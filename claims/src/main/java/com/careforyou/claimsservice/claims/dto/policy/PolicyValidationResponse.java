package com.careforyou.claimsservice.claims.dto.policy;

import lombok.Data;

@Data
public class PolicyValidationResponse {

    private String claimNumber;

    private String validationConstant;

    private String failureReason;

    private PolicyDto policyDto;
}

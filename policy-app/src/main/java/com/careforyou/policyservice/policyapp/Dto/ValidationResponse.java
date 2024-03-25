package com.careforyou.policyservice.policyapp.Dto;

import com.careforyou.policyservice.policyapp.constants.FailureReason;
import com.careforyou.policyservice.policyapp.constants.ValidationConstant;
import lombok.Data;

@Data
public class ValidationResponse {

    private String claimNumber;

    private ValidationConstant validationConstant;

    private FailureReason failureReason;

    private PolicyDto policyDto;
}

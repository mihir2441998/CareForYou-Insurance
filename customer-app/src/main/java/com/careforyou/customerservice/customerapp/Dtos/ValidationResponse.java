package com.careforyou.customerservice.customerapp.Dtos;

import com.careforyou.customerservice.customerapp.constant.FailureReason;
import com.careforyou.customerservice.customerapp.constant.ValidationConstant;
import lombok.Data;

@Data
public class ValidationResponse {

    private String claimNumber;

    private ValidationConstant validationConstant;

    private FailureReason failureReason;

    private CustomerDto customerDto;
}

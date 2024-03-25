package com.careforyou.customerservice.customerapp.services;

import com.careforyou.customerservice.customerapp.Dtos.CustomerDto;
import com.careforyou.customerservice.customerapp.Dtos.ValidationResponse;

public interface KafkaProducer {

    void publishClaimValidation(ValidationResponse validationResponse);
}
package com.careforyou.policyservice.policyapp.service;

import com.careforyou.policyservice.policyapp.Dto.ValidationResponse;

public interface KafkaPublisher {

    void publishClaimValidation(ValidationResponse validationResponse);
}

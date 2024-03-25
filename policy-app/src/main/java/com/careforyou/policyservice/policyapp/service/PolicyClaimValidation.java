package com.careforyou.policyservice.policyapp.service;

import com.careforyou.policyservice.policyapp.Dto.ClaimCreationEvent;

public interface PolicyClaimValidation {

    void validateClaimRequest(ClaimCreationEvent claimCreationEvent);
}

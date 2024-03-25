package com.careforyou.claimsservice.claims.service;

import com.careforyou.claimsservice.claims.dto.ClaimCreationRequest;
import com.careforyou.claimsservice.claims.dto.ClaimDto;
import com.careforyou.claimsservice.claims.dto.customer.CustomerValidationResponse;
import com.careforyou.claimsservice.claims.dto.policy.PolicyValidationResponse;
import com.careforyou.claimsservice.claims.entities.Claim;

public interface ClaimCreationService {

    ClaimDto createClaim(ClaimCreationRequest request);

    void saveClaim(String claimNumber, PolicyValidationResponse policyValidationResponse, CustomerValidationResponse customerValidationResponse);
}

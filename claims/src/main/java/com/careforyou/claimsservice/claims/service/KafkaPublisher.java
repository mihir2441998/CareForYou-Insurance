package com.careforyou.claimsservice.claims.service;

import com.careforyou.claimsservice.claims.dto.ClaimCreationRequest;

public interface KafkaPublisher {

    void saveAndPublishClaimRequest(ClaimCreationRequest claimCreationRequest);
}

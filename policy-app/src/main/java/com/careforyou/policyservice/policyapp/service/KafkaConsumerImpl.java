package com.careforyou.policyservice.policyapp.service;

import com.careforyou.policyservice.policyapp.Dto.ClaimCreationEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerImpl {

    private static final Logger logger= LoggerFactory.getLogger(KafkaConsumerImpl.class);

    @Autowired
    private PolicyClaimValidation policyClaimValidation;

    @KafkaListener(topics = "claim-events", groupId = "policy-group-id")
    public void consumeClaimsEvent(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ClaimCreationEvent claim = objectMapper.readValue(message, ClaimCreationEvent.class);
            logger.info("Claim Event received: " + claim.toString());
            policyClaimValidation.validateClaimRequest(claim);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error(String.format("JsonProcessingException occurred: %s", e));
        }
    }

}

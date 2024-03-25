package com.careforyou.claimsservice.claims.service;

import com.careforyou.claimsservice.claims.dto.customer.CustomerValidationResponse;
import com.careforyou.claimsservice.claims.dto.policy.PolicyValidationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaConsumer {

    private static final Logger logger= LoggerFactory.getLogger(KafkaConsumer.class);

    private Map<String, PolicyValidationResponse> policyStatusMap = new HashMap<>();
    private Map<String, CustomerValidationResponse> customerStatusMap = new HashMap<>();

    @Autowired
    private ClaimCreationService claimCreationService;

    @KafkaListener(topics = "policy-events", groupId = "claims-group-id")
    public void consumePolicyEvent(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PolicyValidationResponse policyValidationResponse = objectMapper.readValue(message, PolicyValidationResponse.class);
            logger.info("Policy Validation Event received: "+ policyValidationResponse.toString());
            policyStatusMap.put(policyValidationResponse.getClaimNumber(), policyValidationResponse);
            processClaimIfBothSuccess(policyValidationResponse.getClaimNumber());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error(String.format("JsonProcessingException occurred: %s",e));
        }
    }

    @KafkaListener(topics = "customer-events", groupId = "claims-group-id")
    public void consumeCustomerEvent(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CustomerValidationResponse customerValidationResponse = objectMapper.readValue(message, CustomerValidationResponse.class);
            logger.info("Customer Validation Event received: "+ customerValidationResponse.toString());
            customerStatusMap.put(customerValidationResponse.getClaimNumber(), customerValidationResponse);
            processClaimIfBothSuccess(customerValidationResponse.getClaimNumber());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error(String.format("JsonProcessingException occurred: %s",e));
        }
    }

    private void processClaimIfBothSuccess(String claimNumber) {
        PolicyValidationResponse policyResponse= policyStatusMap.get(claimNumber);
        CustomerValidationResponse customerResponse= customerStatusMap.get(claimNumber);
        if (policyResponse != null && customerResponse != null) {
           claimCreationService.saveClaim(claimNumber, policyResponse,customerResponse);
        }
    }
}

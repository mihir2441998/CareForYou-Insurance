package com.careforyou.claimsservice.claims.service;

import com.careforyou.claimsservice.claims.config.AppConstants;
import com.careforyou.claimsservice.claims.dto.ClaimCreationRequest;
import com.careforyou.claimsservice.claims.repository.ClaimRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KafkaPublisherImpl implements KafkaPublisher{

    @Autowired
    private KafkaTemplate<Long,Object> kafkaTemplate;

//    @Autowired
//    private ClaimRepository repository;
    private static final Logger logger= LoggerFactory.getLogger(KafkaPublisherImpl.class);


    @Override
    public void saveAndPublishClaimRequest(ClaimCreationRequest claimCreationRequest) {

        //Create message and publish
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String claimJson = objectMapper.writeValueAsString(claimCreationRequest);
            kafkaTemplate.send(AppConstants.TOPIC_CLAIM_EVENTS, 1L, claimJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error(String.format("JsonProcessingException on serialization: %s", e));
        }

    }
}

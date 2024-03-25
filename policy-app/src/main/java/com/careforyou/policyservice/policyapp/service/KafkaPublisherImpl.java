package com.careforyou.policyservice.policyapp.service;

import com.careforyou.policyservice.policyapp.Dto.ValidationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublisherImpl implements KafkaPublisher{

    @Autowired
    private KafkaTemplate<Long,Object> kafkaTemplate;

    private static final Logger logger= LoggerFactory.getLogger(KafkaPublisherImpl.class);


    @Override
    public void publishClaimValidation(ValidationResponse validationResponse) {

        //Create message and publish
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String validationResponseJson = objectMapper.writeValueAsString(validationResponse);
            kafkaTemplate.send("policy-events", 1L, validationResponseJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error(String.format("JsonProcessingException on serialization: %s", e));
        }

    }
}

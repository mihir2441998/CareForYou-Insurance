package com.careforyou.customerservice.customerapp.services;

import com.careforyou.customerservice.customerapp.Dtos.CustomerDto;
import com.careforyou.customerservice.customerapp.Dtos.ValidationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerImpl implements KafkaProducer {
    @Autowired
    private KafkaTemplate<Long,Object> kafkaTemplate;

    private static final Logger logger= LoggerFactory.getLogger(KafkaProducer.class);


    @Override
    public void publishClaimValidation(ValidationResponse validationResponse) {

        //Create message and publish
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String validationResponseJson = objectMapper.writeValueAsString(validationResponse);
            kafkaTemplate.send("customer-events", 1L, validationResponseJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error(String.format("JsonProcessingException on serialization: %s", e));
        }

    }
}

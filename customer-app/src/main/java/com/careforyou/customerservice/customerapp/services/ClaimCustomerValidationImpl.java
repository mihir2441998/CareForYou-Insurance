package com.careforyou.customerservice.customerapp.services;

import com.careforyou.customerservice.customerapp.Dtos.ClaimCreationEvent;
import com.careforyou.customerservice.customerapp.Dtos.CustomerDto;
import com.careforyou.customerservice.customerapp.Dtos.ValidationResponse;
import com.careforyou.customerservice.customerapp.constant.FailureReason;
import com.careforyou.customerservice.customerapp.constant.ValidationConstant;
import com.careforyou.customerservice.customerapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaimCustomerValidationImpl implements ClaimCustomerValidation{

    @Autowired
    private CustomerService customerService;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public void validateCustomer(ClaimCreationEvent event) {

        String customerNumber = event.getCustomerId();
        CustomerDto customerDto = customerService.findCustomerByCustomerNumber(customerNumber);
        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.setClaimNumber(event.getClaimNumber());
        if(customerDto == null){
            validationResponse.setValidationConstant(ValidationConstant.FAILURE);
            validationResponse.setFailureReason(FailureReason.CUSTOMER_NOT_FOUND);
            validationResponse.setCustomerDto(null);
        }
        else{
            validationResponse.setValidationConstant(ValidationConstant.SUCCESS);
            validationResponse.setFailureReason(null);
            validationResponse.setCustomerDto(customerDto);
        }
        kafkaProducer.publishClaimValidation(validationResponse);

    }
}

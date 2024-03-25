package com.careforyou.customerservice.customerapp.services;

import com.careforyou.customerservice.customerapp.Dtos.ClaimCreationEvent;

public interface ClaimCustomerValidation {

    void validateCustomer(ClaimCreationEvent event);
}

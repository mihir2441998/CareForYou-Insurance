package com.careforyou.customerservice.customerapp.services;

import com.careforyou.customerservice.customerapp.Dtos.CustomerDto;
import org.springframework.stereotype.Service;


public interface CustomerService {

    CustomerDto getCustomerById(int id);

    CustomerDto saveCustomer(CustomerDto customerDto);

    CustomerDto findCustomerByCustomerNumber(String customerNumber);

}

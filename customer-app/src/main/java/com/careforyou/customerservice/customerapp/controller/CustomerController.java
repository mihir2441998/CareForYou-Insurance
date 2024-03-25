package com.careforyou.customerservice.customerapp.controller;

import com.careforyou.customerservice.customerapp.Dtos.CustomerDto;
import com.careforyou.customerservice.customerapp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("customer/{id}/load")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable int id){
        CustomerDto customerDto = customerService.getCustomerById(id);
        return new ResponseEntity<>(customerDto, HttpStatus.FOUND);
    }

    @PostMapping("customer/write")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customer){
        CustomerDto customerDto = customerService.saveCustomer(customer);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }
}

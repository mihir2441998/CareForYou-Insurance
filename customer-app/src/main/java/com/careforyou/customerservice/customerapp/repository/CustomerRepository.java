package com.careforyou.customerservice.customerapp.repository;

import com.careforyou.customerservice.customerapp.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findByCustomerNumber(String customerNumber);
}

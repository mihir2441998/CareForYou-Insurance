package com.careforyou.customerservice.customerapp.repository;

import com.careforyou.customerservice.customerapp.entities.AddressInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<AddressInfo, Integer> {
}

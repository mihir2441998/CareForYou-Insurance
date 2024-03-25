package com.careforyou.customerservice.customerapp.repository;

import com.careforyou.customerservice.customerapp.entities.Eligibility;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EligibilityRepository extends CrudRepository<Eligibility, Integer> {
}

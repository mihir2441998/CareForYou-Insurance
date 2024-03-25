package com.careforyou.policyservice.policyapp.repository;

import com.careforyou.policyservice.policyapp.entities.Benefit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenefitRepository extends CrudRepository<Benefit, Integer> {
}

package com.careforyou.policyservice.policyapp.repository;

import com.careforyou.policyservice.policyapp.entities.Policy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends CrudRepository<Policy, Integer> {

    Policy findByPolicyName(String policyName);
}

package com.careforyou.claimsservice.claims.repository;

import com.careforyou.claimsservice.claims.entities.Claim;
import com.careforyou.claimsservice.claims.entities.CustomerInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInfoRepository extends CrudRepository<CustomerInfo, Integer> {
}

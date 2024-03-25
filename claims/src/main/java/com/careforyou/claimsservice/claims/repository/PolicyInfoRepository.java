package com.careforyou.claimsservice.claims.repository;

import com.careforyou.claimsservice.claims.entities.PolicyInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyInfoRepository extends CrudRepository<PolicyInfo, Integer> {
}

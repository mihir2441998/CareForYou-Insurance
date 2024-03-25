package com.careforyou.claimsservice.claims.repository;

import com.careforyou.claimsservice.claims.entities.BenefitInfo;
import com.careforyou.claimsservice.claims.entities.Claim;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenefitInfoRepository extends CrudRepository<BenefitInfo, Integer> {
}

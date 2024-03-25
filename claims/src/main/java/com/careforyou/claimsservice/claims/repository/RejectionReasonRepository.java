package com.careforyou.claimsservice.claims.repository;

import com.careforyou.claimsservice.claims.entities.Claim;
import com.careforyou.claimsservice.claims.entities.RejectionReason;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RejectionReasonRepository extends CrudRepository<RejectionReason, Integer> {
}

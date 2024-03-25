package com.careforyou.claimsservice.claims.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class ClaimIdGenerator implements IdentifierGenerator {

    private static final String PREFIX = "Claim-";
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        // Generate the ID by appending the prefix and a unique number
        return PREFIX + counter.incrementAndGet();
    }
}

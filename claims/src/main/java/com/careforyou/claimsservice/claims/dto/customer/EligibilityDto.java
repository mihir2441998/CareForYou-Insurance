package com.careforyou.claimsservice.claims.dto.customer;

import lombok.Data;

@Data
public class EligibilityDto {
    private boolean isSmoker;
    private boolean isTrafficViolationExits;
    private boolean isCriminalRecordExits;
}

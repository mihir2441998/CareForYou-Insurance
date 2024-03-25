package com.careforyou.claimsservice.claims.dto;

import lombok.Data;

@Data
public class ClaimDto {
    private String id;
    private String claimNumber;
    private ClaimStatus claimStatus;
    private String benefitToClaim;
    private RejectionReasonDto rejectionReason;
    private PolicyInfoDto policyInfo;
    private CustomerInfoDto customerInfo;

}

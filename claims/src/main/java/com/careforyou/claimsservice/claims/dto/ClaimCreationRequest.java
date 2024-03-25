package com.careforyou.claimsservice.claims.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ClaimCreationRequest {

    private String claimNumber;
    private String customerId;
    private String description;
    private Date incidentDate;
    private String policyName;
    private String benefitToClaim;

}

package com.careforyou.customerservice.customerapp.Dtos;

import lombok.Data;

import java.util.Date;

@Data
public class ClaimCreationEvent {

    private String claimNumber;
    private String customerId;
    private String description;
    private Date incidentDate;
    private String policyName;
    private String benefitToClaim;

}

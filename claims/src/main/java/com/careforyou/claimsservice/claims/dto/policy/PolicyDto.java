package com.careforyou.claimsservice.claims.dto.policy;

import lombok.Data;

import java.util.List;

@Data
public class PolicyDto {

    private String policyNumber;

    private String policyName;

    private String policyPlan;

    private List<BenefitDto> benefits;
}

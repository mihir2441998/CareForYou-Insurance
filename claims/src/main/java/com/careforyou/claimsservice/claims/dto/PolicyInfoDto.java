package com.careforyou.claimsservice.claims.dto;

import lombok.Data;

import java.util.List;

@Data
public class PolicyInfoDto {

    private int id;
    private String policyId;
    private String policyName;
    private String policyPlan;
    private List<BenefitInfoDto> benefitInfoList;
}

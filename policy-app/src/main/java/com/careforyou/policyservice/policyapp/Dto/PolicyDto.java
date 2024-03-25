package com.careforyou.policyservice.policyapp.Dto;

import com.careforyou.policyservice.policyapp.entities.Benefit;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class PolicyDto {

    private String policyNumber;

    private String policyName;

    private String policyPlan;

    private List<BenefitDto> benefits;
}

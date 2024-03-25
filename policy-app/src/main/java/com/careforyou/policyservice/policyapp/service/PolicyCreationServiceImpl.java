package com.careforyou.policyservice.policyapp.service;

import com.careforyou.policyservice.policyapp.Dto.BenefitDto;
import com.careforyou.policyservice.policyapp.Dto.PolicyDto;
import com.careforyou.policyservice.policyapp.entities.Benefit;
import com.careforyou.policyservice.policyapp.entities.Policy;
import com.careforyou.policyservice.policyapp.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PolicyCreationServiceImpl implements PolicyCreationService{

    @Autowired
    private PolicyRepository policyRepository;

    @Override
    public PolicyDto createPolicy(PolicyDto policyDto) {
        Policy policyToSave=mapPolicyDtoToPolicy(policyDto);
        policyRepository.save(policyToSave);
        return mapPolicyToPolicyDto(policyToSave);
    }

    @Override
    public List<PolicyDto> getAllPolicies() {
        List<Policy> policies = (List<Policy>) policyRepository.findAll();
        return policies.stream().map(policy -> mapPolicyToPolicyDto(policy)).toList();
    }

    @Override
    public PolicyDto getPolicyByPolicyName(String policyName) {
        Policy policy = policyRepository.findByPolicyName(policyName);
        if(policy==null){
            return null;
        }
        return mapPolicyToPolicyDto(policy);
    }

    public static Policy mapPolicyDtoToPolicy(PolicyDto policyDto) {
        Policy policy = new Policy();
        policy.setPolicyNumber("policy-"+ UUID.randomUUID());
        policy.setPolicyName(policyDto.getPolicyName());
        policy.setPolicyPlan(policyDto.getPolicyPlan());

        // Map benefits
        if (policyDto.getBenefits() != null) {
            List<Benefit> benefits = policyDto.getBenefits().stream()
                    .map(benefitDto -> mapBenefitDtoToBenefit(benefitDto))
                    .collect(Collectors.toList());
            policy.setBenefits(benefits);

        }

        return policy;
    }

    private static Benefit mapBenefitDtoToBenefit(BenefitDto benefitDto) {
        Benefit benefit = new Benefit();
        benefit.setBenefitName(benefitDto.getBenefitName());
        benefit.setBenefitAmount(benefitDto.getBenefitAmount());
        return benefit;
    }

    private PolicyDto mapPolicyToPolicyDto(Policy policy) {
        PolicyDto policyDto = new PolicyDto();
        policyDto.setPolicyNumber(policy.getPolicyNumber());
        policyDto.setPolicyName(policy.getPolicyName());
        policyDto.setPolicyPlan(policy.getPolicyPlan());

        // Map benefits
        if (policy.getBenefits() != null) {
            policyDto.setBenefits(policy.getBenefits().stream()
                    .map(benefit -> mapBenefitToBenefitDto(benefit))
                    .collect(Collectors.toList()));
        }

        return policyDto;
    }

    private BenefitDto mapBenefitToBenefitDto(Benefit benefit) {
        BenefitDto benefitDto = new BenefitDto();
        benefitDto.setBenefitName(benefit.getBenefitName());
        benefitDto.setBenefitAmount(benefit.getBenefitAmount());
        return benefitDto;
    }
}

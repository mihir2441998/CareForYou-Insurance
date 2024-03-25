package com.careforyou.policyservice.policyapp.service;

import com.careforyou.policyservice.policyapp.Dto.ClaimCreationEvent;
import com.careforyou.policyservice.policyapp.Dto.PolicyDto;
import com.careforyou.policyservice.policyapp.Dto.ValidationResponse;
import com.careforyou.policyservice.policyapp.constants.FailureReason;
import com.careforyou.policyservice.policyapp.constants.ValidationConstant;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyClaimsValidationImpl implements PolicyClaimValidation{

    @Autowired
    private PolicyCreationService policyCreationService;

    @Autowired
    private KafkaPublisher publisher;

    @Override
    public void validateClaimRequest(ClaimCreationEvent claimCreationEvent) {
        PolicyDto policyDto = validatePolicy(claimCreationEvent);
        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.setClaimNumber(claimCreationEvent.getClaimNumber());
        if(policyDto!=null) {
            String validBenefitName = validateBenefit(claimCreationEvent,policyDto);
            if(validBenefitName==null){
                validationResponse.setPolicyDto(null);
                validationResponse.setValidationConstant(ValidationConstant.FAILURE);
                validationResponse.setFailureReason(FailureReason.BENEFIT_NOT_FOUND);
            }else{
                validationResponse.setPolicyDto(policyDto);
                validationResponse.setValidationConstant(ValidationConstant.SUCCESS);
                validationResponse.setFailureReason(null);
            }
        }else{
            validationResponse.setPolicyDto(null);
            validationResponse.setValidationConstant(ValidationConstant.FAILURE);
            validationResponse.setFailureReason(FailureReason.POLICY_NOT_FOUND);
        }
        publisher.publishClaimValidation(validationResponse);
    }

    private String validateBenefit(ClaimCreationEvent claimCreationEvent,PolicyDto policyDto) {
        String benefitToClaim = claimCreationEvent.getBenefitToClaim();
        return policyDto.getBenefits()
                .stream()
                .filter(benefit -> benefit.getBenefitName().equals(benefitToClaim))
                .findFirst()
                .map(benefitDto -> benefitDto.getBenefitName())
                .orElse(null);
    }

    private PolicyDto validatePolicy(ClaimCreationEvent claimCreationEvent) {
        String policyName= claimCreationEvent.getPolicyName();
        PolicyDto policyDto = policyCreationService.getPolicyByPolicyName(policyName);
        return policyDto;

    }
}

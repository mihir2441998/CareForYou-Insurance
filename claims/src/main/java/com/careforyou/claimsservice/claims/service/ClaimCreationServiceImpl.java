package com.careforyou.claimsservice.claims.service;

import com.careforyou.claimsservice.claims.dto.ClaimCreationRequest;
import com.careforyou.claimsservice.claims.dto.ClaimDto;
import com.careforyou.claimsservice.claims.dto.ClaimStatus;
import com.careforyou.claimsservice.claims.dto.PolicyInfoDto;
import com.careforyou.claimsservice.claims.dto.customer.CustomerDto;
import com.careforyou.claimsservice.claims.dto.customer.CustomerValidationResponse;
import com.careforyou.claimsservice.claims.dto.policy.BenefitDto;
import com.careforyou.claimsservice.claims.dto.policy.PolicyDto;
import com.careforyou.claimsservice.claims.dto.policy.PolicyValidationResponse;
import com.careforyou.claimsservice.claims.entities.*;
import com.careforyou.claimsservice.claims.repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ClaimCreationServiceImpl implements ClaimCreationService{

    @Autowired
    private KafkaPublisher kafkaPublisher;

    @Autowired(required = true)
    private ClaimRepository claimRepository;

    @Override
    public ClaimDto createClaim(ClaimCreationRequest request) {
        request.setClaimNumber(generateClaimNumber());
        kafkaPublisher.saveAndPublishClaimRequest(request);
        return null;
    }

    public static String generateClaimNumber() {
        String prefix = "claim-";
        return prefix + new Random().nextInt(Integer.MAX_VALUE);
    }

    @Override
    public void saveClaim(String claimNumber, PolicyValidationResponse policyValidationResponse, CustomerValidationResponse customerValidationResponse) {
        Claim claim = new Claim();
        if(policyValidationResponse.getValidationConstant().equals("FAILURE")){
            claim = buildFailureValidationObject(claim, claimNumber, policyValidationResponse.getFailureReason(), "POLICY");
        } else if (customerValidationResponse.getValidationConstant().equals("FAILURE")) {
            claim = buildFailureValidationObject(claim, claimNumber, customerValidationResponse.getFailureReason(), "CUSTOMER");
        }else{
            claim.setBenefitToClaim(null);
            claim.setClaimNumber(claimNumber);
            claim.setClaimStatus(ClaimStatus.OPEN.toString());
            claim.setPolicyInfo(mapPolicyInfo(policyValidationResponse));
            claim.setCustomerInfo(mapCustomerInfo(customerValidationResponse));
            claim.setRejectionReason(null);
        }
        claimRepository.save(claim);
    }

    private CustomerInfo mapCustomerInfo(CustomerValidationResponse response) {
        CustomerInfo customerInfo = new CustomerInfo();
        CustomerDto customerDto = response.getCustomerDto();
        customerInfo.setCustomerId(customerDto.getCustomerNumber());
        customerInfo.setFirstName(customerDto.getNameInfoDto().getFirstName());
        customerInfo.setMiddleName(customerDto.getNameInfoDto().getMiddleName());
        customerInfo.setLastName(customerDto.getNameInfoDto().getLastName());
        return customerInfo;
    }

    private PolicyInfo mapPolicyInfo(PolicyValidationResponse response) {
        PolicyInfo policyInfo = new PolicyInfo();
        PolicyDto policyInfoDto = response.getPolicyDto();
        policyInfo.setPolicyId(policyInfoDto.getPolicyNumber());
        policyInfo.setPolicyName(policyInfoDto.getPolicyName());
        policyInfo.setPolicyPlan(policyInfoDto.getPolicyPlan());
        policyInfo.setBenefitInfos(mapBenefits(policyInfoDto.getBenefits(),policyInfo));
        return policyInfo;
    }

    private List<BenefitInfo> mapBenefits(List<BenefitDto> benefits,PolicyInfo policyInfo) {
        List<BenefitInfo> benefitInfos = new ArrayList<>();
        benefits.stream().forEach(benefit ->{
            BenefitInfo benefitInfo = new BenefitInfo();
            benefitInfo.setBenefitName(benefit.getBenefitName());
            benefitInfo.setBenefitAmount(benefit.getBenefitAmount());
            benefitInfos.add(benefitInfo);
        });
        return benefitInfos;
    }

    private Claim buildFailureValidationObject(Claim claim, String claimNumber, String failureReason, String source) {
        claim.setBenefitToClaim(null);
        claim.setClaimNumber(claimNumber);
        claim.setClaimStatus(ClaimStatus.CLAIM_REJECTED.toString());
        claim.setPolicyInfo(null);
        claim.setCustomerInfo(null);
        RejectionReason rejectionReason = new RejectionReason();
        rejectionReason.setRejectionReason(failureReason);
        rejectionReason.setRejectionSource(source);
        rejectionReason.setClaim(claim);
        claim.setRejectionReason(rejectionReason);
        return claim;
    }
}

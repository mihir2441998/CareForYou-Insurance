package com.careforyou.policyservice.policyapp.service;

import com.careforyou.policyservice.policyapp.Dto.PolicyDto;
import com.careforyou.policyservice.policyapp.entities.Policy;

import java.util.List;

public interface PolicyCreationService {

    PolicyDto createPolicy(PolicyDto policyDto);

    PolicyDto getPolicyByPolicyName(String policyNumber);

    List<PolicyDto> getAllPolicies();
}

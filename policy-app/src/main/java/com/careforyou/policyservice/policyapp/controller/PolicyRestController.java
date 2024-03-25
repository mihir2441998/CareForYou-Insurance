package com.careforyou.policyservice.policyapp.controller;

import com.careforyou.policyservice.policyapp.Dto.PolicyDto;
import com.careforyou.policyservice.policyapp.service.PolicyCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policy")
public class PolicyRestController {

    @Autowired
    private PolicyCreationService policyCreationService;

    @GetMapping("/{policyName}/load")
    private ResponseEntity<PolicyDto> getPolicyByNumber(@PathVariable String policyName){
        PolicyDto policyDto = policyCreationService.getPolicyByPolicyName(policyName);
        return new ResponseEntity<PolicyDto>(policyDto,HttpStatus.FOUND);
    }

    @GetMapping("/load/all")
    private ResponseEntity<List<PolicyDto>> getPolicies(){
        List<PolicyDto> policyDtos = policyCreationService.getAllPolicies();
        return new ResponseEntity<List<PolicyDto>>(policyDtos,HttpStatus.FOUND);
    }

    @PostMapping("/write")
    private ResponseEntity<PolicyDto> getPolicyByNumber(@RequestBody PolicyDto requesPolicyDto){
        PolicyDto policyDto = policyCreationService.createPolicy(requesPolicyDto);
        return new ResponseEntity<PolicyDto>(policyDto,HttpStatus.CREATED);
    }
}

package com.careforyou.claimsservice.claims.controller;

import com.careforyou.claimsservice.claims.dto.ClaimCreationRequest;
import com.careforyou.claimsservice.claims.dto.ClaimDto;
import com.careforyou.claimsservice.claims.entities.Claim;
import com.careforyou.claimsservice.claims.service.ClaimCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/claim")
public class ClaimsRestController {

    @Autowired
    private ClaimCreationService claimCreationService;

    @PostMapping("/create")
    ResponseEntity<ClaimDto> createClaim(@RequestBody ClaimCreationRequest request){
        ClaimDto createdClaim = claimCreationService.createClaim(request);
        return new ResponseEntity<ClaimDto>(createdClaim, HttpStatus.CREATED);
    }
}

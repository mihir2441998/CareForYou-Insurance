package com.careforyou.claimsservice.claims.dto;

import lombok.Data;

@Data
public class RejectionReasonDto {

    private int id;
    private String rejectionReason;
    private String rejectionSource;
}

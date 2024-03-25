package com.careforyou.customerservice.customerapp.Dtos;

import lombok.Data;

@Data
public class EligibilityDto {
    private boolean isSmoker;
    private boolean isTrafficViolationExits;
    private boolean isCriminalRecordExits;
}

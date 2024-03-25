package com.careforyou.claimsservice.claims.entities;

import com.careforyou.claimsservice.claims.dto.ClaimStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.kafka.common.protocol.types.Field;

@Entity
@Table(name="claim")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String claimNumber;

    private String claimStatus;

    private String benefitToClaim;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rejectionReason_id", referencedColumnName = "id")
    private RejectionReason rejectionReason;

    //policy
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "policyInfo_id", referencedColumnName = "id")
    private PolicyInfo policyInfo;

    //customer
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerInfo_id", referencedColumnName = "id")
    private CustomerInfo customerInfo;


    //payment - coming soon

}

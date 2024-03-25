package com.careforyou.claimsservice.claims.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="ClaimsPolicyInfo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PolicyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String policyId;

    private String policyName;

    private String policyPlan;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "policy_id", referencedColumnName = "id")
    private List<BenefitInfo> benefitInfos;

    @OneToOne(mappedBy = "policyInfo")
    private Claim claim;

}

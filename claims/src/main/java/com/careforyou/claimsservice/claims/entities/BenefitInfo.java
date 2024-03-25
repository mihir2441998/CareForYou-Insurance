package com.careforyou.claimsservice.claims.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="BenefitInfo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BenefitInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String benefitName;

    private Double benefitAmount;

}

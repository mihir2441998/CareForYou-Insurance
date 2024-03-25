package com.careforyou.claimsservice.claims.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ClaimsCustomerInfo")
@Getter
@Setter
public class CustomerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String customerId;
    private String firstName;
    private String middleName;
    private String lastName;
}

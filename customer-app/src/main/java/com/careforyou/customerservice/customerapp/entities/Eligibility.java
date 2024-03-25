package com.careforyou.customerservice.customerapp.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Eligibility")
public class Eligibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean isSmoker;
    private boolean isTrafficViolationExits;
    private boolean isCriminalRecordExits;

    @OneToOne(mappedBy = "eligibility")
    private Customer customer;
}

package com.careforyou.customerservice.customerapp.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private int id;

    private String customerNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nameInfoId", referencedColumnName = "id")
    private NameInfo nameInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressInfoId", referencedColumnName = "id")
    private AddressInfo addressInfo;

    private Date bithDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eligibility_id", referencedColumnName = "id")
    private Eligibility eligibility;


}

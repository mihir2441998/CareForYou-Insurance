package com.careforyou.claimsservice.claims.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ClaimRejection")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RejectionReason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String rejectionReason;

    private String rejectionSource;

    @OneToOne(mappedBy = "rejectionReason")
    private Claim claim;
}

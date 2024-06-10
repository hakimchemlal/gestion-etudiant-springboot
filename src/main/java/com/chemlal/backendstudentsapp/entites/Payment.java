package com.chemlal.backendstudentsapp.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id", nullable = false)
    private Long id;
    @Column(name="date")
    private LocalDate date;
    @Column(name="amount")
    private double amount;
    @Column(name="type")
    private PaymentType type;
    @Column(name="status")
    private PaymentStatus status = PaymentStatus.CREATED;
    @Column(name="file")
    private String file;
    //@ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    private Student student;

}

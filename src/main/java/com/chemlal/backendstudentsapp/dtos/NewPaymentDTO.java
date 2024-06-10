package com.chemlal.backendstudentsapp.dtos;

import com.chemlal.backendstudentsapp.entites.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class NewPaymentDTO {
    private String studentCode;
    private double amount;
    private PaymentType type;
    private LocalDate date;
}

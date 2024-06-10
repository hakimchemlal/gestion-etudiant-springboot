package com.chemlal.backendstudentsapp.service;

import com.chemlal.backendstudentsapp.dtos.NewPaymentDTO;
import com.chemlal.backendstudentsapp.entites.Payment;
import com.chemlal.backendstudentsapp.entites.PaymentType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

public interface PaymentService {
     Payment savePayment(MultipartFile file, NewPaymentDTO newPaymentDTO) throws IOException;
}

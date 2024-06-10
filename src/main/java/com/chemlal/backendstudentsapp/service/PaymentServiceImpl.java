package com.chemlal.backendstudentsapp.service;

import com.chemlal.backendstudentsapp.dtos.NewPaymentDTO;
import com.chemlal.backendstudentsapp.entites.Payment;
import com.chemlal.backendstudentsapp.entites.PaymentStatus;
import com.chemlal.backendstudentsapp.entites.PaymentType;
import com.chemlal.backendstudentsapp.entites.Student;
import com.chemlal.backendstudentsapp.repository.PaymentRepository;
import com.chemlal.backendstudentsapp.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;
    @Override
    public Payment savePayment(MultipartFile file, NewPaymentDTO newPaymentDTO) throws IOException {
            Path path = Paths.get(System.getProperty("user.home"), "students-app-files", "payments");
            System.out.println("Path to be created: " + path);

            if (!Files.exists(path)) {
                System.out.println("Directory does not exist, creating...");
                try {
                    Files.createDirectories(path);
                    System.out.println("Directory created successfully!");
                } catch (IOException e) {
                    System.err.println("Error creating directory: " + e.getMessage());
                }
            } else {
                System.out.println("Directory already exists.");
            }

            String fileId = UUID.randomUUID().toString();
            Path filePath = Paths.get(System.getProperty("user.home"),"students-app-files", "payments", fileId+".pdf");
            Files.copy(file.getInputStream(), filePath);

            // Recherche de l'étudiant
            Student student = this.studentRepository.findByCode(newPaymentDTO.getStudentCode()).orElse(null);
            if(student == null){
                return null;
            }
            Payment payment = Payment.builder()
                    .type(newPaymentDTO.getType())
                    .amount(newPaymentDTO.getAmount())
                    .student(student)
                    .status(PaymentStatus.CREATED)
                    .date(newPaymentDTO.getDate())
                    .file(filePath.toUri().toString())
                    .build();

        return paymentRepository.save(payment);
    }
}

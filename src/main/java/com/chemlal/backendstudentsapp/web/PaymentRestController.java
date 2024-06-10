package com.chemlal.backendstudentsapp.web;

import com.chemlal.backendstudentsapp.dtos.NewPaymentDTO;
import com.chemlal.backendstudentsapp.entites.Payment;
import com.chemlal.backendstudentsapp.entites.PaymentStatus;
import com.chemlal.backendstudentsapp.entites.PaymentType;
import com.chemlal.backendstudentsapp.entites.Student;
import com.chemlal.backendstudentsapp.repository.PaymentRepository;
import com.chemlal.backendstudentsapp.repository.StudentRepository;
import com.chemlal.backendstudentsapp.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("payment")
public class PaymentRestController {
    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;
    private final PaymentService paymentService;

    @GetMapping("/all")
    public List<Payment> consulterPayments(){
        return this.paymentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Payment findPaymentById(@PathVariable Long id) throws Exception {
        return this.paymentRepository.findById(id).orElseThrow(() -> new Exception("Payment not found - " + id));
    }

    @GetMapping("/paymentsByStudent/{code}")
    public List<Payment> findPaymentByStudents(@PathVariable String code){
        return this.paymentRepository.findPaymentByStudentCode(code);
    }

    @PostMapping(value = "/savePayment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> savePayment(@RequestParam("file") MultipartFile file,
                                         NewPaymentDTO newPaymentDTO) {
        try {
            Payment savedPayment = paymentService.savePayment(file, newPaymentDTO);
            if (savedPayment == null) {
                System.out.println("savedPayment == null");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found - " + newPaymentDTO.getStudentCode());
            }
            return ResponseEntity.ok(savedPayment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing payment: " + e.getMessage());
        }
    }

    @GetMapping(value = "/paymentFile/{paymentId}", produces = {MediaType.APPLICATION_PDF_VALUE})
    public byte[] getPaymentFile(@PathVariable Long paymentId) throws Exception {
        Payment payment = this.paymentRepository.findById(paymentId).orElseThrow(() -> new Exception("Payment not found - " + paymentId));
        String filePath = payment.getFile();
        return Files.readAllBytes(Path.of(URI.create(filePath)));
    }

    @PutMapping("/updateStatus/{paymentId}")
    public Payment updateStatus(@RequestParam PaymentStatus paymentStatus, @PathVariable Long paymentId) throws Exception {
        this.paymentRepository.updatePaymentStatusById(paymentId, paymentStatus);
        return this.paymentRepository.findById(paymentId).orElseThrow(() -> new Exception("Payment not found - " + paymentId));
    }


}

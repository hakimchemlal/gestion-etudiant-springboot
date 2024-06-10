package com.chemlal.backendstudentsapp.web;

import com.chemlal.backendstudentsapp.entites.Payment;
import com.chemlal.backendstudentsapp.entites.Student;
import com.chemlal.backendstudentsapp.repository.PaymentRepository;
import com.chemlal.backendstudentsapp.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("students")
public class StudentRestController {
    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;


    @GetMapping("/all")
    public List<Student> findStudents() {
        return this.studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable String id) throws Exception {
        return this.studentRepository.findById(id).orElseThrow(() -> new Exception("Student not found - " + id));
    }

    @GetMapping("/byCode/{code}")
    public Student findStudentByCode(@PathVariable String code) throws Exception {
        return this.studentRepository.findByCode(code).orElseThrow(() -> new Exception("Student not found - " + code));
    }

}

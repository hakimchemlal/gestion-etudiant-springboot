package com.chemlal.backendstudentsapp;

import com.chemlal.backendstudentsapp.entites.Payment;
import com.chemlal.backendstudentsapp.entites.PaymentStatus;
import com.chemlal.backendstudentsapp.entites.PaymentType;
import com.chemlal.backendstudentsapp.entites.Student;
import com.chemlal.backendstudentsapp.repository.PaymentRepository;
import com.chemlal.backendstudentsapp.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class BackendStudentsPaymentsUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendStudentsPaymentsUploadApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository,
                                        PaymentRepository paymentRepository) {
        return args -> {
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("abdelhakim").code("123").programId("genie logiciel").lastName("chemlal")
					.email("hakimchemlal0@gmail.com")
					.build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("amine").code("456").programId("licence informatique").lastName("chemlal")
					.email("aminchemlal@gmail.com")
					.build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("salim").code("789").programId("SAP").lastName("chemlal")
					.email("salimchemlal@gmail.com")
					.build());
			PaymentType[] paymentTypes = PaymentType.values();
			Random random = new Random();
			studentRepository.findAll().forEach(student -> {
				for (int i = 0; i < 10; i++){
					int index = random.nextInt(paymentTypes.length);
					Payment payment = Payment.builder()
							.amount(1000+(int)(Math.random()*1000))
							.date(LocalDate.now())
							.type(paymentTypes[index])
							//.file(UUID.randomUUID().toString())
							.status(PaymentStatus.CREATED)
							.student(student)
							.build();
					paymentRepository.save(payment);
				}
			});
        };
    }
}
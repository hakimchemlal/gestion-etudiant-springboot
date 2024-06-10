package com.chemlal.backendstudentsapp.repository;

import com.chemlal.backendstudentsapp.entites.Payment;
import com.chemlal.backendstudentsapp.entites.PaymentStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findPaymentByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);

    @Modifying
    @Transactional
    @Query("UPDATE Payment p SET p.status = :newStatus WHERE p.id = :paymentId")
    void updatePaymentStatusById(@Param("paymentId") Long paymentId, @Param("newStatus") PaymentStatus newStatus);

}

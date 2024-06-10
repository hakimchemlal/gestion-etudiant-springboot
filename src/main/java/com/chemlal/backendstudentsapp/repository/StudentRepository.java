package com.chemlal.backendstudentsapp.repository;

import com.chemlal.backendstudentsapp.entites.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByCode(String code);
}

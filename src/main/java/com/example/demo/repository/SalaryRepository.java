package com.example.demo.repository;

import com.example.demo.model.Salary;
import com.example.demo.model.SalaryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary, SalaryId> {
    List<Salary> findByEmployee_EmpId(Integer empId);
    Optional<Salary> findById(SalaryId id);
}

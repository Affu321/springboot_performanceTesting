package com.example.demo.repository;

import com.example.demo.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TitleRepository extends JpaRepository<Title, Integer> {
    List<Title> findByEmployee_EmpId(Integer empId);
    Optional<Title> findByEmpIdAndFromDate(Integer empId, LocalDate fromDate);
}
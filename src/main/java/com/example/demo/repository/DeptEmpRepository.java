package com.example.demo.repository;

import com.example.demo.model.DeptEmp;
import com.example.demo.model.DeptEmpId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeptEmpRepository extends JpaRepository<DeptEmp, DeptEmpId> {
    List<DeptEmp> findByEmployee_EmpId(Integer empId);
}

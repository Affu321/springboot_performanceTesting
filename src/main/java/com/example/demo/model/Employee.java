package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Integer empId;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "first_name", length = 14, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 16, nullable = false)
    private String lastName;

    @Column(name = "gender", length = 1, nullable = false)
    private String gender;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @OneToMany(mappedBy = "employee")
    private List<DeptEmp> deptEmp;

    @OneToMany(mappedBy = "employee")
    private List<DeptManager> deptManager;

    @OneToMany(mappedBy = "employee")
    private List<Salary> salaries;

    @OneToMany(mappedBy = "employee")
    private List<Title> titles;

    // Getter & Setter
}

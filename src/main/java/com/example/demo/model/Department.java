package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Integer deptId;

    @Column(name = "dept_name", length = 40, nullable = false)
    private String deptName;

    @OneToMany(mappedBy = "department")
    private List<DeptEmp> deptEmp;

    @OneToMany(mappedBy = "department")
    private List<DeptManager> deptManager;

    // Getter & Setter
}

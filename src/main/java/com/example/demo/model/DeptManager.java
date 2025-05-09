package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "dept_manager")
@IdClass(DeptManagerId.class)
public class DeptManager {

    @Id
    @Column(name = "emp_id")
    private Integer empId;

    @Id
    @Column(name = "dept_id")
    private Integer deptId;

    @ManyToOne
    @JoinColumn(name = "emp_id", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "dept_id", insertable = false, updatable = false)
    private Department department;

    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;

    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;

    // Getter & Setter
}

package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "salaries")
public class Salary {

    @EmbeddedId
    private SalaryId id;

    @MapsId("empId")
    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @Column(name = "salary", nullable = false)
    private Integer salary;

    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;

    // Tambahkan getter manual agar GraphQL tahu bagaimana mengambilnya

    public Integer getEmpId() {
        return id != null ? id.getEmpId() : null;
    }

    public LocalDate getFromDate() {
        return id != null ? id.getFromDate() : null;
    }
}

package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "titles")
@IdClass(TitleId.class)
public class Title {

    @Id
    @Column(name = "emp_id")
    private Integer empId;

    @Id
    @Column(name = "title", length = 50)
    private String title;

    @Id
    @Column(name = "from_date")
    private LocalDate fromDate;

    @ManyToOne
    @JoinColumn(name = "emp_id", insertable = false, updatable = false)
    private Employee employee;

    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;

    // Getter & Setter
}

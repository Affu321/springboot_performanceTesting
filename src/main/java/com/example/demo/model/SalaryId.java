package com.example.demo.model;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class SalaryId implements Serializable {

    private Integer empId;
    private LocalDate fromDate;

    public SalaryId() {}

    public SalaryId(Integer empId, LocalDate fromDate) {
        this.empId = empId;
        this.fromDate = fromDate;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalaryId)) return false;
        SalaryId that = (SalaryId) o;
        return Objects.equals(empId, that.empId) && Objects.equals(fromDate, that.fromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, fromDate);
    }
}

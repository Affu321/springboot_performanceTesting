package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.Salary;
import com.example.demo.model.SalaryId;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.SalaryRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class SalaryGraphQLController {

    private final SalaryRepository salaryRepository;
    private final EmployeeRepository employeeRepository;

    public SalaryGraphQLController(SalaryRepository salaryRepository, EmployeeRepository employeeRepository) {
        this.salaryRepository = salaryRepository;
        this.employeeRepository = employeeRepository;
    }

    // ✅ Get all salaries for an employee
    @QueryMapping
    public List<Salary> salaries(@Argument Integer empId) {
        return salaryRepository.findByEmployee_EmpId(empId);
    }

    // ✅ Create salary record
    @MutationMapping
    public Salary createSalary(
            @Argument Integer empId,
            @Argument Integer salary,
            @Argument LocalDate fromDate,
            @Argument LocalDate toDate
    ) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + empId));

        SalaryId salaryId = new SalaryId(empId, fromDate);

        Salary newSalary = new Salary();
        newSalary.setId(salaryId);
        newSalary.setEmployee(employee);
        newSalary.setSalary(salary);
        newSalary.setToDate(toDate);

        return salaryRepository.save(newSalary);
    }

    // ✅ Update salary value
    @MutationMapping
    public Salary updateSalary(
            @Argument Integer empId,
            @Argument LocalDate fromDate,
            @Argument Integer salary
    ) {
        SalaryId salaryId = new SalaryId(empId, fromDate);
        Salary existingSalary = salaryRepository.findById(salaryId)
                .orElseThrow(() -> new RuntimeException("Salary record not found"));

        existingSalary.setSalary(salary);
        return salaryRepository.save(existingSalary);
    }

    // ✅ Delete salary record
    @MutationMapping
    public Salary deleteSalary(
            @Argument Integer empId,
            @Argument LocalDate fromDate
    ) {
        SalaryId salaryId = new SalaryId(empId, fromDate);
        Salary salary = salaryRepository.findById(salaryId)
                .orElseThrow(() -> new RuntimeException("Salary record not found"));

        salaryRepository.delete(salary);
        return salary;
    }
}

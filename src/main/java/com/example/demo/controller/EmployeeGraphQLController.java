package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EmployeeGraphQLController {

    private final EmployeeRepository employeeRepository;

    public EmployeeGraphQLController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Get all employees
    @QueryMapping
    public List<Employee> employees() {
        return employeeRepository.findAll();
    }

    // Get one employee by empId
    @QueryMapping
    public Employee employee(@Argument Integer empId) {
        return employeeRepository.findById(empId).orElse(null);
    }

    // Create employee
    @MutationMapping
    public Employee createEmployee(
            @Argument String firstName,
            @Argument String lastName,
            @Argument String gender,
            @Argument String birthDate,
            @Argument String hireDate
    ) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setGender(gender);
        employee.setBirthDate(LocalDate.parse(birthDate));
        employee.setHireDate(LocalDate.parse(hireDate));
        return employeeRepository.save(employee);
    }

    // Update employee
    @MutationMapping
    public Employee updateEmployee(
            @Argument Integer empId,
            @Argument String birthDate,
            @Argument String firstName,
            @Argument String lastName,
            @Argument String gender,
            @Argument String hireDate
    ) {
        return employeeRepository.findById(empId).map(employee -> {
            if (birthDate != null) employee.setBirthDate(LocalDate.parse(birthDate));
            if (firstName != null) employee.setFirstName(firstName);
            if (lastName != null) employee.setLastName(lastName);
            if (gender != null) employee.setGender(gender);
            if (hireDate != null) employee.setHireDate(LocalDate.parse(hireDate));
            return employeeRepository.save(employee);
        }).orElse(null);
    }

    // Delete employee
    @MutationMapping
    public Employee deleteEmployee(@Argument Integer empId) {
        return employeeRepository.findById(empId).map(employee -> {
            employeeRepository.delete(employee);
            return employee;
        }).orElse(null);
    }
}

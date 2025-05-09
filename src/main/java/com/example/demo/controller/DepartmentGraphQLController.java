package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class DepartmentGraphQLController {

    private final DepartmentRepository departmentRepository;
    private final DeptEmpRepository deptEmpRepository;
    private final DeptManagerRepository deptManagerRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentGraphQLController(
            DepartmentRepository departmentRepository,
            DeptEmpRepository deptEmpRepository,
            DeptManagerRepository deptManagerRepository,
            EmployeeRepository employeeRepository
    ) {
        this.departmentRepository = departmentRepository;
        this.deptEmpRepository = deptEmpRepository;
        this.deptManagerRepository = deptManagerRepository;
        this.employeeRepository = employeeRepository;
    }

    // ✅ Get all departments
    @QueryMapping
    public List<Department> departments() {
        return departmentRepository.findAll();
    }

    // ✅ Get department by ID
    @QueryMapping
    public Department department(@Argument Integer deptId) {
        return departmentRepository.findById(deptId).orElse(null);
    }

    // ✅ Create department
    @MutationMapping
    public Department createDepartment(@Argument String deptName) {
        Department department = new Department();
        department.setDeptName(deptName);
        return departmentRepository.save(department);
    }

    // ✅ Update department
    @MutationMapping
    public Department updateDepartment(@Argument Integer deptId, @Argument String deptName) {
        return departmentRepository.findById(deptId).map(dept -> {
            dept.setDeptName(deptName);
            return departmentRepository.save(dept);
        }).orElse(null);
    }

    // ✅ Delete department
    @MutationMapping
    public Department deleteDepartment(@Argument Integer deptId) {
        return departmentRepository.findById(deptId).map(dept -> {
            departmentRepository.delete(dept);
            return dept;
        }).orElse(null);
    }

    // ✅ Assign employee to department
    @MutationMapping
    public DeptEmp assignEmployeeToDepartment(
            @Argument Integer empId,
            @Argument Integer deptId,
            @Argument LocalDate fromDate,
            @Argument LocalDate toDate
    ) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(() ->
                new RuntimeException("Employee not found with id: " + empId));
        Department department = departmentRepository.findById(deptId).orElseThrow(() ->
                new RuntimeException("Department not found with id: " + deptId));

        DeptEmp deptEmp = new DeptEmp();
        deptEmp.setEmpId(empId);
        deptEmp.setDeptId(deptId);
        deptEmp.setFromDate(fromDate);
        deptEmp.setToDate(toDate);
        deptEmp.setEmployee(employee);
        deptEmp.setDepartment(department);
        return deptEmpRepository.save(deptEmp);
    }

    // ✅ Assign manager to department
    @MutationMapping
    public DeptManager assignManagerToDepartment(
            @Argument Integer empId,
            @Argument Integer deptId,
            @Argument LocalDate fromDate,
            @Argument LocalDate toDate
    ) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(() ->
                new RuntimeException("Employee not found with id: " + empId));
        Department department = departmentRepository.findById(deptId).orElseThrow(() ->
                new RuntimeException("Department not found with id: " + deptId));

        DeptManager deptManager = new DeptManager();
        deptManager.setEmpId(empId);
        deptManager.setDeptId(deptId);
        deptManager.setFromDate(fromDate);
        deptManager.setToDate(toDate);
        deptManager.setEmployee(employee);
        deptManager.setDepartment(department);
        return deptManagerRepository.save(deptManager);
    }
}

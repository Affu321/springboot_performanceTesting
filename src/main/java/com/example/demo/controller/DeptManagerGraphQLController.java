//package com.example.demo.controller;
//
//import com.example.demo.model.DeptManager;
//import com.example.demo.model.Employee;
//import com.example.demo.model.Department;
//import com.example.demo.repository.DeptManagerRepository;
//import com.example.demo.repository.EmployeeRepository;
//import com.example.demo.repository.DepartmentRepository;
//import org.springframework.graphql.data.method.annotation.Argument;
//import org.springframework.graphql.data.method.annotation.MutationMapping;
//import org.springframework.graphql.data.method.annotation.QueryMapping;
//import org.springframework.stereotype.Controller;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Controller
//public class DeptManagerGraphQLController {
//
//    private final DeptManagerRepository deptManagerRepository;
//    private final EmployeeRepository employeeRepository;
//    private final DepartmentRepository departmentRepository;
//
//    public DeptManagerGraphQLController(
//            DeptManagerRepository deptManagerRepository,
//            EmployeeRepository employeeRepository,
//            DepartmentRepository departmentRepository
//    ) {
//        this.deptManagerRepository = deptManagerRepository;
//        this.employeeRepository = employeeRepository;
//        this.departmentRepository = departmentRepository;
//    }
//
//    @QueryMapping
//    public List<DeptManager> deptManagers(@Argument Integer deptId) {
//        return deptManagerRepository.findByDepartment_DeptId(deptId);
//    }
//
//    @MutationMapping
//    public DeptManager assignManagerToDepartment(
//            @Argument Integer empId,
//            @Argument Integer deptId,
//            @Argument String fromDate,
//            @Argument String toDate
//    ) {
//        Employee employee = employeeRepository.findById(empId).orElseThrow(() ->
//                new RuntimeException("Employee not found with id: " + empId));
//        Department department = departmentRepository.findById(deptId).orElseThrow(() ->
//                new RuntimeException("Department not found with id: " + deptId));
//
//        DeptManager deptManager = new DeptManager();
//        deptManager.setEmpId(empId);
//        deptManager.setDeptId(deptId);
//        deptManager.setFromDate(LocalDate.parse(fromDate));
//        deptManager.setToDate(LocalDate.parse(toDate));
//        deptManager.setEmployee(employee);
//        deptManager.setDepartment(department);
//
//        return deptManagerRepository.save(deptManager);
//    }
//}

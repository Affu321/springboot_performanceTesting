//package com.example.demo.controller;
//
//import com.example.demo.model.DeptEmp;
//import com.example.demo.model.Employee;
//import com.example.demo.model.Department;
//import com.example.demo.repository.DeptEmpRepository;
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
//public class DeptEmpGraphQLController {
//
//    private final DeptEmpRepository deptEmpRepository;
//    private final EmployeeRepository employeeRepository;
//    private final DepartmentRepository departmentRepository;
//
//    public DeptEmpGraphQLController(DeptEmpRepository deptEmpRepository,
//                                    EmployeeRepository employeeRepository,
//                                    DepartmentRepository departmentRepository) {
//        this.deptEmpRepository = deptEmpRepository;
//        this.employeeRepository = employeeRepository;
//        this.departmentRepository = departmentRepository;
//    }
//
//    @QueryMapping
//    public List<DeptEmp> deptEmpByEmpId(@Argument Integer empId) {
//        return deptEmpRepository.findByEmployee_EmpId(empId);
//    }
//
////    @MutationMapping(name = "assignDeptEmpToDepartment")
////    public DeptEmp assignEmployeeToDepartment
////(
////            @Argument Integer empId,
////            @Argument Integer deptId,
////            @Argument String fromDate,
////            @Argument String toDate
////    ) {
////        Employee employee = employeeRepository.findById(empId).orElseThrow(() ->
////                new RuntimeException("Employee not found with id: " + empId));
////        Department department = departmentRepository.findById(deptId).orElseThrow(() ->
////                new RuntimeException("Department not found with id: " + deptId));
////
////        DeptEmp deptEmp = new DeptEmp();
////        deptEmp.setEmpId(empId);
////        deptEmp.setDeptId(deptId);
////        deptEmp.setFromDate(LocalDate.parse(fromDate));
////        deptEmp.setToDate(LocalDate.parse(toDate));
////        deptEmp.setEmployee(employee);
////        deptEmp.setDepartment(department);
////
////        return deptEmpRepository.save(deptEmp);
////    }
//}

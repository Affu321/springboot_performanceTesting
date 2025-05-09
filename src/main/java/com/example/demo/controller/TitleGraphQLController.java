package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.Title;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.TitleRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TitleGraphQLController {

    private final TitleRepository titleRepository;
    private final EmployeeRepository employeeRepository;

    public TitleGraphQLController(TitleRepository titleRepository, EmployeeRepository employeeRepository) {
        this.titleRepository = titleRepository;
        this.employeeRepository = employeeRepository;
    }

    @QueryMapping
    public List<Title> titles(@Argument Integer empId) {
        return titleRepository.findByEmployee_EmpId(empId);
    }

    @MutationMapping
    public Title createTitle(
            @Argument Integer empId,
            @Argument String title,
            @Argument LocalDate fromDate,
            @Argument LocalDate toDate
    ) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Title newTitle = new Title();
        newTitle.setEmpId(empId);
        newTitle.setTitle(title);
        newTitle.setFromDate(fromDate);
        newTitle.setToDate(toDate);
        newTitle.setEmployee(employee);

        return titleRepository.save(newTitle);
    }

    @MutationMapping
    public Title updateTitle(
            @Argument Integer empId,
            @Argument String fromDate,
            @Argument String title
    ) {
        Title existing = titleRepository.findByEmpIdAndFromDate(empId, LocalDate.parse(fromDate))
                .orElseThrow(() -> new RuntimeException("Title record not found"));

        existing.setTitle(title);
        return titleRepository.save(existing);
    }
}

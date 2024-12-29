package com.example.employeeappraisal.controller;

import com.example.employeeappraisal.entity.Employee;
import com.example.employeeappraisal.repository.EmployeeRepository;
import com.example.employeeappraisal.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService employeeCategoryService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/update-categories")
    public String updateCategories() {
        // Fetch all employees
        List<Employee> employees = employeeRepository.findAll();

        // Categorize employees
        Map<String, List<Employee>> categorizedEmployees = employeeCategoryService
                .categorizeEmployeesByRatings(employees);

        // Update actual percentages in the Category table
        employeeCategoryService.updateActualPercentagesAndImprovements(categorizedEmployees);

        return "Categories updated successfully!";
    }
}

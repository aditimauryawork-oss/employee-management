package com.addie.employee_management.controller;

import com.addie.employee_management.entity.Employee;
import com.addie.employee_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,
                                   @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/department/{department}")
    public List<Employee> getEmployeesByDepartment(@PathVariable String department) {
        return employeeService.getEmployeesByDepartment(department);
    }

    @GetMapping("/name/{name}")
    public List<Employee> getEmployeesByName(@PathVariable String name) {
        return employeeService.getEmployeesByName(name);
    }

    @GetMapping("/salary")
    public List<Employee> getEmployeesBySalaryRange(
            @RequestParam Double min,
            @RequestParam Double max) {

        return employeeService.getEmployeesBySalaryRange(min, max);
    }

    @GetMapping("/count")
    public Long getEmployeeCount() {
        return employeeService.getEmployeeCount();
    }
}
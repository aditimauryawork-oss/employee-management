package com.addie.employee_management.service;

import com.addie.employee_management.entity.Employee;
import com.addie.employee_management.exception.EmployeeNotFoundException;
import com.addie.employee_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee not found with id: " + id));
    }

    public Employee updateEmployee(Long id, Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee not found with id: " + id));

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setDesignation(employee.getDesignation());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setJoiningDate(employee.getJoiningDate());

        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {

        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }

        employeeRepository.deleteById(id);
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    public List<Employee> getEmployeesByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Employee> getEmployeesBySalaryRange(Double min, Double max) {
        return employeeRepository.findBySalaryBetween(min, max);
    }

    public Long getEmployeeCount() {
        return employeeRepository.count();
    }
}
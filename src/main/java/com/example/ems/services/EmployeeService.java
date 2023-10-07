package com.example.ems.services;

import com.example.ems.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(Employee employee);
    List<Employee> getEmployees();
    Employee updateEmployee(Employee employee, Long id);
    Employee getEmployeeById(Long id);
    void deleteEmployee(Long id);
}

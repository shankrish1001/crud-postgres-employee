package com.shankrish.crudpostgresemployee.service;

import com.shankrish.crudpostgresemployee.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    List<Employee> getAllByEmail(String email);
    Employee getEmployeeById(Long id);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employeeInfo);
    String deleteEmployee(Long id);
}

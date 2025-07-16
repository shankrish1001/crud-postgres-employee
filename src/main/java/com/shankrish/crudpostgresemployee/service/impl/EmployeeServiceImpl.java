package com.shankrish.crudpostgresemployee.service.impl;

import com.shankrish.crudpostgresemployee.model.Employee;
import com.shankrish.crudpostgresemployee.repository.EmployeeRepository;
import com.shankrish.crudpostgresemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeInfo) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setEmployeeName(employeeInfo.getEmployeeName());
        employee.setEmployeeEmail(employeeInfo.getEmployeeEmail());
        employee.setEmployeeDept(employeeInfo.getEmployeeDept());
        employee.setEmployeeAge(employeeInfo.getEmployeeAge());
        return employeeRepository.save(employee);
    }

    public String deleteEmployee(Long id) {
        if(employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return "Record deleted";
        } else {
            return "Record not found";
        }

    }

}

package com.filter.filter.service;

import com.filter.filter.dto.EmployeeDto;
import com.filter.filter.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee save(Employee employee);

    List<Employee> getAll();

    List<Employee> getFilterData(EmployeeDto employeeDto);
}

package com.filter.filter.service;

import com.filter.filter.dto.EmployeeDto;
import com.filter.filter.model.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);

    List<Employee> getAll();

    List<Employee> getFilterData(EmployeeDto employeeDto);

    String updateUser(Long employeeId, Employee employee);

    String deleteEmployee(Long employeeId);

    ResponseEntity<Employee> getEmployeeById(Long employeeId);

    void deleteMatching(String name);

    void downloadData(EmployeeDto employeeDto);
}

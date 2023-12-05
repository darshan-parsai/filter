package com.filter.filter.service.impl;

import com.filter.filter.dto.EmployeeDto;
import com.filter.filter.model.Employee;
import com.filter.filter.repository.EmployeeRepo;
import com.filter.filter.service.EmployeeService;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    public final EmployeeRepo employeeRepo;
    public EmployeeServiceImpl(EmployeeRepo employeeRepo){
        this.employeeRepo = employeeRepo;
    }
    @Override
    public Employee save(Employee employee) {

        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepo.findAll();
    }

    @Override
    public List<Employee> getFilterData(EmployeeDto employeeDto) {
        List<Employee> employees = employeeRepo.findAll();
        if(!employeeDto.getName().isEmpty()){
//             employees = employees.stream().filter(employee -> employeeDto.getName()
//                     .contains(employee.getName())).toList();

            employees = employees.stream()
                    .filter(employee -> employeeDto.getName().stream()
                            .anyMatch(dtoName -> dtoName != null && dtoName.equalsIgnoreCase(employee.getName())))
                    .toList();

        }
        if(!employeeDto.getCompanyName().isEmpty()){
            employees = employees.stream().filter(employee -> employeeDto.getCompanyName().stream()
                    .anyMatch(dtoCompanyName -> dtoCompanyName != null && dtoCompanyName.equalsIgnoreCase(employee.getCompanyName()))).toList();
        }

        if (!employeeDto.getPosition().isEmpty()){
            employees = employees.stream().filter(employee -> employeeDto.getPosition().stream()
                    .anyMatch(dtoPosition -> dtoPosition != null && dtoPosition.equalsIgnoreCase(employee.getPosition()))).toList();
        }
        if (!employeeDto.getSalary().isEmpty()){
            employees= employees.stream().filter(employee -> employeeDto.getSalary()
                    .contains(employee.getSalary())).toList();
        }
        return employees;
    }

}

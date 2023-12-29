package com.filter.filter.service.impl;

import com.filter.filter.dto.EmployeeDto;
import com.filter.filter.model.Employee;
import com.filter.filter.repository.EmployeeRepo;
import com.filter.filter.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Override
    public String updateUser(Long employeeId, Employee employee) {
        Employee emp = Employee.builder().id(employeeId).email(employee.getEmail())
                .name(employee.getName()).address(employee.getAddress()).
                companyName(employee.getCompanyName()).position(employee.getPosition())
                .contact(employee.getContact()).salary(employee.getSalary()).build();
        employeeRepo.save(emp);
        return "Employee Details Updated !!!!!!!";
    }

    @Override
    public String deleteEmployee(Long employeeId) {
        if(employeeRepo.existsById(employeeId)){
            employeeRepo.deleteById(employeeId);
            return "Employee is deleted Successfully";
        }
        return "no record found with the given Id:"+employeeId;
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(Long employeeId) {
        if(employeeRepo.existsById(employeeId)){
            return new ResponseEntity<>(employeeRepo.findById(employeeId).get(),HttpStatus.OK);

        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @Override
    public void deleteMatching(String name){
        List<Employee> employees = employeeRepo.findByName(name);
        if(employees.size()>1) {
            int i = 1;
            for (Employee employee : employees) {
                if (i < employees.size()) {
                    employeeRepo.deleteById(employee.getId());
                }
                i++;
            }
        }
    }

    @Override
    public void downloadData(EmployeeDto employeeDto) {

    }
}


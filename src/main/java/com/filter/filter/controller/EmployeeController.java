package com.filter.filter.controller;

import com.filter.filter.dto.EmployeeDto;
import com.filter.filter.model.Employee;
import com.filter.filter.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService empService;
    public EmployeeController(EmployeeService empService){
        this.empService = empService;
    }
    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(empService.save(employee), HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        return new ResponseEntity<>(empService.getAll(), HttpStatus.OK);
    }
    @PostMapping("/get-filter-data")
    public ResponseEntity<List<Employee>> getFilterdata(@RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(empService.getFilterData(employeeDto), HttpStatus.OK);
    }
    @PutMapping("/update-user/{employeeId}")
    public ResponseEntity<String> updateUser(@PathVariable Long employeeId, @RequestBody Employee employee) {
         return new ResponseEntity<>(empService.updateUser(employeeId, employee), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public String deleteEmployee(@RequestParam Long employeeId){
        return empService.deleteEmployee(employeeId);
    }

    @GetMapping("/get-by-id/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long employeeId){
        return empService.getEmployeeById(employeeId);
    }

    @DeleteMapping("/delete-matching")
    public void deleteMatching(@RequestParam String name){
        empService.deleteMatching(name);
    }

    @GetMapping("/download-data")
    public void downloaddata(@RequestBody EmployeeDto employeeDto){
        empService.downloadData(employeeDto);
    }

}

package com.filter.filter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class EmployeeDto {
    private List<String> name;
    private List<String> companyName;
    private List<String> position;
    private List<String> email;
    private List<Long> salary;
}

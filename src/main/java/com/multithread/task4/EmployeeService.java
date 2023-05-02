package com.multithread.task4;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class EmployeeService {
    public static final int NUMBER_OF_EMPLOYEES = 10_000;
    private final List<Employee> employees;

    public EmployeeService() {
        this.employees = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < NUMBER_OF_EMPLOYEES; i++){
            Employee employee = new Employee(i, "name-" + i, String.valueOf(LocalDateTime.now()), random.nextBoolean(), 0);
            employees.add(employee);
        }
    }

    public List<Employee> getHiredEmployees(){
        return this.employees.stream()
                .filter(Employee::hired)
                .collect(Collectors.toList());
    }
}

package com.multithread.task4;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executors;

public class EmployeeController {
    public static final int DEFAULT_SALARY = -1;
    private final EmployeeService employeeService;
    private final SalaryService salaryService;

    public EmployeeController(EmployeeService employeeService, SalaryService salaryService) {
        this.employeeService = employeeService;
        this.salaryService = salaryService;
    }

    public CompletableFuture<?> hiredEmployeesAsync(){
        return CompletableFuture.supplyAsync (employeeService::getHiredEmployees)
                .thenCompose(employees -> {
                            CompletableFuture<?>[] futures = employees.stream()
                                    .map(this::setSalary)
                                    .toArray(CompletableFuture[]::new);
                            return CompletableFuture.allOf(futures)
                                    .thenApply(unused -> employees);
                        })
                .whenComplete((employees, throwable) -> System.out.println(employees));
    }
    private CompletionStage<Void> setSalary (Employee employee){
        return CompletableFuture.runAsync(() -> employee.setSalary(salaryService.getSalary(employee.id()).orElse(DEFAULT_SALARY)));
    }
}

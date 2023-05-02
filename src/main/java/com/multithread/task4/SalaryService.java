package com.multithread.task4;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

public class SalaryService {
    private final Map<Integer, Integer> salaries;
    public SalaryService() {
        this.salaries = new HashMap<>(EmployeeService.NUMBER_OF_EMPLOYEES);
        Random random = new Random();
        IntStream.range(0, EmployeeService.NUMBER_OF_EMPLOYEES)
                .boxed()
                .forEach(i -> salaries.put(i, random.nextInt(1000, 10000)));
    }

    public Optional<Integer> getSalary(int hiredEmployeeId){
        return Optional.ofNullable(salaries.getOrDefault(hiredEmployeeId, null));
    }
}

package com.multithread.task4;

public class App {
    public static void main(String[] args) {
        SalaryService salaryService = new SalaryService();
        EmployeeService employeeService = new EmployeeService();
        EmployeeController employeeController = new EmployeeController(employeeService, salaryService);
        employeeController.hiredEmployeesAsync().join();
    }
}

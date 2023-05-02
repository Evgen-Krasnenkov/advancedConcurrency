package com.multithread.task4;

import java.util.Objects;

public final class Employee {
    private final int id;
    private final String name;
    private final String dob;
    private final boolean hired;

    private int salary;

    public Employee(int id, String name, String dob, boolean hired, int salary) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.hired = hired;
        this.salary = salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public boolean hired() {
        return hired;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Employee) obj;
        return this.id == that.id &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.dob, that.dob) &&
                this.hired == that.hired &&
                this.salary == that.salary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dob, hired, salary);
    }

    @Override
    public String toString() {
        return "Employee[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "dob=" + dob + ", " +
                "hired=" + hired + ", " +
                "salary=" + salary + ']';
    }

}

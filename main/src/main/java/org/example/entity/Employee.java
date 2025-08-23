package org.example.entity;

import java.util.Objects;

public class Employee {
    private String name;
    private Integer age;
    private String function;

    public Employee(String name, Integer age, String function) {
        this.name = name;
        this.age = age;
        this.function = function;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(age, employee.age) && Objects.equals(function, employee.function);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, function);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", function='" + function + '\'' +
                '}';
    }
}

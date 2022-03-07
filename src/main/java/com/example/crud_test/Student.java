package com.example.crud_test;

public class Student {
    private Long id;
    private String name;
    private long matricNumber;
    private String department;

    public Student(Long id, String name, long matricNumber, String department) {
        this.id = id;
        this.name = name;
        this.matricNumber = matricNumber;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getMatricNumber() {
        return matricNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setId(Long id) {    
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMatricNumber(int matricNumber) {
        this.matricNumber = matricNumber;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

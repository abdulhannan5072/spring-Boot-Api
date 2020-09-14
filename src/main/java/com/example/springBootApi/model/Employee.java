package com.example.springBootApi.model;

import org.springframework.data.annotation.Id;

public class Employee {
    @Id
    private String id;

    private String name;
    private String phoneNo;

    //constructor
    public Employee(String name, String phoneNo) {
        this.name = name;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }



    @Override
    public String toString() {
        return String.format(
                "Employee[id=%s, name='%s', phoneNo='%s']",
                id, name, phoneNo);
    }
}

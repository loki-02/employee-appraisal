package com.example.employeeappraisal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity(name = "EntityEmployee")
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    private int employeeId; // Unique ID for the employee

    @Column(nullable = false)
    private String employeeName; // Employee's name

    @Column(nullable = false)
    private Character rating; // Employee's rating (A, B, C, D, E)
    // Getters and setters

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(Character rating) {
        this.rating = rating;
    }
}

package com.example.employeeappraisal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Category {

    @Id
    private Character category; // A single character (A, B, C, D, E)
    private double standardPercentage; // Standard percentage
    private Double actualPercentage; // Actual percentage

    // Getters and setters
    public char getCategory() {
        return category;
    }

    public void setCategory(char category) {
        this.category = category;
    }

    public double getStandardPercentage() {
        return standardPercentage;
    }

    public void setStandardPercentage(double standardPercentage) {
        this.standardPercentage = standardPercentage;
    }

    public double getActualPercentage() {
        return actualPercentage;
    }

    public void setActualPercentage(Double actualPercentage) {
        this.actualPercentage = actualPercentage;
    }
}
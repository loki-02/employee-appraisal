package com.example.employeeappraisal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String ratingCategory;

    // Constructors
    public Employee() {
    }

    public Employee(String name, String ratingCategory) {
        this.name = name;
        this.ratingCategory = ratingCategory;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRatingCategory() {
        return ratingCategory;
    }

    public void setRatingCategory(String ratingCategory) {
        this.ratingCategory = ratingCategory;
    }
}

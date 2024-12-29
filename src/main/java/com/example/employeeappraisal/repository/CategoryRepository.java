package com.example.employeeappraisal.repository;

import com.example.employeeappraisal.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Character> {
}

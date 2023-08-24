package com.example.TPIntermediaireFavoris.persistence.repository;

import com.example.TPIntermediaireFavoris.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}

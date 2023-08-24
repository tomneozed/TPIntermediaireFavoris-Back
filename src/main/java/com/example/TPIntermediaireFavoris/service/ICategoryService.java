package com.example.TPIntermediaireFavoris.service;

import com.example.TPIntermediaireFavoris.dto.CategoryDTO;
import com.example.TPIntermediaireFavoris.persistence.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> findAll();

    CategoryDTO findOne(Long id);

    void delete(Long id);

    CategoryDTO save(CategoryDTO category);
}
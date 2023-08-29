package com.example.TPIntermediaireFavoris.service;

import com.example.TPIntermediaireFavoris.dto.CategoryDTO;
import com.example.TPIntermediaireFavoris.dto.CategoryReferencesDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryReferencesDTO> findAll();

    CategoryReferencesDTO findOne(Long id);

    void delete(Long id);

    CategoryDTO save(CategoryDTO category);
}

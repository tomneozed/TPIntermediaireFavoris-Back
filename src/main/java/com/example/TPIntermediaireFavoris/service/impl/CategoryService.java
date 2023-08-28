package com.example.TPIntermediaireFavoris.service.impl;

import com.example.TPIntermediaireFavoris.dto.CategoryDTO;
import com.example.TPIntermediaireFavoris.exceptions.NotFoundException;
import com.example.TPIntermediaireFavoris.persistence.entity.Category;
import com.example.TPIntermediaireFavoris.persistence.repository.ICategoryRepository;
import com.example.TPIntermediaireFavoris.service.ICategoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(c -> new CategoryDTO(c.getId(), c.getLabel()))
                .toList();
    }

    @Override
    public CategoryDTO findOne(Long id) {
        return categoryRepository.findById(id).map(c -> new CategoryDTO(
                c.getId(),
                c.getLabel()
        )).orElseThrow(() -> new NotFoundException("L'item d'id " + id + " n'existe pas."));
    }

    @Override
    public void delete(Long id) {
        Category c = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("L'item d'id " + id + " n'existe pas."));
        categoryRepository.delete(c);
    }

    @Override
    public CategoryDTO save(CategoryDTO category) {
        Category c = new Category();
        c.setId(category.getId());
        c.setLabel(category.getLabel());

        c = categoryRepository.save(c);
        return new CategoryDTO(c.getId(), c.getLabel());
    }
}

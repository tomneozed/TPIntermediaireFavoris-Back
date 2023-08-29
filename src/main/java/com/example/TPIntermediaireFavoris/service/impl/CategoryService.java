package com.example.TPIntermediaireFavoris.service.impl;

import com.example.TPIntermediaireFavoris.dto.CategoryDTO;
import com.example.TPIntermediaireFavoris.dto.CategoryReferencesDTO;
import com.example.TPIntermediaireFavoris.exceptions.NotFoundException;
import com.example.TPIntermediaireFavoris.persistence.entity.Category;
import com.example.TPIntermediaireFavoris.persistence.repository.ICategoryRepository;
import com.example.TPIntermediaireFavoris.persistence.repository.IFavoriteRepository;
import com.example.TPIntermediaireFavoris.service.ICategoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;
    private final IFavoriteRepository favoriteRepository;

    public CategoryService(ICategoryRepository categoryRepository, IFavoriteRepository favoriteRepository) {
        this.categoryRepository = categoryRepository;
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public List<CategoryReferencesDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(c -> new CategoryReferencesDTO(c.getId(), c.getLabel(), countReferences(c.getId())))
                .toList();
    }

    @Override
    public CategoryReferencesDTO findOne(Long id) {
        return categoryRepository.findById(id).map(c -> new CategoryReferencesDTO(
                c.getId(),
                c.getLabel(),
                countReferences(id)
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

    private long countReferences(long categoryId) {
            return favoriteRepository.findAll()
                .stream()
                .filter(f -> f.getCategory().getId().equals(categoryId))
                .count();
    }
}

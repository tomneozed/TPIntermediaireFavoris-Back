package com.example.TPIntermediaireFavoris.controller;

import com.example.TPIntermediaireFavoris.dto.CategoryDTO;
import com.example.TPIntermediaireFavoris.dto.FavoriteDTO;
import com.example.TPIntermediaireFavoris.dto.SaveFavoriteDTO;
import com.example.TPIntermediaireFavoris.service.ICategoryService;
import com.example.TPIntermediaireFavoris.service.IFavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/category")
public class CategoryController {

    private final ICategoryService categoryService;
    private final IFavoriteService favoriteService;

    public CategoryController(ICategoryService categoryService, IFavoriteService favoriteService) {
        this.categoryService = categoryService;
        this.favoriteService = favoriteService;
    }

    @GetMapping
    List<CategoryDTO> findAll() {
        return categoryService.findAll();
    }

    @GetMapping(path = "/{id}")
    CategoryDTO findOne(@PathVariable long id) {
        return categoryService.findOne(id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    void delete(@PathVariable long id) {
        categoryService.delete(id);
    }

    @PostMapping
    CategoryDTO save(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }

    @PostMapping(path = "/{categoryId}/favorite")
    FavoriteDTO saveFavorite(@PathVariable Long categoryId, @RequestBody SaveFavoriteDTO saveFavoriteDTO) {
        return favoriteService.save(categoryId, saveFavoriteDTO);
    }
}
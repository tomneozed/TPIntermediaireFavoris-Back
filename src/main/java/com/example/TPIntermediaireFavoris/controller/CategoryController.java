package com.example.TPIntermediaireFavoris.controller;

import com.example.TPIntermediaireFavoris.dto.CategoryDTO;
import com.example.TPIntermediaireFavoris.dto.CategoryReferencesDTO;
import com.example.TPIntermediaireFavoris.dto.FavoriteDTO;
import com.example.TPIntermediaireFavoris.dto.SaveFavoriteDTO;
import com.example.TPIntermediaireFavoris.service.ICategoryService;
import com.example.TPIntermediaireFavoris.service.IFavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Returns all Categories")
    @GetMapping
    List<CategoryReferencesDTO> findAll() {
        return categoryService.findAll();
    }

    @Operation(summary = "Returns a Category by its ID")
    @GetMapping(path = "/{id}")
    CategoryReferencesDTO findOne(@PathVariable long id) {
        return categoryService.findOne(id);
    }

    @GetMapping(path = "/{categoryId}/favorite")
    List<FavoriteDTO> findAllByCategory(@PathVariable long categoryId) {
        return favoriteService.findAllByCategory(categoryId);
    }

    @Operation(summary = "Deletes a Category by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The Category was deleted"),
            @ApiResponse(responseCode = "404", description = "The Category was not found"),
            @ApiResponse(responseCode = "500", description = "The Category couldn't be deleted")
    })
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    void delete(@PathVariable long id) {
        categoryService.delete(id);
    }

    @Operation(summary = "Creates a new Category if ID is null, otherwise it updates the Category")
    @PostMapping
    CategoryDTO save(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }

    @Operation(summary = "Creates a new Favorite of Category {categoryId} if ID is null, otherwise it updates the Favorite")
    @PostMapping(path = "/{categoryId}/favorite")
    FavoriteDTO saveFavorite(@PathVariable Long categoryId, @RequestBody SaveFavoriteDTO saveFavoriteDTO) {
        return favoriteService.save(categoryId, saveFavoriteDTO);
    }
}
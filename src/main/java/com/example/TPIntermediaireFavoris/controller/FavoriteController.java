package com.example.TPIntermediaireFavoris.controller;

import com.example.TPIntermediaireFavoris.dto.FavoriteDTO;
import com.example.TPIntermediaireFavoris.service.IFavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api/favorite")
public class FavoriteController {
    private final IFavoriteService favoriteService;

    public FavoriteController(IFavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @Operation(summary = "Returns all Favorites")
    @GetMapping
    List<FavoriteDTO> findAll() {
        return favoriteService.findAll();
    }

    @Operation(summary = "Returns a Favorite by its ID")
    @GetMapping(path = "/{id}")
    FavoriteDTO findOne(@PathVariable long id) {
        return favoriteService.findOne(id);
    }

    @Operation(summary = "Delete multiple Favorites by their IDs")
    @ApiResponse(responseCode = "200", description = "The Favorite was deleted")
    @DeleteMapping(path = "/{ids}")
    @ResponseStatus(code = HttpStatus.OK)
    void deleteMultiple(@PathVariable String ids) {
        favoriteService.deleteMultiple(
                Arrays.stream(ids.split(",")).map(Long::valueOf).toList()
        );
    }
}
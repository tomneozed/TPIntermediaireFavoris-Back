package com.example.TPIntermediaireFavoris.controller;

import com.example.TPIntermediaireFavoris.dto.FavoriteDTO;
import com.example.TPIntermediaireFavoris.persistence.entity.Favorite;
import com.example.TPIntermediaireFavoris.persistence.repository.IFavoriteRepository;
import com.example.TPIntermediaireFavoris.service.IFavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/favorite")
public class FavoriteController {
    private final IFavoriteService favoriteService;
    private final IFavoriteRepository favoriteRepository;

    public FavoriteController(IFavoriteService favoriteService, IFavoriteRepository favoriteRepository) {
        this.favoriteService = favoriteService;
        this.favoriteRepository = favoriteRepository;
    }

    @GetMapping
    List<FavoriteDTO> findAll() {
        return favoriteService.findAll();
    }

    @GetMapping(path = "/{id}")
    FavoriteDTO findOne(@PathVariable long id) {
        return favoriteService.findOne(id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    void delete(@PathVariable long id) {
        favoriteService.delete(id);
    }

    @GetMapping("/test")
    public Favorite getFavTest() {
        return favoriteRepository.findById(1L).get();
    }
}
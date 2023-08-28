package com.example.TPIntermediaireFavoris.service;

import com.example.TPIntermediaireFavoris.dto.FavoriteDTO;
import com.example.TPIntermediaireFavoris.dto.SaveFavoriteDTO;

import java.util.List;

public interface IFavoriteService {
    List<FavoriteDTO> findAll();

    FavoriteDTO findOne(Long id);

    void delete(Long id);

    FavoriteDTO save(Long CategoryId, SaveFavoriteDTO saveFavoriteDto);
}

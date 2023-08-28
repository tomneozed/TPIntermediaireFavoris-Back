package com.example.TPIntermediaireFavoris.service;

import com.example.TPIntermediaireFavoris.dto.FavoriteDTO;
import com.example.TPIntermediaireFavoris.dto.SaveFavoriteDTO;

import java.util.List;

public interface IFavoriteService {
    List<FavoriteDTO> findAll();

    List<FavoriteDTO> findAllByCategory(long id);

    FavoriteDTO findOne(long id);

    void delete(long id);

    void deleteMultiple(List<Long> ids);

    FavoriteDTO save(Long CategoryId, SaveFavoriteDTO saveFavoriteDto);
}

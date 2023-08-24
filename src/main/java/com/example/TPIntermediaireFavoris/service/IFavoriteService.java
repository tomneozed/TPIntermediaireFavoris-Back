package com.example.TPIntermediaireFavoris.service;

import com.example.TPIntermediaireFavoris.dto.FavoriteDTO;
import java.util.List;

public interface IFavoriteService {
    List<FavoriteDTO> findAll();

    FavoriteDTO findOne(Long id);

    void delete(Long id);

    FavoriteDTO save(FavoriteDTO favorite);
}

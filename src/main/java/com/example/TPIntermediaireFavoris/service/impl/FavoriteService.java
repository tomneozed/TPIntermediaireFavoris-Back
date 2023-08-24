package com.example.TPIntermediaireFavoris.service.impl;

import com.example.TPIntermediaireFavoris.dto.FavoriteDTO;
import com.example.TPIntermediaireFavoris.exceptions.NotFoundException;
import com.example.TPIntermediaireFavoris.persistence.entity.Favorite;
import com.example.TPIntermediaireFavoris.persistence.repository.IFavoriteRepository;
import com.example.TPIntermediaireFavoris.service.IFavoriteService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class FavoriteService implements IFavoriteService {

    private final IFavoriteRepository favoriteRepository;

    public FavoriteService(IFavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public List<FavoriteDTO> findAll() {
        return favoriteRepository.findAll()
                .stream()
                .map(f -> new FavoriteDTO(
                        f.getId(),
                        f.getLink(),
                        f.getLabel(),
                        f.getLast_updated(),
                        f.getCategory()))
                .toList();
    }

    @Override
    public FavoriteDTO findOne(Long id) {
        return favoriteRepository.findById(id)
                .map(f -> new FavoriteDTO(
                        f.getId(),
                        f.getLink(),
                        f.getLabel(),
                        f.getLast_updated(),
                        f.getCategory()))
                .orElseThrow(() -> new NotFoundException("L'item d'id " + id + " n'existe pas."));
    }

    @Override
    public void delete(Long id) {
        Favorite f = favoriteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("L'item d'id " + id + " n'existe pas."));
        favoriteRepository.delete(f);
    }

    @Override
    public FavoriteDTO save(FavoriteDTO favorite) {
        Favorite f = new Favorite(
                favorite.getId(),
                favorite.getLink(),
                favorite.getCategory(),
                getCurrentTime(),
                favorite.getCategory()
        );

        f = favoriteRepository.save(f);
        return new FavoriteDTO(f.getId(), f.getLink(), f.getLabel(), f.getLast_updated(), f.getCategory());
    }

    private String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}

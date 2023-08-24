package com.example.TPIntermediaireFavoris.service.impl;

import com.example.TPIntermediaireFavoris.dto.CategoryDTO;
import com.example.TPIntermediaireFavoris.dto.FavoriteDTO;
import com.example.TPIntermediaireFavoris.exceptions.NotFoundException;
import com.example.TPIntermediaireFavoris.persistence.entity.Favorite;
import com.example.TPIntermediaireFavoris.persistence.repository.IFavoriteRepository;
import com.example.TPIntermediaireFavoris.service.IFavoriteService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
                        CategoryDTO.fromEntity(f.getCategory())))
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
                        CategoryDTO.fromEntity(f.getCategory())))
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
                favorite.getLabel(),
                getCurrentTime(),
                CategoryDTO.toEntity(favorite.getCategoryDto())
        );

        f = favoriteRepository.save(f);
        return new FavoriteDTO(
                f.getId(),
                f.getLink(),
                f.getLabel(),
                f.getLast_updated(),
                CategoryDTO.fromEntity(f.getCategory())
        );
    }

    private String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}

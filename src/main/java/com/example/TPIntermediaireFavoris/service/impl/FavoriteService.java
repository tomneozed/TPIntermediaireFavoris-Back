package com.example.TPIntermediaireFavoris.service.impl;

import com.example.TPIntermediaireFavoris.dto.FavoriteDTO;
import com.example.TPIntermediaireFavoris.exceptions.NotFoundException;
import com.example.TPIntermediaireFavoris.persistence.entity.Favorite;
import com.example.TPIntermediaireFavoris.persistence.repository.IFavoriteRepository;
import com.example.TPIntermediaireFavoris.service.IFavoriteService;
import org.springframework.stereotype.Service;

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
        Favorite f = new Favorite();
        f.setId(favorite.getId());
        f.setLink(favorite.getLink());
        f.setLast_updated(favorite.getLast_updated());
        f.setCategory(favorite.getCategory());
        f.setLabel(favorite.getLabel());

        f = favoriteRepository.save(f);
        return new FavoriteDTO(f.getId(), f.getLink(), f.getLabel(), f.getLast_updated(), f.getCategory());
    }
}

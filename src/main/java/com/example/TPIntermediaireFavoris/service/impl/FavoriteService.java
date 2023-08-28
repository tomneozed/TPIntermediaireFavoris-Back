package com.example.TPIntermediaireFavoris.service.impl;

import com.example.TPIntermediaireFavoris.dto.CategoryDTO;
import com.example.TPIntermediaireFavoris.dto.FavoriteDTO;
import com.example.TPIntermediaireFavoris.dto.SaveFavoriteDTO;
import com.example.TPIntermediaireFavoris.exceptions.NotFoundException;
import com.example.TPIntermediaireFavoris.persistence.entity.Category;
import com.example.TPIntermediaireFavoris.persistence.entity.Favorite;
import com.example.TPIntermediaireFavoris.persistence.repository.ICategoryRepository;
import com.example.TPIntermediaireFavoris.persistence.repository.IFavoriteRepository;
import com.example.TPIntermediaireFavoris.service.IFavoriteService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FavoriteService implements IFavoriteService {

    private final IFavoriteRepository favoriteRepository;
    private final ICategoryRepository categoryRepository;

    public FavoriteService(IFavoriteRepository favoriteRepository, ICategoryRepository categoryRepository) {
        this.favoriteRepository = favoriteRepository;
        this.categoryRepository = categoryRepository;
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
    public FavoriteDTO findOne(long id) {
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
    public void delete(long id) {
        Favorite f = favoriteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("L'item d'id " + id + " n'existe pas."));
        favoriteRepository.delete(f);
    }

    @Override
    public void deleteMultiple(List<Long> ids) {
        ids.forEach(this::delete);
    }

    @Override
    public FavoriteDTO save(Long categoryId, SaveFavoriteDTO saveFavoriteDTO) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("L'item d'id " + categoryId + " n'existe pas."));

        Favorite favorite;

        if(saveFavoriteDTO.getId() != null) {
            favorite = favoriteRepository.findById(saveFavoriteDTO.getId())
                    .orElseThrow(() -> new NotFoundException("L'item d'id " + saveFavoriteDTO.getId() + " n'existe pas."));
        } else {
            favorite = new Favorite();
        }

        favorite.setCategory(category);
        favorite.setLink(saveFavoriteDTO.getLink());
        favorite.setLast_updated(getCurrentTime());
        favorite.setLabel(saveFavoriteDTO.getLabel());

        favorite = favoriteRepository.save(favorite);

        return new FavoriteDTO(
                favorite.getId(),
                favorite.getLink(),
                favorite.getLabel(),
                favorite.getLast_updated(),
                CategoryDTO.fromEntity(favorite.getCategory()));
    }

    private String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}

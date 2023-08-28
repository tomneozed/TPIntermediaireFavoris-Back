package com.example.TPIntermediaireFavoris.dto;

import com.example.TPIntermediaireFavoris.persistence.entity.Favorite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FavoriteDTO {
    private Long id;

    private String link;

    private String label;

    private String last_updated;

    private CategoryDTO categoryDto;

    public static FavoriteDTO fromEntity(Favorite favorite) {
        if (favorite == null) {
            return null;
        } else {
            return new FavoriteDTO(
                    favorite.getId(),
                    favorite.getLink(),
                    favorite.getLabel(),
                    favorite.getLast_updated(),
                    CategoryDTO.fromEntity(favorite.getCategory()));
        }
    }

    public static Favorite toEntity(FavoriteDTO favoriteDto) {
        if (favoriteDto == null) {
            return null;
        } else {
            return new Favorite(
                    favoriteDto.getId(),
                    favoriteDto.getLink(),
                    favoriteDto.getLabel(),
                    favoriteDto.getLast_updated(),
                    CategoryDTO.toEntity(favoriteDto.getCategoryDto())
            );
        }
    }
}

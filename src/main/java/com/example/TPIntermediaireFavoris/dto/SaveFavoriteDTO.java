package com.example.TPIntermediaireFavoris.dto;

import com.example.TPIntermediaireFavoris.persistence.entity.Favorite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SaveFavoriteDTO {
    private Long id;

    private String link;

    private String label;

    private Date last_updated;

    public static SaveFavoriteDTO fromEntity(Favorite favorite) {
        if (favorite == null) {
            return null;
        } else {
            return new SaveFavoriteDTO(
                    favorite.getId(),
                    favorite.getLink(),
                    favorite.getLabel(),
                    favorite.getLast_updated());
        }
    }
}
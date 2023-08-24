package com.example.TPIntermediaireFavoris.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDTO {
    private Long id;

    private String link;

    private String label;

    private String last_updated;

    private String category;
}

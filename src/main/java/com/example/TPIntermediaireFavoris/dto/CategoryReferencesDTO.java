package com.example.TPIntermediaireFavoris.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryReferencesDTO {
    private Long id;
    private String label;
    private long references;
}

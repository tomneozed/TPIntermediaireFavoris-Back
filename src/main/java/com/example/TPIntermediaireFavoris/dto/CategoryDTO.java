package com.example.TPIntermediaireFavoris.dto;

import com.example.TPIntermediaireFavoris.persistence.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;

    private String label;

    public static CategoryDTO fromEntity(Category category) {
        if (category == null) {
            return null;
        } else {
            return new CategoryDTO(
                    category.getId(),
                    category.getLabel()
            );
        }
    }

    public static Category toEntity(CategoryDTO categoryDto) {
        if (categoryDto == null) {
            return null;
        } else {
            Category category = new Category();

            category.setId(categoryDto.getId());
            category.setLabel(categoryDto.getLabel());

            return category;
        }
    }
}

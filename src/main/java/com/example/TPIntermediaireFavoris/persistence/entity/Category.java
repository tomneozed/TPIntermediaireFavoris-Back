package com.example.TPIntermediaireFavoris.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category", uniqueConstraints = { @UniqueConstraint(name = "UniqueCategoryLabel", columnNames = { "label" }) })
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column (name = "label", nullable = false)
    private String label;
}

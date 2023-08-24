package com.example.TPIntermediaireFavoris.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "favorite")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column (name = "link", nullable = false)
    private String link;

    @Column (name = "label")
    private String label;

    @Column (name = "last_updated")
    private String last_updated;

    @Column (name = "category", nullable = false)
    private String category;

    /*
    @Column (name = "category_id", nullable = false)
    @ManyToOne
    private Long category_id;
     */
}

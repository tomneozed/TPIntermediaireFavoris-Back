package com.example.TPIntermediaireFavoris.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "favorite", uniqueConstraints = { @UniqueConstraint(name = "UniqueFavoriteLink", columnNames = { "link" }) })
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn (nullable = false, foreignKey = @ForeignKey(name = "FK_FAVORITE_CATEGORY"))
    @Fetch(FetchMode.JOIN)
    private Category category;
}

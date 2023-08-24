package com.example.TPIntermediaireFavoris.persistence.repository;

import com.example.TPIntermediaireFavoris.persistence.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFavoriteRepository extends JpaRepository<Favorite, Long> {
}

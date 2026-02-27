package com.sensei.apitabibiadminpanel.repository.core;

import com.sensei.apitabibiadminpanel.entities.core.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Favorite.FavoriteId> {
}
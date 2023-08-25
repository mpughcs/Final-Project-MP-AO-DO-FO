package com.company.gamestore.repositories;

import com.company.gamestore.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepo extends JpaRepository<Game, Integer> {
    List<Game> findByTitle(String title);
}

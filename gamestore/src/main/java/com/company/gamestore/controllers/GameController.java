package com.company.gamestore.controllers;

import com.company.gamestore.models.Game;
import com.company.gamestore.repositories.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameRepo gameRepo;

    @GetMapping
    public List<Game> getAllGames() {
        return gameRepo.findAll();
    }

    @GetMapping("/{game_id}")
    public Game getGameById(@PathVariable int game_id) {
        return gameRepo.findById(game_id).orElse(null);
    }

    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return gameRepo.save(game);
    }

    @PutMapping("/{game_id}")
    public Game updateGame(@PathVariable int game_id, @RequestBody Game game) {
        if (gameRepo.existsById(game_id)) {
            game.setId(game_id);
            return gameRepo.save(game);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{game_id}")
    public void deleteGame(@PathVariable int game_id) {
        gameRepo.deleteById(game_id);
    }
}

package com.company.gamestore.controllers;

import com.company.gamestore.models.Console;
import com.company.gamestore.models.Game;
import com.company.gamestore.repositories.ConsoleRepo;
import com.company.gamestore.repositories.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

//Your project must support GraphQL queries to retrieve the following information:
//
//        Get all Games
//
//        Get a Game by ID
//
//        Get a Game by Title
//
//        Get a Game by ESRB rating
//
//        Get a Game by Studio
//
//        Get all Consoles
//
//        Get a Console by ID
//
//        Get a Console by Manufacturer

@Controller
public class GraphController {
    @Autowired
    ConsoleRepo consoleRepo;

    @Autowired
    GameRepo gameRepo;

    //console queries
    @QueryMapping
    public List<Console> getConsoles(){
        return consoleRepo.findAll();
    }
    @QueryMapping
    public List<Console> getConsoleByManufacturer(@Argument String manufacturer){
        return consoleRepo.findByManufacturer(manufacturer);
    }
    @QueryMapping
    public Console getConsoleById(@Argument int id){
        return consoleRepo.findById(id).orElse(null);
    }

//    Game Queries

    @QueryMapping
    public List<Game> getGames(){
        return gameRepo.findAll();
    }
    @QueryMapping
    public Game getGameById(@Argument int id){
        return gameRepo.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Game> getGameByTitle(@Argument String title){
        return gameRepo.findByTitle(title);
    }

//  TODO: Get a Game by ESRB rating
//    TODO: Get a Game by Studio



//
}

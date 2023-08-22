package com.company.gamestore.controllers;


import com.company.gamestore.models.Console;
import com.company.gamestore.repositories.ConsoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ConsoleController {
    @Autowired
    ConsoleRepo repo;

//    Create mapping
    @PostMapping("/consoles")
    @ResponseStatus(HttpStatus.CREATED)
    public Console addConsole(@RequestBody Console toAdd){return repo.save(toAdd);}


//    Read and Read all mappings
    @GetMapping("/consoles")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getConsoles(){return repo.findAll();}

    //    Read by id
    @GetMapping("/consoles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Console getConsolesById(@PathVariable int id){
        Optional<Console> toReturn = repo.findById(id);
        if(toReturn.isPresent()){
            return toReturn.get();
        } else{
            return null;
        }
    }
    @PutMapping("/consoles")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody Console console){
        repo.save(console);
    }

    @DeleteMapping("/consoles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id){
        repo.deleteById(id);
    }

}

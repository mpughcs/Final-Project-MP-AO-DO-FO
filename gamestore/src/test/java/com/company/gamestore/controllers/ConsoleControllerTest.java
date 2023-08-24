package com.company.gamestore.controllers;

import com.company.gamestore.models.Console;
import com.company.gamestore.repositories.ConsoleRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ConsoleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper= new ObjectMapper();

    @Autowired
    private ConsoleRepo repo;


    Console c = new Console("XboxOne","Microsoft","16gb","I7",new BigDecimal("499.99"),100);

    String inputJson;

    @BeforeEach
    public void voidSetup() throws Exception{
        repo.deleteAll();
        inputJson = mapper.writeValueAsString(c);

    }
//    Create tests
    @Test
    void shouldAddConsole() throws Exception {
        c=repo.save(c);
        inputJson = mapper.writeValueAsString(c);


        mockMvc.perform(
                        post("/consoles")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

//    read tests
    @Test
    void shouldGetConsoles() throws Exception{
        mockMvc.perform(get("/consoles"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetConsoleById() throws Exception {
        mockMvc.perform(get("/consoles/{id}",c.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

//    update test
    @Test
    void shouldUpdateConsole() throws Exception {
        repo.save(c);
        mockMvc.perform(
                        put("/consoles/", c)
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
//    delete test
    @Test
    void shouldDeleteConsole() throws Exception {
        repo.save(c);
        mockMvc.perform(delete("/consoles/{id}", c.getId()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

//    Custom Query
    @Test
    void shouldFindByAuthorId() throws Exception{
        repo.save(c);
        mockMvc.perform(get("/consoles/manufacturer/{name}",c.getManufacturer()))
                .andDo(print())
                .andExpect(status().isOk());

    }





}
package com.abhi.beerservice.Controller;

import com.abhi.beerservice.Model.BeerDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getBeerByID() throws Exception {
    mockMvc.perform(get("/api/beer/" + UUID.randomUUID()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void saveBeer() throws Exception {
        BeerDTO beerDTO = BeerDTO.builder().build();
        String BeerJSon = objectMapper.writeValueAsString(beerDTO);
        mockMvc.perform(post("/api/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(BeerJSon))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeer() throws Exception {
        BeerDTO beerDTO = BeerDTO.builder().build();
        String beerJson = objectMapper.writeValueAsString(beerDTO);
        mockMvc.perform(put("/api/beer/" + UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                            .content(beerJson))
                            .andExpect(status().isNoContent());
    }

    @Test
    void deleteBeer() throws Exception {
        BeerDTO beerDTO= BeerDTO.builder().build();
        String beerJson = objectMapper.writeValueAsString(beerDTO);
        mockMvc.perform(delete("/api/beer/" + UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerJson))
                        .andExpect(status().isNoContent());
    }
}
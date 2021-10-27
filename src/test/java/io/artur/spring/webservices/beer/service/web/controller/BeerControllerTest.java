package io.artur.spring.webservices.beer.service.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.artur.spring.webservices.beer.service.bootstrap.BeerLoader;
import io.artur.spring.webservices.beer.service.services.BeerService;
import io.artur.spring.webservices.beer.service.web.model.BeerDto;
import io.artur.spring.webservices.beer.service.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 */
@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    private static final String API_PATH = "/api/v1/beer/";

    @Test
    void getBeerById() throws Exception {
        given(beerService.getById(any(UUID.class), false)).willReturn(getValidBeerDto());

        mockMvc.perform(get(API_PATH + UUID.randomUUID()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        given(beerService.saveNewBeer(any(BeerDto.class))).willReturn(getValidBeerDto());

        BeerDto beerDto = getValidBeerDto();
        String beerToJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post(API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerToJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeer() throws Exception {
        given(beerService.updateBeer(any(UUID.class), any(BeerDto.class))).willReturn(getValidBeerDto());

        BeerDto beerDto = getValidBeerDto();
        String beerToString = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put(API_PATH + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerToString))
                .andExpect(status().isNoContent());
    }

    private BeerDto getValidBeerDto() {
        return BeerDto.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyleEnum.IPA)
                .price(BigDecimal.valueOf(10.99))
                .upc(BeerLoader.BEER_1_UPC)
                .qualityOnHand(200)
                .build();
    }
}
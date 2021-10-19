package io.artur.spring.webservices.msscbeerservice.services;

import io.artur.spring.webservices.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

/**
 *
 */
public interface BeerService {

    BeerDto getById(UUID beerId);
    BeerDto saveNewBeer(BeerDto beerDto);
    BeerDto updateBeer(UUID beerId, BeerDto beerDto);
}

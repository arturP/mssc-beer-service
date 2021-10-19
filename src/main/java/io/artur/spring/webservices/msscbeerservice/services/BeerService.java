package io.artur.spring.webservices.msscbeerservice.services;

import io.artur.spring.webservices.msscbeerservice.web.model.BeerDto;
import io.artur.spring.webservices.msscbeerservice.web.model.BeerPagedList;
import io.artur.spring.webservices.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

/**
 *
 */
public interface BeerService {
    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest);
    BeerDto getById(UUID beerId);
    BeerDto saveNewBeer(BeerDto beerDto);
    BeerDto updateBeer(UUID beerId, BeerDto beerDto);
}

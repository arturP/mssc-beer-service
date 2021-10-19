package io.artur.spring.webservices.msscbeerservice.repositories;

import io.artur.spring.webservices.msscbeerservice.domain.Beer;
import io.artur.spring.webservices.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 *
 */
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
    Page<Beer> findAllByBeerName(String beerName, Pageable pageable);
    Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable pageable);
    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pageable);
}

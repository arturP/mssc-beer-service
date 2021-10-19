package io.artur.spring.webservices.msscbeerservice.services;

import io.artur.spring.webservices.msscbeerservice.domain.Beer;
import io.artur.spring.webservices.msscbeerservice.repositories.BeerRepository;
import io.artur.spring.webservices.msscbeerservice.web.controller.BeerNotFoundException;
import io.artur.spring.webservices.msscbeerservice.web.mapper.BeerMapper;
import io.artur.spring.webservices.msscbeerservice.web.mapper.DateMapper;
import io.artur.spring.webservices.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 *
 */
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId) {

        return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(BeerNotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {

        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beerToUpdate = beerRepository.findById(beerId).orElseThrow(BeerNotFoundException::new);

        beerToUpdate.setBeerName(beerDto.getBeerName());
        beerToUpdate.setBeerStyle(beerDto.getBeerStyle().name());
        beerToUpdate.setPrice(beerDto.getPrice());
        beerToUpdate.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beerToUpdate));
    }
}

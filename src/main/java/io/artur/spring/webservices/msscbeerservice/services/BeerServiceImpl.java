package io.artur.spring.webservices.msscbeerservice.services;

import io.artur.spring.webservices.msscbeerservice.domain.Beer;
import io.artur.spring.webservices.msscbeerservice.repositories.BeerRepository;
import io.artur.spring.webservices.msscbeerservice.web.controller.BeerNotFoundException;
import io.artur.spring.webservices.msscbeerservice.web.mapper.BeerMapper;
import io.artur.spring.webservices.msscbeerservice.web.mapper.DateMapper;
import io.artur.spring.webservices.msscbeerservice.web.model.BeerDto;
import io.artur.spring.webservices.msscbeerservice.web.model.BeerPagedList;
import io.artur.spring.webservices.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 *
 */
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest) {
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search both
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            //search beer_service name
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search beer_service style
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        beerPagedList = new BeerPagedList(beerPage
                .getContent()
                .stream()
                .map(beerMapper::beerToBeerDto)
                .collect(Collectors.toList()),
                PageRequest
                        .of(beerPage.getPageable().getPageNumber(),
                                beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements());

        return beerPagedList;
    }

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

package io.artur.spring.webservices.beer.service.web.mapper;

import io.artur.spring.webservices.beer.service.domain.Beer;
import io.artur.spring.webservices.brewery.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

/**
 *
 */
@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);
    BeerDto beerToBeerDtoWithInventoryOnHand(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
}

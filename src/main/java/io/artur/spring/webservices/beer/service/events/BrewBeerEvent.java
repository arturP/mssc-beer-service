package io.artur.spring.webservices.beer.service.events;

import io.artur.spring.webservices.beer.service.web.model.BeerDto;
import lombok.NoArgsConstructor;

/**
 *
 */
@NoArgsConstructor
//@AllArgsConstructor
public class BrewBeerEvent extends BeerEvent{

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}

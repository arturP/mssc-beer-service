package io.artur.spring.webservices.brewery.model.events;

import io.artur.spring.webservices.brewery.model.BeerDto;
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

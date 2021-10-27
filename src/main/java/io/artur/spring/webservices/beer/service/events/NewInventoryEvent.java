package io.artur.spring.webservices.beer.service.events;

import io.artur.spring.webservices.beer.service.web.model.BeerDto;
import lombok.NoArgsConstructor;

/**
 *
 */
@NoArgsConstructor
//@AllArgsConstructor
public class NewInventoryEvent extends BeerEvent{

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}

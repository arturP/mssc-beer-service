package io.artur.spring.webservices.brewery.model.events;

import io.artur.spring.webservices.brewery.model.BeerDto;
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

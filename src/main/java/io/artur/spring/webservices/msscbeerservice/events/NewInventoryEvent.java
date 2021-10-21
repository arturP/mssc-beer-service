package io.artur.spring.webservices.msscbeerservice.events;

import io.artur.spring.webservices.msscbeerservice.web.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 */
@NoArgsConstructor
@AllArgsConstructor
public class NewInventoryEvent extends BeerEvent{

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}

package io.artur.spring.webservices.msscbeerservice.events;

import io.artur.spring.webservices.msscbeerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 *
 */

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    private static final long serialVersionUID = -6093493931051356815L;

    private final BeerDto beerDto;
}

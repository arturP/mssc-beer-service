package io.artur.spring.webservices.beer.service.events;

import io.artur.spring.webservices.beer.service.web.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerEvent implements Serializable {

    private static final long serialVersionUID = -6093493931051356815L;

    private BeerDto beerDto;
}

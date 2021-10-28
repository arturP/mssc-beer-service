package io.artur.spring.webservices.beer.service.services.order;

import io.artur.spring.webservices.beer.service.repositories.BeerRepository;
import io.artur.spring.webservices.brewery.model.BeerOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidator {

    private final BeerRepository repository;

    public Boolean validate(BeerOrderDto beerOrderDto) {

        AtomicInteger beersNotFound = new AtomicInteger();

        beerOrderDto.getBeerOrderLines().forEach(orderLine -> {
            if (repository.findByUpc(orderLine.getUpc()) == null) {
                beersNotFound.incrementAndGet();
            }
        });
        return beersNotFound.get() == 0;
    }
}

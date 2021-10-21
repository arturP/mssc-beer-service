package io.artur.spring.webservices.msscbeerservice.services.brewing;

import io.artur.spring.webservices.msscbeerservice.config.JmsConfig;
import io.artur.spring.webservices.msscbeerservice.domain.Beer;
import io.artur.spring.webservices.msscbeerservice.events.BrewBeerEvent;
import io.artur.spring.webservices.msscbeerservice.events.NewInventoryEvent;
import io.artur.spring.webservices.msscbeerservice.repositories.BeerRepository;
import io.artur.spring.webservices.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent event) {
        BeerDto beerDto = event.getBeerDto();

        Beer beer = beerRepository.getById(beerDto.getId());

        beerDto.setQualityOnHand(beer.getQuantityToBrew());

        log.debug("Brewed beer " + beer.getMinOnHand() + " : QOH: " + beerDto.getQualityOnHand());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);

    }
}

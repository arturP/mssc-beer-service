package io.artur.spring.webservices.msscbeerservice.services;

import io.artur.spring.webservices.msscbeerservice.config.JmsConfig;
import io.artur.spring.webservices.msscbeerservice.domain.Beer;
import io.artur.spring.webservices.msscbeerservice.events.BrewBeerEvent;
import io.artur.spring.webservices.msscbeerservice.repositories.BeerRepository;
import io.artur.spring.webservices.msscbeerservice.services.inventory.BeerInventoryService;
import io.artur.spring.webservices.msscbeerservice.web.mapper.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BrewingService {
    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {

        List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnhandInventory(beer.getId());
            log.debug("Min OnHand = " + beer.getMinOnHand());
            log.debug("Inventory = " + invQOH);
            if (beer.getMinOnHand() >= invQOH) {
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }
}

package io.artur.spring.webservices.beer.service.services.brewing;

import io.artur.spring.webservices.beer.service.config.JmsConfig;
import io.artur.spring.webservices.beer.service.domain.Beer;
import io.artur.spring.webservices.beer.service.events.BrewBeerEvent;
import io.artur.spring.webservices.beer.service.repositories.BeerRepository;
import io.artur.spring.webservices.beer.service.services.inventory.BeerInventoryService;
import io.artur.spring.webservices.beer.service.web.mapper.BeerMapper;
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

package io.artur.spring.webservices.msscbeerservice.bootstrap;

import io.artur.spring.webservices.msscbeerservice.domain.Beer;
import io.artur.spring.webservices.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 *
 */
@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerData();
    }

    private void loadBeerData() {
        if (beerRepository.count() == 0) {
            beerRepository.save(
                    Beer.builder()
                            .beerName("Lomza")
                            .beerStyle("Lager")
                            .price(BigDecimal.valueOf(5.50))
                            .upc(1234L)
                            .quantityToBrew(200)
                            .build()
            );
            beerRepository.save(
                    Beer.builder()
                            .beerName("Lech")
                            .beerStyle("IPA")
                            .price(BigDecimal.valueOf(5.10))
                            .upc(1254L)
                            .quantityToBrew(100)
                            .build()
            );

            System.out.println("Loaded beers: " + beerRepository.count());
        }
    }
}

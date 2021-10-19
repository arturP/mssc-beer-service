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

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

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
                            .upc(BEER_1_UPC)
                            .quantityToBrew(200)
                            .build()
            );

            beerRepository.save(
                    Beer.builder()
                            .beerName("Lech")
                            .beerStyle("IPA")
                            .price(BigDecimal.valueOf(5.10))
                            .upc(BEER_2_UPC)
                            .quantityToBrew(100)
                            .build()
            );

            beerRepository.save(
                    Beer.builder()
                            .beerName("Zywiec")
                            .beerStyle("Porter")
                            .price(BigDecimal.valueOf(2.99))
                            .upc(BEER_3_UPC)
                            .quantityToBrew(300)
                            .build()
            );

            System.out.println("Loaded beers: " + beerRepository.count());
        }
    }
}

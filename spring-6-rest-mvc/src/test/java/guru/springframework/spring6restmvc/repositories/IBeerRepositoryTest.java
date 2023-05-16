package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.models.BeerStyle;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class IBeerRepositoryTest {

    @Autowired
    IBeerRepository beerRepository;

    @Test
    void testSaveBeerTooLong() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName("My Beer 2323218221232321822123232182212323218221232321822123232182212323218221232321822123232182212323218221232321822123232182212323218221")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("13526782")
                    .price(new BigDecimal("11.99"))
                    .build());
            beerRepository.flush();
        });
    }

    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("13526782")
                .price(new BigDecimal("11.99"))
                .build());
        beerRepository.flush();
        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }
}

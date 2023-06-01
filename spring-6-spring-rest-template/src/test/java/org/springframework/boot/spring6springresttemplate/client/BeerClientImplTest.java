package org.springframework.boot.spring6springresttemplate.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author padmanabhadas
 */

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClientImpl beerClient;

    @Test
    void listBeers() {
        beerClient.listBeers("ALE");
    }

    @Test
    void listBeersNoBeerName() {
        beerClient.listBeers(null);
    }
}

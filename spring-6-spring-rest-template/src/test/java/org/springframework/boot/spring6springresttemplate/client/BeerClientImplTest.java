package org.springframework.boot.spring6springresttemplate.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.spring6springresttemplate.model.BeerDTO;
import org.springframework.boot.spring6springresttemplate.model.BeerStyle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author padmanabhadas
 */

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClientImpl beerClient;

    @Test
    void listBeers() {
        beerClient.listBeers("ALE", null, null, null, null);
    }

    @Test
    void listBeersNoBeerName() {
        beerClient.listBeers(null, null, null, null, null);
    }

    @Test
    void getBeerById() {
        Page<BeerDTO> beerDTOS = beerClient.listBeers();
        BeerDTO beerDTO = beerDTOS.getContent().get(0);
        BeerDTO beerById = beerClient.getBeerById(beerDTO.getId());
        assertNotNull(beerById);
    }

    @Test
    void testCreateBeer() {
        BeerDTO beerDTO = BeerDTO.builder()
                .price(new BigDecimal("10.99"))
                .version(1)
                .beerName("Mango Bobs")
                .beerStyle(BeerStyle.IPA)
                .quantityOnHand(500)
                .upc("12345")
                .build();

        BeerDTO savedDTO = beerClient.createBeer(beerDTO);
        System.out.println("savedDTO: " + savedDTO);
        assertNotNull(savedDTO);
    }

    @Test
    void testUpdateBeer() {
        BeerDTO newDTO = BeerDTO.builder()
                .price(new BigDecimal("10.99"))
                .version(1)
                .beerName("Mango Bobs 2")
                .beerStyle(BeerStyle.IPA)
                .quantityOnHand(500)
                .upc("123245")
                .build();

        BeerDTO beerDTO = beerClient.createBeer(newDTO);

        final String newName = "Mango Bobs 3";
        beerDTO.setBeerName(newName);
        System.out.println("savedDTO: " + beerDTO);
        BeerDTO updatedBeer = beerClient.updateBeer(beerDTO);

        assertEquals(newName, updatedBeer.getBeerName());
    }

    @Test
    void testDeleteBeer() {
        BeerDTO newDTO = BeerDTO.builder()
                .price(new BigDecimal("10.99"))
                .version(1)
                .beerName("Mango Bobs 2")
                .beerStyle(BeerStyle.IPA)
                .quantityOnHand(500)
                .upc("123245")
                .build();

        BeerDTO beerDTO = beerClient.createBeer(newDTO);

        beerClient.deleteBeer(beerDTO.getId());

        assertThrows(HttpClientErrorException.class, () -> {
            // should error
            beerClient.getBeerById(beerDTO.getId());
        });
    }
}

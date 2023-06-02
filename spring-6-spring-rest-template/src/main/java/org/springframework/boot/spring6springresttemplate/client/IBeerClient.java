package org.springframework.boot.spring6springresttemplate.client;

import org.springframework.boot.spring6springresttemplate.model.BeerDTO;
import org.springframework.boot.spring6springresttemplate.model.BeerStyle;
import org.springframework.data.domain.Page;

import java.util.UUID;

/**
 * @author padmanabhadas
 */

public interface IBeerClient {

    Page<BeerDTO> listBeers();

    Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory,
                            Integer pageNumber, Integer pageSize);

    BeerDTO getBeerById(UUID beerId);

    BeerDTO createBeer(BeerDTO beerDTO);

    BeerDTO updateBeer(BeerDTO beerDTO);

    void deleteBeer(UUID id);
}

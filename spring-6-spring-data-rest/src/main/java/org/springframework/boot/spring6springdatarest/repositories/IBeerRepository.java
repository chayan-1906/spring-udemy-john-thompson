package org.springframework.boot.spring6springdatarest.repositories;

import org.springframework.boot.spring6springdatarest.domain.Beer;
import org.springframework.boot.spring6springdatarest.domain.BeerStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

/**
 * @author padmanabhadas
 */

@RepositoryRestResource(path = "beer", collectionResourceRel = "beer")
public interface IBeerRepository extends JpaRepository<Beer, UUID> {
    Page<Beer> findAllByBeerName(String beerName, Pageable pageable);

    Page<Beer> findAllByBeerStyle(BeerStyle beerStyle, Pageable pageable);

    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyle beerStyle, Pageable pageable);

    Beer findByUpc(String upc);
}

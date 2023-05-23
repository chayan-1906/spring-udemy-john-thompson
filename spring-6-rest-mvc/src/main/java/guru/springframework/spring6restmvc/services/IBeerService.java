package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.models.BeerDTO;
import guru.springframework.spring6restmvc.models.BeerStyle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBeerService {

    List<BeerDTO> getAllBeers(String beerName, BeerStyle beerStyle, Boolean showInventory);

    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO saveBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeer(UUID id, BeerDTO beer);

    Boolean deleteBeer(UUID id);

    Optional<BeerDTO> patchBeer(UUID id, BeerDTO beer);
}

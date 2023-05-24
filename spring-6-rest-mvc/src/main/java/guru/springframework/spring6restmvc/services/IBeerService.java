package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.models.BeerDTO;
import guru.springframework.spring6restmvc.models.BeerStyle;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface IBeerService {

    Page<BeerDTO> getAllBeers(String beerName,
                              BeerStyle beerStyle,
                              Boolean showInventory,
                              Integer pageNumber,
                              Integer pageSize);

    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO saveBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeer(UUID id, BeerDTO beer);

    Boolean deleteBeer(UUID id);

    Optional<BeerDTO> patchBeer(UUID id, BeerDTO beer);
}

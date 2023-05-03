package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.models.Beer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBeerService {

    List<Beer> getAllBeers();

    Optional<Beer> getBeerById(UUID id);

    Beer saveBeer(Beer beer);

    Beer updateBeer(UUID id, Beer beer);

    void deleteBeer(UUID id);

    Beer patchBeer(UUID id, Beer beer);
}

package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.models.Beer;

import java.util.List;
import java.util.UUID;

public interface IBeerService {

    List<Beer> getAllBeers();

    Beer getBeerById(UUID id);
}

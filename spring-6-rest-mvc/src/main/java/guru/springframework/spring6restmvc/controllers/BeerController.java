package guru.springframework.spring6restmvc.controllers;

import guru.springframework.spring6restmvc.models.Beer;
import guru.springframework.spring6restmvc.services.IBeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class BeerController {

    private final IBeerService beerService;

    @GetMapping("/beers")
    public List<Beer> getAllBeers() {
        return beerService.getAllBeers();
    }

    @GetMapping("/beer")
    public Beer getBeerById(@RequestParam UUID id) {
        log.debug("Get Beer by Id - in controller");
        return beerService.getBeerById(id);
    }
}

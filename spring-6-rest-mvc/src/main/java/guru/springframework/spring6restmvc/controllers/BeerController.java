package guru.springframework.spring6restmvc.controllers;

import guru.springframework.spring6restmvc.models.Beer;
import guru.springframework.spring6restmvc.services.IBeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Slf4j
@Controller
@AllArgsConstructor
public class BeerController {

    private final IBeerService beerService;

    public Beer getBeerById(UUID id) {
        log.debug("Get Beer by Id - in controller");
        return beerService.getBeerById(id);
    }
}

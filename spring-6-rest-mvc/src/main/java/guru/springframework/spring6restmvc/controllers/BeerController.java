package guru.springframework.spring6restmvc.controllers;

import guru.springframework.spring6restmvc.models.Beer;
import guru.springframework.spring6restmvc.services.IBeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class BeerController {

    private final IBeerService beerService;

    @GetMapping("/beers")
    public ResponseEntity<?> getAllBeers() {
        return new ResponseEntity<>(beerService.getAllBeers(), HttpStatus.OK);
    }

    @GetMapping("/beer")
    public ResponseEntity<?> getBeerById(@RequestParam UUID id) {
        log.debug("Get Beer by Id - in controller");
        return new ResponseEntity<>(beerService.getBeerById(id), HttpStatus.OK);
    }

    @PostMapping("/addBeer")
    public ResponseEntity<?> addBeer(@RequestBody Beer beer) {
        Beer savedBeer = beerService.saveBeer(beer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer?id=" + savedBeer.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/updateBeer")
    public ResponseEntity<?> updateBeer(@RequestBody Beer beer, @RequestParam UUID id) {
        Beer updatedBeer = beerService.updateBeer(id, beer);
        return new ResponseEntity<>(updatedBeer, HttpStatus.OK);
    }
}
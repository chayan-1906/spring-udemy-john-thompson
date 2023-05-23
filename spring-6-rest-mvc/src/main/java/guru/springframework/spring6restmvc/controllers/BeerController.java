package guru.springframework.spring6restmvc.controllers;

import guru.springframework.spring6restmvc.models.BeerDTO;
import guru.springframework.spring6restmvc.models.BeerStyle;
import guru.springframework.spring6restmvc.services.IBeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class BeerController {

    private final IBeerService beerService;

    @GetMapping("/beers")
    public ResponseEntity<?> getAllBeers(@RequestParam(required = false) String beerName,
                                         @RequestParam(required = false) BeerStyle beerStyle,
                                         @RequestParam(required = false) Boolean showInventory) {
        return new ResponseEntity<>(beerService.getAllBeers(beerName, beerStyle, showInventory), HttpStatus.OK);
    }

    /*@GetMapping("/beersByName")
    public List<BeerDTO> getAllBeersByName(@RequestParam String beerName) {
        return beerService.getAllBeers();
    }*/

    @GetMapping("/beer")
    public ResponseEntity<?> getBeerById(@RequestParam UUID id) {
        log.debug("Get Beer by Id - in controller");
        return new ResponseEntity<>(beerService.getBeerById(id).orElseThrow(NotFoundException::new), HttpStatus.OK);
    }

    @PostMapping("/addBeer")
    public ResponseEntity<?> addBeer(@Validated @RequestBody BeerDTO beer) {
        BeerDTO savedBeer = beerService.saveBeer(beer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer?id=" + savedBeer.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/updateBeer")
    public ResponseEntity<?> updateBeer(@Validated @RequestBody BeerDTO beer, @RequestParam UUID id) {
        Optional<BeerDTO> updatedBeer = beerService.updateBeer(id, beer);
        if (updatedBeer.isEmpty()) throw new NotFoundException();
        return new ResponseEntity<>(updatedBeer, HttpStatus.OK);
    }

    @DeleteMapping("/deleteBeer")
    public ResponseEntity<?> deleteBeer(@RequestParam UUID id) {
        if (!beerService.deleteBeer(id)) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>("Beer with " + id + " deleted successfully", HttpStatus.OK);
    }

    @PatchMapping("/patchBeer")
    public ResponseEntity<?> updateBeerPatchById(/*@Validated */@RequestBody BeerDTO beer, @RequestParam UUID id) {
        Optional<BeerDTO> patchedBeer = beerService.patchBeer(id, beer);
        return new ResponseEntity<>(patchedBeer, HttpStatus.OK);
    }
}

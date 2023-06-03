package guru.springframework.spring6restmvc.controllers;

import guru.springframework.spring6restmvc.models.BeerDTO;
import guru.springframework.spring6restmvc.models.BeerStyle;
import guru.springframework.spring6restmvc.services.IBeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
public class BeerController {

    public static final String BEER_PATH = "/api/v1/beer";

    public static final String BEER_PATH_BY_ID = BEER_PATH + "/{id}";

    private final IBeerService beerService;

    @GetMapping(BEER_PATH)
    public Page<BeerDTO> getAllBeers(@RequestParam(required = false) String beerName,
                                     @RequestParam(required = false) BeerStyle beerStyle,
                                     @RequestParam(required = false) Boolean showInventory,
                                     @RequestParam(required = false) Integer pageNumber,
                                     @RequestParam(required = false) Integer pageSize) {
        return beerService.getAllBeers(beerName, beerStyle, showInventory, 1, 25);
    }

    /*@GetMapping("/beersByName")
    public List<BeerDTO> getAllBeersByName(@RequestParam String beerName) {
        return beerService.getAllBeers();
    }*/

    @GetMapping(BEER_PATH_BY_ID)
    public BeerDTO getBeerById(@PathVariable UUID id) {
        log.debug("Get Beer by Id - in controller");
        return beerService.getBeerById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping(BEER_PATH)
    public ResponseEntity<?> addBeer(@Validated @RequestBody BeerDTO beer) {
        BeerDTO savedBeer = beerService.saveBeer(beer);
        HttpHeaders headers = new HttpHeaders();
        System.out.println("savedBeer: " + savedBeer);
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(BEER_PATH_BY_ID)
    public ResponseEntity<?> updateBeer(@Validated @RequestBody BeerDTO beer, @PathVariable UUID id) {
        Optional<BeerDTO> updatedBeer = beerService.updateBeer(id, beer);
        if (updatedBeer.isEmpty()) throw new NotFoundException();
        return new ResponseEntity<>(updatedBeer, HttpStatus.OK);
    }

    @DeleteMapping(BEER_PATH_BY_ID)
    public ResponseEntity<?> deleteBeer(@PathVariable UUID id) {
        if (!beerService.deleteBeer(id)) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>("Beer with " + id + " deleted successfully", HttpStatus.OK);
    }

    @PatchMapping(BEER_PATH_BY_ID)
    public ResponseEntity<?> updateBeerPatchById(/*@Validated */@RequestBody BeerDTO beer, @PathVariable UUID id) {
        Optional<BeerDTO> patchedBeer = beerService.patchBeer(id, beer);
        return new ResponseEntity<>(patchedBeer, HttpStatus.OK);
    }
}

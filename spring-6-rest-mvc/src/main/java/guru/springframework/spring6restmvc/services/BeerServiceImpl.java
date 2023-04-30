package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.models.Beer;
import guru.springframework.spring6restmvc.models.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements IBeerService {

    private final Map<UUID, Beer> beerMap;

    public BeerServiceImpl() {
        beerMap = new HashMap<>();

        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356222")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        Beer beer3 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("12356")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);
    }

    @Override
    public List<Beer> getAllBeers() {
        return new ArrayList<>(beerMap.values());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Beer getBeerById(UUID id) {
        log.debug("Get Beer by Id - in service Id: " + id.toString());
        System.out.println("Get Beer by Id - in service Id: " + id);

        return beerMap.get(id);
    }

    /**
     * @param beer
     * @return
     */
    @Override
    public Beer saveBeer(Beer beer) {
        Beer savedBeer = Beer.builder()
                .id(UUID.randomUUID())
                .beerName(beer.getBeerName())
                .version(beer.getVersion())
                .beerStyle(beer.getBeerStyle())
                .quantityOnHand(beer.getQuantityOnHand())
                .price(beer.getPrice())
                .upc(beer.getUpc())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        beerMap.put(savedBeer.getId(), savedBeer);
        return savedBeer;
    }

    /**
     * @param id
     * @param beer
     * @return
     */
    @Override
    public Beer updateBeer(UUID id, Beer beer) {
        Beer existingBeer = beerMap.get(id);
        existingBeer.setBeerName(beer.getBeerName());
        existingBeer.setVersion(beer.getVersion());
        existingBeer.setBeerStyle(beer.getBeerStyle());
        existingBeer.setUpc(beer.getUpc());
        existingBeer.setQuantityOnHand(beer.getQuantityOnHand());
        existingBeer.setPrice(beer.getPrice());
        existingBeer.setUpdatedDate(LocalDateTime.now());
        beerMap.put(existingBeer.getId(), existingBeer);
        return existingBeer;
    }
}

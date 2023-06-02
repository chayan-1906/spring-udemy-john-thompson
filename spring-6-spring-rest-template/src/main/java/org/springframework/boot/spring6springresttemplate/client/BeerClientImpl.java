package org.springframework.boot.spring6springresttemplate.client;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.spring6springresttemplate.model.BeerDTO;
import org.springframework.boot.spring6springresttemplate.model.BeerDTOPageImpl;
import org.springframework.boot.spring6springresttemplate.model.BeerStyle;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

/**
 * @author padmanabhadas
 */

@RequiredArgsConstructor
@Service
public class BeerClientImpl implements IBeerClient {

    private static final String BASE_URL = "http://localhost:8090";

    private static final String GET_BEER_PATH = "/api/v1/beer";

    private static final String GET_BEER_BY_ID_PATH = "/api/v1/beer/{beerId}";

    private final RestTemplateBuilder restTemplateBuilder;

    /**
     * @return
     */
    @Override
    public Page<BeerDTO> listBeers() {
        return this.listBeers(null, null, null, null, null);
    }

    @Override
    public Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory,
                                   Integer pageNumber, Integer pageSize) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

        if (beerName != null) {
            uriComponentsBuilder.queryParam("beerName", beerName);
        }

        if (beerStyle != null) {
            uriComponentsBuilder.queryParam("beerStyle", beerStyle);
        }

        if (showInventory != null) {
            uriComponentsBuilder.queryParam("showInventory", showInventory);
        }

        if (pageNumber != null) {
            uriComponentsBuilder.queryParam("pageNumber", pageNumber);
        }

        if (pageSize != null) {
            uriComponentsBuilder.queryParam("pageSize", pageSize);
        }

        ResponseEntity<BeerDTOPageImpl> response =
                restTemplate.getForEntity(uriComponentsBuilder.toUriString(), BeerDTOPageImpl.class);

        return response.getBody();
    }

    /**
     * @param beerId
     * @return
     */
    @Override
    public BeerDTO getBeerById(UUID beerId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        return restTemplate.getForObject(GET_BEER_BY_ID_PATH, BeerDTO.class, beerId);
    }

    /**
     * @param beerDTO
     * @return
     */
    @Override
    public BeerDTO createBeer(BeerDTO beerDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        URI uri = restTemplate.postForLocation(GET_BEER_PATH, beerDTO);
        System.out.println("createBeer: " + restTemplate.getForObject(uri.getPath(), BeerDTO.class));
        return restTemplate.getForObject(uri.getPath(), BeerDTO.class);
    }

    /**
     * @param beerDTO
     * @return
     */
    @Override
    public BeerDTO updateBeer(BeerDTO beerDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.put(GET_BEER_BY_ID_PATH, beerDTO, beerDTO.getId());
        return getBeerById(beerDTO.getId());
    }

    /**
     * @param id
     */
    @Override
    public void deleteBeer(UUID id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(GET_BEER_BY_ID_PATH, id);
    }
}

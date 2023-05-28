package org.springframework.boot.spring6springresttemplate.client;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.spring6springresttemplate.model.BeerDTO;
import org.springframework.boot.spring6springresttemplate.model.BeerDTOPageImpl;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author padmanabhadas
 */

@RequiredArgsConstructor
@Service
public class BeerClientImpl implements IBeerClient {

    private static final String BASE_URL = "http://localhost:8090";

    private static final String GET_BEER_PATH = "/api/v1/beer";

    private final RestTemplateBuilder restTemplateBuilder;

    @Override
    public Page<BeerDTO> listBeers() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<BeerDTOPageImpl> stringResponse =
                restTemplate.getForEntity(BASE_URL + GET_BEER_PATH, BeerDTOPageImpl.class);

        return null;
    }
}
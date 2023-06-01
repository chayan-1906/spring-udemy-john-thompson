package org.springframework.boot.spring6springresttemplate.client;

import org.springframework.boot.spring6springresttemplate.model.BeerDTO;
import org.springframework.data.domain.Page;

/**
 * @author padmanabhadas
 */

public interface IBeerClient {

    Page<BeerDTO> listBeers(String beerName);
}

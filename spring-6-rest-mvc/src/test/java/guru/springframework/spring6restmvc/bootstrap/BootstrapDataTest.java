package guru.springframework.spring6restmvc.bootstrap;

import guru.springframework.spring6restmvc.repositories.IBeerRepository;
import guru.springframework.spring6restmvc.repositories.ICustomerRepository;
import guru.springframework.spring6restmvc.services.BeerCSVServiceImpl;
import guru.springframework.spring6restmvc.services.IBeerCSVService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author - padmanabha.das
 */

@DataJpaTest
@Import(BeerCSVServiceImpl.class)
class BootstrapDataTest {

    @Autowired
    IBeerRepository beerRepository;

    @Autowired
    ICustomerRepository customerRepository;

    @Autowired
    IBeerCSVService beerCSVService;

    BootstrapData bootstrapData;

    @BeforeEach
    void setUp() {
        bootstrapData = new BootstrapData(beerRepository, customerRepository, beerCSVService);
    }

    @Test
    void run() throws Exception {
        bootstrapData.run(null);
        assertThat(beerRepository.count()).isEqualTo(2413);
        assertThat(customerRepository.count()).isEqualTo(3);
    }
}

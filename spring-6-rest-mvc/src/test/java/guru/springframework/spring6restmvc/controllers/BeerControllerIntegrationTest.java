package guru.springframework.spring6restmvc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.mappers.BeerMapper;
import guru.springframework.spring6restmvc.models.BeerDTO;
import guru.springframework.spring6restmvc.repositories.IBeerRepository;
import guru.springframework.spring6restmvc.services.IBeerService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author padmanabhadas
 */
@SpringBootTest
class BeerControllerIntegrationTest {

    @Autowired
    BeerController beerController;

    @Autowired
    IBeerRepository beerRepository;

    @Autowired
    IBeerService beerService;

    @Autowired
    BeerMapper beerMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testListBeers() {
        List<BeerDTO> beerDTOs = beerService.getAllBeers();
        assertThat(beerDTOs.size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyList() {
        beerRepository.deleteAll();
        List<BeerDTO> beerDTOs = beerService.getAllBeers();
        assertThat(beerDTOs.size()).isEqualTo(0);
    }

    @Test
    void testGetById() {
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = beerService.getBeerById(beer.getId()).get();
        assertThat(beerDTO).isNotNull();
    }

    @Test
    void testBeerIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            beerController.getBeerById(UUID.randomUUID());
        });
    }

    /*@Rollback
    @Transactional
    @Test
    void testSaveNewBeer() {
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("New Beer")
                .build();
        ResponseEntity<?> responseEntity = beerController.addBeer(beerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String location = responseEntity.getHeaders().getLocation().getPath();
        String locationUUID = location.substring(location.indexOf("?id="));
        UUID savedUUID = UUID.fromString(locationUUID);

        Beer beer = beerRepository.findById(savedUUID).get();
        assertThat(beer).isNotNull();
    }*/

    @Rollback
    @Transactional
    @Test
    void updateExistBeer() {
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = beerMapper.beerToBeerDTO(beer);
        beerDTO.setId(null);
        beerDTO.setVersion(null);
        final String beerName = "UPDATED";
        beerDTO.setBeerName(beerName);

        ResponseEntity<?> responseEntity = beerController.updateBeer(beerDTO, beer.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));

        Beer updatedBeer = beerRepository.findById(beer.getId()).get();
        assertThat(updatedBeer.getBeerName()).isEqualTo(beerName);
    }

    @Test
    void testUpdateNotFound() {
        assertThrows(NotFoundException.class, () -> {
            beerController.updateBeer(BeerDTO.builder().build(), UUID.randomUUID());
        });
    }

    @Rollback
    @Transactional
    @Test
    void testDeleteByIdFound() {
        Beer beer = beerRepository.findAll().get(0);
        ResponseEntity<?> responseEntity = beerController.deleteBeer(beer.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(beerRepository.findById(beer.getId()).isEmpty());
    }

    @Test
    void testDeleteNotFound() {
        assertThrows(NotFoundException.class, () -> {
            beerController.deleteBeer(UUID.randomUUID());
        });
    }

    @Test
    void testPatchBeerBadName() throws Exception {
        Beer beer = beerRepository.findAll().get(0);

        Map<String, Object> beerMap = new HashMap<>();
        beerMap.put("beerName", "New Name 1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");

        MvcResult mvcResult = mockMvc.perform(patch("/api/v1/patchBeer?id=" + beer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerMap)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()", is(1)))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}

package guru.springframework.spring6restmvc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.mappers.BeerMapper;
import guru.springframework.spring6restmvc.models.BeerDTO;
import guru.springframework.spring6restmvc.models.BeerStyle;
import guru.springframework.spring6restmvc.repositories.IBeerRepository;
import guru.springframework.spring6restmvc.services.IBeerService;
import jakarta.transaction.Transactional;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static guru.springframework.spring6restmvc.controllers.BeerControllerTest.PASSWORD;
import static guru.springframework.spring6restmvc.controllers.BeerControllerTest.USERNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    void testListBeers() {
        Page<BeerDTO> beerDTOs = beerService.getAllBeers(null, null, false, 1, 2413);
        assertThat(beerDTOs.getContent().size()).isEqualTo(1000);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyList() {
        beerRepository.deleteAll();
        Page<BeerDTO> beerDTOs = beerService.getAllBeers(null, null, false, 1, 25);
        assertThat(beerDTOs.getContent().size()).isEqualTo(0);
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

    @Test
    void testListBeerByName() throws Exception {
        mockMvc.perform(get("/api/v1/beers")
                        .with(httpBasic(USERNAME, PASSWORD))
                        .queryParam("beerName", "IPA")
                        .queryParam("pageSize", "800"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(336)));
    }

    @Test
    void testListBeerByStyle() throws Exception {
        mockMvc.perform(get("/api/v1/beers")
                        .with(httpBasic(USERNAME, PASSWORD))
                        .queryParam("beerStyle", BeerStyle.IPA.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(548)));
    }

    @Test
    void testListBeerByStyleAndNameShowInventoryTrue() throws Exception {
        mockMvc.perform(get("/api/v1/beers")
                        .with(httpBasic(USERNAME, PASSWORD))
                        .queryParam("beerName", "IPA")
                        .queryParam("beerStyle", BeerStyle.IPA.name())
                        .queryParam("showInventory", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(310)))
                .andExpect(jsonPath("$.content.[0].quantityOnHand").value(IsNull.notNullValue()));
    }

    @Test
    void testListBeerByStyleAndNameShowInventoryTruePage2() throws Exception {
        mockMvc.perform(get("/api/v1/beers")
                        .with(httpBasic(USERNAME, PASSWORD))
                        .queryParam("beerName", "IPA")
                        .queryParam("beerStyle", BeerStyle.IPA.name())
                        .queryParam("showInventory", "true")
                        .queryParam("pageNumber", "2")
                        .queryParam("pageSize", "50"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(310)))
                .andExpect(jsonPath("$.content.[0].quantityOnHand").value(IsNull.notNullValue()));
    }

    @Test
    void testListBeerByStyleAndNameShowInventoryFalse() throws Exception {
        mockMvc.perform(get("/api/v1/beers")
                        .with(httpBasic(USERNAME, PASSWORD))
                        .queryParam("beerName", "IPA")
                        .queryParam("beerStyle", BeerStyle.IPA.name())
                        .queryParam("showInventory", "false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(310)))
                .andExpect(jsonPath("$.content.[0].quantityOnHand").value(IsNull.nullValue()));
    }

    @Test
    void testListBeerByStyleAndName() throws Exception {
        mockMvc.perform(get("/api/v1/beers")
                        .with(httpBasic(USERNAME, PASSWORD))
                        .queryParam("beerName", "IPA")
                        .queryParam("beerStyle", BeerStyle.IPA.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(310)));
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

    @Disabled
    @Test
    void testUpdateBeerBadVersion() throws Exception {
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = beerMapper.beerToBeerDTO(beer);
        beerDTO.setBeerName("Updated Name");
        MvcResult result = mockMvc.perform(put("/api/v1/updateBeer?id=" + beer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerDTO)))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());

        beerDTO.setBeerName("Updated Name 2");

        MvcResult result2 = mockMvc.perform(put("/api/v1/updateBeer?id=" + beer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerDTO)))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result2.getResponse().getStatus());
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
                        .with(httpBasic(USERNAME, PASSWORD))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerMap)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()", is(1)))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testNoAuth() throws Exception {
        mockMvc.perform(get("/api/v1/beers")
//                        .with(httpBasic(USERNAME, PASSWORD))
                        .queryParam("beerStyle", BeerStyle.IPA.name()))
                .andExpect(status().isUnauthorized());
    }
}

package guru.springframework.spring6restmvc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.spring6restmvc.models.BeerDTO;
import guru.springframework.spring6restmvc.services.BeerServiceImpl;
import guru.springframework.spring6restmvc.services.IBeerService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    IBeerService beerService;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<BeerDTO> beerDTOArgumentCaptor;

    BeerServiceImpl beerServiceImpl = new BeerServiceImpl();

    @BeforeEach
    void setUp() {
        beerServiceImpl = new BeerServiceImpl();
    }

    @Test
    void getBeerByIdNotFound() throws Exception {
        given(beerService.getBeerById(any(UUID.class))).willReturn(Optional.empty());
        mockMvc.perform(get("/api/v1/beer?id=" + UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateBeerNullBeerName() throws Exception {
        BeerDTO beerDTO = BeerDTO.builder().build();
        given(beerService.saveBeer(any(BeerDTO.class))).willReturn(beerServiceImpl.getAllBeers().get(1));
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/addBeer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()", is(6)))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testListBeers() throws Exception {
        given(beerService.getAllBeers()).willReturn(beerServiceImpl.getAllBeers());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beers")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", Matchers.is(3)));
    }

    @Test
    void getBeerById() throws Exception {
        BeerDTO testBeer = beerServiceImpl.getAllBeers().get(0);
        given(beerService.getBeerById(testBeer.getId())).willReturn(Optional.of(testBeer));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer?id=" + testBeer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Is.is(testBeer.getId().toString())))
                .andExpect(jsonPath("$.beerName", Is.is(testBeer.getBeerName())));
    }

    @Test
    void testCreateNewBeer() throws Exception {
        BeerDTO beer = beerServiceImpl.getAllBeers().get(0);
        beer.setVersion(null);
        beer.setId(null);
        given(beerService.saveBeer(any(BeerDTO.class)))
                .willReturn(beerServiceImpl.getAllBeers().get(1));
        mockMvc.perform(post("/api/v1/addBeer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void testUpdateBeer() throws Exception {
        BeerDTO beerDTO = beerServiceImpl.getAllBeers().get(0);

        given(beerService.updateBeer(any(), any())).willReturn(Optional.of(beerDTO));

        mockMvc.perform(put("/api/v1/updateBeer?id=" + beerDTO.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(beerDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateBeerBlankName() throws Exception {
        BeerDTO beerDTO = beerServiceImpl.getAllBeers().get(0);
        beerDTO.setBeerName("");
        given(beerService.updateBeer(any(), any())).willReturn(Optional.of(beerDTO));

        mockMvc.perform(put("/api/v1/updateBeer?id=" + beerDTO.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(beerDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()", is(1)));
    }

    @Test
    void testDeleteBeer() throws Exception {
        BeerDTO beerDTO = beerServiceImpl.getAllBeers().get(0);

        given(beerService.deleteBeer(any())).willReturn(true);

        mockMvc.perform(delete("/api/v1/deleteBeer?id=" + beerDTO.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(beerService).deleteBeer(uuidArgumentCaptor.capture());

        assertThat(beerDTO.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void testPatchBeer() throws Exception {
        BeerDTO beerDTO = beerServiceImpl.getAllBeers().get(0);

        Map<String, Object> beerMap = new HashMap<>();
        beerMap.put("beerName", "New Name");

        mockMvc.perform(patch("/api/v1/patchBeer?id=" + beerDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerMap)))
                .andExpect(status().isOk());

        verify(beerService).patchBeer(uuidArgumentCaptor.capture(), beerDTOArgumentCaptor.capture());

        assertThat(beerDTO.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(beerMap.get("beerName")).isEqualTo(beerDTOArgumentCaptor.getValue().getBeerName());
    }
}

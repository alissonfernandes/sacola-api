package br.com.sacola.resource;

import br.com.sacola.builder.RestauranteDTOBuilder;
import br.com.sacola.exception.RestaurantNotFoundException;
import br.com.sacola.resource.dto.ProdutoDTO;
import br.com.sacola.resource.dto.RestauranteDTO;
import br.com.sacola.service.RestauranteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import static org.mockito.Mockito.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("teste restaurante endpoints resurce")
public class RestauranteResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    RestauranteService restauranteService;

    @InjectMocks
    RestauranteResource restauranteResource;

    private final Long VALID_ID = 1L;
    private final Long INVALID_ID = 0L;
    private final String RESTAURANTE_API_URL_PATH = "/v1/ifood/restaurante";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(restauranteResource)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    @DisplayName("when method HTTP POST 'addNewRestaurante' is called then return a restaurante")
    void httpPostAddNewRestaurante() throws Exception {
        RestauranteDTO restauranteDTO = RestauranteDTOBuilder.builder().build().toRestauranteDTO();
        ProdutoDTO produtoDTO = restauranteDTO.getCardapio().get(0);

        when(restauranteService.addNewRestaurant(restauranteDTO)).thenReturn(restauranteDTO);

        mockMvc.perform(post(RESTAURANTE_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(restauranteDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(restauranteDTO.getId().intValue())))
                .andExpect(jsonPath("$.nome", is(restauranteDTO.getNome())))
                .andExpect(jsonPath("$.cardapio[0].id").value(equalTo(produtoDTO.getId().intValue())))
                .andExpect(jsonPath("$.cardapio[0].nome").value(equalTo(produtoDTO.getNome())))
                .andExpect(jsonPath("$.cardapio[0].valorUnitario").value(equalTo(produtoDTO.getValorUnitario())))
                .andExpect(jsonPath("$.cardapio[0].disponivel").value(equalTo(produtoDTO.getDisponivel())))
                .andExpect(jsonPath("$.endereco.cep").value(equalTo(restauranteDTO.getEndereco().getCep())))
                .andExpect(jsonPath("$.endereco.cidade").value(equalTo(restauranteDTO.getEndereco().getCidade())))
                .andExpect(jsonPath("$.endereco.estadoUf").value(equalTo(restauranteDTO.getEndereco().getEstadoUf())))
                .andExpect(jsonPath("$.endereco.bairro").value(equalTo(restauranteDTO.getEndereco().getBairro())))
                .andExpect(jsonPath("$.endereco.numero").value(equalTo(restauranteDTO.getEndereco().getNumero())))
                .andExpect(jsonPath("$.endereco.complemento").value(equalTo(restauranteDTO.getEndereco().getComplemento())));

    }

    @Test
    @DisplayName("when method HTTP PUT 'updateRestaurante' is called with valid id then return a restaurante")
    void httpPutUpdateRestaurante() throws Exception {
        RestauranteDTO restauranteDTO = RestauranteDTOBuilder.builder().build().toRestauranteDTO();
        ProdutoDTO produtoDTO = restauranteDTO.getCardapio().get(0);

        when(restauranteService.updateRestaurant(restauranteDTO.getId(), restauranteDTO)).thenReturn(restauranteDTO);

        mockMvc.perform(put(RESTAURANTE_API_URL_PATH + "/" + VALID_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(restauranteDTO)))
                .andExpect(status().isUpgradeRequired())
                .andExpect(jsonPath("$.id", is(restauranteDTO.getId().intValue())))
                .andExpect(jsonPath("$.nome", is(restauranteDTO.getNome())))
                .andExpect(jsonPath("$.cardapio[0].id").value(equalTo(produtoDTO.getId().intValue())))
                .andExpect(jsonPath("$.cardapio[0].nome").value(equalTo(produtoDTO.getNome())))
                .andExpect(jsonPath("$.cardapio[0].valorUnitario").value(equalTo(produtoDTO.getValorUnitario())))
                .andExpect(jsonPath("$.cardapio[0].disponivel").value(equalTo(produtoDTO.getDisponivel())))
                .andExpect(jsonPath("$.endereco.cep").value(equalTo(restauranteDTO.getEndereco().getCep())))
                .andExpect(jsonPath("$.endereco.cidade").value(equalTo(restauranteDTO.getEndereco().getCidade())))
                .andExpect(jsonPath("$.endereco.estadoUf").value(equalTo(restauranteDTO.getEndereco().getEstadoUf())))
                .andExpect(jsonPath("$.endereco.bairro").value(equalTo(restauranteDTO.getEndereco().getBairro())))
                .andExpect(jsonPath("$.endereco.numero").value(equalTo(restauranteDTO.getEndereco().getNumero())))
                .andExpect(jsonPath("$.endereco.complemento").value(equalTo(restauranteDTO.getEndereco().getComplemento())));
    }

    @Test
    @DisplayName("when method HTTP PUT 'updateRestaurante' is called with invalid id then return exception")
    void httpPutUpdateRestauranteWithInvalidId() throws Exception {
        RestauranteDTO restauranteDTO = RestauranteDTOBuilder.builder().build().toRestauranteDTO();

        when(restauranteService.updateRestaurant(INVALID_ID, restauranteDTO)).thenThrow(new RestaurantNotFoundException(INVALID_ID));

        mockMvc.perform(put(RESTAURANTE_API_URL_PATH + "/" + INVALID_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(restauranteDTO)))
                .andExpect(status().isNotFound());

    }
}

package br.com.sacola.resource;

import br.com.sacola.enumeration.FormaPagamento;
import br.com.sacola.exception.*;
import br.com.sacola.resource.dto.ItemDTO;
import br.com.sacola.service.SacolaServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.core.Is.is;

import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("test sacola resource")
public class SacolaResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    SacolaServiceImpl sacolaService;

    @InjectMocks
    SacolaResource sacolaResource;

    private final Long INVALID_ID = 0L;
    private final Long VALID_ID = 1L;
    private final String SACOLA_API_URL_PATH = "/v1/ifood/sacolas";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(sacolaResource)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    @DisplayName("when method HTTP GET 'verSacola' is called with ID invalid then return a exception")
    void httpGetVerSacolaWithInvalidId() throws Exception {
        when(sacolaService.verSacola(INVALID_ID)).thenThrow(BagNotFoundException.class);

        mockMvc.perform(get(SACOLA_API_URL_PATH + "/" + INVALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("when method HTTP PATCH 'fecharSacola' is called with ID invalid then return a exception")
    void httpPatchFecharSacolaWithInvalidId() throws Exception {
        when(sacolaService.fecharSacola(INVALID_ID, 1)).thenThrow(BagNotFoundException.class);

        mockMvc.perform(patch(SACOLA_API_URL_PATH + "/fecharSacola/" + INVALID_ID +"?formaPagamento=1")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("when method HTTP PATCH 'fecharSacola' is called and bag is empty then return a exception")
    void httpPatchFecharSacolaWhenBagIsEmpty() throws Exception {
        when(sacolaService.fecharSacola(VALID_ID, 1)).thenThrow(BagIsEmptyException.class);
        mockMvc.perform(patch(SACOLA_API_URL_PATH + "/fecharSacola/" + VALID_ID +"?formaPagamento=1")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotAcceptable());
    }

    @Test
    @DisplayName("when method HTTP POST 'incluirItemNaSacola' is called with JSON 'sacolaId' invalid then return an exception")
    void httpPostIncluirItemNaSacolaWithSacolaIdInvalid() throws Exception {
        ItemDTO itemDTO = new ItemDTO(1L, 1, INVALID_ID);
        when(sacolaService.incluirItemNaSacola(itemDTO)).thenThrow(BagNotFoundException.class);
        mockMvc.perform(post(SACOLA_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("when method HTTP POST 'incluirItemNaSacola' is called and JSON 'sacolaId' is closed then return an exception")
    void httpPostIncluirItemNaSacolaWhenBagClosed() throws Exception {
        ItemDTO itemDTO = new ItemDTO(1L, 1, VALID_ID);
        when(sacolaService.incluirItemNaSacola(itemDTO)).thenThrow(BagIsClosedException.class);
        mockMvc.perform(post(SACOLA_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemDTO)))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    @DisplayName("when HTTP POST 'incluirItemNaSacola' is called and product from JSON 'produtoId' not found then return an exception")
    void httpPostIncluirItemNaSacolaProductNotFound() throws Exception {
        ItemDTO itemDTO = new ItemDTO(0L, 1, VALID_ID);
        when(sacolaService.incluirItemNaSacola(itemDTO)).thenThrow(ProductNotFoundException.class);
        mockMvc.perform(post(SACOLA_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("when HTTP POST 'incluirItemNaSacola' is called and item is different restaurant then return an exception")
    void httpPostIncluirItemNaSacolaProducDifferent() throws Exception {
        ItemDTO itemDTO = new ItemDTO(1L, 1, VALID_ID);
        when(sacolaService.incluirItemNaSacola(itemDTO)).thenThrow(ProductDifferentException.class);
        mockMvc.perform(post(SACOLA_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemDTO)))
                .andExpect(status().isNotAcceptable());
    }
}

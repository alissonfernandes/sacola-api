package br.com.sacola.mapper;

import br.com.sacola.builder.RestauranteDTOBuilder;
import br.com.sacola.model.Endereco;
import br.com.sacola.model.Produto;
import br.com.sacola.model.Restaurante;
import br.com.sacola.resource.dto.EnderecoDTO;
import br.com.sacola.resource.dto.ProdutoDTO;
import br.com.sacola.resource.dto.RestauranteDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Restaurante mapper test")
public class RestauranteMapperTest {

    private RestauranteMapper restauranteMapper = new RestauranteMapper();

    @Test
    @DisplayName("restauranteDTO to restauranteModel")
    void restauranteToModelTest() {
        RestauranteDTO restauranteDTO = RestauranteDTOBuilder.builder().build().toRestauranteDTO();
        Restaurante restaurante = restauranteMapper.restauranteToModel(restauranteDTO);

        assertEquals(restauranteDTO.getId(), restaurante.getId());
        assertEquals(restauranteDTO.getNome(), restaurante.getNome());

        ProdutoDTO produtoDTO = restauranteDTO.getCardapio().get(0);
        Produto produto = restaurante.getCardapio().get(0);

        assertEquals(produtoDTO.getId(), produto.getId());
        assertEquals(produtoDTO.getNome(), produto.getNome());
        assertEquals(produtoDTO.getValorUnitario(), produto.getValorUnitario());
        assertEquals(produtoDTO.getDisponivel(), produto.getDisponivel());

        EnderecoDTO enderecoDTO = restauranteDTO.getEndereco();
        Endereco endereco = restaurante.getEndereco();

        assertEquals(enderecoDTO.getCep(), endereco.getCep());
        assertEquals(enderecoDTO.getCidade(), endereco.getCidade());
        assertEquals(enderecoDTO.getBairro(), endereco.getBairro());
        assertEquals(enderecoDTO.getEstadoUf(), endereco.getEstadoUf());
        assertEquals(enderecoDTO.getNumero(), endereco.getNumero());
        assertEquals(enderecoDTO.getComplemento(), endereco.getComplemento());
    }
}

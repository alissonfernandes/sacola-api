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

    @Test
    @DisplayName("restauranteModel to restauranteDTO")
    void restauranteToDTO() {
        RestauranteDTO restauranteDTOToRestaurante = RestauranteDTOBuilder.builder().build().toRestauranteDTO();
        Restaurante restaurante = restauranteMapper.restauranteToModel(restauranteDTOToRestaurante);

        RestauranteDTO restauranteDTO = restauranteMapper.restauranteToDTO(restaurante);

        assertEquals(restaurante.getId(), restauranteDTO.getId());
        assertEquals(restaurante.getNome(), restauranteDTO.getNome());

        Produto produto = restaurante.getCardapio().get(0);
        ProdutoDTO produtoDTO = restauranteDTO.getCardapio().get(0);

        assertEquals(produto.getId(), produtoDTO.getId());
        assertEquals(produto.getNome(), produtoDTO.getNome());
        assertEquals(produto.getValorUnitario(), produtoDTO.getValorUnitario());
        assertEquals(produto.getDisponivel(), produtoDTO.getDisponivel());

        Endereco endereco = restaurante.getEndereco();
        EnderecoDTO enderecoDTO = restauranteDTO.getEndereco();

        assertEquals(endereco.getCep(), enderecoDTO.getCep());
        assertEquals(endereco.getCidade(), enderecoDTO.getCidade());
        assertEquals(endereco.getEstadoUf(), enderecoDTO.getEstadoUf());
        assertEquals(endereco.getBairro(), enderecoDTO.getBairro());
        assertEquals(endereco.getNumero(), enderecoDTO.getNumero());
        assertEquals(endereco.getComplemento(), enderecoDTO.getComplemento());
    }
}

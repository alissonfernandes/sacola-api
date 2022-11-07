package br.com.sacola.builder;

import br.com.sacola.resource.dto.EnderecoDTO;
import br.com.sacola.resource.dto.ProdutoDTO;
import br.com.sacola.resource.dto.RestauranteDTO;
import lombok.Builder;

import java.util.List;

@Builder
public class RestauranteDTOBuilder {

    public RestauranteDTO toRestauranteDTO() {
        return RestauranteDTO.builder()
                .id(1L)
                .nome("nomeTest")
                .cardapio(this.getCardapio())
                .endereco(EnderecoDTOBuilder.builder().build().toEnderecoDTO())
                .build();
    }

    private List<ProdutoDTO> getCardapio() {
        return List.of(ProdutoDTOBuilder.builder().build().toProdutoDTO());
    }
}

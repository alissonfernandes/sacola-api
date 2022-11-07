package br.com.sacola.builder;

import br.com.sacola.resource.dto.ProdutoDTO;
import lombok.Builder;

@Builder
public class ProdutoDTOBuilder {

    public ProdutoDTO toProdutoDTO() {
        return ProdutoDTO.builder()
                .id(1L)
                .nome("nomeTest")
                .valorUnitario(50.50d)
                .disponivel(true)
                .build();
    }
}

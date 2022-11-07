package br.com.sacola.builder;

import br.com.sacola.resource.dto.EnderecoDTO;
import lombok.Builder;

@Builder
public class EnderecoDTOBuilder {

    public EnderecoDTO toEnderecoDTO() {
        return EnderecoDTO.builder()
                .cep("00000-000")
                .cidade("cidadeTest")
                .estadoUf("estadoUfTest")
                .bairro("bairroTest")
                .numero("1")
                .complemento("complementoTest")
                .build();
    }
}

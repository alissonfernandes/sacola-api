package br.com.sacola.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RestauranteDTO {

    private Long id;
    private String nome;
    private List<ProdutoDTO> cardapio;
    @Embedded
    private EnderecoDTO endereco;
}

package br.com.sacola.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Embeddable
public class EnderecoDTO {
    private String cep;
    private String cidade;
    private String estadoUf;
    private String bairro;
    private String numero;
    private String complemento;
}

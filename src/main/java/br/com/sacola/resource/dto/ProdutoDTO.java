package br.com.sacola.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProdutoDTO {
    private Long id;
    private String nome;
    private double valorUnitario;
    private Boolean disponivel;
}

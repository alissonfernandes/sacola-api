package br.com.sacola.mapper;

import br.com.sacola.model.Endereco;
import br.com.sacola.model.Produto;
import br.com.sacola.model.Restaurante;
import br.com.sacola.resource.dto.EnderecoDTO;
import br.com.sacola.resource.dto.ProdutoDTO;
import br.com.sacola.resource.dto.RestauranteDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RestauranteMapper {

    public Restaurante restauranteToModel(RestauranteDTO restauranteDTO) {
        return Restaurante.builder()
                .id(restauranteDTO.getId())
                .nome(restauranteDTO.getNome())
                .cardapio(this.restauranteToModelListProduto(restauranteDTO.getCardapio()))
                .endereco(this.enderecoDTOToModel(restauranteDTO.getEndereco())).build();
    }

    private List<Produto> restauranteToModelListProduto(List<ProdutoDTO> listProdutoDTO) {
        return listProdutoDTO.stream().map(produtoDTO ->
        {
            Produto produto = Produto.builder()
                    .id(produtoDTO.getId())
                    .nome(produtoDTO.getNome())
                    .valorUnitario(produtoDTO.getValorUnitario())
                    .disponivel(produtoDTO.getDisponivel()).build();
            return produto;
        }
        ).collect(Collectors.toList());
    }

    private Endereco enderecoDTOToModel(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .cep(enderecoDTO.getCep())
                .cidade(enderecoDTO.getCidade())
                .estadoUf(enderecoDTO.getEstadoUf())
                .bairro(enderecoDTO.getBairro())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento()).build();
    }
}

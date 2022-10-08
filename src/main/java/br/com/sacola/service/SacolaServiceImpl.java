package br.com.sacola.service;

import br.com.sacola.enumeration.FormaPagamento;
import br.com.sacola.model.Item;
import br.com.sacola.model.Restaurante;
import br.com.sacola.model.Sacola;
import br.com.sacola.repository.ItemRepository;
import br.com.sacola.repository.ProdutoRepository;
import br.com.sacola.repository.SacolaRepository;
import br.com.sacola.resource.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SacolaServiceImpl implements SacolaService {

    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;

    @Override
    public Item incluirItemNaSacola(ItemDTO itemDTO) {
        Sacola sacola = this.verSacola(itemDTO.getSacolaId());

        if (sacola.isFechada()) throw new RuntimeException("Esta sacola está fechada");

        Item itemToInsert = Item.builder()
                .quantidade(itemDTO.getQuantidade())
                .sacola(sacola)
                .produto(produtoRepository.findById(itemDTO.getProdutoId()).orElseThrow(() -> {throw new RuntimeException("Esse produto não existe!");}))
                .build();


        List<Item> itensSacola = sacola.getItems();

        if (itensSacola.isEmpty()) itensSacola.add(itemToInsert);
        else {

            Restaurante restauranteAtual = itensSacola.get(0).getProduto().getRestaurante();
            Restaurante restauranteItemAdicionar = itemToInsert.getProduto().getRestaurante();

            if (restauranteAtual.equals(restauranteItemAdicionar)) itensSacola.add(itemToInsert);
            else throw new RuntimeException("Não é possivel adicionar produtos de restaurante diferentes.");
        }

        sacolaRepository.save(sacola);
        return itemRepository.save(itemToInsert);
    }

    @Override
    public Sacola verSacola(Long id) {
        return sacolaRepository.findById(id)
                .orElseThrow(
                () -> {throw new RuntimeException("Essa Sacola não existe!");}
                );
    }

    @Override
    public Sacola fecharSacola(Long id, int formaPagamento) {
        Sacola sacola = this.verSacola(id);

        if (sacola.getItems().isEmpty()) throw new RuntimeException("Inclua itens na sacola!");

        FormaPagamento fPagamento = formaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;

        sacola.setFormaPagamento(fPagamento);
        sacola.setFechada(true);

        return sacolaRepository.save(sacola);
    }
}

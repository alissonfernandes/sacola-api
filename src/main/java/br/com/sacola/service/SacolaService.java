package br.com.sacola.service;

import br.com.sacola.exception.BagIsClosedException;
import br.com.sacola.model.Item;
import br.com.sacola.model.Sacola;
import br.com.sacola.resource.dto.ItemDTO;

public interface SacolaService {

    Item incluirItemNaSacola(ItemDTO itemDTO) throws BagIsClosedException;
    Sacola verSacola(Long id);
    Sacola fecharSacola(Long id, int formaPagamento);
}

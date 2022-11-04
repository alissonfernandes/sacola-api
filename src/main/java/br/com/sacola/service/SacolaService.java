package br.com.sacola.service;

import br.com.sacola.exception.*;
import br.com.sacola.model.Item;
import br.com.sacola.model.Sacola;
import br.com.sacola.resource.dto.ItemDTO;

public interface SacolaService {

    Item incluirItemNaSacola(ItemDTO itemDTO) throws BagIsClosedException, BagNotFoundException, ProductDifferentException, ProductNotFoundException;
    Sacola verSacola(Long id) throws BagNotFoundException;
    Sacola fecharSacola(Long id, int formaPagamento) throws BagNotFoundException, BagIsEmptyException;
}

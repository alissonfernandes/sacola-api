package br.com.sacola.service;

import br.com.sacola.resource.dto.RestauranteDTO;

public interface RestauranteService {

    RestauranteDTO addNewRestaurant(RestauranteDTO restauranteDTO);
    RestauranteDTO updateRestaurant(RestauranteDTO restauranteDTO);
    void removeRestaurant(Long id);
}

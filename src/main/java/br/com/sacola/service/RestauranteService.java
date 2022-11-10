package br.com.sacola.service;

import br.com.sacola.exception.RestaurantNotFoundException;
import br.com.sacola.resource.dto.RestauranteDTO;

public interface RestauranteService {

    RestauranteDTO addNewRestaurant(RestauranteDTO restauranteDTO);
    RestauranteDTO updateRestaurant(Long id, RestauranteDTO restauranteDTO) throws RestaurantNotFoundException;
    void removeRestaurant(Long id) throws RestaurantNotFoundException;
}

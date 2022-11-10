package br.com.sacola.service;

import br.com.sacola.exception.RestaurantNotFoundException;
import br.com.sacola.mapper.RestauranteMapper;
import br.com.sacola.model.Restaurante;
import br.com.sacola.repository.RestauranteRepository;
import br.com.sacola.resource.dto.RestauranteDTO;

public class RestauranteServiceImpl implements RestauranteService{

    private RestauranteRepository restauranteRepository;

    private RestauranteMapper restauranteMapper = new RestauranteMapper();

    @Override
    public RestauranteDTO addNewRestaurant(RestauranteDTO restauranteDTO) {
        Restaurante restauranteToCreate = restauranteMapper.restauranteToModel(restauranteDTO);
        Restaurante restauranteSaved = restauranteRepository.save(restauranteToCreate);
        return restauranteMapper.restauranteToDTO(restauranteSaved);
    }

    @Override
    public RestauranteDTO updateRestaurant(Long id, RestauranteDTO restauranteDTO) throws RestaurantNotFoundException {
        Restaurante restaurante = verifyExists(id);
        restauranteDTO.setId(id);
        Restaurante restauranteToSave = restauranteMapper.restauranteToModel(restauranteDTO);
        Restaurante restauranteSaved = restauranteRepository.save(restauranteToSave);
        return restauranteMapper.restauranteToDTO(restauranteSaved);
    }

    @Override
    public void removeRestaurant(Long id) throws RestaurantNotFoundException {
        Restaurante restaurante = this.verifyExists(id);
        restauranteRepository.deleteById(id);
    }

    private Restaurante verifyExists(Long id) throws RestaurantNotFoundException {
        return restauranteRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
    }
}

package br.com.sacola.service;

import br.com.sacola.mapper.RestauranteMapper;
import br.com.sacola.model.Produto;
import br.com.sacola.model.Restaurante;
import br.com.sacola.repository.RestauranteRepository;
import br.com.sacola.resource.dto.ProdutoDTO;
import br.com.sacola.resource.dto.RestauranteDTO;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

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
    public RestauranteDTO updateRestaurant(RestauranteDTO restauranteDTO) {

        return null;
    }

    @Override
    public void removeRestaurant(Long id) {

    }

}

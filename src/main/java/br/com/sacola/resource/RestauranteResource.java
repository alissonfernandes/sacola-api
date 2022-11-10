package br.com.sacola.resource;

import br.com.sacola.exception.RestaurantNotFoundException;
import br.com.sacola.resource.dto.RestauranteDTO;
import br.com.sacola.service.RestauranteService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/ifood/restaurante")
@RequiredArgsConstructor
@Api(value = "/v1/ifood/restaurante")
public class RestauranteResource {

    private RestauranteService restauranteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDTO addNewRestaurante(@RequestBody RestauranteDTO restauranteDTO){
        return restauranteService.addNewRestaurant(restauranteDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
    public RestauranteDTO updateRestaurante(@PathVariable Long id, @RequestBody RestauranteDTO restauranteDTO) throws RestaurantNotFoundException {
        return restauranteService.updateRestaurant(id, restauranteDTO);
    }
}

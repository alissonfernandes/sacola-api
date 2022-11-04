package br.com.sacola.resource;

import br.com.sacola.exception.*;
import br.com.sacola.model.Item;
import br.com.sacola.model.Sacola;
import br.com.sacola.resource.dto.ItemDTO;
import br.com.sacola.service.SacolaService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/ifood/sacolas")
@RequiredArgsConstructor
@Api(value = "/v1/ifood/sacolas")
public class SacolaResource {

    private final SacolaService sacolaService;

    @PostMapping
    public Item incluirItemNaSacola(@RequestBody ItemDTO itemDTO) throws BagIsClosedException, BagNotFoundException, ProductDifferentException, ProductNotFoundException {
        return sacolaService.incluirItemNaSacola(itemDTO);
    }

    @GetMapping("/{id}")
    public Sacola verSacola(@PathVariable Long id) throws BagNotFoundException {
        return sacolaService.verSacola(id);
    }

    @PatchMapping("/fecharSacola/{sacolaId}")
    public Sacola fecharSacola(@PathVariable("sacolaId") Long sacolaId, @RequestParam("formaPagamento") int formaPagamento) throws BagNotFoundException, BagIsEmptyException {
        return sacolaService.fecharSacola(sacolaId, formaPagamento);
    }
}

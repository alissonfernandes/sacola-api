package br.com.sacola.resource;

import br.com.sacola.model.Item;
import br.com.sacola.model.Sacola;
import br.com.sacola.resource.dto.ItemDTO;
import br.com.sacola.service.SacolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/ifood/sacolas")
@RequiredArgsConstructor
public class SacolaResource {

    private final SacolaService sacolaService;

    @PostMapping
    public Item incluirItemNaSacola(@RequestBody ItemDTO itemDTO) {
        return sacolaService.incluirItemNaSacola(itemDTO);
    }

    @GetMapping("/{id}")
    public Sacola verSacola(@PathVariable Long id) {
        return sacolaService.verSacola(id);
    }

    @PatchMapping("/fecharSacola/{sacolaId}")
    public Sacola fecharSacola(@PathVariable("sacolaId") Long sacolaId, @RequestParam("formaPagamento") int formaPagamento) {
        return sacolaService.fecharSacola(sacolaId, formaPagamento);
    }
}

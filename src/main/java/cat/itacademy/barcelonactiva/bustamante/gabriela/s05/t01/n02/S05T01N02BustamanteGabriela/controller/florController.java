package cat.itacademy.barcelonactiva.bustamante.gabriela.s05.t01.n02.S05T01N02BustamanteGabriela.controller;

import cat.itacademy.barcelonactiva.bustamante.gabriela.s05.t01.n02.S05T01N02BustamanteGabriela.exceptions.FlorNotFoundException;
import cat.itacademy.barcelonactiva.bustamante.gabriela.s05.t01.n02.S05T01N02BustamanteGabriela.model.domain.Flor;
import cat.itacademy.barcelonactiva.bustamante.gabriela.s05.t01.n02.S05T01N02BustamanteGabriela.model.dto.FlorDTO;
import cat.itacademy.barcelonactiva.bustamante.gabriela.s05.t01.n02.S05T01N02BustamanteGabriela.model.services.FlorServicio;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flor") // Ruta base para todos los endpoints del controlador
public class florController {

    @Autowired
    private FlorServicio florServicio;

    @PostMapping("/add")
    public ResponseEntity<FlorDTO> createFlor(@RequestBody Flor flor) {
        Flor nuevaFlor = florServicio.crearFlor(flor);
        FlorDTO nuevaFlorDTO = florServicio.convertToDto(nuevaFlor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaFlorDTO);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFlor(@PathVariable int id, @RequestBody Flor florActualizada) {
        try {
            florServicio.updateFlor(id, florActualizada.getNombreFlor(), florActualizada.getPaisFlor());
            return ResponseEntity.ok("Flor actualizada exitosamente");
        } catch (FlorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FlorDTO>> getAllFlor() {
        List<FlorDTO> flores = florServicio.getAllFlor();
        return ResponseEntity.ok(flores);

    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Flor> searchFlorById(@PathVariable int id) {
        Optional<Flor> florOptional = florServicio.getFlorById(id);

        return florOptional
                .map(flor -> ResponseEntity.ok().body(flor))  // si la flor existe
                .orElseGet(() -> ResponseEntity.notFound().build());  //si la flor no existe

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFlorById(@PathVariable int id) {
        florServicio.deleteFlorById(id);
        return ResponseEntity.noContent().build();

    }


}

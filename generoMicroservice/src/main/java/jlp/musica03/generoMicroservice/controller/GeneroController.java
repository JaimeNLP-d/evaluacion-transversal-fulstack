package jlp.musica03.generoMicroservice.controller;

import jakarta.validation.Valid;
import jlp.musica03.generoMicroservice.dto.DtoGeneroRequest;
import jlp.musica03.generoMicroservice.model.Genero;
import jlp.musica03.generoMicroservice.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/generos")
@CrossOrigin(origins = "*")
public class GeneroController {

    @Autowired
    private GeneroService generoService;


    @GetMapping
    public ResponseEntity<List<Genero>> getGeneros(){
        List<Genero> generosRegistrados = generoService.findAll();
        return ResponseEntity.ok(generosRegistrados);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getGeneroById(@PathVariable Long id){
        Genero generoObjetivo = generoService.findById(id);
        return ResponseEntity.ok(generoObjetivo);
    }


    @PostMapping
    public ResponseEntity<?> postGenero(@Valid @RequestBody DtoGeneroRequest requestBody){
        Genero nuevaGenero = new Genero();
        nuevaGenero.setNombre( requestBody.getNombre() );
        nuevaGenero.setDescripcion( requestBody.getDescripcion() );
        Genero registroGenero = generoService.save(nuevaGenero, true);
        URI location =
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(nuevaGenero.getId())
                        .toUri();
        return ResponseEntity.created(location).body(registroGenero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putGeneroById(@PathVariable Long id,
                                            @Valid @RequestBody DtoGeneroRequest requestBody){
        Genero generoObjetivo = generoService.findById(id);
        generoObjetivo.setNombre( requestBody.getNombre() );
        generoObjetivo.setDescripcion( requestBody.getDescripcion() );

        Genero registroGenero = generoService.save(generoObjetivo, false);
        return ResponseEntity.ok(registroGenero);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGeneroById(@PathVariable Long id){
        generoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchGeneroById(@PathVariable Long id,
                                              @RequestBody DtoGeneroRequest requestBody){
        Genero generoObjetivo = generoService.findById(id);
        if( !( requestBody.getNombre().isEmpty() || requestBody.getNombre().isBlank() ) ) {
            generoObjetivo.setNombre(requestBody.getNombre());
        }
        if( !( requestBody.getDescripcion().isEmpty() || requestBody.getDescripcion().isBlank() ) ) {
            generoObjetivo.setDescripcion(requestBody.getDescripcion());
        }

        generoService.save(generoObjetivo, false);
        return ResponseEntity.noContent().build();

    }

}
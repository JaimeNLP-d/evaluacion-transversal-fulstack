package jlp.musica03.cancionMicroservice.controller;

import jakarta.validation.Valid;
import jlp.musica03.cancionMicroservice.dto.DtoCancionRequest;
import jlp.musica03.cancionMicroservice.model.Cancion;
import jlp.musica03.cancionMicroservice.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clase controller de práctica
 * Por JaimeNLP
 * Recibe solicitudes REST con respecto a la clase Cancion
 */
@RestController
@RequestMapping("/api/canciones")
@CrossOrigin(origins = "*")
public class CancionController {

    @Autowired
    private CancionService cancionService;


    @GetMapping
    public ResponseEntity< List<Cancion> > getCanciones(){
        System.out.println("Ejecucion de getCanciones()");
        List<Cancion> cancionesRegistradas = cancionService.findAll();
        return ResponseEntity.ok(cancionesRegistradas);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCancionById(@PathVariable Long id){
        System.out.println("Ejecucion de getCancionById()");
        Cancion cancionObjetivo = cancionService.findById(id);
        return ResponseEntity.ok(cancionObjetivo);
    }


    @PostMapping
    public ResponseEntity<?> postCancion(@Valid @RequestBody DtoCancionRequest requestBodyCancion){
        System.out.println("Ejecucion de postCancion()");
        Cancion nuevaCancion = new Cancion();
        nuevaCancion.setNombre( requestBodyCancion.getNombre() );
        nuevaCancion.setDuracionSegundos( requestBodyCancion.getDuracionSegundos() );
        nuevaCancion.setLetra( requestBodyCancion.getLetra() );
        nuevaCancion.setGeneroId( requestBodyCancion.getGeneroId() );
        Cancion registroCancion = cancionService.save(nuevaCancion, true);
        URI location =
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(nuevaCancion.getId())
                        .toUri();
        return ResponseEntity.created(location).body(registroCancion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putCancionById(@PathVariable Long id,
                                            @Valid @RequestBody DtoCancionRequest requestBodyCancion){
        System.out.println("Ejecucion de putCancionById()");
        Cancion cancionObjetivo = cancionService.findById(id);
        cancionObjetivo.setNombre( requestBodyCancion.getNombre() );
        cancionObjetivo.setDuracionSegundos( requestBodyCancion.getDuracionSegundos() );
        cancionObjetivo.setLetra( requestBodyCancion.getLetra() );
        cancionObjetivo.setGeneroId( requestBodyCancion.getGeneroId() );

        Cancion registroCancion = cancionService.save(cancionObjetivo, false);
        return ResponseEntity.ok(registroCancion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCancionById(@PathVariable Long id){
        System.out.println("Ejecucion de deleteCancionById()");
        cancionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchCancionById(@PathVariable Long id,
                                              @RequestBody DtoCancionRequest requestBodyCancion){
        System.out.println("Ejecucion de patchCancionById()");
        Cancion cancionObjetivo = cancionService.findById(id);
        if( !( requestBodyCancion.getNombre().isEmpty() || requestBodyCancion.getNombre().isBlank() ) ) {
            cancionObjetivo.setNombre(requestBodyCancion.getNombre());
        }
        if( !( requestBodyCancion.getDuracionSegundos() == null ) ) {
            cancionObjetivo.setDuracionSegundos(requestBodyCancion.getDuracionSegundos());
        }
        if( !( requestBodyCancion.getLetra().isEmpty() || requestBodyCancion.getLetra().isBlank() ) ) {
            cancionObjetivo.setLetra(requestBodyCancion.getLetra());
        }
        if( !( requestBodyCancion.getGeneroId() == null ) ) {
            cancionObjetivo.setGeneroId(requestBodyCancion.getGeneroId());
        }

        cancionService.save(cancionObjetivo, false);
        return ResponseEntity.noContent().build();

    }

    /**
     * Método que ordena el convertir a 'null' el valor del atributo 'letra'
     * @param id
     * @return
     */
    @PatchMapping("/quitar_letra/{id}")
    public ResponseEntity<?> patchCancionQuitarLetraById(@PathVariable Long id){
        System.out.println("Ejecucion de patchCancionQuitarLetraById()");
        Cancion cancionObjetivo = cancionService.findById(id);
        cancionObjetivo.setLetra(null);
        cancionService.save(cancionObjetivo, false);
        return ResponseEntity.noContent().build();
    }


}

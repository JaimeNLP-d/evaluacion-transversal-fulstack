package jlp.musica03.grupoMicroservice.controller;

import jakarta.validation.Valid;
import jlp.musica03.grupoMicroservice.dto.DtoGrupoRequest;
import jlp.musica03.grupoMicroservice.model.Grupo;
import jlp.musica03.grupoMicroservice.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/grupo")
@CrossOrigin(origins = "*")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;


    @GetMapping
    public ResponseEntity<List<Grupo>> getGrupo(){
        List<Grupo> gruposRegistrados = grupoService.findAll();
        return ResponseEntity.ok(gruposRegistrados);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getGrupoById(@PathVariable Long id){
        Grupo grupoObjetivo = grupoService.findById(id);
        return ResponseEntity.ok(grupoObjetivo);
    }


    @PostMapping
    public ResponseEntity<?> postGrupo(@Valid @RequestBody DtoGrupoRequest requestBody){
        Grupo nuevaGrupo = new Grupo();
        nuevaGrupo.setNombre( requestBody.getNombre() );
        Grupo registroGrupo = grupoService.save(nuevaGrupo, true);
        URI location =
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(nuevaGrupo.getId())
                        .toUri();
        return ResponseEntity.created(location).body(registroGrupo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putGrupoById(@PathVariable Long id,
                                           @Valid @RequestBody DtoGrupoRequest requestBody){
        Grupo grupoObjetivo = grupoService.findById(id);
        grupoObjetivo.setNombre( requestBody.getNombre() );

        Grupo registroGrupo = grupoService.save(grupoObjetivo, false);
        return ResponseEntity.ok(registroGrupo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGrupoById(@PathVariable Long id){
        grupoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchGrupoById(@PathVariable Long id,
                                             @RequestBody DtoGrupoRequest requestBody){
        Grupo grupoObjetivo = grupoService.findById(id);
        if( !( requestBody.getNombre().isEmpty() || requestBody.getNombre().isBlank() ) ) {
            grupoObjetivo.setNombre(requestBody.getNombre());
        }

        grupoService.save(grupoObjetivo, false);
        return ResponseEntity.noContent().build();

    }

}
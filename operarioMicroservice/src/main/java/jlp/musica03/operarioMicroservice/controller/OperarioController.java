package jlp.musica03.operarioMicroservice.controller;

import jakarta.validation.Valid;
import jlp.musica03.operarioMicroservice.dto.DtoOperarioRequest;
import jlp.musica03.operarioMicroservice.model.Operario;
import jlp.musica03.operarioMicroservice.service.OperarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/operarios")
@CrossOrigin(origins = "*")
public class OperarioController {

    @Autowired
    private OperarioService operarioService;


    @GetMapping
    public ResponseEntity<List<Operario>> getOperarios(){
        List<Operario> operariosRegistrados = operarioService.findAll();
        return ResponseEntity.ok(operariosRegistrados);
    }


    @GetMapping("/{rut}")
    public ResponseEntity<?> getOperarioById(@PathVariable String rut){
        Operario operarioObjetivo = operarioService.findById(rut);
        return ResponseEntity.ok(operarioObjetivo);
    }


    @PostMapping
    public ResponseEntity<?> postOperario(@Valid @RequestBody DtoOperarioRequest requestBody){
        Operario nuevaOperario = new Operario();
        nuevaOperario.setNombre( requestBody.getNombre() );
        nuevaOperario.setTelefono( requestBody.getTelefono() );
        Operario registroOperario = operarioService.save(nuevaOperario, true);
        URI location =
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(nuevaOperario.getRut())
                        .toUri();
        return ResponseEntity.created(location).body(registroOperario);
    }

    @PutMapping("/{rut}")
    public ResponseEntity<?> putOperarioById(@PathVariable String rut,
                                           @Valid @RequestBody DtoOperarioRequest requestBody){
        Operario operarioObjetivo = operarioService.findById(rut);
        operarioObjetivo.setNombre( requestBody.getNombre() );
        operarioObjetivo.setTelefono( requestBody.getTelefono() );

        Operario registroOperario = operarioService.save(operarioObjetivo, false);
        return ResponseEntity.ok(registroOperario);
    }

    @DeleteMapping("/{rut}")
    public ResponseEntity<?> deleteOperarioById(@PathVariable String rut){
        operarioService.delete(rut);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchOperarioById(@PathVariable String rut,
                                             @RequestBody DtoOperarioRequest requestBody){
        Operario operarioObjetivo = operarioService.findById(rut);
        if( !( requestBody.getNombre().isEmpty() || requestBody.getNombre().isBlank() ) ) {
            operarioObjetivo.setNombre(requestBody.getNombre());
        }
        if( !( requestBody.getTelefono()==null ) ) {
            operarioObjetivo.setTelefono(requestBody.getTelefono());
        }

        operarioService.save(operarioObjetivo, false);
        return ResponseEntity.noContent().build();

    }

}
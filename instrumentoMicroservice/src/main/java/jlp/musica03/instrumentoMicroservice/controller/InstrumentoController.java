package jlp.musica03.instrumentoMicroservice.controller;

import jakarta.validation.Valid;
import jlp.musica03.instrumentoMicroservice.dto.DtoInstrumentoRequest;
import jlp.musica03.instrumentoMicroservice.model.Instrumento;
import jlp.musica03.instrumentoMicroservice.service.InstrumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/instrumentos")
@CrossOrigin(origins = "*")
public class InstrumentoController {

    @Autowired
    private InstrumentoService instrumentoService;


    @GetMapping
    public ResponseEntity<List<Instrumento>> getInstrumento(){
        List<Instrumento> instrumentosRegistrados = instrumentoService.findAll();
        return ResponseEntity.ok(instrumentosRegistrados);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getGeneroById(@PathVariable Long id){
        Instrumento instrumentoObjetivo = instrumentoService.findById(id);
        return ResponseEntity.ok(instrumentoObjetivo);
    }


    @PostMapping
    public ResponseEntity<?> postInstrumento(@Valid @RequestBody DtoInstrumentoRequest requestBody){
        Instrumento nuevaInstrumento = new Instrumento();
        nuevaInstrumento.setNombre( requestBody.getNombre() );
        nuevaInstrumento.setDescripcion( requestBody.getDescripcion() );
        Instrumento registroInstrumento = instrumentoService.save(nuevaInstrumento, true);
        URI location =
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(nuevaInstrumento.getId())
                        .toUri();
        return ResponseEntity.created(location).body(registroInstrumento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putInstrumentoById(@PathVariable Long id,
                                                @Valid @RequestBody DtoInstrumentoRequest requestBody){
        Instrumento instrumentoObjetivo = instrumentoService.findById(id);
        instrumentoObjetivo.setNombre( requestBody.getNombre() );
        instrumentoObjetivo.setDescripcion( requestBody.getDescripcion() );

        Instrumento registroInstrumento = instrumentoService.save(instrumentoObjetivo, false);
        return ResponseEntity.ok(registroInstrumento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInstrumentoById(@PathVariable Long id){
        instrumentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchInstrumentoById(@PathVariable Long id,
                                             @RequestBody DtoInstrumentoRequest requestBody){
        Instrumento instrumentoObjetivo = instrumentoService.findById(id);
        if( !( requestBody.getNombre().isEmpty() || requestBody.getNombre().isBlank() ) ) {
            instrumentoObjetivo.setNombre(requestBody.getNombre());
        }
        if( !( requestBody.getDescripcion().isEmpty() || requestBody.getDescripcion().isBlank() ) ) {
            instrumentoObjetivo.setDescripcion(requestBody.getDescripcion());
        }

        instrumentoService.save(instrumentoObjetivo, false);
        return ResponseEntity.noContent().build();

    }

}